package main;

import java.io.IOException;

import controleurs.JFxUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe permettant de lancer l'interface de paramétrage du bloc quiz
 *
 */
public class ParametresQuiz extends Application {

	public static void main(String[] args) {
		Application.launch(ParametresQuiz.class, args);
	}

	public void start(Stage stage) throws IOException {
		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		stage.setScene(
				new Scene((Parent) JFxUtils.loadParamQuiz("/vues/ViewParametresQuiz.fxml", "FichiersDeConfig/quiz.xml"),
						850, 650));
		stage.setTitle("ParamÃ©trage d'un quiz");
		stage.show();
		stage.sizeToScene();

	}

}
