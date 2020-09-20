package com.RecruitCRM.CommonAnnotations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.RecruitCRM.GlobalObject.GlobalObject;
import com.RecruitCRM.Pages.BasePage;
import com.RecruitCRM.Pages.Pages;
import com.RecruitCRM.Utility.CommonMethod;
import com.RecruitCRM.Utility.WebDriverListener;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import cucumber.api.Scenario;

public class TestBaseRecruitCRM {
	public static FileInputStream ip;
	public static Properties prop;

	public static FileInputStream RecruitCRMip;
	public static Properties RecruitCRMprop;

	public static WebDriver driver;
	public static Pages page;
	// public WebDriverWait wait;
	public static String Browser;
	public static String ExtentreportConfigPath;
	public static EventFiringWebDriver e_driver;
	public static WebDriverListener eventListener;
	public GlobalObject GBObj;
	public static WebDriverWait wait;
	public static Actions action;
	public static JavascriptExecutor js;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentTest test;
	public static ExtentReports report;

	@BeforeSuite
	public void TestDataSetup() {

		try {
			ip = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
			prop = new Properties();
			prop.load(new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties"));

			RecruitCRMip = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/RecruitCRM.properties");
			RecruitCRMprop = new Properties();
			RecruitCRMprop.load(RecruitCRMip);

			GBObj = GlobalObject.getinstance();

			ExtentreportConfigPath = System.getProperty("user.dir") + "/src/test/resources/extent-config.xml";

			Random random  = new Random();
			String Report_Name = "ExtentReport_" + (LocalDate.now().toString() + "_" + random.nextInt()).replace("-", "");

			htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/output/"+Report_Name+".html");
			report = new ExtentReports(); // create object of ExtentReports
			report.attachReporter(htmlReporter);
			
			htmlReporter.config().setDocumentTitle("Automation Report"); // Tittle of Report
			htmlReporter.config().setReportName("Extent Report V4"); // Name of the report
			htmlReporter.config().setTheme(Theme.DARK);// Default Theme of Report
			report.setSystemInfo("Application Name", "Google Test");
			report.setSystemInfo("User Name", "Ankur Jain");
			report.setSystemInfo("Envirnoment", "Production");
			report.setTestRunnerOutput(ExtentreportConfigPath);

			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Parameters("Browser")
	@BeforeClass
	public void InitializeBrowser(String Browser) {

		// Browser = prop.getProperty("Browser");
		System.out.println(Browser);
		String OS = System.getProperty("os.name").substring(0, 3);

		System.setProperty(prop.getProperty(Browser + "_Property"),
				System.getProperty("user.dir") + "/Drivers/" + "/" + OS + "/" + Browser + "driver.exe");

		if (Browser.contentEquals("chrome"))
			driver = new ChromeDriver();
		else if (Browser.contentEquals("gecko")) {
			driver = new FirefoxDriver();
		}
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebDriverListener();
		e_driver.register(eventListener);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		page = new BasePage(driver);
		BasicConfigurator.configure();

		wait = new WebDriverWait(driver, 5);
		action = new Actions(driver);
		js = (JavascriptExecutor) driver;
		
		
	}

	@AfterClass
	public void TearDown() throws InterruptedException {
		/*
		 * BasicConfigurator.resetConfiguration(); if
		 * (Scenario.getStatus().equals("failed")) { try {
		 * GBObj.CM.TakeScreenShot(driver); } catch (IOException e) {
		 * e.printStackTrace(); } }
		 */
		// report.endTest(test);
		report.flush();
		Thread.sleep(1000);
		driver.quit();

	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			// MarkupHelper is used to display the output in different colors
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			test.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));

			// To capture screenshot path and store the path of the screenshot in the string
			// "screenshotPath"
			// We do pass the path captured by this method in to the extent reports using
			// "logger.addScreenCapture" method.

			// String Scrnshot=TakeScreenshot.captuerScreenshot(driver,"TestCaseFailed");
			String screenshotPath = CommonMethod.TakeScreenshot(driver, result.getName());
			// To add it in the extent report

			test.fail("Test Case Failed Snapshot is below " + test.addScreenCaptureFromPath(screenshotPath));

		} else if (result.getStatus() == ITestResult.SKIP) {
			// logger.log(Status.SKIP, "Test Case Skipped is "+result.getName());
			test.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
		}

	}

}
