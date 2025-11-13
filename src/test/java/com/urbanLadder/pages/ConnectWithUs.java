package com.urbanLadder.pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConnectWithUs extends BasePage {

	public ConnectWithUs(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[@id=\"logo\"]/a/img")
	WebElement logo;
	@FindBy(xpath = "//div[@class='footer-item text-left with-stroke-inverted'][text()='Connect With Us:']")
	WebElement connect;
	@FindBy(xpath = "//li[@class='col-8']/a")
	List<WebElement> media;

	public void ClickOnLogo() {
		logo.click();
	}

	public void Connect() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", connect);
	}

	public void DisplayConnections() {
		System.out.println("Connect with us");
		for (WebElement j : media) {
			System.out.println(j.getDomAttribute("data-gaaction") + "-" + j.getDomAttribute("href"));
		}
	}

}