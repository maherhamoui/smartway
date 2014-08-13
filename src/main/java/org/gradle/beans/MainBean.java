package org.gradle.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.dom4j.bean.BeanAttribute;
import org.gradle.DBTools.ContactsExistingChecker;
import org.gradle.DBTools.HibernateConnector;
import org.gradle.business.Contact;
import org.gradle.tools.excelhandling.ExcelFileReader;
import org.gradle.tools.validation.ContactsValidator;
import org.primefaces.model.UploadedFile;
/**
 * do the main work
 * @author arma
 *
 */
@ManagedBean
public class MainBean implements Serializable {
	private UploadedFile file;
	private HibernateConnector hc;

	public void doWork() {
		FacesContext context = FacesContext.getCurrentInstance();
		UploadFileBean uploadBean = (UploadFileBean) context.getApplication()
				.evaluateExpressionGet(context, "#{uploadFileBean}",
						UploadFileBean.class);
		this.file = uploadBean.getFile();
		if (file != null) {
			hc = new HibernateConnector();

			ExcelFileReader ex = new ExcelFileReader();

			ListContactsBean lcb = (ListContactsBean) context.getApplication()
					.evaluateExpressionGet(context, "#{ListContactBean}",
							ListContactsBean.class);
			ContactsValidator cv = new ContactsValidator();
			List<Contact> con = cv.validateContacts(ex.readFile(file));

			ContactsExistingChecker cc = new ContactsExistingChecker();
			hc.insertContacts(cc.checkNewContacts(lcb.getContacts(), con));
			lcb.refreshContacts();
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
