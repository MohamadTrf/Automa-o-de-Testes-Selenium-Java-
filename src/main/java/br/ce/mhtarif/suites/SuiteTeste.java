package br.ce.mhtarif.suites;

import static br.ce.mhtarif.core.DriverFactory.killDriver;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.mhtarif.test.TesteCadastro;
import br.ce.mhtarif.test.TesteRegrasCadastro;

@RunWith(Suite.class)
@SuiteClasses({ // quais classes serão executadas
	TesteCadastro.class ,
	TesteRegrasCadastro.class
})
public class SuiteTeste {
	
	@AfterClass
	public static void finalizaTudo() {
		 killDriver();
	}

}
