package controleurs;



import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
import com.sun.javafx.application.HostServicesDelegate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import modeles.Explication;

public class ViewPageExplicationController implements Initializable {

	@FXML
	private ImageView fondEcran;
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
	private Button buttonSuivant;
	private String xml="";
	private boolean soloBloc=true;
	private int cmptChronologie=0;
	private String xmlChronologie="";
	private boolean son;
	private int score;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void initData(boolean son, int score){
		this.son=son;
		this.score=score;
		
		Explication expl = new Explication();
		File f =  new File(xml);
		if (f.exists()){
			Explication explXml = expl.convertirXMLToJava(xml);

			theme.setText(explXml.getTitre());
			explication.setText(explXml.getContenu());
			source.setText(explXml.getSource());
			
			if (explXml.getListeImages().get(0).contains("icone_image.png")) {
				image1.setVisible(false);
			} else
				image1.setImage(ViewParametresPageExplicationController.chargerImage(explXml.getListeImages().get(0)));
			
			if (explXml.getListeImages().get(1).contains("icone_image.png")) {
				image2.setVisible(false);
			} else
				image2.setImage(ViewParametresPageExplicationController.chargerImage(explXml.getListeImages().get(1)));
			
			//System.out.println(explXml.getListeLiens().size());
			if (explXml.getListeLiens().contains("Aucun lien") || explXml.getListeLiens().isEmpty()) {
				label1.setVisible(false);
				hyperlien.setVisible(false);
			} else
				hyperlien.setText(explXml.getListeLiens().toString().replace("[", "").replace("]", "").replace(", ", "\n"));
		} else 
			System.out.println("xml :"+xml);
	}
	
	@FXML
	void ouvrirPageWeb(ActionEvent event) throws IOException {
		/*try {
			//java.awt.Desktop.getDesktop().browse(new URL(hyperlien.getText()).toURI());
			 app.getHostServices().showDocument("http://www.jumpingbean.biz");
		} catch (IOException e) {
			System.out.println("Error: the following link could not be open:" + (hyperlien.getText()));
		    e.printStackTrace();
		} catch (URISyntaxException e) {
		    e.printStackTrace();
		}*/
	}

	public void setXML(String xml) {
		this.xml=xml;
	}
	
	@FXML
	private void ClickBT_Suivant(ActionEvent event){
		if(soloBloc){
			Stage stage = (Stage) buttonSuivant.getScene().getWindow();
			stage.close();
		} else {
			Stage stage = (Stage) buttonSuivant.getScene().getWindow();
			Node node=JFxUtils.loadNextBloc(cmptChronologie, xmlChronologie, son,score);
			if (node!=null){
				stage.setScene(new Scene((Parent) node, 850, 650));
			} else {
				stage.close();
			}
		}
	}

	public void setChronologie(boolean soloBloc, int cmptChronologie, String xmlChronologie) {
		this.soloBloc=soloBloc;
		this.cmptChronologie=cmptChronologie;
		this.xmlChronologie=xmlChronologie;
	}
}
