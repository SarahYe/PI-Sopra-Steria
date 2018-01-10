package main;

import java.io.IOException;
import controleurs.ViewMainParametresController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainParametres extends Application {

	public static void main(String[] args) {
		Application.launch(MainParametres.class, args);
	}

	public void start(Stage primaryStage) throws IOException {
		
		primaryStage.setTitle("Page principale de parametrage");
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/ViewMainParametres.fxml"));

		primaryStage.setScene(new Scene(loader.load()));

		ViewMainParametresController controller = loader.<ViewMainParametresController>getController();
		controller.initData(controller);
		
		primaryStage.show();
		
		primaryStage.sizeToScene();
	}

}
