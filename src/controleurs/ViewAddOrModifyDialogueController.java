package controleurs;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.stage.FileChooser;
import modeles.Dialogue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class ViewAddOrModifyDialogueController implements Initializable {

	@FXML
	private TextField imageFondEcran;

	@FXML
	private Button boutonTelechargerFondEcran;

	@FXML
	private ColorPicker CouleurFondEcran;

	@FXML
	private Button boutonSupprimerFDE;

	@FXML
	private Button annulerCouleurFDE;

	@FXML
	private TextArea zoneDialogue;

	@FXML
	private TextField imagePersonnage;

	@FXML
	private Button boutonTelechargerPersonnage;

	@FXML
	private Button boutonSauvegarder;

	private TableViewSelectionModel<Dialogue> tableViewSelectionModel;
	private ViewParametresPNJController mainController;
	private Boolean couleurFDE = false;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void initData(boolean modifyDialogueInterface, TableViewSelectionModel<Dialogue> tableViewSelectionModel,ViewParametresPNJController controller) {
		// TODO Auto-generated method stub

	}

	@FXML
	void annulerCouleurFondEcran(ActionEvent event) {
		imageFondEcran.setText("");
		CouleurFondEcran.setDisable(false);
		imageFondEcran.setDisable(false);
		boutonSupprimerFDE.setDisable(false);
		boutonTelechargerFondEcran.setDisable(false);
		
		couleurFDE = false;
	}

	@FXML
	void sauvegarder(ActionEvent event) {

	}

	@FXML
	void supprimerImageFondEcran(ActionEvent event) {
		imageFondEcran.setText("");
		CouleurFondEcran.setDisable(false);
	}

	@FXML
	void telechargerFondEcran(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		Path cheminAbsoluActuel = Paths.get("").toAbsolutePath();
		Path cheminAbsoluImage = Paths.get(file.getAbsolutePath());
		imageFondEcran.setText(cheminAbsoluActuel.relativize(cheminAbsoluImage).toString());
		

		couleurFDE = false;
		activerCouleur();
	}

	@FXML
	void modifierCouleurFondEcran(ActionEvent event) {	
		couleurFDE = true;
		activerCouleur();
	}
	
	@FXML
	void telechargerPersonnage(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		Path cheminAbsoluActuel = Paths.get("").toAbsolutePath();
		Path cheminAbsoluImage = Paths.get(file.getAbsolutePath());
		imagePersonnage.setText(cheminAbsoluActuel.relativize(cheminAbsoluImage).toString());
	}
	
	public void activerCouleur() {
		if (couleurFDE) {
			imageFondEcran.setDisable(true);
			boutonSupprimerFDE.setDisable(true);
			boutonTelechargerFondEcran.setDisable(true);;
			//couleurFDE = false;
		} else {
			CouleurFondEcran.setDisable(true);
		}
	}


}
