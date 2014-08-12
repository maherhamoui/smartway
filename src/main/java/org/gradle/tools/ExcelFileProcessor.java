package org.gradle.tools;

import java.util.List;

import org.gradle.business.Contact;
import org.primefaces.model.UploadedFile;

public interface ExcelFileProcessor {
	public List<Contact> processXl(UploadedFile file);

}
