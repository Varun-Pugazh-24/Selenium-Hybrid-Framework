package com.tricentis.sampleapp.TestEngine;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.tricentis.sampleapp.Base.Base;

public class KeywordEngine extends Base {

	public WebDriver driver;
	public static Properties prop;

	public static Workbook book;
	public static Sheet sheet;
	public WebElement element;
	Select select;
	
	HashMap<String,String> assertData = new HashMap<String,String>();

	String testStep = null;
	String value = null;
	

	@SuppressWarnings("unchecked")
	public <T> HashMap<T,T> startExecution(String sheetName) {

		String locatorType = null;
		String locatorValue = null;

		FileInputStream file = null;
		try {
			file = new FileInputStream(SCENARIO_SHEET_PATH);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			book = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		sheet = book.getSheet(sheetName);
		int k = 0;
		for (int i = 0; i < sheet.getLastRowNum(); i++) {

			try {
					
				testStep = sheet.getRow(i + 1).getCell(k + 0).toString().trim();
				locatorType = sheet.getRow(i + 1).getCell(k + 1).toString().trim();
				locatorValue = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
				String action = sheet.getRow(i + 1).getCell(k + 3).toString().trim();
				value = sheet.getRow(i + 1).getCell(k + 4).toString().trim();

				switch (action) {
				case "open browser":
					prop = Base.init_properties();

					if (value.isEmpty() || value.equalsIgnoreCase("NA")) {

						driver = this.init_driver(prop.getProperty("browser"));

					} else {

						driver = this.init_driver(value);
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
					driver.quit();
					break;

				default:
					break;
				}
				
				
				Thread.sleep(2000);
				switch (locatorType) {
				case "id":
					element = driver.findElement(By.id(locatorValue));
					locatorAction( action, value);
					locatorType = null;
					break;
					
				case "xpath":
					element = driver.findElement(By.xpath(locatorValue));
					locatorAction( action, value);
					locatorType = null;
					break;
					
				case "class":
					element = driver.findElement(By.className(locatorValue));
					locatorAction( action, value);
					locatorType = null;
					break;
					
				case "name":
					element = driver.findElement(By.name(locatorValue));
					locatorAction( action, value);
					locatorType = null;
					break;
					
				case "css":
					element = driver.findElement(By.cssSelector(locatorValue));
					locatorAction( action, value);
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

	private void locatorAction(String action, String value) throws Exception {
		

		if (action.equalsIgnoreCase("sendkeys")) {
			element.clear();
			element.sendKeys(value);

		} else if (action.equalsIgnoreCase("click")) {
			element.click();
		}else if (action.equalsIgnoreCase("isDisplayed")) {
			Thread.sleep(3000);
			element.isDisplayed();
		}else if (action.equalsIgnoreCase("getText")) {
			Thread.sleep(3000);
			String elementText = element.getText();
			assertData.put(elementText,value);
		}else if (action.equalsIgnoreCase("isSelected")) {
			Thread.sleep(3000);
			element.isSelected();
		}else if (action.equalsIgnoreCase("isEnabled")) {
			Thread.sleep(3000);
			element.isEnabled();
		}else if (action.equalsIgnoreCase("select")) {
			Thread.sleep(3000);
			 select = new Select(element);
			 select.selectByValue(value);
		}
	}

}
