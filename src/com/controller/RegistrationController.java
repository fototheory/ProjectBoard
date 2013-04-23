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
import com.service.DisciplineJdbcServiceImpl;
import com.service.RoleJdbcServiceImpl;
import com.service.UserJdbcServiceImpl;
import com.beans.Discipline;
import com.beans.Role;
import com.beans.User;

@Controller
@RequestMapping("/registrationform")
public class RegistrationController {
	protected final Log logger = LogFactory.getLog(getClass());

	//instantiates UserJdbcServiceImpl for user related activities
	UserJdbcServiceImpl  userService = new UserJdbcServiceImpl();
	//instantiates RoleJdbcServiceImpl for role related activities
	RoleJdbcServiceImpl  roleService = new RoleJdbcServiceImpl();
	//instantiates DisciplineJdbcServiceImpl for role related activities
	DisciplineJdbcServiceImpl disciplineService = new DisciplineJdbcServiceImpl();

	@Autowired
	private RegistrationValidation registrationValidation;

	public void setRegistrationValidation(RegistrationValidation registrationValidation) {
		this.registrationValidation = registrationValidation;
	}

	// Display the form on the get request
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showRegistration(ModelMap model) {
		ModelAndView mav = new ModelAndView("registrationform");

		List<Role> roles = roleService.selectAllRoles();
		List<Discipline> disciplines = disciplineService.selectAllDisciplines();

		Registration registration = new Registration();
		model.put("registration", registration);
		model.addAttribute("roles",roles);
		model.addAttribute("disciplines", disciplines);
		
		mav.addObject("roles",roles);
		mav.addObject("disciplines",disciplines);

		return mav;
	}

	// Process the form.
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView processRegistration( Registration registration, BindingResult result, ModelMap model) {
		ModelAndView mav = new ModelAndView("registrationsuccess");
		User tempUser = new User();
		tempUser.setFname(registration.getFname());
		tempUser.setLname(registration.getLname());
		tempUser.setEmail(registration.getEmail());
		tempUser.setPassword(registration.getPassword());
		tempUser.setRoleId(registration.getRole());
		tempUser.setDisciplineId(registration.getDiscipline());

		logger.info("processRegistration: "+tempUser);

		// set custom Validation by user
		registrationValidation.validate(registration, result);
		if (result.hasErrors()) {
			logger.info("Registration form has missing information: "+result.getFieldError());
			
			List<Role> roles = roleService.selectAllRoles();
			List<Discipline> disciplines = disciplineService.selectAllDisciplines();
			
			mav.setViewName("registrationform");
			mav.addObject("roles",roles);
			mav.addObject("disciplines",disciplines);

			return mav;
		}

		userService.addNewUser(tempUser);

		mav.addObject("role",roleService.getSpringJdbcDao().selectById(registration.getRole()).getName());
		mav.addObject("discipline",disciplineService.getSpringJdbcDao().selectById(registration.getDiscipline()).getName());

		//Log output to show that the data was written to the database.
		logger.info("Registration successfully written to the database."+userService.getUserByEmail(registration.getEmail()));
				
		return mav;
	}
}
