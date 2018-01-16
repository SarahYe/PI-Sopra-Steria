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
	
	public static int longueur=800;
	public static int hauteur=580;
	
	public static void main(String[] args) throws SlickException {
			
		  Reponse reponse1 = new Reponse("Un casque", Boolean.TRUE, "justification 1");
		  Reponse reponse2 = new Reponse("Un gilet", Boolean.TRUE, "justification 2");
		  Reponse reponse3 = new Reponse("Des lunettes de soleil", Boolean.FALSE, "justification 3");
		  
		  ArrayList<Reponse> ListeReponses = new ArrayList<Reponse>();
		  ListeReponses.add(reponse1); ListeReponses.add(reponse2);
		  ListeReponses.add(reponse3);
		  
		  Question question1 = new Question("Dans un chantier, quels équipements suis-je susceptible de porter ? À vous de faire le tri !", ListeReponses);
		  
		  ArrayList<Question> ListeQuestions = new ArrayList<Question>();
		  ListeQuestions.add(question1);
		  
		  Quiz quiz = new Quiz ("Test",ListeQuestions);
		  
		  quiz.convertirJavaToXML(quiz, "FichiersDeConfig/slickGame.xml");
		  //quiz.convertirXMLToJava("FichiersDeConfig/slickGame.xml");
		 
		
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

