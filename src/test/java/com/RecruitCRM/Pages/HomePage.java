package com.RecruitCRM.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.RecruitCRM.Pages.BasePage;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private By Women=By.xpath("//a[@title='Women']");
	private By Evening_Dresses=By.xpath("//a[@title='Evening Dresses']");
	private By SizeM=By.xpath("//input[@name='layered_id_attribute_group_2']");
	private By ColorPink=By.xpath("//input[@name='layered_id_attribute_group_24']");
	private By rangeID=By.xpath("//span[@id='layered_price_range']");
	private By Slidder=By.xpath("//div[@id='left_column']//a[2]");
	
	public WebElement getSlidder() {
		WaitForElementPresence(Slidder);
		return getElement(Slidder);
	}

	public WebElement getWomen() {
		WaitForElementPresence(Women);
		return getElement(Women);
	}

	public WebElement getEvening_Dresses() {
		WaitForElementPresence(Evening_Dresses);
		return getElement(Evening_Dresses);
	}

	public WebElement getSizeM() {
		WaitForElementPresence(SizeM);
		return getElement(SizeM);
	}

	public WebElement getColorPink() {
		WaitForElementPresence(ColorPink);
		return getElement(ColorPink);
	}

	public WebElement getRangeID() {
		WaitForElementPresence(rangeID);
		return getElement(rangeID);
	}
	
	
}
