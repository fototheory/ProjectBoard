package com.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.beans.Project;
import com.beans.User;
import com.mail.MailManager;
import com.service.ProjectJdbcServiceImpl;
import com.service.UserJdbcServiceImpl;
import com.session.SessionScopeData;

/**
 * <code>SponsorDefaultController</code> handles response 
 * when initial request is made to sponsor section after login.
 * operations of login are performed through <code>LoginService</code> and <code>RoleService</code>
 * @author Yumiko Iwai
 * @version 1.0
 */
@Controller
@RequestMapping("/sponsor/projectList")
public class SponsorProjectListController {
	//instantiates UserJdbcServiceImpl for user related activities
	UserJdbcServiceImpl  sessionService = new UserJdbcServiceImpl();
	//instantiates ProjectJdbcServiceImpl for user related activities
	ProjectJdbcServiceImpl  projectService = new ProjectJdbcServiceImpl();
	//autowire Mail Manager
	@Autowired
	private MailManager mailManager;
	//autowire session variable User
	@Autowired
	private SessionScopeData sessionScopeUserData;
		
	@RequestMapping(params = {"action", "id", "dispID"})
	public ModelAndView projectSubmit(@RequestParam(value = "action") String actionVal, @RequestParam(value = "id") int projId, 
				@RequestParam(value = "dispID", required=false) int dispId, HttpServletRequest request) {
		ModelAndView mav;
		String status="";
		if(this.sessionCheck(sessionScopeUserData)) {
			if(actionVal.equals("edit")) {
				mav = new ModelAndView(new RedirectView("projectForm.do"));
				mav.addObject("action", "edit");
				mav.addObject("id", projId);		
			}
			else if(actionVal.equals("submit")) {
				String serverURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
				if(projectService.updateProjectStatusLead(serverURL, mailManager, projId, "Submitted", dispId)>0) {
					status="project successfully submitted to lead faculty";
				}
				else {
					status="project submission failed";
				}
				mav = new ModelAndView(new RedirectView("projectList.do"),"status",status);
			}
			else {
				mav = new ModelAndView("sponsor/project");
			}
		}
		else {
			mav = new ModelAndView(new RedirectView("../login.do"), "status", "Please login first");
		}
		return mav;
	}
	
	@RequestMapping(params = {"action", "id"})
	public ModelAndView projectForm(@RequestParam(value = "action") String actionVal, @RequestParam(value = "id") int projId) {
		ModelAndView mav;
		String status="";
		if(this.sessionCheck(sessionScopeUserData)) {
			if(actionVal.equals("edit")) {
				mav = new ModelAndView(new RedirectView("projectForm.do"));
				mav.addObject("action", "edit");
				mav.addObject("id", projId);		
			}
			else if(actionVal.equals("delete")) {
				if(projectService.delete(projId)>0) {
					status="project successfully deleted";
				}
				else {
					status="project deletion failed";
				}
				mav = new ModelAndView(new RedirectView("projectList.do"),"status",status);
				//mav.addObject("status", status);	
				
			}
			else if(actionVal.equals("archive")) {
				if(projectService.archiveProject(projId)>0) {
					status="project successfully archived";
				}
				else {
					status="project archive failed";
				}
				mav = new ModelAndView(new RedirectView("archivedProjects.do"),"status",status);
				//mav.addObject("status", status);	
			}
			else {
				mav = new ModelAndView("sponsor/project");
			}
		}
		else {
			mav = new ModelAndView(new RedirectView("../login.do"), "status", "Please login first");
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
