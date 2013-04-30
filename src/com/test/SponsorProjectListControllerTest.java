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
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import com.beans.User;
import com.controller.SponsorDefaultController;
import com.controller.SponsorProjectListController;
import com.session.SessionScopeData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:WebContent/WEB-INF/applicationContext.xml")
public class SponsorProjectListControllerTest {

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private User user;
    private SponsorDefaultController controller;
    @Autowired
    private SessionScopeData sessionScopeUserData;
    private AnnotationMethodHandlerAdapter adapter;
    @Autowired
    private ApplicationContext applicationContext;

    @Before
    public void setUp() {
        request    = new MockHttpServletRequest();
        response   = new MockHttpServletResponse();
        response.setOutputStreamAccessAllowed(true);
        controller = new SponsorDefaultController();
        adapter = new AnnotationMethodHandlerAdapter();
        user = new User();
        user.setId(7);
        user.setHasProfile(1);
        sessionScopeUserData.setUserInfo(user);
    }

    @Test
    public void findRelatedVideosTest() throws Exception {
        request.setRequestURI("/sponsor");
        request.setMethod("GET");
        //request.addParameter("myParam", "myValue");
        try{
        	adapter.handle(request, response, controller);
        	System.out.println(response.getContentAsString());
        }catch (Exception exception){
        	System.out.println(exception.getMessage());
            Assert.fail(exception.getMessage());
        }       
    }

}