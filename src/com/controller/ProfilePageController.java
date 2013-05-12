package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.beans.Profile;
import com.beans.User;
import com.service.ProfileJdbcServiceImpl;
import com.service.RoleJdbcServiceImpl;
import com.service.UserJdbcServiceImpl;
import com.session.SessionScopeData;

@Controller
@RequestMapping("/profilepage")
public class ProfilePageController {
	//instantiates UserJdbcServiceImpl for user related activities
	UserJdbcServiceImpl sessionService = new UserJdbcServiceImpl();
	//instantiates ProfileJdbcServiceImpl for profile related activities
	ProfileJdbcServiceImpl profileService = new ProfileJdbcServiceImpl();
	//instantiates RoleJdbcServiceImpl for profile related activities
	RoleJdbcServiceImpl roleService = new RoleJdbcServiceImpl();
	
	//autowire session variable User
	@Autowired
    private SessionScopeData sessionScopeUserData;
	
	//autowire the validation class
	@Autowired
	private ProfilePageValidation profilePageValidation;
	
	// Display the form on the get request
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(ModelMap model) {
		ModelAndView mav = new ModelAndView("profilepage");

		if(this.sessionCheck(sessionScopeUserData)) {
			User user = sessionScopeUserData.getUserInfo();
			//send session variable to view 
			mav.addObject("sessionUserInfo", user);	
		}

		Profile profile = new Profile();
		model.put("profile", profile);

		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView processProfilePage(Profile profile,	BindingResult result, ModelMap model) {
		ModelAndView mav = new ModelAndView("profilepage");

		User user = sessionScopeUserData.getUserInfo();
		mav.addObject("sessionUserInfo", user);	

		//Check if the profile page has errors
		profilePageValidation.validate(profile, result);
		if (result.hasErrors()) {
			return mav;
		}
	
		//Add profile to database
		int profileId = profileService.insert(profile);
		
		//Update user's profile id
		sessionService.updateProfileId(user, profileId);
		//fetch role name
		model.addAttribute("roleName", roleService.getRoleName(user.getRoleId()));
		model.addAttribute("successMsg", "Profile update successful.");
		
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
