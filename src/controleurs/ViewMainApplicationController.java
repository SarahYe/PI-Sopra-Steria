package controleurs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.MainChronologie;

/**
 * Controleur de la page d'accueil de l'application
 *
 */
public class ViewMainApplicationController implements Initializable {

	@FXML
	Button BT_StartGame;
	@FXML
	Label nomApp;

	int cmptChronologie = 1;
	String xmlChronologie = "Games/test1/chronologie_test1.xml";
	boolean son = true;
	int score = 0;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			Font font = Font.loadFont(new FileInputStream(new File("././Ressources/Polices/Matchmaker.ttf")), 100);
			nomApp.setFont(font);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Fonction permettant de lancer un jeu
	 * @param event
	 */
	@FXML
	private void ClickBT_StartGame(ActionEvent event) {
		List<String> choices = new ArrayList<>();
		File dir = new File("Games");
		File[] files = dir.listFiles();
		for (File dossier : files)
			choices.add(dossier.getName());

		ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
		dialog.setTitle("imaGin'S");
		dialog.setHeaderText("Choix du jeu");
		dialog.setContentText("Veuillez choisir le jeu que vous dï¿½sirez lancer");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			xmlChronologie = "Games/" + result.get() + "/chronologie_" + result.get() + ".xml";
			System.out.println(xmlChronologie);

			Node node = JFxUtils.loadNextBloc(cmptChronologie, xmlChronologie, son, score);
			Stage stage = (Stage) BT_StartGame.getScene().getWindow();
			if (node != null) {
				stage.setScene(new Scene((Parent) node, 850, 650));
				stage.setTitle("imaGin'S");
				stage.show();
				stage.sizeToScene();
			} else {
				System.out.println("node null");
			}
		}
	}

	/**
	 * Fonction permettant d'acceder à l'interface de paramétrage globale
	 * @param event
	 */
	@FXML
	private void ClickBT_StartParam(ActionEvent event) {
		Stage stage = (Stage) BT_StartGame.getScene().getWindow();
		stage.setScene(new Scene((Parent) JFxUtils.loadFxml("/vues/ViewMainParametres.fxml"), 960, 780));
		stage.setTitle("Configuration d'un serious game");
		stage.show();
		stage.sizeToScene();
		stage.setMaximized(true);
	}

}
