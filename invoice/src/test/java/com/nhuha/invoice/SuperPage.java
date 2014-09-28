package com.nhuha.invoice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SuperPage {
	public WebDriver driver;

	public SuperPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

}
