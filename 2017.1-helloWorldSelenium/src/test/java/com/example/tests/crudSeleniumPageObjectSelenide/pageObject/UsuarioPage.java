package com.example.tests.crudSeleniumPageObjectSelenide.pageObject;

import static com.codeborne.selenide.Condition.appears;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.codeborne.selenide.WebDriverRunner;

public class UsuarioPage extends AbstractPage {

	private static final int COLUNA_EMAIL_USUARIO = 4;
	
	private static final int COLUNA_LAST_NAME_USUARIO = 3;
	
	private static final int COLUNA_BIRTHDAY_USUARIO = 5;
	
	private String valorFirstNameUsuario;
	
	public UsuarioPage(WebDriver driver) {
		super(driver);
	}

	public void visita() {
		open("http://localhost:9090/crudSelenium/");
	}

	public CriarUsuarioPage novo() {
		$("#linkAddUsuario").click();
		
		return new CriarUsuarioPage(driver);
	}

	public boolean foiCadastradoComSucessoUsuario(String valorFirstName) {
		// Checar se o usuário foi realmente salvo
		$("#tabelaUsuarios").should(appears);
		boolean nomeDoUsuarioEncontrado = WebDriverRunner.source().contains(valorFirstName);

		String textoDaCaixaMensagem = recuperarCaixaMensagem().getText();
		boolean nomeEncontradoCaixaMensagem = textoDaCaixaMensagem.contains("User '" + valorFirstName + "' saved");
		return nomeDoUsuarioEncontrado && nomeEncontradoCaixaMensagem;
	}

	public RemoverUsuarioPage remover(String valorFirstName) {
		// xpath pra selecionar o "Delete user" correto:
		// @formatter:off
		// //*[@id="tabelaUsuarios_data"]/tr[td/text() = "<nome do usuário>"]/td/a[text() = "Delete user"]
		// @formatter:on
		String xpath = String.format("//*[@id=\"tabelaUsuarios_data\"]/tr[td/text() = \"%s\"]/td/a[text() = \"Delete user\"]", 
				valorFirstName);
		$(By.xpath(xpath)).click();
		
		return new RemoverUsuarioPage(driver);
	}

	public boolean foiRemovidoComSucessoUsuario(String valorFirstName) {
		WebElement caixaMensagem = recuperarCaixaMensagem();
		
		// Verificar mensagem de sucesso na remoção
		String textoDaCaixaMensagem = caixaMensagem.getText();
		return textoDaCaixaMensagem.contains("User '" + valorFirstName + "' deleted");
	}

	public boolean usuarioExiste(String valorFirstName) {
		// Esperar retornar para página principal
		$("#tabelaUsuarios").should(appears);
		
		// Checar se o usuário realmente existe
		boolean nomeDoUsuarioEncontrado = WebDriverRunner.source().contains(valorFirstName);
		
		return nomeDoUsuarioEncontrado;
	}

	public UsuarioPage oUsuario(String valorFirstName) {
		valorFirstNameUsuario = valorFirstName;
		return this;
	}

	public boolean temEmail(String email) {
		// xpath pra selecionar o email do usuário correto:
		// @formatter:off
		// //*[@id=\"tabelaUsuarios_data\"]/tr[td/text() = \"<nome do usuário>\"]/td[<coluna do e-mail do usuário>]
		// @formatter:on
		String xpath = String.format("//*[@id=\"tabelaUsuarios_data\"]/tr[td/text() = \"%s\"]/td[%d]", 
				valorFirstNameUsuario, COLUNA_EMAIL_USUARIO);
		String emailDoUsuario = $(By.xpath(xpath)).getText();
		
		return emailDoUsuario.equals(email);
	}

	public EditarUsuarioPage editar(String valorFirstName) {
		// xpath pra selecionar o "Edit user" correto:
		// @formatter:off
		// //*[@id="tabelaUsuarios_data"]/tr[td/text() = "<nome do usuário>"]/td/a[text() = "Edit user"]
		// @formatter:on
		String xpath = String.format("//*[@id=\"tabelaUsuarios_data\"]/tr[td/text() = \"%s\"]/td/a[text() = \"Edit user\"]", 
				valorFirstName);
		$(By.xpath(xpath)).click();
		
		return new EditarUsuarioPage(driver);
	}

	public boolean temLastName(String lastName) {
		// xpath pra selecionar o email do usuário correto:
		// @formatter:off
		// //*[@id=\"tabelaUsuarios_data\"]/tr[td/text() = \"<nome do usuário>\"]/td[<coluna do lastName do usuário>]
		// @formatter:on
		String xpath = String.format("//*[@id=\"tabelaUsuarios_data\"]/tr[td/text() = \"%s\"]/td[%d]", 
				valorFirstNameUsuario, COLUNA_LAST_NAME_USUARIO);
		
		String lastNameDoUsuario = $(By.xpath(xpath)).getText();
		
		return lastNameDoUsuario.equals(lastName);
	}

	public boolean temBirthday(String birthday) {
		
		// xpath pra selecionar o email do usuário correto:
		// @formatter:off
		// //*[@id=\"tabelaUsuarios_data\"]/tr[td/text() = \"<nome do usuário>\"]/td[<coluna do birthday do usuário>]
		// @formatter:on
		String xpath = String.format("//*[@id=\"tabelaUsuarios_data\"]/tr[td/text() = \"%s\"]/td[%d]", 
				valorFirstNameUsuario, COLUNA_BIRTHDAY_USUARIO);
		
		String birthdayDoUsuario = $(By.xpath(xpath)).getText();
		
		return birthdayDoUsuario.equals(birthday);
	}

}
