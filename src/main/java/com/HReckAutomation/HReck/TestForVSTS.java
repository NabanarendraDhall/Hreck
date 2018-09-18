package com.HReckAutomation.HReck;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.HReckAutomation.CommonUtils.CommonUtilities;
import com.mentorstudies.automationframework.common.TestClassUtil;
import com.mentorstudies.automationframework.exception.AutomationFrameworkException;
import com.mentorstudies.automationframework.util.impl.DefaultDriverManager;

public class TestForVSTS extends TestClassUtil {
	WebDriver driver;
	@Test
	public void test() throws IOException, AutomationFrameworkException, InterruptedException {
		CommonUtilities.setvaluefromconfig();
		driver = new DefaultDriverManager().getDriver();
		driver.get(CommonUtilities.URL);
		System.out.println("Chrome Opened");
		driver.manage().window().maximize();
		Thread.sleep(1000);
		System.out.println("Test case pass");
		driver.quit();
	}

}
