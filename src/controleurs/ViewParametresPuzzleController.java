package controleurs;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ViewParametresPuzzleController implements Initializable {

    @FXML
    private ComboBox<?> formatPuzzle;

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
    private ComboBox<?> nbIndices;

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
    
    private String xml;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	public void initData() {
		File f =  new File(xml);
		if (f.exists()) {
			
		} else {
			System.out.println("\"" + xml + "\" doesn't exist");
		}	
	}
	
	public void setXML(String xml) {
		this.xml = xml;
	}

    @FXML
    void choisirFormat(ActionEvent event) {

    }

    @FXML
    void sauvegarder(ActionEvent event) {

    }

    @FXML
    void telechargerfrag1_4_1(ActionEvent event) {

    }

    @FXML
    void telechargerfrag1_4_2(ActionEvent event) {

    }

    @FXML
    void telechargerfrag1_4_4(ActionEvent event) {

    }

    @FXML
    void telechargerfrag1_5_1(ActionEvent event) {

    }

    @FXML
    void telechargerfrag1_5_2(ActionEvent event) {

    }

    @FXML
    void telechargerfrag1_5_3(ActionEvent event) {

    }

    @FXML
    void telechargerfrag1_5_4(ActionEvent event) {

    }

    @FXML
    void telechargerfrag1_5_5(ActionEvent event) {

    }

    @FXML
    void telechargerfrag1_6_1(ActionEvent event) {

    }

    @FXML
    void telechargerfrag1_6_2(ActionEvent event) {

    }

    @FXML
    void telechargerfrag1_6_3(ActionEvent event) {

    }

    @FXML
    void telechargerfrag1_6_4(ActionEvent event) {

    }

    @FXML
    void telechargerfrag1_6_5(ActionEvent event) {

    }

}

