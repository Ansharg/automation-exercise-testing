package com.automationexercise.tests;

import org.testng.annotations.Test;

import com.automationexercise.automationexercise.BaseClassTest;
import com.automationexercise.pages.TestCasesPage;

public class VerifyTestCases extends BaseClassTest {
	
	@Test
	public void testCasesPage() {
		TestCasesPage testPage = landingPage.gotoTestCases();
		testPage.verifyPage();
	}
}
