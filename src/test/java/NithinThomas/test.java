package NithinThomas;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import TestComponents.BaseClass;
import project.pageobjects.CartPage;
import project.pageobjects.CheckOutPage;
import project.pageobjects.HomePage;

public class test extends BaseClass{
	
	String ProductName = "ZARA COAT 3";
	
	@Test(dataProvider= "getDataFromJson")
	public void SubmitOrder(HashMap<String, String> input) throws IOException {
//		LoginPage loginPage = LaunchApplication();
		HomePage homePage = loginPage.login(input.get("email"), input.get("password"));
		homePage.addProductToCart("ZARA COAT 3");
		CartPage cartPage = homePage.goToCartPage();
		Assert.assertTrue(cartPage.verifyCartDisplay(ProductName));
		CheckOutPage checkoutpage = cartPage.CheckOut();
		checkoutpage.sentCountryName();
		//checkoutpage.submitOrder();
	}
	
	@DataProvider
	public Object[][] getDataFromJson() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"/src/test/java/Data/LoginData.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
}
