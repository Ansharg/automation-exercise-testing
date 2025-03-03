package com.automationexercise.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automationexercise.automationexercise.BaseClassTest;

import components.Footer;

public class ArrowBtnCheck extends BaseClassTest {

	@Test
	public void arrowBtnCheck() throws InterruptedException {
		Footer footerElement = landingPage.getFooter();
		footerElement.gotoSubscription();
		footerElement.verifyText();
		driver.findElement(By.xpath("//a[@id='scrollUp']")).click();
		boolean displayed = elementVisible("//a[@href='/']");
		Assert.assertTrue(displayed, "logo not displayed!");
	}
}
