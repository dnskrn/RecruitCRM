package com.RecruitCRM.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleMapsPage extends BasePage {
	
	public GoogleMapsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private By searchboxinput = By.xpath("//input[@id='searchboxinput']");
	private By searchbutton = By.xpath("//button[@id='searchbox-searchbutton']");
	private By SearchResultOverlay = By.xpath("//div[@class='section-hero-header-title-top-container']");
	private By ResultKeyword = By
			.xpath("//h1[@class='section-hero-header-title-title GLOBAL__gm2-headline-5']//span[1]");
	private By NoOfRating = By
			.xpath("//span[@class='section-rating-term']//span//span//button[@class='widget-pane-link']");

	public WebElement getSearchboxinput() {
		WaitForElementPresence(searchboxinput);
		return getElement(searchboxinput);
	}

	public WebElement getSearchbutton() {
		WaitForElementPresence(searchbutton);
		return getElement(searchbutton);
	}

	public By getSearchResultOverlay() {
		WaitForElementPresence(SearchResultOverlay);
		return SearchResultOverlay;
	}

	public WebElement getResultKeyword() {
		WaitForElementPresence(ResultKeyword);
		return getElement(ResultKeyword);
	}

	public WebElement getNoOfRating() {
		WaitForElementPresence(NoOfRating);
		return getElement(NoOfRating);
	}

}
