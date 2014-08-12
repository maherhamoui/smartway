package org.gradle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.gradle.business.Contact;
import org.gradle.hbm.HibernateConnector;

@ManagedBean
public class ContactBean implements Serializable {

	private List<Contact> contacts;
	private HibernateConnector hc;

	public ContactBean() {
		// TODO Auto-generated constructor stub
		contacts = new ArrayList<Contact>();
		hc = new HibernateConnector();
		setContacts(hc.listContacts());

	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public void appendContacts(List<Contact> c) {
		hc.insertContacts(c);
		setContacts(hc.listContacts());
	}

}
