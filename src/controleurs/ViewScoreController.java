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

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ViewScoreController implements Initializable{
	
	private String xml="";
	private boolean soloBloc=true;
	private int cmptChronologie=0;
	private String xmlChronologie="";
	private boolean son;
	private int score;
	
	@FXML
	Label LB_Msg,LB_NbrPoints;
	@FXML
	ImageView IV_FontPict;
	
	
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
		   parser.parse("FichiersDeConfig/score.xml", new DefaultHandler() {
		    public void startDocument() throws SAXException {}
		    public void endDocument() throws SAXException {}
		    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		    	if(qName.equals("ScoreFaible")||qName.equals("ScoreSup"))
		    		bornes.add(Integer.parseInt(attributes.getValue("borne")));
		    	if(qName.startsWith("Msg"))
		    		msgs.add(attributes.getValue("content"));
		    	if(qName.startsWith("Pict"))
		    		picts.add(attributes.getValue("content"));
		    		
		    	System.out.println("startElement: " + qName + " attributs : "+attributes.getValue("content")); 
		    	}
		    public void endElement(String uri, String localName, String qName) throws SAXException {}
		   });  
		  } catch (Exception e) { System.err.println(e); System.exit(1); }
		
		System.out.println(picts.toString());
		
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

	
	
	
}
