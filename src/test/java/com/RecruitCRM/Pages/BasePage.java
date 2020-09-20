package com.RecruitCRM.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePage extends Pages {

	public BasePage(WebDriver driver) {
		super(driver);

	}

	@Override
	public String getpageTitle() {
		return driver.getTitle();
	}

	@Override
	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
			element = driver.findElement(locator);
			return element;
		} catch (Exception e) {
			System.out.println("Some Error Occured during Element Creation " + locator.toString());
			e.printStackTrace();
		}
		return element;
	}

	@Override
	public void WaitForElementPresence(By locator) {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));

		} catch (Exception e) {
			System.out.println("Exception Occured while Waiting for The Element  : " + locator.toString());
		}
	}

	@Override
	public void WaitForPageTitle(String title) {
		try {
			wait.until(ExpectedConditions.titleContains(title));

		} catch (Exception e) {
			System.out.println("Exception Occured while Waiting for The Element  : " + title.toString());
		}

	}

	@Override
	public String getpageHeader(By locator) {

		return getElement(locator).getText();
	}

}
