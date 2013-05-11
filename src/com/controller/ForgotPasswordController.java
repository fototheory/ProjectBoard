package com.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.beans.Project;
import com.beans.User;
import com.form.ForgotPassword;
import com.form.ProjectInfo;
import com.mail.MailManager;
import com.service.ProjectJdbcServiceImpl;
import com.service.UserJdbcServiceImpl;
import com.session.SessionScopeData;


@Controller
@RequestMapping("/forgotpassword")
public class ForgotPasswordController {
		
	
	//From email address
		private static final String from = "webmaster.nuproactive@gmail.com";
	
	//instantiates UserJdbcServiceImpl for user related activities
	UserJdbcServiceImpl  passwordService = new UserJdbcServiceImpl();
	
	
	//autowire Mail Manager
	@Autowired
	private MailManager mailManager;
	//autowire session variable User
	@Autowired
	private SessionScopeData sessionScopeUserData;
	
	// processing the form
	
	@RequestMapping(method= RequestMethod.GET)
	public ModelAndView FortgotPassword(){
		return  new  ModelAndView("forgotpassword", "forgotpassword", new ForgotPassword());
	}
	
	@RequestMapping(method= RequestMethod.POST)
	public ModelAndView passSubmit(@ModelAttribute("forgotpassword") ForgotPassword PwdData, HttpServletRequest request) {
		ModelAndView mav;
		String status="";
		String emailVal = PwdData.getEmail();
		if(emailVal.length()>0) {
			String serverURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
			//get password
			User tempUser = passwordService.getUserByEmail(emailVal);
			if(tempUser.getId() >0) {
				//user exists
				String msg = "Hi "+tempUser.getFname()+ " " +tempUser.getLname()+",\n Your password is: "+tempUser.getPassword()+ ". Please login to the website using your recoverd password. http://www.seeweed.org:8080/ProjectBoard/ "+"\n Thank you,\nProject Board Administrator.\n";
				mailManager.sendMail(from, emailVal, "Project Board - Password Recovery Message.", msg);
				//Send Email Acknowledgment here
				mav = new ModelAndView("forgotpassword","status"," Your Passowrd Recovery request has been sent to the Administrator, please allow us 1-3 minute before you check your email.! ");
			}
			else {
				mav = new ModelAndView("forgotpassword","status"," Your email address doesn't exist in the database. Please re-enter again ?");
			}
		}
		else {
			status="It is failed, please enter a correct information";
			mav = new ModelAndView("forgotpassword","status",status);
		}		
		
		return mav;
	}
		
	
}