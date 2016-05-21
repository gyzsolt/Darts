package modelTest;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import model.Dobas;
import model.Jatekos;
import model.normalDarts;

public class TesztNormalDarts {

	private static normalDarts nD;
	
	
	@BeforeClass
	public static void beforeClass(){
		nD=new normalDarts();
	}

	
	@Test
	public void tesztTooMuch(){
		assertTrue(nD.tooMuch(301, 500));
		assertTrue(nD.tooMuch(301, 302));
		assertTrue(nD.tooMuch(301, 300));
		assertFalse(nD.tooMuch(301, 5));
	}
	
	@Test
	public void tesztIsWinner(){
		Dobas dobas = new Dobas(20,"D");
		
		assertTrue(nD.isWinner(301, 301, dobas));
		assertFalse(nD.isWinner(301, 300, dobas));
		assertFalse(nD.isWinner(301, 302, dobas));	
	}
	
	@Test
	public void tesztRemoveThrow(){
		Jatekos jatekos = new Jatekos("Sanyi");
		jatekos.addDobasok(new Dobas(20,"S"));
		jatekos.addDobasok(new Dobas(20,"D"));
		jatekos.addDobasok(new Dobas(20,"T"));
		
		nD.removeThrows(jatekos, 2);
		
		assertEquals(0, jatekos.getDobasok().size());
	}
	
	@Test
	public void tesztAddSuggestion(){
		
		assertEquals("T20", nD.addSuggestion(1, 300));
		assertEquals("T20D19", nD.addSuggestion(2, 300));
		assertEquals("T20D10T5", nD.addSuggestion(3, 300));
		
		assertEquals("S11D8", nD.addSuggestion(2, 27));
		
	}
}
