package StepDefinitions;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.urbanLadder.hooks.Hook;
import com.urbanLadder.pages.BeingAtHome;
import com.urbanLadder.pages.CheckOutPage;
import com.urbanLadder.pages.ConnectWithUs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckOutPageDefinition {
	WebDriver driver;
	CheckOutPage chk;
	private static final Logger logger = LogManager.getLogger(CheckOutPageDefinition.class);

	@Given("click on a specific Being At Home carpet item")
	public void click_on_a_specific_being_at_home_carpet_item() throws IOException {
		// Write code here that turns the phrase above into concrete actions
		driver = Hook.driver;

		BeingAtHome b = new BeingAtHome(driver);
		b.searchBeingatHome();
		b.clickSearch();
		chk = new CheckOutPage(driver);
		chk.clickProduct();
	}

	@When("I add the item to the cart")
	public void i_add_the_item_to_the_cart() {
		chk.moveToCart();
	}

	@When("I proceed to checkout")
	public void i_proceed_to_checkout() {
		chk.DoCheckOut();
	}

	@When("I enter an invalid email address")
	public void i_enter_an_invalid_email_address() throws IOException {
		chk.passEmail();
	}

	@Then("I should see an error message indicating invalid email")
	public void i_should_see_an_error_message_indicating_invalid_email() throws IOException {
		chk.DisplayErrorMsg();
		
		logger.info("Displaying Invalid Error Message");
		
		
		ConnectWithUs connect = new ConnectWithUs(driver);
		connect.ClickOnLogo();
	}

}
