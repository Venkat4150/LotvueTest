package org.pageObjects;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utility.BrowserUtilities;

public class ModuleUtilities extends BrowserUtilities {
	public ModuleUtilities() {
		PageFactory.initElements(driver, this);
	}

	// loginpage
	@FindBy(id = "tbluser_email")
	WebElement email;

	@FindBy(xpath = "//form[@id='sign_in_check']/h2")
	WebElement loginpage;

	@FindBy(id = "tbluser_password")
	private WebElement password;

	@FindBy(name = "commit")
	private WebElement loginButton;

	@FindBy(xpath = "//span[contains(@for,'tbluser')]")
	private List<WebElement> msg;

	@FindBy(xpath = "//li[@id='admin']")
	public WebElement userIcon;

	@FindBy(xpath = "//li[@id='admin']//span[@class='Pname']")
	public WebElement userprofilename;

	// Marketing page
	@FindBy(xpath = "//li[@class='dropdown']/a[text()='Operations']")
	private WebElement operationmenu;

	@FindBy(xpath = "//a[text()='Marketing']")
	private WebElement marketingMenu;

	@FindBy(xpath = "//ul[@class='sub-menu dropdown-menu pull-right']//a")
	private List<WebElement> productTypes;

	@FindBy(xpath = "//li[@class='dropdown settingList']")
	private WebElement settingmenu;

	@FindBy(xpath = "//div[@class='names_align']")
	private WebElement filter;

	@FindBy(xpath = "//ul[@id='scrollbox3']//a")
	private List<WebElement> settings;

	@FindBy(xpath = "//td/div/h4")
	private List<WebElement> filterOptions;

	@FindBy(xpath = "//div[@id='collapse_body_inventory_lots_collapse']/div/div/label")
	private List<WebElement> communityList;

	@FindBy(xpath = "//button[text()='Save']")
	private WebElement saveButton;

	public boolean verifyLoginPage(String msg) {
		String m = getText(loginpage);
		System.out.println(m);
		return m.contains(msg);

	}

	public void loginWithoutCredentials() {
		elementClick(loginButton);
	}

	public void loginWithCredentials(String user, String pass) {
		enterValueToTextBox(email, user);
		enterValueToTextBox(password, pass);
		elementClick(loginButton);
	}

	public boolean verifyLoggedUserName(String username) {
		elementClick(userIcon);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getText(userprofilename).equals(username);

	}

	public boolean verifyIvalidLogin() {
		List<String> msg1 = new LinkedList<String>(Arrays.asList("Enter Your Email", "Enter Your Password"));
		List<WebElement> list = msg;
		List<String> msg2 = new LinkedList<String>();
		for (int i = 0; i < list.size(); i++) {
			msg2.add(getText(list.get(i)));

		}
		System.out.println(msg2);

		return msg1.equals(msg2);

	}

	public void clickOperationType() {
		elementClick(operationmenu);

	}

	public void getProductTypes() {
		List<WebElement> types = productTypes;
		System.out.println("*****Total No. of Products****" + types.size());
		System.out.println("*****Products****");
		for (WebElement x : types) {
			System.out.println(getText(x));
		}

	}

	public void selectMarketingMenu(String productType) {
		elementClick(driver.findElement(By.xpath("//a[text()='" + productType + "']")));

	}

	public void clickManageIcon() throws InterruptedException {
		Thread.sleep(2000);
		elementClick(settingmenu);

	}

	public void getAllSettings() throws InterruptedException {
		Thread.sleep(2000);
		List<WebElement> setting = settings;
		System.out.println("*****Total Number of Options in Settings*******" + setting.size());
		System.out.println("*****Options in Settings******");
		for (WebElement x : setting) {
			System.out.println(getText(x));
		}

	}

	public void selectWebsiteFiltermenu(String filter) {
		elementClick(driver.findElement(By.xpath("//a[text()='" + filter + "']")));

	}

	public boolean verifyFilter(String m) {
		return getText(filter).equals(m);
	}

	public void getAllFilters() {
		System.out.println("All Filter options");
		for (WebElement x : filterOptions) {
			System.out.println(getText(x));
		}
	}

	public void selectAccordingType(String accordingType) {
		WebElement acc = driver
				.findElement(By.xpath("// span[normalize-space(text()) = '" + accordingType + "']//ancestor::a"));
		scrollDownPage(acc);
		elementClick(acc);
	}

	public void getAllCommunityTypes() {
		List<WebElement> com = communityList;
		System.out.println("********All Communities********");
		System.out.println(com.size());
		for (WebElement x : com) {
			System.out.println(getText(x));
		}

	}

	public void selectCommunityType(String cm, String st) {
		elementClick(driver.findElement(By.xpath("//select[@name='listing_status[" + cm + "][]']//parent::span")));

		WebElement cb = driver.findElement(By.xpath("//select[@name='listing_status[" + cm
				+ "][]']//parent::span//following-sibling::li//input[@value='" + st + "']"));
		if (cb.isSelected()) {
			System.out.println("The status Sold Already updated");
		} else {
			elementClick(cb);
		}

	}

	public void clickSaveButton() {
		scrollDownPage(saveButton);
		elementClick(saveButton);

	}

}
