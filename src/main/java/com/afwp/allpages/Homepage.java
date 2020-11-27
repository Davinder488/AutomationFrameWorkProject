package com.afwp.allpages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.afwp.base.TestBase;

public class Homepage extends TestBase{
	

	@FindBy(xpath = "//img[@class='logo img-responsive']")
	WebElement YourLogoImg;
	
	@FindBy(xpath="//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")
	WebElement ClickonsignOnbtn;
	
	@FindBy(xpath="//input[@name='search_query']")
	WebElement SearchEngine;
	
	@FindBy(xpath="//*[@id=\"searchbox\"]/button")
	WebElement SubmitValueInSearchEngine;
	
	@FindBy(xpath="//*[@id=\"header\"]/div[2]/div/div/nav/span")
	WebElement Callusnow;
	
	public Homepage() {
		PageFactory.initElements(driver, this);
	}


public boolean validateYourLogoImgDisplay() {
	 return YourLogoImg.isDisplayed();
}
public void ClickonsignOnbtn() {
	ClickonsignOnbtn.click();
}
public void EnterValueInSearchEngine(String Value) {
	SearchEngine.sendKeys(Value);
}
public void SubmitValueInSearchEngine() {
	SubmitValueInSearchEngine.click();
}
public boolean CallUsNow() {
	return Callusnow.isDisplayed();
}




}