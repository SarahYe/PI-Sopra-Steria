package slickGames;

import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import modeles.Puzzle;
import slickGames.states.PuzzleGame;

public class MainPuzzleGame extends StateBasedGame{
	
	public static int longueur=850;
	public static int hauteur=650;
	
	public static void main(String[] args) throws SlickException {
			
		ArrayList<String> listeImg = new ArrayList<String>();
		listeImg.add("./Ressources/Images/boutonBleu.png");
		listeImg.add("./Ressources/Images/boutonBleuP.png");
		listeImg.add("./Ressources/Images/boutonJauneP.png");
		listeImg.add("./Ressources/Images/boutonRouge.png");
		listeImg.add("./Ressources/Images/boutonRougeP.png");
		ArrayList<String> listeIndices = new ArrayList<String>();
		listeIndices.add("Ceci est le premier indice du puzzle.");
		listeIndices.add("Ceci est le second indice du puzzle.");
		Puzzle puzzle = new Puzzle("Puzzle Game 1", "Ceci est la question/intitulé/description de cette instance du jeu de puzzle. Puzzle en cours de développement, veuillez patienter...","Image",listeIndices, listeImg); 
		puzzle.convertirJavaToXML(puzzle, "FichiersDeConfig/slickGame2.xml");
		 
		
		AppGameContainer app = new AppGameContainer(new MainPuzzleGame(),longueur, hauteur, false);
		app.setTargetFrameRate(60);
		app.setVSync(true);
		app.setShowFPS(false);
		app.start();
	
	}
	

	public MainPuzzleGame() {
		super("Puzzle Game !");
	}



	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		
		addState(new PuzzleGame());	
		this.enterState(PuzzleGame.ID);
	}
}
