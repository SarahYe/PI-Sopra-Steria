package main;

import controleurs.JFxUtils;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modeles.PNJ;

/**
 * Classe permettant de lancer un bloc PNJ
 *
 */
public class MainPNJ extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		stage.setScene(new Scene((Parent) JFxUtils.loadPNJFxml(new PNJ(), 0, "/vues/ViewPNJ.fxml",
				"FichiersDeConfig/pnj.xml", true, 0, "Games/test/chronologie_test", true, 0), 850, 650));
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
