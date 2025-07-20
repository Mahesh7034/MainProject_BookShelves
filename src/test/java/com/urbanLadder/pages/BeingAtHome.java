package com.urbanLadder.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.urbanLadder.utils.ConfigReader;
import com.urbanLadder.utils.ExcelUtils;

public class BeingAtHome extends BasePage {

	public BeingAtHome(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "search")
	WebElement beingAtHome;
	@FindBy(id = "search_button")
	WebElement searchButton;
	@FindBy(xpath = "//*[@class='item' and @data-group='category']")
	WebElement category;
	@FindBy(xpath = "//ul[@class='clearfix options']/li//label")
	List<WebElement> categories;
	
	

	public void searchBeingatHome() throws IOException {
		beingAtHome.sendKeys(ConfigReader.getProperty("search"));

	}

	public void clickSearch() {
		searchButton.click();
	}

	public void selectCategory() {
		Actions a = new Actions(driver);
		a.moveToElement(category).perform();
	}

	public String[] DisplaySubItems() throws IOException {
		int j = 0;
		List<String> li = new ArrayList<>();
		for (WebElement i : categories) {
			li.add(i.getText());
		}
		System.out.println("Being at home items ");
		for (String s : li) {
			System.out.println(s);
		}
		String[] res = new String[li.size()];
		for (String s : li) {
			res[j] = s;
			j++;
		}
		
		 
		 String file = ExcelUtils.excelFile;
		 String sheet = ExcelUtils.outputSheet;
		 
		 for(int i = 0 ; i < res.length ; i++) {
    		 ExcelUtils.setCellData(file, sheet, i+1, 4, res[i]);

    	 }
		
		return res;
	}

}
