package com.tricentis.sampleapp.Test;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.tricentis.sampleapp.Base.Base;
import com.tricentis.sampleapp.Base.DriverFactory;
import com.tricentis.sampleapp.Base.ExtentFactory;
import com.tricentis.sampleapp.Base.TestLogger;
import com.tricentis.sampleapp.TestEngine.KeywordEngine;
import com.tricentis.sampleapp.Utilities.ExtentTestNGListeners;

@Listeners(ExtentTestNGListeners.class)
public class Test1 {

	public static String tc = null;
	
	
	@Test(dataProvider = "TestSuiteSheet")
	public void TestCase(String testCase, String isSkipped) throws Throwable {
		tc = testCase;
		TestLogger.startTestCase(testCase);
		ExtentFactory.getInstance().getExtent().log(Status.PASS, testCase+" is Started");
		if (isSkipped.equalsIgnoreCase("no")) {
			KeywordEngine.startExecution(testCase);
		}else {
			TestLogger.warn(testCase+" is Skipped");
			ExtentFactory.getInstance().getExtent().log(Status.SKIP, testCase+" is Skipped");
			throw new SkipException("Test Case Skipped");
			
			
		}   
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		TestLogger.endTestCase(tc);
		try {
			DriverFactory.getInstance().closeBrowser();
		} catch (Exception e) {
		}
	}
	
	@DataProvider(name = "TestSuiteSheet")
	public Object[][] TestSuiteSheet() {

		return Base.getTestDataSheet("TestSuite");
	}
	
	

}
