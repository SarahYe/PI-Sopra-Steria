package main;

import controleurs.JFxUtils;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modeles.Quiz;

public class MainScore extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setScene(new Scene((Parent) JFxUtils.loadScoreFxml("/vues/ViewScore.fxml", "FichiersDeConfig/score.xml", true, 1, "Games/test/chronologie_test.xml",true,100), 850, 650));
		stage.setTitle("SeriousSécurité");
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
