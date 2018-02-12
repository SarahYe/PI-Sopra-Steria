package slickGames.states;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import modeles.Puzzle;
import slickGames.MainPuzzleGame;

public class PuzzleGame extends BasicGameState {

	public static int ID = 3;
	public static long chrono = 0L;
	public static ArrayList<Image> piecesImg = new ArrayList<Image>();
	public static ArrayList<String> piecesTxt = new ArrayList<String>();
	public Image bandeTitre, titre, personnage, bulleDialogue, puzzleMatrice, boutonIndices;
	public Image flecheReset;
	public Puzzle puzzle;
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
		ArrayList<String> listeString = new ArrayList<String>();
		Puzzle puzzle = new Puzzle("",listeString,listeString);
		this.puzzle = puzzle.convertirXMLToJava("FichiersDeConfig/slickGame2.xml");
		for(int i=0; i < this.puzzle.getListePiecesImages().size(); i++){
			piecesImg.add(new Image(this.puzzle.getListePiecesImages().get(i)));
		}
		/*for(int i=0; i < this.puzzle.getListePiecesTextes().size(); i++){
			piecesTxt.add(this.puzzle.getListePiecesTextes().get(i));
		}*/
		
		bandeTitre = new Image("./Ressources/Images/bandeTitre.png");
		titre = new Image("./Ressources/Images/SeriousSecurity.png");
		personnage = new Image("./Ressources/Images/perso.png");
		bulleDialogue = new Image("./Ressources/Images/bulleDialogue2.png");
		puzzleMatrice = new Image("./Ressources/Images/puzzleMatrice5.png");
		boutonIndices = new Image("./Ressources/Images/boutonIndices.png");
		flecheReset = new Image("./Ressources/Images/flecheReset.png");
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
		g.setBackground(new Color(115, 115, 115));
		bandeTitre.draw(0, 0, MainPuzzleGame.longueur, 45);
		titre.draw((MainPuzzleGame.longueur - titre.getWidth()*0.15f)/2, -8, 0.15f);
		bulleDialogue.draw(personnage.getWidth()*25/100 - 20, 50, 1.0f);
		personnage.draw(30, 65, 0.3f);
		puzzleMatrice.draw((MainPuzzleGame.longueur - puzzleMatrice.getWidth())/2,(MainPuzzleGame.hauteur - puzzleMatrice.getHeight())/2);
		boutonIndices.draw(MainPuzzleGame.longueur*3/4 - boutonIndices.getWidth()/2,(MainPuzzleGame.hauteur - boutonIndices.getHeight())/2-100, 0.7f);
		flecheReset.draw((MainPuzzleGame.longueur - flecheReset.getWidth())/2,(MainPuzzleGame.hauteur - flecheReset.getHeight())/2-100, 0.7f);
		
		g.setColor(Color.black);
		drawStrings(puzzle.getIntitulePuzzle(), 145, 63, g);
		
		piecesImg.get(0).draw(MainPuzzleGame.longueur/2-300, (MainPuzzleGame.hauteur - puzzleMatrice.getHeight())/2+120, 100, 100);
		piecesImg.get(1).draw(MainPuzzleGame.longueur/2-50, (MainPuzzleGame.hauteur - puzzleMatrice.getHeight())/2+120, 100, 100);
		piecesImg.get(2).draw(MainPuzzleGame.longueur/2+200, (MainPuzzleGame.hauteur - puzzleMatrice.getHeight())/2+120, 100, 100);
		piecesImg.get(3).draw(MainPuzzleGame.longueur/2-175, (MainPuzzleGame.hauteur - puzzleMatrice.getHeight())/2+220, 100, 100);
		piecesImg.get(4).draw(MainPuzzleGame.longueur/2+75, (MainPuzzleGame.hauteur - puzzleMatrice.getHeight())/2+220, 100, 100);
		
		
		int mins = (int) chrono / (60*1000);
	    int remainder = (int) chrono/1000 - mins * 60;
	    int secs = remainder;
	    if(secs <0){ secs = 0; }
	    String timer = mins+" : ";
	    if (secs >=0 && secs <10) { timer += "0"; }
	    timer += secs;
	    g.setColor(Color.white);
		g.drawString(timer, (MainPuzzleGame.longueur-g.getFont().getWidth(timer))/2, MainPuzzleGame.hauteur-40);
		
	}
	
	public static void drawStrings(String text, int x, int y, Graphics g)
    {
		List<String> parts = new ArrayList<>();

	    int length = text.length();
	    int size = 67;
	    for (int i = 0; i < length; i += size) {
	    	parts.add(text.substring(i, Math.min(length, i + size)));
	    }
	    String[] ln = parts.toArray(new String[0]);
			
	    int h = g.getFont().getLineHeight();
	    for (int i=0; i<ln.length; i++) {
	    	g.drawString(ln[i], x, y+(h*i));
	    }
    }

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		chrono += delta;
		
	}

	@Override
	public int getID() {
		return ID;
	}

}
