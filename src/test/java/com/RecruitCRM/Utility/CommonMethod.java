package com.RecruitCRM.Utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.cucumber.listener.Reporter;

public class CommonMethod {

	public static CommonMethod CommonMethod_Instance;

	private CommonMethod() {

	}

	public static CommonMethod getinstance() {

		if (CommonMethod_Instance == null) {
			CommonMethod_Instance = new CommonMethod();
		}

		return CommonMethod_Instance;
	}

	public static  void TakeScreenShot(WebDriver driver) throws IOException, InterruptedException {
		Thread.sleep(1500);
		Random random  = new Random();
		String SS_Name = "SS_" + (LocalDate.now().toString() + "_" + random.nextInt()).replace("-", "");
		String SS_Path=System.getProperty("user.dir") + "\\ScreenShots\\" + SS_Name + ".png";
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(SS_Path));
		

		
	}

	public void switchtowindow(WebDriver driver, String WindTitle) {

		Set<String> WinId = driver.getWindowHandles();

		for (String str : WinId) {
			System.out.println(str);
			driver.switchTo().window(str);
			System.out.println(driver.getTitle());

		}
	}
	
	public static String TakeScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
			
		// after execution, you could see a folder "FailedTestsScreenshots" under src
		// folder
		String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

}
