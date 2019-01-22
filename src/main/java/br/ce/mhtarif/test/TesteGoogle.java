package br.ce.mhtarif.test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class TesteGoogle {
	
	@Test
	public void teste() {
		
	//	System.setProperty("webdriver.gecko.driver","C:\\Drivers\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		//driver.manage().window().maximize();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("https://google.com");
		System.out.println(driver.getTitle());
		Assert.assertEquals("Google", driver.getTitle()); // primeiro valor seria o resultado esperado para o teste ser verdadeiro e o segundo e o valor que o teste irá trazer
		driver.quit();
	}

}
