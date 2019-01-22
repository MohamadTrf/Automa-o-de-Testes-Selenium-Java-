package br.ce.mhtarif.test;

import static br.ce.mhtarif.core.DriverFactory.getDriver;
import static br.ce.mhtarif.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.mhtarif.core.DSL;


public class TesteAlert {

	private DSL dsl;
	
	@Before // antes de cada teste execute o conteudo desse método
	public void inicializaTeste()
	{
		getDriver().get("file:///"+ System.getProperty("user.dir")+"/src/main/resources/componentes.html");
		dsl = new DSL();
	}
	
	@After
	public void finaliza()
	{
		killDriver();
	}
	
	
	
	@Test
	public void deveInteragirComAlerts() 
	{
		dsl.clicarBotao("alert");
		String textoAlert = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Alert Simples", textoAlert);
		dsl.escreve("elementosForm:nome", textoAlert);
	}
	
	@Test
	public void deveInteragirComAlertConfirm()
	{
		dsl.clicarBotao("confirm");
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoEAceita());
		Assert.assertEquals("Confirmado", dsl.alertaObterTextoEAceita());
		
		dsl.clicarBotao("confirm");
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoEAceita());
		Assert.assertEquals("Negado", dsl.alertaObterTextoEAceita());
		
	}
	
	@Test
	public void deveInteragirComAlertPrompt()
	{
		dsl.clicarBotao("prompt");
		Assert.assertEquals("Digite um numero", dsl.alertaObterTexto());
		dsl.alertaEscrever("12");
		Assert.assertEquals("Era 12?", dsl.alertaObterTextoEAceita());
		Assert.assertEquals(":D",dsl.alertaObterTextoEAceita());
	}

}
