package project.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".totalRow button")
	WebElement cartButton;
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartItem;
	
	public CheckOutPage CheckOut() {
		cartButton.click();
		CheckOutPage checkOutpage= new CheckOutPage(driver);
		return checkOutpage;
	}
	
	public Boolean verifyCartDisplay(String  productName) {
		Boolean flag = cartItem.stream().anyMatch(a-> a.getText().equalsIgnoreCase(productName));
		return flag;
	}
}
