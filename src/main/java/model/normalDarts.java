package model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * A darts játék hagyományos dupla kiszálós játékmódjának megvalósítására szolgáló osztály.
 * 
 * @author Gyulai Zsolt
 *
 */
public class normalDarts {
	/**
	 * A naplózáshoz szükséges logger.
	 */
	static private Logger logger = LoggerFactory.getLogger(Main.class);
	
	/**
	 * A játék tanácsadáshoz felhasználandó {@code HashMap}. 
	 */
	private final HashMap<Integer, List<String>> scoreboard = new HashMap<>();

	/**
	 * Konstruktor az osztályhoz ami meghívja a {@link #normalDarts()} függvényt.
	 */
	public normalDarts() {
		loadScoreBoard();
		logger.info("ScoreBoard fel lett töltve.");
	}
	/**
	 * Eldönti ,hogy a játékos álltal végzett dobásokkal nem lépte-e át a játékszabályban megengedettet.
	 * @param max a megengedett legnagyobb érték
	 * @param van a játékos aktuális pontjainak a száma
	 * @return  visszatér a feltétel vizsgálatának az eredményével
	 */
	public boolean tooMuch(int max, int van) {
		if ((max - van) < 0 || (max - van) == 1) {
			return true;
		} else
			return false;

	}
	/**
	 * Ha a játékos túl sokat dobott akkor vissza állítja a pontjainak az értékét a darts szabályainak megfelelően úgy, hogy eltávolítja az adott körben végzett dobásait.
	 * 
	 * @param jatekos a vizsgált játékos
	 * @param dobasSzam a vizsgálandó dobásokszáma
	 */
	public void removeThrows(Jatekos jatekos, int dobasSzam){
		for (int i = 0; i <= dobasSzam; i++)
			 jatekos.getDobasok().remove((jatekos.getDobasok().size() - 1));
	}
	
	/**
	 * Eldonti hogy a dobás győztes dobás volt e azaz megfelet a darts szabályainak.
	 * @param max győzelemhez szükséges pontok
	 * @param van a játékos pontszáma
	 * @param dobas a dobás melyből megállapítható, hogy dupla e
	 * @return visszatér a feltétel vizsgálatának eredményével
	 */
	public boolean isWinner(int max, int van, Dobas dobas) {
		if (max - van == 0 && dobas.getJelzo().equals("D")) {
			return true;
		} else
			return false;

	}

