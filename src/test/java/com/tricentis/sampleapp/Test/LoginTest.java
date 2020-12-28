package com.tricentis.sampleapp.Test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tricentis.sampleapp.TestEngine.KeywordEngine;

public class LoginTest extends KeywordEngine {

	@Test(dataProvider = "TestSuiteSheet")
	public void TestCase(String TestCase, String YesorNo) {
		if (YesorNo.equalsIgnoreCase("yes")) {
			KeywordEngine.startExecution(TestCase).forEach((actual, expected) -> Assert.assertEquals(actual, expected));
		}

	}

	@DataProvider(name = "TestSuiteSheet")
	public Object[][] TestSuiteSheet() {

		return getTestSuiteArray("TestSuite");
	}

	

}
