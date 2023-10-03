package org.runner;

import org.junit.*;
import org.junit.runner.RunWith;
import org.stepdefinition.ExtentReport_Utility;
import org.utility.BrowserUtilities;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", glue = "org.stepdefinition", stepNotifications = true, dryRun = false, monochrome = true, plugin = {
		"html:target\\HTML\\htmlreport.html", "json:target\\JSON\\jsonreport.json" })
public class TestRunner extends BrowserUtilities {
	@BeforeClass
	public static void before() {
		 ExtentReport_Utility.startReport();
	}
	@AfterClass
	public static void report() {
		ExtentReport_Utility.endReport();
	}

}
