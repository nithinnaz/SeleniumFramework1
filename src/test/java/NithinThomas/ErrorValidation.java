package NithinThomas;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import TestComponents.BaseClass;
import project.pageobjects.CartPage;
import project.pageobjects.HomePage;
import TestComponents.Retry;

public class ErrorValidation extends BaseClass {

	@Test(dependsOnMethods = { "ProductCartValidation" })
	public void LoginErrorValidation() throws IOException {
		loginPage.login("abcdefg923@gmail.com", "Password@12345");
		Assert.assertEquals(loginPage.getErrorMessage(), "Incorrect email or password.");
	}
	
	@Test(retryAnalyzer=Retry.class)
	public void ProductCartValidation() throws IOException {
		HomePage homePage = loginPage.login("abcdefg923@gmail.com", "Password@123");
		homePage.addProductToCart("ZARA COAT 3");
		CartPage cartPage = homePage.goToCartPage();
		Assert.assertTrue(cartPage.verifyCartDisplay("ZARA COAT 3"));
	}
}
