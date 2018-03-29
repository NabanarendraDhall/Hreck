package com.HReckAutomation.CommonUtils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class TakeScteenshot {
	public static void getscreenshot( String filename) throws IOException {
		try {
			File source = ((TakesScreenshot)CommonUtilities.driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("/input" + filename + ".png"));
		} catch (Exception e) {
			System.out.println("Failed to take screen shot due to: " + e.getMessage());
		}
	}
}
