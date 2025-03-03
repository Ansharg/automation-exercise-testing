package com.automationexercise.tests;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


import com.automationexercise.automationexercise.BaseClassTest;
import com.automationexercise.pages.LoginRegister;
import com.automationexercise.pages.SignUpPage;

public class Register extends BaseClassTest {

	@Test(dataProvider = "testData")
	public void register(HashMap<String,String> info) {
		LoginRegister loginRegister = landingPage.gotoLoginRegister();
		SignUpPage signup = loginRegister.signup(info.get("name"), info.get("email"));
		signup.fillInfo(info);
	}

	@DataProvider(name = "testData")
	public static Object[][] provider() throws IOException {
		List<HashMap<String,String>> data = dataFetching("\\src\\test\\java\\com\\automationexercise\\resources\\registerData.json");
		
		Object[][] finalData = new Object[data.size()][data.get(0).size()];
		for(int i=0;i<data.size();i++) {
			finalData[i] = new Object[] {data.get(i)};
		}
		return finalData;
	}
}
