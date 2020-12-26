package com.tricentis.sampleapp.Utilities;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadExcel {

	public static Workbook book;
	public static Sheet sheet;

	String SheetName;
	int Row;
	int Column;
	String ExcelPath;

	private ReadExcel() {

	}

	public String ReadExcelData() {

		try {
			FileInputStream FIS = new FileInputStream(this.getExcelPath());
			book = WorkbookFactory.create(FIS);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		sheet = book.getSheet(this.getSheetName());
		String Data = (String) sheet.getRow(this.getRow()).getCell(this.getColumn()).getStringCellValue();

		return Data;

	}

	public int GetRowCount() {

		sheet = book.getSheet(this.getSheetName());
		int row = sheet.getLastRowNum();

		return row;
	}

	public String getSheetName() {
		return SheetName;
	}

	public void setSheetName(String sheetName) {
		SheetName = sheetName;
	}

	public int getRow() {
		return Row;
	}

	public void setRow(int row) {
		Row = row;
	}

	public int getColumn() {
		return Column;
	}

	public void setColumn(int column) {
		Column = column;
	}

	public String getExcelPath() {
		return ExcelPath;
	}

	public void setExcelPath(String excelPath) {
		ExcelPath = excelPath;
	}

	public static class ReadBuilder {

		private String SheetName;
		private int Row;
		private int Column;
		private String ExcelPath;

		public ReadBuilder(String ExcelPath) {

			this.ExcelPath = ExcelPath;
		}

		public ReadBuilder getSheetName(String SheetName) {

			this.SheetName = SheetName;

			return this;
		}

		public ReadBuilder getRow(int Row) {

			this.Row = Row;

			return this;
		}

		public ReadBuilder getColumn(int Column) {

			this.Column = Column;

			return this;
		}

		public ReadExcel getValue() {

			ReadExcel re = new ReadExcel();
			re.setSheetName(this.SheetName);
			re.setRow(this.Row);
			re.setColumn(this.Column);
			re.setExcelPath(this.ExcelPath);
			return re;
		}

	}

}
