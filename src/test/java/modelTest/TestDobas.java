package modelTest;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import model.Dobas;

public class TestDobas {

	private static Dobas dobas;
	
	@BeforeClass
	public static void beforeClass(){
		dobas= new Dobas();
	}
	
	@Test
	public void tesztGetSetPont(){
		dobas.setPont(20);
		assertEquals(20, dobas.getPont());
	}
	
	@Test
	public void tesztGetSetJelzo(){
		dobas.setJelzo("S");
		assertEquals("S", dobas.getJelzo());
		assertNotEquals("D", dobas.getJelzo());
		assertNotEquals("T", dobas.getJelzo());
		dobas.setJelzo("D");
		assertEquals("D", dobas.getJelzo());
		dobas.setJelzo("T");
		assertEquals("T", dobas.getJelzo());
	}
	
	@Test
	public void tesztTeljesPont(){
		dobas.setJelzo("D");
		dobas.setPont(10);
		assertEquals(20, dobas.getTeljesPont());
	}
	
}
