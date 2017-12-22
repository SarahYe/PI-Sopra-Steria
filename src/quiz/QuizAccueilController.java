package quiz;
import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.midi.ControllerEventListener;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import parametres.MainParametres;

public class QuizAccueilController implements Initializable{
	
	@FXML
	private Button buttonJouer;
	@FXML
	private Button ButtonParametrage;
	@FXML
	private Button buttonScore;
	
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
    	
		
		
		
	}
	
	@FXML
	protected void ClickButtonJouer(ActionEvent event) {
		Stage stage = (Stage)buttonJouer.getScene().getWindow();
		stage.getScene().setRoot((Parent) JFxUtils.loadFxml("fxml/ViewQuestion.fxml"));
		
	}
	
	@FXML
	protected void ClickButtonScore(ActionEvent event) {
		Stage stage = (Stage)buttonScore.getScene().getWindow();
		stage.getScene().setRoot((Parent) JFxUtils.loadFxml("fxml/ViewScore.fxml"));
		//ouverture de l'interface de parametrage
		stage.sizeToScene();
		
	}
	
	@FXML
	protected void ClickButtonParametrage(ActionEvent event) {
		Stage stage = (Stage)ButtonParametrage.getScene().getWindow();
		//ouverture de l'interface de parametrage
		stage.sizeToScene();
		
		/*Platform.setImplicitExit(false);
		stage.setOnCloseRequest(e->Platform.exit());
		stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
		stage.close();
		Platform.exit();
		System.exit(0);
		//com.sun.javafx.application.tkExit();*/
		
		
		String[] args = null;
		new MainParametres().start(stage);;
			
	}

}
