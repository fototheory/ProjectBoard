package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.beans.User;
import com.session.SessionScopeData;

/**
 * <code>StudentController</code> handles requests/responses for student/index.jsp
 * default get controller will display welcome message and buttons
 * handles button related activities
 * @author Yumiko Iwai
 * @version 1.0
 */
@Controller
public class StudentController {
	//autowire session variable User
	@Autowired
    private SessionScopeData sessionscopehellodata;
	
	//this method is processed when student/index.jsp is accessed
	@RequestMapping(value = "/student/index", method = RequestMethod.GET)
	public ModelAndView loginDetails() {
		ModelAndView mav = new ModelAndView("/student/index");
		if(sessionscopehellodata!=null) {
			User userbean =sessionscopehellodata.getUserInfo();
			if(userbean.getId()>0) {
				mav.addObject("sessionUserInfo", "Welcome, "+userbean.getFname()+" "+userbean.getLname());
				return mav;
			}
		}
		mav = new ModelAndView(new RedirectView("../login.do"), "status", "Please login first");
		//mav.addObject("status", "success");
		return mav;
	}
	
	@RequestMapping(value = "/student/index", method = {RequestMethod.POST}, params = "logout")
	public ModelAndView logout() {
		ModelAndView mav = new ModelAndView("/student/index");
		sessionscopehellodata.setUserInfo(new User());
		mav = new ModelAndView(new RedirectView("../login.do"), "status", "You successfully logout");
		//mav.addObject("status", "success");
		return mav;
	}
	
	@RequestMapping(value = "/student/index", method = {RequestMethod.POST}, params = "projectList")
	public ModelAndView projectList() {
		ModelAndView mav = new ModelAndView(new RedirectView("projectList.do"));
		//mav.addObject("status", "success");
		return mav;
	}
	
}
