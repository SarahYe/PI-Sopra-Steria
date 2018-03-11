package controleurs;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ViewScoreController implements Initializable{
	
	private String xml="";
	private boolean soloBloc=true;
	private int cmptChronologie=0;
	private String xmlChronologie="";
	private boolean son;
	private int score;
	
	@FXML
	Label LB_Title,LB_Msg,LB_NbrPoints;
	@FXML
	ImageView IV_FontPict;
	@FXML
	Button BT_Suivant;
	@FXML
	AnchorPane AP_Score;
	
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
   
	}
	
	public void initData() {
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
		
		LB_NbrPoints.setText(score + " points");
		if(bornes.get(0)>score){
			LB_Msg.setText(msgs.get(0));
			File file = new File(picts.get(0));
	        Image image = new Image(file.toURI().toString());
	        IV_FontPict.setImage(image);
		} else if(bornes.get(1)>score){
			LB_Msg.setText(msgs.get(1));
			File file = new File(picts.get(1));
	        Image image = new Image(file.toURI().toString());
	        IV_FontPict.setImage(image);
		} else {
			LB_Msg.setText(msgs.get(2));
			File file = new File(picts.get(2));
	        Image image = new Image(file.toURI().toString());
	        IV_FontPict.setImage(image);
		}
		centerImage(IV_FontPict);
	}
	
	public void setXML(String xml) {
		this.xml=xml;
	}
	
	public void setChronologie(boolean soloBloc, int cmptChronologie, String xmlChronologie){
		this.soloBloc=soloBloc;
		this.cmptChronologie=cmptChronologie;
		this.xmlChronologie=xmlChronologie;
	}
	
	public void setSon(boolean son){
		this.son=son;
	}
	
	public void setScore(int score){
		this.score=score;
	}
	
	@FXML
	private void ClickBT_Suivant(ActionEvent event){
		if(soloBloc){
			Stage stage = (Stage) BT_Suivant.getScene().getWindow();
			stage.close();
		} else {
			Stage stage = (Stage) BT_Suivant.getScene().getWindow();
			Node node=JFxUtils.loadNextBloc(cmptChronologie, xmlChronologie, son);
			if (node!=null){
				stage.setScene(new Scene((Parent) node, 850, 650));
			} else {
				stage.close();
			}
		}
	}
	
	public static void centerImage(ImageView imageView) {
        Image img = imageView.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = imageView.getFitWidth() / img.getWidth();
            double ratioY = imageView.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;
            
            //System.out.println((imageView.getFitWidth() - w) / 2);
            //System.out.println((imageView.getFitHeight() - h) / 2);
            

            imageView.setX((imageView.getFitWidth() - w) / 2);
            imageView.setY((imageView.getFitHeight() - h) / 2);

        }
    }
	
	public void previsualisation(String title,String img,String message,String police, String taille, Color color){
		LB_Title.setText(title);
		LB_Msg.setText(message);
		Font font=new Font(police, Double.parseDouble(taille));
		LB_Msg.setTextFill(color);
		LB_Msg.setFont(font);
		LB_NbrPoints.setTextFill(color);
		LB_NbrPoints.setFont(font);
		
		LB_Title.setTextFill(color);
		LB_NbrPoints.setFont(new Font(police, Double.parseDouble(taille)+5));
		
		File file = new File(img);
        Image image = new Image(file.toURI().toString());
        IV_FontPict.setImage(image);
        centerImage(IV_FontPict);
	}

	
	
	
}
