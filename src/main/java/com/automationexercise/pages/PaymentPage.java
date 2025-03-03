package com.automationexercise.pages;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage {

	WebDriver driver;
	
	public PaymentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//form//input")
	List<WebElement> inputs;
	
	@FindBy(xpath = "//button[@id='submit']")
	WebElement payBtn;
	
	@FindBy(xpath = "//a[normalize-space()='Download Invoice']")
	WebElement downloadInvoiceBtn;
	
	public void fillCardDetails() {
		HashMap<String,String> cardDetails = new HashMap<>();
		cardDetails.put("name_on_card", "ansh garg");
		cardDetails.put("card_number", "435588662823");
		cardDetails.put("cvc","456");
		cardDetails.put("expiry_month", "12");
		cardDetails.put("expiry_year", "2027");
		for(int i=0;i<inputs.size();i++) {
			String fill = cardDetails.get(inputs.get(i).getDomAttribute("name"));
			if(fill !=null && inputs.get(i).isEnabled()) {
				inputs.get(i).clear();
				inputs.get(i).sendKeys(fill);
			}
			else {
				continue;
			}
		}
		payBtn.click();
	}
	
	public void downloadInvoice() {
		downloadInvoiceBtn.click();
	}
	
}
