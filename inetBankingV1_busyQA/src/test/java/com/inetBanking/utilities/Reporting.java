package com.inetBanking.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.*;

public class Reporting extends TestListenerAdapter {

	public ExtentSparkReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	
	///1.Add jars in pom
	///2.Add extent-config.xml
	///3.Listener (Reporting.java) -->Utility
	///4.Add listener entry in TestNg xml
	
	public void onStart(ITestContext testContext) {
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName= "Test-Report-"+timestamp+".html";
		
		htmlReporter= new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/"+repName);
		try {
			htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
		
		htmlReporter.config().setDocumentTitle("Inet Banking Test Project");  //Title of report
		htmlReporter.config().setReportName("Functional test Report");		 //Name of Report
//		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
		
		
		
		extent= new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Shubham Sharma");
		
		
	}
	
	
		
	public void onTestSuccess(ITestResult tr) {
			
			logger=extent.createTest(tr.getName());
			logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
			
	}
	

	public void onTestFailure(ITestResult tr) {
		
		logger= extent.createTest(tr.getName());      //Create new entry in the report
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		
		String screenshotPath= System.getProperty("user.dir")+"/Screenshots/"+tr.getName()+".png";
		File f= new File(screenshotPath);
		
		if(f.exists())
		{
			try {
				logger.fail("Screenshot is below:"+logger.addScreenCaptureFromPath(screenshotPath));
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void onTestSkipped(ITestResult tr) {
		logger= extent.createTest(tr.getName());
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}
	
	
	public void onFinish (ITestContext testContext) {
		extent.flush();
	}
}
