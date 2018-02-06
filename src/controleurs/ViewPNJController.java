package controleurs;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modeles.Dialogue;
import modeles.PNJ;
import modeles.Question;
import modeles.Quiz;

public class ViewPNJController implements Initializable {
	
	@FXML
	private ImageView boutonNext;
	
	@FXML
	private AnchorPane anchor;
	
	@FXML
	private Label texte;
	
	@FXML
	private ImageView personnage;
	
	@FXML
	private ImageView anchorImage;
	
	private String xml="";
	private boolean soloBloc=true;
	private int cmptChronologie=0;
	private String xmlChronologie="";
	private PNJ pnj = new PNJ();
	private int cmpt;
	private Dialogue dial;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		//jouerAudio("././Ressources/Sons/Jouer.wav", -25.0f);
	}
	
	public void initData(PNJ pnj, int cmpt) {
		this.cmpt = cmpt;
		this.pnj = pnj; 
		
		File f =  new File(xml);
		if (f.exists()){
			pnj = pnj.convertirXMLToJava(xml);
			System.out.println(pnj.getListeDialogues().size());
			if (pnj.getListeDialogues().size() == cmpt) {
				if(soloBloc){
					Stage stage = (Stage) boutonNext.getScene().getWindow();
					stage.setScene(new Scene((Parent) JFxUtils.loadPNJFxml(new PNJ(), 0, "/vues/ViewPNJ.fxml", xml, true, 0, "Games/test/chronologie_test"), 850, 650));
				} else {
					Stage stage = (Stage) boutonNext.getScene().getWindow();
					//System.out.print(score);
					Node node = JFxUtils.loadNextBloc(cmptChronologie, xmlChronologie);
					
					if (node != null){
						stage.setScene(new Scene((Parent) node, 850, 650));
					} else {
						stage.close();
					}
					
				}
			} else {
				boutonNext.setVisible(true);
				dial = pnj.getListeDialogues().get(cmpt);
				remplissageContentDialogue(dial);
			}
		} else {
			System.out.println("xml : "+xml);
		}
		
	}

	private void remplissageContentDialogue(Dialogue dial2) {
		if (!dial2.getImageFondEcran().isEmpty()) {
			anchorImage.setImage(ViewParametresPageExplicationController.chargerImage(dial2.getImageFondEcran()));
		}
		
		if (!dial2.getCouleurFondEcran().isEmpty()) {
			anchor.setStyle("-fx-background-color:" + dial2.getCouleurFondEcran().replace("0x", "#") + ";");
		}
		
		personnage.setImage(ViewParametresPageExplicationController.chargerImage(dial2.getImagePersonnage()));
		texte.setText(dial2.getIntitule());
	}

	public void setChronologie(boolean soloBloc, int cmptChronologie, String xmlChronologie) {
		this.soloBloc=soloBloc;
		this.cmptChronologie=cmptChronologie;
		this.xmlChronologie=xmlChronologie;
	}

	public void setXML(String xml) {
		this.xml=xml;
	}

	@FXML
	private void ClickButtonNext(ActionEvent event) throws IOException {
		Stage stage = (Stage) boutonNext.getScene().getWindow();
		stage.setScene(new Scene((Parent) JFxUtils.loadPNJFxml(pnj, cmpt + 1, "/vues/ViewPNJ.fxml", xml, soloBloc, cmptChronologie, xmlChronologie), 850, 650));
		stage.show();
		//Stage stage = (Stage) boutonNext.getScene().getWindow();
		//new JFxUtils().loadDialogue(pnj, cmpt + 1, stage,xml,soloBloc,cmptChronologie,xmlChronologie);
	}
	
}
