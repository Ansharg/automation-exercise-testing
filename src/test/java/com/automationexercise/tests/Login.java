package com.automationexercise.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automationexercise.automationexercise.BaseClassTest;
import com.automationexercise.pages.LandingPage;
import com.automationexercise.pages.LoginRegister;

public class Login extends BaseClassTest {
	
	@Test(dataProvider = "loginData")
	public void login(HashMap<String,String> credentials) {
		LoginRegister loginPage = landingPage.gotoLoginRegister();
		LandingPage logedinPage = loginPage.login(credentials.get("email"),credentials.get("password"));
		logedinPage.loginCheck(credentials.get("name"));
		WebElement deleteBtn = landingPage.checkDeleteAccountVisble();
		deleteBtn.click();;
		
	}
	
	@DataProvider(name = "loginData")
	public Object[][] fetchingData() throws IOException{
		List<HashMap<String,String>> data = dataFetching("\\src\\test\\java\\com\\automationexercise\\resources\\loginData.json");
		
		Object[][] finalData = new Object[data.size()][data.get(0).size()];
		for(int i=0;i<data.size();i++) {
			finalData[i] = new Object[] {data.get(i)};
		}
		return finalData;
		
	}

}
