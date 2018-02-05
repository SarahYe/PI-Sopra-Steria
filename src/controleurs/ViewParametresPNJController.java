package controleurs;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import modeles.Dialogue;
import modeles.PNJ;
import modeles.Question;
import modeles.Quiz;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class ViewParametresPNJController implements Initializable {

	@FXML
	private Button btn;

	@FXML
	private Label errorLabel;

	@FXML
	private Button questionUp;

	@FXML
	private Button questionDown;

	@FXML
	private Button boutonAjout;

	@FXML
	private Button boutonModifier;

	@FXML
	private Button boutonSupprimer;

	@FXML
	private TableView<Dialogue> table;
	
	@FXML
	private AnchorPane AP_ParamDialogue;

	private String xml;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void initData() {
		File f =  new File(xml);
		if (f.exists()) {

		} else {
			System.out.println("xml \"" + xml + "\" doesn't exist");
		}
	}

	public void setXML(String xml) {
		this.xml = xml;
	}

	@FXML
	void ClickButtonAdd(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/dialoguePNJ.fxml"));
		ScrollPane newPane = loader.load();
		AP_ParamDialogue.getChildren().setAll(newPane);
		ViewAddOrModifyDialogueController controller = loader.<ViewAddOrModifyDialogueController>getController();
		controller.initData(false, null, this);
	}

	@FXML
	void ClickButtonModify(ActionEvent event) throws IOException {
		if (table.getSelectionModel().isEmpty()) {
			return;
		}

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/dialoguePNJ.fxml"));
		ScrollPane newPane = loader.load();
		AP_ParamDialogue.getChildren().setAll(newPane);
		ViewAddOrModifyDialogueController controller = loader.<ViewAddOrModifyDialogueController>getController();
		controller.initData(true, table.getSelectionModel(),this);
	}

	@FXML
	void ClickButtonRemove(ActionEvent event) {
		if (table.getSelectionModel().isEmpty()) {
			return;
		}

		Dialogue selectedItem = table.getSelectionModel().getSelectedItem();
		table.getItems().remove(selectedItem);
	}

	@FXML
	void ClickButtonSave(ActionEvent event) {
		ArrayList<Dialogue> listeDialogues = new ArrayList<Dialogue>();
		ObservableList<Dialogue> data = table.getItems();
		
		for (int i = 0; i < data.size(); i++) {
			listeDialogues.add(data.get(i));
		}
		
		PNJ dialogue = new PNJ("Nom du bloc", listeDialogues);
		dialogue.convertirJavaToXML(dialogue, xml);
		
		//popup de confirmation
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Paramétrage  d'un quiz");
		alert.setContentText("Le paramétrage a bien été enregistré !");
		alert.showAndWait();
	}

	@FXML
	void ClickButtonUp(ActionEvent event) {
		int index = table.getSelectionModel().getSelectedIndex();
		if (index > 0){
			Dialogue buff = table.getItems().get(index-1);
			table.getItems().set(index-1, table.getItems().get(index));
			table.getItems().set(index, buff);
			table.getSelectionModel().select(index-1);
		}
	}
	
	@FXML
	void ClickButtonDown(ActionEvent event) {
		int index = table.getSelectionModel().getSelectedIndex();
		if (index < table.getItems().size()){
			Dialogue buff = table.getItems().get(index+1);
			table.getItems().set(index + 1, table.getItems().get(index));
			table.getItems().set(index, buff);
			table.getSelectionModel().select(index + 1);
		}
	}

}
