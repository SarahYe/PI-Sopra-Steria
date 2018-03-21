package main;

import controleurs.JFxUtils;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe permettant de lancer l'interface de paramétrage du bloc d'explication
 *
 */
public class ParametresPageExplication extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		stage.setScene(new Scene((Parent) JFxUtils.loadExplicationParamFxml("/vues/PageExplicationParametrage.fxml",
				"FichiersDeConfig/explication.xml"), 850, 650));
		stage.setTitle("ParamÃ©trage d'une page d'explication");
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
