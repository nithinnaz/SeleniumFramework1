package project.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent{

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// LoginPage Elements

	@FindBy(css = "#userEmail")
	WebElement emailbox;

	@FindBy(css = "#userPassword")
	WebElement pwdbox;

	@FindBy(css = "#login")
	WebElement login;
	
	@FindBy(css = ".toast-message")
	WebElement errorMessage;

	// LoginPage Methods
	
	public void sendemail(String email) {
		emailbox.sendKeys(email);
	}
	
	public void sendpwd(String psd) {
		pwdbox.sendKeys(psd);
	}
	
	public HomePage login(String email, String psd) {
		sendemail(email);
		sendpwd(psd);
		login.click();
		HomePage homePage = new HomePage(driver);
		return homePage;
	}
	
	public String getErrorMessage() {
		waitForElementToAppear(By.cssSelector(".toast-message"));
		return errorMessage.getText();
	}

}
