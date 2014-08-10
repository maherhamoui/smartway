package org.gradle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.gradle.hbm.Contact;
import org.gradle.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@ManagedBean
public class ContactBean implements Serializable {
	private HibernateUtil hb;
	private List<Contact> contacts;

	public ContactBean() {
		// TODO Auto-generated constructor stub
		contacts = new ArrayList<Contact>();
		
		// getData();
//		hb=new HibernateUtil();
//		contacts.addAll(hb.listContacts());
//		System.out.print(contacts);
		contacts.add(new Contact("maher", "fdf", "dsd", "3424"));
		contacts.add(new Contact("maher", "fdf", "dsd", "3424"));
		contacts.add(new Contact("maher", "fdf", "dsd", "3424"));
		contacts.add(new Contact("maher", "fdf", "dsd", "3424"));

	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	


}
