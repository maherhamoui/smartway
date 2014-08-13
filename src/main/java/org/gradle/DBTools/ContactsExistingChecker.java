package org.gradle.DBTools;

import java.util.ArrayList;
import java.util.List;

import org.gradle.business.Contact;
import org.gradle.business.ContactsComparer;
/**
 * 
 * @author arma
 * checks list of contacts if they are existed in DB
 */
public class ContactsExistingChecker {
	private List<Contact> contacts;
	private int numExistedContacts = 0;
	

	/**
	 * @param oldCon
	 *            old contacts from DataBase
	 * @param newCon
	 *            new contacts from file
	 * @return not existed users to add
	 */
	public ContactsExistingChecker() {
		// TODO Auto-generated constructor stub
		
	}
	public List<Contact> checkNewContacts(List<Contact> oldCon,
			List<Contact> newCon) {
		contacts = new ArrayList<Contact>();
		int t;
		for (Contact con1 : newCon) {
			t = 0;
			for (Contact con2 : oldCon) {
				if (ContactsComparer.compare(con1, con2) == 0) {
					numExistedContacts++;
					t++;
				}
			}

			if (t == 0) {

				contacts.add(con1);
				t = 0;
			}
		}

		return contacts;
	}

	

	public int getNumExistedContacts() {
		return numExistedContacts;
	}

	public int getNumContactsCreated() {
		return contacts.size();
	}

}
