package org.gradle.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.gradle.hbm.Contact;
import org.primefaces.model.UploadedFile;

public class FileXlReader {

	public List<Contact> readXl(UploadedFile file) {
		String extension = "";
		List<Contact> contacts=new ArrayList<Contact>();
		int i = file.getFileName().lastIndexOf('.');
		if (i > 0) {
			extension = file.getFileName().substring(i + 1);

		}
		if (extension.equals("xlsx")) {

		} else if (extension.equals("xls")) {

			try {
				HSSFWorkbook workbook = new HSSFWorkbook(file.getInputstream());
				HSSFSheet sheet = workbook.getSheetAt(0);
				Iterator<Row> rowIterator = sheet.iterator();
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						
						Cell cell = cellIterator.next();
						switch(cell.getCellType()) {
		                case Cell.CELL_TYPE_BOOLEAN:
		                    System.out.print(cell.getBooleanCellValue() + "\t\t");
		                    break;
		                case Cell.CELL_TYPE_NUMERIC:
		                    System.out.print(cell.getNumericCellValue() + "\t\t");
		                    break;
		                case Cell.CELL_TYPE_STRING:
		                    System.out.print(cell.getStringCellValue() + "\t\t");
		                    break;
		            }
						
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return null;
	}
}
