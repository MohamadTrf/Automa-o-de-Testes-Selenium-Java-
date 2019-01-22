package br.ce.mhtarif.core;

import static br.ce.mhtarif.core.DriverFactory.getDriver;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class DSL {
	
	

// Text Area e text field
	
	public void escreve(By by, String texto) 
	{
		getDriver().findElement(by).clear();
		getDriver().findElement(by).sendKeys(texto);
	}
	
	public void escreve(String id_campo, String texto)
	{
		escreve(By.id(id_campo),texto);
	}
	
	public String obterValorCampo (String id_campo)
	{
		return	getDriver().findElement(By.id(id_campo)).getAttribute("value");
	}
	
// Radio e Check Box
	
	public void clicaRadio(By by)
	{
		getDriver().findElement(by).click();
	}
	
	public void clicaRadio(String id_radio)
	{
		clicaRadio(By.id(id_radio));
	}
	
	public void clicaCheck(String id_check)
	{
		getDriver().findElement(By.id(id_check)).click();
	}
	
	public boolean isRadioMarcado(String id_radio)
	{
		return	getDriver().findElement(By.id(id_radio)).isSelected();
	}
	
	public boolean isCheckMarcado(String id_check)
	{
		return	getDriver().findElement(By.id(id_check)).isSelected();
	}

// Combo Box
	
	public void selecionaCombo(String id, String valor)
	{
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select (element);
		combo.selectByVisibleText(valor);
	}
	
	public void deselecionarCombo(String id, String valor)
	{
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select (element);
		combo.deselectByVisibleText(valor);		
	}
	
	
	
	public String obterValorCombo(String id)
	{
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select (element);
		return combo.getFirstSelectedOption().getText();
	}
	
	public List<String> obterValoresCombo(String id)
	{
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select (element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		List<String> valores = new ArrayList<String>();
		for(WebElement opcao: allSelectedOptions)
		{
			valores.add(opcao.getText());
		}
		
		return valores;
	}
	
	public int obterQuantidadeOpcoesCombo(String id )
	{
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select (element);
		List<WebElement> options = combo.getOptions();
		return options.size();
	}
	
	public boolean verificaOpcoesCombo(String id, String opcao)
	{
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select (element);
		List<WebElement> options = combo.getOptions();
		
		for(WebElement option: options) {
			if(option.getText().equals(opcao)) {
				return true;
			}
		}
		return false;
	}
	
	public void selecionarComboPrime(String radical, String valor)
	{
		clicaRadio(By.xpath("//*[@id='"+radical+"_input']/../..//span"));
		clicaRadio(By.xpath("//*[@id='"+radical+"_items']//li[.='"+valor+"']"));
	}
	
	
// Bot�o
	public void clicarBotao(String id)
	{
		getDriver().findElement(By.id(id)).click();
	}
	
	public String obterValueElemento(String id)
	{
		return getDriver().findElement(By.id(id)).getAttribute("value");
	}
	
// Link
	public void clicarLink(String link)
	{
		getDriver().findElement(By.linkText(link)).click();  // pega o valor que est� visivel no texto
	}
	
//Textos
	public String obterTexto(By by)
	{
		return getDriver().findElement(by).getText();
	}
	
	public String obterTexto(String id)
	{
		return obterTexto(By.id(id));
	}

//Alertas
	public String alertaObterTexto(){
		Alert alert = getDriver().switchTo().alert();
		return alert.getText();
	}
	
	public String alertaObterTextoEAceita(){
		Alert alert = getDriver().switchTo().alert();
		String valor = alert.getText();
		alert.accept();
		return valor;
		
	}
	
	public String alertaObterTextoENega(){
		Alert alert = getDriver().switchTo().alert();
		String valor = alert.getText();
		alert.dismiss();
		return valor;
		
	}
	
	public void alertaEscrever(String valor) {
		Alert alert = getDriver().switchTo().alert();
		alert.sendKeys(valor);
		alert.accept();
	}
	
//Frames e Janelas
	
	public void entrarFrame(String id)
	{
		getDriver().switchTo().frame(id);
	}
	
	public void sairFrame()
	{
		getDriver().switchTo().defaultContent();
	}
	
	public void trocarJanela(String id)
	{
		getDriver().switchTo().window(id);
	}
	
///JS
	
	public Object executarJS(String cmd, Object... param)
	{
		JavascriptExecutor js = (JavascriptExecutor) getDriver();			
		return	js.executeScript(cmd,param);	
	}
	
///Tabelas
	
	public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String idTabela){
		//procurar coluna do registro
		WebElement tabela = getDriver().findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']"));
		int idColuna = obterIndiceColuna(colunaBusca, tabela);
		
		//encontrar a linha do registro
		int idLinha = obterIndiceLinha(valor, tabela, idColuna);
		
		//procurar coluna do botao
		int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);
		
		//clicar no botao da celula encontrada
		WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
		celula.findElement(By.xpath(".//input")).click();
		
	}

	protected int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
		List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+idColuna+"]"));
		int idLinha = -1;
		for(int i = 0; i < linhas.size(); i++) {
			if(linhas.get(i).getText().equals(valor)) {
				idLinha = i+1;
				break;
			}
		}
		return idLinha;
	}

	protected int obterIndiceColuna(String coluna, WebElement tabela) {
		List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
		int idColuna = -1;
		for(int i = 0; i < colunas.size(); i++) {
			if(colunas.get(i).getText().equals(coluna)) {
				idColuna = i+1;
				break;
			}
		}
		return idColuna;
	}	
}
