package com.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.beans.Project;
import com.beans.User;
import com.form.ProjectInfo;
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
@RequestMapping("/sponsor/projectForm")
public class SponsorProjectFormController {
	ProjectJdbcServiceImpl projService  = new ProjectJdbcServiceImpl();
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView projectDefForm() {
		return new ModelAndView("sponsor/projectForm");
	}	
	
	@RequestMapping(params = {"action", "id"},  method = RequestMethod.GET)
	public ModelAndView projectForm( @RequestParam(value = "action") String actionVal, 
			@RequestParam(value = "id") int projId) {
		ModelAndView mav = new ModelAndView("sponsor/projectForm");
		mav.addObject("action", actionVal);
		if(actionVal.equals("edit")) {
			//fetch project information based on id
			Project proj = projService.selectById(projId);
			String dueDate = null;
			if(proj.getDue()!=null) {
				dueDate = changetoSqlDate(proj.getDue());
			}
			ProjectInfo ProjData = new ProjectInfo(proj.getId(),proj.getTitle(),
					proj.getDesc(),proj.getDispId(), dueDate,actionVal);
			mav.addObject("ProjData", ProjData);
		}
		else {
			mav.addObject("ProjData", new Project());
		}
		return mav;
	}	
	
	protected String changetoSqlDate(Date date) {
		DateFormat df =  new SimpleDateFormat("MM/dd/yyyy");
		return df.format(date);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView projectSubmit(@ModelAttribute("ProjData") ProjectInfo ProjData) {
		String status = "";
		if(ProjData.getAction().equals("edit")) {
			Project proj = new Project();
			proj.setId(ProjData.getProjectId());
			proj.setTitle(ProjData.getTitle());
			proj.setDesc(ProjData.getDesc());
			proj.setDispId(ProjData.getDisp());	
			try {
				Date date = new SimpleDateFormat("MM/dd/yyyy").parse(ProjData.getDue());
				proj.setDue(date);		
			}
			catch(Exception e) {
				System.out.println(e.toString());
			}
			if(projService.updateProjectInfo(proj)>0) {
				status="project successfully updated";
			}
			else {
				status="project update failed";
			}
			
		}
		/*mav.addObject("action", actionVal);
		if(actionVal.equals("edit")) {
			//fetch project information based on id
			mav.addObject("ProjData", projService.selectById(projId));
		}
		else {
			mav.addObject("ProjData", new Project());
		}*/
		return new ModelAndView(new RedirectView("projectList.do"),"status",status);
	}	
}
