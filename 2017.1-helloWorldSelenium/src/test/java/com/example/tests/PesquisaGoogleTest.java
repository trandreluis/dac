package com.example.tests;

import static org.junit.Assert.assertEquals;

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

public class PesquisaGoogleTest {

	private WebDriver driver;
	private String baseUrl;

	@Before
	public void setUp() throws Exception {
//		System.setProperty("webdriver.gecko.driver", 
//				"C:\\DAC\\dac\\2017.1-helloWorldSelenium\\lib\\geckodriver.exe");
//		driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver",
				"C:\\DAC\\dac\\2017.1-helloWorldSelenium\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		baseUrl = "http://www.google.com.br/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void buscaMostraPalavraProcuradaNosResultados() {

		driver.get(baseUrl);

		WebElement campoDeTexto = driver.findElement(By.name("q"));
		campoDeTexto.sendKeys("Como dominar o mundo?");
		campoDeTexto.submit();

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.titleContains("Como dominar o mundo?"));

		assertEquals("Como dominar o mundo? - Pesquisa Google", driver.getTitle());
	}
}
