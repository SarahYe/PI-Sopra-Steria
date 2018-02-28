package controleurs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.ParametresOddWordOutGame;
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
	
	public static Node loadQuestion(Quiz quiz, HashMap<Integer,String> reponsesJoueur, int cmpt, String fxml, String xml, boolean soloBloc, int cmptChronologie, String xmlChronologie,boolean son, int score) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(main.MainQuiz.class.getResource(fxml).openStream());
			ViewQuestionController controller = loader.<ViewQuestionController>getController();
			controller.setChronologie(soloBloc,cmptChronologie,xmlChronologie);
			controller.setXML(xml);
			controller.initData(quiz,reponsesJoueur, cmpt,son, score);
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
	
	public static Node loadQuizReviewFxml(HashMap<Integer,String> reponsesJoueur, String fxml, String xml, boolean soloBloc, int cmptChronologie, String xmlChronologie, boolean son, int score) {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(main.MainQuiz.class.getResource(fxml).openStream());
			ViewQuizRecapController controller = loader.<ViewQuizRecapController>getController();
			controller.setChronologie(soloBloc,cmptChronologie,xmlChronologie);
			controller.setXML(xml);
			controller.initData(reponsesJoueur, son, score);
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}
	
	public static Node loadPNJFxml(PNJ pnj, int cmpt, String fxml,String xml, boolean soloBloc, int cmptChronologie, String xmlChronologie, boolean son, int score) {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(main.MainQuiz.class.getResource(fxml).openStream());
			ViewPNJController controller = loader.<ViewPNJController>getController();
			controller.setChronologie(soloBloc,cmptChronologie,xmlChronologie);
			controller.setXML(xml);
			controller.initData(pnj, cmpt, son, score);
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}
	
	public static Node loadExplicationFxml(String fxml,String xml, boolean soloBloc, int cmptChronologie, String xmlChronologie, boolean son, int score) {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(main.MainQuiz.class.getResource(fxml).openStream());
			ViewPageExplicationController controller = loader.<ViewPageExplicationController>getController();
			controller.setChronologie(soloBloc,cmptChronologie,xmlChronologie);
			controller.setXML(xml);
			controller.initData(son, score);
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}
	
	public static Node loadAccueilFxml(String fxml,String xml, boolean soloBloc, int cmptChronologie, String xmlChronologie, boolean son, int score) {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(main.MainQuiz.class.getResource(fxml).openStream());
			ViewAccueilController controller = loader.<ViewAccueilController>getController();
			controller.setChronologie(soloBloc,cmptChronologie,xmlChronologie);
			controller.setXML(xml);
			controller.initData(xml,son,score);
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}
	
	public static Node loadScoreFxml(String fxml,String xml, boolean soloBloc, int cmptChronologie, String xmlChronologie, boolean son, int score) {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			System.out.println(loader);
			Node root = (Node) loader.load(main.MainQuiz.class.getResource(fxml).openStream());
			ViewScoreController controller = loader.<ViewScoreController>getController();
			controller.setChronologie(soloBloc,cmptChronologie,xmlChronologie);
			controller.setXML(xml);
			controller.setSon(son);
			controller.setScore(score);
			controller.initData();
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}
	
	public static Node loadFouilleFxml(String fxml,String xml, boolean soloBloc, int cmptChronologie, String xmlChronologie, boolean son, int score) {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			System.out.println(loader);
			Node root = (Node) loader.load(main.MainQuiz.class.getResource(fxml).openStream());
			ViewFouilleController controller = loader.<ViewFouilleController>getController();
			controller.setChronologie(soloBloc,cmptChronologie,xmlChronologie);
			controller.setXML(xml);
			controller.setSon(son);
			controller.setScore(score);
			controller.initData();
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}
	
	public static void loadOddWordOutGame(String xml, boolean soloBloc, int cmptChronologie, String xmlChronologie, boolean son, int score) {
		MainOddWordOutGame game=new MainOddWordOutGame();
		game.initData(xml,soloBloc,cmptChronologie,xmlChronologie, son,score);
		AppGameContainer app;
		try {
			app = new AppGameContainer(game,850, 650, false);
			app.setTargetFrameRate(60);
			app.setVSync(true);
			app.setShowFPS(false);
			app.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public static Node loadPuzzleParamFxml(String fxml,String xml) {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(main.MainQuiz.class.getResource(fxml).openStream());
			ViewParametresPuzzleController controller = loader.<ViewParametresPuzzleController>getController();
			controller.setXML(xml);
			controller.initData();
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}
	
	public static Node loadScoreParam(String fxml, String xml) {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(main.MainQuiz.class.getResource(fxml).openStream());
			ViewParametresScoreController controller = loader.<ViewParametresScoreController>getController();
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
					HashMap<Integer,String> reponsesJoueur = new HashMap<Integer,String>();
					return loadQuestion(new Quiz(), reponsesJoueur, 0,"/vues/ViewQuestion.fxml", path.get(cmptChronologie), false, cmptChronologie+1, xmlChronologie,son,0);
					} catch (IOException e1) {e1.printStackTrace();}
				case "PageExpl" : return loadExplicationFxml("/vues/PageExplication.fxml",path.get(cmptChronologie), false, cmptChronologie+1, xmlChronologie,son,0);
				case "DiagPNJ" : return loadPNJFxml(new PNJ(), 0, "/vues/ViewPNJ.fxml",path.get(cmptChronologie), false, cmptChronologie+1, xmlChronologie,son,0);
				case "Accueil" : return loadAccueilFxml("/vues/Accueil.fxml",path.get(cmptChronologie), false, cmptChronologie+1, xmlChronologie,son,0);
				case "Intrus" : String[] args={};
				Application.launch(ParametresOddWordOutGame.class, args);
					return null;
				case "Score" : return loadScoreFxml("/vues/ViewScore.fxml", path.get(cmptChronologie), false, cmptChronologie+1, xmlChronologie,son,0/*score*/);
			}
		}
		return null;
	}
	
	public static Node loadNextBloc(int cmptChronologie, String xmlChronologie, boolean son, int score) {
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
					HashMap<Integer,String> reponsesJoueur = new HashMap<Integer,String>();
					return loadQuestion(new Quiz(), reponsesJoueur, 0,"/vues/ViewQuestion.fxml", path.get(cmptChronologie), false, cmptChronologie+1, xmlChronologie,son, score);
					} catch (IOException e1) {e1.printStackTrace();}
				case "PageExpl" : return loadExplicationFxml("/vues/PageExplication.fxml",path.get(cmptChronologie), false, cmptChronologie+1, xmlChronologie,son, score);
				case "DiagPNJ" : return loadPNJFxml(new PNJ(), 0, "/vues/ViewPNJ.fxml",path.get(cmptChronologie), false, cmptChronologie+1, xmlChronologie,son, score);
				case "Accueil" : return loadAccueilFxml("/vues/Accueil.fxml",path.get(cmptChronologie), false, cmptChronologie+1, xmlChronologie,son,score);
				case "Intrus" : loadOddWordOutGame(path.get(cmptChronologie), false, cmptChronologie+1, xmlChronologie, son, score);
					return null;
				case "Score" : return loadScoreFxml("/vues/ViewScore.fxml", path.get(cmptChronologie), false, cmptChronologie+1, xmlChronologie,son,score);
			}
		}
		return null;
	}

	


}