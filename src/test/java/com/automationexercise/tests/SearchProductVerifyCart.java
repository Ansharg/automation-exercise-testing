package com.automationexercise.tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automationexercise.automationexercise.BaseClassTest;
import com.automationexercise.pages.CartPage;
import com.automationexercise.pages.Products;

public class SearchProductVerifyCart extends BaseClassTest {
	
	@Test
	public void searchProductAndVerify() throws InterruptedException {
		Products productsPage = landingPage.gotoProducts();
		productsPage.searchProduct("Blue Top");
		List<WebElement> products = getElementsByXPath("//div[@class='productinfo text-center']");
		List<String> check = new ArrayList<>();
		for(int i=0;i<products.size();i++) {			
			productsPage.addItem(products.get(i));
			check.add(products.get(i).findElement(By.xpath(".//p")).getText());
		}
		CartPage cartPage = productsPage.gotoCartPage();
		Thread.sleep(Duration.ofSeconds(5));
		List<WebElement> cartProducts = cartPage.getProducts();
		for(int i=0;i<cartProducts.size();i++) {
			String cartProduct = cartProducts.get(i).findElement(By.xpath(".//td[2]//a")).getText();
			Assert.assertEquals(cartProduct,check.get(i) );
		}
	}
}
