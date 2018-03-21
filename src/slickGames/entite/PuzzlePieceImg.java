package slickGames.entite;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Classe utilisée dans le cadre du jeu de puzzle PuzzleGame.
 * 
 * Permet d'instancier des éléments graphiques correspondants aux réponses (images).
 * 
 * Variables globales :
 * 		float		x, y  				positions X et Y au sein de la fenêtre de jeu de l'image
 * 		float		originX, originY	positions X et Y de l'image à l'initialisation
 * 		int 		correctPos			indice décrivant la position attendue de l'image afin de réussir le puzzle
 * 		int			width, height 	 	largeur et hauteur correspondant à la hitbox de l'image
 * 		double		speedX, speedY	 	vitesses de déplacement de l'élément selon les axes X et Y 
 * 		Image		sprite 				image/sprite de l'élément
 *
 */
public class PuzzlePieceImg {

	float x,y,originX,originY;
	int correctPos;
	int width = 100;
	int height = 100;
	double speedX,speedY;
	Image sprite;
	
	/**
	 * Constructeur de la classe PuzzlePieceImg
	 */
	public PuzzlePieceImg(int correctPos, Image sprite, float x, float y) throws SlickException{
		
		this.correctPos = correctPos;
		this.sprite = sprite;
		this.x = x;
		this.y = y;
		this.originX = x;
		this.originY = y;
		
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
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
	
	public int getCorrectPos(){
		return correctPos;
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
	
	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}
	
	public void setCorrectPos(int correctPos) {
		this.correctPos = correctPos;
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
	 * Fonction appelant les éléments gérant les évènements de mouvement de l'entité.
	 */
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		move(delta);
	}
	
	/**
	 * Fonction gérant l'affichage de l'entité.
	 * 
	 * @param container : correspond à la fenêtre du jeu Slick2D
	 * @param game : correspond au jeu Slick2D en lui-même
	 * @param g : correspond au gestionnaire des éléments graphiques du jeu Slick2D
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
		sprite.draw(x, y, width, height);
		
	}
	
	/**
	 * Fonction permettant de réinitialiser les positions X et Y de l'entité.
	 */
	public void resetPosition(){
		x = originX;
		y = originY;
	}
	
}
