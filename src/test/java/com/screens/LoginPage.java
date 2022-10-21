package com.screens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.Automation.DataContext;

import com.Automation.TestBase;
import com.util.browser.BrowserActions;

public class LoginPage {

	private final BrowserActions browserActions;
	  private WebDriver webDriver;
		String domName = "automationtea-xwz3649";
		String email = "monikaassudani91@gmail.com";
		String passwd = "Lovemyself@148";
	/*
	 * This constructor is used to initialize the page web elements
	 */
	  
	  @FindBy(css = "input[data-qa='signin_domain_input']")
	  private WebElement domainName;
	  
	  @FindBy(css = "button[data-qa='submit_team_domain_button']")
	  private WebElement submit;
	  
	  @FindBy(xpath = "//*[@id='email']")
	  private WebElement emailAddress;

	  @FindBy(xpath = "//*[@id='password']")
	  private WebElement password;

	  @FindBy(css = "button[data-qa='signin_button']")
	  private WebElement signIn;
	  
	public LoginPage(WebDriver webDriver) {
		
		PageFactory.initElements(webDriver, this);
	    browserActions = new BrowserActions(webDriver);
	    this.webDriver = webDriver;
	}
	
	public void gotoLogInPage() {
	    webDriver.navigate().to("https://app.slack.com/client/T046PD37L8H/C046APSUVBL");
	    webDriver.manage().window().maximize();
	  }
	
	public void domainLogin() {
		browserActions.enterTextInTextField(domainName, "automationtea-xwz3649");
		browserActions.moveTheCursorToElementAndClick(submit);
		
	}
	
	 public void logInHomePage() {
		    System.out.println(DataContext.getEmailAddress());
		    //System.out.println(AccountContext.getPassword());
		    browserActions.enterTextInTextField(emailAddress,DataContext.getEmailAddress());
		    browserActions.enterTextInTextField(password, DataContext.getPassword());
		    browserActions.moveTheCursorToElementAndClick(signIn);
		  }
		}


