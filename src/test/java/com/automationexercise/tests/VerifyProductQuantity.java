package com.automationexercise.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automationexercise.automationexercise.BaseClassTest;
import com.automationexercise.pages.CartPage;
import com.automationexercise.pages.ProductDetail;
import com.automationexercise.pages.Products;

public class VerifyProductQuantity extends BaseClassTest {
	
	@Test
	public void verifyQuantity() throws InterruptedException {
		Products productsPage = landingPage.gotoProducts();
		ProductDetail productDetail = productsPage.gotoFirstDetailPage();
		productDetail.increaseToQuantity("4");
		productDetail.toCart();
		CartPage cartPage = productDetail.gotoCartPage();
		List<WebElement> products = cartPage.getProducts();
		Assert.assertEquals(products.get(0).findElement(By.xpath(".//td[4]//button")).getText(), "4");
	}
}
