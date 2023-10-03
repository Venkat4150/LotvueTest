package org.stepdefinition;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.pageObjects.ModuleUtilities;
import org.utility.BrowserUtilities;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LotVue extends BrowserUtilities {
	ModuleUtilities util;

	@Given("Enter url of the website {string}")
	public void enter_url_of_the_website(String url) {
		enterUrl(url);
		waitImplicitly(20);

	}

	@Then("I should see {string} page with all the details")
	public void i_should_see_page_with_all_the_details(String msg) {
		Assert.assertTrue("Verify Page url", getUrl().contains("abrazohomes"));
		util = new ModuleUtilities();
		Assert.assertTrue(util.verifyLoginPage(msg));

	}

	@When("I click on the Login button without entering the login credentials")
	public void i_click_on_the_login_button_without_entering_the_login_credentials() throws InterruptedException {
		util.loginWithoutCredentials();
		Thread.sleep(2000);
	}

	@Then("I should see user validation text messages")
	public void i_should_see_user_validation_text_messages() {
		Assert.assertTrue("Verify without credentials login", util.verifyIvalidLogin());
	}

	@When("I enter the uname {string} and password {string}")
	public void i_enter_the_uname_and_password(String user, String pass) {
		util.loginWithCredentials(user, pass);

	}

	@Then("based on the entered login details it should show Error Message or Username")
	public void based_on_the_entered_login_details_it_should_show_error_message_or_username() throws InterruptedException {
		Thread.sleep(3000);
		elementClick(util.userIcon);
		Thread.sleep(2000);
		Assert.assertTrue("Verify Userprofile page", getText(util.userprofilename).equals("Gouse"));
	}

	@When("After login click on the operations dropdown list")
	public void after_login_click_on_the_operations_dropdown_list() {
				util.clickOperationType();

	}

	@Then("I should be able to see the list of product type options")
	public void i_should_be_able_to_see_the_list_of_product_type_options() {
		util.getProductTypes();
	}

	@Then("Select the {string} product type from the list")
	public void select_the_product_type_from_the_list(String productType) {
		util.selectMarketingMenu(productType);
	}

	@When("I click on the manage settings icon")
	public void i_click_on_the_manage_settings_icon() throws InterruptedException {
		util.clickManageIcon();

	}

	@Then("I should able to see the list of settings options")
	public void i_should_able_to_see_the_list_of_settings_options() throws InterruptedException {
		util.getAllSettings();
	}

	@When("I selects {string} setting option from the list")
	public void i_selects_setting_option_from_the_list(String filterType) {
		util.selectWebsiteFiltermenu(filterType);
	}

	@Then("I should be able to see {string} page with all the information")
	public void i_should_be_able_to_see_page_with_all_the_information(String type) {
		Assert.assertTrue("Validate The filter detail", util.verifyFilter(type));
		util.getAllFilters();
	}

	@When("I click on the {string} accordion")
	public void i_click_on_the_accordion(String accordingType) {
		util.selectAccordingType(accordingType);
	}

	@Then("I should be able to see all the communities in {string}")
	public void i_should_be_able_to_see_all_the_communities_in(String string) throws InterruptedException {
		Thread.sleep(3000);
		util.getAllCommunityTypes();	}

	@Then("Click on the {string} specific Sales Statuses drop-downs, and from the statuses drop-down list, click on the {string} checkbox")
	public void click_on_the_specific_sales_statuses_drop_downs_and_from_the_statuses_drop_down_list_click_on_the_checkbox(
			String com, String st) {
		util.selectCommunityType(com, st);
	}

	@Then("Save the details")
	public void save_the_details() {
		util.clickSaveButton();
	}

}
