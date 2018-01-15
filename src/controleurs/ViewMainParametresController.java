package controleurs;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class ViewMainParametresController implements Initializable{
	
	@FXML
	private ToggleGroup blocs;
	@FXML
	private ToggleButton TBT_BlcQuiz,TBT_BlcAccueil,TBT_BlcIntrus,TBT_BlcPNJ,TBT_BlcScr,TBT_BlcExpl;
	@FXML
	private Button BT_AjtBlc; 
	@FXML
	private ListView LV_BlcList;
	private ArrayList<String> listTypeBlocs=new ArrayList<String>();
	private ArrayList<Integer> listCmptBloc=new ArrayList<Integer>();
	@FXML
	private AnchorPane AP_ConfBlc;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Initialisation du contenu des listes servant a faire compteur
		listTypeBlocs.add("Accueil");listTypeBlocs.add("Jeu Cherche l'Intrus");listTypeBlocs.add("Score/Resultat Jeu");
		listTypeBlocs.add("Jeu Quiz");listTypeBlocs.add("Dialogue PNJ");listTypeBlocs.add("Page d'explication");
		for (int i=0;i<6;i++)
			listCmptBloc.add(0);
	}
	
	/**
	 * Fonction ajoutant le bloc selectionne dans la liste a gauche
	 * @param event
	 */
	@FXML
	private void ClickBT_AjtBlc(ActionEvent event) {
		if (blocs.getSelectedToggle()!=null){
			ToggleButton TBT_test=(ToggleButton) (blocs.getSelectedToggle());
			int cmpt=0;
			for (int i=0;i< listTypeBlocs.size();i++){
				if(listTypeBlocs.get(i).equals(TBT_test.getText())){
					cmpt=i;
					break;
				}
			}
			listCmptBloc.set(cmpt,listCmptBloc.get(cmpt)+1);
			LV_BlcList.getItems().add(TBT_test.getText()+ " " + listCmptBloc.get(cmpt));
		}
	}
	
	/**
	 * Fonction remontant le bloc selectionne dans la liste d'un cran
	 * @param event
	 */
	@FXML
	private void ClickBT_UpBlc(ActionEvent event) {
		int index=LV_BlcList.getSelectionModel().getSelectedIndex();
		if (index>0){
			Object buff=LV_BlcList.getItems().get(index-1);
			LV_BlcList.getItems().set(index-1, LV_BlcList.getItems().get(index));
			LV_BlcList.getItems().set(index, buff);
			LV_BlcList.getSelectionModel().select(index-1);
		}
	}
	
	/**
	 * Fonction descendant le bloc selectionne dans la liste d'un cran
	 * @param event
	 */
	@FXML
	private void ClickBT_DownBlc(ActionEvent event) {
		int index=LV_BlcList.getSelectionModel().getSelectedIndex();
		if (index<LV_BlcList.getItems().size()){
			Object buff=LV_BlcList.getItems().get(index+1);
			LV_BlcList.getItems().set(index+1, LV_BlcList.getItems().get(index));
			LV_BlcList.getItems().set(index, buff);
			LV_BlcList.getSelectionModel().select(index+1);
		}
	}
	
	/**
	 * Fonction supprimant le bloc selectionne
	 * @param event
	 */
	@FXML
	private void ClickBT_SuprBlc(ActionEvent event) {
		LV_BlcList.getSelectionModel().getSelectedItem();
		LV_BlcList.getItems().remove(LV_BlcList.getSelectionModel().getSelectedItem());
	}
	
	@FXML
	private void ClickBT_ConfBlc(ActionEvent event) throws IOException {
		AnchorPane newPane = FXMLLoader.load(getClass().getResource("../vues/ViewParametresQuiz.fxml"));
		AP_ConfBlc.getChildren().setAll(newPane);
		
	}
	
	@FXML
	private void ClickBT_SaveGame(ActionEvent event) throws ParserConfigurationException, TransformerException {
		//Creation du fichier XML et des differentes instances
		DocumentBuilderFactory XML_Fabrique_Constructeur = DocumentBuilderFactory.newInstance();
		DocumentBuilder XML_Constructeur = XML_Fabrique_Constructeur.newDocumentBuilder();
 
		Document XML_Document = XML_Constructeur.newDocument();
		Element documentation = XML_Document.createElement("Documentation");
		XML_Document.appendChild(documentation);

		Element site = XML_Document.createElement("Site");
		documentation.appendChild(site);
		Attr attribut1 = XML_Document.createAttribute("Wiki");
		attribut1.setValue("Wikibooks");
		site.setAttributeNode(attribut1);
		
		Element livre = XML_Document.createElement("Livre");
		site.appendChild(livre);
		Attr attribut2 = XML_Document.createAttribute("Wikilivre");
		attribut2.setValue("Java");
		livre.setAttributeNode(attribut2);

		Element page1 = XML_Document.createElement("Page1");
		page1.appendChild(XML_Document.createTextNode("Introduction"));
		livre.appendChild(page1);

		Element page2 = XML_Document.createElement("Page2");
		page2.appendChild(XML_Document.createTextNode("Bases du langage"));
		livre.appendChild(page2);		
 
		Element example = XML_Document.createElement("Exemple");
		example.appendChild(XML_Document.createTextNode("XML"));
		livre.appendChild(example);

		//Enregistrement du fichier xml
		TransformerFactory XML_Fabrique_Transformeur = TransformerFactory.newInstance();
		Transformer XML_Transformeur = XML_Fabrique_Transformeur.newTransformer();
		DOMSource source = new DOMSource(XML_Document);
		StreamResult resultat = new StreamResult(new File("XML_résultat.xml"));
		XML_Transformeur.transform(source, resultat); 
		System.out.println("Le fichier XML a été généré !");
		
	}

}
