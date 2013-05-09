package com.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.form.Registration;

@Component("registrationValidator")
public class RegistrationValidation implements Validator {

	//EMAIL_PATTERN taken from:
	//http://www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public boolean supports(Class<?> klass) {
		return Registration.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Registration registration = (Registration) target;

		// Check first name & last name fields
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fname",
				"NotEmpty.registration.fname", "First Name must not be Empty.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lname",
				"NotEmpty.registration.lname", "Last Name must not be Empty.");

		String fname = registration.getFname();
		String lname = registration.getLname();

		if (fname.length() > 0) {
			if (fname.length() < 1 || fname.length() > 45) {
				errors.rejectValue("fname", "lengthOfUser.registration.fname",
						"First name must be not be greater than 45 characters.");
			}
		}

		if (lname.length() > 0) {
			if (lname.length() <1 || lname.length() > 45) {
				errors.rejectValue("lname", "lengthOfUser.registration.lname",
						"Last name must not be greater than 45 characters.");
			}
		}

		// Check password fields
		if (!(registration.getPassword()).equals(registration.getConfirmPassword())) {
			errors.rejectValue("password",
					"matchingPassword.registration.password",
					"Password and Confirm Password do not match.");
		}

		String password = registration.getPassword();
		
		if (password.length() > 0) {
			if (password.length() < 4 || password.length() > 45) {
				errors.rejectValue("password",
						"lengthOfUser.registration.password",
						"Password must be between 4 and 45 characters.");
			}
		}
			
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
				"NotEmpty.registration.password",
				"Password must not be Empty.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
				"NotEmpty.registration.confirmPassword",
				"Confirm Password must not be Empty.");

		// Check email field
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
				"NotEmpty.registration.email", "Email must not be Empty.");

		String email = registration.getEmail();
		
		if (email.length() > 0) {
			if (!validateEmail(email)) {
				errors.rejectValue("email","NotValid.registration.email",
						"Not a valid email address.");
			}
			
			if (email.length() > 40) {
				errors.rejectValue("email","NotValid.registration.email",
						"Email address cannot be longer than 40 characters.");
			}
		}
		
		// Check discipline and roles to make sure one is selected
		if (registration.getRole() == 0) {
			errors.rejectValue("role", "NotEmpty.registration.role",
					"A Role must be selected.");
		}

		if (registration.getDiscipline() == 0) {
			errors.rejectValue("discipline",
					"NotEmpty.registration.discipline",
					"A Discipline must be selected.");
		}
	}

	public boolean validateEmail(final String emailAddress){
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(emailAddress);
		
		return matcher.matches();
	}
}
