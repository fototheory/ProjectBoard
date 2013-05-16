package com.controller;
import com.beans.User;
import com.form.Registration;
import com.form.SendMessage;
import com.mail.MailManager;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;




@Controller
@RequestMapping("/MessageForm")
public class SendMessageController {
	//From email address
	private static final String from = "webmaster.nuproactive@gmail.com";
	@Autowired
	private MailManager mailManager;	
	@Autowired
	private SendMessageValidation sendMessageValidation;
	
	@RequestMapping(method = RequestMethod.GET)	
	public ModelAndView showSendMessage (ModelMap model){
		
		ModelAndView mav =new ModelAndView("MessageForm");
		SendMessage sendMessage = new SendMessage();
		mav.addObject("sendMessage",sendMessage);
		//creates empty user
		User userbean = new User();
		//add user object to the view
		mav.addObject("loginUser", userbean);
		return mav;
	}
	
     
    @RequestMapping(method = RequestMethod.POST)    
    public ModelAndView sendMessage(SendMessage sendMessage, 
			BindingResult result, ModelMap model) {
    	ModelAndView mav = new ModelAndView("MessageForm");
    	//Backend validation of the form
		sendMessageValidation.validate(sendMessage, result);
		if (result.hasErrors()) {
			model.put("msg", "There were errors in the form. " +
					"Please correct and resubmit.");	
			//creates empty user
			User userbean = new User();
			//add user object to the view
			mav.addObject("loginUser", userbean);    
			return mav;
		}
         
        // creates a simple e-mail object
        if(mailManager.sendMail(from, sendMessage.getRecipientAddress(), sendMessage.getSubject(), sendMessage.getMessage())) {
        	model.addAttribute("msg", "(A courtesy email of this page has been emailed to the email address below.)");
        }
        else {
        	model.addAttribute("msg", "Error");
        }
        sendMessage = new SendMessage();
		mav.addObject("sendMessage",sendMessage);
		//creates empty user
		User userbean = new User();
		//add user object to the view
		mav.addObject("loginUser", userbean);        
        // forwards to the view named "SuccessResult"
        return mav;
    }
}

	

