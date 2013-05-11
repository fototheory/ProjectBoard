package com.controller;

import com.form.ForgotPassword;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.beans.User;

@Component("ForgotPasswordValidator")
public class ForgotpasswordValidation implements Validator {

	//EMAIL_PATTERN taken from:
	//http://www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public boolean supports(Class<?> klass) {
		return ForgotPassword.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ForgotPassword forgotpassword = (ForgotPassword) target;
		
		User user=new User();
		
		String email = user.getEmail();
		
		// check e mail field
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
				"NotEmpty.user.email", "Email must not be Empty.");

		
		
		if (email.length() > 0) {
			if (!validateEmail(email)) {
				errors.rejectValue("email","NotValid.user.email",
						"Not a valid email address.");
			}
			
			if (email.length() > 40) {
				errors.rejectValue("email","NotValid.user.email",
						"Email address cannot be longer than 40 characters.");
			}
			
		}	
		
		
		
		}
	public boolean validateEmail(final String emailAddress){
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(emailAddress);
		
		return matcher.matches();
	}
}
