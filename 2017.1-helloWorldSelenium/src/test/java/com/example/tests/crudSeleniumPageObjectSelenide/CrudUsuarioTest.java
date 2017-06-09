package com.example.tests.crudSeleniumPageObjectSelenide;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.codeborne.selenide.WebDriverRunner;
import com.example.tests.crudSeleniumPageObjectSelenide.pageObject.CriarUsuarioPage;
import com.example.tests.crudSeleniumPageObjectSelenide.pageObject.UsuarioPage;

public class CrudUsuarioTest {

	private WebDriver driver;

	private UsuarioPage usuarios;

	@Before
	public void setUp() throws Exception {
//		System.setProperty("webdriver.gecko.driver", 
//				"E:\\jaindsonvs\\workspace-ifpb\\2017.1-helloWorldSelenium\\lib\\geckodriver.exe");
//		driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver",
				"E:\\jaindsonvs\\workspace-ifpb\\2017.1-helloWorldSelenium\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		
		// XXX Quando utilizamos o Selenide, não é preciso usar o "implicitlyWait".
		// Referência: https://groups.google.com/forum/#!topic/selenide/PsM1-xMy_Ag
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
		usuarios = new UsuarioPage(driver);
		WebDriverRunner.setWebDriver(driver);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void adicionarUsuario() {
		usuarios.visita();

		String valorFirstName = "First Name " + System.currentTimeMillis();
		usuarios.novo().cadastra(valorFirstName, "Last name", "10-03-2016", "aa@aa.aa");

		boolean usuarioCadastradoComSucesso = usuarios.foiCadastradoComSucessoUsuario(valorFirstName);
		assertTrue("Usuário deveria ter sido cadastrado com sucesso", usuarioCadastradoComSucesso);
	}

	@Test
	public void adicionarUsuarioComCampoBirthdayNaoPreenchido() {
		usuarios.visita();

		String valorFirstName = "First Name " + System.currentTimeMillis();
		CriarUsuarioPage criarUsuarioPage = usuarios.novo();
		criarUsuarioPage.cadastra(valorFirstName, "Last name", "", "aa@aa.aa");

		boolean usuarioCadastradoSEMsucesso = criarUsuarioPage.houveErroCampoObrigatorioBirthday();
		assertTrue("Deveria ter sido apresentada mensagem de campo obrigatório.", usuarioCadastradoSEMsucesso);
	}

	@Test
	public void adicionarUsuarioComCampoEmailNaoPreenchido() {
		usuarios.visita();

		String valorFirstName = "First Name " + System.currentTimeMillis();
		CriarUsuarioPage criarUsuarioPage = usuarios.novo();
		criarUsuarioPage.cadastra(valorFirstName, "Last name", "10-03-2016", "");

		boolean usuarioCadastradoSEMsucesso = criarUsuarioPage.houveErroCampoObrigatorioEmail();
		assertTrue("Deveria ter sido apresentada mensagem de campo obrigatório.", usuarioCadastradoSEMsucesso);
	}

	@Test
	public void removerUsuario() {
		usuarios.visita();

		String valorFirstName = "First Name " + System.currentTimeMillis();
		usuarios.novo().cadastra(valorFirstName, "Last name", "10-03-2016", "aa@aa.aa");

		usuarios.remover(valorFirstName).confirmaRemocao();

		boolean usuarioRemovidoComSucesso = usuarios.foiRemovidoComSucessoUsuario(valorFirstName);
		assertTrue("Usuário deveria ter sido removido com sucesso", usuarioRemovidoComSucesso);
	}

	@Test
	public void tentarRemoverUsuarioEDesistir() {
		usuarios.visita();

		String valorFirstName = "First Name " + System.currentTimeMillis();
		usuarios.novo().cadastra(valorFirstName, "Last name", "10-03-2016", "aa@aa.aa");

		usuarios.remover(valorFirstName).cancelarRemocao();

		boolean existeUsuario = usuarios.usuarioExiste(valorFirstName);
		assertTrue("Usuário deveria existir", existeUsuario);
	}

	@Test
	public void editarUsuarioEAlterarEmail() {
		usuarios.visita();

		String valorFirstName = "First Name " + System.currentTimeMillis();
		String email = "aa@aa.aa";
		usuarios.novo().cadastra(valorFirstName, "Last name", "10-03-2016", email);

		String novoEmail = System.currentTimeMillis() + email;
		usuarios.editar(valorFirstName).alterarEmail(novoEmail);

		boolean emailEsperado = usuarios.oUsuario(valorFirstName).temEmail(novoEmail);
		assertTrue("Usuário deveria ter email alterado", emailEsperado);
	}

	@Test
	public void editarUsuarioEAlterarLastName() {
		usuarios.visita();

		String valorFirstName = "First Name " + System.currentTimeMillis();
		String lastName = "Last name";
		usuarios.novo().cadastra(valorFirstName, lastName, "10-03-2016", "aa@aa.aa");

		String novoLastName = System.currentTimeMillis() + lastName;
		usuarios.editar(valorFirstName).alterarLastName(novoLastName);

		boolean lastNameEsperado = usuarios.oUsuario(valorFirstName).temLastName(novoLastName);
		assertTrue("Usuário deveria ter email alterado", lastNameEsperado);
	}

	@Test
	public void editarUsuarioEAlterarBirthday() {
		usuarios.visita();

		String valorFirstName = "First Name " + System.currentTimeMillis();
		usuarios.novo().cadastra(valorFirstName, "Last name", "10-03-2016", "aa@aa.aa");

		String novoBirthday = "11-03-2016";
		usuarios.editar(valorFirstName).alterarBirthday(novoBirthday);

		boolean birthdayEsperado = usuarios.oUsuario(valorFirstName).temBirthday(novoBirthday);
		assertTrue("Usuário deveria ter email alterado", birthdayEsperado);
	}

}
