package quiz;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainQuiz extends Application{
	
	@Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("QuizGame.fxml"));
		stage.setScene(new Scene((Parent) JFxUtils.loadFxml("fxml/QuizAccueil.fxml"), 1024, 968));        
        //Scene scene = new Scene(root, 1400, 700);
        
        stage.setTitle("Quiz Serious Game");
        //stage.setScene(scene);
        //stage.setFullScreen(true);
        
        stage.show();
        
        //Faire une fonction de  pour gerer les vues
        //stage.getScene().setRoot((Parent) JFxUtils.loadFxml("fxml/ViewQuestion.fxml"));
        stage.sizeToScene();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}