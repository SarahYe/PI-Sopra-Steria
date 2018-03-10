package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import controleurs.JFxUtils;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class MainChronologie extends Application  {
	
	int cmptChronologie=1;
	String xmlChronologie="Games/test1/chronologie_test1.xml";
	boolean son=true;
	int score=1000;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void initData(int cmptChronologie, String xmlChronologie, boolean son, int score) {
		this.cmptChronologie=cmptChronologie;
		this.xmlChronologie=xmlChronologie;
		this.son=son;
		this.score=score;	
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		List<String> choices = new ArrayList<>();
		File dir = new File("Games");
		File[] files = dir.listFiles();
		for(File dossier:files)
			choices.add(dossier.getName());
		
		ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
		dialog.setTitle("imaGin'S");
		dialog.setHeaderText("Choix du serious game");
		dialog.setContentText("Choisir un serious game");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    xmlChronologie="Games/"+result.get()+"/chronologie_"+result.get()+".xml";
		    System.out.println(xmlChronologie);
		    
		    Node node=JFxUtils.loadNextBloc(cmptChronologie, xmlChronologie,son,score);
			if(node!=null){
				stage.setScene(new Scene((Parent) node, 850, 650));
				stage.setTitle("imaGin'S");
				stage.show();
				stage.sizeToScene();
			} else {
				System.out.println("node null");
			}
		}
		
		
	}
}