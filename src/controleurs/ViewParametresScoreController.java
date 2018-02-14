package controleurs;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javax.swing.text.AbstractDocument.LeafElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

public class ViewParametresScoreController implements Initializable{
	
	private String xml;
	
	@FXML
	TextField TF_title;
	@FXML
	TextField TF_InfScr,TF_InfMsg,TF_InfPict;
	@FXML
	TextField TF_MedMsg,TF_MedPict;
	@FXML
	TextField TF_SupScr,TF_SupMsg,TF_SupPict;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void setXML(String xml) {
		this.xml=xml;
	}
	
	public void initData(){
		
	}
	
	public void ClickBT_ajoutPictInf(ActionEvent event){
		TF_InfPict.setText(getParcourirImage());
	}
	
	@FXML
	public void ClickBT_ajoutPictMed(ActionEvent event){
		TF_MedPict.setText(getParcourirImage());
	}
	
	@FXML
	public void ClickBT_ajoutPictSup(ActionEvent event){
		TF_SupPict.setText(getParcourirImage());
	}
	
	public String getParcourirImage(){
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		Path cheminAbsoluActuel = Paths.get("").toAbsolutePath();
		Path cheminAbsoluImage = Paths.get(file.getAbsolutePath());
		//return cheminAbsoluActuel.relativize(cheminAbsoluImage).toString();
		return cheminAbsoluImage.toString();
	}
	
	@FXML
	public void ClickBT_Save(ActionEvent event) throws ParserConfigurationException, TransformerException{
		DocumentBuilderFactory XML_Fabrique_Constructeur = DocumentBuilderFactory.newInstance();
		DocumentBuilder XML_Constructeur = XML_Fabrique_Constructeur.newDocumentBuilder();
 
		Document XML_Document = XML_Constructeur.newDocument();
		Element scorePage = XML_Document.createElement("ScorePage");
		XML_Document.appendChild(scorePage);
		
		//titre
		Element titre = XML_Document.createElement("Titre");
		scorePage.appendChild(titre);
		Attr attributContent = XML_Document.createAttribute("content");
		attributContent.setValue(TF_title.getText());
		titre.setAttributeNode(attributContent);
		
		//Partie Inf
		Element inf = XML_Document.createElement("ScoreFaible");
		scorePage.appendChild(inf);
		inf.setAttribute("borne",TF_InfScr.getText());
		Element msgInf = XML_Document.createElement("MsgInf");
		inf.appendChild(msgInf);
		msgInf.setAttribute("content", TF_InfMsg.getText());
		Element pictInf =XML_Document.createElement("PictInf");
		inf.appendChild(pictInf);
		pictInf.setAttribute("content", TF_InfPict.getText());
		
		//Partie Med
		Element med = XML_Document.createElement("ScoreMoyen");
		scorePage.appendChild(med);
		Element msgMed = XML_Document.createElement("MsgMed");
		med.appendChild(msgMed);
		msgMed.setAttribute("content", TF_MedMsg.getText());
		Element pictMed =XML_Document.createElement("PictMed");
		med.appendChild(pictMed);
		pictMed.setAttribute("content", TF_MedPict.getText());
		
		//Partie Sup
		Element sup = XML_Document.createElement("ScoreSup");
		scorePage.appendChild(sup);
		sup.setAttribute("borne",TF_SupScr.getText());
		Element msgSup = XML_Document.createElement("MsgSup");
		sup.appendChild(msgSup);
		msgSup.setAttribute("content", TF_SupMsg.getText());
		Element pictSup =XML_Document.createElement("PictSup");
		sup.appendChild(pictSup);
		pictSup.setAttribute("content", TF_SupPict.getText());
		
		//enregistrement du fichier xml
		TransformerFactory XML_Fabrique_Transformeur = TransformerFactory.newInstance();
		Transformer XML_Transformeur = XML_Fabrique_Transformeur.newTransformer();
		DOMSource source = new DOMSource(XML_Document);
		StreamResult resultat;
		
		resultat = new StreamResult(new File(xml));
		XML_Transformeur.transform(source, resultat); 
		System.out.println("Le fichier XML a été généré !");
		
		Alert alert = new Alert(AlertType.INFORMATION, "Le fichier xml a bien été enregistré\nIl se situe au chemin "+xml, ButtonType.OK);
		alert.setHeaderText("Information concernant l'enregistrement");
		alert.showAndWait();
		
		
	}

}
