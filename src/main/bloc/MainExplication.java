package main.bloc;

import controleurs.JFxUtils;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainExplication extends Application {

	@Override
	public void start(Stage stage) throws Exception {

		stage.setScene(new Scene((Parent) JFxUtils.loadExplicationFxml("../vues/PageExplication.fxml","FichiersDeConfig/explication.xml"), 850, 650));
		stage.setTitle("SériousSécurité");
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
