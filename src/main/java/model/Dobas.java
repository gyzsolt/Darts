package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A játékos dobását ábrázolja.
 * 
 * @author Gyulai Zsolt
 *
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Dobas {

	/**
	 * A dobott pontot ábrázolja.
	 */
	@XmlElement(nillable = false, required = true)
	private int pont;

	/**
	 * A dobott pontszám szorzóját reprezentálja.
	 */
	@XmlElement(nillable = false, required = true)
	private String jelzo;

	/**
	 * Üres konstrutor a dobás oszályhoz.
	 */
	public Dobas() {
	};

	/**
	 * Konstruktor a dobás osztályhoz.
	 * 
	 * @param pont
	 *            A dobott pont.
	 * @param jelzo
	 *            A dobott pont szorzőja.
	 */
	public Dobas(int pont, String jelzo) {
		this.pont = pont;
		this.jelzo = jelzo;
	}

	/**
	 * Vissza adja a dobott pontot.
	 * 
	 * @return a visszadott pont
	 */
	public int getPont() {
		return pont;
	}

	/**
	 * Beállítja a dobott pontot
	 * 
	 * @param pont
	 *            a dobott pont
	 */
	public void setPont(int pont) {
		this.pont = pont;
	}

	/**
	 * Vissza adja a dobás jelzőjét.
	 * 
	 * @return jelzo a dobás szorzoja
	 */
	public String getJelzo() {
		return jelzo;
	}

	/**
	 * Beállítja a jelő értékét.
	 * 
	 * @param jelzo
	 *            értéke a jelzőnek
	 */
	public void setJelzo(String jelzo) {
		this.jelzo = jelzo;
	}

	/**
	 * Vissza adja a teljes pontszámot ami a dobás és a jelző szorzatából áll
	 * elő.
	 * 
	 * @return visszatér a teljes pontszámmal
	 */
	public int getTeljesPont() {
		int pontszam = 0;

		if (this.jelzo.equals("S"))
			pontszam = this.pont;

		if (this.jelzo.equals("D"))
			pontszam = this.pont * 2;

		if (this.jelzo.equals("T"))
			pontszam = this.pont * 3;

		return pontszam;
	}
}
