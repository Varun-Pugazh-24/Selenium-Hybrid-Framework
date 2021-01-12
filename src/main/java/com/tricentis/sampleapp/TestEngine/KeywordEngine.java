package com.tricentis.sampleapp.TestEngine;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;
import com.tricentis.sampleapp.Base.Base;
import com.tricentis.sampleapp.Base.BrowserFactory;
import com.tricentis.sampleapp.Base.DriverFactory;
import com.tricentis.sampleapp.Base.ExtentFactory;
import com.tricentis.sampleapp.Base.TestLogger;
import com.tricentis.sampleapp.Utilities.ActionEngine;
import com.tricentis.sampleapp.Utilities.ExcelOperations;
import com.tricentis.sampleapp.Utilities.ExcelOperations.Builder;

public class KeywordEngine extends Base {

	public static Properties prop;

	public static Workbook book;
	public static Sheet sheet;
	public static WebElement element;
	static Select select;

	static String testStep = null;
	public static String value = null;
	public static String action = null;
	

	public static ActionEngine actionEngine = new ActionEngine();

	public static void startExecution(String sheetName) throws Throwable {

		String locatorType = null;
		String locatorValue = null;

		Builder ExcelBuilder = new ExcelOperations.Builder(SCENARIO_SHEET_PATH).setSheetName(sheetName);
		

		int RowCount = ExcelBuilder.build().getRowCount();
		TestLogger.info("Excel started with sheet - " + sheetName);
		TestLogger.info("Row Count is - " + RowCount);
		for (int i = 0; i <= RowCount; i++) {

			try {

				testStep = ExcelBuilder.setRow(i).setColumn(0).build().readFromExcel();
				locatorType = ExcelBuilder.setRow(i).setColumn(1).build().readFromExcel();
				locatorValue = ExcelBuilder.setRow(i).setColumn(2).build().readFromExcel();
				action = ExcelBuilder.setRow(i).setColumn(3).build().readFromExcel();
				value = ExcelBuilder.setRow(i).setColumn(4).build().readFromExcel();

				switch (action) {
				case "open browser":
					prop = Base.initProperties();

					if (value.isEmpty() || value.equalsIgnoreCase("NA")) {
						DriverFactory.getInstance().setDriver(BrowserFactory.initDriver(prop.getProperty("browser")));

					} else {
						DriverFactory.getInstance().setDriver(BrowserFactory.initDriver(value));

					}
					break;

				case "enter url":

					if (value.isEmpty() || value.equalsIgnoreCase("NA")) {
						ExtentFactory.getInstance().getExtent().log(Status.PASS,
								"Entered URL - " + prop.getProperty("url"));
						TestLogger.info("Entered URL - " + prop.getProperty("url"));
						DriverFactory.getInstance().getDriver().get(prop.getProperty("url"));
						DriverFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(15,
								TimeUnit.SECONDS);

					} else {
						ExtentFactory.getInstance().getExtent().log(Status.PASS, "Entered URL - " + value);
						TestLogger.info("Entered URL - " + value);
						DriverFactory.getInstance().getDriver().get(value);
						DriverFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(15,
								TimeUnit.SECONDS);

					}
					break;

				default:
					break;
				}

				switch (locatorType) {
				case "id":
					element = DriverFactory.getInstance().getDriver().findElement(By.id(locatorValue));
					locatorAction(action, value);
					locatorType = null;
					break;

				case "xpath":
					element = DriverFactory.getInstance().getDriver().findElement(By.xpath(locatorValue));
					locatorAction(action, value);
					locatorType = null;
					break;

				case "class":
					element = DriverFactory.getInstance().getDriver().findElement(By.className(locatorValue));
					locatorAction(action, value);
					locatorType = null;
					break;

				case "name":
					element = DriverFactory.getInstance().getDriver().findElement(By.name(locatorValue));
					locatorAction(action, value);
					locatorType = null;
					break;

				case "css":
					element = DriverFactory.getInstance().getDriver().findElement(By.cssSelector(locatorValue));
					locatorAction(action, value);
					locatorType = null;
					break;

				case "linkText":

					element = DriverFactory.getInstance().getDriver().findElement(By.linkText(locatorValue));
					actionEngine.click(element, testStep);
					locatorType = null;
					break;

				case "partialLinkText":

					element = DriverFactory.getInstance().getDriver().findElement(By.partialLinkText(locatorValue));
					actionEngine.click(element, testStep);
					locatorType = null;
					break;

				default:
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	private static void locatorAction(String action, String value) throws Throwable {

		switch (action) {
		case "sendkeys":
			actionEngine.clear(element, testStep);
			actionEngine.sendKeys(element, testStep, value);

			break;

		case "click":
			actionEngine.click(element, testStep);

			break;

		case "isDisplayed":
			boolean isPresent = actionEngine.isDisplayed(element, testStep);
			actionEngine.assertTrue(isPresent, testStep);

			break;

		case "getText":
			String elementText = actionEngine.getText(element, testStep);
			actionEngine.assertEqualsString(elementText, value, testStep);

			break;

		case "isSelected":
			boolean isSelected = actionEngine.isSelected(element, testStep);
			actionEngine.assertTrue(isSelected, testStep);

			break;

		case "isEnabled":
			boolean isEnabled = actionEngine.isEnabled(element, testStep);
			actionEngine.assertTrue(isEnabled, testStep);

			break;

		case "select":
			actionEngine.selectDropDownByValue(element, testStep, value);

			break;

		default:
			break;
		}

	}

}
