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
public class ProjectsController {
	//instantiates UserJdbcServiceImpl for user related activities
	UserJdbcServiceImpl  LoginService = new UserJdbcServiceImpl();
	//instantiates RoleJdbcServiceImpl for role related activities
	RoleJdbcServiceImpl  RoleService = new RoleJdbcServiceImpl();
	
	//autowire session variable User
	@Autowired
    private SessionScopeData sessionscopehellodata;
	
	//this method is processed when login.jsp is accessed
	@RequestMapping(value = "/projects", method = RequestMethod.GET)
	public ModelAndView loginDetails(@RequestParam(value="status", required=false) String  status) {
		//define view page to display: login.jsp
		ModelAndView mav = new ModelAndView("projects");
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
	@RequestMapping(value = "/projects", method = RequestMethod.POST)
	public ModelAndView loginCheck(@ModelAttribute("loginUser") User user) {
		ModelAndView mav = new ModelAndView("projects");
		try {
			//retrieve a user record based on email and password
			User regUser = LoginService.loginCheck(user.getEmail(), user.getPassword());
			//check user found in database
			if (regUser!=null)
			{
				// user exists				
				if(regUser.isVerified()==1) {
					//get role
					Role rl = RoleService.selectById(regUser.getRoleId());
	            	String roleName = rl.getName();
	            	//if role name matches, forward a user to a user specific area
					if(roleName.equals("Student")){
						//set session with the user record
						sessionscopehellodata.setUserInfo(regUser);
						//redirect a user to student/index.jsp
						mav = new ModelAndView(new RedirectView("student/index.do"));
					}
					else if(roleName.equals("Sponsor")){
						//set session with the user record
						sessionscopehellodata.setUserInfo(regUser);
						//redirect a user to sponsor/index.jsp
						mav = new ModelAndView("redirect:/sponsor.do");
					}
					else {
						//error: role doesn't match
						mav.addObject("status", "cannot find a user type, contact admininstrator");
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