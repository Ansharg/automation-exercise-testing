package com.automationexercise.tests;

import org.testng.annotations.Test;

import com.automationexercise.automationexercise.BaseClassTest;
import com.automationexercise.pages.Products;

public class SearchProducts extends BaseClassTest{
	
	@Test
	public void searchProduct(){
		Products productsPage = landingPage.gotoProducts();
		productsPage.searchProduct("Blue Top");
	}

}
