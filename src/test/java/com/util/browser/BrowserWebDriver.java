package com.util.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserWebDriver {

  public WebDriver getWebDriver() {

    System.setProperty(
        "webdriver.chrome.driver", "./src/test/resources/browserdrivers/chromedriver.exe");
    ChromeOptions options = new ChromeOptions();
    //options.addArguments("--headless");
    return new ChromeDriver(options);
  }
}