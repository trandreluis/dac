package com.example.tests.crudSeleniumPageObjectSelenide.pageObject;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.WebDriver;


public class CriarUsuarioPage extends AbstractPage {

	public CriarUsuarioPage(WebDriver driver) {
		super(driver);
	}

	public void cadastra(String firstName, String lastName, String birthday, String email) {
		// Preencher formulário

		$("#form\\:firstName").setValue(firstName);
		$("#form\\:lastName").setValue(lastName);
		$("#form\\:birthDay_input").setValue(birthday);
		$("#form\\:email").setValue(email);

		// Submeter formulário
		$("#form\\:botaoSubmeter").click();
	}

	public boolean houveErroCampoObrigatorioBirthday() {
		// Verificar mensagem de erro
		String textoDaCaixaMensagem = recuperarCaixaMensagem().getText();
		return textoDaCaixaMensagem.contains("O campo Birthday deve ser preenchido");
	}

	public boolean houveErroCampoObrigatorioEmail() {
		// Verificar mensagem de erro
		String textoDaCaixaMensagem = recuperarCaixaMensagem().getText();
		return textoDaCaixaMensagem.contains("Email: Erro de validação: o valor é necessário.");
	}

}
