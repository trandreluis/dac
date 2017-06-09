package com.example.tests.crudSelenium;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CrudUsuarioTest {

	private static final int COLUNA_EMAIL_USUARIO = 4;
	
	private static final int COLUNA_LAST_NAME_USUARIO = 3;
	
	private static final int COLUNA_BIRTHDAY_USUARIO = 5;
	
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
//		System.setProperty("webdriver.gecko.driver", 
//				"E:\\jaindsonvs\\workspace-ifpb\\2017.1-helloWorldSelenium\\lib\\geckodriver.exe");
//		driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver",
				"E:\\jaindsonvs\\workspace-ifpb\\2017.1-helloWorldSelenium\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void adicionarUsuario() {
		// visitar a página principal
		driver.get("http://localhost:9090/crudSelenium/");

		// Acessar página de criação de novo usuário
		WebElement linkAdicionarUsuario = driver.findElement(By.id("linkAddUsuario"));
		linkAdicionarUsuario.click();

		// Preencher formulário
		WebElement firstNameWE = driver.findElement(By.id("form:firstName"));
		WebElement lastNameWE = driver.findElement(By.id("form:lastName"));
		WebElement birthDayWE = driver.findElement(By.id("form:birthDay_input"));
		WebElement emailWE = driver.findElement(By.id("form:email"));

		String firstName = "First Name " + System.currentTimeMillis();
		String lastName = "Last name";
		String birthday = "10-03-2016";
		String email = "aa@aa.aa";

		firstNameWE.sendKeys(firstName);
		lastNameWE.sendKeys(lastName);
		birthDayWE.sendKeys(birthday);
		emailWE.sendKeys(email);

		// Submeter formulário
		WebElement botaoSubmeter = driver.findElement(By.id("form:botaoSubmeter"));
		botaoSubmeter.click();

		// Esperar retornar para página principal
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.titleContains("User list"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idMessages")));

		// Checar se o usuário foi realmente salvo
		boolean nomeDoUsuarioEncontrado = driver.getPageSource().contains(firstName);

		// Checar se mensagem de "usuário salvo com sucesso" foi apresentada
		WebElement caixaMensagem = driver.findElement(By.id("idMessages"));
		String textoDaCaixaMensagem = caixaMensagem.getText();
		boolean nomeEncontradoCaixaMensagem = textoDaCaixaMensagem.contains("User '" + firstName + "' saved");

		boolean usuarioCadastradoComSucesso = nomeDoUsuarioEncontrado && nomeEncontradoCaixaMensagem;

		assertTrue("Usuário deveria ter sido cadastrado com sucesso", usuarioCadastradoComSucesso);
	}

	@Test
	public void adicionarUsuarioComCampoBirthdayNaoPreenchido() {
		// visitar a página principal
		driver.get("http://localhost:9090/crudSelenium/");

		// Acessar página de criação de novo usuário
		WebElement linkAdicionarUsuario = driver.findElement(By.id("linkAddUsuario"));
		linkAdicionarUsuario.click();

		// Preencher formulário
		WebElement firstNameWE = driver.findElement(By.id("form:firstName"));
		WebElement lastNameWE = driver.findElement(By.id("form:lastName"));
		WebElement birthDayWE = driver.findElement(By.id("form:birthDay_input"));
		WebElement emailWE = driver.findElement(By.id("form:email"));

		String firstName = "First Name " + System.currentTimeMillis();
		String lastName = "Last name";
		String birthday = "";
		String email = "aa@aa.aa";

		firstNameWE.sendKeys(firstName);
		lastNameWE.sendKeys(lastName);
		birthDayWE.sendKeys(birthday);
		emailWE.sendKeys(email);

		// Submeter formulário
		WebElement botaoSubmeter = driver.findElement(By.id("form:botaoSubmeter"));
		botaoSubmeter.click();

		// Esperar campo de mensagem de erro ficar visível
		WebDriverWait wait = new WebDriverWait(driver, 15);
		By localizadorCaixaMensagem = By.id("idMessages");
		wait.until(ExpectedConditions.visibilityOfElementLocated(localizadorCaixaMensagem));

		// Recuperar campo de mensagem de erro
		WebElement caixaMensagem = driver.findElement(localizadorCaixaMensagem);

		// Verificar mensagem de erro
		String textoDaCaixaMensagem = caixaMensagem.getText();

		boolean usuarioCadastradoSEMsucesso = textoDaCaixaMensagem.contains("O campo Birthday deve ser preenchido");
		assertTrue("Deveria ter sido apresentada mensagem de campo obrigatório.", usuarioCadastradoSEMsucesso);

	}

	@Test
	public void adicionarUsuarioComCampoEmailNaoPreenchido() {
		// visitar a página principal
		driver.get("http://localhost:9090/crudSelenium/");

		// Acessar página de criação de novo usuário
		WebElement linkAdicionarUsuario = driver.findElement(By.id("linkAddUsuario"));
		linkAdicionarUsuario.click();

		// Preencher formulário
		WebElement firstNameWE = driver.findElement(By.id("form:firstName"));
		WebElement lastNameWE = driver.findElement(By.id("form:lastName"));
		WebElement birthDayWE = driver.findElement(By.id("form:birthDay_input"));
		WebElement emailWE = driver.findElement(By.id("form:email"));

		String firstName = "First Name " + System.currentTimeMillis();
		String lastName = "Last name";
		String birthday = "10-03-2016";
		String email = "";

		firstNameWE.sendKeys(firstName);
		lastNameWE.sendKeys(lastName);
		birthDayWE.sendKeys(birthday);
		emailWE.sendKeys(email);

		// Submeter formulário
		WebElement botaoSubmeter = driver.findElement(By.id("form:botaoSubmeter"));
		botaoSubmeter.click();

		// Esperar campo de mensagem de erro ficar visível
		By localizadorCaixaMensagem = By.id("idMessages");
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(localizadorCaixaMensagem));

		// Recuperar campo de mensagem de erro
		WebElement caixaMensagem = driver.findElement(localizadorCaixaMensagem);

		// Verificar mensagem de erro
		String textoDaCaixaMensagem = caixaMensagem.getText();

		boolean usuarioCadastradoSEMsucesso = textoDaCaixaMensagem
				.contains("Email: Erro de validação: o valor é necessário.");
		assertTrue("Deveria ter sido apresentada mensagem de campo obrigatório.", usuarioCadastradoSEMsucesso);

	}

	@Test
	public void removerUsuario() {
		// visitar a página principal
		driver.get("http://localhost:9090/crudSelenium/");

		// Acessar página de criação de novo usuário
		WebElement linkAdicionarUsuario = driver.findElement(By.id("linkAddUsuario"));
		linkAdicionarUsuario.click();

		// Preencher formulário
		WebElement firstNameWE = driver.findElement(By.id("form:firstName"));
		WebElement lastNameWE = driver.findElement(By.id("form:lastName"));
		WebElement birthDayWE = driver.findElement(By.id("form:birthDay_input"));
		WebElement emailWE = driver.findElement(By.id("form:email"));

		String firstName = "First Name " + System.currentTimeMillis();
		String lastName = "Last name";
		String birthday = "10-03-2016";
		String email = "aa@aa.aa";

		firstNameWE.sendKeys(firstName);
		lastNameWE.sendKeys(lastName);
		birthDayWE.sendKeys(birthday);
		emailWE.sendKeys(email);

		// Submeter formulário
		WebElement botaoSubmeter = driver.findElement(By.id("form:botaoSubmeter"));
		botaoSubmeter.click();

		// xpath pra selecionar o "Delete user" correto:
		// //*[@id="tabelaUsuarios_data"]/tr[td/text() = "<nome do
		// usuário>"]/td/a[text() = "Delete user"]
		String xpath = String.format(
				"//*[@id=\"tabelaUsuarios_data\"]/tr[td/text() = \"%s\"]/td/a[text() = \"Delete user\"]", firstName);
		WebElement linkRemoverUsuario = driver.findElement(By.xpath(xpath));
		linkRemoverUsuario.click();

		// Confirmar remoção do usuário
		WebElement botaoConfirmarRemocaoUsuario = driver.findElement(By.id("form:botaoDelete"));
		botaoConfirmarRemocaoUsuario.click();

		// Esperar retornar para página principal
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.titleContains("User list"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idMessages")));

//		// Esperar campo de mensagem de ficar visível
//		wait = new WebDriverWait(driver, 15);
		By localizadorCaixaMensagem = By.id("idMessages");
//		wait.until(ExpectedConditions.visibilityOfElementLocated(localizadorCaixaMensagem));

		// Recuperar campo de mensagem
		WebElement caixaMensagem = driver.findElement(localizadorCaixaMensagem);

		// Verificar mensagem de sucesso na remoção
		String textoDaCaixaMensagem = caixaMensagem.getText();
		boolean usuarioRemovidoComSucesso = textoDaCaixaMensagem.contains("User '" + firstName + "' deleted");

		assertTrue("Usuário deveria ter sido removido com sucesso", usuarioRemovidoComSucesso);

	}

	@Test
	public void tentarRemoverUsuarioEDesistir() {
		// visitar a página principal
		driver.get("http://localhost:9090/crudSelenium/");

		// Acessar página de criação de novo usuário
		WebElement linkAdicionarUsuario = driver.findElement(By.id("linkAddUsuario"));
		linkAdicionarUsuario.click();

		// Preencher formulário
		WebElement firstNameWE = driver.findElement(By.id("form:firstName"));
		WebElement lastNameWE = driver.findElement(By.id("form:lastName"));
		WebElement birthDayWE = driver.findElement(By.id("form:birthDay_input"));
		WebElement emailWE = driver.findElement(By.id("form:email"));

		String firstName = "First Name " + System.currentTimeMillis();
		String lastName = "Last name";
		String birthday = "10-03-2016";
		String email = "aa@aa.aa";

		firstNameWE.sendKeys(firstName);
		lastNameWE.sendKeys(lastName);
		birthDayWE.sendKeys(birthday);
		emailWE.sendKeys(email);

		// Submeter formulário
		WebElement botaoSubmeter = driver.findElement(By.id("form:botaoSubmeter"));
		botaoSubmeter.click();

		// xpath pra selecionar o "Delete user" correto:
		// @formatter:off
		// //*[@id="tabelaUsuarios_data"]/tr[td/text() = "<nome do usuário>"]/td/a[text() = "Delete user"]
		// @formatter:on
		String xpath = String.format(
				"//*[@id=\"tabelaUsuarios_data\"]/tr[td/text() = \"%s\"]/td/a[text() = \"Delete user\"]", firstName);
		WebElement linkRemoverUsuario = driver.findElement(By.xpath(xpath));
		linkRemoverUsuario.click();

		// Cancelar remoção do usuário
		WebElement botaoConfirmarRemocaoUsuario = driver.findElement(By.id("form:botaoCancelarRemocao"));
		botaoConfirmarRemocaoUsuario.click();

		// Esperar retornar para página principal
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.titleContains("User list"));

		// Checar se o usuário realmente existe
		boolean nomeDoUsuarioEncontrado = driver.getPageSource().contains(firstName);

		assertTrue("Usuário deveria existir", nomeDoUsuarioEncontrado);

	}

	@Test
	public void editarUsuarioEAlterarEmail() {
		// visitar a página principal
		driver.get("http://localhost:9090/crudSelenium/");

		// Acessar página de criação de novo usuário
		WebElement linkAdicionarUsuario = driver.findElement(By.id("linkAddUsuario"));
		linkAdicionarUsuario.click();

		// Preencher formulário
		WebElement firstNameWE = driver.findElement(By.id("form:firstName"));
		WebElement lastNameWE = driver.findElement(By.id("form:lastName"));
		WebElement birthDayWE = driver.findElement(By.id("form:birthDay_input"));
		WebElement emailWE = driver.findElement(By.id("form:email"));

		String firstName = "First Name " + System.currentTimeMillis();
		String lastName = "Last name";
		String birthday = "10-03-2016";
		String email = "aa@aa.aa";

		firstNameWE.sendKeys(firstName);
		lastNameWE.sendKeys(lastName);
		birthDayWE.sendKeys(birthday);
		emailWE.sendKeys(email);

		// Submeter formulário
		WebElement botaoSubmeter = driver.findElement(By.id("form:botaoSubmeter"));
		botaoSubmeter.click();

		// xpath pra selecionar o "Edit user" correto:
		// @formatter:off
		// //*[@id="tabelaUsuarios_data"]/tr[td/text() = "<nome do usuário>"]/td/a[text() = "Edit user"]
		// @formatter:on
		String xpath = String.format(
				"//*[@id=\"tabelaUsuarios_data\"]/tr[td/text() = \"%s\"]/td/a[text() = \"Edit user\"]", firstName);
		WebElement linkEditarUsuario = driver.findElement(By.xpath(xpath));
		linkEditarUsuario.click();

		// Preencher formulário novo email
		String novoEmail = System.currentTimeMillis() + email;
		emailWE = driver.findElement(By.id("form:email"));
		
		emailWE.clear();
		emailWE.sendKeys(novoEmail);
		
		// Submeter formulário
		botaoSubmeter = driver.findElement(By.id("form:botaoSubmeter"));
		botaoSubmeter.click();

		// Esperar retornar para página principal
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.titleContains("User list"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idMessages")));
		
		// xpath pra selecionar o email do usuário correto:
		// @formatter:off
		// //*[@id=\"tabelaUsuarios_data\"]/tr[td/text() = \"<nome do usuário>\"]/td[<coluna do e-mail do usuário>]
		// @formatter:on
		xpath = String.format("//*[@id=\"tabelaUsuarios_data\"]/tr[td/text() = \"%s\"]/td[%d]", 
				firstName, COLUNA_EMAIL_USUARIO);
		WebElement emailElemento = driver.findElement(By.xpath(xpath));
		
		String emailDoUsuario = emailElemento.getText();
		
		boolean emailEsperado = emailDoUsuario.equals(novoEmail);
		
		assertTrue("Usuário deveria ter email alterado", emailEsperado);
	}

	@Test
	public void editarUsuarioEAlterarLastName() {
		// visitar a página principal
		driver.get("http://localhost:9090/crudSelenium/");

		// Acessar página de criação de novo usuário
		WebElement linkAdicionarUsuario = driver.findElement(By.id("linkAddUsuario"));
		linkAdicionarUsuario.click();

		// Preencher formulário
		WebElement firstNameWE = driver.findElement(By.id("form:firstName"));
		WebElement lastNameWE = driver.findElement(By.id("form:lastName"));
		WebElement birthDayWE = driver.findElement(By.id("form:birthDay_input"));
		WebElement emailWE = driver.findElement(By.id("form:email"));

		String firstName = "First Name " + System.currentTimeMillis();
		String lastName = "Last name";
		String birthday = "10-03-2016";
		String email = "aa@aa.aa";

		firstNameWE.sendKeys(firstName);
		lastNameWE.sendKeys(lastName);
		birthDayWE.sendKeys(birthday);
		emailWE.sendKeys(email);

		// Submeter formulário
		WebElement botaoSubmeter = driver.findElement(By.id("form:botaoSubmeter"));
		botaoSubmeter.click();

		// xpath pra selecionar o "Edit user" correto:
		// @formatter:off
		// //*[@id="tabelaUsuarios_data"]/tr[td/text() = "<nome do usuário>"]/td/a[text() = "Edit user"]
		// @formatter:on
		String xpath = String.format(
				"//*[@id=\"tabelaUsuarios_data\"]/tr[td/text() = \"%s\"]/td/a[text() = \"Edit user\"]", firstName);
		WebElement linkEditarUsuario = driver.findElement(By.xpath(xpath));
		linkEditarUsuario.click();

		// Preencher formulário com novo lastName
		String novoLastName = System.currentTimeMillis() + lastName;
		lastNameWE = driver.findElement(By.id("form:lastName"));
		
		lastNameWE.clear();
		lastNameWE.sendKeys(novoLastName);
		
		// Submeter formulário
		botaoSubmeter = driver.findElement(By.id("form:botaoSubmeter"));
		botaoSubmeter.click();

		// Esperar retornar para página principal
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.titleContains("User list"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idMessages")));
		
		// xpath pra selecionar o email do usuário correto:
		// //*[@id=\"tabelaUsuarios_data\"]/tr[td/text() = \"<nome do usuário>\"]/td[<coluna do lastName do usuário>]
		xpath = String.format("//*[@id=\"tabelaUsuarios_data\"]/tr[td/text() = \"%s\"]/td[%d]", 
				firstName, COLUNA_LAST_NAME_USUARIO);
		WebElement lastNameElemento = driver.findElement(By.xpath(xpath));
		
		String lastNameDoUsuario = lastNameElemento.getText();
		
		boolean lastNameEsperado = lastNameDoUsuario.equals(novoLastName);
		
		assertTrue("Usuário deveria ter email alterado", lastNameEsperado);
		
	}

	@Test
	public void editarUsuarioEAlterarBirthday() {
		// visitar a página principal
		driver.get("http://localhost:9090/crudSelenium/");

		// Acessar página de criação de novo usuário
		WebElement linkAdicionarUsuario = driver.findElement(By.id("linkAddUsuario"));
		linkAdicionarUsuario.click();

		// Preencher formulário
		WebElement firstNameWE = driver.findElement(By.id("form:firstName"));
		WebElement lastNameWE = driver.findElement(By.id("form:lastName"));
		WebElement birthDayWE = driver.findElement(By.id("form:birthDay_input"));
		WebElement emailWE = driver.findElement(By.id("form:email"));

		String firstName = "First Name " + System.currentTimeMillis();
		String lastName = "Last name";
		String birthday = "10-03-2016";
		String email = "aa@aa.aa";

		firstNameWE.sendKeys(firstName);
		lastNameWE.sendKeys(lastName);
		birthDayWE.sendKeys(birthday);
		emailWE.sendKeys(email);

		// Submeter formulário
		WebElement botaoSubmeter = driver.findElement(By.id("form:botaoSubmeter"));
		botaoSubmeter.click();

		// xpath pra selecionar o "Edit user" correto:
		// @formatter:off
		// //*[@id="tabelaUsuarios_data"]/tr[td/text() = "<nome do usuário>"]/td/a[text() = "Edit user"]
		// @formatter:on
		String xpath = String.format(
				"//*[@id=\"tabelaUsuarios_data\"]/tr[td/text() = \"%s\"]/td/a[text() = \"Edit user\"]", firstName);
		WebElement linkEditarUsuario = driver.findElement(By.xpath(xpath));
		linkEditarUsuario.click();

		// Preencher formulário com novo birthday
		String novoBirthday = "11-03-2016";
		birthDayWE = driver.findElement(By.id("form:birthDay_input"));
		
		birthDayWE.clear();
		birthDayWE.sendKeys(novoBirthday);
		
		// Submeter formulário
		botaoSubmeter = driver.findElement(By.id("form:botaoSubmeter"));
		botaoSubmeter.click();

		// Esperar retornar para página principal
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.titleContains("User list"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idMessages")));
		
		// xpath pra selecionar o email do usuário correto:
		// //*[@id=\"tabelaUsuarios_data\"]/tr[td/text() = \"<nome do usuário>\"]/td[<coluna do birthday do usuário>]
		xpath = String.format("//*[@id=\"tabelaUsuarios_data\"]/tr[td/text() = \"%s\"]/td[%d]", 
				firstName, COLUNA_BIRTHDAY_USUARIO);
		WebElement birthdayElemento = driver.findElement(By.xpath(xpath));
		
		String birthdayDoUsuario = birthdayElemento.getText();
		
		boolean birthdayEsperado = birthdayDoUsuario.equals(novoBirthday);

		assertTrue("Usuário deveria ter email alterado", birthdayEsperado);
		
	}

}
