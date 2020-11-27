package com.afwp.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.afwp.Constants.Constants;



public class TestUtility {
	
	public static Workbook book;
	public static Sheet sheet;
	
	public static Actions actions;
	public static Select select;
	public static Alert alert;

	//DataProvider Utility is used for getting Data from Excel ==>> Should be used with @DataProvider.
	public static Object[][] getTestData(String sheetName) throws InvalidFormatException 
	{
		FileInputStream file = null;
		try 
		{
			file = new FileInputStream(Constants.TEST_DATA_SHEET_PATH);
		} 
		catch(FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		try 
		{
			book = WorkbookFactory.create(file);
		} 
		catch(IOException e) 
		{
			e.printStackTrace();
		}
		
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for(int i = 0; i < sheet.getLastRowNum(); i++) 
		{
			for(int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) 
			{
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		return data;
	}
	
		public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException 
		{
			String dateName = new SimpleDateFormat("_ddMMyyyy_HHmmss").format(new Date());
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);

			String destination = System.getProperty("user.dir") + "\\FailedTestsScreenshots\\" + screenshotName + dateName + ".png";
			File finalDestination = new File(destination);
			FileUtils.copyFile(source, finalDestination);
			return destination;
		}
		
		//Extent Report - 1.
		public static String getSystemDate() 
		{
			DateFormat dateFormat = new SimpleDateFormat("_ddMMyyyy_HHmmss");
			Date date = new Date();
			return dateFormat.format(date);
		}
		
		//Set Date For Log4J.
		public static void setDateForLog4j()
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("_ddMMyyyy_HHmmss");
			System.setProperty("current_date", dateFormat.format(new Date()));
			PropertyConfigurator.configure("./src/main/resources/log4j.properties");
		}


}
