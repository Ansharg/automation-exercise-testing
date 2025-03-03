package com.automationexercise.pages;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.automationexercise.automationexercise.BaseClass;

public class ContactUs extends BaseClass {
	
	WebDriver driver;
	
	public ContactUs(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//form//div//input")
	List<WebElement> inputs;
	
	@FindBy(xpath="//form//div//input[@type='submit']")
	WebElement submitBtn;
	
	public void submitForm(HashMap<String,String> info) {
		for(int i=0;i<inputs.size();i++) {
			String fill = info.get(inputs.get(i).getDomAttribute("name"));
			if(fill != null && inputs.get(i).isEnabled()) {
				inputs.get(i).clear();
				inputs.get(i).sendKeys(fill);
			}
		}
		submitBtn.click();
	}
	
	public void submitCheck() {
		String path = "//div[text()='Success! Your details have been submitted successfully.']";
		WebElement message = elementVisibleCheck(path);
		Assert.assertEquals(message.getText(), "Success! Your details have been submitted successfully.");
	}
}
