package com.test;

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class P_5ArchiveProject {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://localhost:8080/ProjectBoard/");
		selenium.start();
	}

	@Test
	public void testP_5ArchiveProject() throws Exception {
		selenium.open("/ProjectBoard/");
		selenium.type("id=email", "sponsor@nu.edu");
		selenium.type("id=password", "sponsor");
		selenium.click("id=login");
		selenium.waitForPageToLoad("30000");
		selenium.click("css=div.col-4 > a.button-2 > cufon.cufon.cufon-canvas > canvas");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=| Archive project |");
		selenium.waitForPageToLoad("30000");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
