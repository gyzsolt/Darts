package view;



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

	@FXML
	private MenuItem saveMenu;
	
	@FXML
	private MenuItem loadMenu;
	

	@FXML
	private void initialize() {

	}



	@FXML
	private void save(){
		JatekVezerlo.getInstance().save();
		
	}
	
	@FXML
	private void load(){
		JatekVezerlo.getInstance().load();
	}

}