package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import view.MainApp;

/**
 * A játék vezérlésére szolgáló osztály {@code UI}-nek szükséges adatok
 * ellátására is szolgál.
 * 
 * @author Gyulai Zsolt
 *
 */
public class JatekVezerlo {
	/**
	 * A naplózáshoz szükséges logger.
	 */
	static private Logger logger = LoggerFactory.getLogger(JatekVezerlo.class);

	/**
	 * Játékosokat tartalmazó lista.
	 */
	private List<Jatekos> jatekosok = new ArrayList<>();

	/**
	 * Az aktuális játéktípusa.
	 */
	private String jatekTipus;

	/**
	 * A játék pontozásához szükséges érték.
	 */
	private int jatekPont;

	/**
	 * A dobni következő játékos sorszáma.
	 */
	private int kovetkezoJatekos = 0;

	/**
	 * Az osztály példánya.
	 */
	private static JatekVezerlo instance = null;

	/**
	 * Akutális dobások száma.
	 */
	private int dobas = 0;

	/**
	 * A jaték módokat tartalmazó lista.
	 */
	private final static List<String> Jatekmodok = new ArrayList<>(Arrays.asList("301", "501", "701", "901"));

	/**
	 * A {@code MainApp} való hivatkozás.
	 */
	private MainApp mainApp;
	
	/**
	 * Akkor {@code true} ha felvettük a játékosokat és elkezdtük egyébként {@code false}.
	 */
	private boolean aktivJatek=false;
	
	/**
	 * Lekérdezi az aktivJatek változó értékét.
	 * @return aktivJatek értékét adja vissza1
	 */
	public boolean isAktivJatek() {
		return aktivJatek;
	}

	/**
	 * Beállítja a paraméterül kapot értéket azaktivJatek-ra.
	 * @param aktivJatek a kapott paraméter {@code true} vagy {@code false}
	 */
	public void setAktivJatek(boolean aktivJatek) {
		this.aktivJatek = aktivJatek;
	}

	/**
	 * Ajátékvezérlő konstruktora.
	 */
	private JatekVezerlo() {

	}

	/**
	 * A mentéshez és betöltéshez szükséges elérésiútvonal készítése.
	 * 
	 * @return path visszatér az elérési úttal
	 */
	private String createPath() {
		String path = "";

		path = System.getProperty("user.home") + System.getProperty("file.separator") + ".darts"
				+ System.getProperty("file.separator");

		return path;
	}

	/**
	 * Betölt egy játékállapotot a vezérlőbe.
	 */
	public void load() {
		vegeVan();
		String path = createPath() + "darts.xml";
		File file = new File(path);
		
		 if (file.exists()) {
		 
		SaveLoad loadAllapot = new SaveLoad(path);
		JatekAllapot jatekAllapot = loadAllapot.load();		
		setJatekosok(jatekAllapot.getJatekosok());	
		setJatekTipus(jatekAllapot.getJatekTipus());
		setJatekPont(jatekAllapot.getJatekPont());
		setKovetkezoJatekos(jatekAllapot.getKovetkezoJatekos());
		setDobas(jatekAllapot.getDobas());
		
		if(mainApp !=null){
		mainApp.UdpdatePerson(jatekosok);
		mainApp.ActivateGame();
		}
		 }
	}

	public List<Jatekos> getJatekosok() {
		return jatekosok;
	}

	/**
	 * Kimenti az aktuális játékállapotot.
	 */
	public void save() {

		String path = createPath();
		File file = new File(path);

		if (!file.exists()){ file.mkdirs(); }
	
		SaveLoad saveAllapot = new SaveLoad(path+"darts.xml");
		JatekAllapot jatekAllapot = new JatekAllapot();

		jatekAllapot.setDobas(getDobas());
		jatekAllapot.setJatekosok(jatekosok);
		jatekAllapot.setJatekPont(getJatekPont());
		jatekAllapot.setJatekTipus(getJatekTipus());
		jatekAllapot.setKovetkezoJatekos(getKovetkezoJatekos());

		saveAllapot.save(jatekAllapot);

	}

	/**
	 * Beállítja az aktuális dobást.
	 * 
	 * @param dobas
	 *            au aktuális dobás száma
	 */
	public void setDobas(int dobas) {
		this.dobas = dobas;
	}

	/**
	 * A játék eredményéhez szolgáló érték lekérdezése.
	 * 
	 * @return visszatért pontszám
	 */
	public int getJatekPont() {
		return jatekPont;
	}

	/**
	 * A játék eredményéhez szolgáló érték beállítása.
	 * 
	 * @param jatekPont
	 *            a beállítani kívánt érték
	 */
	public void setJatekPont(int jatekPont) {
		this.jatekPont = jatekPont;
	}

	/**
	 * Beállít egy {@link MainApp}való hivatkozást.
	 * 
	 * @param mainApp
	 *            a MainApp referenciája
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * Elíndítja a grafikusfelület vezérlőjét.
	 */
	public void run() {
		MainApp.main(null);
	}

	/**
	 * Osztály példányának a lekérése.
	 * 
	 * @return az osztály példánya
	 */
	public static JatekVezerlo getInstance() {
		if (instance == null) {
			instance = new JatekVezerlo();
			logger.info("New Jatekvezerlo");
		}
		return instance;
	}

	/**
	 * Játékos hozzáadása a listához , és a játékosok továbbítása a grafikus
	 * felülethez.
	 * 
	 * @param jatekos
	 *            az új játékos
	 */
	public void addJatekos(Jatekos jatekos) {
		jatekosok.add(jatekos);
		logger.info(jatekos.getNeve() + " added to players.");
		mainApp.UdpdatePerson(jatekosok);

	}

