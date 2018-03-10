package main;

import java.util.HashMap;

import controleurs.JFxUtils;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modeles.Quiz;

public class MainQuiz extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		HashMap<Integer,String> reponsesJoueur = new HashMap<Integer,String>();
		stage.setScene(new Scene((Parent) JFxUtils.loadQuestion(new Quiz(), reponsesJoueur, 0,"/vues/ViewQuestion.fxml", "FichiersDeConfig/quiz.xml", true, 1, "Games/test/chronologie_test.xml",true,0), 850, 650));
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
