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
	private static String xml="FichiersDeConfig/slickGame.xml";
	private static boolean soloBloc=true;
	private static int cmptChronologie;
	public static String xmlChronologie;
	public static boolean son=true;
	public static int score=0;
	
	public static void main(String[] args) throws SlickException {
			
		/*ArrayList<String> listeImg = new ArrayList<String>();
		listeImg.add("./Ressources/Images/flame1.png");
		listeImg.add("./Ressources/Images/flame2.png");
		listeImg.add("./Ressources/Images/flame3.png");
		listeImg.add("./Ressources/Images/flame4.png");
		ArrayList<String> listeIndices = new ArrayList<String>();
		listeIndices.add("Indice n�1 : La flamme la plus proche du jaune indique une temp�rature plus faible que les autres flammes.");
		listeIndices.add("Indice n�2 : La flamme la plus proche du bleu indique une temp�rature tr�s �lev�e !");
		Puzzle puzzle = new Puzzle("Puzzle Game 1", "Trier les flammes afin d'obtenir celles de plus basse temp�rature � gauche et celles correspondantes � des temp�ratures plus �lev�es � droite.","Image",listeIndices, listeImg); 
		puzzle.convertirJavaToXML(puzzle, "FichiersDeConfig/slickGame2.xml");
		Puzzle puzzle = new Puzzle();
		puzzle.convertirJavaToXML(puzzle, "FichiersDeConfig/puzzle.xml");*/
		 
		
		AppGameContainer app = new AppGameContainer(new MainPuzzleGame(),longueur, hauteur, false);
		app.setTargetFrameRate(60);
		app.setVSync(true);
		app.setShowFPS(false);
		app.start();
	
	}
	

	public MainPuzzleGame() {
		super("Puzzle Game !");
	}

	
	public void initData(String xml, boolean soloBloc, int cmptChronologie, String xmlChronologie, boolean son, int score){
		this.xml=xml;
		this.soloBloc=soloBloc;
		this.cmptChronologie=cmptChronologie;
		this.xmlChronologie=xmlChronologie;
		this.son=son;
		this.score=score;
	}


	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		
		PuzzleGame puzzleGame = new PuzzleGame();
		puzzleGame.initData(xmlChronologie,xml,son,score,cmptChronologie);
		addState(puzzleGame);	
		this.enterState(PuzzleGame.ID);
	}
}
