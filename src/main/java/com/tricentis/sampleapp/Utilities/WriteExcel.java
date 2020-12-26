package com.tricentis.sampleapp.Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteExcel {

	public static Workbook book;
	public static Sheet sheet;

	String SheetName;
	String input;
	int Row;
	int Column;
	String ExcelPath;

	private WriteExcel() {

	}

	public void WriteExcelData() {

		try {
			FileInputStream FIS = new FileInputStream(this.getExcelPath());
			book = WorkbookFactory.create(FIS);
			sheet = book.getSheet(this.getSheetName());
			sheet.getRow(this.getRow()).createCell(this.getColumn()).setCellValue(this.getInput());
			FileOutputStream FOUT = new FileOutputStream(this.getExcelPath());
			book.write(FOUT);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

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

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
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

	@Override
	public String toString() {
		return "WriteExcel [SheetName=" + SheetName + ", input=" + input + ", Row=" + Row + ", Column=" + Column
				+ ", ExcelPath=" + ExcelPath + "]";
	}

	public static class WriteBuilder {

		private String SheetName;
		private String input;
		private int Row;
		private int Column;
		private String ExcelPath;

		public WriteBuilder(String ExcelPath) {

			this.ExcelPath = ExcelPath;
		}

		public WriteBuilder setSheetName(String SheetName) {

			this.SheetName = SheetName;

			return this;
		}

		public WriteBuilder setRow(int Row) {

			this.Row = Row;

			return this;
		}

		public WriteBuilder setColumn(int Column) {

			this.Column = Column;

			return this;
		}

		public WriteExcel setValue(String input) {

			this.input = input;
			WriteExcel we = new WriteExcel();
			we.setSheetName(this.SheetName);
			we.setInput(this.input);
			we.setRow(this.Row);
			we.setColumn(this.Column);
			we.setExcelPath(this.ExcelPath);
			return we;
		}

	}

}
