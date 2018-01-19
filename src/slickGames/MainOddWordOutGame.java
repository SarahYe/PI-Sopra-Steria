package slickGames;

import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import modeles.Question;
import modeles.Quiz;
import modeles.Reponse;
import slickGames.states.OddWordOutGame;


public class MainOddWordOutGame extends StateBasedGame{
	
	public static int longueur=850;
	public static int hauteur=650;
	
	public static void main(String[] args) throws SlickException {
			
		  /*Reponse reponse1 = new Reponse("Un casque", Boolean.TRUE, "");
		  Reponse reponse2 = new Reponse("Un gilet", Boolean.TRUE, "");
		  Reponse reponse3 = new Reponse("Des lunettes de soleil", Boolean.FALSE, "");
		  Reponse reponse4 = new Reponse("Des chaussures de sécurité", Boolean.TRUE, "");
		  Reponse reponse5 = new Reponse("Des tongs", Boolean.FALSE, "");
		  Reponse reponse6 = new Reponse("Une combinaison", Boolean.TRUE, "");
		  Reponse reponse7 = new Reponse("Des coquilles anti-bruit", Boolean.TRUE, "");
		  Reponse reponse8 = new Reponse("Une casquette", Boolean.FALSE, "");
		  
		  ArrayList<Reponse> ListeReponses = new ArrayList<Reponse>();
		  ListeReponses.add(reponse1); ListeReponses.add(reponse2);
		  ListeReponses.add(reponse3); ListeReponses.add(reponse4);
		  ListeReponses.add(reponse5); ListeReponses.add(reponse6);
		  ListeReponses.add(reponse7); ListeReponses.add(reponse8);
		  
		  Question question1 = new Question("Dans un chantier, quels équipements suis-je susceptible de porter ? À vous de faire le tri !", ListeReponses);
		  
		  ArrayList<Question> ListeQuestions = new ArrayList<Question>();
		  ListeQuestions.add(question1);
		  
		  Quiz quiz = new Quiz ("Test",ListeQuestions);
		  
		  quiz.convertirJavaToXML(quiz, "FichiersDeConfig/slickGame.xml");
		  //quiz.convertirXMLToJava("FichiersDeConfig/slickGame.xml");
		 */
		
		AppGameContainer app = new AppGameContainer(new MainOddWordOutGame(),longueur, hauteur, false);
		app.setTargetFrameRate(60);
		app.setVSync(true);
		app.setShowFPS(true);
		app.start();
	
	}
	

	public MainOddWordOutGame() {
		super("Chassez les intrus !");
	}



	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		//addState(new OddWordOutExplanation());
		addState(new OddWordOutGame());
		
		this.enterState(OddWordOutGame.ID);
	}
}

