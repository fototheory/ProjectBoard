package com.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.form.Registration;
import com.service.DisciplineJdbcServiceImpl;
import com.service.RoleJdbcServiceImpl;
import com.service.UserJdbcServiceImpl;
import com.session.SessionScopeData;
import com.beans.Discipline;
import com.beans.Role;
import com.beans.User;

@Controller
@RequestMapping("/registrationform")


public class RegistrationController {
	protected final Log logger = LogFactory.getLog(getClass());
	
	//instantiates UserJdbcServiceImpl for user related activities
	UserJdbcServiceImpl  LoginService = new UserJdbcServiceImpl();
	//instantiates RoleJdbcServiceImpl for role related activities
	RoleJdbcServiceImpl  RoleService = new RoleJdbcServiceImpl();
	
	DisciplineJdbcServiceImpl DisciplineService = new DisciplineJdbcServiceImpl();

	
	@Autowired
    private RegistrationValidation registrationValidation;

    public void setRegistrationValidation
    
    (
          RegistrationValidation registrationValidation) {
            this.registrationValidation = registrationValidation;
    }

    // Display the form on the get request
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showRegistration(Map model) {
			ModelAndView mav = new ModelAndView("registrationform");
			
			List<Role> roles = RoleService.selectAllRoles();
			List<Discipline> disciplines = DisciplineService.selectAllDisciplines();
		
            Registration registration = new Registration();
            model.put("registration", registration);

            mav.addObject("roles",roles);
            mav.addObject("discipline",disciplines);
            
            return mav;
    }

    // Process the form.
    @RequestMapping(method = RequestMethod.POST)
    public String processRegistration( Registration registration, BindingResult result) {
    	User temp = new User();
    	temp.setFname(registration.getFname());
    	temp.setLname(registration.getLname());
    	temp.setEmail(registration.getEmail());
    	temp.setPassword(registration.getPassword());
    	temp.setRoleId(registration.getRole());
    	temp.setDisciplineId(registration.getDiscipline());
    	
    	logger.info("processRegistration: "+temp);
    	
            // set custom Validation by user
            registrationValidation.validate(registration, result);
            if (result.hasErrors()) {
                    return "registrationform";
            }

        LoginService.addNewUser(temp);
            
            
            return "registrationsuccess";
    }

	
	
}
