package com.urbanLadder.hooks;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.urbanLadder.utils.DriverSetup;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
 
public class Hook {
	public static WebDriver driver;
    static DriverSetup setup;
    public static String browser;
    public Logger logger;
 
    @Before
    public void setUp(Scenario scenario) throws URISyntaxException {
    	logger = LogManager.getLogger(this.getClass());
    	browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
        setup = new DriverSetup();
        try {
			driver = setup.driverInstantiate(browser);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Reads URL from Excel
 
        // Retry only for scenarios tagged with @Retry
        if (scenario.getSourceTagNames().contains("@Retry")) {
            retryScenario(() -> {
                // Any setup logic here if needed
            }, 2); // Retries before scenario starts
        }
    }
 
    @After
    public void tearDown(Scenario scenario) {
    	if(scenario.isFailed())
    	{
    		byte[] screenshot=((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    		Allure.addAttachment("Screenshot on Failure", new ByteArrayInputStream(screenshot));
    	}
    	if(driver!=null)
    	{
            setup.driverTearDown(); // Quits the browser
    	}
    }
// Retry logic
    public static void retryScenario(Runnable runnable, int maxRetries) {
        int attempt = 0;
        while (attempt <= maxRetries) {
            try {
                runnable.run();
                return;
            } catch (Exception e) {
                attempt++;
                System.out.println("🔁 Scenario retry #" + attempt + ": " + e.getMessage());
                if (attempt > maxRetries) {
                    throw new RuntimeException("❌ Scenario setup failed after " + maxRetries + " retries", e);
                }
            }
        }
    }
    
}