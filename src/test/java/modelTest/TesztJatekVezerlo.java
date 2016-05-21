package modelTest;

import static org.junit.Assert.*;


import org.junit.Test;

import model.JatekVezerlo;

public class TesztJatekVezerlo {

	private static JatekVezerlo jv;
	
	
	@Test
	public void tesztIsName(){
		assertTrue(jv.getInstance().isNev("Balalajka"));
		assertFalse(jv.getInstance().isNev(""));
	}
}
