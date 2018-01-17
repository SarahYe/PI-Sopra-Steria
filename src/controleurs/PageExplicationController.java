package controleurs;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
import com.sun.javafx.application.HostServicesDelegate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import modeles.Explication;

public class PageExplicationController implements Initializable {

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Explication expl = new Explication(); 
		Explication explXml = expl.convertirXMLToJava("FichiersDeConfig/explication.xml");

		theme.setText(explXml.getTitre());
		explication.setText(explXml.getContenu());
		source.setText(explXml.getSource());
		
		if (explXml.getListeImages().get(0).contains("icone_image.png")) {
			image1.setVisible(false);
		} else
			image1.setImage(ParametresPageExplicationController.chargerImage(explXml.getListeImages().get(0)));
		
		if (explXml.getListeImages().get(1).contains("icone_image.png")) {
			image2.setVisible(false);
		} else
			image2.setImage(ParametresPageExplicationController.chargerImage(explXml.getListeImages().get(1)));
		
		System.out.println(explXml.getListeLiens().size());
		if (explXml.getListeLiens().contains("Aucun lien") || explXml.getListeLiens().isEmpty()) {
			label1.setVisible(false);
			hyperlien.setVisible(false);
		} else
			hyperlien.setText(explXml.getListeLiens().toString().replace("[", "").replace("]", "").replace(", ", "\n"));
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

}
