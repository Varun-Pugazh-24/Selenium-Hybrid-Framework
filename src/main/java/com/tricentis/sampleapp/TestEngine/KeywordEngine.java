package com.tricentis.sampleapp.TestEngine;

import java.util.HashMap;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.tricentis.sampleapp.Base.Base;
import com.tricentis.sampleapp.Base.BrowserFactory;
import com.tricentis.sampleapp.Base.DriverFactory;
import com.tricentis.sampleapp.Utilities.ReadWriteExcel;
import com.tricentis.sampleapp.Utilities.ReadWriteExcel.Builder;

public class KeywordEngine extends Base {

	public static WebDriver driver;
	public static Properties prop;

	public static Workbook book;
	public static Sheet sheet;
	public static WebElement element;
	static Select select;

	static HashMap<String, String> assertData = new HashMap<String, String>();

	static String testStep = null;
	public static String value = null;
	public static String action = null;

	@SuppressWarnings("unchecked")
	public static <T> HashMap<T, T> startExecution(String sheetName) {
 
		String locatorType = null;
		String locatorValue = null;

		Builder ExcelBuilder = new ReadWriteExcel.Builder(SCENARIO_SHEET_PATH).setSheetName(sheetName);

		 int RowCount = ExcelBuilder.build().GetRowCount();

		for (int i = 0; i < RowCount; i++) {

			try {

				testStep = ExcelBuilder.setRow(i + 1).setColumn(0).build().ReadFromExcel();
				locatorType = ExcelBuilder.setRow(i + 1).setColumn(1).build().ReadFromExcel();
				locatorValue = ExcelBuilder.setRow(i + 1).setColumn(2).build().ReadFromExcel();
				action = ExcelBuilder.setRow(i + 1).setColumn(3).build().ReadFromExcel();
				value = ExcelBuilder.setRow(i + 1).setColumn(4).build().ReadFromExcel();

				switch (action) {
				case "open browser":
					prop = Base.init_properties();

					if (value.isEmpty() || value.equalsIgnoreCase("NA")) {
						
						DriverFactory.getInstance().setDriver(BrowserFactory.init_driver(prop.getProperty("browser")));
						driver = DriverFactory.getInstance().getDriver();


					} else {
						DriverFactory.getInstance().setDriver(BrowserFactory.init_driver(value));
						driver = DriverFactory.getInstance().getDriver();
					}
					break;

				case "enter url":

					if (value.isEmpty() || value.equalsIgnoreCase("NA")) {

						driver.get(prop.getProperty("url"));
						Thread.sleep(2000);

					} else {

						driver.get(value);
						Thread.sleep(2000);
					}
					break;

				case "quit":
					Thread.sleep(2000);
					DriverFactory.getInstance().getDriver().quit();
					break;

				default:
					break;
				}

				Thread.sleep(2000);
				switch (locatorType) {
				case "id":
					element = driver.findElement(By.id(locatorValue));
					locatorAction(action, value);
					locatorType = null;
					break;

				case "xpath":
					element = driver.findElement(By.xpath(locatorValue));
					locatorAction(action, value);
					locatorType = null;
					break;

				case "class":
					element = driver.findElement(By.className(locatorValue));
					locatorAction(action, value);
					locatorType = null;
					break;

				case "name":
					element = driver.findElement(By.name(locatorValue));
					locatorAction(action, value);
					locatorType = null;
					break;

				case "css":
					element = driver.findElement(By.cssSelector(locatorValue));
					locatorAction(action, value);
					locatorType = null;
					break;

				case "linkText":

					element = driver.findElement(By.linkText(locatorValue));
					element.click();
					locatorType = null;
					break;

				case "partialLinkText":

					element = driver.findElement(By.partialLinkText(locatorValue));
					element.click();
					locatorType = null;
					break;

				default:
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return (HashMap<T, T>) assertData;

	}

	private static void locatorAction(String action, String value) throws Exception {

		if (action.equalsIgnoreCase("sendkeys")) {
			element.clear();
			element.sendKeys(value);

		} else if (action.equalsIgnoreCase("click")) {
			element.click();
		} else if (action.equalsIgnoreCase("isDisplayed")) {
			Thread.sleep(2000);
			element.isDisplayed();
		} else if (action.equalsIgnoreCase("getText")) {
			Thread.sleep(2000);
			String elementText = element.getText();
			assertData.put(elementText, value);
		} else if (action.equalsIgnoreCase("isSelected")) {
			Thread.sleep(2000);
			element.isSelected();
		} else if (action.equalsIgnoreCase("isEnabled")) {
			Thread.sleep(2000);
			element.isEnabled();
		} else if (action.equalsIgnoreCase("select")) {
			Thread.sleep(2000);
			select = new Select(element);
			select.selectByValue(value);
		}
	}

}
