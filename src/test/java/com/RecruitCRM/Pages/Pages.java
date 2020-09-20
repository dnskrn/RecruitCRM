package com.RecruitCRM.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.RecruitCRM.CommonAnnotations.TestBaseRecruitCRM;



public abstract class Pages extends TestBaseRecruitCRM{

	public WebDriver driver;
	public WebDriverWait wait;

	public Pages(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, 15);

	}

	public abstract String getpageTitle();

	public abstract String getpageHeader(By locator);

	public abstract WebElement getElement(By locator);

	public abstract void WaitForElementPresence(By locator);

	public abstract void WaitForPageTitle(String title);

	public <Tpage extends BasePage> Tpage getInstance(Class<Tpage> pageClass) {
		try {
			return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
