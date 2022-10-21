package com.Automation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.screens.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	public static WebDriver driver;
	public static FileInputStream fis;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static Logger log = Logger.getLogger(TestBase.class);
	public static WebDriverWait wait;
	private LoginPage loginPage;
	
	

	@BeforeSuite
	public void setUp() throws InterruptedException {
	
		if(driver==null) {
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\\\src\\\\test\\\\resources\\\\properties\\\\Config.properties");
			}
			catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			
			try {
				config.load(fis);
				log.debug("config file is loaded");
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\\\\\\\src\\\\\\\\test\\\\\\\\resources\\\\\\\\properties\\\\\\\\OR.properties");
			}
			catch(FileNotFoundException e) {
				e.printStackTrace();
				
			}
			
			try {
				OR.load(fis);
				log.debug("OR property file is loaded");
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		if(config.getProperty("browser").equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (config.getProperty("browser").equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (config.getProperty("browser").equals("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
			
		loginPage = new LoginPage(driver);
		loginPage.gotoLogInPage();
		loginPage.domainLogin();
	    loginPage.logInHomePage();
	    driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 20);
		
	}
	
	public WebElement retryingFindClick(By by) {
	    WebElement result = null;
	    int attempts = 0;
	    while(attempts < 3) {
	        try {
	            driver.findElement(by).click();
	            result = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	            break;
	        } catch(StaleElementReferenceException e) {
	        }
	        attempts++;
	    }
	    return result;
	}
	
	public boolean retryingClick(By by) {
		boolean result = false;
	    int attempts = 0;
	    while(attempts < 3) {
	        try {
	            driver.findElement(by).click();
	            result = true;
	            break;
	        } catch(StaleElementReferenceException e) {
	        }
	        attempts++;
	    }
	    return result;
	}
	public void click(String locator) {
		
		if(locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		}
		else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		}
	}
	
	
	public void type(String locator, String value) {
		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}
	}
	
	@AfterTest
	public void tearDown() {
		
		if(driver!=null) {
			driver.quit();
		}
	}
}
