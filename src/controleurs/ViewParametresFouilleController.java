package controleurs;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modeles.Instruction;
import modeles.Question;
import modeles.Fouille;

public class ViewParametresFouilleController implements Initializable{

	@FXML
	private TextField imageFDE;

	@FXML
	private Button boutonTelechargerFDE;

	@FXML
	private Button boutonSave;

	@FXML
	private Label errorLabel;

	@FXML
	private Button boutonAjout;

	@FXML
	private Button boutonModifier;

	@FXML
	private Button boutonSupprimer;

	@FXML
	private TableView<Instruction> table;

	@FXML
	private TextField bronze;

	@FXML
	private TextField silver;

	@FXML
	private TextField gold;
	
	@FXML
	private Label errorScore;
	

	private String xml;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		errorLabel.setVisible(false);
		errorScore.setVisible(false);
		bronze.setText("25");
		silver.setText("50");
		gold.setText("100");
	}

	public void initData() {
		File f =  new File(xml);

		if (f.exists()) {
			ArrayList<Instruction> list = new ArrayList<Instruction>();
			Fouille fouille = new Fouille();
			Fouille fParam = fouille.convertirXMLToJava(xml);
			
			ObservableList<Instruction> data = table.getItems();
			for (int i = 0; i<fParam.getListeInstructions().size(); i++)
				data.add(fParam.getListeInstructions().get(i));
		} else 
			System.out.println("\"" + xml + "\" doesn't exist");
	}

	public void setXML(String xml) {
		this.xml = xml;
	}

	@FXML
	void ClickButtonAdd(ActionEvent event) throws IOException {
		/*FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/dialogueFouille.fxml"));
		ScrollPane newPane = loader.load();
		AP_ParamDialogue.getChildren().setAll(newPane);
		ViewAddOrModifyDialogueController controller = loader.<ViewAddOrModifyDialogueController>getController();
		controller.initData(false, null, this);*/
		
		Stage stage = new Stage();
		stage.setTitle("Nouvelle instruction");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/ViewAddOrModifyInstruction.fxml"));
		stage.setScene(new Scene(loader.load()));
		ViewAddOrModifyInstructionController controller = loader.<ViewAddOrModifyInstructionController>getController();
		controller.initData(false, null, this);
		stage.show();
	}

	@FXML
	void ClickButtonModify(ActionEvent event) throws IOException {
		
		if (table.getSelectionModel().isEmpty()) {
			return;
		}

		/*FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/dialogueFouille.fxml"));
		ScrollPane newPane = loader.load();
		ViewAddOrModifyDialogueController controller = loader.<ViewAddOrModifyDialogueController>getController();
		controller.initData(true, table.getSelectionModel(), this);*/
		
		Stage stage = new Stage();
		stage.setTitle("Nouvelle instruction");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/ViewAddOrModifyInstruction.fxml"));
		stage.setScene(new Scene(loader.load()));
		ViewAddOrModifyInstructionController controller = loader.<ViewAddOrModifyInstructionController>getController();
		controller.initData(true, table.getSelectionModel(), this);
		stage.show();

	}

	@FXML
	void ClickButtonRemove(ActionEvent event) {
		if (table.getSelectionModel().isEmpty()) {
			return;
		}

		Instruction selectedItem = table.getSelectionModel().getSelectedItem();
		table.getItems().remove(selectedItem);
	}

	@FXML
	void ClickButtonSave(ActionEvent event) {
		ArrayList<Instruction> listeInstructions = new ArrayList<Instruction>();
		ObservableList<Instruction> data = table.getItems();

		for (int i = 0; i < data.size(); i++) {
			listeInstructions.add(data.get(i));
		}
		
		if (imageFDE.getText().isEmpty() || listeInstructions.size() == 0) {
			errorLabel.setVisible(true);
		} else {
		
			if (ViewAddOrModifyInstructionController.tryParseDouble(gold.getText()) && ViewAddOrModifyInstructionController.tryParseDouble(bronze.getText()) && ViewAddOrModifyInstructionController.tryParseDouble(silver.getText())) {  
				Fouille f = new Fouille("Nom du bloc", listeInstructions, imageFDE.getText(),  Double.parseDouble(gold.getText()),  Double.parseDouble(bronze.getText()),  Double.parseDouble(silver.getText()));
				f.convertirJavaToXML(f, xml);
				
				// popup de confirmation
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Paramétrage  d'un quiz");
				alert.setContentText("Le paramétrage a bien été enregistré !");
				alert.showAndWait();
			} else {
				errorScore.setVisible(true);
			}
		}
	}

	@FXML
	void telechargerImageFDE(ActionEvent event) {
		
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		Path cheminAbsoluActuel = Paths.get("").toAbsolutePath();
		Path cheminAbsoluImage = Paths.get(file.getAbsolutePath());
		imageFDE.setText(cheminAbsoluActuel.relativize(cheminAbsoluImage).toString());
	}

	public void setInstruction(int selectedIndex, String intitule, String imageObjet, Double posX, Double posY, String type) {
		table.getItems().set(selectedIndex, new Instruction (intitule, imageObjet, posX, posY, type));
	}

	public void addInstruction( String intitule, String imageObjet, Double posX, Double posY, String type) {
		table.getItems().add(new Instruction (intitule, imageObjet, posX, posY, type));
	}

}
