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

public class ConnectWithUsDefinition {
	WebDriver driver;
	ConnectWithUs cnct;
	BeingAtHome beingAtHomeObj;
	CheckOutPage connectWithUsObj;
	// CheckOutPage c;
	private static final Logger logger = LogManager.getLogger(ConnectWithUsDefinition.class);

	@Given("Click on the UrbanLadder logo")
	public void click_on_the_urban_ladder_logo() throws IOException {
		driver = Hook.driver;
		cnct = new ConnectWithUs(driver);

	}

	@Then("Scroll to the {string} section")
	public void scroll_to_the_section(String string) {
		cnct.Connect();

	}

	@Then("Display list of social media platforms with their URLs")
	public void display_list_of_social_media_platforms_with_their_ur_ls() {
		// Write code here that turns the phrase above into concrete actions
		cnct.DisplayConnections();
		logger.info("Displaying The list of social media Platforms");

	}

}
