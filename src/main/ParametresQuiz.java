package main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ParametresQuiz extends Application {

	public static void main(String[] args) {
		Application.launch(ParametresQuiz.class, args);
	}

	public void start(Stage primaryStage) throws IOException {

		primaryStage.setTitle("Paramétrage d'un quiz");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/ViewParametresQuiz.fxml"));
		primaryStage.setScene(new Scene(loader.load()));
		//ViewParametresQuizController controller = loader.<ViewParametresQuizController>getController();

		//controller.initData(controller);
		primaryStage.show();
		primaryStage.sizeToScene();

	}

}
