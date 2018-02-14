package controleurs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import modeles.Accueil;
import modeles.FondEcran;
import modeles.Titre;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class ViewAccueilController implements Initializable{


	@FXML
	private Button buttonJouer;
	@FXML
	private AnchorPane anchor;
	@FXML
	private ImageView imageFDE;
	@FXML
	private ImageView imageTitre;
	@FXML
	private Label titreTexte;
	
	private String xml="";
	private boolean soloBloc=true;
	private int cmptChronologie=0;
	private String xmlChronologie="";
	private boolean son;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		//jouerAudio("././Ressources/Sons/Jouer.wav", -25.0f);
	}
	
	public void initData(String xml,boolean son) {
		this.son=son;

		Accueil accueil = new Accueil();
		File f =  new File(xml);
		if (f.exists()) {
			Accueil accXML = accueil.convertirXMLToJava(xml);
			Titre titre = accXML.getTitre();
			
			if (titre.getImageVsTexte().contains("Texte")) {
				imageTitre.setVisible(false);
				titreTexte.setText(titre.getTexte());
				 try {
					Font font = Font.loadFont(new FileInputStream(new File("././Ressources/Polices/" + titre.getPoliceTexte() + ".ttf")), 60);
					titreTexte.setFont(font);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				titreTexte.setStyle("-fx-text-fill:" + titre.getCouleurTexte().replace("0x", "#") + ";");
			} else {
				titreTexte.setVisible(false);
				
				//System.out.println("Image");
				imageTitre.setImage(ViewParametresPageExplicationController.chargerImage(titre.getLienImage()));
			}
			
			FondEcran fondEcran = accXML.getFond();
			
			if (fondEcran.getImageVsCouleur().contains("Image")) {
				anchor.setStyle("-fx-background-color:transparent;");
				
				//System.out.println(fondEcran.getLienImage());
				imageFDE.setImage(ViewParametresPageExplicationController.chargerImage(fondEcran.getLienImage()));
			} else {
				imageFDE.setVisible(false);
				
				//System.out.println("Couleur");
				anchor.setStyle("-fx-background-color:"  + fondEcran.getCouleur().replace("0x", "#") + ";");
			}
			
		} else {
			System.out.println("xml \"" + xml + "\" doesn't exist");
			imageTitre.setVisible(false);
			imageFDE.setVisible(false);
			
			anchor.setStyle("-fx-background-color:transparent;");
			titreTexte.setText("Nom du serious game");
			titreTexte.setFont(Font.font("Courier New Bold", 60));
			titreTexte.setStyle("-fx-text-fill:gray;");
		}
	}
	
	public void setXML(String xml) {
		this.xml=xml;
	}
	
	public void setChronologie(boolean soloBloc, int cmptChronologie, String xmlChronologie){
		this.soloBloc=soloBloc;
		this.cmptChronologie=cmptChronologie;
		this.xmlChronologie=xmlChronologie;
	}

	@FXML
	protected void ClickButtonJouer(ActionEvent event) throws IOException {
		if(soloBloc){
			Stage stage = (Stage) buttonJouer.getScene().getWindow();
			stage.close();
		} else {
			Stage stage = (Stage) buttonJouer.getScene().getWindow();
			Node node=JFxUtils.loadNextBloc(cmptChronologie, xmlChronologie, son);
			if (node!=null){
				stage.setScene(new Scene((Parent) node, 850, 650));
			} else {
				stage.close();
			}
		}
	}
	
	public static void jouerAudio(String son, float volumeReduced){
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(son));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(volumeReduced);
			clip.start();
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
}

	