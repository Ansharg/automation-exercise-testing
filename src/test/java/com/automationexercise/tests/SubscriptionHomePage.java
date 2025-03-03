package com.automationexercise.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automationexercise.automationexercise.BaseClassTest;

import components.Footer;

public class SubscriptionHomePage extends BaseClassTest {
	
	@Test(dataProvider="getSubscription")
	public void subscriptionVerify(String email) {
		Footer footer = landingPage.getFooter();
		footer.verifyText();
		footer.submitSubscription(email);
		footer.verifySubscription();
	}
	
	@DataProvider(name="getSubscription")
	public Object[][] dataProv(){
		return new Object[][] {{"ansh@gmail.com"},{"depesh@gmail.com"}};
	}
}
