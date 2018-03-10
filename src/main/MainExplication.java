package main;

import controleurs.JFxUtils;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainExplication extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		stage.setScene(new Scene((Parent) JFxUtils.loadExplicationFxml("/vues/PageExplication.fxml","FichiersDeConfig/explication.xml", true, 0, "Games/test/chronologie_test",true,0), 850, 650));
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
