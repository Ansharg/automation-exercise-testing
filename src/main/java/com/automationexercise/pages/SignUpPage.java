package com.automationexercise.pages;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SignUpPage {
	
	public WebDriver driver;
	
	public SignUpPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//form//label//following::input")
	List<WebElement> inputs;
	@FindBy(xpath = "//button[@data-qa='create-account']")
	WebElement submitBtn;
	
	@FindBy(xpath = "//b")
	WebElement accountCreatedMessage;
	
	@FindBy(xpath = "//a[normalize-space()='Continue']")
	WebElement continueBtn;	
	
	public LandingPage fillInfo(HashMap<String,String> info) {
		for(int i=0;i<inputs.size();i++) {
			String fill = info.get(inputs.get(i).getDomAttribute("name"));
			if(fill !=null && inputs.get(i).isEnabled()) {
				inputs.get(i).clear();
				inputs.get(i).sendKeys(fill);
			}
			else {
				continue;
			}
		}
		submitBtn.click();
		Assert.assertTrue(accountCreatedMessage.isDisplayed(),"account not created!");
		continueBtn.click();
		return new LandingPage(driver);
	}

}
