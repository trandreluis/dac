package com.example.tests.crudSeleniumPageObject.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditarUsuarioPage extends AbstractPage {

	public EditarUsuarioPage(WebDriver driver) {
		super(driver);
	}

	public void alterarEmail(String novoEmail) {
		// Preencher formulário com novo email
		WebElement emailWE = driver.findElement(By.id("form:email"));
		
		emailWE.clear();
		emailWE.sendKeys(novoEmail);
		
		// Submeter formulário
		WebElement botaoSubmeter = driver.findElement(By.id("form:botaoSubmeter"));
		botaoSubmeter.click();
		
	}

	public void alterarLastName(String novoLastName) {
		// Preencher formulário com novo lastName
		WebElement lastNameWE = driver.findElement(By.id("form:lastName"));
		
		lastNameWE.clear();
		lastNameWE.sendKeys(novoLastName);
		
		// Submeter formulário
		WebElement botaoSubmeter = driver.findElement(By.id("form:botaoSubmeter"));
		botaoSubmeter.click();
	}

	public void alterarBirthday(String novoBirthday) {
		// Preencher formulário com novo birthday
		WebElement birthDayWE = driver.findElement(By.id("form:birthDay_input"));
		
		birthDayWE.clear();
		birthDayWE.sendKeys(novoBirthday);
		
		// Submeter formulário
		WebElement botaoSubmeter = driver.findElement(By.id("form:botaoSubmeter"));
		botaoSubmeter.click();
	}

}
