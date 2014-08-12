package org.gradle.tools;

import java.util.ArrayList;
import java.util.List;

import org.gradle.business.Contact;

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
				if (compare(con1, con2) == 0) {
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

	private int compare(Contact con1, Contact con2) {
		if (con1.getEmail().equals(con2.getEmail()))
			return 0;
		else
			return 1;
	}

	public int getNumExistedContacts() {
		return numExistedContacts;
	}

	public int getNumContactsCreated() {
		return contacts.size();
	}

}
