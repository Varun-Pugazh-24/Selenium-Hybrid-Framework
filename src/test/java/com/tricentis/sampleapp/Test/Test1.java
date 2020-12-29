package com.tricentis.sampleapp.Test;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.tricentis.sampleapp.Base.TestLogger;
import com.tricentis.sampleapp.TestEngine.KeywordEngine;
import com.tricentis.sampleapp.Utilities.ExtentTestNGListeners;

@Listeners(ExtentTestNGListeners.class)
public class Test1 extends KeywordEngine {

	
	@Test(dataProvider = "TestSuiteSheet")
	public void TestCase(String TestCase, String YesorNo) {
		TestLogger.startTestCase(TestCase);
		if (YesorNo.equalsIgnoreCase("yes")) {
			TestLogger.info("Run status is yes");
			KeywordEngine.startExecution(TestCase).forEach((actual, expected) -> Assert.assertEquals(actual, expected));
		}else {
			TestLogger.info("Run status is no so this test case will not run");
			throw new SkipException("Test Case Skipped");
			
			
		}   
		TestLogger.endTestCase(TestCase);
	}

	@DataProvider(name = "TestSuiteSheet")
	public Object[][] TestSuiteSheet() {

		return getTestDataSheet("TestSuite");
	}

}
