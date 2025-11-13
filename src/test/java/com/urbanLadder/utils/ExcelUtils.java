package com.urbanLadder.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	String fileName;
	static FileInputStream fis;
	static FileOutputStream fos;
	static XSSFWorkbook wb;
	static XSSFSheet sheet;
	static XSSFRow row;
	static XSSFCell cell;
	static CellStyle style;

public static	String excelFile =  System.getProperty("user.dir") + "\\src\\test\\resources\\DataSet.xlsx";
public static	String outputSheet = "Result";



	public ExcelUtils(String excelName) {
		this.fileName = System.getProperty("user.dir") + "\\src\\test\\resources\\" + excelName;
		try {
			fis = new FileInputStream(new File(fileName));
			wb = new XSSFWorkbook(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getRowCount(String sheetName) {
		sheet = wb.getSheet(sheetName);
		return sheet.getPhysicalNumberOfRows();
	}

	public int getColumnCount(String sheetName) {
		sheet = wb.getSheet(sheetName);
		return sheet.getRow(0).getLastCellNum();
	}

	public String getCellData(String sheetName, int rownum, int columnum) {
		sheet = wb.getSheet(sheetName);
		Cell cell = sheet.getRow(rownum).getCell(columnum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		try {
			return new DataFormatter().formatCellValue(cell);
		} catch (Exception e) {
			e.printStackTrace();
			return "Missing Data";
		}
	}

	public static void setCellData(String xlfile, String xlsheet, int rownum, int colnum, String data)
			throws IOException {
		fis = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheet(xlsheet);
		if (sheet.getRow(rownum) == null) {
			sheet.createRow(rownum);
		}
		row = sheet.getRow(rownum);

		cell = row.createCell(colnum);
		cell.setCellValue(data);
		fos = new FileOutputStream(xlfile);
		wb.write(fos);
		wb.close();
		fis.close();
		fos.close();

	}

	public void fillGreenColor(String sheetName, int rownum, int columnIndex) throws IOException {
		fillColor(sheetName, rownum, columnIndex, IndexedColors.GREEN);
	}

	public void fillRedColor(String sheetName, int rownum, int columnIndex) throws IOException {
		fillColor(sheetName, rownum, columnIndex, IndexedColors.RED);
	}

	private void fillColor(String sheetName, int rownum, int columnIndex, IndexedColors color) throws IOException {
		sheet = wb.getSheet(sheetName);
		Row row = sheet.getRow(rownum);
		if (row == null)
			row = sheet.createRow(rownum);
		Cell cell = row.getCell(columnIndex);
		if (cell == null)
			cell = row.createCell(columnIndex);
		style = wb.createCellStyle();
		// Set fill color
		style.setFillForegroundColor(color.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		// Set borders
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		// Set alignment
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		cell.setCellStyle(style);
		fos = new FileOutputStream(fileName);
		wb.write(fos);
		fos.close();
	}

	public static void  closeFile() {
		try {
			if (fos != null)
				fos.close();
			if (wb != null)
				wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}