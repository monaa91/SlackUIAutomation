package com.Automation;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebElementDecorator implements WebElement{

	private WebElement element;
	private WebDriver driver;
	private By locator;
	private int timeOutInSeconds = 3;
	
	
	public WebElementDecorator(WebElement element, WebDriver driver, By locator) {
		this.element = element;
		this.driver = driver;
		this.locator = locator;
	}
	
	public WebElementDecorator(WebElement element, WebDriver driver, By locator, int timeOutInSeconds) {
		this.element = element;
		this.driver = driver;
		this.locator = locator;
		this.timeOutInSeconds = timeOutInSeconds;
	
	}
	@Override
	public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void click() {
		
		retry();
		
	}

	@Override
	public void submit() {
		retry();
		
	}

	@Override
	public void sendKeys(CharSequence... keysToSend) {
		retry();
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTagName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAttribute(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WebElement> findElements(By by) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebElement findElement(By by) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Point getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dimension getSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCssValue(String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//retry()
	
	private void retry() {
		
		try {
			System.out.println("text in try block"+element.getText());
			element.click();
			
			
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
		}
		
		element = waitForElement();
		element.click();
		System.out.println("after retry block "+element.getText());
	}
	
	//waitforElement()
	private WebElement waitForElement() {
		/**create the webdriverwait
		 * poll for the element
		 * ignore the element such as NoSuchElementException
		 * Once the element is located in the DOM return the element
		 */
		
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.withTimeout(Duration.ofSeconds(100)).pollingEvery(Duration.ofMillis(600));
		wait.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
		 
	}
	
	

}


