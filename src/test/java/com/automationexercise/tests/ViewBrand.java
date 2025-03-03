package com.automationexercise.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.automationexercise.automationexercise.BaseClassTest;
import com.automationexercise.pages.Products;

public class ViewBrand extends BaseClassTest {
	
	@Test
	public void viewBrand() {
		Products productsPage = landingPage.gotoProducts();
		List<WebElement> brands = productsPage.getBrandsList();
		productsPage.selectBrand(brands,"POLO");
	}
}
