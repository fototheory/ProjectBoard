package com.controller;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.beans.Profile;

@Component("profilePageValidator")
public class ProfilePageValidation implements Validator {

	private static final String PHONE_PATTERN =
			"\\d{10}";
	
	public boolean supports(Class<?> klass) {
		return Profile.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Profile profile = (Profile) target;

		// Check company name field
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "company",
				"NotEmpty.Profile.comany", "Please enter a Company or University name.");

		// Check phone number field
		String phoneNumber = profile.getPhone();

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone",
				"NotEmpty.Profile.phone", "Please enter a phone number.");

		if (!phoneNumber.equals("")){
			if (!validatePhone(phoneNumber)) {
				errors.rejectValue("phone", "NotValid.profile.phone","Invalid phone number format. Please use ##########.");
			}
		}

		// Check skills field
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "skills",
				"NotEmpty.Profile.skills", "Please tell us about your skills.");

	}
	
	public boolean validatePhone(String string) {
		Pattern pattern = Pattern.compile(PHONE_PATTERN);
		Matcher matcher = pattern.matcher(string);
		
		return matcher.matches();
	}
	
	
}
