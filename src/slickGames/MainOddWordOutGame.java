package slickGames;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import slickGames.states.OddWordOutGame;

/**
 * Classe permettant de lancer le jeu de tri via sa fonction main.
 * 
 * Cette derni�re h�rite de la classe StateBasedGame de Slick2D et permet de g�rer
 * un jeu sous Slick2D � l'aide d'�tats.
 * Ici, le seul �tat utilis� par cette classe est l'�tat de la classe OddWordOutGame 
 * qui repr�sente le jeu de tri en lui-m�me.
 *
 */
public class MainOddWordOutGame extends StateBasedGame{
	
	public static int longueur=850;
	public static int hauteur=650;
	private static String xml="FichiersDeConfig/slickGame.xml";
	private static boolean soloBloc=true;
	private static int cmptChronologie;
	public static String xmlChronologie;
	public static boolean son=true;
	public static int score=0;
	
	public static void main(String[] args) throws SlickException {
		
		AppGameContainer app = new AppGameContainer(new MainOddWordOutGame(),longueur, hauteur, false);
		app.setTargetFrameRate(60);
		app.setVSync(true);
		app.setShowFPS(false);
		app.start();
	
	}
	
	/**
	 * Fonction appelant le constructeur de classe issu de l'h�ritage
	 */
	public MainOddWordOutGame() {
		super("Chassez les intrus !");
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
	public void initData(String xml, boolean soloBloc, int cmptChronologie, String xmlChronologie, boolean son, int score){
		this.xml=xml;
		this.soloBloc=soloBloc;
		this.cmptChronologie=cmptChronologie;
		this.xmlChronologie=xmlChronologie;
		this.son=son;
		this.score=score;
	}


	/**
	 * Fonction, issue de l'h�ritage, permettant de g�rer les �tats du jeu.
	 * 
	 * Ici, nous n'avons que l'�tat correspondant au jeu de tri : instance d'OddWordOutGame.
	 * 
	 * On transmet aussi les param�tres li�s � la chronologie au jeu en lui-m�me.
	 */
	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		
		OddWordOutGame oddWordOutGame=new OddWordOutGame();
		oddWordOutGame.initData(xmlChronologie,xml,son,score,cmptChronologie);
		addState(oddWordOutGame);
			
		this.enterState(OddWordOutGame.ID);
	
	}
	
}

