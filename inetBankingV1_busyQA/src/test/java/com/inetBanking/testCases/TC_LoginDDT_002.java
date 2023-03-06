package com.inetBanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.LoginPage;
import com.inetBanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass {

	
	////NEW COMMENTS IN TC02 for GIT  /////
	
	@Test (dataProvider="LoginData")
	public void loginDDT(String user, String pwd) throws IOException, InterruptedException {
		
		LoginPage lp= new LoginPage(driver);
		lp.setUserName(user);
		lp.setPassword(pwd);
		lp.clickSubmit();
		
		Thread.sleep(3000);
		
		if(isAlertPresent()==true)
		{
			captureScreen(driver, "loginDDT");
			driver.switchTo().alert().accept();   //close alert
			driver.switchTo().defaultContent();  //switch to main content
			 
			Assert.assertTrue(false);
			logger.warn("Login failed");
		}
		
		else 
		{
			Assert.assertTrue(true);
			logger.info("Login passed");
			lp.clickLogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();   //closes logout alert
			driver.switchTo().defaultContent();
		}
		
	}
	
	
	
	@DataProvider(name="LoginData")
	
	String[][] getData() throws IOException
	{
		String path= System.getProperty("user.dir")+"/src/test/java/com/inetBanking/testData/LoginData.xlsx";
				
		int rownum= XLUtils.getRowCount(path, "Sheet1");
		int colcount=XLUtils.getCellCount(path, "Sheet1", 1);
		
		String logindata[][]= new String[rownum][colcount];
		for(int i=1; i<=rownum; i++)
		{
			for(int j=0; j<colcount; j++)
			{
				logindata[i-1][j]= XLUtils.getCellData(path, "Sheet1", i,j);
			}
		}
		return logindata;
	}
	
	
	public boolean isAlertPresent()    //Method to check Alert is present or not
	{
		try {
			driver.switchTo().alert();
			return true;
		} catch(Exception e)
		{
			 
			return false;
		}
	}
	
	
	


}
