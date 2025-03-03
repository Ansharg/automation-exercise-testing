package com.automationexercise.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automationexercise.automationexercise.BaseClassTest;
import com.automationexercise.pages.LoginRegister;

public class ExsistRegister extends BaseClassTest {
	
	@Test(dataProvider = "loginData")
	public void existUserRegisterChecking(HashMap<String,String> credentials) {
		LoginRegister registerPage = landingPage.gotoLoginRegister();
		registerPage.signup(credentials.get("name"),credentials.get("email"));
		registerPage.checkRegister();
	}
	
	@DataProvider(name = "loginData")
	public Object[][] fetchingData() throws IOException{
		List<HashMap<String,String>> data = dataFetching("\\src\\test\\java\\com\\automationexercise\\resources\\userData.json");
		Object[][] finalData = new Object[data.size()][data.get(0).size()];
		for(int i=0;i<data.size();i++) {
			finalData[i] = new Object[] {data.get(i)};
		}
		return finalData;
	}

}
