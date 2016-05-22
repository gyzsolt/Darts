package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.JatekVezerlo;
import model.Jatekos;


/**
 * Vége van ablak vezérlését alkotja.  
 * 
 * @author Gyulai Zsolt
 *
 */
public class EndOverviewController {

	@FXML
	private Label winnerLabel;

	@FXML
	private Button newButton;

	@FXML
	private Button closeButton;

	private Stage dialogStage;
	
	/**
	 * A kontroller konstruktora.
	 */
	public EndOverviewController() {

	}
	/**
	 * A kontroller inicializáló függvénye.
	 */	
	@FXML
	private void initialize() {
	}
	
	/**Beállítja a {@code dialogStage}.
	 * 
	 * @param dialogStage a kapott dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * Beállítja a megkapott győztes játékosnevét a árbeszéd anlakra.
	 * @param jatekos a kapott győztes játékos
	 */
	public void setWinner(Jatekos jatekos) {
		winnerLabel.setText(jatekos.getNeve()+"Nyert");
	}

	@FXML
	private void handleNew() {
		JatekVezerlo.getInstance().vegeVan();
		dialogStage.close();

	}

	@FXML
	private void handleClose() {
		dialogStage.close();
		Platform.exit();
		
	}

}