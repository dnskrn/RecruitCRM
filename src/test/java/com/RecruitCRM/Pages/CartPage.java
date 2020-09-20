package com.RecruitCRM.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.RecruitCRM.Pages.BasePage;

public class CartPage extends BasePage {

	public CartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private By Image = By.xpath("//img[@title='Printed Dress']");
	private By More = By.xpath("//span[text()='More']");
	private By Quantityplus = By.xpath("//i[@class='icon-plus']");
	private By Size = By.xpath("//select[@name='group_1']");
	private By Pink = By.xpath("//a[@name='Pink']");
	private By AddtoCart = By.xpath("//span[text()='Add to cart']");

	private By SizeAndColor = By.xpath("//span[@id='layer_cart_product_attributes']");
	private By Quantity = By.xpath("//span[@id='layer_cart_product_quantity']");
	private By TotalProd = By.xpath("//span[@class='ajax_block_products_total']");
	private By totalShiping = By.xpath("//span[@class='ajax_cart_shipping_cost']");
	private By total = By.xpath("//span[@class='ajax_block_cart_total']");

	public WebElement getSizeAndColor() {
		WaitForElementPresence(SizeAndColor);
		return getElement(SizeAndColor);
	}

	public WebElement getQuantity() {
		WaitForElementPresence(Quantity);
		return getElement(Quantity);
	}

	public WebElement getTotalProd() {
		WaitForElementPresence(TotalProd);
		return getElement(TotalProd);
	}

	public WebElement gettotalShiping() {
		WaitForElementPresence(totalShiping);
		return getElement(totalShiping);
	}

	public WebElement gettotal() {
		WaitForElementPresence(total);
		return getElement(total);
	}

	public WebElement getWomen() {
		WaitForElementPresence(Image);
		return getElement(Image);
	}

	public WebElement getImage() {
		WaitForElementPresence(Image);
		return getElement(Image);
	}

	public WebElement getMore() {
		WaitForElementPresence(More);
		return getElement(More);
	}

	public WebElement getQuantityplus() {
		WaitForElementPresence(Quantityplus);
		return getElement(Quantityplus);
	}

	public WebElement getSize() {
		// WaitForElementPresence(Size);
		return getElement(Size);
	}

	public WebElement getPink() {
		WaitForElementPresence(Pink);
		return getElement(Pink);
	}

	public WebElement getAddtoCart() {
		WaitForElementPresence(AddtoCart);
		return getElement(AddtoCart);
	}

}
