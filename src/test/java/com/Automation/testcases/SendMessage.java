package com.Automation.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.Keys;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.Automation.*;
import com.screens.MessagingPage;
import com.util.browser.BrowserActions;

@Test
public class SendMessage extends TestBase{
	long dateTime = System.currentTimeMillis();
	String messagetobeSaved = "Message to be saved" + dateTime;
	private MessagingPage messagingPage;
	
	    @Test(priority = -1)
		public void SendMessage() throws InterruptedException {		
	    	//Thread.sleep(5000);    
	    	retryingClick(By.cssSelector("span[data-qa='channel_sidebar_name_browser-automation']"));
            //messagingPage.goToBrowserAutomation();
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[aria-label='Message to browser-automation']")));
			WebElement sendMessage = driver.findElement(By.cssSelector("div[aria-label='Message to browser-automation']"));
			System.out.println(messagetobeSaved);
			sendMessage.sendKeys(messagetobeSaved);
			sendMessage.sendKeys(Keys.ENTER);
			Thread.sleep(5000);
			
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
			WebElement element = retryingFindClick(By.cssSelector(".c-message_kit__background.p-message_pane_message__message.c-message_kit__message.p-message_pane_message__message--last"));		
			Thread.sleep(2000);
			System.out.println("size is" + element.getText() );
			WebElement saveButton = element.findElement(By.cssSelector("button[data-qa='save_message']"));
			saveButton.click();
			Thread.sleep(20000);    
				
		
	}
	
	
	  @Test(priority = 0)
	  public void checkSavedMessage() throws InterruptedException {
	  
	  WebElement savedItem = driver.findElement(By.cssSelector(
	  "span[data-qa='channel_sidebar_name_page_psaved']")); 
	  savedItem.click();
	  Thread.sleep(5000); 
	  WebElement element1 =  driver.findElement(By.cssSelector(".p-workspace__primary_view_body"));
	  WebElement element = element1.findElement(By.cssSelector(
	  ".p-saved_page__item.c-message_kit__message.p-saved_item.p-saved_page__item--first"
	  )); 
	  List<WebElement> listOfSavedMessages =  driver.findElements(By.cssSelector(".p-rich_text_section"));
	  element1.findElement(By.cssSelector(".p-saved_page__item--first")); 
	  String firstItemSaved = listOfSavedMessages.get(0).getText();
	  System.out.println("firstItemSaved" + firstItemSaved);
	  Assert.assertEquals(firstItemSaved, messagetobeSaved); 
	  }
	  
	
	  @Test(priority = 1)
	  public void searchMessageInSlack() throws InterruptedException {
	  Thread.sleep(10000);
	  
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[data-qa='top_nav_search")));
	  driver.findElement(By.cssSelector("button[data-qa='top_nav_search")).click();
	  driver.findElement(By.cssSelector(".ql-editor.ql-blank")).sendKeys("has:star");
	  WebElement searchElement = driver.findElement(By.id("c-search_autcomplete__suggestion_0"));
	  //WebElementDecorator autosuggestion = new WebElementDecorator(searchElement, driver, By.id("c-search_autcomplete__suggestion_0"));
	  //autosuggestion.click();
	  retryingClick(By.id("c-search_autcomplete__suggestion_0"));
	  Thread.sleep(8000);
	  
	  List<WebElement> listofMessages =
	  driver.findElements(By.cssSelector("div[data-qa='message-text']")); 
	  String firstElement = listofMessages.get(0).getText();
	  System.out.println(firstElement);
	  
	  
	  
	  
	  }
	 
}
