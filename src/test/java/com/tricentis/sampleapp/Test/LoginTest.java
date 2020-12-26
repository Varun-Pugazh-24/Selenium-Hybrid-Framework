package com.tricentis.sampleapp.Test;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.tricentis.sampleapp.TestEngine.KeywordEngine;

import org.testng.Assert;

public class LoginTest {

	public KeywordEngine engine;
	HashMap<String, String> assertValue;

	@Test
	public void loginTestScenario() {
		engine = new KeywordEngine();
		engine.startExecution("TC01").forEach((actual, expected) -> Assert.assertEquals(actual, expected));

	}


}
