package com.example.tests.crudSeleniumPageObject.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CriarUsuarioPage extends AbstractPage {

	public CriarUsuarioPage(WebDriver driver) {
		super(driver);
	}

	public void cadastra(String firstName, String lastName, String birthday, String email) {
		// Preencher formulário

		WebElement firstNameWE = driver.findElement(By.id("form:firstName"));
		WebElement lastNameWE = driver.findElement(By.id("form:lastName"));
		WebElement birthDayWE = driver.findElement(By.id("form:birthDay_input"));
		WebElement emailWE = driver.findElement(By.id("form:email"));

		firstNameWE.sendKeys(firstName);
		lastNameWE.sendKeys(lastName);
		birthDayWE.sendKeys(birthday);
		emailWE.sendKeys(email);

		// Submeter formulário
		WebElement botaoSubmeter = driver.findElement(By.id("form:botaoSubmeter"));
		botaoSubmeter.click();
	}

	public boolean houveErroCampoObrigatorioBirthday() {
		WebElement caixaMensagem = recuperarCaixaMensagem();
		
		// Verificar mensagem de erro
		String textoDaCaixaMensagem = caixaMensagem.getText();
		return textoDaCaixaMensagem.contains("O campo Birthday deve ser preenchido");
	}

	public boolean houveErroCampoObrigatorioEmail() {
		WebElement caixaMensagem = recuperarCaixaMensagem();
		
		// Verificar mensagem de erro
		String textoDaCaixaMensagem = caixaMensagem.getText();
		return textoDaCaixaMensagem.contains("Email: Erro de validação: o valor é necessário.");
	}

}
