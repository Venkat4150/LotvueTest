package org.stepdefinition;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.utility.BrowserUtilities;

import io.cucumber.java.*;

public class HooksClass extends BrowserUtilities {

	@Before
	public void setup() {
		launchChromeBrowser();
		maximizeBrowser();
	}

	@After
	public void tearDown(Scenario s) throws WebDriverException, IOException {
		ExtentReport_Utility.createTestLog(s);
		takeScreenShot(s.getName());
		TakesScreenshot tk=(TakesScreenshot)driver;
		s.attach(tk.getScreenshotAs(OutputType.BYTES),"image/png",s.getName());
		quit();

	}
}
