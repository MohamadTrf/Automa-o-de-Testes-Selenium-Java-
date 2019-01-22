package br.ce.mhtarif.test;

import static br.ce.mhtarif.core.DriverFactory.getDriver;
import static br.ce.mhtarif.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.ce.mhtarif.core.DSL;

public class TesteFramesEJanelas {
	

	
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
	public void deveInteragrirComFrames() 
	{	
		dsl.entrarFrame("frame1");
		dsl.clicarBotao("frameButton");
		String msg = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", msg);
		dsl.sairFrame();
		dsl.escreve("elementosForm:nome",msg);
	}
	
	@Test
	public void deveInteragirComFrameEscondido()
	{ 
		WebElement frame = getDriver().findElement(By.id("frame2"));
		dsl.executarJS("window.scrollBy(0, arguments[0] )", frame.getLocation().y);
		dsl.entrarFrame("frame2");
		dsl.clicarBotao("frameButton");
		String msg = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", msg);

	}
	
	@Test
	public void deveInteragirComJanela()
	{		
		getDriver().findElement(By.id("buttonPopUpEasy")).click();
		getDriver().switchTo().window("Popup");
		getDriver().findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		getDriver().close();
		getDriver().switchTo().window("");
		getDriver().findElement(By.tagName("textarea")).sendKeys("e agora?");
	}
	
	
	
	@Test
	public void deveInteragirComJanelaSemTitulo()
	{
		getDriver().findElement(By.id("buttonPopUpHard")).click();
		System.out.println(getDriver().getWindowHandle());
		System.out.println(getDriver().getWindowHandles());
	
		getDriver().switchTo().window((String) getDriver().getWindowHandles().toArray()[1]);//POP UP
		getDriver().findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		getDriver().switchTo().window((String) getDriver().getWindowHandles().toArray()[0]);//PAGINA PRINCIPAL
		getDriver().findElement(By.tagName("textarea")).sendKeys("E agora na pagina principal?");
	}
	
	
	
}
