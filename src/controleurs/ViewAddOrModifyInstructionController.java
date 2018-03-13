package controleurs;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

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
		zoneEnonce.setWrapText(true);
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
		
		ViewAddOrModifyQuestionController.addTextALimiter(zoneEnonce,150);
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
	void telechargerObjet(ActionEvent event) throws IOException {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		Path cheminAbsoluActuel = Paths.get("").toAbsolutePath();
		Path cheminAbsoluImage = Paths.get(file.getAbsolutePath());
		String[] s = cheminAbsoluImage.toString().split("/");
		String nomImage = s[s.length - 1];
		nomImage=nomImage.substring(nomImage.lastIndexOf("\\")+1);
		
		BufferedImage image = ImageIO.read(new File(cheminAbsoluImage.toString()));
		
		if (nomImage.contains(".png") || nomImage.contains(".PNG")) {
			ImageIO.write(image, "png", new File("././Ressources/Images/" + nomImage));
		} else {
			if (nomImage.contains(".jpg") || nomImage.contains(".JPG"))
				ImageIO.write(image, "jpg", new File("././Ressources/Images/" + nomImage));
			else 
				ImageIO.write(image, "jpeg", new File("././Ressources/Images/" + nomImage));
		}
		imageObjet.setText("././Ressources/Images/" + nomImage);
	}
	
	static boolean tryParseDouble(String value) {  
	     try {  
	    	 	 Double.parseDouble(value);  
	         return true;  
	      } catch (NumberFormatException e) {  
	         return false;  
	      }  
	}

}
