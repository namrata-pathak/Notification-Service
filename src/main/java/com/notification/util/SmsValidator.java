package com.notification.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class SmsValidator {

	private Pattern pattern;
	private Matcher matcher;

	//private static final String SMS_PATTERN = "^\\\\d{10}$";
	
	private static final String SMS_PATTERN = "[0-9]{10}";

	public SmsValidator() {
		pattern = Pattern.compile(SMS_PATTERN);
	}

	/**
	 * Validate hex with regular expression
	 *
	 * @param hex hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public boolean isValid(final String hex) {

		matcher = pattern.matcher(hex);
		return matcher.matches();

	}
	
}
