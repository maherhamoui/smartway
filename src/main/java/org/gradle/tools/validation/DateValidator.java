package org.gradle.tools.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValidator {
	private SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");

	boolean isValidDate(String input) {
		try {
			format.parse(input);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
}
