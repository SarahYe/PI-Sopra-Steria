package main;

import controleurs.JFxUtils;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainAccueil extends Application {

	@Override
	public void start(Stage stage) throws Exception {

		stage.setScene(new Scene((Parent) JFxUtils.loadAccueilFxml("/vues/Accueil.fxml","FichiersDeConfig/accueil.xml", true, 0, "Games/test/chronologie_test",true), 850, 650));
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
