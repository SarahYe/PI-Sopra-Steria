package main;

import controleurs.JFxUtils;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe permettant de lancer l'interface de paramétrage du bloc puzzle
 *
 */
public class ParametresPuzzle extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		stage.setScene(new Scene((Parent) JFxUtils.loadPuzzleParamFxml("../vues/ViewParametresPuzzle.fxml",
				"FichiersDeConfig/puzzle.xml"), 850, 650));
		stage.setTitle("ParamÃ©trage d'un puzzle");
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
