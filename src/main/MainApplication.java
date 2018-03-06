package main;

import controleurs.JFxUtils;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setScene(new Scene((Parent) JFxUtils.loadFxml("/vues/MainApplication.fxml"), 250, 150));
		stage.setTitle("SeriousGame");
		stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
