package controleurs;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javafx.animation.Animation;
import javafx.animation.Transition;
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
import javafx.stage.Stage;
import javafx.util.Duration;
import modeles.Dialogue;
import modeles.PNJ;

/**
 * Controleur de l'interface joueur d'un dialogue avec un PNJ.
 * 
 * @author YESUFU Sarah
 * @version 1.0
 */
public class ViewPNJController implements Initializable {

	@FXML
	private Button boutonNext;

	@FXML
	private Button boutonPrev;

	@FXML
	private AnchorPane anchor;

	@FXML
	private Label texte;

	@FXML
	private ImageView personnage;

	@FXML
	private ImageView anchorImage;

	private String xml = "";
	private boolean soloBloc = true;
	private int cmptChronologie = 0;
	private String xmlChronologie = "";
	private boolean son;
	private int score;
	private PNJ pnj = new PNJ();
	private int cmpt;
	private Dialogue dial;
	private Animation animation;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

	/**
	 * Fonction d'initialisation des données paramétrées d'un dialogue dans le bloc.
	 * Vérifie l'existence d'un fichier xml correspondant puis recupère les
	 * informations paramétrées.
	 * 
	 * @param pnj
	 *            Objet PNJ
	 * @param cmpt
	 *            Compteur définissant le rang de la question présentée.
	 * @param son
	 *            Paramètre d'activation ou non des éléments sonores.
	 * @param score
	 *            Score cumulé par le joueur lors des événements antérieurs.
	 * 
	 * @see PNJ.
	 */
	public void initData(PNJ pnj, int cmpt, boolean son, int score) {
		this.cmpt = cmpt;
		this.pnj = pnj;
		this.son = son;
		this.score = score;

		File f = new File(xml);
		if (f.exists()) {
			this.pnj = pnj.convertirXMLToJava(xml);

			dial = this.pnj.getListeDialogues().get(cmpt);

			if (cmpt == 0) {
				boutonPrev.setVisible(false);
			} else {
				boutonPrev.setVisible(true);
			}

			remplissageContentDialogue(dial);

		} else {
			System.out.println("xml : " + xml);
		}

	}

	/**
	 * Affiche le fond d'écran, le personnage et le texte de dialogue animé.
	 * 
	 * @param dial2
	 *            Objet Dialogue
	 * @see Dialogue.
	 */
	private void remplissageContentDialogue(Dialogue dial2) {
		if (!dial2.getImageFondEcran().isEmpty()) {
			anchorImage.setImage(ViewParametresPageExplicationController.chargerImage(dial2.getImageFondEcran()));
		}

		if (!dial2.getCouleurFondEcran().isEmpty()) {
			anchor.setStyle("-fx-background-color:" + dial2.getCouleurFondEcran().replace("0x", "#") + ";");
		}

		personnage.setImage(ViewParametresPageExplicationController.chargerImage(dial2.getImagePersonnage()));

		AnimateText(texte, dial2.getIntitule());
	}

	/**
	 * Fonction animant l'apparition du texte de dialogue.
	 * 
	 * @param lbl
	 *            Objet de type Label (voir JavaFX). C'est le conteneur du dialogue.
	 * @param descImp
	 *            Texte de dialogue.
	 */
	public void AnimateText(Label lbl, String descImp) {
		String content = descImp;
		animation = new Transition() {
			{
				setCycleDuration(Duration.millis(content.length() * 20));
			}

			protected void interpolate(double frac) {
				final int length = content.length();
				final int n = Math.round(length * (float) frac);
				lbl.setText(content.substring(0, n));
			}
		};
		animation.play();
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
	 * Modifie le chemin relatif vers le fichier xml de quiz.
	 * 
	 * @param xml
	 */
	public void setXML(String xml) {
		this.xml = xml;
	}

	/**
	 * Fonction permettant de passer au dialogue suivant ou à l'évènement suivant.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 * @throws IOException
	 */
	@FXML
	private void ClickButtonNext(ActionEvent event) throws IOException {
		if (pnj.getListeDialogues().size() == cmpt + 1) {
			if (soloBloc) {
				Stage stage = (Stage) boutonNext.getScene().getWindow();
				stage.close();
			} else {
				Stage stage = (Stage) boutonNext.getScene().getWindow();
				Node node = JFxUtils.loadNextBloc(cmptChronologie, xmlChronologie, son, score);

				if (node != null) {
					stage.setScene(new Scene((Parent) node, 850, 650));
				} else {

					Platform.setImplicitExit(false);
					System.out.println("Fermeture du bloc JAVAFX précédent");
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
							JFxUtils.loadPuzzleGame(path.get(cmptChronologie), false, cmptChronologie + 1,
									xmlChronologie, son, score);
							break;
						}
					}
				}

			}
		} else {
			Stage stage = (Stage) boutonNext.getScene().getWindow();
			stage.setScene(new Scene((Parent) JFxUtils.loadPNJFxml(pnj, cmpt + 1, "/vues/ViewPNJ.fxml", xml, soloBloc,
					cmptChronologie, xmlChronologie, son, score), 850, 650));
			stage.show();
		}
	}

	/**
	 * Fonction d'affichage du dialogue précédent.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 * @throws IOException
	 */
	@FXML
	private void ClickButtonPrev(ActionEvent event) throws IOException {
		Stage stage = (Stage) boutonPrev.getScene().getWindow();
		stage.setScene(new Scene((Parent) JFxUtils.loadPNJFxml(pnj, cmpt - 1, "/vues/ViewPNJ.fxml", xml, soloBloc,
				cmptChronologie, xmlChronologie, son, score), 850, 650));
		stage.show();
	}
}
