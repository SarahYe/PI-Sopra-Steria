package controleurs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import modeles.Accueil;
import modeles.FondEcran;
import modeles.Titre;

/**
 * Controleur de l'interface joueur d'un accueil.
 * 
 * @author YESUFU Sarah
 * @version 1.0
 */
public class ViewAccueilController implements Initializable {

	@FXML
	private Button buttonJouer;
	@FXML
	private AnchorPane anchor;
	@FXML
	private ImageView imageFDE;
	@FXML
	private ImageView imageTitre;
	@FXML
	private Label titreTexte;

	private String xml = "";
	private boolean soloBloc = true;
	private int cmptChronologie = 0;
	private String xmlChronologie = "";
	private boolean son;
	private int score;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

	/**
	 * Fonction d'initialisation des données paramétrées d'un accueil. Vérifie
	 * l'existence d'un fichier xml correspondant puis recupère les informations
	 * paramétrées.
	 * 
	 * @param xml Chemin vers le fichier xml de paramétrage.
	 * @param son Paramètre d'activation ou non des éléments sonores.
	 * @param score Score cumulé par le joueur lors des événements antérieurs.
	 */
	public void initData(String xml, boolean son, int score) {
		this.son = son;
		this.score = score;

		Accueil accueil = new Accueil();
		File f = new File(xml);
		if (f.exists()) {
			Accueil accXML = accueil.convertirXMLToJava(xml);
			Titre titre = accXML.getTitre();

			if (titre.getImageVsTexte().contains("Texte")) {
				imageTitre.setVisible(false);
				titreTexte.setText(titre.getTexte());
				try {
					Font font = Font.loadFont(
							new FileInputStream(new File("././Ressources/Polices/" + titre.getPoliceTexte() + ".ttf")),
							Integer.parseInt(titre.getTaille()));
					titreTexte.setFont(font);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				titreTexte.setStyle("-fx-text-fill:" + titre.getCouleurTexte().replace("0x", "#") + ";");
			} else {
				titreTexte.setVisible(false);

				imageTitre.setImage(ViewParametresPageExplicationController.chargerImage(titre.getLienImage()));
			}

			FondEcran fondEcran = accXML.getFond();

			if (fondEcran.getImageVsCouleur().contains("Image")) {
				anchor.setStyle("-fx-background-color:transparent;");
				imageFDE.setImage(ViewParametresPageExplicationController.chargerImage(fondEcran.getLienImage()));
			} else {
				imageFDE.setVisible(false);
				anchor.setStyle("-fx-background-color:" + fondEcran.getCouleur().replace("0x", "#") + ";");
			}

		} else {
			System.out.println("xml \"" + xml + "\" doesn't exist");
			imageTitre.setVisible(false);
			imageFDE.setVisible(false);

			anchor.setStyle("-fx-background-color:transparent;");
			titreTexte.setText("Nom du serious game");
			titreTexte.setFont(Font.font("Courier New Bold", 60));
			titreTexte.setStyle("-fx-text-fill:gray;");
		}
	}

	/**
	 * Modifie le chemin relatif vers le fichier xml d'accueil.
	 * 
	 * @param xml
	 */
	public void setXML(String xml) {
		this.xml = xml;
	}

	/**
	 * 
	 * @param soloBloc
	 * @param cmptChronologie
	 * @param xmlChronologie
	 */
	public void setChronologie(boolean soloBloc, int cmptChronologie, String xmlChronologie) {
		this.soloBloc = soloBloc;
		this.cmptChronologie = cmptChronologie;
		this.xmlChronologie = xmlChronologie;
	}

	/**
	 * 
	 * @param event  Listener d'action sur un bouton.
	 * @throws IOException
	 */
	@FXML
	protected void ClickButtonJouer(ActionEvent event) throws IOException {
		if (soloBloc) {
			Stage stage = (Stage) buttonJouer.getScene().getWindow();
			stage.close();
		} else {
			Stage stage = (Stage) buttonJouer.getScene().getWindow();
			Node node = JFxUtils.loadNextBloc(cmptChronologie, xmlChronologie, son, score);
			if (node != null) {
				stage.setScene(new Scene((Parent) node, 850, 650));
			} else {

				Platform.setImplicitExit(false);
				stage.close();

				ArrayList<String> names = new ArrayList<String>();
				ArrayList<String> path = new ArrayList<String>();
				try {
					SAXParserFactory factory = SAXParserFactory.newInstance();
					SAXParser parser = factory.newSAXParser();
					parser.parse(xmlChronologie, new DefaultHandler() {
						public void startDocument() throws SAXException {
						}

						public void endDocument() throws SAXException {
						}

						public void startElement(String uri, String localName, String qName, Attributes attributes)
								throws SAXException {
							names.add(qName);
							path.add(attributes.getValue("pathXML"));
							// System.out.println("startElement: " + qName + " attributs :
							// "+attributes.getValue("pathXML"));
						}

						public void endElement(String uri, String localName, String qName) throws SAXException {
						}
					});
				} catch (Exception e) {
					System.err.println(e);
					System.exit(1);
				}
				if (cmptChronologie < names.size()) {
					switch (names.get(cmptChronologie)) {
					case "Intrus":
						JFxUtils.loadOddWordOutGame(path.get(cmptChronologie), false, cmptChronologie + 1,
								xmlChronologie, son, score);
						break;
					case "Puzzle":
						JFxUtils.loadPuzzleGame(path.get(cmptChronologie), false, cmptChronologie + 1, xmlChronologie,
								son, score);
						break;
					}
				}
			}
		}
	}

	/**
	 * Fonction de lecture des éléments sonores avec indication du volume.
	 * 
	 * @param son
	 *            Chemin vers le fichier audio.
	 * @param volumeReduced
	 *            Volume de lecture de l'audio (décimal).
	 */
	public static void jouerAudio(String son, float volumeReduced) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(son));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(volumeReduced);
			clip.start();
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
}
