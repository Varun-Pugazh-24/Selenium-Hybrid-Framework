package com.tricentis.sampleapp.Test;

import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.tricentis.sampleapp.Base.DriverFactory;
import com.tricentis.sampleapp.BaseTest.BaseTest;
import com.tricentis.sampleapp.Utilities.ActionEngine;
import com.tricentis.sampleapp.Utilities.ExtentTestNGListeners;

@Listeners(ExtentTestNGListeners.class)
public class Test2 extends BaseTest {

	@Test
	public void TestCase1() throws Throwable {

		

		ActionEngine ae = new ActionEngine();

		ae.click_custom(DriverFactory.getInstance().getDriver().findElement(By.linkText("Automobile")),
				"Auto Mobile Link");
		Thread.sleep(2000);
		ae.selectDropDownByValue_custom(DriverFactory.getInstance().getDriver().findElement(By.id("make")), "Make",
				"Audi");
		ae.sendKeys_custom(DriverFactory.getInstance().getDriver().findElement(By.id("engineperormance")),
				"EnginePerf", "125");
		

	}

	@Test
	public void TestCase2() throws Throwable {

		
		ActionEngine ae = new ActionEngine();

		ae.click_custom(DriverFactory.getInstance().getDriver().findElement(By.linkText("Automobile")),
				"Auto Mobile Link");
		Thread.sleep(2000);
		ae.selectDropDownByValue_custom(DriverFactory.getInstance().getDriver().findElement(By.id("make")), "Make",
				"Audi");
		ae.sendKeys_custom(DriverFactory.getInstance().getDriver().findElement(By.id("engineperformance")),
				"EnginePerf", "125");


	}

}
