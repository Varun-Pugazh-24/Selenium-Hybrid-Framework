package com.tricentis.sampleapp.Base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

public class Base extends Constants {

	
	public WebDriver driver;
	static ChromeDriver chromedriver;
	static InternetExplorerDriver iedriver;
	static EdgeDriver edgedriver;
	static FirefoxDriver firefoxdriver;
	static OperaDriver operadriver;
	static Properties prop;
	

	public WebDriver init_driver(String browserName) throws Exception {

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

	public static Properties init_properties() {

		File PropertyFile = new File(CONFIG_PROPERTIES_FILE_PATH);
		try {
			FileInputStream fis = new FileInputStream(PropertyFile);
			prop = new Properties();
			prop.load(fis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return prop;

	}
	

	public static ChromeDriver StartChromeBrowser() {

		ChromeOptions options = new ChromeOptions();
		if (prop.getProperty("headless").equals("yes")) {
			options.addArguments("--headless");
			System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriver"));
			chromedriver = new ChromeDriver(options);

		} else {
			options.addArguments("--disable-notifications");
			System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriver"));
			chromedriver = new ChromeDriver(options);
			chromedriver.manage().window().maximize();

		}

		return chromedriver;
	}

	public static InternetExplorerDriver StartIEBrowser() {

		System.setProperty("webdriver.ie.driver", prop.getProperty("IEDriver"));
		iedriver = new InternetExplorerDriver();
		iedriver.manage().window().maximize();
		return iedriver;
	}

	public static EdgeDriver StartEdgeBrowser() {
		System.setProperty("webdriver.edge.driver", prop.getProperty("EdgeDriver"));
		edgedriver = new EdgeDriver();
		edgedriver.manage().window().maximize();
		return edgedriver;
	}

	public static FirefoxDriver StartFireFoxBrowser() {
		FirefoxOptions options = new FirefoxOptions();
		if (prop.getProperty("headless").equals("yes")) {
			options.addArguments("--headless");
			System.setProperty("webdriver.gecko.driver", prop.getProperty("FireFoxDriver"));
			firefoxdriver = new FirefoxDriver();

		} else {
			options.addArguments("--disable-notifications");
			System.setProperty("webdriver.gecko.driver", prop.getProperty("FireFoxDriver"));
			firefoxdriver = new FirefoxDriver();
			firefoxdriver.manage().window().maximize();

		}

		return firefoxdriver;
	}

	public static OperaDriver StartOperaBrowser() {
		OperaOptions options = new OperaOptions();
		if (prop.getProperty("headless").equals("yes")) {
			options.addArguments("--headless");
			System.setProperty("webdriver.opera.driver", prop.getProperty("OperaDriver"));
			operadriver = new OperaDriver();
		} else {
			options.addArguments("--disable-notifications");
			System.setProperty("webdriver.opera.driver", prop.getProperty("OperaDriver"));
			operadriver = new OperaDriver();
			operadriver.manage().window().maximize();

		}

		return operadriver;
	}

}
