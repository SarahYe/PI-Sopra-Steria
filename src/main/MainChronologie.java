package main;

import java.util.ArrayList;

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
import javafx.stage.Stage;

public class MainChronologie extends Application  {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		Node node=JFxUtils.loadNextBloc(1, "Games/test1/chronologie_test1.xml",false,100);
		if(node!=null){
			stage.setScene(new Scene((Parent) node, 850, 650));
			stage.setTitle("SériousSécurité");
			stage.show();
			stage.sizeToScene();
		} else {
			System.out.println("node null");
		}
		
		
		/*stage.setScene(new Scene((Parent) JFxUtils.loadNextBloc(1, "Games/test/chronologie_test.xml"), 850, 650));
		stage.setTitle("SériousSécurité");
		stage.show();
		stage.sizeToScene();*/
		
	}
}