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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	protected ModelAndView setupForm(ModelAndView mav) {
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
		return mav;
	}
	//this method is processed when admin clicks on user list on index.jsp
	@RequestMapping(value = "/admin/userList", method = RequestMethod.GET)
	public ModelAndView dispUserList() {
		ModelAndView mav = new ModelAndView("admin/userList");
		if(this.sessionCheck(sessionScopeUserData)) {
			User sessionUserInfo = sessionScopeUserData.getUserInfo();
			mav.addObject("sessionUserInfo", sessionUserInfo);
			mav = setupForm(mav);
			AdminUserForm user = new AdminUserForm();
			mav.addObject("user", user);
		}
		else {
			return new ModelAndView(new RedirectView("../login.do"), "status", "Please login first");
		}
		return mav;
	}
	
		//this method is processed when admin submits user information
		@RequestMapping(value = "/admin/userList", method = RequestMethod.POST)
		public ModelAndView updateUser(@ModelAttribute("user") AdminUserForm userData, 
				HttpServletRequest request, BindingResult result, ModelMap model) {
			ModelAndView mav = new ModelAndView("admin/userList");
			if(this.sessionCheck(sessionScopeUserData)) {				
				if(request.getParameter("formAction").equalsIgnoreCase("edit")) {
					//validation
					adminUserFormValidation.validate(userData, result);
					if (result.hasErrors()) {
						model.addAttribute("status", "There were errors in the form. " +
								"Please correct and resubmit.");	
						model.addAttribute("error","true");
						mav = setupForm(mav);
						return mav;
					}
					//update user
					if(userData.isHasprofile() && userData.getProfile()>0) {
						int isVerified = (userData.isIsverified())?1:0;
						int HasProfile = (userData.isHasprofile())?1:0;
						//user has profile
						//fetch profile original data for rollback
						Profile profilePrevData = profileService.selectById(userData.getProfile());
						//update profile
						Profile newData = new Profile(userData.getProfile(),userData.getCompany(),
								userData.getSkills(),userData.getPhone());
						if(profileService.update(newData)>0) {
							//update user data
							User upUser = new User(userData.getId(),userData.getFname(),
									userData.getLname(),userData.getEmail(),userData.getPassword(),
									isVerified,HasProfile,userData.getRole(),
									userData.getProfile(),userData.getDiscipline(),userData.getGroup());
							if(sessionService.updateUserWithCond(upUser, "")>0) {
								mav.addObject("status", "User record updated successfully.");
							}
							else {
								//rollback profile record
								profileService.update(profilePrevData);
								mav.addObject("status", "User record update failed.");
							}
						}
						else {
							mav.addObject("status", "User record update failed.");
						}
					}
					else if(request.getParameter("profileAdd").equalsIgnoreCase("yes")) {
						boolean profIdExist = false;
						Profile profilePrevData = new Profile();
						//add profile, update user
						//check user doesn't have profile
						if(userData.getProfile()==0) {
							Profile newData = new Profile(userData.getProfile(),userData.getCompany(),
									userData.getSkills(),userData.getPhone());
							int newProfId = profileService.insert(newData);
							if(newProfId < 1) {
								//insertion failed
								mav.addObject("status", "User record update failed.");
								return mav;
							}
							//insert success
							userData.setProfile(newProfId);
							userData.setHasprofile(true);
						}
						else {
							//fetch profile original data for rollback
							profilePrevData = profileService.selectById(userData.getProfile());
							Profile newData = new Profile(userData.getProfile(),userData.getCompany(),
									userData.getSkills(),userData.getPhone());
							if(profileService.update(newData)>0) {
								//update failed
								mav.addObject("status", "User record update failed.");
								return mav;
							}
							profIdExist = true;
						}					
						int isVerified = (userData.isIsverified())?1:0;
						int HasProfile = (userData.isHasprofile())?1:0;
						User upUser = new User(userData.getId(),userData.getFname(),
								userData.getLname(),userData.getEmail(),userData.getPassword(),
								isVerified,HasProfile,userData.getRole(),
								userData.getProfile(),userData.getDiscipline(),userData.getGroup());
						if(sessionService.updateUserWithCond(upUser, "")>0) {
							mav.addObject("status", "User record updated successfully.");
						}
						else {
							if(profIdExist) {
								//rollback
								profileService.update(profilePrevData);
							}
							else {
								//delete
								profileService.delete(upUser.getProfileId());
							}
							mav.addObject("status", "User record update failed.");
						}
					}
					else {
						//only update user
						int isVerified = (userData.isIsverified())?1:0;
						int HasProfile = (userData.isHasprofile())?1:0;
						User upUser = new User(userData.getId(),userData.getFname(),
								userData.getLname(),userData.getEmail(),userData.getPassword(),
								isVerified,HasProfile,userData.getRole(),
								userData.getProfile(),userData.getDiscipline(),userData.getGroup());
						if(sessionService.updateUserWithCond(upUser, "noProfile")>0) {
							
							//Send Email
							boolean mailResult = this.emailUser(upUser);
							if (mailResult) {//Email sent successfully
								mav.addObject("status", "User record update successful\n" + 
										"(A courtesy email has been sent to the user.)");
							}
							else {//Problem sending email
								mav.addObject("status", "User record update successful\n" +
										"(Error sending courtesy email.  Possibly an error with the mail server.");
							}
						}
						else {
							mav.addObject("status", "User record update failed.");
						}
					}
				} 
				else if(request.getParameter("formAction").equalsIgnoreCase("delete")) {
					if(sessionService.deleteUser(userData.getId())>0) {
						mav.addObject("status", "User record deleted successfully.");
					}
					else {
						String roleName = roleService.getRoleName(userData.getRole());
						//If deletion failed and user was a sponsor, the likely cause is the sponsor has a project
						if (roleName.equals("Sponsor")) {
							mav.addObject("status", "Deletion failed. Cannot delete a sponsor that has a project.");
						}
						else {
							mav.addObject("status", "User record deletion failed.");
						}
					}
				}
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
	
	//Send account acknowledgment email
	protected boolean emailUser(User tempUser){
		//Send Email Acknowledgment
		String to = tempUser.getEmail();
		String subject = "Project Board Account Updated";
		String message = tempUser.getFname() + " " + tempUser.getLname() +
				",\n\nYour NU Captstone Project Board account has been updated by the System Administrator.\n\n" +
				"Account Details:\nUsername: " + tempUser.getEmail() + "\nPassword: (on file)" +
				"\nDiscipline: " + disciplineService.getDisciplineName(tempUser.getDisciplineId()) + 
				"\nRole: " + roleService.getRoleName(tempUser.getRoleId()) + 
				"\n\nIf you don't know the password, Please logon to the Project Board and click the forgot " +
				"password link.\n\nThank you,\nProject Board Administrator";
		
		//Send the email
		return mailManager.sendMail(from, to, subject, message);
	}
}
