package org.gradle.tools;

import java.util.List;

import org.gradle.hbm.Contact;
import org.primefaces.model.UploadedFile;

public class ExcelExtReader {
	private ExcelFileProcessor ep;

	public List<Contact> readFile(UploadedFile file) {
		String extension = "";
		int i = file.getFileName().lastIndexOf('.');
		if (i > 0) {
			extension = file.getFileName().substring(i + 1);
		}
		if (extension.equals("xlsx")) {
			ep = new XssfProcessor();
		} else if (extension.equals("xls")) {
			ep = new HssfProcessor();
		}
		return ep.processXl(file);
	}
}