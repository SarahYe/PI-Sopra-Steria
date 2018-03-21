package slickGames.states;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.*;

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
import modeles.Question;
import modeles.Quiz;
import modeles.Reponse;
import slickGames.MainOddWordOutGame;
import slickGames.entite.EntiteReponse;


/**
 * Classe du jeu de tri héritant de la classe BasicGameState issue de Slick2D.
 * 
 * Correspond à un état de jeu utilisable par les classes héritant de StateBasedGame.
 *
 * Crédits liés aux musiques et SFX du jeu de tri :
 * Music by Eric Matyas
 * www.soundimage.org
 * 
 */
public class OddWordOutGame extends BasicGameState {
	
	public static int ID = 2;
	public static ArrayList<EntiteReponse> reponses = new ArrayList<EntiteReponse>();
	public static int score = 0;
	public static int posYRedArrow = 120;
	public static int posYGreenArrow = 120;
	public static double fallingSpeed = 0.04;
	public static double minDeployDelay = 1.75;
	public static double sidesSpeed = 2;
	public static long chrono = 45000L;
	public static Clip backgroundClip;
	public static boolean gameFinished = false;
	public static boolean gameMuted = false;
	public long previousTime = 0;
	public Image flecheVerte;
	public Image flecheRouge;
	public Image volumeImg;
	public EntiteReponse reponseSelected;
	public Quiz quiz;
	public ArrayList<Reponse> listeReponses;
	public int previousReponse;
	public boolean exit_flag = false;
	
	private static String xml="FichiersDeConfig/slickGame.xml";
	private static boolean soloBloc=true;
	private static int cmptChronologie;
	public static String xmlChronologie;
	public static boolean son=true;

	/**
	 * Fonction, issue de l'héritage, permettant d'initialiser les variables globales
	 * et de récupérer l'ensemble du contenu présent au sein du fichier XML correspondant au jeu.
	 */
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
		
		ArrayList<Question> ListeQuestions = new ArrayList<Question>();
		Quiz quiz = new Quiz ("",ListeQuestions);
		this.quiz = quiz.convertirXMLToJava(xml);
		
		listeReponses = this.quiz.getListeQuestions().get(0).getListeReponses();
		Random rand = new Random();
		previousReponse = rand.nextInt(listeReponses.size());
		String intitule = listeReponses.get(previousReponse).getIntitule();
		Boolean correct = listeReponses.get(previousReponse).getCorrect();
		int max = MainOddWordOutGame.longueur-250;
		int min = 150;
		int xPos = rand.nextInt((max - min) + 1) + min;
		reponses.add(new EntiteReponse(intitule, correct, xPos, fallingSpeed, minDeployDelay));
		reponseSelected = reponses.get(0);
		
		flecheVerte = new Image("./Ressources/Images/flecheVerte.png");
		flecheRouge = new Image("./Ressources/Images/flecheRouge.png");
		
		if(gameMuted){
			volumeImg = new Image("./Ressources/Images/volume_off.png");
		} else {
			volumeImg = new Image("./Ressources/Images/volume_on.png");
		}
		
