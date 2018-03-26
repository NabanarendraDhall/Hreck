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
import com.mentorstudies.automationframework.util.impl.DefaultDriverManager;

public class ChangePassword extends TestClassUtil {
	WebDriver driver;
	String actual, expected;
	
//	@Test(priority = 0, dataProvider = "defaultDP")
//	public void logInHReck() throws InterruptedException, AutomationFrameworkException, IOException {
//		CommonUtilities.setvaluefromconfig();
//		driver = new DefaultDriverManager().getDriver();
//		driver.get(CommonUtilities.URL);
//		driver.manage().window().maximize();
//		Thread.sleep(2000);
//		System.out.println(CommonUtilities.URL+" "+CommonUtilities.UID+" "+CommonUtilities.PASSWORD);
//		driver.findElement(KeyWordTool.getLocator("logInHReck", "email")).click();
//		driver.findElement(KeyWordTool.getLocator("logInHReck", "email")).clear();
//		driver.findElement(KeyWordTool.getLocator("logInHReck", "email")).sendKeys(CommonUtilities.UID);
//		driver.findElement(KeyWordTool.getLocator("logInHReck", "password")).click();
//		driver.findElement(KeyWordTool.getLocator("logInHReck", "password")).clear();
//		driver.findElement(KeyWordTool.getLocator("logInHReck", "password")).sendKeys(CommonUtilities.PASSWORD);
//		Thread.sleep(1000);
//		driver.findElement(KeyWordTool.getLocator("logInHReck", "LogInButton")).click();
//		System.out.println("User "+CommonUtilities.UID+" loggedIn successfully ");
//		Thread.sleep(1000);
//	}
	
	@Test(priority = 1, dataProvider = "defaultDP")
	public void changePassword() throws AutomationFrameworkException, IOException, InterruptedException {
		CommonUtilities.AccessToHReckURL(true);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(KeyWordTool.getLocator("changePassword", "PreLogOutBotton")).click();
		driver.findElement(KeyWordTool.getLocator("changePassword", "ChPassLink")).click();
		Assert.assertEquals(driver.findElement(KeyWordTool.getLocator("", "ChPassButton")).isEnabled(), false); 
		CommonUtilities.AccessToHReckURL(false);
	}
}
