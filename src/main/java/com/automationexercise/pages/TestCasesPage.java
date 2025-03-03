package com.automationexercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class TestCasesPage {
	
	WebDriver driver;
	
	public TestCasesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void verifyPage() {
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.automationexercise.com/test_cases");
	}

}
