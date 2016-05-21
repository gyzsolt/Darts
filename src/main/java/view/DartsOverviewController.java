package view;

import javafx.beans.property.SimpleStringProperty;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.VBox;
import model.Dobas;
import model.JatekVezerlo;
import model.Jatekos;;
/**
 * A megjelenítésre szolgáló ablak vezérlője.
 * 
 * @author Gyulai Zsolt
 *
 */
public class DartsOverviewController {
	@FXML
	private TableView<Jatekos> personTable;
	@FXML
	private TableColumn<Jatekos, String> firstNameColumn;

	@FXML
	private TableColumn<Jatekos, String> lastNameColumn;

	@FXML
	private Group group;

	@FXML
	private Label NeveLabel;
	@FXML
	private Label pontLabel;

	@FXML
	private TextField textField;

	@FXML
	private VBox nameVBox;

	@FXML
	private VBox pointVBox;

	@FXML
	private Button StartButton;

	@FXML
	private Button AddButton;

	@FXML
	private Button SuggestionButton;

	@FXML
	private Label jatekModLabel;

	@FXML
	private ComboBox<String> comboBox;

	private boolean ready = false;

	private MainApp mainApp;

	private boolean isReady() {
		return ready;
	}

	/**
	 * The constructor. The constructor is called before the initialize()
	 * method.
	 */
	public DartsOverviewController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

		firstNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNeve()));
		lastNameColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getPontjai())));

		BuildTable table = new BuildTable();

		group.getChildren().add(table.tableBuild(00.0, 0.0, 150.0));
		group.getChildren().addAll(table.createTextList(00.0, 0.0, 170.0));

		comboBox.getItems().addAll(JatekVezerlo.getJatekmodok());
		comboBox.getSelectionModel().select(0);

		SuggestionButton.setVisible(false);
		pontLabel.setText("Üdvözöljük a Játékosokat");

	}
	/**
	 * Beállítja a {@code MainApp} a hivatkozás, majd játékoso listáját.
	 * @param mainApp a mainApp hivatkozása
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		personTable.setItems(mainApp.getPersonData());

	}

	@FXML
	private void handleStart() {
		if (personTable.getItems().size() > 0) {
			JatekVezerlo.getInstance().setJatekTipus(comboBox.getSelectionModel().getSelectedItem());

			textField.setVisible(false);
			AddButton.setVisible(false);
			NeveLabel.setVisible(false);
			StartButton.setVisible(false);
			comboBox.setVisible(false);

			SuggestionButton.setVisible(true);
			pontLabel.setText("");
			personTable.getSelectionModel().select(0);

			jatekModLabel.setText("Jatek Mod: " + JatekVezerlo.getInstance().getJatekTipus());

			ready = true;

		}

	}
	/**
	 * Frissíti a játékosokat tartalmazó táblát.
	 */
	public void UpdatePersonList() {
		personTable.refresh();
	}
	
	/**
	 * Az aktuális játékoshoz tanácsot kér a {@link JatekVezerlo}.
	 */
	public void UpdateSuggesion() {
		int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex != -1)
			pontLabel.setText(JatekVezerlo.getInstance().getJavaslat(comboBox.getValue(),
					mainApp.getPersonData().get(selectedIndex)));
	}

	@FXML
	private void handleHozzaadJatekos() {

		if (JatekVezerlo.getInstance().isNev(textField.getText()))
			JatekVezerlo.getInstance().addJatekos(new Jatekos(textField.getText()));
		textField.clear();

	}

	@FXML
	private void handleSuggestion() {
		UpdateSuggesion();
	}

	@FXML
	private void handleDobas(MouseEvent me) {
		if (isReady()) {
			if (personTable.getItems().size() > 0) {

				String id = me.getPickResult().getIntersectedNode().getId();

				String marker = id.substring(0, 1).trim();

				int point = Integer.parseInt(id.substring(1));

				int selectedIndex = personTable.getSelectionModel().getSelectedIndex();

				if (selectedIndex != -1) {

					JatekVezerlo.getInstance().addDobasJatekoshoz(mainApp.getPersonData().get(selectedIndex),
							new Dobas(point, marker));
					personTable.getSelectionModel().select(JatekVezerlo.getInstance().getKovetkezoJatekos());
				}

			}
		}
	}

}