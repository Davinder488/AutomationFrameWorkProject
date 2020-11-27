package com.afwp.Reports;

import java.io.File;

import com.afwp.Utilities.TestUtility;
import com.afwp.base.TestBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReportSetUp extends TestBase{
	public static ExtentReports extent;
	public static ExtentTest extentTest;
	public static ExtentSparkReporter sparkReport;
		
	public static ExtentReports extentReportSetup()
	{
		//sparkReport = new ExtentSparkReporter(System.getProperty("user.dir") + "\\Guru99ExtentResults\\Guru99ExtentReport" + TestUtility.getSystemDate() + ".html");
		sparkReport = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator + "afwpExtentResults" + File.separator + "afwpExtentReport" + TestUtility.getSystemDate() + ".html");
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReport);
		
		sparkReport.config().setTheme(Theme.STANDARD);
		sparkReport.config().setReportName("Test Automation Report");
		sparkReport.config().setDocumentTitle("Test Automation Report");
		
		return extent;
	}
}

