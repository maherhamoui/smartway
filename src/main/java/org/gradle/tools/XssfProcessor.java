package org.gradle.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.gradle.hbm.Contact;
import org.primefaces.model.UploadedFile;

public class XssfProcessor implements ExcelFileProcessor {
	@Override
	public List<Contact> processXl(UploadedFile file) {
		// TODO Auto-generated method stub
		List<Contact> contacts = new ArrayList<Contact>();
		int i = file.getFileName().lastIndexOf('.');
		int nameIndex = 0;
		int dobIndex = 0;
		int emailIndex = 0;
		int phoneIndex = 0;
		Row firstRow;

		try {
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputstream());
			XSSFSheet sheet = workbook.getSheetAt(0);
			firstRow = sheet.getRow(0);
			for (Cell header : firstRow) {
				if (header.getStringCellValue().contains("Name")) {
					nameIndex = header.getColumnIndex();
				} else if (header.getStringCellValue().contains("DOB")) {
					dobIndex = header.getColumnIndex();
				} else if (header.getStringCellValue().contains("email")) {
					emailIndex = header.getColumnIndex();
				} else if (header.getStringCellValue().contains("phone")) {
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
								con.setDob(cell.getDateCellValue().toString());
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
			for (Contact cont : contacts) {
				System.out.println(cont.getName());
				System.out.println(cont.getEmail());
				System.out.println(cont.getDob());
				System.out.println(cont.getPhone());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return contacts;
	}

}
