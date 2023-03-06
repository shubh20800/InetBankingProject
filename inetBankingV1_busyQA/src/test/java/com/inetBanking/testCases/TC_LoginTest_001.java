package com.inetBanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass{
	
	
	@Test
	public void loginTest() throws IOException {
		
	
		LoginPage lp= new LoginPage(driver);
 
		
		lp.setUserName(username);
		logger.info("Entered username");  //logging
		
		lp.setPassword(password);
		logger.info("Entered password");
		
		lp.clickSubmit();
		logger.info("Logged in successfully !!");
		
		
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage1234")) {
			Assert.assertTrue(true);
			
			logger.info("Login test passed !!!");
		}
		else {
			captureScreen(driver, "loginTest");    //Capture screenshot method call  ---WHEN FAILURE IS ENCOUNTERED
			Assert.assertTrue(false);
			logger.info("Login test failed :(");
			
		}
		
	}
	

}
