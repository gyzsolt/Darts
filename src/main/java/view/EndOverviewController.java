package view;

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

	public EndOverviewController() {

	}

	@FXML
	private void initialize() {
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setWinner(Jatekos jatekos) {
		winnerLabel.setText(jatekos.getNeve());
	}

	@FXML
	private void handleNew() {
		JatekVezerlo.getInstance().vegeVan();
		dialogStage.close();

	}

	@FXML
	private void handleClose() {
		dialogStage.close();
	}

}