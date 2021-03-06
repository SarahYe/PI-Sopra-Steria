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

import controleurs.JFxUtils;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modeles.Puzzle;
import slickGames.MainOddWordOutGame;
import slickGames.MainPuzzleGame;
import slickGames.entite.PuzzlePieceImg;

/**
 * Classe du jeu de puzzle h�ritant de la classe BasicGameState issue de Slick2D.
 * 
 * Correspond � un �tat de jeu utilisable par les classes h�ritant de StateBasedGame.
 *
 * Cr�dits li�s aux musiques et SFX du jeu de puzzle :
 * Music by Eric Matyas
 * www.soundimage.org
 * 
 */
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
	public boolean exit_flag = false;
	
	public static int score;
	private static String xml="FichiersDeConfig/slickGame.xml";
	private static boolean soloBloc=true;
	private static int cmptChronologie;
	public static String xmlChronologie;
	public static boolean son=true;
	
	/**
	 * Fonction, issue de l'h�ritage, permettant d'initialiser les variables globales
	 * et de r�cup�rer l'ensemble du contenu pr�sent au sein du fichier XML correspondant au jeu.
	 */
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
		bandeTitre = new Image("./Ressources/Images/bandeTitre.png");
		titre = new Image("./Ressources/Images/SeriousSecurity.png");
		personnage = new Image("./Ressources/Images/perso.png");
		bulleDialogue = new Image("./Ressources/Images/bulleDialogue2.png");
		boutonIndices = new Image("./Ressources/Images/boutonIndice.png");
		flecheReset = new Image("./Ressources/Images/boutonReset.png");
		
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
	
	/**
	 * Fonction initialisant tous les param�tres utiles � la chronologie (encha�nement des
	 * blocs/modules/jeux/interfaces...)
	 * 
	 * 
	 * @param xml : chemin vers le fichier XML du jeu
	 * @param soloBloc : vaut true si bloc appel� seul, sinon false.
	 * @param cmptChronologie : indice correspondant � la position actuelle au sein de la chronologie
	 * @param xmlChronologie : chemin vers le fichier XML de chronologie des �v�nements
	 * @param son : vaut true si le son est activ�, sinon false.
	 * @param score : score global ou points cumul�s par l'utilisateur au cours des jeux pr�c�dents
	 */
	public void initData(String xml, String xml2, boolean son, int score, int cmptChronologie){
		this.xmlChronologie=xml;
		this.xml=xml2;
		this.score=score;
		this.gameMuted=!son;
		this.cmptChronologie=cmptChronologie;
	}

	/**
	 * Fonction, issue de l'h�ritage, g�rant l'affichage globale du jeu de puzzle et appel�e � chaque frame.
	 * 
	 * @param container : correspond � la fen�tre du jeu Slick2D
	 * @param game : correspond au jeu Slick2D en lui-m�me
	 * @param g : correspond au gestionnaire des �l�ments graphiques du jeu Slick2D
	 */
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
			
			String str1 = "Bien jou� !";
			Double score = puzzle.getScore_init() - mins*(60/puzzle.getDecr_sec())*puzzle.getDecr_pts() - ((int) secs/puzzle.getDecr_sec())*puzzle.getDecr_pts();
			if (score < puzzle.getScore_min()){
				score = puzzle.getScore_min();
			}
			this.score = score.intValue();
			String str2 = "Votre score : "+this.score;
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
	
	/**
	 * Fonction permettant de v�rifier si le joueur a correctement plac� l'ensemble des pi�ces de puzzle.
	 * 
	 * @return true en cas de r�ussite du joueur, false sinon.
	 */
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
	
	/**
	 * Fonction permettant d'afficher l'intitul� de la question au sein de la bulle de dialogue.
	 * Une fois le 68�me caract�re atteint, saute � la ligne suivante.
	 * 
	 * @param text : texte de l'intitul� de la question � afficher
	 * @param x : position X de l'affichage
	 * @param y : position Y de l'affichage
	 * @param g : gestionnaire des �l�ments graphiques d'un jeu Slick2D
	 */
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

	/**
	 * Fonction permettant de :
	 * 		- g�rer l'arr�t complet du jeu en cas de fin de jeu
	 * 		- appeler l'�v�nement/bloc/module suivant en cas de fin de jeu
	 * 		- g�rer le chronom�tre
	 * 		- mettre � jour l'ensemble des �l�ments affich�s
	 * 		- g�rer les clics de souris relatifs � l'activation/d�sactivation du son du jeu
	 */
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		chrono += delta;
		
		if(exit_flag){
			container.setForceExit(false);
			container.exit();
			
			if(Platform.isFxApplicationThread()){
	             Platform.runLater(new Runnable() {
	                 @Override public void run() {
	                	
	                	 Stage stage = new Stage();
	                	 System.out.println("compteur : "+cmptChronologie);
	                	 System.out.println("XML : "+xmlChronologie);
	                	 System.out.println("son : "+son);
	                	 System.out.println("score : "+score);
	                	 Node node=JFxUtils.loadNextBloc(cmptChronologie, xmlChronologie, son, score);
	         			if (node!=null){
	         				stage.setScene(new Scene((Parent) node, 850, 650));
	         				stage.show();
	         			} else {
	        				System.out.println("node = null");
	        				stage.close();
	        			}
	             		
	                 }
	             });
	             
			}
		}
		
		if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
	    	if(container.getInput().getMouseX() > 5 && container.getInput().getMouseX() < 30 && container.getInput().getMouseY() > 5 && container.getInput().getMouseY() < 30){
	    		muteUnmuteGame();
	    	}
	    }
		
	}
	
	/**
	 * Fonction, issue de l'h�ritage, g�rant les inputs provenant de la souris.
	 */
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
    
    /**
	 * Fonction, issue de l'h�ritage, g�rant les �v�nements relatifs aux d�placements de la souris.
	 */
	@Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        if (draggedPieceNumber != -1) {
        	piecesImg.get(draggedPieceNumber).setX(piecesImg.get(draggedPieceNumber).getX()+newx-oldx);
        	piecesImg.get(draggedPieceNumber).setY(piecesImg.get(draggedPieceNumber).getY()+newy-oldy);
        }
    }
	
	/**
	 * Fonction, issue de l'h�ritage, g�rant les inputs clavier.
	 */
	public void keyPressed(int key, char c) {
		switch (key){
		case Input.KEY_ENTER:
			if(gameFinished){
				//System.exit(0);
				exit_flag = true;
			}
			break;
		case Input.KEY_M:
			muteUnmuteGame();
			break;
		}
	}
	
	/**
	 * Fonction mettant � jour les informations relatives � la matrice du puzzle � compl�ter par le joueur
	 * en cas de retrait d'une pi�ce de puzzle.
	 * 
	 * @param x : position X de l'�l�ment retir�
	 */
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
	
	/**
	 * Fonction g�rant l'ensemble des cas de placement de pi�ces de puzzle au sein de la matrice � compl�ter par le joueur
	 * 
	 * @param x : position X de la souris � la fin du drag and drop.
	 */
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
	
	/**
	 * Fonction permettant de placer correctement une pi�ce de puzzle � la fin d'un drag and drop de cette derni�re au sein de la matrice � compl�ter par le joueur.
	 * Si un �l�ment est d�j� pr�sent � l'endroit d�sir�, r�initialise sa position et le nouvel �l�ment prend sa place au sein de la matrice.
	 * 
	 * @param index : indice correspondant � la position au sein de la matrice o� l'�l�ment est ajout�
	 * @param pos : position X o� l'�l�ment doit �tre plac�
	 */
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
	
	/**
	 * Fonction permettant de lire un �l�ment audio.
	 * 
	 * @param son : chemin vers le fichier audio
	 * @param volumeReduced : r�duction sonore en nombre de d�cibels
	 * @param backgroundMusic : vaut true dans le cas d'une musique de fond n�cessitant une boucle, vaut false sinon.
	 */
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
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Fonction permettant d'activer ou d�sactiver le son du jeu selon son �tat ant�rieur.
	 */
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