	/**
	 * Tanácsot ad a játékosnak ahoz, hogy mit lenne érdemes dobnia az alapján ,hogy hány pont kell még neki és hány dobással rendelkezik az adott körben.
	 * 
	 * @param dobasok dobásainak száma
	 * @param pontKell szükséges pontok
	 * @return vissza adja a javaslatot
	 */
	public String addSuggestion(int dobasok, int pontKell) {
		String javaslat = "";
		//if(pontKell>1)
		if (pontKell <= 100) {
			switch (dobasok) {
			case 1:
				if (scoreboard.get(pontKell).size() >= dobasok)
					javaslat = scoreboard.get(pontKell).get(0);
				break;
			case 2:
				if (scoreboard.get(pontKell).size() >= dobasok) {
					javaslat = scoreboard.get(pontKell).get(0) + scoreboard.get(pontKell).get(1);
				} else {
					javaslat = scoreboard.get(pontKell).get(0);
				}
				break;
			case 3:
				if (scoreboard.get(pontKell).size() == 1)
					javaslat = scoreboard.get(pontKell).get(0);
				if (scoreboard.get(pontKell).size() == 2)
					javaslat = scoreboard.get(pontKell).get(0) + scoreboard.get(pontKell).get(1);
				if (scoreboard.get(pontKell).size() == 3)
					javaslat = scoreboard.get(pontKell).get(0) + scoreboard.get(pontKell).get(1) + scoreboard.get(pontKell).get(2);

				break;
			default:
				break;
			}
		} else {
			switch (dobasok) {
			case 1:
				javaslat = "T20";
				break;
			case 2:
				javaslat = "T20D19";
				break;
			case 3:
				javaslat = "T20D10T5";
				break;
			default:
				break;
			}
		}
		/*
		 * switch (dobasok) { case 1: javaslat = seged(pont); break; case 2: if
		 * (pont <= 40) { javaslat = seged(pont); } else { javaslat = seged(pont
		 * - 40) + " " + seged(pont); }
		 * 
		 * break; case 3: if (pont > 181) { javaslat = "T20 T20 T20"; break; }
		 * if (pont <= 40) { javaslat = seged(pont); } if(pont<=121){ javaslat =
		 * seged(pont - 40) + " " + seged(pont); } if(pont>121) javaslat=
		 * "T20 T20"+seged(pont-120); break;
		 * 
		 * default: break; }
		 */
		return javaslat;
	}
/**
 * A tanácsadáshoz szolgáló táblázat feltöltése.
 */
	private void loadScoreBoard() {

		scoreboard.put(2, Arrays.asList("D1"));
		scoreboard.put(3, Arrays.asList("S1", "D1"));
		scoreboard.put(4, Arrays.asList("D2"));
		scoreboard.put(5, Arrays.asList("S1", "D2"));
		scoreboard.put(6, Arrays.asList("D3"));

		scoreboard.put(7, Arrays.asList("S3", "D2"));
		scoreboard.put(8, Arrays.asList("D4"));
		scoreboard.put(9, Arrays.asList("S1", "D4"));
		scoreboard.put(10, Arrays.asList("D5"));
		scoreboard.put(11, Arrays.asList("D3", "D4"));

		scoreboard.put(12, Arrays.asList("D6"));
		scoreboard.put(13, Arrays.asList("S5", "D4"));
		scoreboard.put(14, Arrays.asList("D7"));
		scoreboard.put(15, Arrays.asList("S7", "D4"));
		scoreboard.put(16, Arrays.asList("D8"));

		scoreboard.put(17, Arrays.asList("S1", "D8"));
		scoreboard.put(18, Arrays.asList("D9"));
		scoreboard.put(19, Arrays.asList("S3", "D8"));
		scoreboard.put(20, Arrays.asList("D10"));
		scoreboard.put(21, Arrays.asList("S5", "D8"));

		scoreboard.put(22, Arrays.asList("D11"));
		scoreboard.put(23, Arrays.asList("S7", "D8"));
		scoreboard.put(24, Arrays.asList("D12"));
		scoreboard.put(25, Arrays.asList("S9", "D8"));
		scoreboard.put(26, Arrays.asList("D13"));

		scoreboard.put(27, Arrays.asList("S11", "D8"));
		scoreboard.put(28, Arrays.asList("D14"));
		scoreboard.put(29, Arrays.asList("S13", "D8"));
		scoreboard.put(30, Arrays.asList("D15"));
		scoreboard.put(31, Arrays.asList("S15", "D8"));

		scoreboard.put(32, Arrays.asList("D16"));
		scoreboard.put(33, Arrays.asList("S17", "D8"));
		scoreboard.put(34, Arrays.asList("D17"));
		scoreboard.put(35, Arrays.asList("S3", "D16"));
		scoreboard.put(36, Arrays.asList("D19"));

		scoreboard.put(37, Arrays.asList("S5", "D16"));
		scoreboard.put(38, Arrays.asList("D19"));
		scoreboard.put(39, Arrays.asList("S3", "D18"));
		scoreboard.put(40, Arrays.asList("D20"));
		scoreboard.put(41, Arrays.asList("S9", "D16"));

		scoreboard.put(42, Arrays.asList("S6", "D18"));
		scoreboard.put(43, Arrays.asList("S3", "D20"));
		scoreboard.put(44, Arrays.asList("S4", "D20"));
		scoreboard.put(45, Arrays.asList("S13", "D16"));
		scoreboard.put(46, Arrays.asList("S10", "D18"));

		scoreboard.put(47, Arrays.asList("S7", "D20"));
		scoreboard.put(48, Arrays.asList("S16", "D16"));
		scoreboard.put(49, Arrays.asList("S9", "D120"));
		scoreboard.put(50, Arrays.asList("S10", "D20"));
		scoreboard.put(51, Arrays.asList("S15", "D20"));

		scoreboard.put(52, Arrays.asList("T12", "D8"));
		scoreboard.put(53, Arrays.asList("S17", "D18"));
		scoreboard.put(54, Arrays.asList("S14", "D20"));
		scoreboard.put(55, Arrays.asList("S15", "D20"));
		scoreboard.put(56, Arrays.asList("T16", "D4"));

		scoreboard.put(57, Arrays.asList("S17", "D20"));
		scoreboard.put(58, Arrays.asList("S18", "D20"));
		scoreboard.put(59, Arrays.asList("S19", "D20"));
		scoreboard.put(60, Arrays.asList("S20", "D20"));
		scoreboard.put(61, Arrays.asList("T11", "D14"));

		scoreboard.put(62, Arrays.asList("T12", "D13"));
		scoreboard.put(63, Arrays.asList("T13", "D12"));
		scoreboard.put(64, Arrays.asList("T14", "D11"));
		scoreboard.put(65, Arrays.asList("T15", "D10"));
		scoreboard.put(66, Arrays.asList("T18", "D16"));

		scoreboard.put(67, Arrays.asList("T17", "D8"));
		scoreboard.put(68, Arrays.asList("T18", "D7"));
		scoreboard.put(69, Arrays.asList("T19", "D6"));
		scoreboard.put(70, Arrays.asList("T20", "D5"));
		scoreboard.put(71, Arrays.asList("T19", "D7"));

		scoreboard.put(72, Arrays.asList("T20", "D6"));
		scoreboard.put(73, Arrays.asList("T19", "D8"));
		scoreboard.put(74, Arrays.asList("T14", "D16"));
		scoreboard.put(75, Arrays.asList("T17", "D12"));
		scoreboard.put(76, Arrays.asList("T16", "D14"));

		scoreboard.put(77, Arrays.asList("T19", "D10"));
		scoreboard.put(78, Arrays.asList("T18", "D12"));
		scoreboard.put(79, Arrays.asList("T13", "D20"));
		scoreboard.put(80, Arrays.asList("T20", "D10"));
		scoreboard.put(81, Arrays.asList("T19", "D12"));

		scoreboard.put(82, Arrays.asList("T14", "D20"));
		scoreboard.put(83, Arrays.asList("T17", "D16"));
		scoreboard.put(84, Arrays.asList("T20", "D12"));
		scoreboard.put(85, Arrays.asList("T15", "D20"));
		scoreboard.put(86, Arrays.asList("T18", "D16"));

		scoreboard.put(87, Arrays.asList("T17", "D18"));
		scoreboard.put(88, Arrays.asList("T20", "D14"));
		scoreboard.put(89, Arrays.asList("T19", "D16"));
		scoreboard.put(90, Arrays.asList("T8", "D18"));
		scoreboard.put(91, Arrays.asList("T17", "D20"));

		scoreboard.put(92, Arrays.asList("T20", "D16"));
		scoreboard.put(93, Arrays.asList("T19", "D18"));
		scoreboard.put(94, Arrays.asList("T18", "D20"));
		scoreboard.put(95, Arrays.asList("T19", "D19"));
		scoreboard.put(96, Arrays.asList("T20", "D18"));

		scoreboard.put(97, Arrays.asList("T19", "D20"));
		scoreboard.put(98, Arrays.asList("T20", "D19"));
		scoreboard.put(99, Arrays.asList("T19", "S10", "D16"));
		scoreboard.put(100, Arrays.asList("T20", "D20"));
	}
}
