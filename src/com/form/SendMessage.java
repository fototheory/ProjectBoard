package com.form;

import java.io.UnsupportedEncodingException;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SendMessage {
	private String subject;
	private String message;
	//@Email
	private String recipientAddress= "webmaster.nuproactive@gmail.com" ;  
	   
	private String senderAddress; 
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getRecipientAddress() {
		return recipientAddress;
	}
	public void setRecipientAddress(String recipientAddress) {
		this.recipientAddress = recipientAddress;
	}	
	
	public String getsenderAddress() {
		return senderAddress;
	}
	
	
	public void setsenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}	
	
	
	
	
	}	
	
	
	
	
	
