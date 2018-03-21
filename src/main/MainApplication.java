package main;

import controleurs.JFxUtils;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe permettant de lancer l'application ImaGin'S
 *
 */
public class MainApplication extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		stage.setScene(new Scene((Parent) JFxUtils.loadFxml("/vues/MainApplication.fxml"), 800, 600));
		stage.setTitle("imaGin'S");
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
