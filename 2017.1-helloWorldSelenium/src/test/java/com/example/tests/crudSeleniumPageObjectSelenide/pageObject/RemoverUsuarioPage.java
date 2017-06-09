package com.example.tests.crudSeleniumPageObjectSelenide.pageObject;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.WebDriver;

public class RemoverUsuarioPage extends AbstractPage {

	public RemoverUsuarioPage(WebDriver driver) {
		super(driver);
	}
	
	public void confirmaRemocao() {
		$("#form\\:botaoDelete").click();
	}

	public void cancelarRemocao() {
		$("#form\\:botaoCancelarRemocao").click();
	}

}
