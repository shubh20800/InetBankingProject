package com.inetBanking.testCases;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetBanking.utilities.ReadConfig;


public class BaseClass {
	
	ReadConfig readconfig= new ReadConfig();

	protected String baseURL=  readconfig.getApplicationUrl();
	protected String username= readconfig.getUsername();
	protected String password= readconfig.getPassword();
	public static WebDriver driver;
	public Logger logger;
	
	
	@SuppressWarnings("deprecation")
	@Parameters("browser")
	@BeforeClass
	public void setup(String br)						///Driver setup
	{

//		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver");
//		driver= new ChromeDriver();
		
		 logger= Logger.getLogger("inetbanking");  //Initiate log4j obj
		PropertyConfigurator.configure("log4j.properties");
		
		
		if(br.equals("chrome")) {
		System.setProperty("webdriver.chrome.driver", readconfig.getChromepath());
		driver= new ChromeDriver();
		}
		
		else if(br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxpath());
			driver= new FirefoxDriver();
		}
		
		else if(br.equals("edge")){
			System.setProperty("webdriver.edge.driver",readconfig.getEdgepath());
			driver=new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(baseURL);
	}
	
	
	@AfterClass 
	public void tearDown() {				///Quit browser
		
		driver.quit();
		
		try{
            driver.quit();
        }catch (Exception e){
            System.out.println("Browser closed already, " +
                            "did not need to quit after all");
            e.printStackTrace();
        }
	}
	
	
	
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {  //Capture screenshot 
		TakesScreenshot ts= (TakesScreenshot)driver;
		
		File source= ts.getScreenshotAs(OutputType.FILE);
		File target= new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
		
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
		 
	}
	
}
