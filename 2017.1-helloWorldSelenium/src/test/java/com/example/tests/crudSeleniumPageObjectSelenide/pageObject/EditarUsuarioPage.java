package com.example.tests.crudSeleniumPageObjectSelenide.pageObject;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.WebDriver;

public class EditarUsuarioPage extends AbstractPage {

	public EditarUsuarioPage(WebDriver driver) {
		super(driver);
	}

	public void alterarEmail(String novoEmail) {
		// Preencher formulário com novo email
		$("#form\\:email").setValue(novoEmail);
		
		// Submeter formulário
		$("#form\\:botaoSubmeter").click();
	}

	public void alterarLastName(String novoLastName) {
		// Preencher formulário com novo lastName
		$("#form\\:lastName").setValue(novoLastName);
		
		// Submeter formulário
		$("#form\\:botaoSubmeter").click();
	}

	public void alterarBirthday(String novoBirthday) {
		// Preencher formulário com novo birthday
		$("#form\\:birthDay_input").setValue(novoBirthday);
		
		// Submeter formulário
		$("#form\\:botaoSubmeter").click();
	}

}
