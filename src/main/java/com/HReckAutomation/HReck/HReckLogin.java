package com.HReckAutomation.HReck;
/*
 * 
 * Author : Nabanarendra Dhall
 */
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
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
import com.mentorstudies.automationframework.util.impl.DefaultDriverManager;


public class HReckLogin extends TestClassUtil {
	WebDriver driver;
	String actual, expected;
	/*
	 * openBrowser will open the configured URL as per config.properties
	 */
	@BeforeClass()
	public void openBrowser() throws InterruptedException, AutomationFrameworkException, IOException {
		CommonUtilities.setvaluefromconfig();
		driver = new DefaultDriverManager().getDriver();
		driver.get(CommonUtilities.URL);
		System.out.println("Chrome Opened");
		driver.manage().window().maximize();
		Thread.sleep(1000);
	}
	/*
	 * This test case will check all the possible tests for login along with success case.
	 * 
	 */
	@Test(priority = 0, dataProvider = "defaultDP")
	public void hReckLogin(String x, String y, String z) throws InterruptedException, AutomationFrameworkException {
		try {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			String loginPageURL = driver.getCurrentUrl();
			System.out.println(loginPageURL);
			driver.findElement(KeyWordTool.getLocator("hReckLogin", "email")).click();
			driver.findElement(KeyWordTool.getLocator("hReckLogin", "email")).clear();
			driver.findElement(KeyWordTool.getLocator("hReckLogin", "email")).sendKeys(x);
			Thread.sleep(2000);
			driver.findElement(KeyWordTool.getLocator("hReckLogin", "password")).click();
			driver.findElement(KeyWordTool.getLocator("hReckLogin", "password")).clear();
			driver.findElement(KeyWordTool.getLocator("hReckLogin", "password")).sendKeys(y);
			Thread.sleep(2000);
			String handle = driver.getWindowHandle();
			driver.findElement(KeyWordTool.getLocator("hReckLogin", "LogInButton")).click();
			Thread.sleep(2000);
			String afterLoginPageURL = driver.getCurrentUrl();
			System.out.println(afterLoginPageURL);
			if (loginPageURL.equals(afterLoginPageURL)) {
				System.out.println("Entered If");
				Set handles = driver.getWindowHandles();
				for (String handle1 : driver.getWindowHandles()) {
					driver.switchTo().window(handle1);
					Thread.sleep(2000);
					String ErrorMessage = driver.findElement(KeyWordTool.getLocator("hReckLogin", "popUpErrorMessage"))
							.getText();
					driver.findElement(KeyWordTool.getLocator("hReckLogin", "PopUpOkButton"));
					Assert.assertEquals(ErrorMessage, z, "Test case failed");
					driver.navigate().refresh();
				}
			} else {
				System.out.println("Entered Else");
				String dashBoardPgeURL = driver.getCurrentUrl();
				System.out.println("After Login URL are : "+dashBoardPgeURL);
				Assert.assertEquals(dashBoardPgeURL, z, "Test case passed");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * The test case will verify the steps to logout. The test case is totally depend on login method
	 */
	@Test(dependsOnMethods="hReckLogin", dataProvider = "defaultDP")
	public void hReckLogout() throws AutomationFrameworkException, InterruptedException {
		Thread.sleep(2000);
		String dashBoardPageURL = driver.getCurrentUrl();
		driver.findElement(KeyWordTool.getLocator("hReckLogout", "PreLogOutBotton")).click();
		driver.findElement(KeyWordTool.getLocator("hReckLogout", "logOutButton")).click();
		Thread.sleep(2000);
		Assert.assertNotEquals(dashBoardPageURL, driver.getCurrentUrl());
		System.out.println();
	}

	@AfterClass()
	public void tearBrowser() {
		driver.close();
	}
	@AfterMethod()
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			TakeScteenshot.getscreenshot( "HReckLogin" + System.currentTimeMillis());
		}
	}
}
