package com.tricentis.sampleapp.Base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory extends Base {
	static WebDriver driver;

	static ChromeDriver chromedriver;
	static InternetExplorerDriver iedriver;
	static EdgeDriver edgedriver;
	static FirefoxDriver firefoxdriver;
	static OperaDriver operadriver;

	public static WebDriver init_driver(String browserName) throws Exception {

		switch (browserName.toLowerCase()) {

		case "chrome":
			return StartChromeBrowser();

		case "ie":
			return StartIEBrowser();

		case "edge":
			return StartEdgeBrowser();

		case "firefox":
			return StartFireFoxBrowser();

		case "opera":
			return StartOperaBrowser();

		}
		return null;

	}

	public static ChromeDriver StartChromeBrowser() {

		ChromeOptions options = new ChromeOptions();
		if (prop.getProperty("headless").equals("yes")) {
			options.addArguments("--headless");
			WebDriverManager.chromedriver().setup();
			// System.setProperty("webdriver.chrome.driver",
			// prop.getProperty("ChromeDriver"));
			chromedriver = new ChromeDriver(options);
			chromedriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		} else {
			options.addArguments("--disable-notifications");
			WebDriverManager.chromedriver().setup();
			// System.setProperty("webdriver.chrome.driver",
			// prop.getProperty("ChromeDriver"));
			chromedriver = new ChromeDriver(options);
			chromedriver.manage().window().maximize();
			chromedriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		}

		return chromedriver;
	}

	public static InternetExplorerDriver StartIEBrowser() {

		WebDriverManager.iedriver().arch32().setup();
		// System.setProperty("webdriver.ie.driver", prop.getProperty("IEDriver"));
		iedriver = new InternetExplorerDriver();
		iedriver.manage().window().maximize();
		iedriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return iedriver;
	}

	public static EdgeDriver StartEdgeBrowser() {
		WebDriverManager.edgedriver().arch32().setup();
		// System.setProperty("webdriver.edge.driver", prop.getProperty("EdgeDriver"));
		edgedriver = new EdgeDriver();
		edgedriver.manage().window().maximize();
		edgedriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return edgedriver;
	}

	public static FirefoxDriver StartFireFoxBrowser() {
		FirefoxOptions options = new FirefoxOptions();
		if (prop.getProperty("headless").equals("yes")) {
			options.addArguments("--headless");
			WebDriverManager.firefoxdriver().arch32().setup();
			// System.setProperty("webdriver.gecko.driver",
			// prop.getProperty("FireFoxDriver"));
			firefoxdriver = new FirefoxDriver();
			firefoxdriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		} else {
			options.addArguments("--disable-notifications");
			// WebDriverManager.firefoxdriver().arch32().setup();
			System.setProperty("webdriver.gecko.driver", prop.getProperty("FireFoxDriver"));
			firefoxdriver = new FirefoxDriver();
			firefoxdriver.manage().window().maximize();
			firefoxdriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		}

		return firefoxdriver;
	}

	public static OperaDriver StartOperaBrowser() {
		OperaOptions options = new OperaOptions();
		if (prop.getProperty("headless").equals("yes")) {
			options.addArguments("--headless");
			WebDriverManager.operadriver().arch32().setup();
			// System.setProperty("webdriver.opera.driver",
			// prop.getProperty("OperaDriver"));
			operadriver = new OperaDriver();
			operadriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		} else {
			options.addArguments("--disable-notifications");
			WebDriverManager.operadriver().arch32().setup();
			// System.setProperty("webdriver.opera.driver",
			// prop.getProperty("OperaDriver"));
			operadriver = new OperaDriver();
			operadriver.manage().window().maximize();
			operadriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		}

		return operadriver;
	}

}