package com.automationexercise.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import components.Footer;

public class CartPage {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[normalize-space()='Proceed To Checkout']")
	WebElement checkoutBtn;
	
	public Footer getFooter() {
		return new Footer(driver);
	}
	
	public List<WebElement> getProducts() {
		return driver.findElements(By.xpath("//table//tr[contains(@id,'product')]"));
	}
	
	public CheckoutPage checkout() {
		checkoutBtn.click();
		return new CheckoutPage(driver);
	}
	public LoginRegister register() {		
		driver.findElement(By.xpath("//u[normalize-space()='Register / Login']")).click();
		return new LoginRegister(driver);
	}

}
