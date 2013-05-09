package com.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.beans.Discipline;
import com.beans.Role;
import com.beans.User;
import com.mail.MailManager;
import com.service.DisciplineJdbcServiceImpl;
import com.service.RoleJdbcServiceImpl;
import com.service.UserJdbcServiceImpl;
import com.session.SessionScopeData;

@Controller
@RequestMapping("/admin/adminpage")
public class AdminPageController {
	//From email address
	private static final String from = "webmaster.nuproactive@gmail.com";
	
	//instantiates UserJdbcServiceImpl for user related activities
	UserJdbcServiceImpl sessionService = new UserJdbcServiceImpl();
	// instantiates RoleJdbcServiceImpl for role related activities
	RoleJdbcServiceImpl roleService = new RoleJdbcServiceImpl();
	// instantiates DisciplineJdbcServiceImpl for role related activities
	DisciplineJdbcServiceImpl disciplineService = new DisciplineJdbcServiceImpl();
	
	//autowire session variable User
	@Autowired
    private SessionScopeData sessionScopeUserData;
	
	//autowire the mail manager
	@Autowired
	private MailManager mailManager;

	private List<User> userList;
	private List<Role> roles;
	private List<Discipline> disciplines;

	// Display the form on the get request
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(ModelMap model) {
		ModelAndView mav = new ModelAndView("admin/adminpage");
		
		//Check if user is logged in
		if(this.sessionCheck(sessionScopeUserData)) {
			User user = new User();
			roles = roleService.selectAllRoles();
			disciplines = disciplineService.selectAllDisciplines();

			//Send session variable to the view 
			user = sessionScopeUserData.getUserInfo();
			mav.addObject("sessionUserInfo", user);	
			
			//Get unverified users and add them to the model and view
			userList =  sessionService.getUnVerifiedUsers();
			model.put("user",new User());
			model.addAttribute("userList", userList);
			mav.addObject("userList", userList);
			
			//Add the roles to the model and view
			model.put("role", new Role());
			model.addAttribute("roles", roles);
			mav.addObject("roles", roles);
			
			//Add the disciplines to the model and view
			model.put("discipline", new Discipline());
			mav.addObject("disciplines", disciplines);
			model.addAttribute("disciplines", disciplines);
			
		}
		else {
			//user hasn't logged in
			mav.addObject("status", "Please login first.");
			mav.setViewName("redirect:/login.do");
		}
		return mav;
	}

	//Process the admin page
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView processAdminPage(User selectedUser, ModelMap model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/adminpage");

		//Check if user is logged in
		if(this.sessionCheck(sessionScopeUserData)) {
			User user = sessionScopeUserData.getUserInfo();
			//send session variable to view 
			mav.addObject("sessionUserInfo", user);	

			//Get selected user's data from the DB
			selectedUser = sessionService.getUserByEmail(selectedUser.getEmail());

			//Check if no user was selected
			if(selectedUser.getEmail() != null) {
				//verify user
				sessionService.verifyUser(selectedUser);
				
				//Send email
				String serverURL = request.getScheme() + "://" + request.getServerName() + ":" +
						request.getServerPort() + request.getContextPath();

				//Setup email strings
				String to = selectedUser.getEmail();
				String subject = "Account Verified";
				String message = selectedUser.getFname() + " " + selectedUser.getLname() +
						",\n\nYour request for account activation has been approved.\n\n" +
						"Please login to the Project Board and complete your profile page.\n\n" +
						"Project Board url: " + serverURL + "\n\n" +
						"Thank you,\nProject Board Administrator";
				
				//Send email
				boolean result = mailManager.sendMail(from, to, subject, message);
				
				if (result) {
					model.addAttribute("successMsg", selectedUser.getFname()+" " +
							selectedUser.getLname()+" verification successful.");
				}
				else {
					model.addAttribute("successMsg", "There was an problem emailing " + 
							selectedUser.getFname()+" " +selectedUser.getLname()+".");
				}
			}
			else {
				model.addAttribute("successMsg", "No user was selected. Please select a user first.");
			}
		}
		else {
			//User hasn't logged in
			mav.addObject("status", "Please login first.");
			mav.setViewName("redirect:/login.do");
		}

		//Get unverified users and add them to the model and view
		userList =  sessionService.getUnVerifiedUsers();
		model.addAttribute("userList", userList);
		mav.addObject("userList", userList);

		//Add the roles to the model and view
		model.put("role", new Role());
		model.addAttribute("roles", roles);
		mav.addObject("roles", roles);
		
		//Add the disciplines to the model and view
		model.put("discipline", new Discipline());
		mav.addObject("disciplines", disciplines);
		model.addAttribute("disciplines", disciplines);
		
		return mav;
	}

	@RequestMapping(params = {"logout"})
	public ModelAndView logout(@RequestParam(value = "logout") String logoutVal) {
		ModelAndView mav;
		if(logoutVal.equals("yes")) {
			sessionScopeUserData.setUserInfo(new User());
			mav = new ModelAndView("redirect:/login.do", "status", "You successfully logout.");
		}
		else {
			mav = new ModelAndView("redirect:/admin/adminpage.do");
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
