package org.gradle.beans;

import org.primefaces.model.UploadedFile;
/**
 * uploads the file and hold it in session
 * @author arma
 *
 */
public class UploadFileBean {
	private UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

}
