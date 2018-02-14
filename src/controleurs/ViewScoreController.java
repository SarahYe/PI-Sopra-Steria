package controleurs;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class ViewScoreController implements Initializable{
	
	private String xml="";
	private boolean soloBloc=true;
	private int cmptChronologie=0;
	private String xmlChronologie="";
	private boolean son;
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
    	
		
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
	
	
}
