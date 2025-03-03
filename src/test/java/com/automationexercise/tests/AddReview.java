package com.automationexercise.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automationexercise.automationexercise.BaseClassTest;
import com.automationexercise.pages.ProductDetail;
import com.automationexercise.pages.Products;

public class AddReview extends BaseClassTest {
	
	@Test(dataProvider="reviewData")
	public void addReview(HashMap<String,String> data) {
		Products productsPage = landingPage.gotoProducts();
		ProductDetail detailPage = productsPage.gotoFirstDetailPage();
		detailPage.checkWriteReview();
		detailPage.writeReview(data);
	}
	
	@DataProvider(name="reviewData")
	public Object[][] fetchingData() throws IOException{
		List<HashMap<String,String>> data = dataFetching("\\src\\test\\java\\com\\automationexercise\\resources\\reviewData.json");
		
		Object[][] finalData = new Object[data.size()][data.get(0).size()];
		for(int i=0;i<data.size();i++) {
			finalData[i] = new Object[] {data.get(i)};
		}
		return finalData;
	}

}
