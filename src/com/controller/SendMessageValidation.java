package com.controller;

import com.form.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator; 


@Component("sendmessageValidator")
public class SendMessageValidation implements Validator {

	
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public boolean supports(Class<?> p) {
		return SendMessage.class.isAssignableFrom(p);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		SendMessage sendmessage = (SendMessage) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "recipientAddress",
				"NotEmpty.sendmessage.recipientAddress", "Recipent Address must not be empty.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subject",
				"NotEmpty.sendmessage.subject", "Subject must not be empty.");
	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message",
				"NotEmpty.sendmessage.message", "Message must not be empty.");	
		
		String recipientAddress = sendmessage.getRecipientAddress();
		
		if (recipientAddress.length() > 0) {
			if (!validateEmail(recipientAddress)) {
				errors.rejectValue("recipientAddress","NotValid.sendmessage.recipientAddress",
						"Not a valid email address.");
			}
			
			if (recipientAddress.length() > 40) {
				errors.rejectValue("recipientAddress","NotValid.sendmessage.recipientAddress",
						"Email address cannot be longer than 40 characters.");
			}
		}
	}		
	
	public boolean validateEmail(final String recipientAddress){
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(recipientAddress);
		
		return matcher.matches();
	}	
}