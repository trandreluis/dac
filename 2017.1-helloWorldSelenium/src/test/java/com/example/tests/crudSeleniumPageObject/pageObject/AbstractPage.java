package com.example.tests.crudSeleniumPageObject.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
	
	protected WebDriver driver;
	
	public AbstractPage(WebDriver driver) {
		this.driver = driver;
	}

	protected WebElement recuperarCaixaMensagem() {
		
		// Esperar campo de mensagem ficar visível
		By localizadorCaixaMensagem = By.id("idMessages");
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(localizadorCaixaMensagem));
		
		// Recuperar campo de mensagem
		WebElement caixaMensagem = driver.findElement(localizadorCaixaMensagem);
		
		return caixaMensagem;
	}

}
