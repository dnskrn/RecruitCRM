package com.QATest.RecruitCRM;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.RecruitCRM.CommonAnnotations.TestBaseRecruitCRM;
import com.RecruitCRM.Pages.CartPage;
import com.RecruitCRM.Pages.HomePage;
import com.RecruitCRM.Pages.LoginPage;

public class AutomationpracticeTest extends TestBaseRecruitCRM {
	// public static ExtentTest test;
	@Parameters({ "env" })
	@Test
	public static void LaunchTheLoginPage(String env) {
		// 1. Go to "http://automationpractice.com/index.php" and click on Sign-In.

		driver.get(env);
		test = report.createTest("Go to \"http://automationpractice.com/index.php\" and click on Sign-In");
	}

	@Parameters({ "UserName", "PassWord" })
	@Test(dependsOnMethods = "LaunchTheLoginPage")
	public void LogintotheURL(String UserName, String PassWord) {
		/*
		 * 2. Sign-In using following credentials: Username: phishing@springct.com
		 * Password: Phish@123
		 */
		page.getInstance(LoginPage.class).getLoginTab().click();

		page.getInstance(LoginPage.class).getEmail().sendKeys(UserName);
		page.getInstance(LoginPage.class).getPassword().sendKeys(PassWord);
		page.getInstance(LoginPage.class).getLogiButton().click();

		test = report.createTest(
				"Sign-In using following credentials: Username: phishing@springct.com  Password: Phish@123");
	}

	@Test(dependsOnMethods = "LogintotheURL")
	public void Scenario1() {
		/*
		 * Explore within Women >> Dresses >> Evening Dresses Once page uploded, go to
		 * Catalog and filter out dress: Size(M) >> Color(Pink) >> Set Range: $50.00
		 * -$52.28
		 */
		WebElement Women = page.getInstance(HomePage.class).getWomen();
		WebElement Evening_Dresses = page.getInstance(HomePage.class).getEvening_Dresses();

		action.moveToElement(Women).build().perform();
		wait.until(ExpectedConditions.visibilityOf(Evening_Dresses));
		action.moveToElement(Evening_Dresses).build().perform();
		wait.until(ExpectedConditions.visibilityOf(Evening_Dresses));

		Evening_Dresses.click();

		WebElement SizeM = page.getInstance(HomePage.class).getSizeM();
		WebElement ColorPink = page.getInstance(HomePage.class).getColorPink();
		WebElement rangeID = page.getInstance(HomePage.class).getRangeID();
		
		SizeM.click();
		ColorPink.click();

		WebElement Slidder = page.getInstance(HomePage.class).getSlidder();

		js.executeScript("arguments[0].scrollIntoView();", rangeID);
		while (!rangeID.getText().equals("$50.00 - $52.28")) {
			wait.until(ExpectedConditions.visibilityOf(Slidder));
			action.moveToElement(Slidder).click(Slidder).sendKeys(Keys.ARROW_LEFT).build().perform();
		}
		
		test = report.createTest("Explore within Women >> Dresses >> Evening Dresses Once page uploded");
		test = report.createTest(" go to Catalog and filter out dress: Size(M) >> Color(Pink) >> ");
		test = report.createTest("Set Range: $50.00-$52.28");
	}

	@Test(dependsOnMethods = "Scenario1")
	public void Scenario2() {
		WebElement Image = page.getInstance(CartPage.class).getImage();
		WebElement More = page.getInstance(CartPage.class).getMore();

		action.moveToElement(Image).build().perform();
		wait.until(ExpectedConditions.visibilityOf(More));

		More.click();

		WebElement Quantityplus = page.getInstance(CartPage.class).getQuantityplus();
		WebElement Size = page.getInstance(CartPage.class).getSize();
		WebElement Pink = page.getInstance(CartPage.class).getPink();
		WebElement AddtoCart = page.getInstance(CartPage.class).getAddtoCart();

		Quantityplus.click();
		Quantityplus.click();

		Select sl = new Select(Size);
		sl.selectByVisibleText("M");
		Pink.click();
		AddtoCart.click();
		
		test = report.createTest("On The Cart Page increase the Quantity to 3 and select size as M and color as Pink");
	}

	@Test(dependsOnMethods = "Scenario2")
	public void Scenario3() {

		/*
		 * 5. verify quantity, size, color and total cost of the product on pop-up. Find
		 * out shipping cost. Verify total cost (total product cost + shipping cost).
		 * 
		 * 6. Print all values (quantity,Size,Color,Total product cost, Shipping cost,
		 * total cost) on the console. Use assertions, Waits as needed.
		 */
		WebElement SizeAndColor = page.getInstance(CartPage.class).getSizeAndColor();
		WebElement Quantity = page.getInstance(CartPage.class).getQuantity();
		WebElement TotalProd = page.getInstance(CartPage.class).getTotalProd();
		WebElement totalShiping = page.getInstance(CartPage.class).gettotalShiping();
		WebElement total = page.getInstance(CartPage.class).gettotal();

		wait.until(ExpectedConditions.visibilityOf(SizeAndColor));
		
		
		String SIZEANDCOLOR = SizeAndColor.getText();
		String SIZE = SIZEANDCOLOR.substring(SIZEANDCOLOR.length() - 1);
		String COLOR = SIZEANDCOLOR.substring(0, SIZEANDCOLOR.length() - 3);
		String QUANTITY = Quantity.getText();
		String TOTALPRODCOST = TotalProd.getText();
		String TOTALSHIPING = totalShiping.getText();
		String TOTAL = total.getText();

		Double prodcost = Double.valueOf(TOTALPRODCOST.substring(1));
		Double Shippingcost = Double.valueOf(TOTALSHIPING.substring(1));
		Double Totalcost = Double.valueOf(TOTAL.substring(1));

		Assert.assertEquals(SIZE, "M");
		Assert.assertEquals(COLOR, "Pink");
		Assert.assertEquals(QUANTITY, "3");
		Assert.assertEquals(TOTALPRODCOST, "$152.97");
		Assert.assertEquals(TOTALSHIPING, "$2.00");
		Assert.assertEquals(TOTAL, "$154.97");

		Assert.assertEquals(Totalcost, prodcost + Shippingcost);

		System.out.println("COLOR : " + COLOR);
		System.out.println("Size : " + SIZE);
		System.out.println("QUANTITY : " + QUANTITY);
		System.out.println("TOTALPRODCOST : " + TOTALPRODCOST);
		System.out.println("TOTALSHIPING : " + TOTALSHIPING);
		System.out.println("TOTAL : " + TOTAL);

		test = report.createTest("Scenario3");
	}

}
