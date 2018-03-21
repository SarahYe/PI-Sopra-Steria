package slickGames.entite;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Classe utilis�e dans le cadre du jeu de puzzle PuzzleGame.
 * 
 * Permet d'instancier des �l�ments graphiques correspondants aux r�ponses (images).
 * 
 * Variables globales :
 * 		float		x, y  				positions X et Y au sein de la fen�tre de jeu de l'image
 * 		float		originX, originY	positions X et Y de l'image � l'initialisation
 * 		int 		correctPos			indice d�crivant la position attendue de l'image afin de r�ussir le puzzle
 * 		int			width, height 	 	largeur et hauteur correspondant � la hitbox de l'image
 * 		double		speedX, speedY	 	vitesses de d�placement de l'�l�ment selon les axes X et Y 
 * 		Image		sprite 				image/sprite de l'�l�ment
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
	 * Fonction permettant d'actualiser les positions de l'�l�ment en fonction de ses vitesses de d�placement.
	 * 
	 * @param dt : temps �coul�
	 */
	public void move(int dt){
		x += speedX*dt;
		y += speedY*dt;	
	}
	
	/**
	 * Fonction appelant les �l�ments g�rant les �v�nements de mouvement de l'entit�.
	 */
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		move(delta);
	}
	
	/**
	 * Fonction g�rant l'affichage de l'entit�.
	 * 
	 * @param container : correspond � la fen�tre du jeu Slick2D
	 * @param game : correspond au jeu Slick2D en lui-m�me
	 * @param g : correspond au gestionnaire des �l�ments graphiques du jeu Slick2D
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
		sprite.draw(x, y, width, height);
		
	}
	
	/**
	 * Fonction permettant de r�initialiser les positions X et Y de l'entit�.
	 */
	public void resetPosition(){
		x = originX;
		y = originY;
	}
	
}
