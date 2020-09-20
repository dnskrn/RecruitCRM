package com.QATest.RecruitCRM;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.RecruitCRM.CommonAnnotations.TestBaseRecruitCRM;
import com.RecruitCRM.Pages.GoogleMapsPage;
import com.RecruitCRM.Utility.CommonMethod;

public class GogleMapsTest extends TestBaseRecruitCRM {

	@Parameters({ "env" })
	@Test(enabled = true)
	public void GogleMapsTesting(String url) throws InterruptedException, IOException {
		// a. Go to the below URL: https://www.google.com/maps/
		driver.get(url);
		test = report.createTest("Go to the below URL: https://www.google.com/maps/");
	}
	@Test(dependsOnMethods = "GogleMapsTesting")
	public void SearchinGoogleMaps() {
		// b. Search for “Wankhede Stadium” and then click on Maps
		WebElement searchboxinput = page.getInstance(GoogleMapsPage.class).getSearchboxinput();
		WebElement searchbutton = page.getInstance(GoogleMapsPage.class).getSearchbutton();
		
		searchboxinput.sendKeys("Wankhede Stadium");
		searchbutton.click();
		test = report.createTest("Search for “Wankhede Stadium” and then click on Maps");
	}
	@Test(dependsOnMethods = "SearchinGoogleMaps")
	public void TaketheScreenShotofSearchResult() throws IOException, InterruptedException {
		// c. Save the screen shot image at this test execution instant
		CommonMethod.TakeScreenShot(driver);
		System.out.println(page.getInstance(GoogleMapsPage.class).getSearchResultOverlay().toString());
		test = report.createTest("Save the screen shot image at this test execution instant");
	}
	@Test(dependsOnMethods = "TaketheScreenShotofSearchResult")
	public void VerifyTheTextStadium() {
		// d. Verify the Text Present “Stadium” in the left frame
		By SearchResultOverlay=page.getInstance(GoogleMapsPage.class).getSearchResultOverlay();
		
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(SearchResultOverlay));
		WebElement Wanke = page.getInstance(GoogleMapsPage.class).getResultKeyword();
		
		assertTrue(Wanke.getText().contains("Stadium"));
		test = report.createTest("Verify the Text Present “Stadium” in the left frame");
	}
	@Test(dependsOnMethods = "VerifyTheTextStadium")
	public void VerifyTheTitle() {
		// e. Verify the Title “Wankhede Stadium - Google Maps”
		assertTrue(driver.getTitle().equals("Wankhede Stadium - Google Maps"));
		test = report.createTest("Verify the Title “Wankhede Stadium - Google Maps”");
	}
	@Test(dependsOnMethods = "VerifyTheTitle")
	public void PrintTheNoOfRating() {
		// f. Print the ratings point and number of reviews in the console.

		WebElement el = page.getInstance(GoogleMapsPage.class).getNoOfRating();
				System.out.println(el.getText());
				
				test = report.createTest("Print the ratings point and number of reviews in the console.");

	}

}
