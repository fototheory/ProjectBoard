package com.mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
	
	public boolean sendMailFromUser(String msgFrom, String msgTo, String subject, String msg) {
		 
		// Recipient's email ID needs to be mentioned.
	      String to = msgTo;

	      // Sender's email ID needs to be mentioned
	      String from = msgFrom;

	      // Assuming you are sending email from smtp.gmail.com
	      String host = "smtp.gmail.com";

	      // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	      properties.setProperty("mail.smtp.host", host);
	      properties.setProperty("mail.smtp.port", "587");
	      properties.setProperty("mail.smtp.user", "webmaster.nuproactive@gmail.com");
	      properties.setProperty("mail.smtp.password", "capstone13");
	      properties.put("mail.smtp.starttls.enable", "true");
	      //properties.put("mail.smtp.ssl.enable", "true");
	      properties.setProperty("mail.smtp.auth", "true");       
	      properties.setProperty("mail.debug", "true");

	      // Get the default Session object.
	      Session session = Session.getInstance(properties, null);

	      try{
	    	 Transport transport = session.getTransport("smtp");
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from, from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject(subject);

	         // Now set the actual message
	         message.setText(msg);
	         Address[] addresses = new Address[1];
	         //actually i want to addressee to reply-to
	         addresses[0] = new InternetAddress( from, from );
	         message.setReplyTo(addresses);

	         // Send message
	         //transport = session.getTransport("smtp");
	         transport.connect(host, 587, "webmaster.nuproactive@gmail.com", "capstone13");
	         message.saveChanges(); 
	         transport.sendMessage(message, message.getAllRecipients());
	         transport.close();	        
	         System.out.println("Sent message successfully....");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	         return false;
	      }
	      catch (UnsupportedEncodingException mex) {
		         mex.printStackTrace();
		         return false;
		  }
	      return true;
	 
	}
}
