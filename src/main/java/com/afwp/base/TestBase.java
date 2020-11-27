package com.afwp.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

import com.afwp.Constants.Constants;
import com.afwp.Utilities.TestUtility;
import com.afwp.Utilities.WebEventListeners;



public class TestBase {
	public static WebDriver driver; 
	public static Properties property;
	public static ChromeOptions chromeOptions;
	public static EventFiringWebDriver e_driver;
	public static WebEventListeners eventListener;
	public static Logger Log;
		
	public TestBase()
	{
		Log = Logger.getLogger(this.getClass());
		try 
		{                    //System.getProperty("user.dir") + "/src/main/java/com/guru/qa/config/config.properties");
			property = new Properties();
			FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/afwp/config/config.properties");
			property.load(inputStream);
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	@BeforeTest
	public void setLog4j()
	{
		TestUtility.setDateForLog4j();
	}
	
	public static void initialization()
	{
		String broswerName = property.getProperty("browser");
		
		//String broswerName = System.getProperty("Browser");
		if(broswerName.equals("chrome"))
		{
			chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("useAutomationExtension", false);
			chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH);
			driver = new ChromeDriver(chromeOptions);
		}
		else if(broswerName.equals("IE"))
		{
			System.setProperty("webdriver.ie.driver", Constants.INTERNET_EXPLORER_DRIVER_PATH);
			driver = new InternetExplorerDriver();
		}
		else if(broswerName.equals("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver", Constants.FIREFOX_DRIVER_PATH);
			driver = new FirefoxDriver();
		}
		else
		{
			System.out.println("Path of Driver Executable is not Set for any Browser");
		}
		
		e_driver = new EventFiringWebDriver(driver);
		
		eventListener = new WebEventListeners();

		//e_driver.unregister(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(property.getProperty("url"));
	}
	
	/*@AfterTest
	public void endReport()
	{
		driver.close();
	}*/
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() throws IOException
	{
		//driver.quit();
		Log.info("Browser Terminated");
		Log.info("-----------------------------------------------");
	}

}
