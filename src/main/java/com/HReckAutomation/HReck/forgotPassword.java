package com.HReckAutomation.HReck;
/*
 * Author : Nabanarendra Dhall
 */
import java.awt.AWTException;
import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.By;
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

public class forgotPassword extends TestClassUtil

{
	static int linkCount = 0;
	static String mailContent = "";
	static String matchFor = "Password:";
	static String myPass = "";
/*
 * This method will open browser and will navigate to the home page as before the test open
 */
	@BeforeClass()
	public void openBrowser() throws IOException, AutomationFrameworkException {
		CommonUtilities.openBrowser(true);
		CommonUtilities.driver.manage().deleteAllCookies();
	}
/*
 * This method will close browser and will execute after the test cases in the class over.
 */
	@AfterClass()
	public void closeBrowser() throws IOException, AutomationFrameworkException {
		CommonUtilities.openBrowser(false);
	}
/*
 * The below test case will check all possible conditions for forgot password. 
 */
	@Test(priority = 0, dataProvider = "defaultDP")
	public void forgotPasswordMain(String p, String q)
			throws AutomationFrameworkException, IOException, InterruptedException, AWTException {
		CommonUtilities.driver.get(CommonUtilities.URL);
		String currentUrl = CommonUtilities.driver.getCurrentUrl();
		Thread.sleep(2000);
		CommonUtilities.driver.findElement(By.xpath("//a[@href=\'#/forgotpassword\']")).click();
		Thread.sleep(2000);
		String ForgotPassPage = CommonUtilities.driver.getCurrentUrl();
		Assert.assertNotEquals(currentUrl, ForgotPassPage);
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("forgotPasswordMain", "emailform")).click();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("forgotPasswordMain", "emailform")).clear();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("forgotPasswordMain", "emailform")).sendKeys(p);
		String handle = CommonUtilities.driver.getWindowHandle();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("forgotPasswordMain", "forgotPassSubmit")).click();
		System.out.println("Clicked on submit");
		Thread.sleep(2000);
		Set handles = CommonUtilities.driver.getWindowHandles();
		for (String handle1 : CommonUtilities.driver.getWindowHandles()) {
			CommonUtilities.driver.switchTo().window(handle1);
			Thread.sleep(2000);
			String ErrorMessage = CommonUtilities.driver
					.findElement(KeyWordTool.getLocator("forgotPasswordMain", "popUpErrorMessage")).getText();
			System.out.println(ErrorMessage);
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("forgotPasswordMain", "PopUpOkButton")).click();
			Assert.assertEquals(ErrorMessage, q, "Test case passed");
			Thread.sleep(2000);
			/*
			 * Checking for success case of forgot password
			 */
			if (ErrorMessage.equals("New password is generated and email is sent to registered email address.")) {
				/*
				 * gets the new password by reading Gmail in java.
				 */
				String mailContent = SendMail.readGmail("upavptechease@gmail.com", "Tes@1234", "Your ‘Shree Jayshar’ New Password");
				int index = mailContent.indexOf("Password:");
				String myPass = mailContent.substring(index + 10, index + 18);
				System.out.println(myPass);
				CommonUtilities.driver.findElement(By.linkText("Go To Login Page")).click();
									CommonUtilities.driver
											.findElement(KeyWordTool.getLocator("forgotPasswordMain", "email"))
											.sendKeys("Jagdish");
									CommonUtilities.driver
											.findElement(KeyWordTool.getLocator("forgotPasswordMain", "password"))
											.sendKeys(myPass.trim());
									System.out.println("Inserted password is : " + myPass.trim());
									Thread.sleep(1000);
									CommonUtilities.driver
											.findElement(KeyWordTool.getLocator("forgotPasswordMain", "LogInButton"))
											.click();
									Thread.sleep(2000);
									String actual = CommonUtilities.driver.getCurrentUrl();
									String expected = "http://hreck.techeasesystems.in/#/change-password";
									Assert.assertEquals(actual, expected);
								}
							}
						}
	
	@AfterMethod()
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
		if (testResult.getStatus() == ITestResult.FAILURE) {
//			SendMail.mail();
			TakeScteenshot.getscreenshot( "ForgotPassHreck" + System.currentTimeMillis());
		}
	}
}
