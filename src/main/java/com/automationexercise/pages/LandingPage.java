package com.automationexercise.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.automationexercise.automationexercise.BaseClass;

import components.Footer;

public class LandingPage extends BaseClass {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "(//a[@href='/'])[2]")
	WebElement homeBtn;
	
	@FindBy(xpath = "//a[@href='/products']")
	WebElement productsBtn;
	
	@FindBy(xpath = "//a[normalize-space()='Cart']")
	WebElement cartBtn;
	
	@FindBy(xpath="//a[@href='/login']")
	WebElement loginBtn;
	
	@FindBy(xpath ="//a[contains(text(),'Test Cases')]")
	WebElement testCasesBtn;
	
	@FindBy(xpath ="//a[contains(text(),'API Testing')]")
	WebElement apiTestingBtn;
	
	@FindBy(xpath ="//a[contains(text(),'Video Tutorials')]")
	WebElement videoTutorialBtn;
	
	@FindBy(xpath = "//a[@href='/contact_us']")
	WebElement contactUsBtn;
	
	@FindBy(xpath = "//li[10]//b")
	WebElement loginCheck;
	
	@FindBy(xpath = "//a[@href='/delete_account']")
	WebElement deleteAccBtn;
	
	@FindBy(xpath = "//h2[text()='Category']")
	WebElement categoryTitle;
	
	@FindBy(xpath = "//div[@id='accordian']//div//div")
	List<WebElement> categories;
	
	public void categoryVisible() {
		boolean title = categoryTitle.isDisplayed();
		Assert.assertTrue(title, "category is not visible");
	}
	
	public List<WebElement> selectCategory(String category) throws InterruptedException {
		for(int i=0;i<categories.size();i++) {
			if(categories.get(i).findElement(By.xpath(".//a")).getText().equals(category)) {
				categories.get(i).findElement(By.xpath(".//a")).click();
				Thread.sleep(Duration.ofSeconds(5));
				return categories.get(i).findElements(By.xpath(".//following-sibling::div//div//ul//li//a"));
			}
		}
		return null;
	}
	
	public CategoryPage selectSubCategories(List<WebElement> subCategories,String subcategory) throws InterruptedException {
		for(int i=0;i<subCategories.size();i++) {
			System.out.println(subCategories.get(i).getText().trim().equals(subcategory));
			if(subCategories.get(i).getText().trim().contentEquals(subcategory)) {
				subCategories.get(i).click();
				return new CategoryPage(driver);
			}
		}
		return null;
	}
	
	public Products gotoProducts() {
		productsBtn.click();
		return new Products(driver);
	}
	public CartPage gotoCart() {
		cartBtn.click();
		return new CartPage(driver);
	}
	public TestCasesPage gotoTestCases() {
		testCasesBtn.click();
		return new TestCasesPage(driver);
	}
	public void gotoApiTesting() {
		apiTestingBtn.click();
	}
	public void gotoVideoTutorial() {
		videoTutorialBtn.click();
	}
	public ContactUs gotoContactUs() {
		contactUsBtn.click();
		return new ContactUs(driver);
	}
	public LoginRegister gotoLoginRegister() {
		loginBtn.click();
		return new LoginRegister(driver);
	}
	public void loginCheck(String check) {
		Assert.assertEquals(loginCheck.getText(), check);	
	}
	public WebElement checkDeleteAccountVisble() {
		String path = "//a[@href='/delete_account']";
		return elementVisibleCheck(path);
	}
	public void clickDeleteAccount() {
		deleteAccBtn.click();
		elementVisibleCheck("//b");
	}
	public void logout() {
		String path = "//a[@href='/logout']";
		WebElement logoutBtn = elementVisibleCheck(path);
		logoutBtn.click();
	}
	public Footer getFooter() {
		return new Footer(driver);
	}
}
