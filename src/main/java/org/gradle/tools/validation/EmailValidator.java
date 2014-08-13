package org.gradle.tools.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * validate email address
 * 
 * @author arma
 *
 */
public class EmailValidator {

	public EmailValidator() {

	}

	/**
	 * method uses a pattern to validate email with
	 * 
	 * @param email
	 *            the email as string to be validated
	 * @return true if valide email false if not
	 */
	public boolean isValidEmailAddress(String email) {
		String emailPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		Pattern p = Pattern.compile(emailPattern);
		Matcher m = p.matcher(email);
		return m.matches();
	}
}
