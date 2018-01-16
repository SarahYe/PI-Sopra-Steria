package controleurs;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class PageExplicationController implements Initializable {

	    @FXML
	    private ImageView fondEcran;

	    @FXML
	    private TextField champTitre;
	    
	    @FXML
	    private TextArea champExplication;

	    @FXML
	    private Label theme;

	    @FXML
	    private Label explication;

	    @FXML
	    private ImageView image1;

	    @FXML
	    private ImageView image2;
	    
	    @FXML
	    private Button validerExplication;
	    
	    @Override
		public void initialize(URL location, ResourceBundle resources) {
	    		champTitre.setVisible(false);
	    		champExplication.setVisible(false);
		}

	    @FXML
	    void ChangerImage(MouseEvent event) {

	    }

	    @FXML
	    void changerTitre(MouseEvent event) {
	    		theme.setVisible(false);
	    		champTitre.setText(theme.getText());
	    		champTitre.setVisible(true);
	    }

	    @FXML
	    void validerTitre(KeyEvent event) {
	    	
	    		if (event.getCode().equals(KeyCode.ENTER)) {
		    		champTitre.setVisible(false);
		    		theme.setText(champTitre.getText());
		    		theme.setVisible(true);
	    		}
	    }
	    
	    @FXML
	    void changerExplication(MouseEvent event) {
		    explication.setVisible(false);
	    		champExplication.setText(explication.getText());
	    		champExplication.setVisible(true);
	    }
	    
	    @FXML
	    void validerExplication(ActionEvent event) throws IOException {
		    		champExplication.setVisible(false);
		    		explication.setText(champExplication.getText());
		    		explication.setVisible(true);
	    }
}
