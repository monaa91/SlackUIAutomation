package com.Automation.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.Keys;import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.Automation.*;
import com.screens.LoginPage;
import com.screens.MessagingPage;
import com.util.browser.BrowserActions;

@Test
public class SendMessage extends TestBase{
	long dateTime = System.currentTimeMillis();
	String messagetobeSaved = "Message to be saved" + dateTime;
	private MessagingPage messagingPage;
	
	
	    @Test(priority = -1)
		public void SendMessage() throws InterruptedException {	
	    	messagingPage = new MessagingPage(driver);
	    	//Thread.sleep(10000);    
	    	messagingPage.goToBrowserAutomation();
			messagingPage.sendMessageToSlack();
				
		
	}
	
	
	  @Test(priority = 0)
	  public void checkSavedMessage() throws InterruptedException {
	  messagingPage = new MessagingPage(driver);
	  messagingPage.clickSavedItems();
	  messagingPage.fetchFirstElement();
	  }
	  
	
	  @Test(priority = 1)
	  public void searchMessageInSlack() throws InterruptedException {
		  messagingPage = new MessagingPage(driver);
		  messagingPage.searchMessageInSlack();	  
	  
	  }
	 
}
