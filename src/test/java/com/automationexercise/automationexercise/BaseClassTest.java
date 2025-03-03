package com.automationexercise.automationexercise;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.automationexercise.pages.LandingPage;
import com.automationexercise.resources.DataReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClassTest {

	public static WebDriver driver;

	public LandingPage landingPage;

	public WebDriver initializeApp() throws IOException {

		// properties object is used for for reading .properties file format for
		// configuration.
		Properties prop = new Properties();

		// FileInputStream object is used here for converting file in input streams.
		// also System... code line used for getting dynamically user dir from the
		// system.
		FileInputStream path = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\test\\java\\com\\automationexercise\\resources\\config.properties");

		// now we loaded the file input stream in prop.
		prop.load(path);

		// getting browser name from .property file.
		String browser = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.automationexercise.com/");
		return driver;

	}

	public boolean isFileDownloaded(String downloadPath, String fileName) {
		File dir = new File(downloadPath);
		File[] dirContents = dir.listFiles();

		for (int i = 0; i < dirContents.length; i++) {
			if (dirContents[i].getName().contains(fileName)) {
				// File has been found, it can now be deleted:
				dirContents[i].delete();
				return true;
			}
		}
		return false;
	}

	public static List<HashMap<String, String>> dataFetching(String path) throws IOException {
		return new DataReader().dataReader(path);
	}

	public static String takeScreenShot(String testCase, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File des = new File(System.getProperty("user.dir") + "\\reports\\" + testCase + ".png");
		FileUtils.copyFile(src, des);
		return (System.getProperty("user.dir") + "\\reports\\" + testCase + ".png");
	}

	public boolean elementVisible(String path) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(path))).isDisplayed();
	}

	public List<WebElement> getElementsByXPath(String path) {
		return driver.findElements(By.xpath(path));
	}

	@BeforeMethod
	public void entryPoint() throws IOException {
		WebDriver driver = initializeApp();
		landingPage = new LandingPage(driver);
	}

	@AfterMethod
	public void closingPoint() {
		driver.close();
	}

}
