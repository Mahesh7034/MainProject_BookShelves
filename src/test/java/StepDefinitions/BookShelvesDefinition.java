package StepDefinitions;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.urbanLadder.hooks.Hook;
import com.urbanLadder.pages.BookShelves;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BookShelvesDefinition {

	WebDriver driver;
	BookShelves bookshelvesPage;
	private static final Logger logger = LogManager.getLogger(BookShelvesDefinition.class);

	@Given("I open the Urban Ladder website")
	public void i_open_the_urban_ladder_website() {
		driver = Hook.driver;

		// Initialize the page object
		bookshelvesPage = new BookShelves(driver);
	}

	@Given("I navigate to the {string} section")
	public void i_navigate_to_the_section(String section) {
		bookshelvesPage.moveToLiving();
	}

	@When("I click on the {string} category")
	public void i_click_on_the_category(String category) {
		bookshelvesPage.clickBookShelves();
	}

	@When("I close the popup modal if it appears")
	public void i_close_the_popup_modal_if_it_appears() {
		try {
			bookshelvesPage.closePopup();
		} catch (Exception e) {
			System.out.println("Popup not displayed.");
		}
	}

	@When("I apply the {string} storage type filter")
	public void i_apply_the_storage_type_filter(String storageType) {
		bookshelvesPage.openType();
	}

	@When("I adjust the price range using the slider")
	public void i_adjust_the_price_range_using_the_slider() {
		bookshelvesPage.moveToPrice();
		bookshelvesPage.moveSlider();
	}

	@When("I select the {string} checkbox")
	public void i_select_the_checkbox(String checkbox) {
		bookshelvesPage.clickExcludeStock();
	}

	@Then("I should see the top {int} bookshelves displayed with their names and prices")
	public void i_should_see_the_top_bookshelves_displayed_with_their_names_and_prices(Integer int1) throws IOException {
		bookshelvesPage.displayNamesandPrices();
		logger.info("Displaying The top Three BookShelves With names and prices");
	}

}
