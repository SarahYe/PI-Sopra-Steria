package controleurs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modeles.Fouille;
import modeles.Instruction;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class ViewFouilleController implements Initializable{
	
	private String xml="";
	private static boolean soloBloc=true;
	private static int cmptChronologie=0;
	private static String xmlChronologie="";
	private static boolean son;
	private static int score;
	
	private static Fouille fouille;
	private static ArrayList<String> intitules= new ArrayList<String>();
	private static int cmpt=0;
	
	@FXML
	AnchorPane AP_Game,AP_Inventory;
	@FXML
	ImageView imageFond,IV_IconeSon;
	@FXML
	Label LB_Instruction,LB_Score;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(son)
			sonOn();
		else
			sonOff();
		
	}
	
	public void initData() {
		
		try{
			JAXBContext jc = JAXBContext.newInstance(modeles.Fouille.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			//unmarshaller.setValidating(true);
			fouille = (Fouille) unmarshaller.unmarshal(new File(xml));
		} catch (Exception e){
			System.out.println("erreur dans le chargement du fichier "+xml);
		}
		
		File file = new File(fouille.getFondEcran());
		Image image = new Image(file.toURI().toString());
		imageFond.setImage(image);
		centerImage(imageFond);
		
		
		
		for(Instruction instruction : fouille.getListeInstructions()){
			intitules.add(instruction.getIntitule());
			
			file = new File(instruction.getImageObjet());
			image = new Image(file.toURI().toString());
			ImageView element=new ImageView(image);
			element.setX(instruction.getPosX()+imageFond.getX());
			element.setY(instruction.getPosY()+imageFond.getY());
			AP_Game.getChildren().add(element);
			element.setOnMouseClicked(new EventHandler<MouseEvent>()
	        {
	            @Override
	            public void handle(MouseEvent t) {
	            	if (LB_Instruction.getText().equals(instruction.getIntitule())){
	            		AP_Game.getChildren().remove(element);
	            		AP_Inventory.getChildren().add(element);
	            		majScorePlus(LB_Score,fouille.getListeInstructions().get(cmpt-1).getType());
	            		majInstruction(LB_Instruction);
	            		
	            	} else{
	            		//erreur
	            		majScoreMoins(LB_Score,fouille.getListeInstructions().get(cmpt-1).getType());
	            	}
	            	
	            }
	        });
		}
		
		majInstruction(LB_Instruction);
	}
	
	public void setXML(String xml) {
		this.xml=xml;
	}
	
	public void setChronologie(boolean soloBloc, int cmptChronologie, String xmlChronologie){
		this.soloBloc=soloBloc;
		this.cmptChronologie=cmptChronologie;
		this.xmlChronologie=xmlChronologie;
	}
	
	public void setSon(boolean son){
		this.son=son;
	}
	
	public void setScore(int score){
		this.score=score;
		LB_Score.setText("Score : "+Integer.toString(score));
	}
	
	public static void centerImage(ImageView imageView) {
        Image img = imageView.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = imageView.getFitWidth() / img.getWidth();
            double ratioY = imageView.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;
            
            //System.out.println((imageView.getFitWidth() - w) / 2);
            //System.out.println((imageView.getFitHeight() - h) / 2);
            

            imageView.setX((imageView.getFitWidth() - w) / 2);
            imageView.setY((imageView.getFitHeight() - h) / 2);

        }
    }
	
	private static void majScorePlus(Label LBscore, String typeObjet){
		if(son)
			jouerAudio("Ressources/Sons/succes.wav");
		if(typeObjet.equals("BRONZE"))
			score=(int) (score+fouille.getBronze());
		else if (typeObjet.equals("SILVER"))
			score=(int) (score+fouille.getSilver());
		else
			score=(int) (score+fouille.getGold());
		LBscore.setText("Score : "+Integer.toString(score));
	}
	
	private static void majScoreMoins(Label LBscore, String typeObjet){
		if(son)
			jouerAudio("Ressources/Sons/echec1.wav");
		if(typeObjet.equals("BRONZE"))
			score=(int) (score-(fouille.getBronze()/2));
		else if (typeObjet.equals("SILVER"))
			score=(int) (score-(fouille.getSilver()/2));
		else
			score=(int) (score-(fouille.getGold()/2));
		LBscore.setText("Score : "+Integer.toString(score));
	}

	public static void majInstruction(Label label){
		if (cmpt<intitules.size()){
			label.setText(intitules.get(cmpt));
			cmpt++;
		} else {
			Alert alert = new Alert(AlertType.INFORMATION, "Le jeu fouille est fini.\n Votre Score : "+ score, ButtonType.OK);
			alert.showAndWait();
			
			if(soloBloc){
				Stage stage = (Stage) label.getScene().getWindow();
				stage.close();
			} else {
				Stage stage = (Stage) label.getScene().getWindow();
				Node node=JFxUtils.loadNextBloc(cmptChronologie, xmlChronologie, son);
				if (node!=null){
					stage.setScene(new Scene((Parent) node, 850, 650));
				} else {
					stage.close();
				}
			}
		}
	}
	
	public static void jouerAudio(String son) {
		InputStream in;
		try {
			in = new FileInputStream(son);
			AudioStream as = new AudioStream(in);
			AudioPlayer.player.start(as);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void swapSon(MouseEvent t){
		son=!son;
		if(son)
			sonOn();
		else
			sonOff();
	}
	
	private void sonOn(){
		File file = new File("Ressources/Images/volume_on.png");
		Image image = new Image(file.toURI().toString());
		IV_IconeSon.setImage(image);
		centerImage(IV_IconeSon);
	}
	
	private void sonOff(){
		File file = new File("Ressources/Images/volume_off.png");
		Image image = new Image(file.toURI().toString());
		IV_IconeSon.setImage(image);
		centerImage(IV_IconeSon);
	}

}
