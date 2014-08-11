package org.gradle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.gradle.hbm.Contact;

@ManagedBean
public class ContactBean implements Serializable {
	
	private List<Contact> contacts;

	public ContactBean() {
		// TODO Auto-generated constructor stub
		contacts = new ArrayList<Contact>();
		
		// getData();
//		hb=new HibernateUtil();
//		contacts.addAll(hb.listContacts());
//		System.out.print(contacts);
		

	}
	

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	
	public void appendContacts(List<Contact> c){
		this.contacts.addAll(c);
	}

	


}
