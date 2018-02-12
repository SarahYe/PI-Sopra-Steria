package controleurs;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modeles.Instruction;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class ViewAddOrModifyInstructionController implements Initializable {

	@FXML
	private TextField imageObjet;

	@FXML
	private Button boutonTelechargerObjet;

	@FXML
	private TextArea zoneEnonce;

	@FXML
	private Button boutonSauvegarder;

	@FXML
	private ComboBox<String> comboType;

	@FXML
	private TextField zonePosX;

	@FXML
	private TextField zonePosY;
	
	@FXML
	private Label errorLabel;
	
	@FXML
	private Label errorPosition;

	private TableViewSelectionModel<Instruction> tableViewSelectionModel;
	
	private ViewParametresFouilleController mainController;
	private boolean modifyDialogueInterface;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		errorLabel.setVisible(false);
		errorPosition.setVisible(false);
		zonePosX.setText("0");
		zonePosY.setText("0");
		
		comboType.getItems().addAll (
			        "BRONZE",
			        "SILVER",
			        "OR"
			    );
		comboType.setValue("BRONZE");
	}

	public void initData(boolean modifyDialogueInterface, TableViewSelectionModel<Instruction> tableViewSelectionModel,
			ViewParametresFouilleController controller) {
		this.modifyDialogueInterface = modifyDialogueInterface;
		this.tableViewSelectionModel = tableViewSelectionModel;
		mainController = controller;

		if (this.modifyDialogueInterface) {
			Instruction selected = tableViewSelectionModel.getSelectedItem();
			zoneEnonce.setText(selected.getIntitule());
			imageObjet.setText(selected.getImageObjet());
			zonePosX.setText("" + selected.getPosX());
			zonePosY.setText("" + selected.getPosY());
			comboType.setValue(selected.getType());
		}
	}

	@FXML
	void sauvegarder(ActionEvent event) {
		
		if (zoneEnonce.getText().isEmpty() || imageObjet.getText().isEmpty())
				errorLabel.setVisible(true);
		else {
			/*zonePosX.textProperty().addListener(new ChangeListener<String>() {
			    @Override
			    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			        if (!newValue.matches("\\d*")) {
			        		zonePosX.setText(newValue.replaceAll("[^\\d]", ""));
			        }
			    }
			});*/
			
			
			if (modifyDialogueInterface) {	
				if (tryParseDouble(zonePosX.getText()) && tryParseDouble(zonePosY.getText())) {  
					mainController.setInstruction(tableViewSelectionModel.getSelectedIndex(), zoneEnonce.getText(), imageObjet.getText(), Double.parseDouble(zonePosX.getText()), Double.parseDouble(zonePosY.getText()), comboType.getValue().toString());		

					Stage stage = (Stage) boutonSauvegarder.getScene().getWindow();
					stage.close();
				} else {
					errorPosition.setVisible(true);
				}
			} else {		
				if (tryParseDouble(zonePosX.getText()) && tryParseDouble(zonePosY.getText())) {  
					mainController.addInstruction(zoneEnonce.getText(), imageObjet.getText(), Double.parseDouble(zonePosX.getText()), Double.parseDouble(zonePosY.getText()), comboType.getValue().toString());	

					Stage stage = (Stage) boutonSauvegarder.getScene().getWindow();
					stage.close();
				} else {
					errorPosition.setVisible(true);
				}
		
			}
		}
	}

	@FXML
	void telechargerObjet(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		Path cheminAbsoluActuel = Paths.get("").toAbsolutePath();
		Path cheminAbsoluImage = Paths.get(file.getAbsolutePath());
		imageObjet.setText(cheminAbsoluActuel.relativize(cheminAbsoluImage).toString());
	}
	
	static boolean tryParseDouble(String value) {  
	     try {  
	    	 	 Double.parseDouble(value);
	         //Integer.parseInt(value);  
	         return true;  
	      } catch (NumberFormatException e) {  
	         return false;  
	      }  
	}

}
