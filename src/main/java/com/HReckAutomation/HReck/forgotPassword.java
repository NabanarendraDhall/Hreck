package com.HReckAutomation.HReck;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.HReckAutomation.CommonUtils.CommonUtilities;
import com.mentorstudies.automationframework.common.TestClassUtil;
import com.mentorstudies.automationframework.exception.AutomationFrameworkException;
import com.mentorstudies.automationframework.util.common.KeyWordTool;

public class forgotPassword extends TestClassUtil

{
	static int linkCount = 0;
	static String mailContent = "";
	static String matchFor = "Password:";
	static String myPass = "";

	@BeforeClass()
	public void openBrowser() throws IOException, AutomationFrameworkException {
		CommonUtilities.openBrowser(true);
		CommonUtilities.driver.manage().deleteAllCookies();
	}
	
	@AfterClass
	public void closeBrowser() throws IOException, AutomationFrameworkException {
		CommonUtilities.openBrowser(false);
	}

	@Test(priority = 0, dataProvider = "defaultDP")
	public void forgotPasswordMain(String p, String q)
			throws AutomationFrameworkException, IOException, InterruptedException, AWTException {
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
			CommonUtilities.driver.navigate().refresh();
			Assert.assertEquals(ErrorMessage, q, "Test case passed");
			Thread.sleep(2000);
			if (ErrorMessage.equals("New password is generated and email is sent to registered email address."))
				;
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_T);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			// robot.keyRelease(KeyEvent.VK_T);
			Thread.sleep(5000);
			CommonUtilities.driver.get("https://mail.google.com/");
			Thread.sleep(2000);
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("forgotPasswordMain", "gmailUsername"))
					.sendKeys("upavptechease@gmail.com");
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("forgotPasswordMain", "gmailusernameNext"))
					.click();
			Thread.sleep(5000);
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("forgotPasswordMain", "gmailPassword"))
					.sendKeys("Tes@1234");
			Thread.sleep(5000);
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("forgotPasswordMain", "gmailpasswordNext"))
					.click();
			Thread.sleep(3000);
			List<WebElement> x = CommonUtilities.driver
					.findElements(KeyWordTool.getLocator("forgotPasswordMain", "inbox"));

			Thread.sleep(5000);
			System.out.println(x.size());
			for (int i = 0; i < x.size(); i++) {
				System.out.println(x.get(i).getText());

				if (x.get(i).getText().equals("HRECK")) {
					x.get(i).click();

					CommonUtilities.driver.navigate().refresh();
					Thread.sleep(3000);

					for (WebElement links : CommonUtilities.driver.findElements(By.tagName("p"))) {
						mailContent = links.getText();
						Thread.sleep(1000);
						System.out.println(mailContent);
						String abc[] = mailContent.split(" ");
						Thread.sleep(1000);
						for (int j = 0; j < abc.length; j++) {
							if (abc[j].equals(matchFor)) {
								myPass = abc[j + 1];
								System.out.println(myPass);


								CommonUtilities.driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "w");
								System.out.println("Email tab closed");
								Thread.sleep(5000);
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
								String actual=CommonUtilities.driver.getCurrentUrl();
								String expected="http://hreck.techeasesystems.in/#/change-password";
								Assert.assertEquals(actual, expected);
							}
						}
					}
				}
			}
		}

	}
}
