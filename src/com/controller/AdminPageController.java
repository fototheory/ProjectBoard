package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.beans.User;
import com.service.UserJdbcServiceImpl;
import com.session.SessionScopeData;

@Controller
@RequestMapping("/admin/adminpage")
public class AdminPageController {
	//instantiates UserJdbcServiceImpl for user related activities
	UserJdbcServiceImpl sessionService = new UserJdbcServiceImpl();
	
	//autowire session variable User
	@Autowired
    private SessionScopeData sessionScopeUserData;
		
	private List<User> userList;
	
	// Display the form on the get request
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(ModelMap model) {
		ModelAndView mav = new ModelAndView("admin/adminpage");
		User user = new User();
		
		//Check if user is logged in
		if(this.sessionCheck(sessionScopeUserData)) {
			//Send session variable to the view 
			user = sessionScopeUserData.getUserInfo();
			mav.addObject("sessionUserInfo", user);	
			
			//Get unverified users and add them to the view
			userList =  sessionService.getUnVerifiedUsers();
			model.addAttribute("userList", userList);
			mav.addObject("userList", userList);

			model.put("user",new User());
		}
		else {
			//user hasn't logged in
			mav.addObject("status", "Please login first.");
			mav.setViewName("redirect:/login.do");
		}
		return mav;
	}

	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView processAdminPage(User selectedUser, ModelMap model) {
		ModelAndView mav = new ModelAndView("admin/adminpage");

		//Check if user is logged in
		if(this.sessionCheck(sessionScopeUserData)) {
			User user = sessionScopeUserData.getUserInfo();
			//send session variable to view 
			mav.addObject("sessionUserInfo", user);	

			//Get selected user's data from the DB
			selectedUser = sessionService.getUserByEmail(selectedUser.getEmail());

			//Check if no user was selected
			if(selectedUser.getEmail() != null) {
				//verify user
				sessionService.verifyUser(selectedUser);
				
				model.addAttribute("successMsg", selectedUser.getFname()+" " +
						selectedUser.getLname()+" verification successful.");
			}
			else {
				model.addAttribute("successMsg", "No user was selected. Please select a user first.");
			}
		}
		else {
			//User hasn't logged in
			mav.addObject("status", "Please login first.");
			mav.setViewName("redirect:/login.do");
		}

		//Get unverified users and add them to the view
		userList =  sessionService.getUnVerifiedUsers();
		model.addAttribute("userList", userList);
		mav.addObject("userList", userList);
	
		return mav;
	}

	@RequestMapping(params = {"logout"})
	public ModelAndView logout(@RequestParam(value = "logout") String logoutVal) {
		ModelAndView mav;
		if(logoutVal.equals("yes")) {
			sessionScopeUserData.setUserInfo(new User());
			mav = new ModelAndView("redirect:/login.do", "status", "You successfully logout.");
		}
		else {
			mav = new ModelAndView("redirect:/admin/adminpage.do");
		}
		return mav;
	}
	
	
	protected boolean sessionCheck(SessionScopeData sessionData) {		
		if(sessionScopeUserData!=null) {
			if(sessionService.sessionCheck(sessionData.getUserInfo().getId())>0) {
				return true;
			}				
		}
		return false;
	}
}
