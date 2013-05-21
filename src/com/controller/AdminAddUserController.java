package com.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.beans.Discipline;
import com.beans.Profile;
import com.beans.Role;
import com.beans.User;
import com.form.AdminUserForm;
import com.mail.MailManager;
import com.service.DisciplineJdbcServiceImpl;
import com.service.ProfileJdbcServiceImpl;
import com.service.RoleJdbcServiceImpl;
import com.service.UserJdbcServiceImpl;
import com.session.SessionScopeData;

@Controller
@RequestMapping(value = "/admin/addUser")
public class AdminAddUserController {
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
		//autowire the registration validation
		@Autowired
		private AdminUserFormValidation adminUserFormValidation;
		//autowire the mail manager
		@Autowired
		private MailManager mailManager;

		//From email address
		private static final String from = "webmaster.nuproactive@gmail.com";

		public void setAdminUserFormValidation(
				AdminUserFormValidation adminUserFormValidation) {
			this.adminUserFormValidation = adminUserFormValidation;
		}
		
		protected ModelAndView setupForm(ModelAndView mav) {
			//get all the users
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
			return mav;
		}
		
		//this method is processed when admin clicks on user list on index.jsp
		@RequestMapping(method = RequestMethod.GET)
		public ModelAndView addUserForm() {
			ModelAndView mav = new ModelAndView("admin/addUser");
			if(this.sessionCheck(sessionScopeUserData)) {
				User sessionUserInfo = sessionScopeUserData.getUserInfo();
				mav.addObject("sessionUserInfo", sessionUserInfo);
				setupForm(mav);
				AdminUserForm user = new AdminUserForm();
				mav.addObject("user", user);
			}
			else {
				return new ModelAndView(new RedirectView("../login.do"), "status", "Please login first");
			}
			return mav;
		}
		
		//this method is processed when admin submits user information
		@RequestMapping(method = RequestMethod.POST)
		public ModelAndView addUser(@ModelAttribute("user") AdminUserForm userData, 
				HttpServletRequest request, BindingResult result, ModelMap model) {
			ModelAndView mav = new ModelAndView("admin/addUser");
			if(this.sessionCheck(sessionScopeUserData)) {
				//validation
				adminUserFormValidation.validate(userData, result);
				if (result.hasErrors()) {
					model.addAttribute("status", "There were errors in the form. " +
							"Please correct and resubmit.");	
					model.addAttribute("error","true");
					mav = setupForm(mav);
					return mav;
				}
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
				//add user
				if(request.getParameter("profileAdd").equalsIgnoreCase("yes")) {
					//add profile, add user
					//create profile
					Profile newData = new Profile(userData.getProfile(),userData.getCompany(),
								userData.getSkills(),userData.getPhone());
					int newProfId = profileService.insert(newData);
					if(newProfId < 1) {
						//insertion failed
						mav.addObject("status", "User record insertion failed.");
						return mav;
					}
					//insert success
					userData.setProfile(newProfId);
					userData.setHasprofile(true);	
					//int isVerified = (userData.isIsverified())?1:0;
					int isVerified = 1; //User is verified by sysad
					int HasProfile = (userData.isHasprofile())?1:0;
					User newUser = new User(userData.getId(),userData.getFname(),
							userData.getLname(),userData.getEmail(),userData.getPassword(),
							isVerified,HasProfile,userData.getRole(),
							userData.getProfile(),userData.getDiscipline(),userData.getGroup());
					if(sessionService.addNewUser(newUser)>0) {
						newUser.setId(sessionService.getIdByEmail(userData.getEmail()));
						if(sessionService.updateUserWithCond(newUser,"")>0) {
							AdminUserForm user = new AdminUserForm();
							mav.addObject("user", user);
							
							//email user
							boolean mailResult = this.emailUser(newUser);
							if (mailResult) {//Email sent successfully
								mav.addObject("status", "User record insertion successful\n" + 
										"(A courtesy email has been sent to the user.)");
							}
							else {//Problem sending email
								mav.addObject("status", "User record insertion successful\n" +
										"(Error sending courtesy email.  Possibly an error with the mail server.");
							}
						}
						else {
							profileService.delete(newUser.getProfileId());
							sessionService.deleteUser(newUser.getId());
							mav.addObject("status", "User record insertion failed.");
						}
					}
					else {
						profileService.delete(newUser.getProfileId());
						mav.addObject("status", "User record insertion failed.");
					}
				}
				else {
					//only add user
					//int isVerified = (userData.isIsverified())?1:0;
					int isVerified = 1; //User is verified by sysad
					int HasProfile = (userData.isHasprofile())?1:0;
					User newUser = new User(userData.getId(),userData.getFname(),
							userData.getLname(),userData.getEmail(),userData.getPassword(),
							isVerified,HasProfile,userData.getRole(),
							userData.getProfile(),userData.getDiscipline(),userData.getGroup());
					if(sessionService.addNewUser(newUser)>0) {
						AdminUserForm user = new AdminUserForm();
						mav.addObject("user", user);
						
						//email user
						boolean mailResult = this.emailUser(newUser);
						if (mailResult) {//Email sent successfully
							mav.addObject("status", "User record insertion successful\n" + 
									"(A courtesy email has been sent to the user.)");
						}
						else {//Problem sending email
							mav.addObject("status", "User record insertion successful\n" +
									"(Error sending courtesy email.  Possibly an error with the mail server.");
						}
					}
					else {
						mav.addObject("status", "User record insertion failed.");
					}
				}
			}
			else {
				return new ModelAndView(new RedirectView("../login.do"), "status", "Please login first");
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
		
		//Send account acknowledgment email
		protected boolean emailUser(User tempUser){
			//Send Email Acknowledgment
			String to = tempUser.getEmail();
			String subject = "Project Board Account Acknowledgment";
			String message = tempUser.getFname() + " " + tempUser.getLname() +
					",\n\nAn account has been created for you on the NU Capstone Project Board.\n\n" +
					"Account Details:\nUsername: " + tempUser.getEmail() + "\nPassword: (on file)" +
					"\nDiscipline: " + disciplineService.getDisciplineName(tempUser.getDisciplineId()) + 
					"\nRole: " + roleService.getRoleName(tempUser.getRoleId()) + 
					"\n\nPlease logon to the Project Board and click the forgot password link to retrieve " +
					"your password.\n\nThank you,\nProject Board Administrator";
			
			//Send the email
			return mailManager.sendMail(from, to, subject, message);
		}
}
