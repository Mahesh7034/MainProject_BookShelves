package StepDefinitions;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.urbanLadder.hooks.Hook;
import com.urbanLadder.pages.BeingAtHome;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BeingAtHomeDefinition {
	WebDriver driver;
	BeingAtHome beingatHomePage;
	private static final Logger logger = LogManager.getLogger(BeingAtHomeDefinition.class);

	@Given("I search for {string}")
	public void i_search_for(String searchTerm) throws IOException {

		driver = Hook.driver;
		beingatHomePage = new BeingAtHome(driver);
		beingatHomePage.searchBeingatHome();
		beingatHomePage.clickSearch();
	}

	@When("the search results are displayed")
	public void the_search_results_are_displayed() {
		beingatHomePage.selectCategory(); // Hover over category
	}

	@Then("I should see a list of categories under Being At Home")
	public void i_should_see_a_list_of_categories_under_being_at_home() throws IOException {
		beingatHomePage.DisplaySubItems(); // Print categories to console
		logger.info("Retrieving all the sub-menu items of Being at Home");
		// driver.quit(); // Clean up
	}

}
