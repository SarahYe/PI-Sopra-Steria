package controleurs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.ParametresQuiz;
import modeles.Quiz;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class QuizAccueilController implements Initializable {

	@FXML
	private Button buttonJouer;
	@FXML
	private Button ButtonParametrage;
	@FXML
	private Button buttonScore;
	
	private String xml="";
	private boolean soloBloc=true;
	private int cmptChronologie=0;
	private String xmlChronologie="";

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		jouerAudio("././Ressources/Sons/Jouer.wav", -25.0f);
	}
	
	public void setXML(String xml) {
		this.xml=xml;
	}
	
	public void setChronologie(boolean soloBloc, int cmptChronologie, String xmlChronologie){
		this.soloBloc=soloBloc;
		this.cmptChronologie=cmptChronologie;
		this.xmlChronologie=xmlChronologie;
	}

	@FXML
	protected void ClickButtonJouer(ActionEvent event) throws IOException {
		Stage stage = (Stage) buttonJouer.getScene().getWindow();
		//jouerAudio("/Users/SarahYe/git/PI-Sopra-Steria/Ressources/Sons/Jouer.wav");
		new JFxUtils().loadQuestion(new Quiz(), 0, stage,xml,soloBloc,cmptChronologie, xmlChronologie);
	}

	@FXML
	protected void ClickButtonScore(ActionEvent event) {
		Stage stage = (Stage) buttonScore.getScene().getWindow();
		stage.getScene().setRoot((Parent) JFxUtils.loadFxml("/vues/ViewScore.fxml"));
		stage.sizeToScene();
	}

	public static void jouerAudio(String son) {
		InputStream in;
		try {
			in = new FileInputStream(son);
			AudioStream as = new AudioStream(in);
			AudioPlayer.player.start(as);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void jouerAudio(String son, float volumeReduced){
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(son));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(volumeReduced); // Reduce volume by 10 decibels.
			clip.start();
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	protected void ClickButtonParametrage(ActionEvent event) throws IOException {
		Stage stage = (Stage) ButtonParametrage.getScene().getWindow();
		// ouverture de l'interface de parametrage
		stage.sizeToScene();
		/*
		 * Platform.setImplicitExit(false); stage.setOnCloseRequest(e->Platform.exit());
		 * stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
		 * stage.close(); Platform.exit(); System.exit(0);
		 * //com.sun.javafx.application.tkExit();
		 */
		// String[] args = null;
		new ParametresQuiz().start(stage);
	}
}
