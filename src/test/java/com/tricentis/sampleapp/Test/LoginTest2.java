package com.tricentis.sampleapp.Test;

import org.testng.annotations.Test;

import com.tricentis.sampleapp.TestEngine.KeywordEngine;
import com.tricentis.sampleapp.Utilities.ReadWriteExcel;
import com.tricentis.sampleapp.Utilities.ReadWriteExcel.Builder;

public class LoginTest2 extends KeywordEngine {

	public KeywordEngine engine;

	@Test
	public void loginTestScenario() {
		
		Builder ExcelBuilder = new ReadWriteExcel.Builder(SCENARIO_SHEET_PATH).setSheetName("TC01");
		System.out.println(ExcelBuilder.build().GetRowCount());

		System.out.println(ExcelBuilder.setRow(1).setColumn(0)
				.build().ReadFromExcel());
		
		
		ExcelBuilder.setRow(1).setColumn(5).setInputToWrite("Passed New")
		.build().WriteToExcel();

	}

}
