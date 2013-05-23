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
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "senderAddress",
				"NotEmpty.sendmessage.senderAddress", "Sender Address must not be empty.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subject",
				"NotEmpty.sendmessage.subject", "Subject must not be empty.");
	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message",
				"NotEmpty.sendmessage.message", "Message must not be empty.");	
		
		
		String senderAddress= sendmessage.getsenderAddress();
		
		}
			
	
	public boolean validateEmail(final String senderAddress){
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(senderAddress);
		
		return matcher.matches();
	}	
}