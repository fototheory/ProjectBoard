package com.test;

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class SP_7ProjectListUnit {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://localhost:8080/ProjectBoard/");
		selenium.start();
	}

	@Test
	public void testSP_7ProjectListUnit() throws Exception {
		selenium.open("/ProjectBoard/");
		selenium.type("id=email", "sponsor@nu.edu");
		selenium.type("id=password", "sponsor");
		selenium.click("id=login");
		selenium.waitForPageToLoad("30000");
		selenium.click("css=#addProj > cufon.cufon.cufon-canvas > canvas");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=title", "Nursing Call Web App");
		selenium.type("id=desc", "Create a nursing call web app");
		selenium.click("id=due");
		selenium.click("link=30");
		selenium.click("id=disp");
		selenium.click("//a[@id='linkSubmit']/cufon[2]/canvas");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=ui-accordion-accordion-header-1");
		selenium.click("id=ui-accordion-accordion-header-0");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
