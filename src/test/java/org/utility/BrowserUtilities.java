package org.utility;

import java.io.*;
import java.time.Duration;
import java.util.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class BrowserUtilities {
	public static WebDriver driver;

	public static void launchChromeBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	public static void enterUrl(String url) {
		driver.get(url);

	}

	public static String getUrl() {
		return driver.getCurrentUrl();

	}

	public static void waitImplicitly(long sec) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));

	}

	public static void maximizeBrowser() {
		driver.manage().window().maximize();

	}

	public static void enterValueToTextBox(WebElement e, String value) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(20));
		w.until(ExpectedConditions.visibilityOf(e));
		try {
			e.sendKeys(value);
		} catch (Exception a) {
			jsSendKeys(e, value);
			a.printStackTrace();
		}
	}

	public static void jsSendKeys(WebElement e, String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','" + value + ")", e);

	}

	public static String getText(WebElement e) {
		return e.getText();
	}

	public static void scrollDownPage(WebElement e) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", e);

	}

	public static void windowHandling(int index) {
		List<String> list = new LinkedList<String>(driver.getWindowHandles());
		driver.switchTo().window(list.get(index));

	}

	public static void elementClick(WebElement e) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(20));
		w.until(ExpectedConditions.elementToBeClickable(e));
		try {
			e.click();
		} catch (Exception a) {
			jsClick(e);
			a.printStackTrace();
		}

	}

	public static void jsClick(WebElement e) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", e);

	}

	public static void quit() {
		driver.quit();

	}

	public String getPropertyValue(String key) throws FileNotFoundException, IOException {

		Properties p = new Properties();
		p.load(new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\Config\\config.properties"));
		String value = p.get(key).toString();

		return value;
	}

	public static void takeScreenShot(String a) throws WebDriverException, IOException {
		FileUtils.copyFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE),
				new File(System.getProperty("user.dir") + "\\src\\test\\resources\\Screenshots\\" + a + ".png"));

	}
	public static void generateJVMReport(String j) {
		Configuration config = new Configuration(new File("target\\JVMReport"), "LotVue");
		config.addClassifications("Browser Name", "Chrome");
		config.addClassifications("OS", "Windows 11");
		List<String> jsonFiles = new LinkedList<String>(Arrays.asList(j));
		ReportBuilder r = new ReportBuilder(jsonFiles, config);
		r.generateReports();

	}
}
