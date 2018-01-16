package slickGames.states;


import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.FontUtils;

import modeles.Question;
import modeles.Quiz;
import modeles.Reponse;
import slickGames.MainOddWordOutGame;
import slickGames.entite.EntiteReponse;

//import slickGames.FontUtils;

public class OddWordOutGame extends BasicGameState {
	
	public static int ID = 2;
	public static ArrayList<EntiteReponse> reponses = new ArrayList<EntiteReponse>();
	public Image flecheVerte;
	public Image flecheRouge;
	public EntiteReponse reponseSelected;
	public Quiz quiz;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
		ArrayList<Question> ListeQuestions = new ArrayList<Question>();
		Quiz quiz = new Quiz ("Test",ListeQuestions);
		this.quiz = quiz.convertirXMLToJava("FichiersDeConfig/slickGame.xml");
		ArrayList<Reponse> listeReponses = this.quiz.getListeQuestions().get(0).getListeReponses();
		int nombreReponses = listeReponses.size();
		System.out.println(listeReponses);
		for (int i = 0; i < nombreReponses; i++){
			String intitule = listeReponses.get(i).getIntitule();
			int max = MainOddWordOutGame.longueur-250;
			int min = 150;
			Random rand = new Random();;
			int randomNum = rand.nextInt((max - min) + 1) + min;
			reponses.add(new EntiteReponse(intitule, randomNum, -150+i*80));
		}
		reponseSelected = reponses.get(nombreReponses-1);
		
		flecheVerte = new Image("./Ressources/Images/flecheVerte1.png");
		flecheRouge = new Image("./Ressources/Images/flecheRouge1.png");
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
		
		for (int i = 0; i < reponses.size(); i++){
			reponseSelected = reponses.get(i);
			reponses.get(i).update(container, game, delta);
			
		}
			
	}
	
	public void keyPressed(int key, char c) {
		switch (key){
		case Input.KEY_LEFT:
			if(reponseSelected.getY() > 100){
				reponseSelected.setMovingByUser(true);
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
				reponseSelected.setMovingByUser(true);
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
				reponseSelected.setMovingByUser(false);
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
				reponseSelected.setMovingByUser(false);
				reponseSelected.setDirection(0);
				reponseSelected.setSpeedX(0);
			}	
			break;
		}
	}
	
	@Override
	public int getID() {
		return ID;
	}

}
