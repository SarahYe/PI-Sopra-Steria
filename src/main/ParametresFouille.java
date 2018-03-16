package main;

import controleurs.JFxUtils;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ParametresFouille extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		stage.setScene(new Scene((Parent) JFxUtils.loadFouilleParamFxml("../vues/ViewFouilleParametres.fxml",
				"FichiersDeConfig/fouille.xml"), 1000, 650));
		stage.setTitle("Paramétrage d'un jeu de Fouille");
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
