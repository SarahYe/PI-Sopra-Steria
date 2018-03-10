package main;

import java.io.File;

import controleurs.JFxUtils;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainParametres extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		stage.setScene(new Scene((Parent) JFxUtils.loadFxml("/vues/ViewMainParametres.fxml"), 1250, 780));
		stage.setTitle("imaGin'S");
		stage.show();
		stage.sizeToScene();
		//stage.setMaximized(true);
	}
	
	@Override
	public void stop(){
	    System.out.println("Stage is closing");
	    
	    File dir = new File ("Games/temp");
		dir.mkdirs();
		
		File[] files = dir.listFiles();
		for(File fichier:files)
			fichier.delete();
		
		dir.delete();
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
