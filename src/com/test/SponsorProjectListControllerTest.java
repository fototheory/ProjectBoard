package com.test;

import static org.junit.Assert.*;

import junit.framework.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.RedirectView;
import com.beans.User;
import com.controller.SponsorDefaultController;
import com.controller.SponsorProjectListController;
import com.session.SessionScopeData;
import com.session.SessionScopeDataImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:WebContent/WEB-INF/applicationContext.xml")
public class SponsorProjectListControllerTest {

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private User user;
    private SponsorDefaultController controller;
    @Autowired
    private SessionScopeData sessionScopeUserData;
    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;
    @Autowired
    private RequestMappingHandlerMapping handlerMapping;
    @Autowired
    private ApplicationContext applicationContext;
    
    @Before
    public void setUp() {
        request    = new MockHttpServletRequest();
        response   = new MockHttpServletResponse();
        response.setOutputStreamAccessAllowed(true);
        controller = new SponsorDefaultController();
        
    }

    @Test
    public void notLoggedIn() throws Exception {  
    	sessionScopeUserData.setUserInfo(new User());
        request.setRequestURI("/sponsor.do");
        request.setMethod("GET");
        //request.addParameter("myParam", "myValue");
        Object handler;
        try{
        	handler = handlerMapping.getHandler(request).getHandler();
        	request.setAttribute(DispatcherServlet.OUTPUT_FLASH_MAP_ATTRIBUTE,new FlashMap());
        	ModelAndView mv = handlerAdapter.handle(request, response, handler);
        	assertEquals(((RedirectView) mv.getView()).getUrl(),"../login.do");
        }catch (Exception exception){
        	System.out.println(exception.getMessage());
            Assert.fail(exception.getMessage());
        }       
    }
    
    @Test
    public void logout() throws Exception {  
    	setSessionUser();
        request.setRequestURI("/sponsor.do");
        request.setMethod("GET");
        request.addParameter("logout", "yes");
        Object handler;
        try{
        	handler = handlerMapping.getHandler(request).getHandler();
        	request.setAttribute(DispatcherServlet.OUTPUT_FLASH_MAP_ATTRIBUTE,new FlashMap());
        	ModelAndView mv = handlerAdapter.handle(request, response, handler);
        	User emptyUser = sessionScopeUserData.getUserInfo();
        	assertEquals(emptyUser.getId(),0);
        	assertEquals(emptyUser.getHasProfile(),0);
        	assertEquals(emptyUser.getRoleId(),0);
        }catch (Exception exception){
        	System.out.println(exception.getMessage());
            Assert.fail(exception.getMessage());
        }       
    }
    
    @Test
    public void displayProjectList() throws Exception {
    	setSessionUser();
        request.setRequestURI("/sponsor.do");
        request.setMethod("GET");
        //request.addParameter("myParam", "myValue");
        Object handler;
        try{
        	handler = handlerMapping.getHandler(request).getHandler();
        	request.setAttribute(DispatcherServlet.OUTPUT_FLASH_MAP_ATTRIBUTE,new FlashMap());
        	ModelAndView mv = handlerAdapter.handle(request, response, handler);
        	assertEquals(((RedirectView) mv.getView()).getUrl(),"sponsor/projectList.do");
        }catch (Exception exception){
        	System.out.println(exception.getMessage());
            Assert.fail(exception.getMessage());
        }       
    }

    protected void setSessionUser() {
    	user = new User();
        user.setId(6);
        user.setHasProfile(1);
        user.setRoleId(2);
        sessionScopeUserData.setUserInfo(user);
    }
}