package com.test;

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class P_1AddProject {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://localhost:8080/ProjectBoard/");
		selenium.start();
	}

	@Test
	public void testP_1AddProject() throws Exception {
		selenium.open("/ProjectBoard/");
		selenium.type("id=email", "sponsor@nu.edu");
		selenium.type("id=password", "sponsor");
		selenium.click("id=login");
		selenium.waitForPageToLoad("30000");
		selenium.open("/ProjectBoard/sponsor/projectList.do");
		selenium.click("//a[@id='addProj']/cufon[2]/canvas");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=title", "Nursing Call Web App");
		selenium.type("id=desc", "Develop a nursing call web app for our nurses to help our patients ASAP");
		selenium.click("id=due");
		selenium.click("css=span.ui-icon.ui-icon-circle-triangle-e");
		selenium.click("link=27");
		selenium.click("id=disp");
		selenium.click("//a[@id='linkSubmit']/cufon[4]/canvas");
		selenium.waitForPageToLoad("30000");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
