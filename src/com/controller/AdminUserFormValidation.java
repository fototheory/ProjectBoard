package com.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.form.AdminUserForm;

@Component("adminUserFormValidator")
public class AdminUserFormValidation implements Validator {

	//EMAIL_PATTERN taken from:
	//http://www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String PHONE_REGEX = "\\d{10}";
	public boolean supports(Class<?> klass) {
		return AdminUserForm.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AdminUserForm adminUserForm = (AdminUserForm) target;

		// Check first name & last name fields
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fname",
				"NotEmpty.adminUserForm.fname", "First Name must not be Empty.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lname",
				"NotEmpty.adminUserForm.lname", "Last Name must not be Empty.");

		String fname = adminUserForm.getFname();
		String lname = adminUserForm.getLname();

		if (fname.length() > 0) {
			if (fname.length() < 1 || fname.length() > 45) {
				errors.rejectValue("fname", "lengthOfUser.adminUserForm.fname",
						"First name must be not be greater than 45 characters.");
			}
		}

		if (lname.length() > 0) {
			if (lname.length() <1 || lname.length() > 45) {
				errors.rejectValue("lname", "lengthOfUser.adminUserForm.lname",
						"Last name must not be greater than 45 characters.");
			}
		}

		// Check password fields
		if (!(adminUserForm.getPassword()).equals(adminUserForm.getConfirmPassword())) {
			errors.rejectValue("password",
					"matchingPassword.adminUserForm.password",
					"Password and Confirm Password do not match.");
		}

		String password = adminUserForm.getPassword();
		
		if (password.length() > 0) {
			if (password.length() < 4 || password.length() > 45) {
				errors.rejectValue("password",
						"lengthOfUser.adminUserForm.password",
						"Password must be between 4 and 45 characters.");
			}
		}
			
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
				"NotEmpty.adminUserForm.password",
				"Password must not be Empty.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
				"NotEmpty.adminUserForm.confirmPassword",
				"Confirm Password must not be Empty.");

		// Check email field
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
				"NotEmpty.adminUserForm.email", "Email must not be Empty.");

		String email = adminUserForm.getEmail();
		
		if (email.length() > 0) {
			if (!validateEmail(email)) {
				errors.rejectValue("email","NotValid.adminUserForm.email",
						"Not a valid email address.");
			}
			
			if (email.length() > 40) {
				errors.rejectValue("email","NotValid.adminUserForm.email",
						"Email address cannot be longer than 40 characters.");
			}
		}
		
		String phone = adminUserForm.getPhone();
		
		if (phone.length() > 0) {
			if (!validatePhone(phone)) {
				errors.rejectValue("phone","NotValid.adminUserForm.phone",
						"Not a valid phone number.");
			}
		}
		// Check discipline and roles to make sure one is selected
		if (adminUserForm.getRole() == 0) {
			errors.rejectValue("role", "NotEmpty.adminUserForm.role",
					"A Role must be selected.");
		}

		if (adminUserForm.getDiscipline() == 0) {
			errors.rejectValue("discipline",
					"NotEmpty.adminUserForm.discipline",
					"A Discipline must be selected.");
		}
	}

	public boolean validateEmail(final String emailAddress){
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(emailAddress);
		
		return matcher.matches();
	}
	
	public boolean validatePhone(final String phone){
		Pattern pattern = Pattern.compile(PHONE_REGEX);
		Matcher matcher = pattern.matcher(phone);
		
		return matcher.matches();
	}
}
