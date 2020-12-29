package com.tricentis.sampleapp.Test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tricentis.sampleapp.Base.TestLogger;
import com.tricentis.sampleapp.TestEngine.KeywordEngine;

public class LoginTest extends KeywordEngine {

	
	@Test(dataProvider = "TestSuiteSheet")
	public void TestCase(String TestCase, String YesorNo) {
		TestLogger.startTestCase(TestCase);
		if (YesorNo.equalsIgnoreCase("yes")) {
			TestLogger.info("Run status is yes");
			KeywordEngine.startExecution(TestCase).forEach((actual, expected) -> Assert.assertEquals(actual, expected));
		}else {
			TestLogger.info("Run status is no so this test case will not run");
			
		}
		TestLogger.endTestCase(TestCase);
	}

	@DataProvider(name = "TestSuiteSheet")
	public Object[][] TestSuiteSheet() {

		return getTestSuiteArray("TestSuite");
	}

}
