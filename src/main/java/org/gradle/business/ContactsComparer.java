/**
 * 
 */
package org.gradle.business;

/**
 * compares tow contacts (not using compareto in contact class because hibernate
 * throws exceptions )
 * 
 * @author arma compare tow contacts
 */
public class ContactsComparer {
	
	public static int compare(Contact con1, Contact con2) {
		if (con1.getEmail().equals(con2.getEmail()))
			return 0;
		else
			return 1;
	}

}
