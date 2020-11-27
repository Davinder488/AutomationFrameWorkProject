package com.afwp.testcases;



import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.afwp.allpages.Homepage;
import com.afwp.base.TestBase;





public class HomepageTestcases extends TestBase{
	
	Homepage hm;

	public HomepageTestcases () {
		super();
	}
	

	@BeforeMethod
	public void setUp() {
		initialization();
		Log.info("Application Launched Successfully");
		//loginPage = new LoginPage();
		hm = new Homepage();
		
	}
	
      @Test(priority=1)
    public  void validateYourLogoImgDisplay() {
	Boolean flag = hm.validateYourLogoImgDisplay();
	System.out.println("Yes, Logo is Present");
	/*Assert.assertTrue(flag);
	System.out.println(flag);
	Log.info("Verified validatePayGatPrjBtnTest");
	return flag;*/
}
      @Test(priority=2)
       public void ClickonsignOnbtn() {
	   hm.ClickonsignOnbtn();
	   System.out.println("clicked on ");
}
	@Test(priority=3)
	public void EnterValueInSearchEngine() throws InterruptedException {
		Thread.sleep(3000);
		hm.EnterValueInSearchEngine("Shirts");
		System.out.println("Enter Shirt in the Search Engine");
	}
	@Test(priority=4)
	public void SubmitValueInSearchEngine() {
		hm.SubmitValueInSearchEngine();
		hm.SubmitValueInSearchEngine();
		System.out.println("Clicked on Submit button");
	}
	@Test(priority=5)
	public void CallUsNow() {
		Boolean flag1=hm.CallUsNow();
		System.out.println("Yes Call Us Now _ is Displaying");
	}

  @AfterMethod
public void tearDown() {
	driver.quit();
}
      
}

	
	
	
	
	
	
	
	
	
	
	
	



















