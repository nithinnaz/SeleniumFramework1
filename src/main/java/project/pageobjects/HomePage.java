package project.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class HomePage extends AbstractComponent {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".card")
	List<WebElement> Products;

	public List<WebElement> getProductsList() {
		waitForElementToAppear(By.cssSelector(".card"));
		System.out.println(Products.size());
		for (WebElement Product : Products) {
			System.out.println(Product.getText());
		}
		return Products;
	}

	public WebElement getProductByName(String ProductName) {
		WebElement productis = null;
		//WebElement Product = getProductsList().stream().filter(a -> a.getText().equals(ProductName)).findFirst().orElse(null);
		for (WebElement product : getProductsList()) {
			System.out.println(product.getText());
			if (product.findElement(By.cssSelector("b")).getText().equals(ProductName)) {
				productis = product;
				break;
			}
		}
		System.out.println(productis.getText());
		return productis;
	}

	public void addProductToCart(String ProductName) {
		WebElement product = getProductByName(ProductName);
		product.findElement(By.cssSelector(".btn.w-10.rounded")).click();
		waitForElementToAppear(By.cssSelector(".toast-message.ng-star-inserted"));
	}

}
