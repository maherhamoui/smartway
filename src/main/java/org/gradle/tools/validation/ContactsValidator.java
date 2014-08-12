package org.gradle.tools.validation;

import java.util.ArrayList;
import java.util.List;

import org.gradle.business.Contact;

public class ContactsValidator {
	private List<Contact> contacts;
	private EmailValidator ev;
	private DateValidator dv;
	private int numInvalidContacs = 0;

	public ContactsValidator() {
		// TODO Auto-generated constructor stub
		ev = new EmailValidator();
		dv = new DateValidator();
	}

	public List<Contact> validateContacts(List<Contact> c) {
		contacts=new ArrayList<Contact>();
		for (Contact con : c) {
			if (ev.isValidEmailAddress(con.getEmail())) {
				
				contacts.add(con);
			}
		}
		numInvalidContacs = c.size() - contacts.size();
		return contacts;

	}

	public int getNumInvalidContacs() {
		return numInvalidContacs;
	}

}
