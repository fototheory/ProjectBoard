package com.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import com.form.Registration;

@Component("registrationValidator")
public class RegistrationValidation implements Validator {

	public boolean supports(Class<?> klass) {
		return Registration.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Registration registration = (Registration) target;

		// Check first name & last name fields
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fname",
				"NotEmpty.registration.fname", "First Name must not be Empty.");
		String fname = registration.getFname();

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lname",
				"NotEmpty.registration.lname", "Last Name must not be Empty.");
		String lname = registration.getLname();

		if (!((fname.length()) == 0))

		{

			if ((fname.length()) < 5 || (fname.length()) > 20) {
				errors.rejectValue("fname", "lengthOfUser.registration.fname",
						"First Name must be between 4 and 20 characters.");
			}

		}

		if (!((lname.length()) == 0))

		{

			if ((lname.length()) < 5 || (lname.length()) > 20) {
				errors.rejectValue("lname", "lengthOfUser.registration.lname",
						"Last Name must be between 4 and 20 characters.");
			}
		}

		// Check password fields
		// Still need to check whether the password conforms to our password
		// rules (i.e. >4 <20 characters)

		if (!(registration.getPassword()).equals(registration
				.getConfirmPassword())) {
			errors.rejectValue("password",
					"matchingPassword.registration.password",
					"Password and Confirm Password do not match.");
		}

		String password = registration.getPassword();

		
		if (!((password.length()) == 0))
			
		{
		
		if ((password.length()) < 5 || (password.length()) > 20) {
			errors.rejectValue("password",
					"lengthOfUser.registration.password",
					"Password must be between 4 and 20 characters.");
		}

		
		}
		
			
			
		ValidationUtils
				.rejectIfEmptyOrWhitespace(errors, "password",
						"NotEmpty.registration.password",
						"Password must not be Empty.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
				"NotEmpty.registration.confirmPassword",
				"Confirm Password must not be Empty.");

		// Check email field
		// Still need to check whether the email is in a valid format
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
				"NotEmpty.registration.email", "Email must not be Empty.");

		// e mail validation codes

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
}
