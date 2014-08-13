package org.gradle.tools.excelhandling;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.model.UploadedFile;

/**
 * chooses the type of Workbook depending on the extension of the excel file (xls or xlsx)
 * 
 * @author arma
 *
 */
public class ExtentionHandler {
	private Workbook workbook = null;
	private Sheet sheet = null;

	/**
	 * chooses the suitable workbook implementation
	 * 
	 * @param file
	 *            the excel file
	 * @return the suitable workbook implementation
	 */
	public Sheet handleFile(UploadedFile file) {
		int i = file.getFileName().lastIndexOf('.');

		String extension = "";
		if (i > 0) {
			extension = file.getFileName().substring(i + 1);
		}
		try {
			if (extension.equals("xlsx")) {

				workbook = new XSSFWorkbook(file.getInputstream());

				sheet = (XSSFSheet) workbook.getSheetAt(0);

			} else if (extension.equals("xls")) {
				workbook = new HSSFWorkbook(file.getInputstream());
				sheet = (HSSFSheet) workbook.getSheetAt(0);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sheet;
	}

}
