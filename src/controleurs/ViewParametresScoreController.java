package controleurs;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.text.AbstractDocument.LeafElement;
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

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
	@FXML
	Label LB_Title,LB_Warning;
	@FXML
	TitledPane TP_Faible;
	@FXML
	Accordion AD_Title;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		AD_Title.setExpandedPane(TP_Faible);
		
		try {
			Font font = Font.loadFont(
					new FileInputStream(new File("././Ressources/Polices/PoetsenOne-Regular.ttf")), 20);
			LB_Title.setFont(font);
		} catch (FileNotFoundException e) {}
		
	}
	
	public void setXML(String xml) {
		this.xml=xml;
	}
	
	public void initData(){
		ArrayList<Integer> bornes=new ArrayList<Integer>();
		ArrayList<String> msgs=new ArrayList<String>();
		ArrayList<String> picts=new ArrayList<String>();
		
		
		try {
		   SAXParserFactory factory = SAXParserFactory.newInstance();
		   SAXParser parser = factory.newSAXParser();
		   parser.parse(xml, new DefaultHandler() {
		    public void startDocument() throws SAXException {}
		    public void endDocument() throws SAXException {}
		    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		    	if(qName.equals("ScoreFaible")||qName.equals("ScoreSup"))
		    		bornes.add(Integer.parseInt(attributes.getValue("borne")));
		    	if(qName.startsWith("Msg"))
		    		msgs.add(attributes.getValue("content"));
		    	if(qName.startsWith("Pict"))
		    		picts.add(attributes.getValue("content"));
		    		
		    	//System.out.println("startElement: " + qName + " attributs : "+attributes.getValue("content")); 
		    	}
		    public void endElement(String uri, String localName, String qName) throws SAXException {}
		   });  
		  } catch (Exception e) {}
		
		//System.out.println(picts.toString());
		if(msgs.size()>0){
			TF_InfMsg.setText(msgs.get(0));
			TF_InfPict.setText(picts.get(0));
			TF_InfScr.setText(Integer.toString(bornes.get(0)));
			
			TF_MedMsg.setText(msgs.get(1));
			TF_MedPict.setText(picts.get(1));
			
			TF_SupMsg.setText(msgs.get(2));
			TF_SupPict.setText(picts.get(2));
			TF_SupScr.setText(Integer.toString(bornes.get(1)));
		}
		
	}
	
	public void ClickBT_ajoutPictInf(ActionEvent event) throws IOException{
		TF_InfPict.setText(getParcourirImage());
	}
	
	@FXML
	public void ClickBT_ajoutPictMed(ActionEvent event) throws IOException{
		TF_MedPict.setText(getParcourirImage());
	}
	
	@FXML
	public void ClickBT_ajoutPictSup(ActionEvent event) throws IOException{
		TF_SupPict.setText(getParcourirImage());
	}
	
	public String getParcourirImage() throws IOException{
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		Path cheminAbsoluActuel = Paths.get("").toAbsolutePath();
		Path cheminAbsoluImage = Paths.get(file.getAbsolutePath());

		String[] s = cheminAbsoluImage.toString().split("/");
		String nomImage = s[s.length - 1];
		nomImage=nomImage.substring(nomImage.lastIndexOf("\\")+1);
		
		BufferedImage image = ImageIO.read(new File(cheminAbsoluImage.toString()));
		
		if (nomImage.contains(".png") || nomImage.contains(".PNG")) {
			ImageIO.write(image, "png", new File("././Ressources/Images/" + nomImage));
		} else {
			if (nomImage.contains(".jpg") || nomImage.contains(".JPG"))
				ImageIO.write(image, "jpg", new File("././Ressources/Images/" + nomImage));
			else 
				ImageIO.write(image, "jpeg", new File("././Ressources/Images/" + nomImage));
		}
		
		return "././Ressources/Images/" + nomImage;
		
		
	}
	
	@FXML
	public void ClickBT_Save(ActionEvent event) throws ParserConfigurationException, TransformerException{
		System.out.println(TF_InfMsg.getText());
		if(!TF_InfMsg.getText().equals("") && !TF_InfPict.getText().equals("") && !TF_InfScr.getText().equals("") && !TF_MedMsg.getText().equals("") && !TF_MedPict.getText().equals("") && 
				!TF_SupMsg.getText().equals("") && !TF_SupPict.getText().equals("") && !TF_SupScr.getText().equals("")){
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
			System.out.println("Le fichier XML a été généré!");
			
			Alert alert = new Alert(AlertType.INFORMATION, "Le fichier xml a bien été enregistré\nIl se situe au chemin "+xml, ButtonType.OK);
			alert.setHeaderText("Information concernant l'enregistrement");
			alert.showAndWait();
		} else {
			LB_Warning.setVisible(true);
		}
	}
	
	@FXML
	public void ClickBT_PrevInf(ActionEvent event) throws IOException{
		Stage stage = new Stage();
		stage.setTitle("Prévisualisation Score Faible");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/ViewScore.fxml"));
		stage.setScene(new Scene(loader.load()));
		ViewScoreController controller = loader.<ViewScoreController>getController();
		controller.previsualisation(TF_InfPict.getText(), TF_InfMsg.getText());
		
		stage.show();
		
		
	}
	
	@FXML
	public void ClickBT_PrevMed(ActionEvent event) throws IOException{
		Stage stage = new Stage();
		stage.setTitle("Prévisualisation Score Moyen");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/ViewScore.fxml"));
		stage.setScene(new Scene(loader.load()));
		ViewScoreController controller = loader.<ViewScoreController>getController();
		controller.previsualisation(TF_InfPict.getText(), TF_InfMsg.getText());
		
		stage.show();
	}
	
	@FXML
	public void ClickBT_PrevSup(ActionEvent event) throws IOException{
		Stage stage = new Stage();
		stage.setTitle("Prévisualisation Score Elevé");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/ViewScore.fxml"));
		stage.setScene(new Scene(loader.load()));
		ViewScoreController controller = loader.<ViewScoreController>getController();
		controller.previsualisation(TF_InfPict.getText(), TF_InfMsg.getText());
		
		stage.show();
	}
	
	

}
