package com.test;

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class SP_2LoginFailure {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://localhost:8080/ProjectBoard/");
		selenium.start();
	}

	@Test
	public void testSP_2LoginFailure() throws Exception {
		selenium.open("/ProjectBoard/");
		selenium.click("id=email");
		selenium.type("id=email", "sponsor@nu.edu");
		selenium.type("id=password", "sponsorrrrr");
		selenium.click("id=login");
		selenium.waitForPageToLoad("30000");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
