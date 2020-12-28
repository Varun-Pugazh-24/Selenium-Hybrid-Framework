package com.tricentis.sampleapp.Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadWriteExcel {

	public static Workbook book;
	public static Sheet sheet;
	public static FileInputStream FIS;
	public static FileOutputStream FOUT;

	String SheetName;
	String input;
	int Row;
	int Column;
	String ExcelPath;

	private ReadWriteExcel(Builder builder) {
		this.SheetName = builder.SheetName;
		this.input = builder.input;
		this.Row = builder.Row;
		this.Column = builder.Column;
		this.ExcelPath = builder.ExcelPath;
	}

	public void WriteToExcel() {

		try {
			FIS = new FileInputStream(this.getExcelPath());
			book = WorkbookFactory.create(FIS);
			sheet = book.getSheet(this.getSheetName());
			sheet.getRow(this.getRow()).createCell(this.getColumn()).setCellValue(this.getInput());
			FOUT = new FileOutputStream(this.getExcelPath());
			book.write(FOUT);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public String ReadFromExcel() {

		try {
			FIS = new FileInputStream(this.getExcelPath());
			book = WorkbookFactory.create(FIS);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		sheet = book.getSheet(this.getSheetName());
		String Data = (String) sheet.getRow(this.getRow()).getCell(this.getColumn()).getStringCellValue().trim();

		return Data;

	}

	public int GetRowCount() {

		try {
			FIS = new FileInputStream(this.getExcelPath());
			book = WorkbookFactory.create(FIS);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

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

	public static class Builder {

		private String SheetName;
		private String input;
		private int Row;
		private int Column;
		private String ExcelPath;

		public Builder(String ExcelPath) {

			this.ExcelPath = ExcelPath;
		}

		public Builder setSheetName(String SheetName) {

			this.SheetName = SheetName;

			return this;
		}

		public Builder setRow(int Row) {

			this.Row = Row;

			return this;
		}

		public Builder setColumn(int Column) {

			this.Column = Column;

			return this;
		}

		public Builder setInputToWrite(String input) {

			this.input = input;

			return this;
		}

		public ReadWriteExcel build() {
			return new ReadWriteExcel(this);
		}

	}

}
