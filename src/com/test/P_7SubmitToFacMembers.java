package com.test;

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class P_7SubmitToFacMembers {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://localhost:8080/ProjectBoard/");
		selenium.start();
	}

	@Test
	public void testP_7SubmitToFacMembers() throws Exception {
		selenium.open("/ProjectBoard/");
		selenium.type("id=email", "sponsor@nu.edu");
		selenium.type("id=password", "sponsor");
		selenium.click("id=login");
		selenium.waitForPageToLoad("30000");
		selenium.click("xpath=(//a[contains(text(),'| Submit this project to Lead Faculty |')])[2]");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=ui-accordion-accordion-header-1");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
