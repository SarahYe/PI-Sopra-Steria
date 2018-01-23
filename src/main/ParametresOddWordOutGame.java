package main;

import controleurs.JFxUtils;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ParametresOddWordOutGame extends Application {

	public static void main(String[] args) {
		Application.launch(ParametresOddWordOutGame.class, args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setScene(new Scene((Parent) JFxUtils.loadParamOddWordOutGame("../vues/ViewParametresOddWordOutGame.fxml","FichiersDeConfig/slickGame.xml"), 850, 650));
		stage.setTitle("SeriousSécurité");
		stage.show();
		stage.sizeToScene();
		
	}

}
