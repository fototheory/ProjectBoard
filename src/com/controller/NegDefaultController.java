package com.controller;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.beans.Discipline;
import com.beans.Project;
import com.beans.User;
import com.service.DisciplineJdbcServiceImpl;
import com.service.ProjectJdbcServiceImpl;
import com.service.UserJdbcServiceImpl;
import com.session.SessionScopeData;

/**
 * <code>NEgFacDefaultController</code> 

 */
@Controller
@RequestMapping("/negFac")
public class NegDefaultController {
	//instantiates UserJdbcServiceImpl for user related activities
	UserJdbcServiceImpl  sessionService = new UserJdbcServiceImpl();
	//instantiates ProjectJdbcServiceImpl for user related activities
	ProjectJdbcServiceImpl  projectService = new ProjectJdbcServiceImpl();
	DisciplineJdbcServiceImpl dispService  = new DisciplineJdbcServiceImpl();
	//autowire session variable User
	@Autowired
    private SessionScopeData sessionScopeUserData;
	
	//this method is processed when negFac directory is accessed
	@RequestMapping(value = "/index/{id}", method = RequestMethod.GET)
	public ModelAndView loginDetails(@PathVariable int id) {
		ModelAndView mav = new ModelAndView("/student/index");
		if(this.sessionCheck(sessionScopeUserData)) {
			User user = sessionScopeUserData.getUserInfo();
			//send session variable to view 
			mav.addObject("sessionUserInfo", user);	
			//check sponsor has a project already
			int projectCnt = projectService.countByRole(user.getId(), user.getRoleId());
		}
		//session check failed
		mav = new ModelAndView(new RedirectView("../login.do"), "status", "Please login first");
		return mav;
	}
	
	
	@RequestMapping(value = "/projectList", method = RequestMethod.GET)
	public ModelAndView projectList(@RequestParam(value = "status", required=false) String stat) {
		ModelAndView mav = new ModelAndView("/negFac/projectList");
		if(this.sessionCheck(sessionScopeUserData)) {
			User user = sessionScopeUserData.getUserInfo();	
			//check sponsor has a project already
			Collection<Map<String, String>> projList = projectService.selectByRole(user.getId(), user.getRoleId());	
			Collection<Map<String, String>> archiveList = projectService.selectArchivedByRole(user.getId(), user.getRoleId());	
			//send session variable to view 
			mav.addObject("sessionUserInfo", user);	
			//send session variable to view 
			mav.addObject("projectList", projList);	
			//send session variable to view 
			mav.addObject("archiveList", archiveList);	
			mav.addObject("status", stat);
			//get all disciplines
			List<Discipline> disciplinesData = dispService.selectAllDisciplines();
			Map<String,String> disciplines = new LinkedHashMap<String,String>();
			for(int i=0; i<disciplinesData.size(); i++) {
				disciplines.put(Integer.toString(disciplinesData.get(i).getId()), disciplinesData.get(i).getName());
			}
			mav.addObject("disciplines",disciplines);
			return mav;
		}
		else {
			//user hasn't logged in
			return  new ModelAndView(new RedirectView("../login.do"), "status", "Please login first");
		}
	}
	
	@RequestMapping(value = "/archivedProjects", method = RequestMethod.GET)
	public ModelAndView projectArchivedList(@RequestParam(value = "status", required=false) String stat) {
		ModelAndView mav = new ModelAndView("/negFac/archivedProjects");
		if(this.sessionCheck(sessionScopeUserData)) {
			User user = sessionScopeUserData.getUserInfo();	
			//check sponsor has a project already
			Collection<Map<String, String>> projList = projectService.selectArchivedByRole(user.getId(), user.getRoleId());	
			//send session variable to view 
			mav.addObject("sessionUserInfo", user);	
			//send session variable to view 
			mav.addObject("projectList", projList);	
			mav.addObject("status", stat);
			return mav;
		}
		else {
			//user hasn't logged in
			return  new ModelAndView(new RedirectView("../login.do"), "status", "Please login first");
		}
	}
	
	@RequestMapping(value = "/projectprogress", method = RequestMethod.GET)
	public ModelAndView projectProgress(@RequestParam(value = "status", required=false) String stat) {
		ModelAndView mav = new ModelAndView("/negFac/projectprogress");
		if(this.sessionCheck(sessionScopeUserData)) {
			User user = sessionScopeUserData.getUserInfo();	
			mav.addObject("sessionUserInfo", user);
			return mav;
		}
		else {
			//user hasn't logged in
			return  new ModelAndView(new RedirectView("../login.do"), "status", "Please login first");
		}
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		if(this.sessionCheck(sessionScopeUserData)) {
			User user = sessionScopeUserData.getUserInfo();	
			return new ModelAndView ( new RedirectView("negFac/projectList.do"));
		}
		else {
			//user hasn't logged in
			return  new ModelAndView(new RedirectView("../login.do"), "status", "Please login first");
		}
	}

	@RequestMapping(params = {"logout"})
	public ModelAndView logout(@RequestParam(value = "logout") String logoutVal) {
		ModelAndView mav;
		if(logoutVal.equals("yes")) {
			sessionScopeUserData.setUserInfo(new User());
			mav = new ModelAndView(new RedirectView("login.do"), "status", "You successfully logout");
			//mav.addObject("status", "success");
		}
		else {
			mav = new ModelAndView("redirect:/negFac.do");
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

