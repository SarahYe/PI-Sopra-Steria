package controleurs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import modeles.Fouille;
import modeles.Instruction;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * Controlleur de l'interface joueur du jeu de fouille
 *
 */
public class ViewFouilleController implements Initializable {

	private String xml = "";
	private static boolean soloBloc = true;
	private static int cmptChronologie = 0;
	private static String xmlChronologie = "";
	private static boolean son;
	private static int score;

	private static Fouille fouille;
	private static ArrayList<String> intitules = new ArrayList<String>();
	private static int cmpt = 0;
	private int inventoryX = 5, inventoryY = 0;
	private static int printScore = 0;

	@FXML
	AnchorPane AP_Game, AP_Inventory;
	@FXML
	ImageView imageFond, IV_IconeSon;
	@FXML
	Label LB_Instruction, LB_Score;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (son)
			sonOn();
		else
			sonOff();

	}

	/**
	 * Fonction permettant d'initaliser le jeu de fouille
	 */
	public void initData() {

		try {
			JAXBContext jc = JAXBContext.newInstance(modeles.Fouille.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			// unmarshaller.setValidating(true);
			fouille = (Fouille) unmarshaller.unmarshal(new File(xml));
		} catch (Exception e) {
			System.out.println("erreur dans le chargement du fichier " + xml);
		}

		File file = new File(fouille.getFondEcran());
		Image image = new Image(file.toURI().toString());
		imageFond.setImage(image);
		centerImage(imageFond);

		for (Instruction instruction : fouille.getListeInstructions()) {
			intitules.add(instruction.getIntitule());

			file = new File(instruction.getImageObjet());
			image = new Image(file.toURI().toString());
			ImageView element = new ImageView(image);
			element.setX(instruction.getPosX() + imageFond.getX());
			element.setY(instruction.getPosY() + imageFond.getY());
			if (element.getImage().getHeight() > inventoryY)
				inventoryY = (int) element.getImage().getHeight() + 10;
			AP_Game.getChildren().add(element);
			element.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent t) {
					if (LB_Instruction.getText().equals(instruction.getIntitule())) {
						AP_Game.getChildren().remove(element);

						element.setX(inventoryX);
						
						element.setY(0);
						
						element.setFitHeight(100);
						element.setPreserveRatio(true);
						element.setFitWidth(100);
						
						inventoryX = (int) (inventoryX + 100);
						
						AP_Inventory.getChildren().add(element);
						majScorePlus(LB_Score, fouille.getListeInstructions().get(cmpt - 1).getType());
						majInstruction(LB_Instruction);
					} else {
						if (!AP_Inventory.getChildren().contains(element))
							majScoreMoins(LB_Score, fouille.getListeInstructions().get(cmpt - 1).getType());
					}

				}
			});
			element.setOnMouseEntered(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent t) {
					DropShadow ds = new DropShadow(20, Color.RED);
					element.setEffect(ds);

				}

			});
			element.setOnMouseExited(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent t) {
					element.setEffect(null);
				}

			});
		}

		majInstruction(LB_Instruction);
	}

	/**
	 * Definie le chemin relatif du fichier xml du bloc fouille
	 * @param xml 
	 */
	public void setXML(String xml) {
		this.xml = xml;
	}
	
	/**
	 * Definie la chronologie ainsi que son avancement
	 * @param soloBloc boolean indiquant si le bloc est seul ou fait partie d'une chronologie
	 * @param cmptChronologie compteur de l'avancement dans la chronologie
	 * @param xmlChronologie chronologie xml sur laquelle le jeu se base
	 */
	public void setChronologie(boolean soloBloc, int cmptChronologie, String xmlChronologie) {
		this.soloBloc = soloBloc;
		this.cmptChronologie = cmptChronologie;
		this.xmlChronologie = xmlChronologie;
	}
	
	/**
	 * Definie si le son est actif ou non
	 * @param son
	 */
	public void setSon(boolean son) {
		this.son = son;
	}

	/**
	 * Defini le score du joueur
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
		LB_Score.setText("Score : " + Integer.toString(score));
	}

	/**
	 * Fonction permettant de centrer une imageView
	 * @param imageView imageView à centrer
	 */
	public static void centerImage(ImageView imageView) {
		Image img = imageView.getImage();
		if (img != null) {
			double w = 0;
			double h = 0;

			double ratioX = imageView.getFitWidth() / img.getWidth();
			double ratioY = imageView.getFitHeight() / img.getHeight();

			double reducCoeff = 0;
			if (ratioX >= ratioY) {
				reducCoeff = ratioY;
			} else {
				reducCoeff = ratioX;
			}

			w = img.getWidth() * reducCoeff;
			h = img.getHeight() * reducCoeff;

			// System.out.println((imageView.getFitWidth() - w) / 2);
			// System.out.println((imageView.getFitHeight() - h) / 2);

			imageView.setX((imageView.getFitWidth() - w) / 2);
			imageView.setY((imageView.getFitHeight() - h) / 2);

		}
	}

	/**
	 * Fonction permettant de mettre à jour le score suite à une bonne réponse
	 * @param LBscore label du score à mettre à jour
	 * @param typeObjet rang de l'objet selectionné
	 */
	private static void majScorePlus(Label LBscore, String typeObjet) {
		if (son)
			jouerAudio("Ressources/Sons/succes.wav");
		if (typeObjet.equals("BRONZE"))
			printScore = (int) (printScore + fouille.getBronze());
		else if (typeObjet.equals("SILVER"))
			printScore = (int) (printScore + fouille.getSilver());
		else
			printScore = (int) (printScore + fouille.getGold());
		LBscore.setText("Score : " + Integer.toString(printScore));
	}

	/**
	 * Fonction permettant de mettre à jour le score suite à une mauvaise réponse
	 * @param LBscore label du score à mettre à jour
	 * @param typeObjet rang de l'objet selectionné
	 */
	private static void majScoreMoins(Label LBscore, String typeObjet) {
		if (son)
			jouerAudio("Ressources/Sons/echec1.wav");
		if (typeObjet.equals("BRONZE"))
			printScore = (int) (printScore - (fouille.getBronze() / 2));
		else if (typeObjet.equals("SILVER"))
			printScore = (int) (printScore - (fouille.getSilver() / 2));
		else
			printScore = (int) (printScore - (fouille.getGold() / 2));
		LBscore.setText("Score : " + Integer.toString(printScore));
	}

	/**
	 * Fonction permettant de mettre à jour l'instruction suite à une bonne réponse (ou passer au bloc suivant si fin du bloc)
	 * @param label label affichant les instructions
	 */
	public static void majInstruction(Label label) {
		if (cmpt < intitules.size()) {
			label.setText(intitules.get(cmpt));
			cmpt++;
		} else {
			Alert alert = new Alert(AlertType.INFORMATION, "Le jeu fouille est fini.\n Votre Score : " + printScore,
					ButtonType.OK);
			alert.showAndWait();

			if (soloBloc) {
				Stage stage = (Stage) label.getScene().getWindow();
				stage.close();
			} else {
				Stage stage = (Stage) label.getScene().getWindow();
				Node node = JFxUtils.loadNextBloc(cmptChronologie, xmlChronologie, son, printScore + score);
				if (node != null) {
					stage.setScene(new Scene((Parent) node, 850, 650));
				} else {

					Platform.setImplicitExit(false);
					System.out.println("Fermeture du bloc JAVAFX prï¿½cï¿½dent");
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
		}
	}

	/**
	 * Fonction permettant de joueur un son audio
	 * @param son chemin du fichier audio à lire
	 */
	public static void jouerAudio(String son) {
		InputStream in;
		try {
			in = new FileInputStream(son);
			AudioStream as = new AudioStream(in);
			AudioPlayer.player.start(as);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fonction permettant d'activer ou désactiver le son
	 * @param t
	 */
	@FXML
	public void swapSon(MouseEvent t) {
		son = !son;
		if (son)
			sonOn();
		else
			sonOff();
	}

	/**
	 * fonction activant le son
	 */
	private void sonOn() {
		File file = new File("Ressources/Images/volume_on.png");
		Image image = new Image(file.toURI().toString());
		IV_IconeSon.setImage(image);
		IV_IconeSon.setFitHeight(60);
		IV_IconeSon.setFitWidth(70);
		centerImage(IV_IconeSon);
	}

	/**
	 * fonction désactivant le son
	 */
	private void sonOff() {
		File file = new File("Ressources/Images/volume_off.png");
		Image image = new Image(file.toURI().toString());
		IV_IconeSon.setImage(image);
		IV_IconeSon.setFitHeight(60);
		IV_IconeSon.setFitWidth(70);
		centerImage(IV_IconeSon);
	}

}
