package controleurs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import modeles.Puzzle;

public class ViewParametresPuzzleController implements Initializable {

    @FXML
    private ComboBox<String> formatPuzzle;

    @FXML
    private TextArea description;

    @FXML
    private VBox frag1_6;

    @FXML
    private TextField frag1_6_1;

    @FXML
    private Button btnTelechargerfrag1_6_1;

    @FXML
    private TextField frag1_6_2;

    @FXML
    private Button btnTelechargerfrag1_6_2;

    @FXML
    private TextField frag1_6_3;

    @FXML
    private Button btnTelechargerfrag1_6_3;

    @FXML
    private TextField frag1_6_4;

    @FXML
    private Button btnTelechargerfrag1_6_4;

    @FXML
    private TextField frag1_6_5;

    @FXML
    private Button btnTelechargerfrag1_6_5;

    @FXML
    private TextField frag1_6_6;

    @FXML
    private Button btnTelechargerfrag1_6_6;

    @FXML
    private VBox frag1_5;

    @FXML
    private TextField frag1_5_1;

    @FXML
    private Button btnTelechargerfrag1_5_1;

    @FXML
    private TextField frag1_5_2;

    @FXML
    private Button btnTelechargerfrag1_5_2;

    @FXML
    private TextField frag1_5_3;

    @FXML
    private Button btnTelechargerfrag1_5_3;

    @FXML
    private TextField frag1_5_4;

    @FXML
    private Button btnTelechargerfrag1_5_4;

    @FXML
    private TextField frag1_5_5;

    @FXML
    private Button btnTelechargerfrag1_5_5;

    @FXML
    private VBox frag1_4;

    @FXML
    private TextField frag1_4_1;

    @FXML
    private Button btnTelechargerfrag1_4_1;

    @FXML
    private TextField frag1_4_2;

    @FXML
    private Button btnTelechargerfrag1_4_2;

    @FXML
    private TextField frag1_4_3;

    @FXML
    private Button btnTelechargerfrag1_4_3;

    @FXML
    private TextField frag1_4_4;

    @FXML
    private Button btnTelechargerfrag1_4_4;

    @FXML
    private ComboBox<Integer> nbIndices;

    @FXML
    private TextArea indice1;

    @FXML
    private CheckBox puzzleImage;

    @FXML
    private Label lbl_ind2;

    @FXML
    private TextArea indice2;

    @FXML
    private TextArea indice3;

    @FXML
    private Label lbl_ind3;

    @FXML
    private Button btnSauvegarder;

    @FXML
    private CheckBox defaultScoring;

    @FXML
    private TextField score_init;

    @FXML
    private TextField decr_points;

    @FXML
    private TextField decr_sec;

    @FXML
    private TextField score_min;
    
    @FXML
    private Label errorScore;
    
    @FXML
    private Label errorFragment;
    
    @FXML
    private Label errorLabel;
    
    @FXML
    private Label errorType;
    
    private String xml;
    
    private String fragmentType;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ViewAddOrModifyQuestionController.addTextALimiter(description,200);
		ViewAddOrModifyQuestionController.addTextALimiter(indice1,200);
		ViewAddOrModifyQuestionController.addTextALimiter(indice2,200);
		ViewAddOrModifyQuestionController.addTextALimiter(indice3,200);
		
		 errorScore.setVisible(false);
		 errorLabel.setVisible(false);
		 errorFragment.setVisible(false);
		 errorType.setVisible(false);
		 
		nbIndices.getItems().addAll(1,2,3);
		nbIndices.setValue(1);
		lbl_ind2.setVisible(false);
		indice2.setVisible(false);
		lbl_ind3.setVisible(false);
		indice3.setVisible(false);
		
		formatPuzzle.getItems().addAll(
				"1x4",
				"1x5",
				"1x6");
		formatPuzzle.setValue("1x4");
		frag1_4.setVisible(true);
		frag1_5.setVisible(false);
		frag1_6.setVisible(false);

		score_init.setText("2500");
		decr_points.setText("75");
		decr_sec.setText("15");
		score_min.setText("400");
		defaultScoring.setSelected(true);
		score_init.setDisable(true);
		decr_points.setDisable(true);
		decr_sec.setDisable(true);
		score_min.setDisable(true);
		
