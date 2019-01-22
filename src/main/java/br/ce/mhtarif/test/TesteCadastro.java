package br.ce.mhtarif.test;

import static br.ce.mhtarif.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.mhtarif.core.BaseTest;
import br.ce.mhtarif.page.CampoTreinamentoPage;


public class TesteCadastro extends BaseTest{
	

private CampoTreinamentoPage page; 

	@Before // antes de cada teste execute o conteudo desse método
	public void inicializaTeste()
	{
		getDriver().get("file:///"+ System.getProperty("user.dir")+"/src/main/resources/componentes.html");
		page = new CampoTreinamentoPage();
	}
	

	
	@Test
	public void deveInteragirComCadastro () 
	{
		page.setNome("Mohamad");
		page.setSobrenome("Hicham Tarif");
		page.setSexoMasculino();
		page.setComidaPizza();
		page.setEscolaridade("Mestrado");
		page.setEsportes("Natacao");
		
//---Verificando se o cadastro está ok 		
		page.cadastrar();
		Assert.assertEquals("Cadastrado!",page.obterResultadoCadastro());
		Assert.assertEquals("Mohamad",page.obterNomeCadastro());

		Assert.assertEquals("Hicham Tarif", page.obterSobrenomeCadastro());
		Assert.assertEquals("Masculino", page.obterSexoCadastro());
		Assert.assertEquals("Pizza", page.obterComidaCadastro());
		Assert.assertEquals("mestrado", page.obterEscolaridadeCadastro());
		Assert.assertEquals("Natacao", page.obterEsporteCadastro());	
	}

	
}
