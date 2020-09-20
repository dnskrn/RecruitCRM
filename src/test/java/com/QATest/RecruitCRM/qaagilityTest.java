package com.QATest.RecruitCRM;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.RecruitCRM.CommonAnnotations.TestBaseRecruitCRM;
import com.RecruitCRM.Pages.qaagilityPage;

public class qaagilityTest extends TestBaseRecruitCRM{
	@Parameters({ "env" })
	@Test(enabled = true)
	public void qaagilityTesting(String URL) throws InterruptedException, IOException {
		// 1. Open https://www.qaagility.com and write TestNG / WebDriver test for the
		// following using Chrome:

		driver.get(URL);
		test = report.createTest("qaagilityTesting");
	}
	@Test(enabled = true,dependsOnMethods = "qaagilityTesting")
	public void VerifyTitle() {
		// a. The title contains “QAAgility” in it.
		assertTrue(driver.getTitle().contains("QAAgility"));
		test = report.createTest("Verify Title");
	}
	@Test(enabled = true ,dependsOnMethods = "qaagilityTesting")
	public void GetTheSizeofLogo() {
		// b. Get the size attributes for the QAAgility logo on top left corner
		WebElement Logo = page.getInstance(qaagilityPage.class).getLogo();

		System.out.println("Width of the Logo is :"+Logo.getAttribute("naturalWidth"));
		System.out.println("Height of the Logo is :"+Logo.getAttribute("naturalHeight"));
		test = report.createTest("Get The Size of Logo");
	}
	
	@Test(enabled = true,dependsOnMethods = "qaagilityTesting")
	public void VerifyTwitterAndFooterText() {
		// c. Verify that Twitter” button is present at the top right. Ensure that the
		// footer text is “QAAgility Technologies Pvt. Ltd. © 2018. All Rights
		// Reserved.”

		WebElement Twitter = page.getInstance(qaagilityPage.class).getTwitter();
		WebElement FooterText = page.getInstance(qaagilityPage.class).getFooterText();

		assertTrue(Twitter.isDisplayed());
		assertTrue(FooterText.getText().contains("QAAgility Technologies © 2020. All Rights Reserved"));
		
		test = report.createTest("Verify Twitter And Footer Text");
		
	}


}
