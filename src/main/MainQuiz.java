package main;

import controleurs.JFxUtils;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainQuiz extends Application {

	@Override
	public void start(Stage stage) throws Exception {

		// Font.loadFont(getClass().getResourceAsStream("./Ressources/Polices/GoodMorning.ttf"),
		// 12);
		stage.setScene(new Scene((Parent) JFxUtils.loadFxml("../vues/QuizAccueil.fxml"), 850, 650));
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
