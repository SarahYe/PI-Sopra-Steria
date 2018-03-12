package slickGames.states;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.sound.sampled.*;

import org.newdawn.slick.AppGameContainer;
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
import main.MainChronologie;
import modeles.Question;
import modeles.Quiz;
import modeles.Reponse;
import slickGames.MainOddWordOutGame;
import slickGames.MainPuzzleGame;
import slickGames.entite.EntiteReponse;

//import slickGames.FontUtils;

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
	
	public String xml;

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
	
	public void initData(String xml, boolean son, int score){
		this.xml=xml;
		this.score=score;
		this.gameMuted=!son;
		//muteUnmuteGame();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		
		//fontConfirmText = FontUtils.loadCustomFont("PressStart2P.ttf",Font.PLAIN,20);
				
		//g.setBackground(new Color(230, 242, 255));
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
		
		/*Image fond3 = new Image("./Ressources/Images/fond3.png");
		Image fond2 = new Image("./Ressources/Images/fond2.png");
		fond3.draw(0, 45, 850, 605);
		fond2.draw(0, 45, 850, 605);*/	
		
		//g.setColor(new Color(153, 202, 255));
		//g.fillRect(0, 0, MainOddWordOutGame.longueur, 40);
		Image bandeTitre = new Image("./Ressources/Images/bandeTitre.png");
		bandeTitre.draw(0, 0, MainOddWordOutGame.longueur, 45);
		
		//Image titre = new Image("./Ressources/Images/titreSeriousGame.png");
		Image titre = new Image("./Ressources/Images/SeriousSecurity.png");		
		//g.drawImage(titre, (MainOddWordOutGame.longueur - titre.getWidth())/2, 0);
		titre.draw((MainOddWordOutGame.longueur - titre.getWidth()*0.15f)/2, -8, 0.15f);
		
		g.setColor(Color.black);
		g.drawString("Score : "+score+" points", MainOddWordOutGame.longueur - g.getFont().getWidth("Score : "+score+" points") - 20, 10);
		
		//Image personnage = new Image("./Ressources/Images/personnage1.png");
		Image personnage = new Image("./Ressources/Images/perso.png");
		
		//Image bulleDialogue = new Image("./Ressources/Images/bulleDialogue.png");
		Image bulleDialogue = new Image("./Ressources/Images/bulleDialogue2.png");
		bulleDialogue.draw(personnage.getWidth()*25/100 - 20, 50, 1.0f);
		
		personnage.draw(30, 65, 0.3f);
			
		drawStrings(quiz.getListeQuestions().get(0).getIntituleQuestion(), 145, 63, g);	
		
		//g.setColor(Color.red);
		//g.fillRect(0, 40, 5, MainOddWordOutGame.hauteur);
		//g.setColor(Color.green);
		//g.fillRect(MainOddWordOutGame.longueur - 6, 40, 6, MainOddWordOutGame.hauteur);
				
		flecheVerte.draw((MainOddWordOutGame.longueur/2)+15, MainOddWordOutGame.hauteur-posYGreenArrow, 0.65f);
		flecheRouge.draw((MainOddWordOutGame.longueur/2)-15-flecheRouge.getWidth()*65/100, MainOddWordOutGame.hauteur-posYRedArrow, 0.65f);
		//g.drawImage(flecheVerte, (MainOddWordOutGame.longueur/2)+10, MainOddWordOutGame.hauteur-100);	
		//g.drawImage(flecheRouge, (MainOddWordOutGame.longueur/2)-flecheRouge.getWidth()-10, MainOddWordOutGame.hauteur-100);		
	
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
			
			//Image img = new Image("./Ressources/Images/rectangleReponse2.png");
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

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		Input input = container.getInput();
        if(input.isKeyPressed( Input.KEY_SPACE ))
        {
            game.getCurrentState().init(container, game);
            game.enterState(game.getCurrentStateID());

        }
		
		if(exit_flag){
			//game.getCurrentState().leave(container, game);
			container.setForceExit(false);
			container.exit();
			
			System.out.println("close demandé");
			
			//game.enterState(PuzzleGame.ID);
			
			/*System.out.println("");
			Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
			Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
			System.out.println(threadArray.length);
			for(int i=0; i < threadArray.length; i++){
				System.out.println(threadArray[i]);
			}
			System.out.println("");*/
			
			//MainPuzzleGame.main(null);
			
	             Platform.runLater(new Runnable() {
	                 @Override public void run() {
	                	
	                	 JFxUtils.loadTest();
	             		
	                 }
	             });
	           
	             //container.setForceExit(true);
	             //container.exit();
	    
	         
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
	
	public static void increaseFallingSpeed(double speedAdded){
		for (int i = 0; i < reponses.size(); i++){
			reponses.get(i).setSpeedY((float) (reponses.get(i).getSpeedY()+speedAdded));		
		}
		fallingSpeed += speedAdded;	
	}
	
	/*public static void increaseSidesSpeed(){
		sidesSpeed += 0.005;	
	}*/
	
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
