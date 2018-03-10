package slickGames.states;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

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
	public static boolean gameMuted = false;
	public static Clip backgroundClip;
	public int draggedPieceNumber = -1;
	public int indiceIndex = 0;
	public Image bandeTitre, titre, personnage, bulleDialogue, puzzleMatrice, boutonIndices;
	public Image flecheReset;
	public Image volumeImg;
	public Puzzle puzzle;
	public boolean indiceAsked = false;
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
		bandeTitre = new Image("./Ressources/Images/bandeTitre.png");
		titre = new Image("./Ressources/Images/SeriousSecurity.png");
		personnage = new Image("./Ressources/Images/perso.png");
		bulleDialogue = new Image("./Ressources/Images/bulleDialogue2.png");
		boutonIndices = new Image("./Ressources/Images/boutonIndices.png");
		flecheReset = new Image("./Ressources/Images/flecheReset.png");
		
		ArrayList<String> listeString = new ArrayList<String>();
		Puzzle puzzle = new Puzzle("","","",listeString,listeString);
		this.puzzle = puzzle.convertirXMLToJava("FichiersDeConfig/slickGame2.xml");
		
		ArrayList<String> list = this.puzzle.getListeFragments();
		ArrayList<Integer> listCorrectPos = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++){
			listCorrectPos.add(i);
		}
		long seed = System.nanoTime();
		Collections.shuffle(list, new Random(seed));
		Collections.shuffle(listCorrectPos, new Random(seed));
		
		if(this.puzzle.getFragmentType().equals("Image")){
			puzzleMatrice = new Image("./Ressources/Images/puzzleMatrice"+list.size()+".png");		
			if(list.size() <= 4){
				float partX = MainPuzzleGame.longueur/(2*list.size()+1);
				int indexX = 1;
				for (int i = 0; i < list.size(); i++){
					piecesImg.add(new PuzzlePieceImg(listCorrectPos.get(i), new Image(list.get(i)), partX*indexX, (MainPuzzleGame.hauteur*3)/4-50));
					System.out.println("Ajout pi�ce correctPos : "+listCorrectPos.get(i));
					indexX+=2;
				}
			} else if (list.size() == 5){
				PuzzlePieceImg p1 = new PuzzlePieceImg(listCorrectPos.get(0), new Image(list.get(0)), MainPuzzleGame.longueur/2-300, (MainPuzzleGame.hauteur - puzzleMatrice.getHeight())/2+120);
				PuzzlePieceImg p2 = new PuzzlePieceImg(listCorrectPos.get(1), new Image(list.get(1)), MainPuzzleGame.longueur/2-50, (MainPuzzleGame.hauteur - puzzleMatrice.getHeight())/2+120);
				PuzzlePieceImg p3 = new PuzzlePieceImg(listCorrectPos.get(2), new Image(list.get(2)), MainPuzzleGame.longueur/2+200, (MainPuzzleGame.hauteur - puzzleMatrice.getHeight())/2+120);
				PuzzlePieceImg p4 = new PuzzlePieceImg(listCorrectPos.get(3), new Image(list.get(3)), MainPuzzleGame.longueur/2-175, (MainPuzzleGame.hauteur - puzzleMatrice.getHeight())/2+220);
				PuzzlePieceImg p5 = new PuzzlePieceImg(listCorrectPos.get(4), new Image(list.get(4)), MainPuzzleGame.longueur/2+75, (MainPuzzleGame.hauteur - puzzleMatrice.getHeight())/2+220);
				piecesImg.add(p1);
				piecesImg.add(p2);
				piecesImg.add(p3);
				piecesImg.add(p4);
				piecesImg.add(p5);
			} else if (list.size() == 6){
				float partX = MainPuzzleGame.longueur/(list.size()+1);
				int indexX1 = 1;
				int indexX2 = 1;
				int indexY1 = 4;
				int indexY2 = 5;
				for (int i = 0; i < list.size(); i++){
					if(i < list.size()/2){
						piecesImg.add(new PuzzlePieceImg(listCorrectPos.get(i), new Image(list.get(i)), partX*indexX1 , (MainPuzzleGame.hauteur*indexY1)/6-50));
						indexX1+=2;
					} else {
						piecesImg.add(new PuzzlePieceImg(listCorrectPos.get(i), new Image(list.get(i)), partX*indexX2 , (MainPuzzleGame.hauteur*indexY2)/6-50));
						indexX2+=2;
					}
					
				}
			}
		}
		
		for (int i = 0; i < list.size(); i++){
			currentMatriceOrder.add(-1);
		}
		
		if(gameMuted){
			volumeImg = new Image("./Ressources/Images/volume_off.png");
		} else {
			volumeImg = new Image("./Ressources/Images/volume_on.png");
			PuzzleGame.jouerAudio("./Ressources/Sons/musicJeuPuzzle.wav", -18.0f, true);
		}
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
		g.setBackground(new Color(115, 115, 115));
		bandeTitre.draw(0, 0, MainPuzzleGame.longueur, 45);
		titre.draw((MainPuzzleGame.longueur - titre.getWidth()*0.15f)/2, -8, 0.15f);
		bulleDialogue.draw(personnage.getWidth()*25/100 - 20, 50, 1.0f);
		personnage.draw(30, 65, 0.3f);
		boutonIndices.draw(MainPuzzleGame.longueur*3/4 - boutonIndices.getWidth()/2,(MainPuzzleGame.hauteur - boutonIndices.getHeight())/2-100, 0.7f);
		flecheReset.draw((MainPuzzleGame.longueur - flecheReset.getWidth())/2,(MainPuzzleGame.hauteur - flecheReset.getHeight())/2-100, 0.7f);
		
		if(this.puzzle.getFragmentType().equals("Image")){
			puzzleMatrice.draw((MainPuzzleGame.longueur - puzzleMatrice.getWidth())/2,(MainPuzzleGame.hauteur - puzzleMatrice.getHeight())/2);
		}
		
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
		
		volumeImg.draw(5, 5, 25, 25);
		
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
			backgroundClip.close();
			gameFinished = true;
			
			Image img = new Image("./Ressources/Images/rectReponse.png");
			
			String str1 = "Game Over";
			Double score = puzzle.getScore_init() - mins*(60/puzzle.getDecr_sec())*puzzle.getDecr_pts() - ((int) secs/puzzle.getDecr_sec())*puzzle.getDecr_pts();
			if (score < puzzle.getScore_min()){
				score = puzzle.getScore_min();
			}
			int scoreInt = score.intValue();
			String str2 = "Votre score : "+scoreInt;
			String str3 = "PRESS ENTER TO EXIT";
			
			float middleXstr1 = (MainOddWordOutGame.longueur-g.getFont().getWidth(str1))/2;
			float middleYstr1 = (MainOddWordOutGame.hauteur-g.getFont().getHeight(str1))/2+100;
			float middleXstr2 = (MainOddWordOutGame.longueur-g.getFont().getWidth(str2))/2;
			float middleYstr2 = (MainOddWordOutGame.hauteur-g.getFont().getHeight(str2))/2+150;
			float middleXstr3 = (MainOddWordOutGame.longueur-g.getFont().getWidth(str3))/2;
			float middleYstr3 = (MainOddWordOutGame.hauteur-g.getFont().getHeight(str3))/2+180;
			img.draw(middleXstr2-35, middleXstr2-20-32+150, g.getFont().getWidth(str2)+70, g.getFont().getHeight(str2)+80);
			g.setColor(Color.white);
			g.drawString(str1, middleXstr1, middleYstr1);
			if(score > puzzle.getScore_init().intValue()*2/3){
				g.setColor(Color.green);
			} else if(score >= puzzle.getScore_init().intValue()*1/3){
				g.setColor(Color.orange);
			} else {
				g.setColor(Color.red);
			}
			g.drawString(str2, middleXstr2, middleYstr2);
			g.setColor(Color.white);
			g.drawString(str3, middleXstr3, middleYstr3);
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
		
		if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
	    	if(container.getInput().getMouseX() > 5 && container.getInput().getMouseX() < 30 && container.getInput().getMouseY() > 5 && container.getInput().getMouseY() < 30){
	    		muteUnmuteGame();
	    	}
	    }
		
	}
	
    @Override
    public void mouseReleased(int button, int x, int y) {
    	if(button == Input.MOUSE_LEFT_BUTTON) {
    		if(!gameFinished){
	    		if (x > (MainPuzzleGame.longueur - flecheReset.getWidth())/2 && x < (MainPuzzleGame.longueur - flecheReset.getWidth())/2+flecheReset.getWidth()*0.7f  && y >(MainPuzzleGame.hauteur - flecheReset.getHeight())/2-100 && y < (MainPuzzleGame.hauteur - flecheReset.getHeight())/2-100+flecheReset.getHeight()*0.7f){
	    			PuzzleGame.jouerAudio("./Ressources/Sons/reset.wav", -22.0f, false);
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
	    					PuzzleGame.jouerAudio("./Ressources/Sons/draggedOn.wav", -22.0f, false);
	    					if(y > MainPuzzleGame.hauteur/2-50 && y < MainPuzzleGame.hauteur/2+50){
	    						changeMatriceInfos(x);
	    	        		}
	    				}
	    			}
	        	} else {
	        		if(y > MainPuzzleGame.hauteur/2-50 && y < MainPuzzleGame.hauteur/2+50){
	        			fillMatricePart(x);
	        		} else if (y < MainPuzzleGame.hauteur/2+50){
	        			piecesImg.get(draggedPieceNumber).resetPosition();			
	        		}
	        		draggedPieceNumber = -1;
	        	}   	        	
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
		case Input.KEY_M:
			muteUnmuteGame();
			break;
		}
	}
	
	public void changeMatriceInfos(int x){
		if (this.puzzle.getListeFragments().size() == 4){
			if(x > MainPuzzleGame.longueur/2-200 && x < MainPuzzleGame.longueur/2-100){
				currentMatriceOrder.set(0, -1);
				System.out.println("Matrice n�0 vide");
			} else if(x > MainPuzzleGame.longueur/2-100 && x < MainPuzzleGame.longueur/2){
				currentMatriceOrder.set(1, -1);
				System.out.println("Matrice n�1 vide");
			} else if(x > MainPuzzleGame.longueur/2 && x < MainPuzzleGame.longueur/2+100){
				currentMatriceOrder.set(2, -1);
				System.out.println("Matrice n�2 vide");
			} else if(x > MainPuzzleGame.longueur/2+100 && x < MainPuzzleGame.longueur/2+200){
				currentMatriceOrder.set(3, -1);
				System.out.println("Matrice n�3 vide");
			}
		} else if (this.puzzle.getListeFragments().size() == 5){
			if(x > MainPuzzleGame.longueur/2-250 && x < MainPuzzleGame.longueur/2-150){
				currentMatriceOrder.set(0, -1);
				System.out.println("Matrice n�0 vide");
			} else if(x > MainPuzzleGame.longueur/2-150 && x < MainPuzzleGame.longueur/2-50){
				currentMatriceOrder.set(1, -1);
				System.out.println("Matrice n�1 vide");
			} else if(x > MainPuzzleGame.longueur/2-50 && x < MainPuzzleGame.longueur/2+50){
				currentMatriceOrder.set(2, -1);
				System.out.println("Matrice n�2 vide");
			} else if(x > MainPuzzleGame.longueur/2+50 && x < MainPuzzleGame.longueur/2+150){
				currentMatriceOrder.set(3, -1);
				System.out.println("Matrice n�3 vide");
			} else if(x > MainPuzzleGame.longueur/2+150 && x < MainPuzzleGame.longueur/2+250){
				currentMatriceOrder.set(4, -1);
				System.out.println("Matrice n�4 vide");
			}
		} else if (this.puzzle.getListeFragments().size() == 6){
			if(x > MainPuzzleGame.longueur/2-300 && x < MainPuzzleGame.longueur/2-200){
				currentMatriceOrder.set(0, -1);
				System.out.println("Matrice n�0 vide");
			} else if(x > MainPuzzleGame.longueur/2-200 && x < MainPuzzleGame.longueur/2-100){
				currentMatriceOrder.set(1, -1);
				System.out.println("Matrice n�1 vide");
			} else if(x > MainPuzzleGame.longueur/2-100 && x < MainPuzzleGame.longueur/2){
				currentMatriceOrder.set(2, -1);
				System.out.println("Matrice n�2 vide");
			} else if(x > MainPuzzleGame.longueur/2 && x < MainPuzzleGame.longueur/2+100){
				currentMatriceOrder.set(3, -1);
				System.out.println("Matrice n�3 vide");
			} else if(x > MainPuzzleGame.longueur/2+100 && x < MainPuzzleGame.longueur/2+200){
				currentMatriceOrder.set(4, -1);
				System.out.println("Matrice n�4 vide");
			} else if(x > MainPuzzleGame.longueur/2+200 && x < MainPuzzleGame.longueur/2+300){
				currentMatriceOrder.set(5, -1);
				System.out.println("Matrice n�5 vide");
			}
		}		
	}
	
	public void fillMatricePart(int x){
		if (this.puzzle.getListeFragments().size() == 4){
			if(x > MainPuzzleGame.longueur/2-200 && x < MainPuzzleGame.longueur/2-100){
				placeElementInMatrice(0, -200);
			} else if(x > MainPuzzleGame.longueur/2-100 && x < MainPuzzleGame.longueur/2){
				placeElementInMatrice(1, -100);
			} else if(x > MainPuzzleGame.longueur/2 && x < MainPuzzleGame.longueur/2+100){
				placeElementInMatrice(2, 0);
			} else if(x > MainPuzzleGame.longueur/2+100 && x < MainPuzzleGame.longueur/2+200){
				placeElementInMatrice(3, 100);
			}
		} else if (this.puzzle.getListeFragments().size() == 5){
			if(x > MainPuzzleGame.longueur/2-250 && x < MainPuzzleGame.longueur/2-150){
				placeElementInMatrice(0, -250);
			} else if(x > MainPuzzleGame.longueur/2-150 && x < MainPuzzleGame.longueur/2-50){
				placeElementInMatrice(1, -150);
			} else if(x > MainPuzzleGame.longueur/2-50 && x < MainPuzzleGame.longueur/2+50){
				placeElementInMatrice(2, -50);
			} else if(x > MainPuzzleGame.longueur/2+50 && x < MainPuzzleGame.longueur/2+150){
				placeElementInMatrice(3, 50);
			} else if(x > MainPuzzleGame.longueur/2+150 && x < MainPuzzleGame.longueur/2+250){
				placeElementInMatrice(4, 150);
			}
		} else if (this.puzzle.getListeFragments().size() == 6){
			if(x > MainPuzzleGame.longueur/2-300 && x < MainPuzzleGame.longueur/2-200){
				placeElementInMatrice(0, -300);
			} else if(x > MainPuzzleGame.longueur/2-200 && x < MainPuzzleGame.longueur/2-100){
				placeElementInMatrice(1, -200);
			} else if(x > MainPuzzleGame.longueur/2-100 && x < MainPuzzleGame.longueur/2){
				placeElementInMatrice(2, -100);
			} else if(x > MainPuzzleGame.longueur/2 && x < MainPuzzleGame.longueur/2+100){
				placeElementInMatrice(3, 0);
			} else if(x > MainPuzzleGame.longueur/2+100 && x < MainPuzzleGame.longueur/2+200){
				placeElementInMatrice(4, 100);
			} else if(x > MainPuzzleGame.longueur/2+200 && x < MainPuzzleGame.longueur/2+300){
				placeElementInMatrice(5, 200);
			}
		}
	}
	
	public void placeElementInMatrice(int index, int pos){
		if (currentMatriceOrder.get(index) != -1){
			for (int i = 0; i < piecesImg.size(); i++){
				if(piecesImg.get(i).getCorrectPos() == currentMatriceOrder.get(index)){
					piecesImg.get(i).resetPosition();
				}
			}
		}
		piecesImg.get(draggedPieceNumber).setX(MainPuzzleGame.longueur/2+pos);
		piecesImg.get(draggedPieceNumber).setY(MainPuzzleGame.hauteur/2-50);
		currentMatriceOrder.set(index, piecesImg.get(draggedPieceNumber).getCorrectPos());
		PuzzleGame.jouerAudio("./Ressources/Sons/draggedOff.wav", -22.0f, false);
	}
	
	public static void jouerAudio(String son, float volumeReduced, boolean backgroundMusic){
		if(!gameMuted){
			try {
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(son));
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(volumeReduced); // Reduce volume by 10 decibels.
				if(backgroundMusic){				
					backgroundClip = clip;
					backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
					backgroundClip.start();
				} else {
					clip.start();
				}
				
			} catch (UnsupportedAudioFileException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void muteUnmuteGame(){
		if(gameMuted){
			backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
			backgroundClip.start();
			try {
				volumeImg = new Image("./Ressources/Images/volume_on.png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			backgroundClip.stop();
			try {
				volumeImg = new Image("./Ressources/Images/volume_off.png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		gameMuted = !gameMuted;
	}

	@Override
	public int getID() {
		return ID;
	}

}
