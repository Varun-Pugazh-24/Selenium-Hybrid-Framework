package com.tricentis.sampleapp.BaseTest;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.tricentis.sampleapp.Base.Base;
import com.tricentis.sampleapp.Base.BrowserFactory;
import com.tricentis.sampleapp.Base.DriverFactory;
import com.tricentis.sampleapp.Base.TestLogger;


public class BaseTest extends Base  {

	@BeforeMethod
	public void LaunchApplication(Method method) throws Exception {
		TestLogger.startTestCase(method.getName());
		String browser = Base.initProperties().getProperty("browser");
		String url = 	Base.initProperties().getProperty("url");

		DriverFactory.getInstance().setDriver(BrowserFactory.initDriver(browser));

		DriverFactory.getInstance().getDriver().manage().window().maximize();
		DriverFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		DriverFactory.getInstance().getDriver().navigate().to(url);
		Thread.sleep(2000);

	}

	@AfterMethod
	public void tearDown(Method method) {
		DriverFactory.getInstance().closeBrowser();
		TestLogger.endTestCase(method.getName());
	}

}
