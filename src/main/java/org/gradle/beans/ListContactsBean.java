package org.gradle.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.gradle.DBTools.HibernateConnector;
import org.gradle.business.Contact;

/**
 * list contacts to view
 * 
 * @author arma
 *
 */
@ManagedBean
public class ListContactsBean implements Serializable {

	private List<Contact> contacts;
	private HibernateConnector hc;

	public ListContactsBean() {
		// TODO Auto-generated constructor stub
		contacts = new ArrayList<Contact>();
		hc = new HibernateConnector();
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

}
