package com.test;

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class US_2RegistrationFailure {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://localhost:8080/ProjectBoard/");
		selenium.start();
	}

	@Test
	public void testUS_2RegistrationFailure() throws Exception {
		selenium.open("/ProjectBoard/");
		selenium.click("link=Sign up");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=fname", "User_1");
		selenium.type("id=lname", "1");
		selenium.type("id=password", "11111");
		selenium.type("id=confirmPassword", "11111");
		selenium.type("id=email", "jjj.g.sss@gmail.com");
		selenium.click("id=discipline1");
		selenium.click("id=role1");
		selenium.click("//input[@value='Submit']");
		selenium.waitForPageToLoad("30000");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
