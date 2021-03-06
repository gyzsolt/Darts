package model;


import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A játék állaptát ábrázolja SaveLoad osztály használja az általa készített XML álllományok ki- és betöltéséhez.
 * 
 * @author Gyulai Zsolt
 *
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class JatekAllapot {
	
/**
 * Játékosokat tartalmazó lista.
 */
	@XmlElementWrapper(name = "dobasok")
	@XmlElement(name = "dobas")
	private List<Jatekos> jatekosok;

	/**
	 * A játék típusára szolgáló megnevezés.
	 */
	
	@XmlElement(nillable=false, required=true)
	private String jatekTipus;

	/**
	 * A játék pontszámításához használt érték.
	 */
	@XmlElement(nillable=false, required=true)
	private int jatekPont;

	
	/**
	 * A játék során következő játékos sorszáma.
	 */
	@XmlElement(nillable=false, required=true)
	private int kovetkezoJatekos = 0;

	
	/**
	 * Az aktuális játékos hanyadik dobásnál tart.
	 */
	@XmlElement(nillable=false, required=true)
	private int dobas = 0;

	/**
	 * A játékosokat tartalmazó listát szolgáltatja.
	 * @return a vissza adott játékosok.
	 */
	public List<Jatekos> getJatekosok() {
		return jatekosok;
	}

	
	/**
	 * A játékosokat tartalmazó listát szolgáltatja.
	 * 
	 * @param jatekosok vissza adott játékosok listája
	 */
	public void setJatekosok(List<Jatekos> jatekosok) {
		this.jatekosok = jatekosok;
	}

	/**
	 * A játék típusát adja vissza.
	 * @return jatekTipus a vissza adott játéktipus
	 */
	public String getJatekTipus() {
		return jatekTipus;
	}

	
	/**
	 * Beállítja a játék típusát.
	 * @param jatekTipus a beállított játéktípus
	 */
	public void setJatekTipus(String jatekTipus) {
		this.jatekTipus = jatekTipus;
	}

	
	/**
	 * Beállítja a játék pontszámolásához szükséges értéke.
	 * @return jatekPont a beállított érték
	 */
	public int getJatekPont() {
		return jatekPont;
	}
	/**
	*Visszaadja játék pontszámolásához szükséges értéke. 
 	* @param jatekPont a viszza adott érték
 	*/
	public void setJatekPont(int jatekPont) {
		this.jatekPont = jatekPont;
	}

	/**
	 * Megadja a következő játékos sorszámát. 
	 * @return kovetkezoJatekos a következő játékos
	 */
	public int getKovetkezoJatekos() {
		return kovetkezoJatekos;
	}
	/**
	 * Beállítja a következő játékos sorszámát.
	 * @param kovetkezoJatekos soronkövetkező játékos sorszáma
	 */
	public void setKovetkezoJatekos(int kovetkezoJatekos) {
		this.kovetkezoJatekos = kovetkezoJatekos;
	}
	
	/**
	 * Megmondja hanyadik dobásnál tart a játékos.
	 * @return dobas melyik dobásnál tart
	 */
	public int getDobas() {
		return dobas;
	}

	/**
	 * Beállítja ,hogy hanyadik dobásnál tart a játékos.
	 * @param dobas az aktuális dobás sorszáma
	 */
	public void setDobas(int dobas) {
		this.dobas = dobas;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dobas;
		result = prime * result + jatekPont;
		result = prime * result + ((jatekTipus == null) ? 0 : jatekTipus.hashCode());
		result = prime * result + ((jatekosok == null) ? 0 : jatekosok.hashCode());
		result = prime * result + kovetkezoJatekos;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JatekAllapot other = (JatekAllapot) obj;
		if (dobas != other.dobas)
			return false;
		if (jatekPont != other.jatekPont)
			return false;
		if (jatekTipus == null) {
			if (other.jatekTipus != null)
				return false;
		} else if (!jatekTipus.equals(other.jatekTipus))
			return false;
		if (jatekosok == null) {
			if (other.jatekosok != null)
				return false;
		} else if (!jatekosok.equals(other.jatekosok))
			return false;
		if (kovetkezoJatekos != other.kovetkezoJatekos)
			return false;
		return true;
	}

	
	
}
