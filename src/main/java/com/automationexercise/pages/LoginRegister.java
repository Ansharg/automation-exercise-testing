package com.automationexercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.automationexercise.automationexercise.BaseClass;

public class LoginRegister extends BaseClass {
	
	public WebDriver driver;
	
	
	public LoginRegister(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@data-qa='login-email']")
	WebElement loginEmail;
	
	
	@FindBy(name = "password")
	WebElement loginPassword;   
	
	
	@FindBy(name = "name")
	WebElement registerName;
	
	
	@FindBy(xpath = "//input[@data-qa='signup-email']")
	WebElement registerEmail;
	
	
	@FindBy(xpath = "//button[@data-qa='signup-button']")
	WebElement registerBtn;
	
	@FindBy(xpath = "//button[@data-qa='login-button']")
	WebElement loginBtn;
	
	
	public SignUpPage signup(String name, String email) {
		registerName.sendKeys(name);
		registerEmail.sendKeys(email);
		registerBtn.click();
		return new SignUpPage(driver);
	}
	
	public LandingPage login(String email, String pass) {
		loginEmail.sendKeys(email);
		loginPassword.sendKeys(pass);
		loginBtn.click();
		return new LandingPage(driver);
	}
	public void checkIncorrect() {
		WebElement message = elementVisibleCheck("//p[text()='Your email or password is incorrect!']");
		Assert.assertEquals(message.getText(), "Your email or password is incorrect!");
	}
	public void checkRegister() {
		WebElement message = elementVisibleCheck("//p[text()='Email Address already exist!']");
		Assert.assertEquals(message.getText(), "Email Address already exist!");
	}
	
}
