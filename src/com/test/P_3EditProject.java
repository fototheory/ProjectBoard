package com.test;

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class P_3EditProject {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://localhost:8080/ProjectBoard/");
		selenium.start();
	}

	@Test
	public void testP_3EditProject() throws Exception {
		selenium.open("/ProjectBoard/");
		selenium.type("id=email", "sponsor@nu.edu");
		selenium.type("id=password", "sponsor");
		selenium.click("id=login");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=| Edit project |");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=desc", "Develop 2 type of nursing call web apps for our nurses using iPhones and Android phones to help our patients ASAP");
		selenium.click("id=due");
		selenium.click("css=span.ui-icon.ui-icon-circle-triangle-e");
		selenium.click("css=span.ui-icon.ui-icon-circle-triangle-e");
		selenium.click("link=23");
		selenium.click("id=disp");
		selenium.click("//a[@id='linkSubmit']/cufon[2]/canvas");
		selenium.waitForPageToLoad("30000");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
