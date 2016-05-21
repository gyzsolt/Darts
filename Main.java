package model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A játék Main osztálya a játék indítására szolgál.
 * 
 * @author Gyulai Zsolt
 *
 */
public class Main {
	/**
	 * A naplózáshoz szükséges logger.
	 */
	static private Logger logger = LoggerFactory.getLogger(Main.class);
	/**
	 * A program main metódusa.
	 * @param args parancssori argumentumok
	 */
	public static void main(String[] args) {

		logger.info("A program elindult");
		JatekVezerlo.getInstance().run();
	}

}
