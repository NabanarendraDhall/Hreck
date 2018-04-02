package com.HReckAutomation.CommonUtils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class TakeScteenshot {
	public static void getscreenshot( String filename) throws IOException {
//		try {
//			File source = ((TakesScreenshot)CommonUtilities.driver).getScreenshotAs(OutputType.FILE);
//			FileUtils.copyFile(source, new File("/input" + filename + ".png"));
//		} catch (Exception e) {
//			System.out.println("Failed to take screen shot due to: " + e.getMessage());
//		}
		
		try {
			DateFormat df = new SimpleDateFormat("dd_MM_yy_HH_mm_ss");
			Date dateobj = new Date();
			System.out.println(df.format(dateobj));
			File source = ((TakesScreenshot)CommonUtilities.driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("D:\\"+df.format(dateobj)+"\\ " + filename + ".png"));
		} catch (Exception e) {
			System.out.println("Failed to take screen shot due to: " + e.getMessage());
		}
	}
}
