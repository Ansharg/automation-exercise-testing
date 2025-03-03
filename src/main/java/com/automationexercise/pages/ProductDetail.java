package com.automationexercise.pages;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.automationexercise.automationexercise.BaseClass;

public class ProductDetail extends BaseClass {
	
	WebDriver driver;
	
	public ProductDetail(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='product-information']//h2")
	WebElement productName;
	
	@FindBy(xpath="//div[@class='product-information']//p")
	WebElement productCategory;
	
	@FindBy(xpath="//div[@class='product-information']//span//span")
	WebElement productPrice;
	
	@FindBy(xpath="//div[@class='product-information']//p[2]")
	WebElement productAval;
	
	@FindBy(xpath="//div[@class='product-information']//p[3]")
	WebElement productCondition;
	
	@FindBy(xpath="//div[@class='product-information']//p[4]")
	WebElement productBrand;
	
	@FindBy(xpath="//input[@name='quantity']")
	WebElement quantity;
	
	@FindBy(xpath="//button[normalize-space()='Add to cart']")
	WebElement addToCart;
	
	@FindBy(xpath="//button[normalize-space()='Continue Shopping']")
	WebElement continueShopping;
	
	@FindBy(xpath="//a[normalize-space()='Cart']")
	WebElement cartPageBtn;
	
	@FindBy(xpath="//a[normalize-space()='Write Your Review']")
	WebElement reviewFormText;
	
	public void verifyInfo() {
		List<WebElement> list = Arrays.asList(new WebElement[] {productName,productCategory,productPrice,productAval,productCondition,productBrand});
		for(int i=0;i<list.size();i++) {			
			elementVisibleCheck(list.get(i));
		}
	}
	
	public void increaseToQuantity(String val) {
		quantity.clear();
		quantity.sendKeys(val);
	}
	
	public void toCart() {
		addToCart.click();
		continueShopping.click();
	}
	
	public CartPage gotoCartPage() {
		cartPageBtn.click();
		return new CartPage(driver);
	}
	
	public void checkWriteReview() {
		Assert.assertTrue(reviewFormText.isDisplayed(),"review form is not displayed!");
	}
	public void writeReview(HashMap<String, String> data) {
		driver.findElement(By.xpath("//form//input[@id='name']")).sendKeys(data.get("name"));
		driver.findElement(By.xpath("//form//input[@id='email']")).sendKeys(data.get("email"));
		driver.findElement(By.xpath("//form//textarea")).sendKeys(data.get("review"));
		driver.findElement(By.xpath("//button[@id='button-review']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[normalize-space()='Thank you for your review.']")).isDisplayed());
	}
	
}
