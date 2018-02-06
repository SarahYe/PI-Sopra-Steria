package main;

import controleurs.JFxUtils;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ParametresPNJ extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setScene(new Scene((Parent) JFxUtils.loadPNJParamFxml("../vues/ViewPNJParametres.fxml","FichiersDeConfig/pnj.xml"), 850, 650));
		stage.setTitle("Paramétrage d'un dialogue avec un personnage");
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
