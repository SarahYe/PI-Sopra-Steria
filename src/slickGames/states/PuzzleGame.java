package slickGames.states;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import modeles.Puzzle;
import slickGames.MainOddWordOutGame;
import slickGames.MainPuzzleGame;
import slickGames.entite.PuzzlePieceImg;

public class PuzzleGame extends BasicGameState {

	public static int ID = 3;
	public static ArrayList<PuzzlePieceImg> piecesImg = new ArrayList<PuzzlePieceImg>();
	public static long chrono = 0L;
	public static ArrayList<String> piecesTxt = new ArrayList<String>();
	public static ArrayList<Integer> currentMatriceOrder = new ArrayList<Integer>();
	public static boolean gameFinished = false;
	public int draggedPieceNumber = -1;
	public int indiceIndex = 0;
	public Image bandeTitre, titre, personnage, bulleDialogue, puzzleMatrice, boutonIndices;
	public Image flecheReset;
	public Puzzle puzzle;
	public boolean indiceAsked = false;
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
		bandeTitre = new Image("./Ressources/Images/bandeTitre.png");
		titre = new Image("./Ressources/Images/SeriousSecurity.png");
		personnage = new Image("./Ressources/Images/perso.png");
		bulleDialogue = new Image("./Ressources/Images/bulleDialogue2.png");
		puzzleMatrice = new Image("./Ressources/Images/puzzleMatrice5.png");
		boutonIndices = new Image("./Ressources/Images/boutonIndices.png");
		flecheReset = new Image("./Ressources/Images/flecheReset.png");
		
		ArrayList<String> listeString = new ArrayList<String>();
		Puzzle puzzle = new Puzzle("","","",listeString,listeString);
		this.puzzle = puzzle.convertirXMLToJava("FichiersDeConfig/slickGame2.xml");
		/*for(int i=0; i < this.puzzle.getListePiecesImages().size(); i++){
			piecesImg.add(new Image(this.puzzle.getListePiecesImages().get(i)));
		}*/
		/*for(int i=0; i < this.puzzle.getListePiecesTextes().size(); i++){
			piecesTxt.add(this.puzzle.getListePiecesTextes().get(i));
		}*/
		ArrayList<String> list = this.puzzle.getListeFragments();
		PuzzlePieceImg p1 = new PuzzlePieceImg(1, new Image(list.get(0)), MainPuzzleGame.longueur/2-300, (MainPuzzleGame.hauteur - puzzleMatrice.getHeight())/2+120);
		PuzzlePieceImg p2 = new PuzzlePieceImg(2, new Image(list.get(1)), MainPuzzleGame.longueur/2-50, (MainPuzzleGame.hauteur - puzzleMatrice.getHeight())/2+120);
		PuzzlePieceImg p3 = new PuzzlePieceImg(3, new Image(list.get(2)), MainPuzzleGame.longueur/2+200, (MainPuzzleGame.hauteur - puzzleMatrice.getHeight())/2+120);
		PuzzlePieceImg p4 = new PuzzlePieceImg(4, new Image(list.get(3)), MainPuzzleGame.longueur/2-175, (MainPuzzleGame.hauteur - puzzleMatrice.getHeight())/2+220);
		PuzzlePieceImg p5 = new PuzzlePieceImg(5, new Image(list.get(4)), MainPuzzleGame.longueur/2+75, (MainPuzzleGame.hauteur - puzzleMatrice.getHeight())/2+220);
		piecesImg.add(p1);
		piecesImg.add(p2);
		piecesImg.add(p3);
		piecesImg.add(p4);
		piecesImg.add(p5);
		
		for (int i = 0; i < this.puzzle.getListeFragments().size(); i++){
			currentMatriceOrder.add(-1);
		}
		
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
		if(!indiceAsked){
			drawStrings(puzzle.getIntitule(), 145, 63, g);
		} else {
			if(puzzle.getListeIndices().size() >= indiceIndex){
				drawStrings(puzzle.getListeIndices().get(indiceIndex-1), 145, 63, g);
			} else {
				drawStrings("Il n'y a plus d'indice pour ce puzzle...", 145, 63, g);
			}		
		}
		
		
		for (int i = 0; i < piecesImg.size(); i++){
			piecesImg.get(i).render(container, game, g);
		}
		
