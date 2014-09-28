package com.nhuha.invoice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class SuperTest {

	public WebDriver driver;

	private static final String url = "file:///D:/Selenium/Project/html_invoice/index.html";

	public static String price = "12";
	public static String producName = "Super";
	public static String quantity = "3";

	public static Set<String> invalidProducNameList = new HashSet<>(
			Arrays.asList(" ", "12", "C", "c", "CA", "ca", "%$"));
	public static Set<String> quantityPriceFieldList = new HashSet<>(
			Arrays.asList(" ", "0", "C", "c", "CA", "ca", "%$", "-1", "-1.1"));

	@Parameters({ "browser" })
	@BeforeClass
	
	public void setUp(String browser) {
		  if (browser.equalsIgnoreCase("firefox")) {
              driver = new FirefoxDriver();
       } else if (browser.equalsIgnoreCase("chrome")) {
                     System.setProperty("webdriver.chrome.driver",
                           "D:\\chromedriver.exe");
              driver = new ChromeDriver();}
		/*// System.setProperty("webdriver.chrome.driver",
		// "D://chromedriver.exe");
		driver = new FirefoxDriver();
		// driver=new ChromeDriver();
*/		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);
	}

	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}

	public WebDriver getDriverInstance() {
		return driver;
	}

	@DataProvider
	public static Iterator<Object[]> stringFieldsProvider() {
		List<Object[]> dataToBeReturned = new ArrayList<>();
		for (String invalidProducName : invalidProducNameList) {
			{
				dataToBeReturned.add(new Object[] { invalidProducName });
			}
		}
		return dataToBeReturned.iterator();
	}

	@DataProvider
	public static Iterator<Object[]> quantityPriceProvider() {
		List<Object[]> dataToBeReturned = new ArrayList<>();
		for (String quantityPriceField : quantityPriceFieldList) {
			{
				dataToBeReturned.add(new Object[] { quantityPriceField });
			}
		}
		return dataToBeReturned.iterator();
	}

}
