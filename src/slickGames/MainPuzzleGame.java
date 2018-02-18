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
		listeImg.add("./Ressources/Images/flame1.png");
		listeImg.add("./Ressources/Images/flame2.png");
		listeImg.add("./Ressources/Images/flame3.png");
		listeImg.add("./Ressources/Images/flame4.png");
		ArrayList<String> listeIndices = new ArrayList<String>();
		listeIndices.add("Indice n°1 : La flamme la plus proche du jaune indique une température plus faible que les autres flammes.");
		listeIndices.add("Indice n°2 : La flamme la plus proche du bleu indique une température très élevée !");
		Puzzle puzzle = new Puzzle("Puzzle Game 1", "Trier les flammes afin d'obtenir celles de plus basse température à gauche et celles correspondantes à des températures plus élevées à droite.","Image",listeIndices, listeImg); 
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
