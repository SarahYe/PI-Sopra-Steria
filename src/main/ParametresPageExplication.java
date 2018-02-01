package main;

import controleurs.JFxUtils;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ParametresPageExplication extends Application {

	@Override
	public void start(Stage stage) throws Exception {

		stage.setScene(new Scene((Parent) JFxUtils.loadExplicationParamFxml("/vues/PageExplicationParametrage.fxml","FichiersDeConfig/explication.xml"), 850, 650));
		stage.setTitle("Paramétrage d'une page d'explication");
		stage.show();
		stage.sizeToScene();
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
