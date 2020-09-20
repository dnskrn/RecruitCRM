package com.RecruitCRM.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class qaagilityPage extends BasePage {

	public qaagilityPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private By Logo = By.xpath("//div[@class='sticky-navigation-logo']//a//img");
	private By Twitter = By.xpath("//i[@class='fab fa-twitter']");
	private By FooterText = By.xpath("//div[@class='copyright-bar']");
	
	public WebElement getLogo() {
		WaitForElementPresence(Logo);
		return getElement(Logo);
	}
	
	public WebElement getTwitter() {
		WaitForElementPresence(Twitter);
		return getElement(Twitter);
	}
	
	public WebElement getFooterText() {
		WaitForElementPresence(FooterText);
		return getElement(FooterText);
	}
}
