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

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modeles.PNJ;
import modeles.Quiz;
import slickGames.MainOddWordOutGame;
import slickGames.MainPuzzleGame;

/**
 * Classe regroupant les différentes fonctions permettant de charger les différentes interfaces de jeu et de paramétrages ainsi que permettant de passer d'un bloc à l'autre
 * @author Tagre
 *
 */
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

	/*
	 * public static Node loadQuizFxml(String fxml, String xml, boolean soloBloc,
	 * int cmptChronologie, String xmlChronologie) { FXMLLoader loader = new
	 * FXMLLoader(); try { loader.setLocation(JFxUtils.class.getResource(fxml));
	 * Node root = (Node)
	 * loader.load(main.MainQuiz.class.getResource(fxml).openStream());
	 * QuizAccueilController controller =
	 * loader.<QuizAccueilController>getController();
	 * controller.setChronologie(soloBloc, cmptChronologie, xmlChronologie);
	 * controller.setXML(xml); return root; } catch (IOException e) { throw new
	 * IllegalStateException("cannot load FXML screen", e); } }
	 */

	/**
	 * Fonction permettant de récupérer un noeud contenant une question du quiz
	 * @param quiz quiz actuellement en cours
	 * @param reponsesJoueur liste des réponses possibles du joueur
	 * @param cmpt compteur représentant l'avancement du quiz
	 * @param fxml fxml contenant l'interface de la question à charger
	 * @param xml xml sur lequel s'appuyer pour charger la question
	 * @param soloBloc boolean indiquant si le bloc est seul ou fait partie d'une chronologie
	 * @param cmptChronologie compteur de l'avancement dans la chronologie
	 * @param xmlChronologie chronologie xml sur laquelle le jeu se base
	 * @param son booleen signifiant si le son est actif ou non
	 * @param score score obtenu par le joueur
	 * @return le Node contenant la question du quiz voulue
	 * @throws IOException
	 */
	public static Node loadQuestion(Quiz quiz, HashMap<Integer, String> reponsesJoueur, int cmpt, String fxml,
			String xml, boolean soloBloc, int cmptChronologie, String xmlChronologie, boolean son, int score)
			throws IOException {

		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(main.MainQuiz.class.getResource(fxml).openStream());
			ViewQuestionController controller = loader.<ViewQuestionController>getController();
			controller.setChronologie(soloBloc, cmptChronologie, xmlChronologie);
			controller.setXML(xml);
			controller.initData(quiz, reponsesJoueur, cmpt, son, score);
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}

	/**
	 * Fonction permettant de récupérer un noeud contenant une review du Quiz
	 * @param reponsesJoueur liste des réponses possibles du joueur
	 * @param fxml fxml contenant l'interface d'une review d'un quiz
	 * @param xml xml sur lequel s'appuyer pour charger la review
	 * @param soloBloc boolean indiquant si le bloc est seul ou fait partie d'une chronologie
	 * @param cmptChronologie compteur de l'avancement dans la chronologie
	 * @param xmlChronologie chronologie xml sur laquelle le jeu se base
	 * @param son booleen signifiant si le son est actif ou non
	 * @param score score obtenu par le joueur
	 * @return le noeud contenant la review du quiz voulue
	 */
	public static Node loadQuizReviewFxml(HashMap<Integer, String> reponsesJoueur, String fxml, String xml,
			boolean soloBloc, int cmptChronologie, String xmlChronologie, boolean son, int score) {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(main.MainQuiz.class.getResource(fxml).openStream());
			ViewQuizRecapController controller = loader.<ViewQuizRecapController>getController();
			controller.setChronologie(soloBloc, cmptChronologie, xmlChronologie);
			controller.setXML(xml);
			controller.initData(reponsesJoueur, son, score);
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}

	/**
	 * Fonction permettant de récupérer un noeud contenant l'affichage d'un bloc pnj
	 * @param pnj le pnj à afficher
	 * @param cmpt le compteur représentant l'avancement dans le bloc
	 * @param fxml fxml contenant l'interface du bloc pnj
	 * @param xml xml sur lequel s'appuyer pour charger le bloc pnj
	 * @param soloBloc boolean indiquant si le bloc est seul ou fait partie d'une chronologie
	 * @param cmptChronologie compteur de l'avancement dans la chronologie
	 * @param xmlChronologie chronologie xml sur laquelle le jeu se base
	 * @param son booleen signifiant si le son est actif ou non
	 * @param score score obtenu par le joueur
	 * @return le noeud contenant le bloc pnj voulu
	 */
	public static Node loadPNJFxml(PNJ pnj, int cmpt, String fxml, String xml, boolean soloBloc, int cmptChronologie,
			String xmlChronologie, boolean son, int score) {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(main.MainQuiz.class.getResource(fxml).openStream());
			ViewPNJController controller = loader.<ViewPNJController>getController();
			controller.setChronologie(soloBloc, cmptChronologie, xmlChronologie);
			controller.setXML(xml);
			controller.initData(pnj, cmpt, son, score);
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}

	/**
	 * Fonction permettant de récupérer un noeud contenant l'affichage d'un bloc explication
	 * @param fxml fxml contenant l'interface du bloc explication
	 * @param xml xml sur lequel s'appuyer pour charger le bloc explication
	 * @param soloBloc boolean indiquant si le bloc est seul ou fait partie d'une chronologie
	 * @param cmptChronologie compteur de l'avancement dans la chronologie
	 * @param xmlChronologie chronologie xml sur laquelle le jeu se base
	 * @param son booleen signifiant si le son est actif ou non
	 * @param score score obtenu par le joueur
	 * @return le noeud contenant le bloc explication voulu
	 */
	public static Node loadExplicationFxml(String fxml, String xml, boolean soloBloc, int cmptChronologie,
			String xmlChronologie, boolean son, int score) {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(main.MainQuiz.class.getResource(fxml).openStream());
			ViewPageExplicationController controller = loader.<ViewPageExplicationController>getController();
			controller.setChronologie(soloBloc, cmptChronologie, xmlChronologie);
			controller.setXML(xml);
			controller.initData(son, score);
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}

	/**
	 * Fonction permettant de récupérer un noeud contenant l'affichage d'un bloc accueil
	 * @param fxml fxml contenant l'interface du bloc accueil
	 * @param xml xml sur lequel s'appuyer pour charger le bloc accueil
	 * @param soloBloc boolean indiquant si le bloc est seul ou fait partie d'une chronologie
	 * @param cmptChronologie compteur de l'avancement dans la chronologie
	 * @param xmlChronologie chronologie xml sur laquelle le jeu se base
	 * @param son booleen signifiant si le son est actif ou non
	 * @param score score obtenu par le joueur
	 * @return le noeud contenant le bloc accueil voulu
	 */
	public static Node loadAccueilFxml(String fxml, String xml, boolean soloBloc, int cmptChronologie,
			String xmlChronologie, boolean son, int score) {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(main.MainQuiz.class.getResource(fxml).openStream());
			ViewAccueilController controller = loader.<ViewAccueilController>getController();
			controller.setChronologie(soloBloc, cmptChronologie, xmlChronologie);
			controller.setXML(xml);
			controller.initData(xml, son, score);
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}

	/**
	 * Fonction permettant de récupérer un noeud contenant l'affichage d'un bloc score
	 * @param fxml fxml contenant l'interface du bloc score
	 * @param xml xml sur lequel s'appuyer pour charger le bloc score
	 * @param soloBloc boolean indiquant si le bloc est seul ou fait partie d'une chronologie
	 * @param cmptChronologie compteur de l'avancement dans la chronologie
	 * @param xmlChronologie chronologie xml sur laquelle le jeu se base
	 * @param son booleen signifiant si le son est actif ou non
	 * @param score score obtenu par le joueur
	 * @return le noeud contenant le bloc score voulu
	 */
	public static Node loadScoreFxml(String fxml, String xml, boolean soloBloc, int cmptChronologie,
			String xmlChronologie, boolean son, int score) {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			System.out.println(loader);
			Node root = (Node) loader.load(main.MainQuiz.class.getResource(fxml).openStream());
			ViewScoreController controller = loader.<ViewScoreController>getController();
			controller.setChronologie(soloBloc, cmptChronologie, xmlChronologie);
			controller.setXML(xml);
			controller.setSon(son);
			controller.setScore(score);
			controller.initData();
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}

	/**
	 * Fonction permettant de récupérer un noeud contenant l'affichage d'un bloc fouille
	 * @param fxml fxml contenant l'interface du bloc fouille
	 * @param xml xml sur lequel s'appuyer pour charger le bloc fouille
	 * @param soloBloc boolean indiquant si le bloc est seul ou fait partie d'une chronologie
	 * @param cmptChronologie compteur de l'avancement dans la chronologie
	 * @param xmlChronologie chronologie xml sur laquelle le jeu se base
	 * @param son booleen signifiant si le son est actif ou non
	 * @param score score obtenu par le joueur
	 * @return le noeud contenant le bloc fouille voulu
	 */
	public static Node loadFouilleFxml(String fxml, String xml, boolean soloBloc, int cmptChronologie,
			String xmlChronologie, boolean son, int score) {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			System.out.println(loader);
			Node root = (Node) loader.load(main.MainQuiz.class.getResource(fxml).openStream());
			ViewFouilleController controller = loader.<ViewFouilleController>getController();
			controller.setChronologie(soloBloc, cmptChronologie, xmlChronologie);
			controller.setXML(xml);
			controller.setSon(son);
			controller.setScore(score);
			controller.initData();
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}

	/**
	 * Fonction permettant de charger un jeu de tri
	 * @param xml xml sur lequel s'appuyer pour charger le bloc tri
	 * @param soloBloc boolean indiquant si le bloc est seul ou fait partie d'une chronologie
	 * @param cmptChronologie compteur de l'avancement dans la chronologie
	 * @param xmlChronologie chronologie xml sur laquelle le jeu se base
	 * @param son booleen signifiant si le son est actif ou non
	 * @param score score obtenu par le joueur
	 */
	public static void loadOddWordOutGame(String xml, boolean soloBloc, int cmptChronologie, String xmlChronologie,
			boolean son, int score) {
		MainOddWordOutGame game = new MainOddWordOutGame();
		game.initData(xml, soloBloc, cmptChronologie, xmlChronologie, son, score);
		AppGameContainer app;
		try {
			app = new AppGameContainer(game, 850, 650, false);
			app.setTargetFrameRate(60);
			app.setVSync(true);
			app.setShowFPS(false);
			app.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Fonction permettant de charger un jeu de puzzle
	 * @param xml xml sur lequel s'appuyer pour charger le bloc puzzle
	 * @param soloBloc boolean indiquant si le bloc est seul ou fait partie d'une chronologie
	 * @param cmptChronologie compteur de l'avancement dans la chronologie
	 * @param xmlChronologie chronologie xml sur laquelle le jeu se base
	 * @param son booleen signifiant si le son est actif ou non
	 * @param score score obtenu par le joueur
	 */
	public static void loadPuzzleGame(String xml, boolean soloBloc, int cmptChronologie, String xmlChronologie,
			boolean son, int score) {
		MainPuzzleGame game = new MainPuzzleGame();
		game.initData(xml, soloBloc, cmptChronologie, xmlChronologie, son, score);
		AppGameContainer app;
		try {
			app = new AppGameContainer(game, 850, 650, false);
			app.setTargetFrameRate(60);
			app.setVSync(true);
			app.setShowFPS(false);
			app.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Fonction permettant de récupérer un noeud contenant l'interface de paramétrage d'un bloc explication
	 * @param fxml fxml contenant l'interface de parametrage du bloc explication
	 * @param xml xml sur lequel s'appuyer pour charger l'interface de parametrage du bloc explication
	 * @return le noeud contenant cette interface
	 */
	public static Node loadExplicationParamFxml(String fxml, String xml) {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(main.MainQuiz.class.getResource(fxml).openStream());
			ViewParametresPageExplicationController controller = loader
					.<ViewParametresPageExplicationController>getController();
			controller.setXML(xml);
			controller.initData();
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}

	/**
	 * Fonction permettant de récupérer un noeud contenant l'interface de paramétrage d'un bloc quiz
	 * @param fxml fxml contenant l'interface de parametrage du bloc quiz
	 * @param xml xml sur lequel s'appuyer pour charger l'interface de parametrage du bloc quiz
	 * @return le noeud contenant cette interface
	 * @throws IOException
	 */
	public static Node loadParamQuiz(String fxml, String xml) throws IOException {
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

	/**
	 * Fonction permettant de récupérer un noeud contenant l'interface de paramétrage d'un bloc fouille
	 * @param fxml fxml contenant l'interface de parametrage du bloc fouille
	 * @param xml xml sur lequel s'appuyer pour charger l'interface de parametrage du bloc fouille
	 * @return le noeud contenant cette interface
	 * @return
	 */
	public static Node loadFouilleParamFxml(String fxml, String xml) {
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

	/**
	 * Fonction permettant de récupérer un noeud contenant l'interface de paramétrage d'un bloc jeu de tri
	 * @param fxml fxml contenant l'interface de parametrage du bloc jeu de tri
	 * @param xml xml sur lequel s'appuyer pour charger l'interface de parametrage du bloc jeu de tri
	 * @return le noeud contenant cette interface
	 * @throws IOException
	 */
	public static Node loadParamOddWordOutGame(String fxml, String xml) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(main.MainQuiz.class.getResource(fxml).openStream());
			ViewParametresOddWordOutGameController controller = loader
					.<ViewParametresOddWordOutGameController>getController();
			controller.setXML(xml);
			controller.initData();
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}

	/**
	 * Fonction permettant de récupérer un noeud contenant l'interface de paramétrage d'un bloc accueil
	 * @param fxml fxml contenant l'interface de parametrage du bloc accueil
	 * @param xml xml sur lequel s'appuyer pour charger l'interface de parametrage du bloc accueil
	 * @return le noeud contenant cette interface
	 */
	public static Node loadAccueilParamFxml(String fxml, String xml) {
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

	/**
	 * Fonction permettant de récupérer un noeud contenant l'interface de paramétrage d'un bloc pnj
	 * @param fxml fxml contenant l'interface de parametrage du bloc pnj
	 * @param xml xml sur lequel s'appuyer pour charger l'interface de parametrage du bloc pnj
	 * @return le noeud contenant cette interface
	 */
	public static Node loadPNJParamFxml(String fxml, String xml) {
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

	/**
	 * Fonction permettant de récupérer un noeud contenant l'interface de paramétrage d'un bloc puzzle
	 * @param fxml fxml contenant l'interface de parametrage du bloc puzzle
	 * @param xml xml sur lequel s'appuyer pour charger l'interface de parametrage du bloc puzzle
	 * @return le noeud contenant cette interface
	 */
	public static Node loadPuzzleParamFxml(String fxml, String xml) {
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

	/**
	 * Fonction permettant de récupérer un noeud contenant l'interface de paramétrage d'un bloc score
	 * @param fxml fxml contenant l'interface de parametrage du bloc score
	 * @param xml xml sur lequel s'appuyer pour charger l'interface de parametrage du bloc score
	 * @return le noeud contenant cette interface
	 */
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

	/**
	 * Fonction permettant de charger le bloc suivant se trouvant sur une chronologie donnée (avec remise à zero du score)
	 * @param cmptChronologie compteur indiquant l'avancement dans la chronologie
	 * @param xmlChronologie xml contenant la chronologie
	 * @param son booleen signifiant si le son est actif ou non
	 * @return le noeud contenant le prochain bloc (null si le jeu est sous slick2D ou il n'y a pas de prochain bloc)
	 */
	public static Node loadNextBloc(int cmptChronologie, String xmlChronologie, boolean son) {
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> path = new ArrayList<String>();
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
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
			System.exit(1);
		}
		if (cmptChronologie < names.size()) {
			switch (names.get(cmptChronologie)) {
			case "Quiz":
				try {
					HashMap<Integer, String> reponsesJoueur = new HashMap<Integer, String>();
					return loadQuestion(new Quiz(), reponsesJoueur, 0, "/vues/ViewQuestion.fxml",
							path.get(cmptChronologie), false, cmptChronologie + 1, xmlChronologie, son, 0);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			case "PageExpl":
				return loadExplicationFxml("/vues/PageExplication.fxml", path.get(cmptChronologie), false,
						cmptChronologie + 1, xmlChronologie, son, 0);
			case "DiagPNJ":
				return loadPNJFxml(new PNJ(), 0, "/vues/ViewPNJ.fxml", path.get(cmptChronologie), false,
						cmptChronologie + 1, xmlChronologie, son, 0);
			case "Accueil":
				return loadAccueilFxml("/vues/Accueil.fxml", path.get(cmptChronologie), false, cmptChronologie + 1,
						xmlChronologie, son, 0);
			case "Intrus": // String[] args={};
				// Application.launch(ParametresOddWordOutGame.class, args);
				return null;
			case "Score":
				return loadScoreFxml("/vues/ViewScore.fxml", path.get(cmptChronologie), false, cmptChronologie + 1,
						xmlChronologie, son, 0/* score */);
			case "Fouille":
				return loadFouilleFxml("/vues/ViewFouille.fxml", path.get(cmptChronologie), false, cmptChronologie + 1,
						xmlChronologie, son, 0);
			case "Puzzle":
				return null;
			}
		}
		return null;
	}

	/**
	 * Fonction permettant de charger le bloc suivant se trouvant sur une chronologie donnée
	 * @param cmptChronologie compteur indiquant l'avancement dans la chronologie
	 * @param xmlChronologie xml contenant la chronologie
	 * @param son booleen signifiant si le son est actif ou non
	 * @param score le score obtenu par le joueur
	 * @return le noeud contenant le prochain bloc (null si le jeu est sous slick2D ou il n'y a pas de prochain bloc)
	 */
	public static Node loadNextBloc(int cmptChronologie, String xmlChronologie, boolean son, int score) {
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> path = new ArrayList<String>();
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
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
			System.exit(1);
		}
		// System.out.println("COMPTEUR : "+cmptChronologie+" | names.size :
		// "+names.size());
		if (cmptChronologie < names.size()) {
			switch (names.get(cmptChronologie)) {
			case "Quiz":
				try {
					HashMap<Integer, String> reponsesJoueur = new HashMap<Integer, String>();
					return loadQuestion(new Quiz(), reponsesJoueur, 0, "/vues/ViewQuestion.fxml",
							path.get(cmptChronologie), false, cmptChronologie + 1, xmlChronologie, son, score);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			case "PageExpl":
				return loadExplicationFxml("/vues/PageExplication.fxml", path.get(cmptChronologie), false,
						cmptChronologie + 1, xmlChronologie, son, score);
			case "DiagPNJ":
				return loadPNJFxml(new PNJ(), 0, "/vues/ViewPNJ.fxml", path.get(cmptChronologie), false,
						cmptChronologie + 1, xmlChronologie, son, score);
			case "Accueil":
				return loadAccueilFxml("/vues/Accueil.fxml", path.get(cmptChronologie), false, cmptChronologie + 1,
						xmlChronologie, son, score);
			case "Intrus":
				// loadOddWordOutGame(path.get(cmptChronologie), false, cmptChronologie+1,
				// xmlChronologie, son, score);
				return null;
			case "Score":
				return loadScoreFxml("/vues/ViewScore.fxml", path.get(cmptChronologie), false, cmptChronologie + 1,
						xmlChronologie, son, score);
			case "Fouille":
				return loadFouilleFxml("/vues/ViewFouille.fxml", path.get(cmptChronologie), false, cmptChronologie + 1,
						xmlChronologie, son, 0);
			case "Puzzle":
				return null;
			}
		}
		return null;
	}

}