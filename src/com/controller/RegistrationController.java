package com.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.form.Registration;
import com.service.RoleJdbcServiceImpl;
import com.service.UserJdbcServiceImpl;
import com.session.SessionScopeData;
import com.beans.Role;
import com.beans.User;

@Controller
@RequestMapping("/registrationform")


public class RegistrationController {
	//instantiates UserJdbcServiceImpl for user related activities
	UserJdbcServiceImpl  LoginService = new UserJdbcServiceImpl();
	//instantiates RoleJdbcServiceImpl for role related activities
	RoleJdbcServiceImpl  RoleService = new RoleJdbcServiceImpl();

	
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
		
            Registration registration = new Registration();
            model.put("registration", registration);
            mav.addObject("roles",roles);
            return mav;
    }

    // Process the form.
    @RequestMapping(method = RequestMethod.POST)
    public String processRegistration( Registration registration,
                    BindingResult result) {
            // set custom Validation by user
            registrationValidation.validate(registration, result);
            if (result.hasErrors()) {
                    return "registrationform";
            }
            return "registrationsuccess";
    }

	
	
}
