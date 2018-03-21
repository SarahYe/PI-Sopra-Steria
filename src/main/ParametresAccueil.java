package main;

import controleurs.JFxUtils;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Classe permettant de lancer l'interface de paramétrage du bloc Accueil
 *
 */
public class ParametresAccueil extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		stage.setScene(new Scene((Parent) JFxUtils.loadAccueilParamFxml("../vues/AccueilParametres.fxml",
				"FichiersDeConfig/accueil.xml"), 850, 650));
		stage.setTitle("ParamÃ©trage d'un accueil");
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
