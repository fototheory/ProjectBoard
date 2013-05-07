package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.beans.Project;
import com.service.ProjectJdbcServiceImpl;

@Controller
@RequestMapping("/acceptNewProject")
public class AcceptProjectController {
	//instantiates ProjectJdbcServiceImpl for user related activities
	ProjectJdbcServiceImpl  projectService = new ProjectJdbcServiceImpl();
		
	@RequestMapping(params = {"key"},  method = RequestMethod.GET)
	public ModelAndView acceptProjectForm( @RequestParam(value = "key") String key) {
		//decode key
		String[] decodeKeys = projectService.decodeKey(key);
		ModelAndView mav = new ModelAndView("acceptNewProject");
		if(decodeKeys.length==2) {
			int projId = Integer.parseInt(decodeKeys[0]);
			int leadId = Integer.parseInt(decodeKeys[1]);
			//check project is already accepted or not
			if(projectService.checkProjAcceptedbyLead(projId, leadId)==0) {
				String messages[] = projectService.createRequestMsg(projId, "html");
				Project submittedProj = projectService.selectById(projId);
				mav.addObject("projId", projId);
				mav.addObject("statId", submittedProj.getStatusId());
				mav.addObject("leadId", leadId);
				mav.addObject("title", messages[0]);
				mav.addObject("body", messages[1]);
			}
			else {
				mav.addObject("result", "Project is already accepted.");
			}
		}
		else {
			mav.addObject("result", "Key is malformed, please contact administrator.");
		}
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView acceptProjectSubmission(HttpServletRequest request) {
		//decode key
		ModelAndView mav = new ModelAndView("acceptNewProject");
		if(request.getParameter("acptVal").equalsIgnoreCase("true")) {
			int projId = Integer.parseInt(request.getParameter("projId"));
			int leadId = Integer.parseInt(request.getParameter("leadId"));
			int statId = Integer.parseInt(request.getParameter("statId"));
			if(projectService.leadAcceptProj(leadId, projId, statId)>0) {
				mav.addObject("result", "You successfully accepted the request.");
			}
			else {
				mav.addObject("result", "There was an error accepting the project.");
			}
		}
		else {
			mav.addObject("status", "You didn't accept the request.");
		}
		return mav;
	}
}
