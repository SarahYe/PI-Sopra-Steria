package controleurs;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import modeles.Fouille;
import modeles.Instruction;

public class ViewFouilleController implements Initializable{
	
	private String xml="";
	private boolean soloBloc=true;
	private int cmptChronologie=0;
	private String xmlChronologie="";
	private boolean son;
	private int score;
	
	private Fouille fouille;
	
	@FXML
	AnchorPane AP_Game,AP_Inventory;
	@FXML
	ImageView imageFond;
	@FXML
	Label LB_Instruction;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
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
	            	AP_Game.getChildren().remove(element);
	            	AP_Inventory.getChildren().add(element);
	            }
	        });
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
	
	public void setSon(boolean son){
		this.son=son;
	}
	
	public void setScore(int score){
		this.score=score;
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

	

}
