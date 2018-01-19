package main;

import java.io.IOException;

import controleurs.JFxUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ParametresQuiz extends Application {

	public static void main(String[] args) {
		Application.launch(ParametresQuiz.class, args);
	}

	public void start(Stage stage) throws IOException {

		/*primaryStage.setTitle("Paramétrage d'un quiz");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/ViewParametresQuiz.fxml"));
		primaryStage.setScene(new Scene(loader.load()));
		//ViewParametresQuizController controller = loader.<ViewParametresQuizController>getController();

		//controller.initData(controller);
		primaryStage.show();
		primaryStage.sizeToScene();*/
		
		stage.setScene(new Scene((Parent) JFxUtils.loadParamQuiz("../vues/ViewParametresQuiz.fxml","FichiersDeConfig/quiz.xml"), 850, 650));
		stage.setTitle("SeriousSécurité");
		stage.show();
		stage.sizeToScene();

	}

}
