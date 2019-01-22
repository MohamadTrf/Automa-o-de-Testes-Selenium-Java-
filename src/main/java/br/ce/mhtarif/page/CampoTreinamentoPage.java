package br.ce.mhtarif.page;

import org.openqa.selenium.By;	
import br.ce.mhtarif.core.BasePage;

public class CampoTreinamentoPage extends BasePage{
	

	
	public void setNome(String nome)
	{
		dsl.escreve("elementosForm:nome", nome);
	}
	
	public void setSobrenome(String sobrenome)
	{
		dsl.escreve("elementosForm:sobrenome", sobrenome);
	}
	
	public void setSexoMasculino()
	{
		dsl.clicaRadio("elementosForm:sexo:0");
	}
	
	public void setSexoFeminino()
	{
		dsl.clicaRadio("elementosForm:sexo:1");
	}
	
	public void setComidaPizza()
	{
		dsl.clicaCheck("elementosForm:comidaFavorita:2");
	}
	
	public void setComidaCarne()
	{
		dsl.clicaCheck("elementosForm:comidaFavorita:0");
	}
	
	public void setComidaVegetariano()
	{
		dsl.clicaCheck("elementosForm:comidaFavorita:3");
	}
	
	public void setEscolaridade(String valor)
	{
		dsl.selecionaCombo("elementosForm:escolaridade", valor);
	}
	
	public void setEsportes(String... valores)
	{
		for(String valor: valores)
		dsl.selecionaCombo("elementosForm:esportes", valor);
	}
	
	public void cadastrar()
	{
		dsl.clicarBotao("elementosForm:cadastrar");
	}
	
	public String obterResultadoCadastro()
	{
		return dsl.obterTexto(By.xpath("//*[@id='resultado']/span"));
	}
	
	public String obterNomeCadastro()
	{
		return dsl.obterTexto(By.xpath("//*[@id='descNome']/span"));
	}
	
	public String obterSobrenomeCadastro()
	{
		return dsl.obterTexto(By.xpath("//*[@id='descSobrenome']/span"));
	}
	
	public String obterSexoCadastro()
	{
		return dsl.obterTexto(By.xpath("//*[@id='descSexo']/span"));
	} 
	
	
	public String obterComidaCadastro()
	{
		return dsl.obterTexto(By.xpath("//*[@id='descComida']/span"));
	}
	
	
	public String obterEscolaridadeCadastro()
	{
		return dsl.obterTexto(By.xpath("//*[@id='descEscolaridade']/span"));
	}
	
	public String obterEsporteCadastro()
	{
		return dsl.obterTexto(By.xpath("//*[@id='descEsportes']/span"));
	}
}
