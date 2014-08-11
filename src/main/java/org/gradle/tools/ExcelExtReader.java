package org.gradle.tools;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.gradle.hbm.Contact;
import org.primefaces.model.UploadedFile;

public class ExcelExtReader {
	private ExcelFileProcessor ep;

	public List<Contact> readFile(UploadedFile file) {

		List<Contact> contacts = new ArrayList<Contact>();

		int nameIndex = 0;
		int dobIndex = 0;
		int emailIndex = 0;
		int phoneIndex = 0;
		Row firstRow;

		try {
			Workbook workbook;
			Sheet sheet = null;
			String extension = "";
			int i = file.getFileName().lastIndexOf('.');
			if (i > 0) {
				extension = file.getFileName().substring(i + 1);
			}
			if (extension.equals("xlsx")) {
				workbook = new XSSFWorkbook(file.getInputstream());
				sheet = (XSSFSheet) workbook.getSheetAt(0);

			} else if (extension.equals("xls")) {
				workbook = new HSSFWorkbook(file.getInputstream());
				sheet = (HSSFSheet) workbook.getSheetAt(0);
			}

			firstRow = sheet.getRow(0);
			for (Cell header : firstRow) {

				if (header.getStringCellValue().toLowerCase().contains("name")) {
					nameIndex = header.getColumnIndex();
				} else if (header.getStringCellValue().toLowerCase()
						.contains("dob")
						|| header.getStringCellValue().toLowerCase()
								.contains("date")) {
					dobIndex = header.getColumnIndex();
				} else if (header.getStringCellValue().toLowerCase()
						.contains("email")) {
					emailIndex = header.getColumnIndex();
				} else if (header.getStringCellValue().toLowerCase()
						.contains("phone")) {
					phoneIndex = header.getColumnIndex();
				}
			}

			for (int j = 1; j <= sheet.getLastRowNum(); j++) {
				Row row = sheet.getRow(j);
				Contact con = new Contact();
				for (Cell cell : row) {
					int type = cell.getColumnIndex();
					if (type == nameIndex) {
						con.setName(cell.getStringCellValue());
					} else if (type == dobIndex) {
						if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							if (DateUtil.isCellDateFormatted(cell)) {
								SimpleDateFormat sm = new SimpleDateFormat(
										"dd/MM/yyyy");
								String s = sm.format(cell.getDateCellValue());
								con.setDob(s);
							}
						}

					} else if (type == phoneIndex) {
						cell.setCellType(org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
						con.setPhone(cell.getStringCellValue());
					} else if (type == emailIndex) {
						con.setEmail(cell.getStringCellValue());
					}

				}
				contacts.add(con);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return contacts;
	}

}