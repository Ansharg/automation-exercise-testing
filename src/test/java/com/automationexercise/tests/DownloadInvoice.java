package com.automationexercise.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automationexercise.automationexercise.BaseClassTest;
import com.automationexercise.pages.CartPage;
import com.automationexercise.pages.CheckoutPage;
import com.automationexercise.pages.LoginRegister;
import com.automationexercise.pages.PaymentPage;
import com.automationexercise.pages.Products;
import com.automationexercise.pages.SignUpPage;

public class DownloadInvoice extends BaseClassTest {

	@Test(dataProvider="testData")
	public void downloadInvoice(HashMap<String,String> info) throws InterruptedException {
		LoginRegister loginRegister = landingPage.gotoLoginRegister();
		SignUpPage signup = loginRegister.signup(info.get("name"), info.get("email"));
		if(getElementsByXPath("//p[normalize-space()='Email Address already exist!']").size()==1) {
			loginRegister.login(info.get("email"),info.get("password"));
		}
		else {			
			signup.fillInfo(info);
		}
		Products productsPage = landingPage.gotoProducts();
		List<WebElement> products = getElementsByXPath("//div[@class='productinfo text-center']");
		List<String> check = new ArrayList<>();
		for(int i=0;i<2;i++) {			
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
		CheckoutPage checkoutPage = cartPage.checkout();
		checkoutPage.verifyAddresses();
		PaymentPage payment = checkoutPage.proceedToCheckout();
		payment.fillCardDetails();
		Assert.assertEquals(driver.findElement(By.xpath("//b[text()='Order Placed!']")).getText(),"ORDER PLACED!");
		payment.downloadInvoice();
		//checking invoice download.
		Thread.sleep(Duration.ofSeconds(10));
		Assert.assertTrue(isFileDownloaded("C:\\Users\\AnshGarg\\Downloads","invoice"));
		//download account.
		driver.findElement(By.xpath("//a[@href='/delete_account']")).click();
	}
	
	@DataProvider(name = "testData")
	public static Object[][] provider() throws IOException {
		List<HashMap<String,String>> data = dataFetching("\\src\\test\\java\\com\\automationexercise\\resources\\registerData.json");
		
		Object[][] finalData = new Object[data.size()][data.get(0).size()];
		for(int i=0;i<data.size();i++) {
			finalData[i] = new Object[] {data.get(i)};
		}
		return finalData;
	}
}
