package main;

import java.io.File;
import java.io.FileInputStream;

import controleurs.JFxUtils;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainQuiz extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		
		Font.loadFont(getClass().getResourceAsStream("./Ressources/Polices/GoodMorning.ttf"), 12);
		
		stage.setScene(new Scene((Parent) JFxUtils.loadFxml("../vues/QuizAccueil.fxml"), 960, 600));
		stage.setTitle("Quiz Serious Game");
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
