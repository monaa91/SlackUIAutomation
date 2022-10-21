package com.screens;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import com.Automation.DataContext;
import com.Automation.TestBase;
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
	  
	  @FindBy(css = ".p-message_pane_message__message--last")
	  private WebElement lastMessage;

	  @FindBy(xpath = "//i[@class='c-icon c-icon--bookmark' and (@aria-hidden='true')]")
	  private WebElement saveButton;
	  
	  @FindBy(css = "span[data-qa='channel_sidebar_name_page_psaved']")
	  private WebElement savedItem;
	  
	  @FindBy(css = ".p-rich_text_section")
	  private List<WebElement> lisofSavedMessages;
	  
	  @FindBy(css = ".p-workspace__primary_view_body")
	  private WebElement fetchFirstElement;
	  
	  @FindBy(css = "button[data-qa='top_nav_search")
	  private WebElement searchBox;
	  
	  @FindBy(css = ".ql-editor.ql-blank")
	  private WebElement searchItem;

	  @FindBy(id = "c-search_autcomplete__suggestion_0")
	  private WebElement autoSuggestion;
	  
	  @FindBy(css = "div[data-qa='message-text']")
	  private List<WebElement> searchList;
	
	  public MessagingPage(WebDriver webDriver) {

		  PageFactory.initElements(webDriver, this);
		  browserActions = new BrowserActions(webDriver);
		  this.webDriver = webDriver;
	  }
	  
	  public void goToBrowserAutomation() throws InterruptedException {
		  //browserActions.waitForCondition("clickAble", browserAutomation);
		  Thread.sleep(10000);
		  browserActions.moveTheCursorToElementAndClick(browserAutomation);
		  //browserActions.waitForCondition("clickAble, element);
		  
	  }
	  
	  public void sendMessageToSlack() {
		  browserActions.waitForCondition("clickAble", sendMessage);
		  browserActions.enterTextInTextField(sendMessage, DataContext.getMessageToBeSaved());
		  browserActions.enterTextInTextField(sendMessage, Keys.ENTER);
		  WebElement lastMessageSaved = browserActions.retryingFindClick(lastMessage);
		  System.out.println("last Message saved is" + lastMessageSaved.getText());
		  WebElement saveButton1 =  lastMessageSaved.findElement(By.cssSelector("button[data-qa='save_message']"));
		  browserActions.moveTheCursorToElementAndClick(saveButton1);
		  
	  }
	  
	  public void clickSavedItems() {
		  
		  browserActions.moveTheCursorToElementAndClick(savedItem);
		  
	  }
	  
	  public void fetchFirstElement() {
		  
		  String firstElementSaved = lisofSavedMessages.get(0).getText();
		  System.out.println("firstElementSaved"+ firstElementSaved);
		  Assert.assertEquals(firstElementSaved, DataContext.getMessageToBeSaved()); 
	  }
	  
	  public void searchMessageInSlack() throws InterruptedException {
		  Wait<WebDriver> wait = browserActions.getWebDriverWait(120000);
		  //browserActions.getWebDriverWait(60000);
		  Thread.sleep(15000);
		  browserActions.moveTheCursorToElementAndClick(searchBox);
		  browserActions.enterTextInTextField(searchItem, "has:star");
		  //browserActions.enterTextInTextField(searchItem, Keys.ENTER);
		  //Thread.sleep(5000);
		  browserActions.moveTheCursorToElementAndClick(autoSuggestion);
		  Thread.sleep(5000);
		  String searchText = searchList.get(0).getText();
		  System.out.println("seacrhText is" + searchText);
		  Assert.assertEquals(searchText, DataContext.getMessageToBeSaved());
		  
	  }

}
