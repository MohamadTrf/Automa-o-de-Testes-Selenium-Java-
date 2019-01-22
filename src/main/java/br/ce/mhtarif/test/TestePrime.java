package br.ce.mhtarif.test;

import static br.ce.mhtarif.core.DriverFactory.getDriver;
import static br.ce.mhtarif.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.mhtarif.core.DSL;

public class TestePrime {

	private DSL dsl;

		@Before // antes de cada teste execute o conteudo desse método
		public void inicializaTeste()
		{
			dsl = new DSL();
		}
		
		@After
		public void finaliza()
		{
			killDriver();
		}
		
		@Test
		public void deveInteragirComRadioPrime()
		{
			getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
			dsl.selecionarComboPrime("j_idt692:console", "Xbox One");
			Assert.assertEquals("Xbox One", dsl.obterTexto("j_idt695:console_label"));
		}
		
		@Test
		public void deveInteragirComSelectPrime()
		{
			getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
			Assert.assertEquals("PS4", dsl.obterTexto("j_idt695:console_label"));
			

		}
		
		
}
