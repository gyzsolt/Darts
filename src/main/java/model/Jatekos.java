package model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A Játékos ábrázolására szolgáló osztály. ami tartalmazza a nevét, és a dobásait.
 * 
 * @author Gyulai Zsolt
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Jatekos {

	/**
	 * A játékos neve.
	 */
	@XmlElement(nillable = false, required = true)
	private String neve;

	/**
	 * A játékos dobásainak a listája.
	 */
	@XmlElementWrapper(name = "dobasok")
	@XmlElement(name = "dobas")
	private List<Dobas> dobasok = new ArrayList<>();

	/**
	 * Üres konstruktor a játékoshoz.
	 */
	public Jatekos() {
	}

	/**
	 * Konstruktor a játékos nevével való létrehozásához.
	 * @param neve a játékos neve
	 */
	public Jatekos(String neve) {
		this.neve = neve;
	}

	/**Vissza adja a játékos nevét.
	 * 
	 * @return  neve a játékosnak
	 */
	public String getNeve() {
		return neve;
	}

	/**
	 * Beállítja a játékos nevét.
	 * @param neve a játékosnak
	 */
	public void setNeve(String neve) {
		this.neve = neve;
	}

	/**
	 * A játékosok dobásainak listájának beállítására szolgál.
	 * @return dobasok a jtékos dobásai
	 */
	public List<Dobas> getDobasok() {
		return dobasok;
	}

	/**
	 * Hozzá ad egy dobást a játékos listájához.
	 * @param dobas a hozzá adott dobás
	 */
	public void addDobasok(Dobas dobas) {
		dobasok.add(dobas);
	}

	/**
	 * A játékos álltal elvégzett dobások összértéke ami a pont és a jelző szorzatából áll elő.
	 * @return osszeg a kiszámolt dobások értéke
	 */
	public int getPontjai() {
		int osszeg = 0;

		for (Dobas dobas : this.dobasok) {
			osszeg += dobas.getTeljesPont();
		}
		return osszeg;
	}

}
