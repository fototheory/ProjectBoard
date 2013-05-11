package com.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.beans.Discipline;
import com.beans.Profile;
import com.beans.Role;
import com.beans.User;
import com.form.AdminUserForm;
import com.form.Registration;
import com.service.DisciplineJdbcServiceImpl;
import com.service.ProfileJdbcServiceImpl;
import com.service.RoleJdbcServiceImpl;
import com.service.UserJdbcServiceImpl;
import com.session.SessionScopeData;

@Controller
public class AdminUserListController {
	//instantiates UserJdbcServiceImpl for user related activities
	UserJdbcServiceImpl sessionService = new UserJdbcServiceImpl();
	//instantiates UserJdbcServiceImpl for user related activities
	ProfileJdbcServiceImpl profileService = new ProfileJdbcServiceImpl();
	// instantiates RoleJdbcServiceImpl for role related activities
	RoleJdbcServiceImpl roleService = new RoleJdbcServiceImpl();
	// instantiates DisciplineJdbcServiceImpl for role related activities
	DisciplineJdbcServiceImpl disciplineService = new DisciplineJdbcServiceImpl();
	//autowire session variable User
	@Autowired
    private SessionScopeData sessionScopeUserData;
	
	//this method is processed when admin login to site
	@RequestMapping(value = "/admin/index", method = RequestMethod.GET)
	public ModelAndView index() {
		if(this.sessionCheck(sessionScopeUserData)) {
			User sessionUserInfo = sessionScopeUserData.getUserInfo();
			return new ModelAndView("admin/index", "sessionUserInfo", sessionUserInfo);
		}
		else {
			return new ModelAndView(new RedirectView("../login.do"), "status", "Please login first");
		}
	}
	
	//this method is processed when admin clicks on user list on index.jsp
	@RequestMapping(value = "/admin/userList", method = RequestMethod.GET)
	public ModelAndView dispUserList() {
		ModelAndView mav = new ModelAndView("admin/userList");
		if(this.sessionCheck(sessionScopeUserData)) {
			User sessionUserInfo = sessionScopeUserData.getUserInfo();
			mav.addObject("sessionUserInfo", sessionUserInfo);
			//get all the users
			List<User> allUsers = sessionService.getAllUsers();
			mav.addObject("allUsers", allUsers);
			List<Role> rolesData = roleService.selectAllRoles();
			List<Discipline> disciplinesData = disciplineService.selectAllDisciplines();
			Map<String,String> roles = new LinkedHashMap<String,String>();
			for(int i=0; i<rolesData.size(); i++) {
				roles.put(Integer.toString(rolesData.get(i).getId()), rolesData.get(i).getName());
			}
			Map<String,String> disciplines = new LinkedHashMap<String,String>();
			for(int i=0; i<disciplinesData.size(); i++) {
				disciplines.put(Integer.toString(disciplinesData.get(i).getId()), disciplinesData.get(i).getName());
			}
			mav.addObject("roles", roles);
			mav.addObject("disciplines", disciplines);
			AdminUserForm user = new AdminUserForm();
			mav.addObject("user", user);
		}
		else {
			return new ModelAndView(new RedirectView("../login.do"), "status", "Please login first");
		}
		return mav;
	}
	
	//this method is as AJAX from when user is selected on userList.jsp and returns JSON
	@RequestMapping(value = "/admin/displayUser", params = {"userId"}, method = RequestMethod.GET, produces="application/json")
	public @ResponseBody AdminUserForm dispUser(@RequestParam(value = "userId") int userId) {
		User userInfo = sessionService.selectById(userId);
		AdminUserForm user;
		if(userInfo.getHasProfile()>0 && userInfo.getProfileId()>0) {
			Profile prof = profileService.selectById(userInfo.getProfileId());
			user = new AdminUserForm(userInfo.getId(), userInfo.getFname(), userInfo.getLname(), 
					((userInfo.getIsVerified()==1)?true:false), ((userInfo.getHasProfile()==1)?true:false),
					userInfo.getPassword(), userInfo.getPassword(), userInfo.getEmail(), prof.getCompany(),
					prof.getPhone(), prof.getSkills(), userInfo.getRoleId(), userInfo.getDisciplineId(), 
					userInfo.getProfileId(), userInfo.getGroupId());
		}
		else {
			user = new AdminUserForm(userInfo.getId(), userInfo.getFname(), userInfo.getLname(), 
					((userInfo.getIsVerified()==1)?true:false), ((userInfo.getHasProfile()==1)?true:false),
					userInfo.getPassword(), userInfo.getPassword(), userInfo.getEmail(), "",
					"", "", userInfo.getRoleId(), userInfo.getDisciplineId(), 
					userInfo.getProfileId(), userInfo.getGroupId());
		}
		return user;
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
