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
import com.service.DisciplineJdbcServiceImpl;
import com.service.ProjectJdbcServiceImpl;
import com.service.StatusJdbcServiceImpl;
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
	//instantiates service classes for service related activities
	UserJdbcServiceImpl  sessionService = new UserJdbcServiceImpl();
	ProjectJdbcServiceImpl projService  = new ProjectJdbcServiceImpl();
	StatusJdbcServiceImpl statService  = new StatusJdbcServiceImpl();
	DisciplineJdbcServiceImpl dispService  = new DisciplineJdbcServiceImpl();
	//autowire session variable User
	@Autowired
	private SessionScopeData sessionScopeUserData;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView projectDefForm() {
		return new ModelAndView("sponsor/projectForm");
	}	
	
	@RequestMapping(params = {"action", "id"},  method = RequestMethod.GET)
	public ModelAndView projectForm( @RequestParam(value = "action") String actionVal, 
			@RequestParam(value = "id") int projId) {
		ModelAndView mav = new ModelAndView("sponsor/projectForm");
		mav.addObject("action", actionVal);
		//get all disciplines
		mav.addObject("disciplines",dispService.selectAllDisciplines());
		if(this.sessionCheck(sessionScopeUserData)) {
			if(actionVal.equals("edit")) {
				//fetch project information based on id
				Project proj = projService.selectById(projId);
				String dueDate = null;
				if(proj.getDue()!=null) {
					dueDate = changetoSqlDate(proj.getDue());
				}
				ProjectInfo ProjData = new ProjectInfo(proj.getId(),proj.getTitle(),
						proj.getDesc(),proj.getDispId(), dueDate, proj.getSponsorId(), actionVal);
				mav.addObject("ProjData", ProjData);
			}
			else if(actionVal.equals("add")) {
				ProjectInfo ProjData = new ProjectInfo(0,"", "",0, null,
							sessionScopeUserData.getUserInfo().getId(),actionVal);
				mav.addObject("ProjData", ProjData);
			}
			else {
				mav.addObject("ProjData", new Project());
			}
		}
		else {
			//session check failed
			mav = new ModelAndView(new RedirectView("../login.do"), "status", "Please login first");
		}
		return mav;
	}	
	
	protected String changetoSqlDate(Date date) {
		DateFormat df =  new SimpleDateFormat("MM/dd/yyyy");
		return df.format(date);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView projectSubmit(@ModelAttribute("ProjData") ProjectInfo ProjData) {
		if(this.sessionCheck(sessionScopeUserData)) {
			String status = "";
			Map<String, String> errors = new HashMap<String, String>();
			Project proj = new Project();
			//check project title is not empty
			if(ProjData.getTitle().length()>0) {
				proj.setTitle(ProjData.getTitle());
			}
			else {
				errors.put("title", "Title is required");
			}
			//check project description is not empty
			if(ProjData.getDesc().length()>0) {
				proj.setDesc(ProjData.getDesc());
			}
			else {
				errors.put("desc", "Project Description is required");
			}
			//check date is not empty
			if(ProjData.getDue().length()>0) {
				try {					
					Date date = new SimpleDateFormat("MM/dd/yyyy").parse(ProjData.getDue());
					proj.setDue(date);		
				}
				catch(Exception e) {
					System.out.println(e.toString());
				}
			}
			else {
				errors.put("due", "Due Date is required");
			}	
			//check discipline is selected
			if(ProjData.getDisp()>0) {
				proj.setDispId(ProjData.getDisp());	
			}
			else {
				errors.put("disp", "Disciplinen is required");
			}
			//check there is no error
			if(errors.isEmpty()) {
				if(ProjData.getAction().equals("edit")) {
					proj.setId(ProjData.getProjectId());
					if(projService.updateProjectInfo(proj)>0) {
						status="project successfully updated";
					}
					else {
						status="project update failed";
					}	
				}
				else if(ProjData.getAction().equals("add")) {
					//set sponsor id and discipline
					proj.setSponsorId(sessionScopeUserData.getUserInfo().getId());
					proj.setDispId(sessionScopeUserData.getUserInfo().getDisciplineId());
					//get status 'new'
					proj.setStatusId(statService.selectIdByName("New"));
					if(projService.add(proj)>0) {
						status="project successfully added";
					}
					else {
						status="project addition failed";
					}
				}			
			}
			else {
				ModelAndView mav = new ModelAndView("sponsor/projectForm");
				mav.addObject("ProjData", ProjData);
				mav.addObject("errors", errors);
				return mav;
			}
					
			return new ModelAndView(new RedirectView("projectList.do"),"status",status);
		}
		return new ModelAndView(new RedirectView("../login.do"), "status", "Please login first");
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
