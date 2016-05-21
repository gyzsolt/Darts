package view;

import java.io.IOException;
import java.util.List;

import javafx.application.Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.JatekVezerlo;
import model.Jatekos;

/**
 * A grafikus felület fő fezérlője.
 * 
 * @author Gyulai Zsolt
 *
 */
public class MainApp extends Application {

	private DartsOverviewController controller;
	private Stage primaryStage;
	private BorderPane rootLayout;

	private ObservableList<Jatekos> personData = FXCollections.observableArrayList();

	/**
	 *  A {@code MainApp} konstruktora.
	 */
	public MainApp() {

	}

	/**
	 * Frissíti a játékosok listáját és azt át adja a {@code DartsOverviewController}.
	 * @param Persons játékosokat tartalmazó lista
	 */
	public void UdpdatePerson(List<Jatekos> Persons) {
		personData.clear();
		personData.addAll(Persons);
		controller.UpdatePersonList();
	}

	/**
	 * Frissiti a tanácsokat.
	 */
	public void UpdateSuggesion() {
		controller.UpdateSuggesion();
	}

	/**
	 * Vissza adja {@code ObservableList} tárolt játékosokat.
	 * @return visszatér a játékosok listájával
	 */
	public ObservableList<Jatekos> getPersonData() {
		return personData;
	}
	/** {@inheritDoc} */
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Darts");

		initRootLayout();

		showDartsOverview();
	}

	/**
	 * Inicialiálja a {@code RootLayout}.
	 */
	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			Scene scene = new Scene(rootLayout);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Megjeleniti a játék fő ablakát.
	 */
	public void showDartsOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("DartsOverview.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			rootLayout.setCenter(personOverview);

			controller = loader.getController();
			controller.setMainApp(this);
			JatekVezerlo.getInstance().setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Megjeleníti a befejezett mecsután megjelenő ablakot.
	 * @param jatekos megkapja a győztes játékos
	 */
	public void showEndDialog(Jatekos jatekos) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("EndOverview.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
	
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Winner Player");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			EndOverviewController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setWinner(jatekos);

			dialogStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lekérdezi {@code primariStage}.
	 * @return vissza té a primariStage-vel
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * A grafikus felület megjelenítésére szolgáló main függvény.
	 * @param args parancs sori argumentumok
	 */
	public static void main(String[] args) {
		launch(args);
	}
}