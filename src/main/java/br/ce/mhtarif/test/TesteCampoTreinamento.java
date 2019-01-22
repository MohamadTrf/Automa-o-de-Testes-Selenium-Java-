package br.ce.mhtarif.test;

import java.util.Arrays;
import java.util.List;

import static br.ce.mhtarif.core.DriverFactory.getDriver;
import static br.ce.mhtarif.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import br.ce.mhtarif.core.DSL;
import br.ce.mhtarif.core.DriverFactory;

public class TesteCampoTreinamento {
	
	private DSL dsl;
	
	@Before // antes de cada teste execute o conteudo desse método
	public void inicializaTeste()
	{
		DriverFactory.getDriver().get("file:///"+ System.getProperty("user.dir")+"/src/main/resources/componentes.html");
		dsl = new DSL();
	}
	
	@After // no final de todo teste execute esse método
	public void finaliza()
	{
		killDriver();
	}
	
	@Test
	public void teste() 
	{
		dsl.escreve("elementosForm:nome", "teste de escrita");
	//	driver.findElement(By.name("elementosForm:nome")).sendKeys("teste de escrita");
		Assert.assertEquals("teste de escrita",dsl.obterValorCampo("elementosForm:nome"));
		//	driver.findElement(By.name("elementosForm:nome")).submit();
		System.out.println(getDriver().getTitle());
	}
	
	@Test
	public void testeTextArea() 
	{
		dsl.escreve("elementosForm:sugestoes", "teste textArea");
		Assert.assertEquals("teste textArea",dsl.obterValorCampo("elementosForm:sugestoes"));
	}
	
	@Test
	public void testTextFieldDuplo()
	{
		dsl.escreve("elementosForm:nome", "Mohamad");
		Assert.assertEquals("Mohamad", dsl.obterValorCampo("elementosForm:nome"));
		dsl.escreve("elementosForm:nome", "Tarif");
		Assert.assertEquals("Tarif", dsl.obterValorCampo("elementosForm:nome"));

	}
	
	@Test
	public void selecionaRadioButton() 
	{
		dsl.clicaRadio("elementosForm:sexo:0");
		getDriver().findElement(By.id("elementosForm:sexo:0")).click();
		
		Boolean selecionado = getDriver().findElement(By.id("elementosForm:sexo:0")).isSelected();
		Assert.assertTrue(selecionado);
		
	}
	

	
	@Test
	public void selecionaCheckbox()
	{
		dsl.clicaCheck("elementosForm:comidaFavorita:2");
	Boolean radioMarcado = dsl.isCheckMarcado("elementosForm:comidaFavorita:2");
		Assert.assertTrue(radioMarcado);

	}
	
	@Test
	public void selecionaComboBox()
	{
		dsl.selecionaCombo("elementosForm:escolaridade", "Superior");
		Assert.assertEquals("Superior", dsl.obterValorCombo("elementosForm:escolaridade"));
	} 
	
	
	@Test
	public void verificaIndexCombo() 
	{
		WebElement element = getDriver().findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select (element);
		List<WebElement> options= combo.getOptions();
		Assert.assertEquals(8, options.size());
		
		boolean encontrou = false;
		
		for(WebElement option:options) {
			if(option.getText().equals("Superior")) {
				encontrou=true;
				break;
			}
		}
		
		Assert.assertTrue(encontrou);
		
	}
	
	
	@Test
	public void verificaComboMultiplo() 
	{
		dsl.selecionaCombo("elementosForm:esportes", "Natacao");
		dsl.selecionaCombo("elementosForm:esportes", "Corrida");
		dsl.selecionaCombo("elementosForm:esportes", "O que eh esporte?");
		
		List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());
		
		dsl.deselecionarCombo("elementosForm:esportes", "Corrida");
		opcoesMarcadas=dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao","O que eh esporte?")));
	}
	
	@Test
	public void deveInteragirComBotao() 
	{
		dsl.clicarBotao("buttonSimple");
		Assert.assertEquals("Obrigado!", dsl.obterValueElemento("buttonSimple"));
		
	}

	@Test
	public void deveInteragirComLink() 
	{
		dsl.clicarLink("Voltar");
		
		Assert.assertEquals("Voltou!",dsl.obterTexto("resultado"));	
	}

	@Test
	public void deveBuscarTextosNaPagina()
	{		
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		
		String verificaSpan = dsl.obterTexto(By.className("facilAchar"));
		
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", verificaSpan);
		
		//driver.findElement(By.tagName("body"));
	}
	
	@Test
	public void testeJavascript()
	{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			//js.executeScript("alert('Testando js via selenium') ");
			js.executeScript("document.getElementById('elementosForm:nome').value='Escrito via js'");
			js.executeScript("document.getElementById('elementosForm:sobrenome').type='radio'");
			
			WebElement element = getDriver().findElement(By.id("elementosForm:nome"));
			js.executeScript("arguments[0].style.border = arguments[1]", element, "solid  4px red");
			
	}
	
	@Test
	public void deveClicarBotaoTabela()
	{
		dsl.clicarBotaoTabela("Nome", "Maria", "Botao", "elementosForm:tableUsuarios");
	}
	
}

