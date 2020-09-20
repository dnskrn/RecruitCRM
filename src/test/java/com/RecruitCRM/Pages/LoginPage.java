package com.RecruitCRM.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.RecruitCRM.Pages.BasePage;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private By LoginTab=By.xpath("//a[@class='login']");
	private By Email=By.xpath("//input[@id='email']");
	private By Password = By.xpath("//input[@id='passwd']");
	private By LogiButton= By.xpath("//button[@id='SubmitLogin']//span");
	
	public WebElement getLoginTab() {
		WaitForElementPresence(LoginTab);
		return getElement(LoginTab);
	}
	
	public WebElement getEmail() {
		WaitForElementPresence(Email);
		return getElement(Email);
	}
	
	public WebElement getPassword() {
		WaitForElementPresence(Password);
		return getElement(Password);
	}
	
	public WebElement getLogiButton() {
		WaitForElementPresence(LogiButton);
		return getElement(LogiButton);
	}
	
	public  HomePage LoginSuccess() {
		return getInstance(HomePage.class);
	}
	
}
