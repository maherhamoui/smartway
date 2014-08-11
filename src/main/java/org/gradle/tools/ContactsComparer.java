package org.gradle.tools;

import java.util.ArrayList;
import java.util.List;

import org.gradle.hbm.Contact;

public class ContactsComparer {
	private List<Contact> contacts;
	private int numExistedContacts = 0;

	/**
	 * @param oldCon
	 *            old contacts from DataBase
	 * @param newCon
	 *            new contacts from file
	 * @return not existed users to add
	 */
	public List<Contact> checkNewContacts(List<Contact> oldCon,
			List<Contact> newCon) {
		contacts = new ArrayList<Contact>();
		int t;
		for (Contact con1 : newCon) {
			t = 0;
			for (Contact con2 : oldCon) {
				if (con1.compareTo(con2) == 0) {
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
