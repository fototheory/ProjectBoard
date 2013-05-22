package com.controller;

import java.util.Collection;
import java.util.HashSet;
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

import com.beans.Project;
import com.beans.User;
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
@RequestMapping("/sponsor")
public class SponsorDefaultController {
	//instantiates UserJdbcServiceImpl for user related activities
	UserJdbcServiceImpl  sessionService = new UserJdbcServiceImpl();
	//instantiates ProjectJdbcServiceImpl for user related activities
	ProjectJdbcServiceImpl  projectService = new ProjectJdbcServiceImpl();
	//autowire session variable User
	@Autowired
    private SessionScopeData sessionScopeUserData;
	
	//this method is processed when sponsor directory is accessed
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
	public ModelAndView projectList(@RequestParam(value = "status", required=false) String stat, 
				@RequestParam(value = "selectedId", required=false) String selectedId) {
		ModelAndView mav = new ModelAndView("/sponsor/projectList");
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
			if(selectedId !=null && selectedId.length()>0) {
				//check selectedId is number
				try { 
					mav.addObject("selectedId", getSelectedProject(Integer.parseInt(selectedId), projList));
			    } catch(NumberFormatException e) { 
			    	System.out.println(e.toString());
			    }				
			}
			return mav;
		}
		else {
			//user hasn't logged in
			return  new ModelAndView(new RedirectView("../login.do"), "status", "Please login first");
		}
	}
	
	protected int getSelectedProject(int selectedId, Collection<Map<String, String>> projList) {
		//loop each project
		int counter = 0;
		for (Map<String, String> proj: projList) {
			if(selectedId==Integer.parseInt(proj.get("ID"))) {
				return counter;
			}
			counter++;

	    }
		return 0;
	}
	
	@RequestMapping(value = "/archivedProjects", method = RequestMethod.GET)
	public ModelAndView projectArchivedList(@RequestParam(value = "status", required=false) String stat, 
			@RequestParam(value = "id", required=false) String selectedId) {
		ModelAndView mav = new ModelAndView("/sponsor/archivedProjects");
		if(this.sessionCheck(sessionScopeUserData)) {
			User user = sessionScopeUserData.getUserInfo();	
			//check sponsor has an archived project already
			//check id exists
			boolean idExist = false;
			int id = 0;
			Collection<Map<String, String>> projList = new HashSet<Map<String, String>>();
			//check sponsor has a project already
			if(selectedId !=null && selectedId.length()>0) {
				try { 
					id = Integer.parseInt(selectedId);
					idExist = true;
			    } catch(NumberFormatException e) { 
			    	System.out.println(e.toString());
			    }	
				
				if(idExist) {
					Project project = projectService.selectById(id);
					projList.add(projectService.formatProjectItem(project));
				}
				else {
					
				}
			}
			
			if(!idExist) {
				projList = projectService.selectArchivedByRole(user.getId(), user.getRoleId());	
			}
			
			//send session variable to view 
			mav.addObject("sessionUserInfo", user);	
			//send session variable to view 
			mav.addObject("projectList", projList);	
			mav.addObject("status", stat);
			if(selectedId !=null && selectedId.length()>0) {
				//check selectedId is number
				try { 
					mav.addObject("selectedId", getSelectedProject(Integer.parseInt(selectedId), projList));
			    } catch(NumberFormatException e) { 
			    	System.out.println(e.toString());
			    }				
			}
			return mav;
		}
		else {
			//user hasn't logged in
			return  new ModelAndView(new RedirectView("../login.do"), "status", "Please login first");
		}
	}
	
	@RequestMapping(value = "/projectprogress", method = RequestMethod.GET)
	public ModelAndView projectProgress(@RequestParam(value = "status", required=false) String stat) {
		ModelAndView mav = new ModelAndView("/sponsor/projectprogress");
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
	
	@RequestMapping(value = "/communication", method = RequestMethod.GET)
	public ModelAndView communication(@RequestParam(value = "status", required=false) String stat) {
		ModelAndView mav = new ModelAndView("/sponsor/communication");
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
			//check sponsor has a project already
			int projectCnt = projectService.countByRole(user.getId(), user.getRoleId());		
			if(projectCnt > 0) {
				return new ModelAndView ( new RedirectView("sponsor/projectList.do"));
			}
			else {
				ModelAndView mav = new ModelAndView ( new RedirectView("sponsor/projectForm.do"));
				mav.addObject("action","add");
				mav.addObject("id",0);
				return mav;
			}
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
			mav = new ModelAndView("redirect:/sponsor.do");
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
