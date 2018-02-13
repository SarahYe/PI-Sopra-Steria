package controleurs;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.newdawn.slick.SlickException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modeles.PNJ;
import modeles.Quiz;
import slickGames.MainOddWordOutGame;

public class JFxUtils {

	/**
	 * Fonction recuperant le noeud d'une vue provenant d'un fichier fxml pour son
	 * affichage
	 * 
	 * @param fxml
	 * @return
	 */
	public static Node loadFxml(String fxml) {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(main.MainQuiz.class.getResource(fxml).openStream());
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}
	
	public static Node loadQuizFxml(String fxml,String xml, boolean soloBloc, int cmptChronologie, String xmlChronologie) {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(main.MainQuiz.class.getResource(fxml).openStream());
			QuizAccueilController controller = loader.<QuizAccueilController>getController();
			controller.setChronologie(soloBloc,cmptChronologie,xmlChronologie);
			controller.setXML(xml);
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}
	
	public static Node loadQuestion(Quiz quiz, int cmpt, String fxml, String xml, boolean soloBloc, int cmptChronologie, String xmlChronologie,boolean son) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(main.MainQuiz.class.getResource(fxml).openStream());
			ViewQuestionController controller = loader.<ViewQuestionController>getController();
			controller.setChronologie(soloBloc,cmptChronologie,xmlChronologie);
			controller.setXML(xml);
			controller.initData(quiz, cmpt,son);
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
		
		
		/*FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/ViewQuestion.fxml"));
		stage.setScene(new Scene((Pane) loader.load()));

		ViewQuestionController controller = loader.<ViewQuestionController>getController();
		controller.setChronologie(soloBloc,cmptChronologie,xmlChronologie);
		controller.setXML(xml);
		controller.initData(quiz, cmpt);

		stage.show();
		return stage;*/
	}
	
	public static Node loadPNJFxml(PNJ pnj, int cmpt, String fxml,String xml, boolean soloBloc, int cmptChronologie, String xmlChronologie, boolean son) {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(main.MainQuiz.class.getResource(fxml).openStream());
			ViewPNJController controller = loader.<ViewPNJController>getController();
			controller.setChronologie(soloBloc,cmptChronologie,xmlChronologie);
			controller.setXML(xml);
			controller.initData(pnj, cmpt, son);
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}
	
	public static Node loadExplicationFxml(String fxml,String xml, boolean soloBloc, int cmptChronologie, String xmlChronologie, boolean son) {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(main.MainQuiz.class.getResource(fxml).openStream());
			ViewPageExplicationController controller = loader.<ViewPageExplicationController>getController();
			controller.setChronologie(soloBloc,cmptChronologie,xmlChronologie);
			controller.setXML(xml);
			controller.initData(son);
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}
	
	public static Node loadAccueilFxml(String fxml,String xml, boolean soloBloc, int cmptChronologie, String xmlChronologie, boolean son) {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(main.MainQuiz.class.getResource(fxml).openStream());
			ViewAccueilController controller = loader.<ViewAccueilController>getController();
			controller.setChronologie(soloBloc,cmptChronologie,xmlChronologie);
			controller.setXML(xml);
			controller.initData(xml,son);
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}

	
	public static Node loadExplicationParamFxml(String fxml,String xml) {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(main.MainQuiz.class.getResource(fxml).openStream());
			ViewParametresPageExplicationController controller = loader.<ViewParametresPageExplicationController>getController();
			controller.setXML(xml);
			controller.initData();
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}
	
	public static Node loadParamQuiz(String fxml,String xml) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(main.MainQuiz.class.getResource(fxml).openStream());
			ViewParametresQuizController controller = loader.<ViewParametresQuizController>getController();
			controller.setXML(xml);
			controller.initData();
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}
	

	public static Node loadFouilleParamFxml(String fxml,String xml) {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(main.MainQuiz.class.getResource(fxml).openStream());
			ViewParametresFouilleController controller = loader.<ViewParametresFouilleController>getController();
			controller.setXML(xml);
			controller.initData();
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}
	
	
	public static Node loadParamOddWordOutGame(String fxml,String xml) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(main.MainQuiz.class.getResource(fxml).openStream());
			ViewParametresOddWordOutGameController controller = loader.<ViewParametresOddWordOutGameController>getController();
			controller.setXML(xml);
			controller.initData();
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}
	
	public static Node loadAccueilParamFxml(String fxml,String xml) {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(main.MainQuiz.class.getResource(fxml).openStream());
			ViewParametresAccueilController controller = loader.<ViewParametresAccueilController>getController();
			controller.setXML(xml);
			controller.initData();
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}
	
	public static Node loadPNJParamFxml(String fxml,String xml) {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(main.MainQuiz.class.getResource(fxml).openStream());
			ViewParametresPNJController controller = loader.<ViewParametresPNJController>getController();
			controller.setXML(xml);
			controller.initData();
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}
	
	
	public static Node loadNextBloc(int cmptChronologie, String xmlChronologie, boolean son) {
		ArrayList<String> names=new ArrayList<String>();
		ArrayList<String> path=new ArrayList<String>();
		try {
			   SAXParserFactory factory = SAXParserFactory.newInstance();
			   SAXParser parser = factory.newSAXParser();
			   parser.parse(xmlChronologie, new DefaultHandler() {
			    public void startDocument() throws SAXException {}
			    public void endDocument() throws SAXException {}
			    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			    	names.add(qName);
			    	path.add(attributes.getValue("pathXML"));
			    	//System.out.println("startElement: " + qName + " attributs : "+attributes.getValue("pathXML")); 
			    	}
			    public void endElement(String uri, String localName, String qName) throws SAXException {}
			   });  
			  } catch (Exception e) { System.err.println(e); System.exit(1); }
		if (cmptChronologie<names.size()){
			switch (names.get(cmptChronologie)){
				case "Quiz" : try {
					return loadQuestion(new Quiz(),0,"/vues/ViewQuestion.fxml", path.get(cmptChronologie), false, cmptChronologie+1, xmlChronologie,son);
					} catch (IOException e1) {e1.printStackTrace();}
				case "PageExpl" : return loadExplicationFxml("/vues/PageExplication.fxml",path.get(cmptChronologie), false, cmptChronologie+1, xmlChronologie,son);
				case "DiagPNJ" : return loadPNJFxml(new PNJ(), 0, "/vues/ViewPNJ.fxml",path.get(cmptChronologie), false, cmptChronologie+1, xmlChronologie,son);
				case "Accueil" : return loadAccueilFxml("/vues/Accueil.fxml",path.get(cmptChronologie), false, cmptChronologie+1, xmlChronologie,son);
				case "Intrus" : String[] args={};
				try {
					MainOddWordOutGame.main(args);
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					return null;
				case "Score" :
			}
		}
		
		
		return null;
	}


}