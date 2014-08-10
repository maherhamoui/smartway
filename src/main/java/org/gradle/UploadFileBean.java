package org.gradle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 

import org.primefaces.model.UploadedFile;



@ManagedBean
public class UploadFileBean {
	private UploadedFile file;
	private boolean notUploaded = true;
	

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public boolean isNotUploaded() {
		return notUploaded;
	}

	public void setUploaded(boolean uploaded) {
		this.notUploaded = uploaded;
	}

	public void upload() {
		if(file != null) {
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            
            
        }
	}
}