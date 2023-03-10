package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	@FindBy(id = "inputUsername")
	private WebElement inputUserName;

	@FindBy(id = "inputPassword")
	private WebElement inputPassword;

	@FindBy(id = "submit-button")
	private WebElement submitButton;

	private final JavascriptExecutor js;

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
	}

	public void setUserName(String userName) {
		js.executeScript("arguments[0].value='" + userName + "';", inputUserName);
	}

	public void setPassword(String password) {
		js.executeScript("arguments[0].value='" + password + "';", inputPassword);
	}

	public void login() {
		js.executeScript("arguments[0].click();", submitButton);
	}

	public void loginUser(String username, String pass) {
		this.setUserName(username);
		this.setPassword(pass);
		this.login();
	}
}