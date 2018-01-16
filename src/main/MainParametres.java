package main;

import controleurs.JFxUtils;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainParametres extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setScene(new Scene((Parent) JFxUtils.loadFxml("../vues/ViewMainParametres.fxml"), 960, 780));
		stage.setTitle("Configuration d'un serious game");
		stage.show();
		stage.sizeToScene();
		stage.setMaximized(true);
		
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
