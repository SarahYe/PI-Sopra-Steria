package main;

import controleurs.JFxUtils;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe permettant de lancer un bloc fouille
 *
 */
public class MainFouille extends Application {

	@Override
	public void start(Stage stage) throws Exception {

		stage.setScene(new Scene((Parent) JFxUtils.loadFouilleFxml("/vues/ViewFouille.fxml",
				"Games/SeriousSecurity/Jeu Fouille 1.xml", true, 0, "Games/test/chronologie_test", false, 50), 900, 700));
		stage.setTitle("imaGin'S");
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
