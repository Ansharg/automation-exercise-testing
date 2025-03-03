package com.automationexercise.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automationexercise.automationexercise.BaseClassTest;
import com.automationexercise.pages.ContactUs;

public class ContactUsForm extends BaseClassTest {
	
	@Test(dataProvider="contactData")
	public void contactUsForm(HashMap<String,String> info) {
		ContactUs contactPage = landingPage.gotoContactUs();
		contactPage.submitForm(info);
		driver.switchTo().alert().accept();
		contactPage.submitCheck();
	}
	
	@DataProvider(name="contactData")
	public Object[][] fetchingData() throws IOException {
		List<HashMap<String,String>> data = dataFetching("\\src\\test\\java\\com\\automationexercise\\resources\\contactUs.json");
		Object[][] finalData = new Object[data.size()][data.get(0).size()];
		for(int i=0;i<data.size();i++) {
			finalData[i] = new Object[] {data.get(i)};
		}
		return finalData;
	}
}
