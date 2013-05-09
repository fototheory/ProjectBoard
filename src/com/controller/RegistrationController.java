package com.controller;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.form.Registration;
import com.mail.MailManager;
import com.service.DisciplineJdbcServiceImpl;
import com.service.RoleJdbcServiceImpl;
import com.service.UserJdbcServiceImpl;
import com.beans.Discipline;
import com.beans.Role;
import com.beans.User;

@Controller
@RequestMapping("/registrationform")
public class RegistrationController {
	//Initialize the logger
	private final Log logger = LogFactory.getLog(getClass());

	//From email address
	private static final String from = "webmaster.nuproactive@gmail.com";

	// instantiates UserJdbcServiceImpl for user related activities
	UserJdbcServiceImpl userService = new UserJdbcServiceImpl();
	// instantiates RoleJdbcServiceImpl for role related activities
	RoleJdbcServiceImpl roleService = new RoleJdbcServiceImpl();
	// instantiates DisciplineJdbcServiceImpl for role related activities
	DisciplineJdbcServiceImpl disciplineService = new DisciplineJdbcServiceImpl();

	//autowire the registration validation
	@Autowired
	private RegistrationValidation registrationValidation;

	//autowire the mail manager
	@Autowired
	private MailManager mailManager;

	public void setRegistrationValidation(
			RegistrationValidation registrationValidation) {
		this.registrationValidation = registrationValidation;
	}

	// Display the form on the get request
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showRegistration(ModelMap model) {
		ModelAndView mav = new ModelAndView("registrationform");

		List<Role> roles = roleService.selectAllRolesExceptAdmin();
		List<Discipline> disciplines = disciplineService.selectAllDisciplines();

		Registration registration = new Registration();
		model.put("registration", registration);
		model.addAttribute("roles", roles);
		model.addAttribute("disciplines", disciplines);

		mav.addObject("roles", roles);
		mav.addObject("disciplines", disciplines);

		return mav;
	}

	// Process the form.
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView processRegistration(Registration registration,
			BindingResult result, ModelMap model) {
		ModelAndView mav = new ModelAndView("registrationsuccess");
		User tempUser = new User();
		tempUser.setFname(registration.getFname());
		tempUser.setLname(registration.getLname());
		tempUser.setEmail(registration.getEmail());
		tempUser.setPassword(registration.getPassword());
		tempUser.setRoleId(registration.getRole());
		tempUser.setDisciplineId(registration.getDiscipline());

		//Add the roles and disciplines to the view
		List<Role> roles = roleService.selectAllRolesExceptAdmin();
		List<Discipline> disciplines = disciplineService.selectAllDisciplines();
		mav.addObject("roles", roles);
		mav.addObject("disciplines", disciplines);

		//Backend validation of the form
		registrationValidation.validate(registration, result);
		if (result.hasErrors()) {
			mav.setViewName("registrationform");
			model.addAttribute("successMsg", "There were errors in the form. " +
					"Please correct and resubmit.");	
			return mav;
		}

		//Add User to the DB
		int addResult = userService.addNewUser(tempUser);
		
		//Check if the user was added
		if (addResult != 1) { //Duplicate email error
			result.rejectValue("email", "", "Duplicate email address.");
			model.addAttribute("successMsg", "Email address already used. " +
					"Please use a different email address.");
			mav.setViewName("registrationform");
		}
		else { //User was added to the DB
			mav.addObject("role",roleService.getSpringJdbcDao()
						.selectById(registration.getRole()).getName());
			mav.addObject("discipline", disciplineService.getSpringJdbcDao()
					.selectById(registration.getDiscipline()).getName());
			
			//Send Email Acknowledgment
			String to = tempUser.getEmail();
			String subject = "Project Board Registration Acknowledgment";
			String message = tempUser.getFname() + " " + tempUser.getLname() +
					",\n\nThank you for registering with the NU Capstone Project Board.\n\n" +
					"Account Details:\nUsername: " + tempUser.getEmail() + "\nPassword: (on file)" +
					"\nDiscipline: " + disciplineService.getDisciplineName(tempUser.getDisciplineId()) + 
					"\nRole: " + roleService.getRoleName(tempUser.getRoleId()) + 
					"\n\nYou should receive an email within 24hrs informing you that your account is " +
					"ready.\n\nThank you,\nProject Board Administrator";
			
			//Send email
			boolean mailResult = mailManager.sendMail(from, to, subject, message);

			if (mailResult) {//Email sent successfully
				logger.info("Email acknowledgement sent to " + tempUser.getFname() +" " + 
							tempUser.getLname() + ".");
				model.addAttribute("successMsg", 
						"(A courtesy email of this page has been emailed to the email address above.)");

			}
			else {//Problem sending email
				logger.warn("Email ackowledgement not sent to user. " +
						"Possible an error with the mail server.");
			}
		}
		return mav;
	}
}
