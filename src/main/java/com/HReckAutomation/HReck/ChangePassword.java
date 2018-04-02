package com.HReckAutomation.HReck;

/*
 * 
 * 
 * Author : Nabanarendra Dhall
 * 
 * 
 */
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.HReckAutomation.CommonUtils.CommonUtilities;
import com.HReckAutomation.CommonUtils.TakeScteenshot;
import com.mentorstudies.automationframework.common.TestClassUtil;
import com.mentorstudies.automationframework.exception.AutomationFrameworkException;
import com.mentorstudies.automationframework.util.common.KeyWordTool;

public class ChangePassword extends TestClassUtil {
	/*
	 *
	 * OpenBrowser method will open browser and URL which is configured from
	 * config.proporties Then It will login to HRech site with the UID and Password
	 * which is given from Config.Proporties
	 */


	@BeforeClass
	public void openBrowser() throws IOException, AutomationFrameworkException, InterruptedException {
		CommonUtilities.AccessToHReckURL(true);
	}

	/*
	 * After class will quit browser once all test cases executed in the same class
	 * 
	 */
	@AfterClass
	public void quitBrowser() throws IOException, AutomationFrameworkException, InterruptedException {
		CommonUtilities.AccessToHReckURL(false);
	}
	@Test(priority = 1, dataProvider = "defaultDP")
	public void changePassword() throws AutomationFrameworkException, IOException, InterruptedException {
		CommonUtilities.AccessToHReckURL(false);
		CommonUtilities.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("changePassword", "PreLogOutBotton")).click();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("changePassword", "ChPassLink")).click();
		Assert.assertEquals(CommonUtilities.driver.findElement(KeyWordTool.getLocator("changePassword", "ChPassButton")).isEnabled(), false); 

	}

	/*
	 * buttonChk method will check weather the change password button is disable
	 * initially or not.
	 */
	@Test(priority = 0, dataProvider = "defaultDP")
	public void buttonChk() throws AutomationFrameworkException, IOException, InterruptedException {
		try {
			Thread.sleep(2000);
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("buttonChk", "PreLogOutBotton")).click();
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("buttonChk", "ChPassLink")).click();
			Thread.sleep(2000);
			Assert.assertEquals(
					CommonUtilities.driver.findElement(KeyWordTool.getLocator("buttonChk", "ChPassButton")).isEnabled(),
					false);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/*
	 * The chPassMain1 test case will check the front end validation of the change
	 * password and will verify the error message.
	 */
	@Test(priority = 1, dataProvider = "defaultDP")
	public void chPassMain1(String x, String y, String z, String a) {
		try {
			Thread.sleep(2000);
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain1", "PreLogOutBotton")).click();
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain1", "ChPassLink")).click();
			Thread.sleep(1000);
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain1", "OldPassField")).click();
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain1", "OldPassField")).clear();
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain1", "OldPassField")).sendKeys(x);
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain1", "newPassField")).click();
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain1", "newPassField")).clear();
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain1", "newPassField")).sendKeys(y);
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain1", "confirmPassField")).click();
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain1", "confirmPassField")).clear();
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain1", "confirmPassField")).sendKeys(z);
			String error1 = CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain1", "chPassErr1"))
					.getText();
			System.out.println(error1 + "///" + a);
			Assert.assertEquals(error1, a, "Test case passed");
			Assert.assertEquals(CommonUtilities.driver
					.findElement(KeyWordTool.getLocator("chPassMain1", "ChPassButton")).isEnabled(), false);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/*
	 * The test case will check whether the new password and confirm password are
	 * same or not.
	 */
	@Test(priority = 2, dataProvider = "defaultDP")
	public void chPassMain2(String x, String y, String z, String a) {
		try {
			Thread.sleep(2000);
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain2", "PreLogOutBotton")).click();
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain2", "ChPassLink")).click();
			Thread.sleep(1000);
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain2", "OldPassField")).click();
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain2", "OldPassField")).clear();
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain2", "OldPassField")).sendKeys(x);
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain2", "newPassField")).click();
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain2", "newPassField")).clear();
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain2", "newPassField")).sendKeys(y);
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain2", "confirmPassField")).click();
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain2", "confirmPassField")).clear();
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain2", "confirmPassField")).sendKeys(z);
			String error2 = CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain2", "chPassErr2"))
					.getText();
			System.out.println(error2 + "///" + a);
			Assert.assertEquals(error2, a, "Test case passed");
			Assert.assertEquals(CommonUtilities.driver
					.findElement(KeyWordTool.getLocator("chPassMain2", "ChPassButton")).isEnabled(), false);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/*
	 * The test case will verify two conditions 1: If old entered password is wrong
	 * and 2: success case
	 */
	@Test(priority = 3, dataProvider = "defaultDP")
	public void chPassMain(String x, String y, String z, String a)
			throws IOException, AutomationFrameworkException, InterruptedException {
		try {
			Thread.sleep(2000);
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain", "PreLogOutBotton")).click();
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain", "ChPassLink")).click();
			Thread.sleep(1000);
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain", "OldPassField")).click();
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain", "OldPassField")).clear();
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain", "OldPassField")).sendKeys(x);
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain", "newPassField")).click();
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain", "newPassField")).clear();
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain", "newPassField")).sendKeys(y);
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain", "confirmPassField")).click();
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain", "confirmPassField")).clear();
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain", "confirmPassField")).sendKeys(z);
			String handle = CommonUtilities.driver.getWindowHandle();
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain", "ChPassButton")).click();
			Set handles = CommonUtilities.driver.getWindowHandles();
			for (String handle1 : CommonUtilities.driver.getWindowHandles()) {
				CommonUtilities.driver.switchTo().window(handle1);
				Thread.sleep(5000);
				String ErrorMessage = CommonUtilities.driver
						.findElement(KeyWordTool.getLocator("chPassMain", "chPassErr3")).getText();
				System.out.println(ErrorMessage + "////" + a);
				CommonUtilities.driver.findElement(KeyWordTool.getLocator("chPassMain", "PopUpOkButton")).click();
				Assert.assertEquals(ErrorMessage, a, "Test case failed");
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	@AfterMethod()
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			TakeScteenshot.getscreenshot( "ChangePassHreck" + System.currentTimeMillis());
		}
	}
}

