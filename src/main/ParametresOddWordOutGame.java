package main;

import controleurs.JFxUtils;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe permettant de lancer l'interface de paramétrage du bloc jeu de tri
 *
 */
public class ParametresOddWordOutGame extends Application {

	public static void main(String[] args) {
		Application.launch(ParametresOddWordOutGame.class, args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		stage.setScene(new Scene((Parent) JFxUtils.loadParamOddWordOutGame("/vues/ViewParametresOddWordOutGame.fxml",
				"FichiersDeConfig/slickGame.xml"), 850, 650));
		stage.setTitle("ParamÃ©trage d'un jeu de tri");
		stage.show();
		stage.sizeToScene();

	}

}
