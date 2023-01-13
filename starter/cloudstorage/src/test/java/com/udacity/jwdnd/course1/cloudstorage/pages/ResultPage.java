package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {

	@FindBy(id = "success")
	private WebElement successMsg;
	
	@FindBy(id = "aResultSuccess")
	private WebElement aResultSuccess;

	
	private final JavascriptExecutor js;

	public ResultPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
	}

	public String getSuccessMsg() {
		return successMsg.getText();
	}

	public void clickOk() {
		js.executeScript("arguments[0].click();", aResultSuccess);
	}
}