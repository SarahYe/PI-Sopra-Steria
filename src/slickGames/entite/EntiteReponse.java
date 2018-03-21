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
 * Classe utilisée dans le cadre du jeu de tri OddWordOutGame.
 * 
 * Permet d'instancier des éléments graphiques correspondants aux réponses défilantes.
 * 
 * Variables globales :
 * 		float		x, y  				positions X et Y au sein de la fenêtre de jeu de l'élément
 * 		int			width, height 	 	largeur et hauteur correspondant à la hitbox de l'élément
 * 		int			direction 			direction désirée par le joueur afin de trier l'élément (vaut -1 pour gauche et 1 pour droite, 0 sinon)
 * 		double		speedX, speedY	 	vitesses de déplacement de l'élément selon les axes X et Y 
 * 		double		deployDelay			temps d'apparition de l'élément sur la fenêtre de jeu
 * 		boolean		correct				vaut true si la réponse est correcte vis-à-vis de la question posée au joueur, false sinon.
 * 		boolean		deployed 			vaut true si l'élément a été affiché au sein de la fenêtre, false sinon.
 * 		long		pastTime 			stocke le temps passé avant déploiement de l'élément au sein de la fenêtre de jeu.
 * 		Image		sprite 				image/sprite de l'élément
 * 		String		textReponse 		texte correspondant à la réponse à afficher
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
	 * Fonction permettant d'actualiser les positions de l'élément en fonction de ses vitesses de déplacement.
	 * 
	 * @param dt : temps écoulé
	 */
	public void move(int dt){
		x += speedX*dt;
		y += speedY*dt;	
	}
	
	/**
	 * Fonction permettant de supprimer l'élément au sein du jeu.
	 */
	public void die(){	
		OddWordOutGame.reponses.remove(this);
	}
	
	/**
	 * Fonctionant gérant les collisions avec les limites gauche, droite et bas de la fenêtre de jeu.
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
	 * Fonction appelant les éléments gérant les évènements de collisions et de mouvement de l'entité.
	 */
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		checkForCollision();
		move(delta);
	}
	
	/**
	 * Fonction gérant l'initialisation de l'affichage de l'élément.
	 * 
	 * @param delta : temps écoulé
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
	 * Fonction gérant l'affichage de l'entité.
	 * 
	 * @param container : correspond à la fenêtre du jeu Slick2D
	 * @param game : correspond au jeu Slick2D en lui-même
	 * @param g : correspond au gestionnaire des éléments graphiques du jeu Slick2D
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
		width = g.getFont().getWidth(textReponse)+22;
		height = g.getFont().getHeight(textReponse)+15;
		sprite.draw(x-11, y-5, width, height);
		g.setColor(Color.white);
		g.drawString(textReponse,(float) x,(float) y);
		
	}
	
}
