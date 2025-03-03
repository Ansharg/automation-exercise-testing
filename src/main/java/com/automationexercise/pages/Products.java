package com.automationexercise.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.automationexercise.automationexercise.BaseClass;

public class Products extends BaseClass {
	
	WebDriver driver;
	
	public Products(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='product-image-wrapper']")
	List<WebElement> products;
	
	@FindBy(xpath="//a[contains(@href,'/product_details/')]")
	List<WebElement> viewProductBtn;
	
	@FindBy(xpath="//input[@id='search_product']")
	WebElement searchInput;
	
	@FindBy(xpath="//button[@id='submit_search']")
	WebElement searchBtn;
	
	@FindBy(xpath="//div[@class='productinfo text-center'][1]")
	WebElement addProductBtn;
	
	@FindBy(xpath="//button[normalize-space()='Continue Shopping']")
	WebElement continueShoppingBtn;
	
	@FindBy(xpath="//a[normalize-space()='Cart']")
	WebElement cartPageBtn;
	
	@FindBy(xpath="//h2[normalize-space()='Brands']")
	WebElement brandText;
	
	@FindBy(xpath="//div[@class='brands-name']//a")
	List<WebElement> brands;
	
	public void verify() {
		String path = driver.getCurrentUrl();
		Assert.assertEquals(path, "https://www.automationexercise.com/products");
	}
	
	public ProductDetail gotoFirstDetailPage() {
		viewProductBtn.get(0).click();
		return new ProductDetail(driver);
	}
	
	public void addItem(WebElement product) throws InterruptedException {
		Actions ac = new Actions(driver);
		ac.moveToElement(product).pause(Duration.ofSeconds(10)).perform();
		ac.click(product.findElement(By.xpath(".//following-sibling::div[@class='product-overlay']//a"))).pause(Duration.ofSeconds(5)).perform();
		ac.click(continueShoppingBtn).click().perform();
	}
	
	public void searchProduct(String name) {
		searchInput.sendKeys(name);
		searchBtn.click();
		List<WebElement> result = getElements("//div[@class='productinfo text-center']//p");
		try {			
			for(int i=0;i<result.size();i++) {
				if(result.get(i).getText().contains(name)) {
					continue;
				}
				else {
					throw new Exception("item is not compatible with the search");
				}
			}
		}
		catch(Exception e) {
			Assert.fail("not pass", e);
		}
	}
	
	public CartPage gotoCartPage() {
		cartPageBtn.click();
		return new CartPage(driver);
	}
	
	public boolean verifyBrandTextVisible() {
		return brandText.isDisplayed();
	}
	
	public List<WebElement> getBrandsList() {
		Assert.assertTrue(verifyBrandTextVisible());
		return brands;
	}
	public void selectBrand(List<WebElement> brandsList, String brand) {
		for(int i=0;i<brandsList.size();i++) {
			if(brandsList.get(i).getText().contains(brand)) {
				brandsList.get(i).click();
			}
		}
		verifyBrandSelected();
		String selectedBrand = getSelectedBrand();
		Assert.assertTrue(selectedBrand.equalsIgnoreCase(brand),"selected brand not correct!");
	}
	public void verifyBrandSelected() {
		Assert.assertTrue(driver.getCurrentUrl().contains("https://www.automationexercise.com/brand_products/"));
	}
	public String getSelectedBrand() {
		return driver.getCurrentUrl().split("/")[4];
	}
}
