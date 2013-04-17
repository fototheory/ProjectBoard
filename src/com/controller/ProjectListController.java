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
 * <code>ProjectListController</code> handles requests/responses for student/projectList.jsp
 * default get controller will retrieve project list related to the user
 * handles project list related activities
 * @author Yumiko Iwai
 * @version 1.0
 */
@Controller
public class ProjectListController {
	//autowire session variable User
	@Autowired
    private SessionScopeData sessionscopehellodata;
	
	//this method is processed when student/projectList.jsp is accessed
	@RequestMapping(value = "/student/projectList", method = RequestMethod.GET)
	public ModelAndView loginDetails() {
		//define view page to display: student/projectList.jsp
		ModelAndView mav = new ModelAndView("/student/projectList");
		//check session is set: if not set, user hasn't logged in
		if(sessionscopehellodata!=null) {
			User userbean =sessionscopehellodata.getUserInfo();
			//session check (right now just checking user_id is more than 0)
			if(userbean.getId()>0) {
				//add welcome message to view
				mav.addObject("sessionUserInfo", "Welcome, "+userbean.getFname()+" "+userbean.getLname());
				return mav;
			}
		}
		//user is not logged in, redirect user back to login.jsp with message
		mav = new ModelAndView(new RedirectView("../login.do"), "status", "Please login first");
		return mav;
	}
}
