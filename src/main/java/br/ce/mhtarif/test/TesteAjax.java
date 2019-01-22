package br.ce.mhtarif.test;

import static br.ce.mhtarif.core.DriverFactory.getDriver;
import static br.ce.mhtarif.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.ce.mhtarif.core.DSL;

public class TesteAjax {


	private DSL dsl;
	
	
	@Before // antes de cada teste execute o conteudo desse método
	public void inicializaTeste()
	{
		getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
		dsl = new DSL ();
	}
	
	@After // no final de todo teste execute esse método
	public void finaliza()
	{
		killDriver();
	}

	
	
	@Test
	public void testeAjax()
	{
		dsl.escreve("j_idt694:name", "Teste");
		dsl.clicarBotao("j_idt694:j_idt697");
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.textToBe(By.id("j_idt694:display"), "Teste")); // vai segurar o script de teste ate passar na assertiva
		Assert.assertEquals("Teste", dsl.obterTexto("j_idt694:display"));
		
		//j_idt694:name
	}
}
