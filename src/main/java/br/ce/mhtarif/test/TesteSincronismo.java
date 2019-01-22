package br.ce.mhtarif.test;

import java.util.concurrent.TimeUnit;

import static br.ce.mhtarif.core.DriverFactory.getDriver;
import static br.ce.mhtarif.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.ce.mhtarif.core.DSL;


public class TesteSincronismo {
	
	private DSL dsl;
	
	@Before // antes de cada teste execute o conteudo desse m�todo
	public void inicializaTeste()
	{

		getDriver().get("file:///"+ System.getProperty("user.dir")+"/src/main/resources/componentes.html");
		dsl = new DSL();
	}
	
	@After // no final de todo teste execute esse m�todo
	public void finaliza()
	{
		killDriver();
	}

	@Test
	public void deveUtilizarEsperaFixa() throws InterruptedException
	{
		dsl.clicarBotao("buttonDelay");
		Thread.sleep(5000);
		dsl.escreve("novoCampo", "Deu certo?");
	}
	
	@Test
	public void deveUtilizarEsperaImplicita() throws InterruptedException
	{
		getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		dsl.clicarBotao("buttonDelay");
		dsl.escreve("novoCampo", "Deu certo?");
		getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}
	
	@Test
	public void deveUtilizarEsperaExplicita() throws InterruptedException
	{
		dsl.clicarBotao("buttonDelay");
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("novoCampo")));
		dsl.escreve("novoCampo", "Deu certo?");
	}
	
	
}
