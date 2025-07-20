package StepDefinitions;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.urbanLadder.hooks.Hook;
import com.urbanLadder.pages.StudyChairs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StudyChairsDefinition {

	WebDriver driver;
	StudyChairs studyChairsPage;
	private static final Logger logger = LogManager.getLogger(StudyChairsDefinition.class);

	@Given("I scroll to the {string} section")
	public void i_scroll_to_the_section(String section) {
		driver = Hook.driver;

		studyChairsPage = new StudyChairs(driver);

		if (section.equalsIgnoreCase("Study")) {
			studyChairsPage.moveToStudy();
		} else {
			System.out.println("Unsupported section: " + section);
		}
	}

	@When("I move  on to the {string} category")
	public void i_move_on_to_the_category(String category) {
		if (category.equalsIgnoreCase("Study Chairs")) {
			studyChairsPage.clickStudyChair();
		} else {
			System.out.println("Unsupported category: " + category);
		}
	}

	@Then("I should see the top 3 study chairs displayed with their names and prices")
	public void i_should_see_the_top_3_study_chairs_displayed_with_their_names_and_prices() throws IOException {
		studyChairsPage.scrollToStudyChairs();
		studyChairsPage.DisplayChairsPrices();
		logger.info("Displaying The Top Three Study Chairs");

	}
}
