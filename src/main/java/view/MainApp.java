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


	public MainApp() {

	}

	public void UdpdatePerson(List<Jatekos> Persons) {
		personData.clear();
		personData.addAll(Persons);
		controller.UpdatePersonList();
	}

	public void UpdateSuggesion() {
		controller.UpdateSuggesion();
	}

	public ObservableList<Jatekos> getPersonData() {
		return personData;
	}


	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Darts");

		initRootLayout();

		showDartsOverview();
	}

	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.

			Scene scene = new Scene(rootLayout);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void showDartsOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("DartsOverview.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);

			// Give the controller access to the main app.
			controller = loader.getController();
			controller.setMainApp(this);
			JatekVezerlo.getInstance().setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showEndDialog(Jatekos jatekos) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			System.out.println("sad");
			loader.setLocation(MainApp.class.getResource("EndOverview.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			System.out.println("sqw");
			// Create the dialog Stage.
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

			// return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			// return false;
		}
	}


	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}