		puzzleImage.setSelected(false);
		fragmentType = "Texte";
		btnTelechargerfrag1_4_1.setVisible(false);
		btnTelechargerfrag1_4_2.setVisible(false);
		btnTelechargerfrag1_4_3.setVisible(false);
		btnTelechargerfrag1_4_4.setVisible(false);
		
		btnTelechargerfrag1_5_1.setVisible(false);
		btnTelechargerfrag1_5_2.setVisible(false);
		btnTelechargerfrag1_5_3.setVisible(false);
		btnTelechargerfrag1_5_4.setVisible(false);
		btnTelechargerfrag1_5_5.setVisible(false);
	
		btnTelechargerfrag1_6_1.setVisible(false);
		btnTelechargerfrag1_6_2.setVisible(false);
		btnTelechargerfrag1_6_3.setVisible(false);
		btnTelechargerfrag1_6_4.setVisible(false);
		btnTelechargerfrag1_6_5.setVisible(false);
		btnTelechargerfrag1_6_6.setVisible(false);
	}
	
	public void initData() {
		File f =  new File(xml);
		if (f.exists()) {
			 defaultScoring.setSelected(false);
			 Puzzle puzzleFile = new Puzzle();
			 Puzzle p = puzzleFile.convertirXMLToJava(xml);
			 decr_points.setText("" + p.getDecr_pts());
			 decr_points.setDisable(false);
			 score_init.setText("" + p.getScore_init());
			 score_init.setDisable(false);
			 decr_sec.setText("" + p.getDecr_sec());
			 decr_sec.setDisable(false);
			 score_min.setText("" + p.getScore_min());
			 score_min.setDisable(false);
			 int nbFrag = p.getListeFragments().size();
			 switch (nbFrag) {
			 case 4:
				 formatPuzzle.setValue("1x4");
				 frag1_4.setVisible(true);
	    			 frag1_5.setVisible(false);
	    			 frag1_6.setVisible(false);
 
	    			if (p.getFragmentType().equals("Texte")) {
		    			puzzleImage.setSelected(false);
			    		btnTelechargerfrag1_4_1.setVisible(false);
			    		btnTelechargerfrag1_4_2.setVisible(false);
			    		btnTelechargerfrag1_4_3.setVisible(false);
			    		btnTelechargerfrag1_4_4.setVisible(false);
	    			} else {
	    				puzzleImage.setSelected(true);
			    		btnTelechargerfrag1_4_1.setVisible(true);
			    		btnTelechargerfrag1_4_2.setVisible(true);
			    		btnTelechargerfrag1_4_3.setVisible(true);
			    		btnTelechargerfrag1_4_4.setVisible(true);
	    			}
		    		
		    		frag1_4_1.setText(p.getListeFragments().get(0));
		    		frag1_4_2.setText(p.getListeFragments().get(1));
		    		frag1_4_3.setText(p.getListeFragments().get(2));
		    		frag1_4_4.setText(p.getListeFragments().get(3));
		    		break;
			 case 5:
				 formatPuzzle.setValue("1x5");
				 frag1_4.setVisible(false);
	    			 frag1_5.setVisible(true);
	    			 frag1_6.setVisible(false);
 
	    			if (p.getFragmentType().equals("Texte")) {
		    			puzzleImage.setSelected(false);
			    		btnTelechargerfrag1_5_1.setVisible(false);
			    		btnTelechargerfrag1_5_2.setVisible(false);
			    		btnTelechargerfrag1_5_3.setVisible(false);
			    		btnTelechargerfrag1_5_4.setVisible(false);
			    		btnTelechargerfrag1_5_5.setVisible(false);
	    			} else {
	    				puzzleImage.setSelected(true);
			    		btnTelechargerfrag1_5_1.setVisible(true);
			    		btnTelechargerfrag1_5_2.setVisible(true);
			    		btnTelechargerfrag1_5_3.setVisible(true);
			    		btnTelechargerfrag1_5_4.setVisible(true);
			    		btnTelechargerfrag1_5_5.setVisible(true);
	    			}
		    		
		    		frag1_5_1.setText(p.getListeFragments().get(0));
		    		frag1_5_2.setText(p.getListeFragments().get(1));
		    		frag1_5_3.setText(p.getListeFragments().get(2));
		    		frag1_5_4.setText(p.getListeFragments().get(3));
		    		frag1_5_5.setText(p.getListeFragments().get(4));
		    		break;
			 case 6:
				 formatPuzzle.setValue("1x6");
				 frag1_4.setVisible(false);
	    			 frag1_5.setVisible(false);
	    			 frag1_6.setVisible(true);
 
	    			if (p.getFragmentType().equals("Texte")) {
		    			puzzleImage.setSelected(false);
			    		btnTelechargerfrag1_6_1.setVisible(false);
			    		btnTelechargerfrag1_6_2.setVisible(false);
			    		btnTelechargerfrag1_6_3.setVisible(false);
			    		btnTelechargerfrag1_6_4.setVisible(false);
			    		btnTelechargerfrag1_6_5.setVisible(false);
			    		btnTelechargerfrag1_6_6.setVisible(false);
	    			} else {
	    				puzzleImage.setSelected(true);
			    		btnTelechargerfrag1_6_1.setVisible(true);
			    		btnTelechargerfrag1_6_2.setVisible(true);
			    		btnTelechargerfrag1_6_3.setVisible(true);
			    		btnTelechargerfrag1_6_4.setVisible(true);
			    		btnTelechargerfrag1_6_5.setVisible(true);
			    		btnTelechargerfrag1_6_6.setVisible(true);
	    			}
		    		
		    		frag1_6_1.setText(p.getListeFragments().get(0));
		    		frag1_6_2.setText(p.getListeFragments().get(1));
		    		frag1_6_3.setText(p.getListeFragments().get(2));
		    		frag1_6_4.setText(p.getListeFragments().get(3));
		    		frag1_6_5.setText(p.getListeFragments().get(4));
		    		frag1_6_6.setText(p.getListeFragments().get(4));
		    		break;
			 }
			 
			 description.setText(p.getIntitule());
			 switch (p.getListeIndices().size()) {
			 case 1:
				 nbIndices.setValue(1);
				 indice1.setText(p.getListeIndices().get(0));
	    			lbl_ind2.setVisible(false);
	    			indice2.setVisible(false);
	    			lbl_ind3.setVisible(false);
	    			indice3.setVisible(false);
				 break;
			 case 2:
				 nbIndices.setValue(2);
				 indice1.setText(p.getListeIndices().get(0));
				 indice2.setText(p.getListeIndices().get(1));
	    			lbl_ind2.setVisible(true);
	    			indice2.setVisible(true);
	    			lbl_ind3.setVisible(false);
	    			indice3.setVisible(false);
				 break;
			 case 3:
				 nbIndices.setValue(3);
				 indice1.setText(p.getListeIndices().get(0));
				 indice2.setText(p.getListeIndices().get(1));
				 indice3.setText(p.getListeIndices().get(2));
	    			lbl_ind2.setVisible(true);
	    			indice2.setVisible(true);
	    			lbl_ind3.setVisible(true);
	    			indice3.setVisible(true);
			 }
			 
		} else {
			System.out.println("\"" + xml + "\" doesn't exist");
		}	
	}
	
	public void setXML(String xml) {
		this.xml = xml;
	}
	
	@FXML
    void handleCheckboxScoring(ActionEvent event) {
		if (defaultScoring.isSelected()) {
			score_init.setText("2500");
			decr_points.setText("75");
			decr_sec.setText("15");
			score_min.setText("400");
			
			score_init.setDisable(true);
			decr_points.setDisable(true);
			decr_sec.setDisable(true);
			score_min.setDisable(true);
		} else {
			score_init.setDisable(false);
			decr_points.setDisable(false);
			decr_sec.setDisable(false);
			score_min.setDisable(false);
		}
    }
	
	@FXML
    void handleCheckboxTypePuzzle(ActionEvent event) {

		String formatSelect = formatPuzzle.getValue();
		
		if (puzzleImage.isSelected()) {
			fragmentType = "Image";
			switch (formatSelect) {
			case "1x4":
				btnTelechargerfrag1_4_1.setVisible(true);
				btnTelechargerfrag1_4_2.setVisible(true);
				btnTelechargerfrag1_4_3.setVisible(true);
				btnTelechargerfrag1_4_4.setVisible(true);
				break;
			case "1x5":
				btnTelechargerfrag1_5_1.setVisible(true);
				btnTelechargerfrag1_5_2.setVisible(true);
				btnTelechargerfrag1_5_3.setVisible(true);
				btnTelechargerfrag1_5_4.setVisible(true);
				btnTelechargerfrag1_5_5.setVisible(true);
				break;
			case "1x6":
				btnTelechargerfrag1_6_1.setVisible(true);
				btnTelechargerfrag1_6_2.setVisible(true);
				btnTelechargerfrag1_6_3.setVisible(true);
				btnTelechargerfrag1_6_4.setVisible(true);
				btnTelechargerfrag1_6_5.setVisible(true);
				btnTelechargerfrag1_6_6.setVisible(true);
				break;
			}
		} else {
			fragmentType = "Texte";
			switch (formatSelect) {
			case "1x4":
				btnTelechargerfrag1_4_1.setVisible(false);
				btnTelechargerfrag1_4_2.setVisible(false);
				btnTelechargerfrag1_4_3.setVisible(false);
				btnTelechargerfrag1_4_4.setVisible(false);
				break;
			case "1x5":
				btnTelechargerfrag1_5_1.setVisible(false);
				btnTelechargerfrag1_5_2.setVisible(false);
				btnTelechargerfrag1_5_3.setVisible(false);
				btnTelechargerfrag1_5_4.setVisible(false);
				btnTelechargerfrag1_5_5.setVisible(false);
				break;
			case "1x6":
				btnTelechargerfrag1_6_1.setVisible(false);
				btnTelechargerfrag1_6_2.setVisible(false);
				btnTelechargerfrag1_6_3.setVisible(false);
				btnTelechargerfrag1_6_4.setVisible(false);
				btnTelechargerfrag1_6_5.setVisible(false);
				btnTelechargerfrag1_6_6.setVisible(false);
				break;
			}
		}

    }

    @FXML
    void choisirFormat(ActionEvent event) {
    		puzzleImage.setSelected(false);
    		btnTelechargerfrag1_4_1.setVisible(false);
    		btnTelechargerfrag1_4_2.setVisible(false);
    		btnTelechargerfrag1_4_3.setVisible(false);
    		btnTelechargerfrag1_4_4.setVisible(false);

    		btnTelechargerfrag1_5_1.setVisible(false);
    		btnTelechargerfrag1_5_2.setVisible(false);
    		btnTelechargerfrag1_5_3.setVisible(false);
    		btnTelechargerfrag1_5_4.setVisible(false);
    		btnTelechargerfrag1_5_5.setVisible(false);

    		btnTelechargerfrag1_6_1.setVisible(false);
    		btnTelechargerfrag1_6_2.setVisible(false);
    		btnTelechargerfrag1_6_3.setVisible(false);
    		btnTelechargerfrag1_6_4.setVisible(false);
    		btnTelechargerfrag1_6_5.setVisible(false);
    		btnTelechargerfrag1_6_6.setVisible(false);
    		
    		String formatSelect = formatPuzzle.getValue();
    		
    		switch (formatSelect) {
	    		case "1x4":
	    			frag1_4.setVisible(true);
	    			frag1_5.setVisible(false);
	    			frag1_6.setVisible(false);
	    			break;
	    		case "1x5":
	    			frag1_4.setVisible(false);
	    			frag1_5.setVisible(true);
	    			frag1_6.setVisible(false);
	    			break;
	    		case "1x6":
	    			frag1_4.setVisible(false);
	    			frag1_5.setVisible(false);
	    			frag1_6.setVisible(true);
	    			break;
    		}
    }
    
    @FXML
    void handleComboIndice(ActionEvent event) {
    		int nbIndiceSelect = nbIndices.getValue();
		
		switch (nbIndiceSelect) {
    		case 1:
    			lbl_ind2.setVisible(false);
    			indice2.setVisible(false);
    			lbl_ind3.setVisible(false);
    			indice3.setVisible(false);
    			break;
    		case 2:
    			lbl_ind2.setVisible(true);
    			indice2.setVisible(true);
    			lbl_ind3.setVisible(false);
    			indice3.setVisible(false);
    			break;
    		case 3:
    			lbl_ind2.setVisible(true);
    			indice2.setVisible(true);
    			lbl_ind3.setVisible(true);
    			indice3.setVisible(true);
    			break;
		}
    }

    @FXML
    void sauvegarder(ActionEvent event) {
    		if (score_init.getText().isEmpty() || decr_points.getText().isEmpty() || decr_sec.getText().isEmpty() || score_min.getText().isEmpty()) {
    			errorScore.setVisible(true);
    		} else {
    			if (!ViewAddOrModifyInstructionController.tryParseDouble(score_init.getText()) || 
    					!ViewAddOrModifyInstructionController.tryParseDouble(decr_points.getText()) ||
    					!ViewAddOrModifyInstructionController.tryParseDouble(decr_sec.getText()) || 
    					!ViewAddOrModifyInstructionController.tryParseDouble(score_min.getText())) {
        			errorType.setVisible(true);
    			} else {
	    			String formatSelect = formatPuzzle.getValue();
	        		
	        		switch (formatSelect) {
	    	    		case "1x4":
	    	    			
	    	    			 if (frag1_4_1.getText().isEmpty() || frag1_4_2.getText().isEmpty() || frag1_4_3.getText().isEmpty() || frag1_4_4.getText().isEmpty()) {
	    	    		    	 	errorFragment.setVisible(true);
	    	    		     } else {
	    	    		    	 	if (nbIndices.getValue().equals(1) && !indice1.getText().isEmpty() ||
	    	    		    	 		nbIndices.getValue().equals(2) && !indice1.getText().isEmpty() && !indice2.getText().isEmpty() ||
	    	    		    	 		nbIndices.getValue().equals(3) && !indice1.getText().isEmpty() && !indice2.getText().isEmpty()  && !indice3.getText().isEmpty()) {
	    	    		    	 		 if (description.getText().isEmpty())
	    	    		    	 			errorLabel.setVisible(true);
	    	    		    	 		 else 
	    	    		    	 			 creerXML();
	    	    		    	 	} else 
	    	    		    	 		errorLabel.setVisible(true);
	    	    		     }
	 
	    	    			break;
	    	    		case "1x5":
	    	    			
	    	    			if (frag1_5_1.getText().isEmpty() || frag1_5_2.getText().isEmpty() || frag1_5_3.getText().isEmpty() || frag1_5_4.getText().isEmpty() || frag1_5_5.getText().isEmpty()) {
		    		    	 	errorFragment.setVisible(true);
		    		     } else {
		    		    	 	if (nbIndices.getValue().equals(1) && !indice1.getText().isEmpty() ||
		    		    	 		nbIndices.getValue().equals(2) && !indice1.getText().isEmpty() && !indice2.getText().isEmpty() ||
		    		    	 		nbIndices.getValue().equals(3) && !indice1.getText().isEmpty() && !indice2.getText().isEmpty()  && !indice3.getText().isEmpty()) {
		    		    	 		 if (description.getText().isEmpty())
		    		    	 			errorLabel.setVisible(true);
		    		    	 		 else 
		    		    	 			 creerXML();
		    		    	 	} else 
		    		    	 		errorLabel.setVisible(true);
		    		     }
	    	    			
	    	    			break;
	    	    		case "1x6":
	    	    			
	    	    			if (frag1_6_1.getText().isEmpty() || frag1_6_2.getText().isEmpty() || frag1_6_3.getText().isEmpty() || frag1_6_4.getText().isEmpty() || frag1_6_5.getText().isEmpty() || frag1_6_6.getText().isEmpty()) {
		    		    	 	errorFragment.setVisible(true);
		    		     } else {
		    		    	 	if (nbIndices.getValue().equals(1) && !indice1.getText().isEmpty() ||
		    		    	 		nbIndices.getValue().equals(2) && !indice1.getText().isEmpty() && !indice2.getText().isEmpty() ||
		    		    	 		nbIndices.getValue().equals(3) && !indice1.getText().isEmpty() && !indice2.getText().isEmpty()  && !indice3.getText().isEmpty()) {
		    		    	 		 if (description.getText().isEmpty())
		    		    	 			errorLabel.setVisible(true);
		    		    	 		 else 
		    		    	 			 creerXML();
		    		    	 	} else 
		    		    	 		errorLabel.setVisible(true);
		    		     }
	    	    			
	    	    			break;
	        		}
    			}	
    		}
    }

    private void creerXML() {
    	
	    	ArrayList<String> listeIndices = new ArrayList<String>();
	    	ArrayList<String> listeFragments = new ArrayList<String>();
	
	    	String formatSelect = formatPuzzle.getValue();
	
	    	switch (formatSelect) {
	    	case "1x4":
	    		listeFragments.add(frag1_4_1.getText());
		    	listeFragments.add(frag1_4_2.getText());
		    	listeFragments.add(frag1_4_3.getText());
		    	listeFragments.add(frag1_4_4.getText());
	    		break;
	    	case "1x5":
	    		listeFragments.add(frag1_5_1.getText());
		    	listeFragments.add(frag1_5_2.getText());
		    	listeFragments.add(frag1_5_3.getText());
		    	listeFragments.add(frag1_5_4.getText());
		    	listeFragments.add(frag1_5_5.getText());
	    		break;
	    	case "1x6":
	    		listeFragments.add(frag1_6_1.getText());
		    	listeFragments.add(frag1_6_2.getText());
		    	listeFragments.add(frag1_6_3.getText());
		    	listeFragments.add(frag1_6_4.getText());
		    	listeFragments.add(frag1_6_5.getText());
		    	listeFragments.add(frag1_6_6.getText());
	    		break;
	    	}
	    	
	    	int nbIndiceSelect = nbIndices.getValue();
			
			switch (nbIndiceSelect) {
	    		case 1:
	    			listeIndices.add(indice1.getText());
	    			break;
	    		case 2:
	    			listeIndices.add(indice1.getText());
	        		listeIndices.add(indice2.getText());
	    			break;
	    		case 3:
	    			listeIndices.add(indice1.getText());
	        		listeIndices.add(indice2.getText());
	        		listeIndices.add(indice3.getText());
	    			break;
			}
			
		if (puzzleImage.isSelected()) {
			fragmentType = "Image";
		} else {
			fragmentType = "Texte";
		}
		
	    	Puzzle p = new Puzzle("Nom", Double.parseDouble(score_init.getText().toString()), Double.parseDouble(decr_points.getText().toString()), Double.parseDouble(decr_sec.getText().toString()),Double.parseDouble(score_min.getText().toString()), description.getText().toString(), fragmentType, listeIndices, listeFragments);
	    	
	    	p.convertirJavaToXML(p, xml);
	    	// popup de confirmation
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Paramétrage  d'un puzzle");
			alert.setContentText("Le paramétrage a bien été enregistré !");
			alert.showAndWait();
	}

	@FXML
    void telechargerfrag1_4_1(ActionEvent event) {
    		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		Path cheminAbsoluActuel = Paths.get("").toAbsolutePath();
		Path cheminAbsoluImage = Paths.get(file.getAbsolutePath());
		frag1_4_1.setText(cheminAbsoluActuel.relativize(cheminAbsoluImage).toString());
    }

    @FXML
    void telechargerfrag1_4_2(ActionEvent event) {
    		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		Path cheminAbsoluActuel = Paths.get("").toAbsolutePath();
		Path cheminAbsoluImage = Paths.get(file.getAbsolutePath());
		frag1_4_2.setText(cheminAbsoluActuel.relativize(cheminAbsoluImage).toString());
    }

    @FXML
    void telechargerfrag1_4_3(ActionEvent event) {
    		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		Path cheminAbsoluActuel = Paths.get("").toAbsolutePath();
		Path cheminAbsoluImage = Paths.get(file.getAbsolutePath());
		frag1_4_3.setText(cheminAbsoluActuel.relativize(cheminAbsoluImage).toString());
    }
    
    @FXML
    void telechargerfrag1_4_4(ActionEvent event) {
    		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		Path cheminAbsoluActuel = Paths.get("").toAbsolutePath();
		Path cheminAbsoluImage = Paths.get(file.getAbsolutePath());
		frag1_4_4.setText(cheminAbsoluActuel.relativize(cheminAbsoluImage).toString());
    }

    @FXML
    void telechargerfrag1_5_1(ActionEvent event) {
    		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		Path cheminAbsoluActuel = Paths.get("").toAbsolutePath();
		Path cheminAbsoluImage = Paths.get(file.getAbsolutePath());
		frag1_5_1.setText(cheminAbsoluActuel.relativize(cheminAbsoluImage).toString());
    }

    @FXML
    void telechargerfrag1_5_2(ActionEvent event) {
    		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		Path cheminAbsoluActuel = Paths.get("").toAbsolutePath();
		Path cheminAbsoluImage = Paths.get(file.getAbsolutePath());
		frag1_5_2.setText(cheminAbsoluActuel.relativize(cheminAbsoluImage).toString());
    }

    @FXML
    void telechargerfrag1_5_3(ActionEvent event) {
    		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		Path cheminAbsoluActuel = Paths.get("").toAbsolutePath();
		Path cheminAbsoluImage = Paths.get(file.getAbsolutePath());
		frag1_5_3.setText(cheminAbsoluActuel.relativize(cheminAbsoluImage).toString());
    }

    @FXML
    void telechargerfrag1_5_4(ActionEvent event) {
    		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		Path cheminAbsoluActuel = Paths.get("").toAbsolutePath();
		Path cheminAbsoluImage = Paths.get(file.getAbsolutePath());
		frag1_5_4.setText(cheminAbsoluActuel.relativize(cheminAbsoluImage).toString());
    }

    @FXML
    void telechargerfrag1_5_5(ActionEvent event) {
    		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		Path cheminAbsoluActuel = Paths.get("").toAbsolutePath();
		Path cheminAbsoluImage = Paths.get(file.getAbsolutePath());
		frag1_5_5.setText(cheminAbsoluActuel.relativize(cheminAbsoluImage).toString());
    }

    @FXML
    void telechargerfrag1_6_1(ActionEvent event) {
    		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		Path cheminAbsoluActuel = Paths.get("").toAbsolutePath();
		Path cheminAbsoluImage = Paths.get(file.getAbsolutePath());
		frag1_6_1.setText(cheminAbsoluActuel.relativize(cheminAbsoluImage).toString());
    }

    @FXML
    void telechargerfrag1_6_2(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		Path cheminAbsoluActuel = Paths.get("").toAbsolutePath();
		Path cheminAbsoluImage = Paths.get(file.getAbsolutePath());
		frag1_6_2.setText(cheminAbsoluActuel.relativize(cheminAbsoluImage).toString());
    }

    @FXML
    void telechargerfrag1_6_3(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		Path cheminAbsoluActuel = Paths.get("").toAbsolutePath();
		Path cheminAbsoluImage = Paths.get(file.getAbsolutePath());
		frag1_6_3.setText(cheminAbsoluActuel.relativize(cheminAbsoluImage).toString());
    }

    @FXML
    void telechargerfrag1_6_4(ActionEvent event) {
    		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		Path cheminAbsoluActuel = Paths.get("").toAbsolutePath();
		Path cheminAbsoluImage = Paths.get(file.getAbsolutePath());
		frag1_6_4.setText(cheminAbsoluActuel.relativize(cheminAbsoluImage).toString());
    }

    @FXML
    void telechargerfrag1_6_5(ActionEvent event) {
    		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		Path cheminAbsoluActuel = Paths.get("").toAbsolutePath();
		Path cheminAbsoluImage = Paths.get(file.getAbsolutePath());
		frag1_6_5.setText(cheminAbsoluActuel.relativize(cheminAbsoluImage).toString());
    }
    
    @FXML
    void telechargerfrag1_6_6(ActionEvent event) {
    		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		Path cheminAbsoluActuel = Paths.get("").toAbsolutePath();
		Path cheminAbsoluImage = Paths.get(file.getAbsolutePath());
		frag1_6_6.setText(cheminAbsoluActuel.relativize(cheminAbsoluImage).toString());
    }

 
}

