package slickGames.entite;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import slickGames.MainOddWordOutGame;
import slickGames.states.OddWordOutGame;

/**
 * Classe utilis�e dans le cadre du jeu de tri OddWordOutGame.
 * 
 * Permet d'instancier des �l�ments graphiques correspondants aux r�ponses d�filantes.
 * 
 * Variables globales :
 * 		float		x, y  				positions X et Y au sein de la fen�tre de jeu de l'�l�ment
 * 		int			width, height 	 	largeur et hauteur correspondant � la hitbox de l'�l�ment
 * 		int			direction 			direction d�sir�e par le joueur afin de trier l'�l�ment (vaut -1 pour gauche et 1 pour droite, 0 sinon)
 * 		double		speedX, speedY	 	vitesses de d�placement de l'�l�ment selon les axes X et Y 
 * 		double		deployDelay			temps d'apparition de l'�l�ment sur la fen�tre de jeu
 * 		boolean		correct				vaut true si la r�ponse est correcte vis-�-vis de la question pos�e au joueur, false sinon.
 * 		boolean		deployed 			vaut true si l'�l�ment a �t� affich� au sein de la fen�tre, false sinon.
 * 		long		pastTime 			stocke le temps pass� avant d�ploiement de l'�l�ment au sein de la fen�tre de jeu.
 * 		Image		sprite 				image/sprite de l'�l�ment
 * 		String		textReponse 		texte correspondant � la r�ponse � afficher
 *
 */
public class EntiteReponse {

	float x,y;
	int width,height,direction;
	double speedX,speedY,deployDelay;
	boolean correct,deployed;
	long pastTime = 0;
	Image sprite;
	String textReponse;
	
	/**
	 * Constructeur de la classe EntiteReponse
	 */
	public EntiteReponse(String textReponse, Boolean correct, float x, double fallingSpeed, double minDeployDelay) throws SlickException{
		this.textReponse = textReponse;
		this.x = x;
		this.direction = 0;
		//this.sprite = new Image("./Ressources/Images/rectangleReponse2.png");
		this.sprite = new Image("./Ressources/Images/rectReponse.png");
		this.speedY = fallingSpeed;
		this.correct = correct;
		this.deployed = false;
		Random rand = new Random();
		deployDelay = rand.nextInt(4)*0.25+minDeployDelay;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public int getDirection(){
		return direction;
	}
	
	public double getSpeedX(){
		return speedX;
	}
	
	public double  getSpeedY(){
		return speedY;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public Image getSprite(){
		return sprite;
	}
	
	public String getTextReponse(){
		return textReponse;
	}
	
	public boolean isDeployed(){
		return deployed;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setDirection(int dirX) {
		this.direction = dirX;
	}

	public void setSpeedX(double d) {
		this.speedX = d;
	}

	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}
	
	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}
	
	public void setTextReponse(String textReponse){
		this.textReponse = textReponse;
	}
	
	public void setDeployed(boolean deployed){
		this.deployed = deployed;
	}

	/**
	 * Fonction permettant d'actualiser les positions de l'�l�ment en fonction de ses vitesses de d�placement.
	 * 
	 * @param dt : temps �coul�
	 */
	public void move(int dt){
		x += speedX*dt;
		y += speedY*dt;	
	}
	
	/**
	 * Fonction permettant de supprimer l'�l�ment au sein du jeu.
	 */
	public void die(){	
		OddWordOutGame.reponses.remove(this);
	}
	
	/**
	 * Fonctionant g�rant les collisions avec les limites gauche, droite et bas de la fen�tre de jeu.
	 */
	public void checkForCollision(){	
		if(x+width-11 > MainOddWordOutGame.longueur){
			if(this.correct){
				OddWordOutGame.jouerAudio("./Ressources/Sons/succes1.wav", -18.0f, false);
				OddWordOutGame.score += 50;
				OddWordOutGame.chrono += 5000;
				OddWordOutGame.increaseFallingSpeed(0.003);
				OddWordOutGame.decreaseDeployDelay();
			} else {
				OddWordOutGame.jouerAudio("./Ressources/Sons/echec1.wav", -18.0f, false);
				OddWordOutGame.score -= 25;
				OddWordOutGame.chrono -= 25000;
				OddWordOutGame.increaseFallingSpeed(0.005);
			}
			die();
		}
		if(x < 0){
			if(!this.correct){
				OddWordOutGame.jouerAudio("./Ressources/Sons/succes1.wav", -18.0f, false);
				OddWordOutGame.score += 50;
				OddWordOutGame.chrono += 5000;
				OddWordOutGame.increaseFallingSpeed(0.003);
				OddWordOutGame.decreaseDeployDelay();
			} else {
				OddWordOutGame.jouerAudio("./Ressources/Sons/echec1.wav", -18.0f, false);
				OddWordOutGame.score -= 25;
				OddWordOutGame.chrono -= 25000;
				OddWordOutGame.increaseFallingSpeed(0.005);
			}
			die();
		}
		if(y+height > MainOddWordOutGame.hauteur){
			OddWordOutGame.jouerAudio("./Ressources/Sons/echec1.wav", -18.0f, false);
			OddWordOutGame.score -= 25;
			OddWordOutGame.chrono -= 20000;
			OddWordOutGame.increaseFallingSpeed(0.005);
			die();
		}		
	}
	
	/**
	 * Fonction appelant les �l�ments g�rant les �v�nements de collisions et de mouvement de l'entit�.
	 */
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		checkForCollision();
		move(delta);
	}
	
	/**
	 * Fonction g�rant l'initialisation de l'affichage de l'�l�ment.
	 * 
	 * @param delta : temps �coul�
	 */
	public boolean isReadyToDeploy(long delta) {
	    if(pastTime < deployDelay * 1000) {
	        pastTime += delta;
	        return false;
	    }else{
	        pastTime = 0;
	        return true;
	    }
	}
	
	/**
	 * Fonction g�rant l'affichage de l'entit�.
	 * 
	 * @param container : correspond � la fen�tre du jeu Slick2D
	 * @param game : correspond au jeu Slick2D en lui-m�me
	 * @param g : correspond au gestionnaire des �l�ments graphiques du jeu Slick2D
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
		width = g.getFont().getWidth(textReponse)+22;
		height = g.getFont().getHeight(textReponse)+15;
		sprite.draw(x-11, y-5, width, height);
		g.setColor(Color.white);
		g.drawString(textReponse,(float) x,(float) y);
		
	}
	
}