		OddWordOutGame.jouerAudio("./Ressources/Sons/musicJeuIntrus.wav", -18.0f, true);
	}
	
	/**
	 * Fonction initialisant tous les paramètres utiles à la chronologie (enchaînement des
	 * blocs/modules/jeux/interfaces...)
	 * 
	 * 
	 * @param xml : chemin vers le fichier XML du jeu
	 * @param soloBloc : vaut true si bloc appelé seul, sinon false.
	 * @param cmptChronologie : indice correspondant à la position actuelle au sein de la chronologie
	 * @param xmlChronologie : chemin vers le fichier XML de chronologie des évènements
	 * @param son : vaut true si le son est activé, sinon false.
	 * @param score : score global ou points cumulés par l'utilisateur au cours des jeux précédents
	 */
	public void initData(String xml, String xml2, boolean son, int score, int cmptChronologie){
		this.xmlChronologie=xml;
		this.xml=xml2;
		this.score=score;
		this.gameMuted=!son;
		this.cmptChronologie=cmptChronologie;
	}

	/**
	 * Fonction, issue de l'héritage, gérant l'affichage globale du jeu de tri et appelée à chaque frame.
	 * 
	 * @param container : correspond à la fenêtre du jeu Slick2D
	 * @param game : correspond au jeu Slick2D en lui-même
	 * @param g : correspond au gestionnaire des éléments graphiques du jeu Slick2D
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
		g.setBackground(new Color(115, 115, 115));
		
		Image bandeVRED = new Image("./Ressources/Images/bandeVRED.png");
		bandeVRED.draw(0, 45, 107, 605);
		Image bandeVGREEN = new Image("./Ressources/Images/bandeVGREEN.png");
		bandeVGREEN.draw(MainOddWordOutGame.longueur-107, 45, 107, 605);
		
		g.setColor(Color.black);
		if (chrono > 0){
			for (int i = 0; i < reponses.size(); i++){
				reponses.get(i).render(container, game, g);
			}
		}
		
		Image bandeTitre = new Image("./Ressources/Images/bandeTitre.png");
		bandeTitre.draw(0, 0, MainOddWordOutGame.longueur, 45);
		
		Image titre = new Image("./Ressources/Images/SeriousSecurity.png");		
		titre.draw((MainOddWordOutGame.longueur - titre.getWidth()*0.15f)/2, -8, 0.15f);
		
		g.setColor(Color.black);
		g.drawString("Score : "+score+" points", MainOddWordOutGame.longueur - g.getFont().getWidth("Score : "+score+" points") - 20, 10);
		
		Image personnage = new Image("./Ressources/Images/perso.png");
		
		Image bulleDialogue = new Image("./Ressources/Images/bulleDialogue2.png");
		bulleDialogue.draw(personnage.getWidth()*25/100 - 20, 50, 1.0f);
		
		personnage.draw(30, 65, 0.3f);
			
		drawStrings(quiz.getListeQuestions().get(0).getIntituleQuestion(), 145, 63, g);	
		
		flecheVerte.draw((MainOddWordOutGame.longueur/2)+15, MainOddWordOutGame.hauteur-posYGreenArrow, 0.65f);
		flecheRouge.draw((MainOddWordOutGame.longueur/2)-15-flecheRouge.getWidth()*65/100, MainOddWordOutGame.hauteur-posYRedArrow, 0.65f);
		
		volumeImg.draw(5, 5, 25, 25);
		
		int mins = (int) chrono / (60*1000);
	    int remainder = (int) chrono/1000 - mins * 60;
	    int secs = remainder;
	    if(secs <0){ secs = 0; }
	    String timer = mins+" : ";
	    if (secs >=0 && secs <10) { timer += "0"; }
	    timer += secs;
	    g.setColor(Color.white);
		g.drawString(timer, (MainOddWordOutGame.longueur-g.getFont().getWidth(timer))/2, MainOddWordOutGame.hauteur-40);
	
		if(chrono < 0){
			container.pause();
			backgroundClip.close();
			gameFinished = true;
			
			Image img = new Image("./Ressources/Images/rectReponse.png");
			
			String str1 = "Game Over";
			String str2 = "Votre score : "+score;
			String str3 = "PRESS ENTER TO EXIT";
			
			float middleXstr1 = (MainOddWordOutGame.longueur-g.getFont().getWidth(str1))/2;
			float middleYstr1 = (MainOddWordOutGame.hauteur-g.getFont().getHeight(str1))/2-50;
			float middleXstr2 = (MainOddWordOutGame.longueur-g.getFont().getWidth(str2))/2;
			float middleYstr2 = (MainOddWordOutGame.hauteur-g.getFont().getHeight(str2))/2;
			float middleXstr3 = (MainOddWordOutGame.longueur-g.getFont().getWidth(str3))/2;
			float middleYstr3 = (MainOddWordOutGame.hauteur-g.getFont().getHeight(str3))/2+30;
			img.draw(middleXstr2-35, middleXstr2-20-32, g.getFont().getWidth(str2)+70, g.getFont().getHeight(str2)+80);
			g.setColor(Color.white);
			g.drawString(str1, middleXstr1, middleYstr1);
			if(score > 0){
				g.setColor(Color.green);
			} else {
				g.setColor(Color.red);
			}
			g.drawString(str2, middleXstr2, middleYstr2);
			g.setColor(Color.white);
			g.drawString(str3, middleXstr3, middleYstr3);		
		}
		
	}
	
	/**
	 * Fonction permettant d'afficher l'intitulé de la question au sein de la bulle de dialogue.
	 * Une fois le 68ème caractère atteint, saute à la ligne suivante.
	 * 
	 * @param text : texte de l'intitulé de la question à afficher
	 * @param x : position X de l'affichage
	 * @param y : position Y de l'affichage
	 * @param g : gestionnaire des éléments graphiques d'un jeu Slick2D
	 */
	public static void drawStrings(String text, int x, int y, Graphics g)
    {
		List<String> parts = new ArrayList<>();

	    int length = text.length();
	    int size = 68;
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
	 * 		- gérer l'arrêt complet du jeu en cas de fin de jeu
	 * 		- appeler l'évènement/bloc/module suivant en cas de fin de jeu
	 * 		- gérer le chronomètre
	 * 		- mettre à jour l'ensemble des éléments affichés
	 * 		- gérer les clics de souris relatifs à l'activation/désactivation du son du jeu
	 */
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
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
		
		long tmp = System.currentTimeMillis();
	    long customDelta = tmp - previousTime;
	    previousTime = tmp;
	    
	    chrono -= delta;
	    
	    if(reponses.size() > 0){
	    	reponseSelected = reponses.get(0);
	    }    
		
	    for (int i = 0; i < reponses.size(); i++){
	    	if(!reponses.get(i).isDeployed()){
	    		if (reponses.get(i).isReadyToDeploy(customDelta)){	    	
					Random rand = new Random();
					int randomReponse = rand.nextInt(listeReponses.size());
					while(randomReponse == previousReponse){
						randomReponse = rand.nextInt(listeReponses.size());
					}
					
					previousReponse = randomReponse;
						
					reponses.get(i).setY(50);
				    reponses.get(i).setDeployed(true);			    	
				    	
				    reponses.get(i).update(container, game, delta);
						
					String intitule = listeReponses.get(randomReponse).getIntitule();
					Boolean correct = listeReponses.get(randomReponse).getCorrect();
					int max = MainOddWordOutGame.longueur-250;
					int min = 150;
					int randomNum = rand.nextInt((max - min) + 1) + min;
					reponses.add(new EntiteReponse(intitule, correct, randomNum, fallingSpeed, minDeployDelay));
								
			    }
	    	} else {
	    		reponses.get(i).update(container, game, delta);
	    	}	
	    }
	    
	    if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
	    	if(container.getInput().getMouseX() > 5 && container.getInput().getMouseX() < 30 && container.getInput().getMouseY() > 5 && container.getInput().getMouseY() < 30){
	    		muteUnmuteGame();
	    	}
	    }
			
	}
	
	/**
	 * Fonction, issue de l'héritage, gérant les inputs clavier.
	 */
	public void keyPressed(int key, char c) {
		switch (key){
		case Input.KEY_LEFT:
			if(reponseSelected.getY() > 100){
				reponseSelected.setDirection(-1);
				reponseSelected.setSpeedX(-sidesSpeed);
			}
			try {
				flecheRouge = new Image("./Ressources/Images/flecheRougeP.png");
				posYRedArrow = 110;
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case Input.KEY_RIGHT:
			if(reponseSelected.getY() > 100){
				reponseSelected.setDirection(1);
				reponseSelected.setSpeedX(sidesSpeed);
			}
			try {
				flecheVerte = new Image("./Ressources/Images/flecheVerteP.png");
				posYGreenArrow = 110;
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
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
	 * Seconde fonction, issue de l'héritage, gérant les inputs clavier.
	 */
	public void keyReleased(int key, char c) {
		switch (key) {	
		case Input.KEY_LEFT:
			try {
				flecheRouge = new Image("./Ressources/Images/flecheRouge.png");
				posYRedArrow = 120;
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(reponseSelected.getDirection() == -1){
				reponseSelected.setDirection(0);
				reponseSelected.setSpeedX(0);
			}	
			break;
		case Input.KEY_RIGHT:
			try {
				flecheVerte = new Image("./Ressources/Images/flecheVerte.png");
				posYGreenArrow = 120;
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(reponseSelected.getDirection() == 1){
				reponseSelected.setDirection(0);
				reponseSelected.setSpeedX(0);
			}	
			break;
		}
	}
	
	/**
	 * Fonction permettant de lire un élément audio.
	 * 
	 * @param son : chemin vers le fichier audio
	 * @param volumeReduced : réduction sonore en nombre de décibels
	 * @param backgroundMusic : vaut true dans le cas d'une musique de fond nécessitant une boucle, vaut false sinon.
	 */
	public static void jouerAudio(String son, float volumeReduced, boolean backgroundMusic){
		if(!gameMuted){
			try {
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(son));
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(volumeReduced);
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
	 * Fonction permettant d'activer ou désactiver le son du jeu selon son état antérieur.
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
	
	/**
	 * Fonction permettant d'augmenter la vitesse de chute des réponses du jeu.
	 * 
	 * @param speedAdded : vitesse ajoutée aux éléments.
	 */
	public static void increaseFallingSpeed(double speedAdded){
		for (int i = 0; i < reponses.size(); i++){
			reponses.get(i).setSpeedY((float) (reponses.get(i).getSpeedY()+speedAdded));		
		}
		fallingSpeed += speedAdded;	
	}
	
	/**
	 * Fonction permettant de réduire le délai minimal d'apparition des réponses au sein de la fenêtre de jeu.
	 */
	public static void decreaseDeployDelay(){
		if(minDeployDelay > 0.3){
			minDeployDelay -= 0.025;
		}
	}
	
	@Override
	public int getID() {
		return ID;
	}

}
