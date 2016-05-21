package modelTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import model.Dobas;
import model.Jatekos;

public class JatekosTeszt {

	private static Jatekos jatekos;
	
	
	@BeforeClass
	public static void beforeClass(){
		jatekos = new Jatekos();
		
		jatekos.addDobasok(new Dobas(10,"S"));
		jatekos.addDobasok(new Dobas(5,"D"));
		jatekos.addDobasok(new Dobas(20,"T"));
	}
	
	
	@Test
	public void TesztGetSetNeve(){
		jatekos.setNeve("Sanyi");
		assertEquals("Sanyi", jatekos.getNeve());
	}
	
	@Test
	public void TesztPontjai(){
		assertEquals(80, jatekos.getPontjai());
	}
	
	@Test
	public void TesztAddJatekos(){
		assertEquals(3, jatekos.getDobasok().size());
	}
	
	@Test
	public void TesztGetDobasok(){
		List<Dobas> dobasok=jatekos.getDobasok();
		assertTrue(dobasok.equals(jatekos.getDobasok()));
	}
}
