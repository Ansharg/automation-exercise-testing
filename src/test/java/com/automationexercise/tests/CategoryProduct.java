package com.automationexercise.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.automationexercise.automationexercise.BaseClassTest;
import com.automationexercise.pages.CategoryPage;

public class CategoryProduct extends BaseClassTest {
	
	@Test
	public void categoryProduct() throws InterruptedException {
		landingPage.categoryVisible();
		List<WebElement> subCategories = landingPage.selectCategory("WOMEN");
		CategoryPage categoryPage = landingPage.selectSubCategories(subCategories,"DRESS");
		categoryPage.verify();
	}
}