	/**
	 * Aktuális dobás lekérdezése.
	 * 
	 * @return dobas az aktuális dobás
	 */
	public int getDobas() {
		return dobas;
	}

	/**
	 * Dobások számának nullázása.
	 */
	private void resetDobas() {
		this.dobas = 0;

	}

	/**
	 * Megvizsgálja ,hogy a névnek szolgló érték nem e üres.
	 * 
	 * @param nev a vizsgálandó név
	 * @return a vizsgálat eredménye
	 */
	public boolean isNev(String nev) {
		return !nev.isEmpty();
	}

	/**
	 * Dobási javaslat lekérdezése a játékmód és az aktuális játékos
	 * függvényében.
	 * 
	 * @param jatekmod  a játékmód
	 * @param jatekos a javaslatra szorulójátékos
	 * @return tanacs az adott tanács
	 */
	public String getJavaslat(String jatekmod, Jatekos jatekos) {
		String tanacs = "";
		if (jatekmod.substring(1, 3).equals("01")) {
			normalDarts nd = new normalDarts();
			if (3 - getDobas() > 0 && getJatekPont() - jatekos.getPontjai() > 0) {
				tanacs = nd.addSuggestion(3 - getDobas(), (getJatekPont() - jatekos.getPontjai()));
			}
		}

		return tanacs;
	}

	/**
	 * A játék állapotának ürítése.
	 */
	public void vegeVan() {

		jatekosok.clear();
		resetDobas();
		setJatekPont(0);
		setJatekTipus("301");
		setKovetkezoJatekos(0);
		if(mainApp != null){
		mainApp.UdpdatePerson(jatekosok);
		mainApp.showDartsOverview();
		}
	}

	/**
	 * Vizsgálja hogy az adott játékmódnak megfelelően nem dobott e túl sokat
	 * vagy nem e győzött.
	 * 
	 * @param jatekos
	 *            aktuális játékos
	 * @param dobas
	 *            a játékos dobása
	 */
	public void jatekIranyito(Jatekos jatekos, Dobas dobas) {
		normalDarts nDarts = new normalDarts();

		boolean tooMuhc = false;
		boolean isWinner = false;
		tooMuhc = nDarts.tooMuch(getJatekPont(), jatekos.getPontjai());
		isWinner = nDarts.isWinner(getJatekPont(), jatekos.getPontjai(), dobas);
		if (tooMuhc) {
			nDarts.removeThrows(jatekos, getDobas());
			kiaKovetkezo(tooMuhc, isWinner);
			logger.warn(jatekos.getNeve() + " Too much!");
		}
		if (!tooMuhc && !isWinner) {
			kiaKovetkezo(tooMuhc, isWinner);
			mainApp.UpdateSuggesion();

		}
		if (!isWinner)
			mainApp.UdpdatePerson(jatekosok);
		if (isWinner) {
			mainApp.showEndDialog(jatekos);

			}
	}

	/**
	 * Új dobás felvétel egy játékos dobásainak listájához.
	 * 
	 * @param jatekos
	 *            az adott játékos
	 * @param dobas
	 *            az adott játékos dobása
	 */
	public void addDobasJatekoshoz(Jatekos jatekos, Dobas dobas) {
		jatekos.addDobasok(dobas);
		logger.trace(jatekos.getNeve() + " point.");
		jatekIranyito(jatekos, dobas);

	}

	/**
	 * Játékosok listájának beállítása egy adott lista alapján.
	 * 
	 * @param jatekosok játékosokat tartalmazó lisa.
	 */
	public  void setJatekosok(List<Jatekos> jatekosok) {
		this.jatekosok.addAll(jatekosok);
	}

	/**
	 * Eldönti ki a következő játékos az alapján ,hogy az aktuális játékos
	 * ,hogyan teljesített és hanyadik dobása volt.
	 * 
	 * @param tM az aktuális játékos túl sokat dobott e
	 * @param iW az aktuális játékos nem győzott e
	 */
	private void kiaKovetkezo(boolean tM, boolean iW) {
		this.dobas++;
		if (!tM) {
			if (getDobas() >= 3) {
				setKovetkezoJatekos((getKovetkezoJatekos() + 1) % jatekosok.size());
				resetDobas();
			}
		} else {
			setKovetkezoJatekos((getKovetkezoJatekos() + 1) % jatekosok.size());
			resetDobas();
		}

	}

	/**
	 * A játékmódok listájának lekérdezésére.
	 * 
	 * @return Jatekmodok a játékmódok listája
	 */
	public static final List<String> getJatekmodok() {
		return Jatekmodok;
	}

	/**
	 * A játék típusának lekérdezése.
	 * 
	 * @return jetekTipus a vissza adott játék ítőus
	 */
	public String getJatekTipus() {
		return jatekTipus;
	}

	/**
	 * A játék módjának a beállítása és ez alapján a számoláshoz szükséges
	 * pontszám beállítása.
	 * 
	 * @param jatekTipus a játékmód típusa
	 */
	public void setJatekTipus(String jatekTipus) {
		this.jatekTipus = jatekTipus;
		if (!jatekTipus.equals("") && jatekTipus.substring(1, 3).equals("01"))
			setJatekPont(Integer.parseInt(jatekTipus));

	}

	/**
	 * A következő játékos lekérdezése.
	 * 
	 * @return visszatér az adott játékos sorszámával.
	 */
	public int getKovetkezoJatekos() {
		return kovetkezoJatekos;
	}

	/**
	 * A következő játékos beállítása.
	 * 
	 * @param kovetkezoJatekos sorszámának beállítása.
	 */
	public void setKovetkezoJatekos(int kovetkezoJatekos) {
		this.kovetkezoJatekos = kovetkezoJatekos;
	}

}
