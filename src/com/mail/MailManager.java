package com.mail;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public interface MailManager {
	 
	public void setMailSender(MailSender mailSender);
 
	public boolean sendMail(String from, String to, String subject, String msg);
}