		int mins = (int) chrono / (60*1000);
	    int remainder = (int) chrono/1000 - mins * 60;
	    int secs = remainder;
	    if(secs <0){ secs = 0; }
	    String timer = mins+" : ";
	    if (secs >=0 && secs <10) { timer += "0"; }
	    timer += secs;
	    g.setColor(Color.white);
		g.drawString(timer, (MainPuzzleGame.longueur-g.getFont().getWidth(timer))/2, MainPuzzleGame.hauteur-40);
		
		if(isGoodAnswer()){
			container.pause();
			//backgroundClip.close();
			gameFinished = true;
			
			String str1 = "Game Over";
			String str3 = "PRESS ENTER TO EXIT";
			
			float middleXstr1 = (MainOddWordOutGame.longueur-g.getFont().getWidth(str1))/2;
			float middleYstr1 = (MainOddWordOutGame.hauteur-g.getFont().getHeight(str1))/2-50;
			float middleXstr3 = (MainOddWordOutGame.longueur-g.getFont().getWidth(str3))/2;
			float middleYstr3 = (MainOddWordOutGame.hauteur-g.getFont().getHeight(str3))/2+30;
			g.setColor(Color.white);
			g.drawString(str1, middleXstr1, middleYstr1);
			
			g.setColor(Color.white);
			g.drawString(str3, middleXstr3, middleYstr3);	
			
			System.out.println("BONNE REPONSE !");
		}
	}
	
	public static boolean isGoodAnswer(){
		int index = 0;
		for(int i : currentMatriceOrder) {
			if (i != index) {
				return false;
			} else {
				index++;
			}
		}
	    return true;
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
    public void mouseReleased(int button, int x, int y) {
    	if(button == Input.MOUSE_LEFT_BUTTON) {
    		if (x > (MainPuzzleGame.longueur - flecheReset.getWidth())/2 && x < (MainPuzzleGame.longueur - flecheReset.getWidth())/2+flecheReset.getWidth()*0.7f  && y >(MainPuzzleGame.hauteur - flecheReset.getHeight())/2-100 && y < (MainPuzzleGame.hauteur - flecheReset.getHeight())/2-100+flecheReset.getHeight()*0.7f){
    			for (int i = 0; i < piecesImg.size(); i++){
    				piecesImg.get(i).resetPosition();
    			}
    			indiceAsked = false;
    			indiceIndex = 0;
    			for (int i = 0; i < this.puzzle.getListeFragments().size(); i++){
    				currentMatriceOrder.set(i, -1);
    			}
    		}
    		if (x > (MainPuzzleGame.longueur*3/4 - boutonIndices.getWidth()/2) && x < MainPuzzleGame.longueur*3/4 - boutonIndices.getWidth()/2+boutonIndices.getWidth()*0.7f  && y >(MainPuzzleGame.hauteur - boutonIndices.getHeight())/2-100 && y < (MainPuzzleGame.hauteur - boutonIndices.getHeight())/2-100+boutonIndices.getHeight()*0.7f){
    			indiceAsked = true;
    			indiceIndex++;
    		}
        	if (draggedPieceNumber == -1){
        		for (int i = 0; i < piecesImg.size(); i++){
    				if (x > piecesImg.get(i).getX() && x < piecesImg.get(i).getX()+piecesImg.get(i).getWidth() && y > piecesImg.get(i).getY() && y < piecesImg.get(i).getY()+piecesImg.get(i).getHeight()){
    					draggedPieceNumber = i;
    					
    					if(y > MainPuzzleGame.hauteur/2-50 && y < MainPuzzleGame.hauteur/2+50){
    	        			if(x > MainPuzzleGame.longueur/2-250 && x < MainPuzzleGame.longueur/2-150){
    	        				currentMatriceOrder.set(0, -1);
    	        				System.out.println("Matrice n°0 vide");
    	        			} else if(x > MainPuzzleGame.longueur/2-150 && x < MainPuzzleGame.longueur/2-50){
    	        				currentMatriceOrder.set(1, -1);
    	        				System.out.println("Matrice n°1 vide");
    	        			} else if(x > MainPuzzleGame.longueur/2-50 && x < MainPuzzleGame.longueur/2+50){
    	        				currentMatriceOrder.set(2, -1);
    	        				System.out.println("Matrice n°2 vide");
    	        			} else if(x > MainPuzzleGame.longueur/2+50 && x < MainPuzzleGame.longueur/2+150){
    	        				currentMatriceOrder.set(3, -1);
    	        				System.out.println("Matrice n°3 vide");
    	        			} else if(x > MainPuzzleGame.longueur/2+150 && x < MainPuzzleGame.longueur/2+250){
    	        				currentMatriceOrder.set(4, -1);
    	        				System.out.println("Matrice n°4 vide");
    	        			}
    	        		}
    				}
    			}
        	} else {
        		if(y > MainPuzzleGame.hauteur/2-50 && y < MainPuzzleGame.hauteur/2+50){
        			if(x > MainPuzzleGame.longueur/2-250 && x < MainPuzzleGame.longueur/2-150){
        				if (currentMatriceOrder.get(0) != -1){
        					piecesImg.get(currentMatriceOrder.get(0)).resetPosition();
        				}
        				piecesImg.get(draggedPieceNumber).setX(MainPuzzleGame.longueur/2-250);
        				piecesImg.get(draggedPieceNumber).setY(MainPuzzleGame.hauteur/2-50);
        				currentMatriceOrder.set(0, draggedPieceNumber);
        				System.out.println("Matrice n°0 remplie !");
        			} else if(x > MainPuzzleGame.longueur/2-150 && x < MainPuzzleGame.longueur/2-50){
        				if (currentMatriceOrder.get(1) != -1){
        					piecesImg.get(currentMatriceOrder.get(1)).resetPosition();
        				}
        				piecesImg.get(draggedPieceNumber).setX(MainPuzzleGame.longueur/2-150);
        				piecesImg.get(draggedPieceNumber).setY(MainPuzzleGame.hauteur/2-50);
        				currentMatriceOrder.set(1, draggedPieceNumber);
        				System.out.println("Matrice n°1 remplie !");
        			} else if(x > MainPuzzleGame.longueur/2-50 && x < MainPuzzleGame.longueur/2+50){
        				if (currentMatriceOrder.get(2) != -1){
        					piecesImg.get(currentMatriceOrder.get(2)).resetPosition();
        				}
        				piecesImg.get(draggedPieceNumber).setX(MainPuzzleGame.longueur/2-50);
        				piecesImg.get(draggedPieceNumber).setY(MainPuzzleGame.hauteur/2-50);
        				currentMatriceOrder.set(2, draggedPieceNumber);
        				System.out.println("Matrice n°2 remplie !");
        			} else if(x > MainPuzzleGame.longueur/2+50 && x < MainPuzzleGame.longueur/2+150){
        				if (currentMatriceOrder.get(3) != -1){
        					piecesImg.get(currentMatriceOrder.get(3)).resetPosition();
        				}
        				piecesImg.get(draggedPieceNumber).setX(MainPuzzleGame.longueur/2+50);
        				piecesImg.get(draggedPieceNumber).setY(MainPuzzleGame.hauteur/2-50);
        				currentMatriceOrder.set(3, draggedPieceNumber);
        				System.out.println("Matrice n°3 remplie !");
        			} else if(x > MainPuzzleGame.longueur/2+150 && x < MainPuzzleGame.longueur/2+250){
        				if (currentMatriceOrder.get(4) != -1){
        					piecesImg.get(currentMatriceOrder.get(4)).resetPosition();
        				}
        				piecesImg.get(draggedPieceNumber).setX(MainPuzzleGame.longueur/2+150);
        				piecesImg.get(draggedPieceNumber).setY(MainPuzzleGame.hauteur/2-50);
        				currentMatriceOrder.set(4, draggedPieceNumber);
        				System.out.println("Matrice n°4 remplie !");
        			}
        		} else if (y < MainPuzzleGame.hauteur/2+50){
        			piecesImg.get(draggedPieceNumber).resetPosition();			
        		}
        		draggedPieceNumber = -1;
        	}     	
        }
    }
    
	@Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        if (draggedPieceNumber != -1) {
        	piecesImg.get(draggedPieceNumber).setX(piecesImg.get(draggedPieceNumber).getX()+newx-oldx);
        	piecesImg.get(draggedPieceNumber).setY(piecesImg.get(draggedPieceNumber).getY()+newy-oldy);
        }
    }
	
	public void keyPressed(int key, char c) {
		switch (key){
		case Input.KEY_ENTER:
			if(gameFinished){
				System.exit(0);
			}
			break;
		}
	}

	@Override
	public int getID() {
		return ID;
	}

}
