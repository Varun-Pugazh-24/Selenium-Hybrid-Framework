package com.tricentis.sampleapp.TestEngine;

import java.util.Properties;

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
		 TestLogger.info("Excel started with sheet - "+sheetName);
		 TestLogger.info("Row Count is - "+RowCount);
		for (int i = 0; i < RowCount; i++) {

			try {

				testStep = ExcelBuilder.setRow(i + 1).setColumn(0).build().readFromExcel();
				locatorType = ExcelBuilder.setRow(i + 1).setColumn(1).build().readFromExcel();
				locatorValue = ExcelBuilder.setRow(i + 1).setColumn(2).build().readFromExcel();
				action = ExcelBuilder.setRow(i + 1).setColumn(3).build().readFromExcel();
				value = ExcelBuilder.setRow(i + 1).setColumn(4).build().readFromExcel();

				
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
						ExtentFactory.getInstance().getExtent().log(Status.PASS, "Entered URL - "+prop.getProperty("url"));
						TestLogger.info("Entered URL - "+prop.getProperty("url"));
						DriverFactory.getInstance().getDriver().get(prop.getProperty("url"));
						Thread.sleep(2000);

					} else {
						ExtentFactory.getInstance().getExtent().log(Status.PASS, "Entered URL - "+value);
						TestLogger.info("Entered URL - "+value);
						DriverFactory.getInstance().getDriver().get(value);
						Thread.sleep(2000);
					}
					break;

				default:
					break;
				}

				Thread.sleep(2000);
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
					actionEngine.click_custom(element, testStep);
					locatorType = null;
					break;

				case "partialLinkText":

					element = DriverFactory.getInstance().getDriver().findElement(By.partialLinkText(locatorValue));
					actionEngine.click_custom(element, testStep);
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

		if (action.equalsIgnoreCase("sendkeys")) {
			actionEngine.clear_custom(element, testStep);
			actionEngine.sendKeys_custom(element, testStep, value);
		} else if (action.equalsIgnoreCase("click")) {
			actionEngine.click_custom(element, testStep);
		} else if (action.equalsIgnoreCase("isDisplayed")) {
			Thread.sleep(2000);
			boolean isPresent = actionEngine.isElementPresent_custom(element, testStep);
			actionEngine.assertTrue_custom(isPresent, testStep);
		} else if (action.equalsIgnoreCase("getText")) {
			Thread.sleep(2000);
			String elementText = actionEngine.getText_custom(element, testStep);
			actionEngine.assertEqualsString_custom(elementText, value, testStep);
		} else if (action.equalsIgnoreCase("isSelected")) {
			Thread.sleep(2000);
			boolean isSelected = actionEngine.isElementSelected_custom(element, testStep);
			actionEngine.assertTrue_custom(isSelected, testStep);
		} else if (action.equalsIgnoreCase("isEnabled")) {
			Thread.sleep(2000);
			boolean isEnabled = actionEngine.isElementEnabled_custom(element, testStep);
			actionEngine.assertTrue_custom(isEnabled, testStep);
		} else if (action.equalsIgnoreCase("select")) {
			Thread.sleep(2000);
			actionEngine.selectDropDownByValue_custom(element, testStep, value);
		}
	}

}
