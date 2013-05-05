package com.test;

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class GUI_sponsorEditProject {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://localhost:8080/ProjectBoard/");
		selenium.start();
	}

	@Test
	public void testGUI_sponsorEditProject() throws Exception {
		selenium.open("/ProjectBoard/");
		selenium.click("css=span");
		selenium.type("id=email", "sponsor@nu.edu");
		selenium.click("id=password");
		selenium.type("id=password", "sponsor");
		selenium.click("id=login");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=| Submit this project to Lead Faculty |");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=email", "sponsor@nu.edu");
		selenium.type("id=password", "sponsor");
		selenium.click("id=login");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=ui-accordion-accordion-header-1");
		selenium.click("link=| Delete project |");
		selenium.waitForPageToLoad("30000");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
