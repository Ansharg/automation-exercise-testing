package com.automationexercise.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CheckoutPage {

	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[normalize-space()='Place Order']")
	WebElement checkoutBtn;
	
	@FindBy(xpath="//ul[@id='address_delivery']//li")
	List<WebElement> deliveryAddress;
	
	@FindBy(xpath="//ul[@id='address_invoice']//li")
	List<WebElement> billingAddress;
	
	public void verifyAddresses() {
		for(int i=1;i<deliveryAddress.size();i++) {
			Assert.assertEquals(deliveryAddress.get(i).getText(),billingAddress.get(i).getText(), "Delivery Address and Billing Address are not equal!");
		}
	}
	
	public PaymentPage proceedToCheckout() {
		checkoutBtn.click();
		return new PaymentPage(driver);
	}
}
