package org.gradle.tools;

import java.util.List;

import org.gradle.hbm.Contact;
import org.primefaces.model.UploadedFile;

public interface ExcelFileProcessor {
	public List<Contact> processXl(UploadedFile file);

}
