package com.mail;

import java.io.UnsupportedEncodingException;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class MailManagerImpl implements MailManager {
	private MailSender mailSender;
	
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
 
	public boolean sendMail(String from, String to, String subject, String msg) {
 
		SimpleMailMessage message = new SimpleMailMessage();
 
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		try {
			mailSender.send(message);	
		}
		catch(MailException e) {
			System.out.println(e.toString());
			return false;
		}
		return true;
	}
}
