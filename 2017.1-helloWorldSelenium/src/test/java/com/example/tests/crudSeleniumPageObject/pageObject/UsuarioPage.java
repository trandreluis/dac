package com.example.tests.crudSeleniumPageObject.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UsuarioPage extends AbstractPage {

	private static final int COLUNA_EMAIL_USUARIO = 4;
	
	private static final int COLUNA_LAST_NAME_USUARIO = 3;
	
	private static final int COLUNA_BIRTHDAY_USUARIO = 5;
	
	private String valorFirstNameUsuario;
	
	public UsuarioPage(WebDriver driver) {
		super(driver);
	}

	public void visita() {
		driver.get("http://localhost:9090/crudSelenium/");
	}

	private void esperarRetornarParaPaginaPrincipal() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.titleContains("User list"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idMessages")));
	}

	private void esperarRetornarParaPaginaPrincipalSemCaixaDeMensagem() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.titleContains("User list"));
	}

	public CriarUsuarioPage novo() {
		WebElement linkAdicionarUsuario = driver.findElement(By.id("linkAddUsuario"));
		linkAdicionarUsuario.click();
		
		return new CriarUsuarioPage(driver);
	}

	public boolean foiCadastradoComSucessoUsuario(String valorFirstName) {
		// Esperar retornar para página principal
		esperarRetornarParaPaginaPrincipal();
		
		// Checar se o usuário foi realmente salvo
		boolean nomeDoUsuarioEncontrado = driver.getPageSource().contains(valorFirstName);

		// Checar se mensagem de "usuário salvo com sucesso" foi apresentada
		WebElement caixaMensagem = driver.findElement(By.id("idMessages"));
		String textoDaCaixaMensagem = caixaMensagem.getText();
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
		WebElement linkRemoverUsuario = driver.findElement(By.xpath(xpath));
		linkRemoverUsuario.click();
		
		return new RemoverUsuarioPage(driver);
	}

	public boolean foiRemovidoComSucessoUsuario(String valorFirstName) {
		// Esperar retornar para página principal
		esperarRetornarParaPaginaPrincipal();
		
		WebElement caixaMensagem = recuperarCaixaMensagem();
		
		// Verificar mensagem de sucesso na remoção
		String textoDaCaixaMensagem = caixaMensagem.getText();
		return textoDaCaixaMensagem.contains("User '" + valorFirstName + "' deleted");
	}

	public boolean usuarioExiste(String valorFirstName) {
		// Esperar retornar para página principal
		esperarRetornarParaPaginaPrincipalSemCaixaDeMensagem();
		
		// Checar se o usuário realmente existe
		boolean nomeDoUsuarioEncontrado = driver.getPageSource().contains(valorFirstName);
		
		return nomeDoUsuarioEncontrado;
	}

	public UsuarioPage oUsuario(String valorFirstName) {
		valorFirstNameUsuario = valorFirstName;
		return this;
	}

	public boolean temEmail(String email) {
		// Esperar retornar para página principal
		esperarRetornarParaPaginaPrincipal();
		
		// xpath pra selecionar o email do usuário correto:
		// @formatter:off
		// //*[@id=\"tabelaUsuarios_data\"]/tr[td/text() = \"<nome do usuário>\"]/td[<coluna do e-mail do usuário>]
		// @formatter:on
		String xpath = String.format("//*[@id=\"tabelaUsuarios_data\"]/tr[td/text() = \"%s\"]/td[%d]", 
				valorFirstNameUsuario, COLUNA_EMAIL_USUARIO);
		WebElement emailElemento = driver.findElement(By.xpath(xpath));
		
		String emailDoUsuario = emailElemento.getText();
		
		return emailDoUsuario.equals(email);
	}

	public EditarUsuarioPage editar(String valorFirstName) {
		// xpath pra selecionar o "Edit user" correto:
		// @formatter:off
		// //*[@id="tabelaUsuarios_data"]/tr[td/text() = "<nome do usuário>"]/td/a[text() = "Edit user"]
		// @formatter:on
		String xpath = String.format("//*[@id=\"tabelaUsuarios_data\"]/tr[td/text() = \"%s\"]/td/a[text() = \"Edit user\"]", 
				valorFirstName);
		WebElement linkEditarUsuario = driver.findElement(By.xpath(xpath));
		linkEditarUsuario.click();
		
		return new EditarUsuarioPage(driver);
	}

	public boolean temLastName(String lastName) {
		// Esperar retornar para página principal
		esperarRetornarParaPaginaPrincipal();
		
		// xpath pra selecionar o email do usuário correto:
		// @formatter:off
		// //*[@id=\"tabelaUsuarios_data\"]/tr[td/text() = \"<nome do usuário>\"]/td[<coluna do lastName do usuário>]
		// @formatter:on
		String xpath = String.format("//*[@id=\"tabelaUsuarios_data\"]/tr[td/text() = \"%s\"]/td[%d]", 
				valorFirstNameUsuario, COLUNA_LAST_NAME_USUARIO);
		WebElement lastNameElemento = driver.findElement(By.xpath(xpath));
		
		String lastNameDoUsuario = lastNameElemento.getText();
		
		return lastNameDoUsuario.equals(lastName);
	}

	public boolean temBirthday(String birthday) {
		// Esperar retornar para página principal
		esperarRetornarParaPaginaPrincipal();
		
		// xpath pra selecionar o email do usuário correto:
		// @formatter:off
		// //*[@id=\"tabelaUsuarios_data\"]/tr[td/text() = \"<nome do usuário>\"]/td[<coluna do birthday do usuário>]
		// @formatter:on
		String xpath = String.format("//*[@id=\"tabelaUsuarios_data\"]/tr[td/text() = \"%s\"]/td[%d]", 
				valorFirstNameUsuario, COLUNA_BIRTHDAY_USUARIO);
		WebElement birthdayElemento = driver.findElement(By.xpath(xpath));
		
		String birthdayDoUsuario = birthdayElemento.getText();
		
		return birthdayDoUsuario.equals(birthday);
	}

}
