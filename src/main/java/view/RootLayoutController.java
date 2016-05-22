package view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;

import javafx.scene.control.MenuItem;

import model.JatekVezerlo;

/**
 * A gyökér ablak vezérlője.
 * 
 * @author Gyulai Zsolt
 *
 */

public class RootLayoutController {

	/**
	 * A naplózáshoz szükséges logger.
	 */
	static private Logger logger = LoggerFactory.getLogger(RootLayoutController.class);
	@FXML
	private MenuItem saveMenu;

	@FXML
	private MenuItem loadMenu;

	@FXML
	private MenuItem newMenu;

	@FXML
	private void initialize() {

	}

	@FXML
	private void save() {
		if (JatekVezerlo.getInstance().isAktivJatek()) {
			JatekVezerlo.getInstance().save();
		}

	}

	@FXML
	private void load() {
		logger.info("Be akar tölteni egy játékot.");
		JatekVezerlo.getInstance().load();
	}

	@FXML
	private void newGame() {
		JatekVezerlo.getInstance().setAktivJatek(false);
		JatekVezerlo.getInstance().vegeVan();
	}

}