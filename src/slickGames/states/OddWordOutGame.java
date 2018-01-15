package slickGames.states;


import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.FontUtils;

import slickGames.MainOddWordOutGame;

//import slickGames.FontUtils;

public class OddWordOutGame extends BasicGameState {
	
	public static int ID = 2;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		
		//fontConfirmText = FontUtils.loadCustomFont("PressStart2P.ttf",Font.PLAIN,20);
		
		g.setBackground(new Color(230, 242, 255));
		g.setColor(new Color(153, 202, 255));
		g.fillRect(0, 0, MainOddWordOutGame.longueur, 40);
		
		Image titre = new Image("./Ressources/Images/titreSeriousGame.png");
		g.drawImage(titre, (MainOddWordOutGame.longueur - titre.getWidth())/2, 0);
		
		g.setColor(Color.black);
		//g.drawString("Muffin Game !", (MainOddWordOutGame.longueur - g.getFont().getWidth("Muffin Game !"))/2, 10);
		g.drawString("Score : 0 points", MainOddWordOutGame.longueur - g.getFont().getWidth("Score : 0 points") - 20, 10);
		
		Image personnage = new Image("./Ressources/Images/personnage1.png");
		personnage.draw(0, 45, 0.35f);
		
		Image bulleDialogue = new Image("./Ressources/Images/bulleDialogue.png");
		bulleDialogue.draw(personnage.getWidth()*35/100 - 20, 45, 1.0f);
		
		g.setColor(Color.white);
		g.drawString("Jusqu'à combien de degrés peuvent monter les fumées d'un incendie ?", 145, 60);
		
		//System.out.println("Longueur maximale d'une question : " + "Jusqu'à combien de degrés peuvent monter les fumées d'un incendie ?".length() + " length");
		
		g.setColor(Color.red);
		g.fillRect(0, 40, 5, MainOddWordOutGame.hauteur);
		g.setColor(Color.green);
		g.fillRect(MainOddWordOutGame.longueur - 6, 40, 6, MainOddWordOutGame.hauteur);
		
		Image flecheVerte1 = new Image("./Ressources/Images/flecheVerte1.png");
		g.drawImage(flecheVerte1, (MainOddWordOutGame.longueur/2)+10, MainOddWordOutGame.hauteur-100);
		Image flecheRouge1 = new Image("./Ressources/Images/flecheRouge1.png");
		g.drawImage(flecheRouge1, (MainOddWordOutGame.longueur/2)-flecheRouge1.getWidth()-10, MainOddWordOutGame.hauteur-100);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(int key, char c) {

		/*
		 * super.keyPressed(key, c);
		//time=System.currentTimeMillis();
		switch (key) {
		//case Input.KEY_NUMPAD2:
		case Input.KEY_DOWN:
			if (selection < items.size() - 1)
				selection++;
			else
				selection = 0;
			if(selection>=MAX_ITEMS_VISIBLE){
				decalage=selection-MAX_ITEMS_VISIBLE+1;
			}else decalage=0;
			onOptionItemFocusedChanged(selection);
			break;
		//case Input.KEY_NUMPAD8:
		case Input.KEY_UP:
			if (selection > 0)
				selection--;
			else
				selection = items.size() - 1;
			if(selection>=MAX_ITEMS_VISIBLE){
				decalage=selection-MAX_ITEMS_VISIBLE+1;
			}else decalage=0;
			onOptionItemFocusedChanged(selection);
			break;
		case Input.KEY_ENTER:
			onOptionItemSelected(selection);
			break;

		case Input.KEY_ESCAPE:
			//exit();
			break;
		}
		 */
	}
	
	@Override
	public int getID() {
		return ID;
	}

}
