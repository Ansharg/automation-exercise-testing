package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.automationexercise.automationexercise.BaseClass;

public class Footer extends BaseClass {

	WebDriver driver;
	
	public Footer(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//footer[@id='footer']//h2")
	WebElement subscriptionText;
	
	@FindBy(xpath="//footer[@id='footer']//input[@id='susbscribe_email']")
	WebElement subscriptionInput;
	
	@FindBy(xpath="//footer[@id='footer']//button[@id='subscribe']")
	WebElement submitButton;
		
	public void verifyText() {
		String message = subscriptionText.getText();
		Assert.assertEquals(message, "SUBSCRIPTION");
	}
	
	public void submitSubscription(String email) {
		Actions ac = new Actions(driver);
		ac.scrollToElement(subscriptionInput).sendKeys(subscriptionInput, email).click(submitButton).build().perform();
	}
	
	public void verifySubscription() {
		elementVisible("//footer//div[@class='alert-success alert']");
	}
	
	public void gotoSubscription() {
		Actions ac = new Actions(driver);
		ac.scrollToElement(subscriptionInput).perform();
	}
}
