package controleurs;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import modeles.Explication;

public class PageExplicationController implements Initializable {

	    @FXML
	    private ImageView fondEcran;

	    @FXML
	    private TextField champTitre;
	    
	    @FXML
	    private TextField champSource;
	    
	    @FXML
	    private TextArea champExplication;
	    
	    @FXML
	    private TextArea champHyperlien;

	    @FXML
	    private Label theme;

	    @FXML
	    private Label explication;
	    
	    @FXML
	    private Label source;
	    
	    @FXML
	    private Label label1;
	    
	    @FXML
	    private Hyperlink hyperlien;

	    @FXML
	    private ImageView image1;

	    @FXML
	    private ImageView image2;
	    
	    @FXML
	    private Button validerExplication;
	    
	    @FXML
	    private Button enregistrer;
	    
	    @Override
		public void initialize(URL location, ResourceBundle resources) {
	    		champTitre.setVisible(false);
	    		champExplication.setVisible(false);
	    		validerExplication.setVisible(false);
	    		champHyperlien.setVisible(false);
	    		champSource.setVisible(false);
	    		enregistrer.setVisible(false);
	    		
	    		Explication expl = new Explication(); 
	    		Explication explXml = expl.convertirXMLToJava("FichiersDeConfig/explication.xml");
	    		
	    		theme.setText(explXml.getTitre());
	    		explication.setText(explXml.getContenu());
	    		source.setText(explXml.getSource());
	    		hyperlien.setText(explXml.getListeLiens().toString().replace("[", "").replace("]", ""));
		}

	    @FXML
	    void ChangerImage(MouseEvent event) {

	    }

	    @FXML
	    void changerTitre(MouseEvent event) {
	    		//titre
	    		theme.setVisible(false);
	    		champTitre.setText(theme.getText());
	    		champTitre.setVisible(true);
	    		
	    		//explication
	    		 explication.setVisible(false);
	 		    source.setVisible(false);
	 	    		champExplication.setText(explication.getText());
	 	    		champExplication.setVisible(true);
	 	    		validerExplication.setVisible(true);
	 	    		
	 	    	//source
	 	    		source.setVisible(false);
		    		champSource.setText(source.getText());
		    		champSource.setVisible(true);
	 	    	
	 	    	//lien(s)
				    hyperlien.setVisible(false);
				    source.setVisible(false);
				    label1.setVisible(false);
			    		champHyperlien.setText(hyperlien.getText());
			    		champHyperlien.setVisible(true);
			    		enregistrer.setVisible(true);
	    }

	  /*  @FXML
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
		    source.setVisible(false);
	    		champExplication.setText(explication.getText());
	    		champExplication.setVisible(true);
	    		validerExplication.setVisible(true);
	    }
	    
	    @FXML
	    void validerExplication(ActionEvent event) throws IOException {
		    		champExplication.setVisible(false);
		    		explication.setText(champExplication.getText());
		    		explication.setVisible(true);
		    		source.setVisible(true);
		    		validerExplication.setVisible(false);
	    }
	    
	    @FXML
	    void changerSource(MouseEvent event) {
		    	source.setVisible(false);
	    		champSource.setText(source.getText());
	    		champSource.setVisible(true);
	    }
	    
	    @FXML
	    void validerSource(KeyEvent event) {
	    		if (event.getCode().equals(KeyCode.ENTER)) {
	    			champSource.setVisible(false);
		    		source.setText(champSource.getText());
		    		source.setVisible(true);
	    		}
	    }
	    
	    @FXML
	    void changerLien(MouseEvent event) {
		    hyperlien.setVisible(false);
		    source.setVisible(false);
		    label1.setVisible(false);
	    		champHyperlien.setText(hyperlien.getText());
	    		champHyperlien.setVisible(true);
	    		enregistrer.setVisible(true);
	    }*/
	    
	    @FXML
	    void enregistrerParametrage(ActionEvent event) throws IOException {
	    		
	    		//titre
	    		champTitre.setVisible(false);
	    		theme.setText(champTitre.getText());
	    		theme.setVisible(true);
	    		
	    		//explication
	    		champExplication.setVisible(false);
	    		explication.setText(champExplication.getText());
	    		explication.setVisible(true);
	    		source.setVisible(true);
	    		validerExplication.setVisible(false);
	    		
	    		//source
	    		champSource.setVisible(false);
	    		source.setText(champSource.getText());
	    		source.setVisible(true);
	    		
	    		//lien(s)
	    		champHyperlien.setVisible(false);
	    		hyperlien.setText(champHyperlien.getText());
	    		hyperlien.setVisible(true);
	    		
	    		
	    		//image1
	    		
	    		
	    		//image2
	    		
	    		//general
	    		label1.setVisible(true);
	    		enregistrer.setVisible(false);
	    		
	    		//ecriture xml
	    		ArrayList<String> ListeLiens = new ArrayList<String>();
	    		//while (!hyperlien.getText().equals("Entrez un ou plusieurs lien(s)") || hyperlien.getText().length() != 0) {
	    			ListeLiens.add(hyperlien.getText());
	    		//}
	   		 
	   		 Explication objetExplication = new Explication(theme.getText(), explication.getText(), source.getText(), ListeLiens);
	   		 
	   		objetExplication.convertirJavaToXML(objetExplication, "FichiersDeConfig/explication.xml");
	    		
	    }
}
