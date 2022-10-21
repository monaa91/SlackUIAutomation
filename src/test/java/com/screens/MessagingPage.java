package com.screens;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.util.browser.BrowserActions;


public class MessagingPage {

	  private final BrowserActions browserActions;
	  private WebDriver webDriver;
	  long dateTime = System.currentTimeMillis();
	  String messagetobeSaved = "Message to be saved" + dateTime;
	  
	  @FindBy(css = "span[data-qa='channel_sidebar_name_browser-automation']")
	  private WebElement browserAutomation;
	  
	  @FindBy(css = "div[aria-label='Message to browser-automation']")
	  private WebElement sendMessage;
	  
	  @FindBy(css = ".c-message_kit__background.p-message_pane_message__message.c-message_kit__message.p-message_pane_message__message--last")
	  private WebElement lastMessage;

	  @FindBy(css = "button[data-qa='save_message']")
	  private WebElement saveButton;

	
	  public MessagingPage(WebDriver webDriver) {

		  PageFactory.initElements(webDriver, this);
		  browserActions = new BrowserActions(webDriver);
		  this.webDriver = webDriver;
	  }
	  
	  public void goToBrowserAutomation() {
		  //browserActions.waitForCondition("clickAble", browserAutomation);
		  browserActions.retryingClick(browserAutomation);
		  //browserActions.waitForCondition("clickAble, element);
		  
	  }
	  
	  public void sendMessageToSlack() {
		  browserActions.waitForCondition("clickAble", sendMessage);
		  browserActions.enterTextInTextField(sendMessage, messagetobeSaved);
		  browserActions.enterTextInTextField(sendMessage, Keys.ENTER);
		  WebElement lastMessageSaved = browserActions.moveTheCursorToElementAndClickAndReturnElement(lastMessage);
		  System.out.println("last Message saved is" + lastMessageSaved.getText());
		  WebElement saveButton1 =  lastMessageSaved.findElement(By.cssSelector("button[data-qa='save_message']"));
		  browserActions.moveTheCursorToElementAndClick(saveButton1);
		  
	  }
}
