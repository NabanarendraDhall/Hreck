package com.HReckAutomation.Masters;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
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

public class AddCostCentre extends TestClassUtil {
	String ErrorMessage;

	@BeforeClass
	public void openBrowser() throws IOException, AutomationFrameworkException, InterruptedException {
		CommonUtilities.AccessToHReckURL(true);
	}

	@AfterClass
	public void closeBrowser() throws IOException, AutomationFrameworkException, InterruptedException {
		CommonUtilities.AccessToHReckURL(false);
	}

	@Test(priority = 0, dataProvider = "defaultDP")
	public void goToCostCentre() throws AutomationFrameworkException, InterruptedException {

		CommonUtilities.driver.findElement(KeyWordTool.getLocator("goToCostCentre", "masters")).click();
		Thread.sleep(1000);
		WebElement element = CommonUtilities.driver.findElement(KeyWordTool.getLocator("goToCostCentre", "costCentre"));
		Actions action = new Actions(CommonUtilities.driver);
		action.moveToElement(element).build().perform();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("goToCostCentre", "addCostCentre")).click();
		Thread.sleep(1000);
		String actualUrl = CommonUtilities.driver.getCurrentUrl();
		String Expected = "http://hreck.techeasesystems.in/#/cost-centre-master";
		Assert.assertEquals(actualUrl, Expected);
	}

	@Test(dependsOnMethods = "goToCostCentre", dataProvider = "defaultDP")
	public void checkCostCentreEmpty(String costCentreameError, String costCentreCodeError, String costCentreadd1Error,
			String costCentreAdd2Error, String costCentreAdd3Error, String costcentreStateError,
			String costCentreCityError, String costCentrePinError)
			throws AutomationFrameworkException, InterruptedException {
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("checkCostCentreEmpty", "costCentreSubmit")).click();
		Thread.sleep(1000);
		String ccne = CommonUtilities.driver
				.findElement(KeyWordTool.getLocator("checkCostCentreEmpty", "costCentreNameError")).getText();
		System.out.println(ccne + "//" + costCentreameError);
		Assert.assertEquals(ccne, costCentreameError);
		String ccce = CommonUtilities.driver
				.findElement(KeyWordTool.getLocator("checkCostCentreEmpty", "costCentreCodeError")).getText();
		System.out.println(ccce + "//" + costCentreCodeError);
		Assert.assertEquals(ccce, costCentreCodeError);
		String ccae1 = CommonUtilities.driver
				.findElement(KeyWordTool.getLocator("checkCostCentreEmpty", "costCentreadd1Error")).getText();
		System.out.println(ccae1 + "//" + costCentreadd1Error);
		Assert.assertEquals(ccae1, costCentreadd1Error);
		String ccae2 = CommonUtilities.driver
				.findElement(KeyWordTool.getLocator("checkCostCentreEmpty", "costCentreAdd2Error")).getText();
		System.out.println(ccae2 + "//" + costCentreAdd2Error);
		Assert.assertEquals(ccae2, costCentreAdd2Error);
		String ccae3 = CommonUtilities.driver
				.findElement(KeyWordTool.getLocator("checkCostCentreEmpty", "costCentreAdd3Error")).getText();
		System.out.println(ccae3 + "//" + costCentreAdd3Error);
		Assert.assertEquals(ccae3, costCentreAdd3Error);
		String ccSe = CommonUtilities.driver
				.findElement(KeyWordTool.getLocator("checkCostCentreEmpty", "costcentreStateError")).getText();
		System.out.println(ccSe + "//" + costcentreStateError);
		Assert.assertEquals(ccSe, costcentreStateError);
		String ccCE = CommonUtilities.driver
				.findElement(KeyWordTool.getLocator("checkCostCentreEmpty", "costCentreCityError")).getText();
		System.out.println(ccCE + "//" + costCentreCityError);
		Assert.assertEquals(ccCE, costCentreCityError);
		String ccpe = CommonUtilities.driver
				.findElement(KeyWordTool.getLocator("checkCostCentreEmpty", "costCentrePinError")).getText();
		System.out.println(ccpe + "//" + costCentrePinError);
		Assert.assertEquals(ccpe, costCentrePinError);
	}

	@Test(priority = 6, dataProvider = "defaultDP")
	public void costCentreName(String content, int size) throws AutomationFrameworkException, InterruptedException {
		Thread.sleep(1000);
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreName", "costCentreName")).clear();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreName", "costCentreName"))
				.sendKeys(content);
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreName", "costCentreSubmit")).click();
		Thread.sleep(1000);
		String nameContent = CommonUtilities.driver
				.findElement(KeyWordTool.getLocator("costCentreName", "costCentreName")).getAttribute("value");
		int expected = nameContent.length();
		System.out.println("costCentreName :" + expected + " |" + size);
		Assert.assertEquals(size, expected);
	}

	@Test(priority = 1, dataProvider = "defaultDP")
	public void costCentreCode(String content, int size) throws AutomationFrameworkException, InterruptedException {
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreCode", "costCentreCode")).clear();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreCode", "costCentreCode"))
				.sendKeys(content);
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreCode", "costCentreSubmit")).click();
		Thread.sleep(1000);
		String nameContent = CommonUtilities.driver
				.findElement(KeyWordTool.getLocator("costCentreCode", "costCentreCode")).getAttribute("value");
		int expected = nameContent.length();
		System.out.println("costCentreCode :" + expected + "//" + size);
		Assert.assertEquals(size, expected);
	}

	@Test(priority = 2, dataProvider = "defaultDP")
	public void costCentreAdd1(String content, int size) throws AutomationFrameworkException, InterruptedException {
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreAdd1", "costCentreAdd1")).clear();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreAdd1", "costCentreAdd1"))
				.sendKeys(content);
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreAdd1", "costCentreSubmit")).click();
		Thread.sleep(1000);
		String nameContent = CommonUtilities.driver
				.findElement(KeyWordTool.getLocator("costCentreAdd1", "costCentreAdd1")).getAttribute("value");
		int expected = nameContent.length();
		System.out.println("costCentreAdd1 :" + expected + "//" + size);
		Assert.assertEquals(size, expected);
	}

	@Test(priority = 3, dataProvider = "defaultDP")
	public void costCentreAdd2(String content, int size) throws AutomationFrameworkException, InterruptedException {
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreAdd2", "costCentreAdd2")).clear();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreAdd2", "costCentreAdd2"))
				.sendKeys(content);
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreAdd2", "costCentreSubmit")).click();
		Thread.sleep(1000);
		String nameContent = CommonUtilities.driver
				.findElement(KeyWordTool.getLocator("costCentreAdd2", "costCentreAdd2")).getAttribute("value");
		int expected = nameContent.length();
		System.out.println("costCentreAdd2 :" + expected + "//" + size);
		Assert.assertEquals(size, expected);
	}

	@Test(priority = 4, dataProvider = "defaultDP")
	public void costCentreAdd3(String content, int size) throws AutomationFrameworkException, InterruptedException {
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreAdd3", "costCentreAdd3")).clear();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreAdd3", "costCentreAdd3"))
				.sendKeys(content);
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreAdd3", "costCentreSubmit")).click();
		Thread.sleep(1000);
		String nameContent = CommonUtilities.driver
				.findElement(KeyWordTool.getLocator("costCentreAdd3", "costCentreAdd3")).getAttribute("value");
		int expected = nameContent.length();
		System.out.println("costCentreAdd3 :" + expected + "//" + size);
		Assert.assertEquals(size, expected);
	}

	@Test(priority = 5, dataProvider = "defaultDP")
	public void costCentrePin(String content, String error) throws AutomationFrameworkException, InterruptedException {
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentrePin", "costCentrePin")).clear();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentrePin", "costCentrePin")).sendKeys(content);
		Thread.sleep(1000);
		String ccpe = CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentrePin", "costCentrePinError"))
				.getText();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentrePin", "costCentreSubmit")).click();
		System.out.println("costCentrePin :" + error + "//" + ccpe);
		Assert.assertEquals(error, ccpe);
	}

	@Test(priority = 7, dataProvider = "defaultDP")
	public void costCentreSuccess(String name, String code, String address1, String address2, String address3,
			String state, String city, String pin, String successMessage, String nameDuplicateMessage,
			String codeDuplicateMessage) throws AutomationFrameworkException, InterruptedException {
		Random rand = new Random();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreSuccess", "costCentreName")).clear();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreSuccess", "costCentreName"))
				.sendKeys(name);
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreSuccess", "costCentreCode")).clear();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreSuccess", "costCentreCode"))
				.sendKeys(code);
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreSuccess", "costCentreAdd1")).clear();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreSuccess", "costCentreAdd1"))
				.sendKeys(address1);
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreSuccess", "costCentreAdd2")).clear();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreSuccess", "costCentreAdd2"))
				.sendKeys(address2);
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreSuccess", "costCentreAdd3")).clear();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreSuccess", "costCentreAdd3"))
				.sendKeys(address3);
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreSuccess", "stateDropDown")).click();
		Select select = new Select(
				CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreSuccess", "stateDropDown")));
		select.selectByVisibleText(state);
		Thread.sleep(2000);
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreSuccess", "cityDropDown")).click();
		Select select1 = new Select(
				CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreSuccess", "cityDropDown")));
		select1.selectByVisibleText(city);
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreSuccess", "costCentrePin")).clear();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreSuccess", "costCentrePin")).sendKeys(pin);
		String handle = CommonUtilities.driver.getWindowHandle();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreSuccess", "costCentreSubmit")).click();
		Thread.sleep(2000);
		Set handles = CommonUtilities.driver.getWindowHandles();
		for (String handle1 : CommonUtilities.driver.getWindowHandles()) {
			CommonUtilities.driver.switchTo().window(handle1);
			Thread.sleep(2000);
			ErrorMessage = CommonUtilities.driver
					.findElement(KeyWordTool.getLocator("costCentreSuccess", "popUpErrorMessage")).getText();
			CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreSuccess", "PopUpOkButton")).click();
			do {

				if (ErrorMessage.equals(nameDuplicateMessage)) {
					System.out.println("Erooe message is : " + ErrorMessage);
					CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreSuccess", "costCentreName"))
							.clear();
					CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreSuccess", "costCentreName"))
							.sendKeys(name + rand.nextInt());
					CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreSuccess", "costCentreSubmit"))
							.click();
					Thread.sleep(2000);
					Set handles2 = CommonUtilities.driver.getWindowHandles();
					for (String handle2 : CommonUtilities.driver.getWindowHandles()) {
						CommonUtilities.driver.switchTo().window(handle2);
						Thread.sleep(2000);
						ErrorMessage = CommonUtilities.driver
								.findElement(KeyWordTool.getLocator("costCentreSuccess", "popUpErrorMessage"))
								.getText();
						CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreSuccess", "PopUpOkButton"))
								.click();

					}
				}
				if (ErrorMessage.equals(codeDuplicateMessage)) {
					System.out.println("Msg in codeduplicate: " + ErrorMessage);
					CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreSuccess", "costCentreCode"))
							.clear();
					CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreSuccess", "costCentreCode"))
							.sendKeys("1" + rand.nextInt());
					Thread.sleep(2000);
					CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreSuccess", "costCentreSubmit"))
							.click();
					Thread.sleep(2000);
					Set handles3 = CommonUtilities.driver.getWindowHandles();
					for (String handle3 : CommonUtilities.driver.getWindowHandles()) {
						CommonUtilities.driver.switchTo().window(handle3);
						Thread.sleep(2000);
						ErrorMessage = CommonUtilities.driver
								.findElement(KeyWordTool.getLocator("costCentreSuccess", "popUpErrorMessage"))
								.getText();
						CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreSuccess", "PopUpOkButton"))
								.click();
						// CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreSuccess","costCentreSubmit")).click();
						Thread.sleep(2000);
						System.out.println("Final EM: " + ErrorMessage);
					}
				}

			} while (successMessage.equals(ErrorMessage));
			System.out.println("Error123 message: " + ErrorMessage);
			System.out.println(ErrorMessage + "|" + successMessage);
			Assert.assertEquals(ErrorMessage, successMessage, "Test case passed");
		}
	}

	@Test(priority = 8, dataProvider = "defaultDP")
	public void costCentreVerify(String name1) throws AutomationFrameworkException, InterruptedException {
		Thread.sleep(1000);
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreVerify", "masters")).click();
		Thread.sleep(1000);
		WebElement element = CommonUtilities.driver
				.findElement(KeyWordTool.getLocator("costCentreVerify", "costCentre"));
		Actions action = new Actions(CommonUtilities.driver);
		action.moveToElement(element).build().perform();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreVerify", "viewCostCentre")).click();
		Thread.sleep(1000);
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("costCentreVerify", "costCentreFilter"))
				.sendKeys(name1);
		Thread.sleep(1000);
		WebElement htmltable = CommonUtilities.driver
				.findElement(KeyWordTool.getLocator("costCentreVerify", "nameInTheTable"));
		List<WebElement> names = htmltable.findElements(By.tagName("tr"));
		for (int i = 1; i <= names.size(); i++) {
			String s = CommonUtilities.driver.findElement(By.xpath(
					"html/body/hreck-app/app-home/ng-component/div[2]/div/div/div/div/table/tbody/tr[" + i + "]/td[2]"))
					.getText();
			System.out.println(i + ": " + s);
			if (s.equals(name1)) {
				Assert.assertEquals(name1, s);
			}
		}
	}

	@AfterMethod()
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			TakeScteenshot.getscreenshot("AddCostCentre" + System.currentTimeMillis());
			// SendMail.mail();
		}
	}
}
