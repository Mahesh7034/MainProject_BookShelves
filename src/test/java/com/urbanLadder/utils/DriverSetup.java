package com.urbanLadder.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


 
public class DriverSetup {
	private static String fileName= "DataSet.xlsx";
	String sheet= "Sheet1";
	private static final ExcelUtils exe = new ExcelUtils(fileName);
	@SuppressWarnings("unused")
	private static final Logger logger=LogManager.getLogger(DriverSetup.class);	

    private static WebDriver driver;
    public DriverSetup()
    {
    	
    }
 
    @SuppressWarnings({ "unused", "deprecation" })
	public WebDriver driverInstantiate(String browser) throws URISyntaxException, IOException {
        String executionEnv = ConfigReader.getProperty("environment").toLowerCase();
        String browserType = ConfigReader.getProperty("browserType").toLowerCase();
        String os = ConfigReader.getProperty("os").toLowerCase();
 
        if (executionEnv.equals("remote")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
 
            switch (os) {
                case "windows":
                    capabilities.setPlatform(Platform.WINDOWS);
                    break;
                case "mac":
                    capabilities.setPlatform(Platform.MAC);
                    break;
                case "linux":
                    capabilities.setPlatform(Platform.LINUX);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported OS: " + os);
            }
 
            switch (browser) {
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;
                case "edge":
                    capabilities.setBrowserName("MicrosoftEdge");
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
 
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
 
        }else if (executionEnv.equals("local")) {
            if (browser.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("edge")) {
              driver = new EdgeDriver();
          } else {
              throw new IllegalArgumentException("Unsupported browser: " + browser);
           }
 
            
        }
        driver.get("https://www.urbanladder.com");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        
 
        return driver;
    }
 
    public static WebDriver getDriver() {
        return driver;
    }

	public void driverTearDown() {
		// TODO Auto-generated method stub
		driver.quit();
		
	}
 

}
 
