package com.automationexercise.tests;

import org.testng.annotations.Test;

import com.automationexercise.automationexercise.BaseClassTest;
import com.automationexercise.pages.ProductDetail;
import com.automationexercise.pages.Products;

public class AllProducts extends BaseClassTest{
	
	@Test
	public void allProducts() {
		Products productsPage = landingPage.gotoProducts();
		productsPage.verify();
		ProductDetail detailPage = productsPage.gotoFirstDetailPage();
		detailPage.verifyInfo();
	}
}
