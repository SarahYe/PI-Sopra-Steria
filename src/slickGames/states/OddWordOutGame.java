package slickGames.states;


import java.awt.Font;
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
import org.newdawn.slick.util.FontUtils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import modeles.Question;
import modeles.Quiz;
import modeles.Reponse;
import slickGames.MainOddWordOutGame;
import slickGames.entite.EntiteReponse;

//import slickGames.FontUtils;

public class OddWordOutGame extends BasicGameState {
	
	public static int ID = 2;
	public static ArrayList<EntiteReponse> reponses = new ArrayList<EntiteReponse>();
	public long previousTime = 0;
	public Image flecheVerte;
	public Image flecheRouge;
	public EntiteReponse reponseSelected;
	public Quiz quiz;
	public ArrayList<Reponse> listeReponses;
	public int previousReponse;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
		ArrayList<Question> ListeQuestions = new ArrayList<Question>();
		Quiz quiz = new Quiz ("",ListeQuestions);
		this.quiz = quiz.convertirXMLToJava("FichiersDeConfig/slickGame.xml");
		
		listeReponses = this.quiz.getListeQuestions().get(0).getListeReponses();
		Random rand = new Random();
		previousReponse = rand.nextInt(listeReponses.size());
		String intitule = listeReponses.get(previousReponse).getIntitule();
		Boolean correct = listeReponses.get(previousReponse).getCorrect();
		int max = MainOddWordOutGame.longueur-250;
		int min = 150;
		int xPos = rand.nextInt((max - min) + 1) + min;
		reponses.add(new EntiteReponse(intitule, correct, xPos));
		reponseSelected = reponses.get(0);
		
		flecheVerte = new Image("./Ressources/Images/flecheVerte1.png");
		flecheRouge = new Image("./Ressources/Images/flecheRouge1.png");
		
		OddWordOutGame.jouerAudio("./Ressources/Sons/musicJeuIntrus.wav", -18.0f);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		
		//fontConfirmText = FontUtils.loadCustomFont("PressStart2P.ttf",Font.PLAIN,20);
		
		g.setBackground(new Color(230, 242, 255));
		
		g.setColor(Color.black);
		for (int i = 0; i < reponses.size(); i++){
			reponses.get(i).render(container, game, g);
		}
		
		g.setColor(new Color(153, 202, 255));
		g.fillRect(0, 0, MainOddWordOutGame.longueur, 40);
		
		Image titre = new Image("./Ressources/Images/titreSeriousGame.png");
		g.drawImage(titre, (MainOddWordOutGame.longueur - titre.getWidth())/2, 0);
		
		g.setColor(Color.black);
		
		g.drawString("Score : 0 points", MainOddWordOutGame.longueur - g.getFont().getWidth("Score : 0 points") - 20, 10);
		
		Image personnage = new Image("./Ressources/Images/personnage1.png");
		personnage.draw(0, 45, 0.35f);
		
		Image bulleDialogue = new Image("./Ressources/Images/bulleDialogue.png");
		bulleDialogue.draw(personnage.getWidth()*35/100 - 20, 45, 1.0f);
			
		g.setColor(Color.white);
		drawStrings(quiz.getListeQuestions().get(0).getIntituleQuestion(), 145, 58, g);
		
		g.setColor(Color.red);
		g.fillRect(0, 40, 5, MainOddWordOutGame.hauteur);
		g.setColor(Color.green);
		g.fillRect(MainOddWordOutGame.longueur - 6, 40, 6, MainOddWordOutGame.hauteur);
				
		g.drawImage(flecheVerte, (MainOddWordOutGame.longueur/2)+10, MainOddWordOutGame.hauteur-100);	
		g.drawImage(flecheRouge, (MainOddWordOutGame.longueur/2)-flecheRouge.getWidth()-10, MainOddWordOutGame.hauteur-100);		
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
		
		long tmp = System.currentTimeMillis();
	    long customDelta = tmp - previousTime;
	    previousTime = tmp;
	    
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
					reponses.add(new EntiteReponse(intitule, correct, randomNum));
								
			    }
	    	} else {
	    		reponses.get(i).update(container, game, delta);
	    	}	
	    }    
			
	}
	
	public void keyPressed(int key, char c) {
		switch (key){
		case Input.KEY_LEFT:
			if(reponseSelected.getY() > 100){
				reponseSelected.setDirection(-1);
				reponseSelected.setSpeedX(-0.15);
			}
			try {
				flecheRouge = new Image("./Ressources/Images/flecheRouge2.png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case Input.KEY_RIGHT:
			if(reponseSelected.getY() > 100){
				reponseSelected.setDirection(1);
				reponseSelected.setSpeedX(0.15);
			}
			try {
				flecheVerte = new Image("./Ressources/Images/flecheVerte2.png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}
	
	public void keyReleased(int key, char c) {
		switch (key) {	
		case Input.KEY_LEFT:
			try {
				flecheRouge = new Image("./Ressources/Images/flecheRouge1.png");
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
				flecheVerte = new Image("./Ressources/Images/flecheVerte1.png");
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
	
	public static void jouerAudio(String son, float volumeReduced){
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(son));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(volumeReduced); // Reduce volume by 10 decibels.
			clip.start();
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public int getID() {
		return ID;
	}

}
