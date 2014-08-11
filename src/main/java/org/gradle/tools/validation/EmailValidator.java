package org.gradle.tools.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

	public EmailValidator() {

	}

	public boolean isValidEmailAddress(String email) {
		String emailPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		Pattern p = Pattern.compile(emailPattern);
		Matcher m = p.matcher(email);
		return m.matches();
	}
}
