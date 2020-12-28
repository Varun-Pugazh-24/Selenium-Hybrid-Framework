package com.tricentis.sampleapp.Base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.tricentis.sampleapp.Utilities.ReadWriteExcel;
import com.tricentis.sampleapp.Utilities.ReadWriteExcel.Builder;

public class Base extends Constants {
	static Properties prop;

	public static Properties init_properties() {

		File PropertyFile = new File(CONFIG_PROPERTIES_FILE_PATH);
		try {
			FileInputStream fis = new FileInputStream(PropertyFile);
			prop = new Properties();
			prop.load(fis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return prop;

	}

	public String[][] getTestSuiteArray(String sheetName) {
		Builder ExcelBuilder = new ReadWriteExcel.Builder(SCENARIO_SHEET_PATH).setSheetName(sheetName);
		int totalRows = ExcelBuilder.build().GetRowCount();
		int totalColumns = 2;
		int ci, cj;

		String[][] testSuiteSheetArray = new String[totalRows][totalColumns];

		ci = 0;

		for (int i = 1; i <= totalRows; i++, ci++) {

			cj = 0;

			for (int j = 1; j <= totalColumns; j++, cj++) {

				testSuiteSheetArray[ci][cj] = ExcelBuilder.setRow(i).setColumn(j).build().ReadFromExcel();

			}

		}

		return testSuiteSheetArray;
	}

}
