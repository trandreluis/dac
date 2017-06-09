package com.example.tests.crudSeleniumPageObjectSelenide.pageObject;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.WebDriver;

import com.codeborne.selenide.SelenideElement;

public abstract class AbstractPage {
	
	protected WebDriver driver;
	
	public AbstractPage(WebDriver driver) {
		this.driver = driver;
	}

	protected SelenideElement recuperarCaixaMensagem() {
		
		return $("#idMessages");
	}

}
