package com.tricentis.sampleapp.Test;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.tricentis.sampleapp.TestEngine.KeywordEngine;
import com.tricentis.sampleapp.Utilities.ReadExcel;
import com.tricentis.sampleapp.Utilities.WriteExcel;

public class LoginTest2 extends KeywordEngine {

	public KeywordEngine engine;
	HashMap<String, String> assertValue;

	@Test
	public void loginTestScenario() {

		System.out.println(new ReadExcel.ReadBuilder(SCENARIO_SHEET_PATH).getRow(1).getColumn(1)
				.getSheetName("testcase1").getValue().ReadExcelData());

		System.out.println(new ReadExcel.ReadBuilder(SCENARIO_SHEET_PATH)
				.getSheetName("testcase1").getValue().GetRowCount());
		
		
		new WriteExcel.WriteBuilder(SCENARIO_SHEET_PATH).setRow(1).setColumn(5).setSheetName("testcase1")
				.setValue("Passed").WriteExcelData();
		new WriteExcel.WriteBuilder(SCENARIO_SHEET_PATH).setRow(2).setColumn(5).setSheetName("testcase1")
				.setValue("Passed").WriteExcelData();
		new WriteExcel.WriteBuilder(SCENARIO_SHEET_PATH).setRow(3).setColumn(5).setSheetName("testcase1")
				.setValue("Passed").WriteExcelData();

	}

}
