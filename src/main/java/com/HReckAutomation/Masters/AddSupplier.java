package com.HReckAutomation.Masters;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.HReckAutomation.CommonUtils.CommonUtilities;
import com.HReckAutomation.CommonUtils.SendMail;
import com.HReckAutomation.CommonUtils.TakeScteenshot;
import com.mentorstudies.automationframework.common.TestClassUtil;
import com.mentorstudies.automationframework.exception.AutomationFrameworkException;
import com.mentorstudies.automationframework.util.common.KeyWordTool;

public class AddSupplier extends TestClassUtil
{
	
//		@BeforeClass
//		public void openBrowser() throws IOException, AutomationFrameworkException, InterruptedException 
//		{
//			CommonUtilities.AccessToHReckURL(true);
//		}
//		
//		@AfterClass
//		public void closeBrowser() throws IOException, AutomationFrameworkException, InterruptedException {
//			CommonUtilities.AccessToHReckURL(false);
//		}
		@Test(priority=0,dataProvider="defaultDP")
		public void gotoSupplier() throws AutomationFrameworkException, InterruptedException 
		{
			
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("gotoSupplier", "masters")).click();
			WebElement element = CommonUtilities.driver.findElement(KeyWordTool.getLocator("gotoSupplier", "supplier"));
	        Actions action = new Actions(CommonUtilities.driver);
	        action.moveToElement(element).build().perform();
	        CommonUtilities.driver.findElement(KeyWordTool.getLocator("gotoSupplier", "supplieradd")).click();
	        Thread.sleep(1000);
	        String actualUrl=CommonUtilities.driver.getCurrentUrl();
	        String Expected= "http://hreck.techeasesystems.in/#/supplier";
	        Assert.assertEquals(actualUrl, Expected);
		}
		
		@AfterMethod()
		public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
			if (testResult.getStatus() == ITestResult.FAILURE) {
				//SendMail.mail();
				TakeScteenshot.getscreenshot( "AddSupplier" + System.currentTimeMillis());
			}
		}
	}



