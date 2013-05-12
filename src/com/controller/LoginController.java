package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.beans.Role;
import com.beans.User;
import com.service.RoleJdbcServiceImpl;
import com.service.UserJdbcServiceImpl;
import com.session.SessionScopeData;

/**
 * <code>LoginController</code> handles requests/responses for login.jsp
 * operations of login are performed through <code>LoginService</code> and <code>RoleService</code>
 * @author Yumiko Iwai
 * @version 1.0
 */
@Controller
public class LoginController {
	//instantiates UserJdbcServiceImpl for user related activities
	UserJdbcServiceImpl  LoginService = new UserJdbcServiceImpl();
	//instantiates RoleJdbcServiceImpl for role related activities
	RoleJdbcServiceImpl  RoleService = new RoleJdbcServiceImpl();
	
	//autowire session variable User
	@Autowired
    private SessionScopeData sessionscopehellodata;
	
	//this method is processed when login.jsp is accessed
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginDetails(@RequestParam(value="status", required=false) String  status) {
		//define view page to display: login.jsp
		ModelAndView mav = new ModelAndView("login");
		//creates empty user
		User userbean = new User();
		//add user object to the view
		mav.addObject("loginUser", userbean);
		//check if there is an error message passed by GET
		if(status != null) {
			//message if there is an error
			mav.addObject("status", status);
		}
		return mav;
	}

	//handles login form submission
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginCheck(@ModelAttribute("loginUser") User user) {
		ModelAndView mav = new ModelAndView("login");
		try {
			//retrieve a user record based on email and password
			User regUser = LoginService.loginCheck(user.getEmail(), user.getPassword());
			//check user found in database
			if (regUser.getEmail() != null)
			{
				// user exists				
				if(regUser.isVerified()==1) {
					//set session with the user record
					sessionscopehellodata.setUserInfo(regUser);
					
					//Check if verified user has a profile
					if (regUser.getHasProfile() == 0){
						mav.setViewName("redirect:/profilepage.do");
						return mav;
					}
					
					//get role
					Role rl = RoleService.selectById(regUser.getRoleId());
	            	String roleName = rl.getName();

	            	switch (roleName) {
	            	case "Admin":
	            		System.out.println("User is an admin.");
	            		mav.setViewName("redirect:/admin/index.do");
	            		break;
	            	case "Student":
						//redirect a user to student/index.jsp
						//mav = new ModelAndView(new RedirectView("student/index.do"));
	            		mav.setViewName("redirect:/student/index.do");
						break;
	            	case "Sponsor":
	            		System.out.println("User is a sponsor.");
						//redirect a user to sponsor/index.jsp
						mav = new ModelAndView("redirect:/sponsor.do");
						break;
	            	case "Lead faculty":
	            		System.out.println("User is a Lead faculty.");
						//redirect a user to sponsor/index.jsp
						mav = new ModelAndView("redirect:/leadFac/projectList.do");
	            		break;
	            	case "Capstone faculty":
	            		System.out.println("User is a Capstone faculty.");
						//redirect a user to sponsor/index.jsp
						mav = new ModelAndView("redirect:/capFac/projectList.do");
	            		break;
	            	case "Negotiating faculty":
	            		System.out.println("User is a Negotiating faculty.");
						//redirect a user to sponsor/index.jsp
						mav = new ModelAndView("redirect:/negFac/projectList.do");
	            		break;
	            	default:
	            		System.out.println("User is a Negotiating faculty.");
						//redirect a user to sponsor/index.jsp
						mav = new ModelAndView("redirect:/student/projectList.do");
						break;
	            	}
				}
				else {
					//error: user has not been verified
					mav.addObject("status", "user is not verified yet, contact administrator");
				}
			}
			else
			{
				// user is not in database
				mav.addObject("status", "user doesn't exist in database");				
			}
		} catch (Exception e) {
				e.printStackTrace();
		}
		return mav;
	}
}