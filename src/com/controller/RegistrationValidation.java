package com.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.form.Registration;

@Component("registrationValidator")
public class RegistrationValidation {

	public boolean supports(Class<?> klass) {
		return Registration.class.isAssignableFrom(klass);
	}

	public void validate(Object target, Errors errors) {
		Registration registration = (Registration) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fname",
				"NotEmpty.registration.fname", "User Name must not be Empty.");
		String fname = registration.getfname();

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lname",
				"NotEmpty.registration.lname", "User Name must not be Empty.");
		String lname = registration.getlname();

		if ((fname.length()) > 50) {
			errors.rejectValue("fname", "lengthOfUser.registration.fname",
					"User Name must not more than 50 characters.");
		}

		if ((lname.length()) > 50) {
			errors.rejectValue("lname", "lengthOfUser.registration.lname",
					"User Name must not more than 50 characters.");
		}

		if (!(registration.getPassword()).equals(registration
				.getConfirmPassword())) {
			errors.rejectValue("password",
					"matchingPassword.registration.password",
					"Password and Confirm Password Not match.");
		}
	}

}
