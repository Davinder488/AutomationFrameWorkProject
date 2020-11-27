package com.afwp.TestListeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.afwp.Reports.ExtentReportSetUp;
import com.afwp.Utilities.TestUtility;
import com.aventstack.extentreports.Status;


public class ExtentReportListener extends ExtentReportSetUp implements ITestListener{
	
	
	public void onTestStart(ITestResult result) 
	{
		extentTest = extent.createTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) 
	{
		extentTest.log(Status.PASS, "Test Case Passed is ::: " +result.getMethod().getMethodName());
	}

	public void onTestFailure(ITestResult result) 
	{
		extentTest.log(Status.FAIL, "Test Case Failed is ::: " +result.getMethod().getMethodName());
		extentTest.log(Status.FAIL, result.getThrowable());
		
		try 
		{
			extentTest.addScreenCaptureFromPath(TestUtility.getScreenshot(driver, result.getMethod().getMethodName()));
		} 
		catch(IOException e) 
		{
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) 
	{
		extentTest.log(Status.SKIP, "Test Case Skipped is ::: " +result.getMethod().getMethodName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{

	}

	public void onStart(ITestContext context) 
	{
		extent = ExtentReportSetUp.extentReportSetup();
	}

	public void onFinish(ITestContext context) 
	{
		extent.flush();
	}
}



