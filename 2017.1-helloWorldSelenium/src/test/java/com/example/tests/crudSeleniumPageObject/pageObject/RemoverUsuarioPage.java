package com.example.tests.crudSeleniumPageObject.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RemoverUsuarioPage extends AbstractPage {

	public RemoverUsuarioPage(WebDriver driver) {
		super(driver);
	}
	
	public void confirmaRemocao() {
		WebElement botaoConfirmarRemocaoUsuario = driver.findElement(By.id("form:botaoDelete"));
		botaoConfirmarRemocaoUsuario.click();
	}

	public void cancelarRemocao() {
		WebElement botaoCancelarRemocaoUsuario = driver.findElement(By.id("form:botaoCancelarRemocao"));
		botaoCancelarRemocaoUsuario.click();
	}

}
