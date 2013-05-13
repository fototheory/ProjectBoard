package com.test;

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class SP_4LoginCondition1 {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://localhost:8080/ProjectBoard/");
		selenium.start();
	}

	@Test
	public void testSP_4LoginCondition1() throws Exception {
		selenium.open("/ProjectBoard/");
		selenium.type("id=email", "sponsor@nu.edu");
		selenium.type("id=password", "sponsor");
		selenium.click("id=login");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=company", "USGS");
		selenium.type("id=phone", "6192225555");
		selenium.type("id=skills", "Many");
		selenium.click("css=input.button-2");
		selenium.waitForPageToLoad("30000");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
