package com.controller;

import java.util.Collection;
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
@RequestMapping("/sponsor/projectList")
public class SponsorProjectListController {
	@RequestMapping(params = {"action", "id"})
	public ModelAndView projectForm(@RequestParam(value = "action") String actionVal, @RequestParam(value = "id") int projId) {
		ModelAndView mav;
		if(actionVal.equals("edit")) {
			mav = new ModelAndView(new RedirectView("projectForm.do"));
			mav.addObject("action", "edit");
			mav.addObject("id", projId);		
		}
		else if(actionVal.equals("submit")) {
			mav = new ModelAndView("redirect:/sponsor.do");
		}
		else {
			mav = new ModelAndView("sponsor/project");
		}
		return mav;
	}
	
}
