package com.test;

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class SP_8ProjectListIntegration {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://localhost:8080/ProjectBoard/");
		selenium.start();
	}

	@Test
	public void testSP_8ProjectListIntegration() throws Exception {
		selenium.open("/ProjectBoard/");
		selenium.type("id=email", "sponsor@nu.edu");
		selenium.type("id=password", "sponsor");
		selenium.click("id=login");
		selenium.waitForPageToLoad("30000");
		selenium.click("//a[@id='addProj']/cufon[2]/canvas");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=title", "Project to be retrived after logon");
		selenium.type("id=desc", "test");
		selenium.click("id=due");
		selenium.click("link=31");
		selenium.click("id=disp");
		selenium.click("//a[@id='linkSubmit']/cufon[3]/canvas");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=ui-accordion-accordion-header-2");
		selenium.click("css=span");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=email", "sponsor@nu.edu");
		selenium.type("id=password", "sponsor");
		selenium.click("id=login");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=ui-accordion-accordion-header-2");
		selenium.click("css=#ui-accordion-accordion-panel-2 > p > a");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=title", "Project to be retrived after logon=Passed");
		selenium.click("id=linkSubmit");
		selenium.waitForPageToLoad("30000");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
