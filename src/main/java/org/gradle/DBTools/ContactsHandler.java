package org.gradle.DBTools;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.gradle.business.Contact;

/**
 * list contacts
 * 
 * @author arma
 *
 */

public class ContactsHandler {

	private List<Contact> contacts;
	private HibernateConnector hc;

	public ContactsHandler() {
		// TODO Auto-generated constructor stub
		contacts = new ArrayList<Contact>();
		hc = HibernateConnector.getInstance();
		refreshContacts();

	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public void refreshContacts() {

		setContacts(hc.listContacts());
	}

	public void insertContacts(List<Contact> contacts) {
		hc.insertContacts(contacts);
	}
}
