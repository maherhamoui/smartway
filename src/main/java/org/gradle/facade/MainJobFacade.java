/**
 * 
 */
package org.gradle.facade;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.gradle.DBTools.ContactsExistingChecker;
import org.gradle.DBTools.HibernateConnector;
import org.gradle.DBTools.ContactsHandler;
import org.gradle.business.Contact;
import org.gradle.tools.excelhandling.ExcelFileReader;
import org.gradle.tools.validation.ContactsValidator;
import org.primefaces.model.UploadedFile;

/**
 * @author Maher
 *
 */
public class MainJobFacade {
	/**
	 * @param hc
	 * 
	 */
	private HibernateConnector hc;
	private ExcelFileReader ex;
	private ContactsHandler ch;
	private ContactsValidator cv;
	private ContactsExistingChecker cc;

	public MainJobFacade() {
		// TODO Auto-generated constructor stub

		ex = new ExcelFileReader();
		ch = new ContactsHandler();
		cv = new ContactsValidator();
		cc = new ContactsExistingChecker();
	}

	public List<Contact> fetchContacts() {

		return ch.getContacts();
	}

	public void insertNewContacts(UploadedFile file) {
		List<Contact> con = cv.validateContacts(ex.readFile(file));
		ch.insertContacts(cc.checkNewContacts(ch.getContacts(), con));
	}

	public void showMessages() {
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
