package com.HReckAutomation.CommonUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.mentorstudies.automationframework.exception.AutomationFrameworkException;
import com.mentorstudies.automationframework.util.common.ConfigReader;
import com.mentorstudies.automationframework.util.impl.DefaultDriverManager;

public class CommonUtilities {
	public static WebDriver driver;

	public static String UID;
	public static String PASSWORD;
	public static String URL;
	public static String logOutUrl;
	public static double currentPayout;
	public static boolean status;

	public static void setvaluefromconfig() throws IOException {

		UID = ConfigReader.getProperty("config.properties", "UID");
		PASSWORD = ConfigReader.getProperty("config.properties", "PASSWORD");
		URL = ConfigReader.getProperty("config.properties", "URL");
		logOutUrl = ConfigReader.getProperty("config.properties", "logOutUrl");

	}

	public static void AccessToHReckURL(boolean status)
			throws IOException, AutomationFrameworkException, InterruptedException {
		if (status == true) {
			CommonUtilities.setvaluefromconfig();
			driver = new DefaultDriverManager().getDriver();
			driver.get(CommonUtilities.URL);
			driver.manage().window().maximize();
			Thread.sleep(2000);
			System.out.println(CommonUtilities.URL + " " + CommonUtilities.UID + " " + CommonUtilities.PASSWORD);
			driver.findElement(By.id("login_username")).click();
			driver.findElement(By.id("login_username")).clear();
			driver.findElement(By.id("login_username")).sendKeys(CommonUtilities.UID);
			driver.findElement(By.id("login_password")).click();
			driver.findElement(By.id("login_password")).clear();
			driver.findElement(By.id("login_password")).sendKeys(CommonUtilities.PASSWORD);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"login\"]/div/div[3]/button")).click();
			System.out.println("User " + CommonUtilities.UID + " loggedIn successfully ");
			Thread.sleep(1000);
		} else {
//			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//			driver.findElement(By.xpath("html/body/hreck-app/app-home/div[1]/div/button")).click();
//			driver.findElement(By.xpath("html/body/hreck-app/app-home/div[1]/div/ul/li[2]/a")).click();
//			System.out.println("User " + CommonUtilities.UID + " loggedOut successfully ");
			driver.quit();

		}
	}
}
