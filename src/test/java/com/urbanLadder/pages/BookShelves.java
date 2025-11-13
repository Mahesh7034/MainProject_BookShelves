package com.urbanLadder.pages;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.urbanLadder.utils.ExcelUtils;

public class BookShelves extends BasePage {

	public BookShelves(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//li[@class='topnav_item livingunit']//span[@class='topnav_itemname']")
	WebElement living;
	@FindBy(xpath = "//a[@href='/bookshelf?src=g_topnav_living_living-storage_bookshelves']")
	WebElement bookShelves;
	@FindBy(xpath = "//a[@class='close-reveal-modal hide-mobile']")
	WebElement close;
	@FindBy(xpath = "//li[@data-group='storage type']")
	WebElement StorageType;
	@FindBy(xpath = "//input[@id='filters_storage_type_Open']")
	WebElement open;
	@FindBy(xpath = "//li[@class='item' and @data-group='price']")
	WebElement priceBox;
	@FindBy(xpath = "//div[@class='noUi-handle noUi-handle-upper']")
	WebElement sliderHandle;
	@FindBy(xpath = "//input[@name='filters[availability][]'][@id='filters_availability_In_Stock_Only']")
	WebElement excludeStock;
	@FindBy(xpath = "(//div[@class='product-info-block']//div[contains(@class,'product-title')]/span)[position()<=3]")
	List<WebElement> books;
	@FindBy(xpath = "(//div[@class='product-info-block']//div[contains(@class,'price-number')]/span)[position()<=3]")
	List<WebElement> prices;
	
	
	String[] bookShelveNames;
	String[] bookShelvePrices;
	   

	public static void explicitWait(WebElement element, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void moveToLiving() {
		Actions a = new Actions(driver);
		a.moveToElement(living).perform();
	}

	public void clickBookShelves() {
		explicitWait(bookShelves, 10);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", bookShelves);

	}

	public void closePopup() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(close));
		close.click();

	}

	public void moveToPrice() {
		explicitWait(priceBox, 10);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", priceBox);
		Actions a = new Actions(driver);
		a.moveToElement(priceBox).perform();
	}

	public void moveSlider() {
		explicitWait(sliderHandle, 10);
		Actions a = new Actions(driver);
		a.dragAndDropBy(sliderHandle, -230, 0).perform();
	}

	public void openType() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", StorageType);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(StorageType)).click();
		Actions a = new Actions(driver);
		a.moveToElement(StorageType).perform();

		wait.until(ExpectedConditions.visibilityOf(open));
		wait.until(ExpectedConditions.elementToBeClickable(open));

		js.executeScript("arguments[0].click();", open);

	}

	public void clickExcludeStock() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".product-loader")));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].scrollIntoView(true);", excludeStock);

		wait.until(ExpectedConditions.visibilityOf(excludeStock));

		js.executeScript("arguments[0].click();", excludeStock);
	}

	public void displayNamesandPrices() throws IOException {
		 bookShelveNames = name();
		 bookShelvePrices = price();
		 
		 String file = ExcelUtils.excelFile;
		 String sheet = ExcelUtils.outputSheet;
		 
		 for(int i = 0 ; i < bookShelveNames.length ; i++) {
     		 ExcelUtils.setCellData(file, sheet, i+1, 0, bookShelveNames[i]);
     		 ExcelUtils.setCellData(file, sheet, i+1, 1, bookShelvePrices[i]);

     	 }
		
		
		
		System.out.print(books.size() + "-" + prices.size());

		for (int i = 0; i < 3; i++) {

			System.out.println(books.get(i).getText() + " - " + prices.get(i).getText());

		}

	}

	public String[] name() {
		int i = 0;
		String[] res = new String[3];
		for (WebElement w : books) {
			res[i] = w.getText();
			i++;
		}
		return res;
	}

	public String[] price() {
		int i = 0;
		String[] res = new String[3];
		for (WebElement p : prices) {
			res[i] = p.getText();
			i++;
		}
		return res;
	}
}
