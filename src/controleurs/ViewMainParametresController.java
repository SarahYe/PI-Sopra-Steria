package controleurs;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.newdawn.slick.SlickException;
import org.w3c.dom.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ViewMainParametresController implements Initializable {

	@FXML
	private ToggleGroup blocs;
	@FXML
	private ToggleButton TBT_BlcQuiz, TBT_BlcAccueil, TBT_BlcIntrus, TBT_BlcPNJ, TBT_BlcScr, TBT_BlcExpl, TBT_BlcPuzzle,
			TBT_BlcFouille;
	@FXML
	private Button BT_Save, BT_AjtBlc;
	@FXML
	private ListView LV_BlcList;
	private ArrayList<String> listTypeBlocs = new ArrayList<String>();
	private ArrayList<String> listFxmlBlocsParam = new ArrayList<String>();
	private ArrayList<String> listFxmlBlocs = new ArrayList<String>();
	private ArrayList<String> listXMLBlocs = new ArrayList<String>();
	private ArrayList<Integer> listCmptBloc = new ArrayList<Integer>();
	@FXML
	private AnchorPane AP_ConfBlc;
	@FXML
	private TextField TF_GameName;
	private String gamePath = "Games/temp/";
	private String xmlChronologie = "Games/temp/chronologie_temp.xml";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// BT_Save.getStylesheets().add(ViewMainParametresController.class.getResource("../../Ressources/CSS/button_hover.css").toExternalForm());
		// cré¥Œtion du dossier temporaire oï¿½ seront enregistrer tout les xml
		File dir = new File("Games/temp");
		dir.mkdirs();

		File[] files = dir.listFiles();
		for (File fichier : files)
			fichier.delete();

		System.out.println("ouverture page MainParametre");
		// Initialisation du contenu des listes servant a faire compteur
		listTypeBlocs.add("Accueil");
		listTypeBlocs.add("Jeu Cherche l'Intrus");
		listTypeBlocs.add("Score");
		listTypeBlocs.add("Jeu Quiz");
		listTypeBlocs.add("Dialogue PNJ");
		listTypeBlocs.add("Page d'explication");
		listTypeBlocs.add("Jeu Fouille");
		listTypeBlocs.add("Jeu Puzzle");
		listFxmlBlocsParam.add("/vues/AccueilParametres.fxml");
		listFxmlBlocsParam.add("/vues/ViewParametresOddWordOutGame.fxml");
		listFxmlBlocsParam.add("/vues/ViewScoreParametres.fxml");
		listFxmlBlocsParam.add("/vues/ViewParametresQuiz.fxml");
		listFxmlBlocsParam.add("/vues/ViewPNJParametres.fxml");
		listFxmlBlocsParam.add("/vues/PageExplicationParametrage.fxml");
		listFxmlBlocsParam.add("/vues/ViewFouilleParametres.fxml");
		listFxmlBlocsParam.add("/vues/ViewParametresPuzzle.fxml");
		listFxmlBlocs.add("/vues/Accueil.fxml");
		listFxmlBlocs.add(" ");
		listFxmlBlocs.add("/vues/ViewScore.fxml");
		listFxmlBlocs.add("/vues/QuizAccueil.fxml");
		listFxmlBlocs.add("/vues/ViewPNJ.fxml");
		listFxmlBlocs.add("/vues/PageExplication.fxml");
		listFxmlBlocs.add("/vues/ViewFouille.fxml");
		listFxmlBlocs.add(" ");
		listXMLBlocs.add("Accueil");
		listXMLBlocs.add("Intrus");
		listXMLBlocs.add("Score");
		listXMLBlocs.add("Quiz");
		listXMLBlocs.add("DiagPNJ");
		listXMLBlocs.add("PageExpl");
		listXMLBlocs.add("Fouille");
		listXMLBlocs.add("Puzzle");
		for (int i = 0; i < 8; i++)
			listCmptBloc.add(1);
	}

	/**
	 * Fonction ajoutant le bloc selectionne dans la liste a gauche
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	private void ClickBT_AjtBlc(ActionEvent event) throws IOException {
		if (blocs.getSelectedToggle() != null) {
			ToggleButton TBT_test = (ToggleButton) (blocs.getSelectedToggle());
			TBT_test.setSelected(false);
			int cmpt = 0;
			for (int i = 0; i < listTypeBlocs.size(); i++) {
				if (listTypeBlocs.get(i).equals(TBT_test.getText())) {
					cmpt = i;
					break;
				}
			}
			while (LV_BlcList.getItems().contains(TBT_test.getText() + " " + listCmptBloc.get(cmpt))) {
				listCmptBloc.set(cmpt, listCmptBloc.get(cmpt) + 1);
			}
			LV_BlcList.getItems().add(TBT_test.getText() + " " + listCmptBloc.get(cmpt));
			listCmptBloc.set(cmpt, 1);

			String fxml = listFxmlBlocsParam.get(cmpt);
			String xml = gamePath + LV_BlcList.getItems().get(LV_BlcList.getItems().size() - 1) + ".xml";
			loadConfBlc(fxml, xml, cmpt);

		}

	}

	/**
	 * Fonction remontant le bloc selectionne dans la liste d'un cran
	 * 
	 * @param event
	 */
	@FXML
	private void ClickBT_UpBlc(ActionEvent event) {
		int index = LV_BlcList.getSelectionModel().getSelectedIndex();
		if (index > 0) {
			Object buff = LV_BlcList.getItems().get(index - 1);
			LV_BlcList.getItems().set(index - 1, LV_BlcList.getItems().get(index));
			LV_BlcList.getItems().set(index, buff);
			LV_BlcList.getSelectionModel().select(index - 1);
		}
	}

	/**
	 * Fonction descendant le bloc selectionne dans la liste d'un cran
	 * 
	 * @param event
	 */
	@FXML
	private void ClickBT_DownBlc(ActionEvent event) {
		int index = LV_BlcList.getSelectionModel().getSelectedIndex();
		if (index < LV_BlcList.getItems().size()) {
			Object buff = LV_BlcList.getItems().get(index + 1);
			LV_BlcList.getItems().set(index + 1, LV_BlcList.getItems().get(index));
			LV_BlcList.getItems().set(index, buff);
			LV_BlcList.getSelectionModel().select(index + 1);
		}
	}

	/**
	 * Fonction supprimant le bloc selectionne
	 * 
	 * @param event
	 */
	@FXML
	private void ClickBT_SuprBlc(ActionEvent event) {
		// LV_BlcList.getSelectionModel().getSelectedItem();
		Alert alert = new Alert(AlertType.CONFIRMATION,
				"Supprimer le bloc \"" + LV_BlcList.getSelectionModel().getSelectedItem() + "\" ?", ButtonType.YES,
				ButtonType.NO);
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
			LV_BlcList.getItems().remove(LV_BlcList.getSelectionModel().getSelectedItem());
		}
	}

	@FXML
	private void ClickBT_ConfBlc(ActionEvent event) throws IOException {
		int index = LV_BlcList.getSelectionModel().getSelectedIndex();
		String fxml = null;
		int cmpt = -1;
		for (int i = 0; i < listTypeBlocs.size(); i++) {
			if (((String) LV_BlcList.getItems().get(index)).contains(listTypeBlocs.get(i))) {
				fxml = listFxmlBlocsParam.get(i);
				cmpt = i;
				break;
			}
		}
		String xml = gamePath + LV_BlcList.getSelectionModel().getSelectedItem() + ".xml";
		loadConfBlc(fxml, xml, cmpt);
	}

	private void loadConfBlc(String fxml, String xml, int cmpt) throws IOException {
		if (fxml.endsWith("fxml")) {
			System.out.println("cmpt=" + cmpt);
			Node newPane;

			switch (cmpt) {
			case 0:
				newPane = JFxUtils.loadAccueilParamFxml(fxml, xml);
				AP_ConfBlc.getChildren().setAll(newPane);
				break;
			case 1:
				newPane = JFxUtils.loadParamOddWordOutGame(fxml, xml);
				AP_ConfBlc.getChildren().setAll(newPane);
				break;
			case 2:
				newPane = JFxUtils.loadScoreParam(fxml, xml);
				AP_ConfBlc.getChildren().setAll(newPane);
				break;
			case 3:
				newPane = JFxUtils.loadParamQuiz(fxml, xml);
				AP_ConfBlc.getChildren().setAll(newPane);
				break;
			case 4:
				newPane = JFxUtils.loadPNJParamFxml(fxml, xml);
				AP_ConfBlc.getChildren().setAll(newPane);
				break;
			case 5:
				newPane = JFxUtils.loadExplicationParamFxml(fxml, xml);
				AP_ConfBlc.getChildren().setAll(newPane);
				break;
			case 6:
				newPane = JFxUtils.loadFouilleParamFxml(fxml, xml);
				AP_ConfBlc.getChildren().setAll(newPane);
				break;
			case 7:
				newPane = JFxUtils.loadPuzzleParamFxml(fxml, xml);
				AP_ConfBlc.getChildren().setAll(newPane);
				break;
			default:
			}
		}
	}

	@FXML
	private void ClickBT_SaveGame(ActionEvent event) throws ParserConfigurationException, TransformerException {
		// ré¦—upé§»ation du nom du jeu entrï¿½
		String nomJeu;
		if (!TF_GameName.getText().equals(""))
			nomJeu = TF_GameName.getText();
		else
			nomJeu = "DefaultName";

		// Creation du fichier XML et des differentes instances
		DocumentBuilderFactory XML_Fabrique_Constructeur = DocumentBuilderFactory.newInstance();
		DocumentBuilder XML_Constructeur = XML_Fabrique_Constructeur.newDocumentBuilder();

		Document XML_Document = XML_Constructeur.newDocument();
		Element seriousGame = XML_Document.createElement("SeriousGame");
		XML_Document.appendChild(seriousGame);

		for (int i = 0; i < LV_BlcList.getItems().size(); i++) {
			int cmpt = 0;
			for (int j = 0; j < listTypeBlocs.size(); j++) {
				if (((String) LV_BlcList.getItems().get(i)).contains(listTypeBlocs.get(j)))
					cmpt = j;
			}
			Element type = XML_Document.createElement(listXMLBlocs.get(cmpt));
			seriousGame.appendChild(type);
			Attr attribut1 = XML_Document.createAttribute("pathXML");
			String path = "Games/" + nomJeu;

			attribut1.setValue(path + "/" + LV_BlcList.getItems().get(i) + ".xml");
			type.setAttributeNode(attribut1);
		}

		// Enregistrement du fichier xml
		TransformerFactory XML_Fabrique_Transformeur = TransformerFactory.newInstance();
		Transformer XML_Transformeur = XML_Fabrique_Transformeur.newTransformer();
		DOMSource source = new DOMSource(XML_Document);
		StreamResult resultat;

		xmlChronologie = gamePath + "chronologie_" + nomJeu + ".xml";
		resultat = new StreamResult(new File(xmlChronologie));
		XML_Transformeur.transform(source, resultat);
		System.out.println("Le fichier XML a é¨�ï¿½ gé§­é§»ï¿½ !");

		Alert alert = new Alert(AlertType.INFORMATION,
				"Le jeu \"" + nomJeu + "\" a Ã©tÃ© enregistrÃ©.\n\nSon contenu a Ã©tÃ© enregistrÃ© au chemin : \""
						+ System.getProperty("user.dir") + "\\Games\\" + nomJeu
						+ "\"\n\nLe fichier contenant la chronologie se nomme \"chronologie_" + nomJeu + ".xml\"",
				ButtonType.OK);
		alert.setHeaderText("Information concernant l'enregistrement");
		alert.showAndWait();

		// Renommage du dossier du jeu
		File dir = new File("Games/temp");
		File newDir = new File(dir.getParent() + "/" + nomJeu);
		xmlChronologie = nomJeu + "/" + "chronologie_" + nomJeu + ".xml";

		Boolean rename = dir.renameTo(newDir);
		if (rename) {
			gamePath = "Games/" + nomJeu + "/";
			System.out.println(gamePath);
		}
		System.out.println("Rename dir ? :" + rename);

		Stage st = (Stage) BT_AjtBlc.getScene().getWindow();
		// st.close();
	}

	@FXML
	private void ClickBT_LoadGame(ActionEvent event)
			throws ParserConfigurationException, TransformerException, SlickException {

		File dir = new File("Games");
		DirectoryChooser dirChooser = new DirectoryChooser();
		dirChooser.setInitialDirectory(dir);

		// Show open file dialog
		File dirGame = dirChooser.showDialog(null);
		if (dirGame != null) {
			LV_BlcList.getItems().clear();
			Path cheminAbsoluActuel = Paths.get("").toAbsolutePath();
			Path cheminAbsoluImage = Paths.get(dirGame.getAbsolutePath());
			String cheminRelatif = cheminAbsoluActuel.relativize(cheminAbsoluImage).toString();
			TF_GameName.setText(dirGame.getName());
			gamePath = cheminRelatif.replace("\\", "/") + "/";

			ArrayList<String> names = new ArrayList<String>();
			ArrayList<String> path = new ArrayList<String>();
			try {
				SAXParserFactory factory = SAXParserFactory.newInstance();
				SAXParser parser = factory.newSAXParser();
				System.out.println(gamePath + "chronologie_" + dirGame.getName() + ".xml");
				xmlChronologie = gamePath + "chronologie_" + dirGame.getName() + ".xml";
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
			}

			for (String chemin : path) {
				if (chemin != null) {
					chemin = chemin.replace(gamePath, "");
					LV_BlcList.getItems().add(chemin.substring(0, chemin.length() - 4));
				}
			}
		}

	}

	@FXML
	private void ClickBT_TestGame(ActionEvent event)
			throws ParserConfigurationException, TransformerException, SlickException {
		if (LV_BlcList.getItems().size() > 0) {
			// Creation du fichier XML et des differentes instances
			DocumentBuilderFactory XML_Fabrique_Constructeur = DocumentBuilderFactory.newInstance();
			DocumentBuilder XML_Constructeur = XML_Fabrique_Constructeur.newDocumentBuilder();

			Document XML_Document = XML_Constructeur.newDocument();
			Element seriousGame = XML_Document.createElement("SeriousGame");
			XML_Document.appendChild(seriousGame);

			for (int i = 0; i < LV_BlcList.getItems().size(); i++) {
				int cmpt = 0;
				for (int j = 0; j < listTypeBlocs.size(); j++) {
					if (((String) LV_BlcList.getItems().get(i)).contains(listTypeBlocs.get(j)))
						cmpt = j;
				}
				Element type = XML_Document.createElement(listXMLBlocs.get(cmpt));
				seriousGame.appendChild(type);
				Attr attribut1 = XML_Document.createAttribute("pathXML");
				String path = "Games/temp";

				attribut1.setValue(path + "/" + LV_BlcList.getItems().get(i) + ".xml");
				type.setAttributeNode(attribut1);
			}

			// Enregistrement du fichier xml
			TransformerFactory XML_Fabrique_Transformeur = TransformerFactory.newInstance();
			Transformer XML_Transformeur = XML_Fabrique_Transformeur.newTransformer();
			DOMSource source = new DOMSource(XML_Document);
			StreamResult resultat;

			resultat = new StreamResult(new File("Games/temp/chronologie_temp.xml"));
			XML_Transformeur.transform(source, resultat);
			System.out.println("Le fichier XML a é¨�ï¿½ gé§­é§»ï¿½ !");

			Node newPane;
			System.out.println(xmlChronologie);
			newPane = JFxUtils.loadNextBloc(1, xmlChronologie, true, 0);
			// AP_ConfBlc.getChildren().setAll(newPane);
			Stage stage = new Stage();
			stage.setTitle("Test du jeu");
			stage.setScene(new Scene((Parent) newPane));
			stage.show();

		} else {
			Alert alert = new Alert(AlertType.INFORMATION, "Aucun bloc n'est pré§¸ent dans la chronologie",
					ButtonType.OK);
			alert.setHeaderText("Information concernant le test");
			// alert.showAndWait();
		}

	}

}
