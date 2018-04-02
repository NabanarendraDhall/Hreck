package com.HReckAutomation.HReck;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.HReckAutomation.CommonUtils.CommonUtilities;
import com.mentorstudies.automationframework.common.TestClassUtil;
import com.mentorstudies.automationframework.exception.AutomationFrameworkException;
import com.mentorstudies.automationframework.util.common.KeyWordTool;

public class forgotPassword extends TestClassUtil 
{
	WebDriver driver;
	String actual, expected;
	

	@Test(priority = 1, dataProvider = "defaultDP")
	public void changePassword() throws AutomationFrameworkException, IOException, InterruptedException {
		CommonUtilities.AccessToHReckURL(true);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(KeyWordTool.getLocator("changePassword", "PreLogOutBotton")).click();
		driver.findElement(KeyWordTool.getLocator("changePassword", "ChPassLink")).click();
		Assert.assertEquals(driver.findElement(KeyWordTool.getLocator("changePassword", "ChPassButton")).isEnabled(), false); 
		CommonUtilities.AccessToHReckURL(false);
	}
}