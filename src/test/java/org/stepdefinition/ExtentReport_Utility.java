package org.stepdefinition;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.cucumber.java.Scenario;
import io.cucumber.java.Status;

public class ExtentReport_Utility {
	static ExtentReports report;

	public static void startReport() {
		ExtentHtmlReporter html = new ExtentHtmlReporter(
				"target\\ExtentReports\\extent_report.html");
		html.config().setDocumentTitle("Extent Report");
		html.config().setReportName("LotVue");
		html.config().setTheme(Theme.DARK);

		report = new ExtentReports();
		report.attachReporter(html);
		report.setSystemInfo("Browser Name", "Chrome");
		report.setSystemInfo("OS", "Window 11");
		report.setSystemInfo("Tester", "Venkat");
	}

	public static void endReport() {
		report.flush();

	}

	public static void createTestLog(Scenario sc) {
		String name = sc.getName();
		Status status = sc.getStatus();
		switch (status) {
		case PASSED:
			report.createTest(name).pass("Test Case Passed");
			break;
		case FAILED:
			report.createTest(name).fail("Test Case Failed");
			break;

		default:
			report.createTest(name).skip("Test Case Skipped");
			break;
		}

	}

}
