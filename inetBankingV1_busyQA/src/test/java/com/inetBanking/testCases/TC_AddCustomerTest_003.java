package com.inetBanking.testCases;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.AddCustomerPage;
import com.inetBanking.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass{

	
	@Test
	public void addNewCustomer() throws InterruptedException, IOException
	{
		LoginPage lp =new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Username is provided");
		lp.setPassword(password);
		logger.info("Password is provided");
		lp.clickSubmit();
		
		Thread.sleep(3000);
		AddCustomerPage addcust= new AddCustomerPage(driver);
		
		addcust.clickAddNewCustomer();
		
		logger.info("Adding customer details....");
		
		addcust.custName("Shubham");
		addcust.custgender("male");
		addcust.custdob("01", "01", "1994");
		Thread.sleep(3000);
		
		addcust.custaddress("India");
		addcust.custcity("Bangalore");
		addcust.custstate("Karnataka");
		addcust.custpinno("560066");
		addcust.custtelephoneno("45645586868");
		
		String email=randomString()+"@gmail.com";
		
		addcust.custemailid(email);
		addcust.custpassword("adhjasjkdas");
		addcust.custsubmit();
		
		Thread.sleep(3000);
		
		logger.info("Validation started...");
		
		boolean res= driver.getPageSource().contains("Customer Registered Successfully!!!");
		
		if(res==true)
		{
			Assert.assertTrue(true);
			logger.info("Testcase is passed...");
		}
		else 
		{
			captureScreen(driver, "AddCustomer");
			logger.info("Testcase has failed...");
			captureScreen(driver,"addNewCustomer" );
			Assert.assertTrue(false);
		}
	}
	
	
	public static String randomString() {			//dynamic email generator
		
		String generatedstring= RandomStringUtils.randomAlphabetic(8);
		return generatedstring;
	}
	
	public static String randomNumeric() {			//dynamic email generator
		
		String generatedstring2= RandomStringUtils.randomNumeric(4);
		return generatedstring2;
	}
}
