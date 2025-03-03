package com.automationexercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CategoryPage {

	WebDriver driver;
	
	public CategoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void verify() {
		Assert.assertTrue(driver.getCurrentUrl().contains("/category_products/"),"category page is not visible");
	}
}
