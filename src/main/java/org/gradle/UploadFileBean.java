package org.gradle;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.dom4j.bean.BeanAttribute;
import org.gradle.business.Contact;
import org.gradle.tools.ContactsComparer;
import org.gradle.tools.ExcelExtReader;
import org.gradle.tools.validation.ContactsValidator;
import org.primefaces.model.UploadedFile;

@ManagedBean
public class UploadFileBean implements Serializable{
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
		if (file != null) {

			ExcelExtReader ex = new ExcelExtReader();

			FacesContext context = FacesContext.getCurrentInstance();
			ContactBean b = (ContactBean) context.getApplication()
					.evaluateExpressionGet(context, "#{contactBean}",
							ContactBean.class);
			ContactsValidator cv = new ContactsValidator();
			List<Contact> con = cv.validateContacts(ex.readFile(file));

			ContactsComparer cc = new ContactsComparer();

			b.appendContacts(cc.checkNewContacts(b.getContacts(), con));
			FacesMessage message = new FacesMessage("Succesful ");
			FacesMessage messageCreated = new FacesMessage(
					cc.getNumContactsCreated() + " rows are created");
			FacesMessage messageInvalid = new FacesMessage(
					cv.getNumInvalidContacs() + " rows are invalid ");
			FacesMessage messageExisted = new FacesMessage(
					cc.getNumExistedContacts() + " rows are already exist");

			FacesContext.getCurrentInstance().addMessage(null, message);
			FacesContext.getCurrentInstance().addMessage(null, messageCreated);
			FacesContext.getCurrentInstance().addMessage(null, messageInvalid);
			FacesContext.getCurrentInstance().addMessage(null, messageExisted);

		}
	}
}
