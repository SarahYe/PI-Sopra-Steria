package controleurs;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;
import modeles.Explication;

public class ParametresPageExplicationController implements Initializable {
	
	private boolean modifierExplication;

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
	private Label erreur;
	@FXML
	private Label messageImage;
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
	private Button enregistrer;
	@FXML
	private Button boutonImage1;
	@FXML
	private Button boutonImage2;

	String cheminImage1 = "Ressources/Images/icone_image.png";
	String cheminImage2 = "Ressources/Images/icone_image.png";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		champTitre.setVisible(false);
		champExplication.setVisible(false);
		champHyperlien.setVisible(false);
		champSource.setVisible(false);
		enregistrer.setVisible(false);
		erreur.setVisible(false);
		boutonImage1.setDisable(true);
		boutonImage2.setDisable(true);
		messageImage.setVisible(false);

		//initData(true);
		
		/*Explication expl = new Explication();
		Explication explXml = expl.convertirXMLToJava("FichiersDeConfig/explication.xml");

		theme.setText(explXml.getTitre());
		explication.setText(explXml.getContenu());
		source.setText(explXml.getSource());

		if (explXml.getListeImages().size() == 0) {
			image1.setImage(chargerImage(cheminImage1));
			image2.setImage(chargerImage(cheminImage2));
		} else if (explXml.getListeImages().size() == 1) {
			image1.setImage(chargerImage(explXml.getListeImages().get(0)));
			image2.setImage(chargerImage(cheminImage2));
		} else {
			image1.setImage(chargerImage(explXml.getListeImages().get(0)));
			image2.setImage(chargerImage(explXml.getListeImages().get(1)));
		}

		hyperlien.setText(explXml.getListeLiens().toString().replace("[", "").replace("]", "").replace(", ", "\n"));*/
	}
	
	public void initData(String fichierXML) {
		File f =  new File(fichierXML);
		if (f.exists()) 
			System.out.print("found");
		else 
			System.out.print("not found");
	}
	
	/*public void initData(boolean modification) {
		this.modifierExplication = modification;
		
		if (this.modifierExplication) {
			Explication expl = new Explication();
			Explication explXml = expl.convertirXMLToJava("FichiersDeConfig/explication.xml");

			theme.setText(explXml.getTitre());
			explication.setText(explXml.getContenu());
			source.setText(explXml.getSource());

			if (explXml.getListeImages().size() == 0) {
				image1.setImage(chargerImage(cheminImage1));
				image2.setImage(chargerImage(cheminImage2));
			} else if (explXml.getListeImages().size() == 1) {
				image1.setImage(chargerImage(explXml.getListeImages().get(0)));
				image2.setImage(chargerImage(cheminImage2));
			} else {
				image1.setImage(chargerImage(explXml.getListeImages().get(0)));
				image2.setImage(chargerImage(explXml.getListeImages().get(1)));
			}

			hyperlien.setText(explXml.getListeLiens().toString().replace("[", "").replace("]", "").replace(", ", "\n"));
		} else {
			theme.setText("Cliquez moi pour modifier");
			explication.setText("Explication");
			source.setText("Source");
			image1.setImage(chargerImage(cheminImage1));
			image2.setImage(chargerImage(cheminImage2));
			hyperlien.setText("Lien");
		}
	}
*/
	@FXML
	void changerImage1(ActionEvent event) throws IOException {

		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		try {
			BufferedImage bufferedImage = ImageIO.read(file);
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			image1.setImage(image);

			Path cheminAbsoluActuel = Paths.get("").toAbsolutePath();
			Path cheminAbsoluImage = Paths.get(file.getAbsolutePath());
			cheminImage1 = cheminAbsoluActuel.relativize(cheminAbsoluImage).toString();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	void changerImage2(ActionEvent event) throws IOException {

		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		try {
			BufferedImage bufferedImage = ImageIO.read(file);
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			image2.setImage(image);

			Path cheminAbsoluActuel = Paths.get("").toAbsolutePath();
			Path cheminAbsoluImage = Paths.get(file.getAbsolutePath());
			cheminImage2 = cheminAbsoluActuel.relativize(cheminAbsoluImage).toString();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	void changerTitre(MouseEvent event) {

		// éléments invisibles
		theme.setVisible(false);
		explication.setVisible(false);
		source.setVisible(false);
		hyperlien.setVisible(false);
		label1.setVisible(false);
		erreur.setVisible(false);

		// éléments visibles
		champTitre.setVisible(true);
		champExplication.setVisible(true);
		champSource.setVisible(true);
		champHyperlien.setVisible(true);
		enregistrer.setVisible(true);
		messageImage.setVisible(true);
		boutonImage1.setDisable(false);
		boutonImage2.setDisable(false);

		// titre
		champTitre.setText(theme.getText());

		// explication
		champExplication.setText(explication.getText());

		// source
		champSource.setText(source.getText());

		// lien(s)
		champHyperlien.setText(hyperlien.getText());

	}

	@FXML
	void enregistrerParametrage(ActionEvent event) throws IOException {

		if (champExplication.getText().length() == 0)
			erreur.setVisible(true);
		else {
			
			//Popup pour le nom du fichier
			TextInputDialog dialog = new TextInputDialog("");
			dialog.setTitle("Enregistrement du fichier de configuration");
			dialog.setHeaderText("Entrez le nom du fichier de configuration (sans l'extension)");
			//dialog.setContentText("Entrez le nom du fichier de configuration (sans l'extension) :");
			
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
			    System.out.println("Nom entré: " + result.get());
			}
			// éléments invisibles
			champTitre.setVisible(false);
			champExplication.setVisible(false);
			champHyperlien.setVisible(false);
			champSource.setVisible(false);
			enregistrer.setVisible(false);
			erreur.setVisible(false);
			messageImage.setVisible(false);
			boutonImage1.setDisable(true);
			boutonImage2.setDisable(true);

			// titre
			theme.setText(champTitre.getText());

			// explication
			explication.setText(champExplication.getText());

			// source
			source.setText(champSource.getText());

			// lien(s)
			if (champHyperlien.getText().length() == 0)
				hyperlien.setText("Aucun lien");
			else
				hyperlien.setText(champHyperlien.getText());

			// image1

			// image2

			// éléments visibles
			theme.setVisible(true);
			explication.setVisible(true);
			source.setVisible(true);
			hyperlien.setVisible(true);
			label1.setVisible(true);

			// ecriture xml
			ArrayList<String> ListeLiens = new ArrayList<String>();
			if (hyperlien.getText().contains("\n") || !hyperlien.getText().isEmpty()) {
				String[] difLiens = hyperlien.getText().split("\n");
				for (String s : difLiens)
					ListeLiens.add(s);
			}

			ArrayList<String> ListeImages = new ArrayList<String>();
			ListeImages.add(cheminImage1);
			ListeImages.add(cheminImage2);

			Explication objetExplication = new Explication(theme.getText(), explication.getText(), source.getText(),
					ListeLiens, ListeImages);
			objetExplication.convertirJavaToXML(objetExplication, "FichiersDeConfig/explication.xml");
			//objetExplication.convertirJavaToXML(objetExplication, "FichiersDeConfig/" + result.get() +".xml");
		}

	}
	
	public void setTitre(String mTitre) {
		theme.setText(mTitre);
	}

	public void setExplication(String mExplication) {
		explication.setText(mExplication);
	}
	
	public void setSource(String mSource) {
		source.setText(mSource);
	}
	
	public void setLien(String mlien) {
		hyperlien.setText(mlien);
	}
	
	public void setImage1(String chemin) {
		image1.setImage(chargerImage(chemin));
	}
	
	public void setImage2(String chemin) {
		image2.setImage(chargerImage(chemin));
	}

	public static Image chargerImage(String chemin) {
		File file = new File(chemin);
		Image image = new Image(file.toURI().toString());
		return image;
	}
	
	
	/*
	 * @FXML void validerTitre(KeyEvent event) {
	 * 
	 * if (event.getCode().equals(KeyCode.ENTER)) { champTitre.setVisible(false);
	 * theme.setText(champTitre.getText()); theme.setVisible(true); } }
	 * 
	 * @FXML void changerExplication(MouseEvent event) {
	 * explication.setVisible(false); source.setVisible(false);
	 * champExplication.setText(explication.getText());
	 * champExplication.setVisible(true); validerExplication.setVisible(true); }
	 * 
	 * @FXML void validerExplication(ActionEvent event) throws IOException {
	 * champExplication.setVisible(false);
	 * explication.setText(champExplication.getText());
	 * explication.setVisible(true); source.setVisible(true);
	 * validerExplication.setVisible(false); }
	 * 
	 * @FXML void changerSource(MouseEvent event) { source.setVisible(false);
	 * champSource.setText(source.getText()); champSource.setVisible(true); }
	 * 
	 * @FXML void validerSource(KeyEvent event) { if
	 * (event.getCode().equals(KeyCode.ENTER)) { champSource.setVisible(false);
	 * source.setText(champSource.getText()); source.setVisible(true); } }
	 * 
	 * @FXML void changerLien(MouseEvent event) { hyperlien.setVisible(false);
	 * source.setVisible(false); label1.setVisible(false);
	 * champHyperlien.setText(hyperlien.getText()); champHyperlien.setVisible(true);
	 * enregistrer.setVisible(true); }
	 */

}
