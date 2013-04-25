package com.controller;

import com.beans.*;
import com.form.Registration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

	private Pattern pattern;
	private Matcher matcher;
	
private static final String EMAIL_FORMAT= 

"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
public EmailValidator() {
	pattern = Pattern.compile(EMAIL_FORMAT);
}

public boolean validate(final String input) {
	 
	matcher = pattern.matcher(input);
	return matcher.matches();

}




}
