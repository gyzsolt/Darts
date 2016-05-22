package modelTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import model.Dobas;
import model.JatekAllapot;
import model.JatekVezerlo;
import model.Jatekos;

public class TestJatekVezerlo {


	private static JatekAllapot jatekAllapot;
	
	@BeforeClass
	public static void beforeClass(){
		
		Jatekos Kati = new Jatekos("Kati");
		Jatekos Sanyi = new Jatekos("Sanyi");

		Kati.addDobasok(new Dobas(20, "T"));
		Kati.addDobasok(new Dobas(20, "T"));
		Kati.addDobasok(new Dobas(20, "S"));

		Sanyi.addDobasok(new Dobas(6, "S"));
		Sanyi.addDobasok(new Dobas(17, "D"));
		Sanyi.addDobasok(new Dobas(19, "S"));

		String jatekTipus = "301";
		int jatekPont = 301;
		int kovetkezoJatekos = 0;
		int dobas = 0;

		List<Jatekos> jatekosok = new ArrayList<>();
		jatekosok.add(Kati);
		jatekosok.add(Sanyi);

		jatekAllapot = new JatekAllapot();
		jatekAllapot.setDobas(dobas);
		jatekAllapot.setJatekosok(jatekosok);
		jatekAllapot.setJatekPont(jatekPont);
		jatekAllapot.setJatekTipus(jatekTipus);
		jatekAllapot.setKovetkezoJatekos(kovetkezoJatekos);
		
	}
	
	private void loader(){			
		List<Jatekos> jatekosk = new ArrayList<>();
		
		jatekosk.addAll(jatekAllapot.getJatekosok());
		JatekVezerlo.getInstance().setJatekosok(jatekosk);	
		JatekVezerlo.getInstance().setJatekTipus(jatekAllapot.getJatekTipus());
		JatekVezerlo.getInstance().setJatekPont(jatekAllapot.getJatekPont());
		JatekVezerlo.getInstance().setKovetkezoJatekos(jatekAllapot.getKovetkezoJatekos());
		JatekVezerlo.getInstance().setDobas(jatekAllapot.getDobas());
	}
	
	@Test
	public void testIsName(){
		assertTrue(JatekVezerlo.getInstance().isNev("Balalajka"));
		assertFalse(JatekVezerlo.getInstance().isNev(""));

	}
	
	/*@Test
	public void testAddJatekos(){
	}
	
	*/
	
	@Test
	public void testSaveLoad(){
		loader();
		
		JatekVezerlo.getInstance().save();
		
		JatekVezerlo.getInstance().setJatekTipus("Blabla");
		JatekVezerlo.getInstance().setJatekPont(111);
		JatekVezerlo.getInstance().setKovetkezoJatekos(-1);
		JatekVezerlo.getInstance().setDobas(99);
		
		JatekVezerlo.getInstance().load();
		
		assertEquals(jatekAllapot.getJatekosok(), JatekVezerlo.getInstance().getJatekosok());
		assertEquals(jatekAllapot.getJatekPont(), JatekVezerlo.getInstance().getJatekPont());
		assertEquals(jatekAllapot.getJatekTipus(), JatekVezerlo.getInstance().getJatekTipus());
		assertEquals(jatekAllapot.getKovetkezoJatekos(), JatekVezerlo.getInstance().getKovetkezoJatekos());
		assertEquals(jatekAllapot.getDobas(), JatekVezerlo.getInstance().getDobas());
		
		
		
	}
}
