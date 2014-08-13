package org.gradle.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.dom4j.bean.BeanAttribute;
import org.gradle.DBTools.ContactsExistingChecker;
import org.gradle.DBTools.HibernateConnector;
import org.gradle.DBTools.ContactsHandler;
import org.gradle.business.Contact;
import org.gradle.facade.MainJobFacade;
import org.gradle.tools.excelhandling.ExcelFileReader;
import org.gradle.tools.validation.ContactsValidator;
import org.primefaces.model.UploadedFile;

/**
 * do the main work
 * 
 * @author arma
 *
 */
@ManagedBean
public class MainBean implements Serializable {
	private UploadedFile file;
	private MainJobFacade facade;
	private List<Contact> contacts;

	/**
 * 
 */
	public MainBean() {
		
		// TODO Auto-generated constructor stub
		facade=new MainJobFacade();
		contacts = facade.fetchContacts();
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public void doWork() {
		try{
			facade.insertNewContacts(file);
			contacts=facade.fetchContacts();
			facade.showMessages();
		} catch(NullPointerException n) {
			FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR," select a file first plz",null);
			FacesContext.getCurrentInstance().addMessage(null, error);

		}
	}
}
