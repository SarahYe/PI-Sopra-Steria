package controleurs;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modeles.Question;
import modeles.Reponse;

public class ViewAddOrModifyQuestionController implements Initializable{

	private boolean modifyQuestionInterface;
	private TableViewSelectionModel<Question> tableViewSelectionModel;
	private ViewParametresQuizController mainController;

	
	@FXML
	private Button btn;
	@FXML
	private CheckBox checkBoxRep1;
	@FXML
	private CheckBox checkBoxRep2;
	@FXML
	private CheckBox checkBoxRep3;
	@FXML
	private CheckBox checkBoxRep4;
	@FXML
	private CheckBox checkBoxRep5;
	@FXML
	private ComboBox<String> ComboBoxNbrQuestion;
	@FXML
	private Label errorLabel;
	@FXML
	private TextArea textAreaRep1;
	@FXML
	private TextArea textAreaRep2;
	@FXML
	private TextArea textAreaRep3;
	@FXML
	private TextArea textAreaRep4;
	@FXML
	private TextArea textAreaRep5;
	@FXML 
	private TextField textFieldQuestion;
	@FXML 
	private TextField textFieldRep1;
	@FXML 
	private TextField textFieldRep2;
	@FXML 
	private TextField textFieldRep3;
	@FXML 
	private TextField textFieldRep4;
	@FXML 
	private TextField textFieldRep5;
	@FXML 
	private VBox VBoxRep3;
	@FXML 
	private VBox VBoxRep4;
	@FXML 
	private VBox VBoxRep5;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		initData(false,null);
	}
	
	public void initData(boolean modifyQuestionInterface, TableViewSelectionModel<Question> tableViewSelectionModel) {
		
		this.modifyQuestionInterface = modifyQuestionInterface;
		this.tableViewSelectionModel = tableViewSelectionModel;
		//mainController = controller;
		
		if(this.modifyQuestionInterface){
			
			Question selectedQuestion = tableViewSelectionModel.getSelectedItem();
			
			textFieldQuestion.setText(selectedQuestion.getIntituleQuestion());
			
			textFieldRep1.setText(selectedQuestion.getListeReponses().get(0).getIntitule());
			textFieldRep2.setText(selectedQuestion.getListeReponses().get(1).getIntitule());
			
			checkBoxRep1.setSelected(selectedQuestion.getListeReponses().get(0).getCorrect());
			checkBoxRep2.setSelected(selectedQuestion.getListeReponses().get(1).getCorrect());
			
			textAreaRep1.setText(selectedQuestion.getListeReponses().get(0).getJustification());
			textAreaRep2.setText(selectedQuestion.getListeReponses().get(1).getJustification());	
			
			int nbrReponses = selectedQuestion.getListeReponses().size();			
			ComboBoxNbrQuestion.setValue(Integer.toString(nbrReponses));
			
			if(nbrReponses >= 3){
				textFieldRep3.setText(selectedQuestion.getListeReponses().get(2).getIntitule());
				checkBoxRep3.setSelected(selectedQuestion.getListeReponses().get(2).getCorrect());
				textAreaRep3.setText(selectedQuestion.getListeReponses().get(2).getJustification());	
				VBoxRep3.setVisible(true);
				VBoxRep3.setManaged(true);
			}
			if(nbrReponses >= 4){
				textFieldRep4.setText(selectedQuestion.getListeReponses().get(3).getIntitule());
				checkBoxRep4.setSelected(selectedQuestion.getListeReponses().get(3).getCorrect());
				textAreaRep4.setText(selectedQuestion.getListeReponses().get(3).getJustification());	
				VBoxRep4.setVisible(true);
				VBoxRep4.setManaged(true);
			}
			if(nbrReponses == 5){
				textFieldRep5.setText(selectedQuestion.getListeReponses().get(4).getIntitule());
				checkBoxRep5.setSelected(selectedQuestion.getListeReponses().get(4).getCorrect());
				textAreaRep5.setText(selectedQuestion.getListeReponses().get(4).getJustification());	
				VBoxRep5.setVisible(true);
				VBoxRep5.setManaged(true);
			}		
			
		} else {
			
			ComboBoxNbrQuestion.setValue("2");			
			
		}
		
		ComboBoxNbrQuestion.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		    	switch (newValue) {
		            case "2":	VBoxRep3.setVisible(false);
								VBoxRep3.setManaged(false);
								VBoxRep4.setVisible(false);
								VBoxRep4.setManaged(false);
								VBoxRep5.setVisible(false);
								VBoxRep5.setManaged(false);
			                    break;
		            case "3": 	VBoxRep3.setVisible(true);
								VBoxRep3.setManaged(true);
								VBoxRep4.setVisible(false);
								VBoxRep4.setManaged(false);
								VBoxRep5.setVisible(false);
								VBoxRep5.setManaged(false);
		                    	break;
		            case "4": 	VBoxRep3.setVisible(true);
								VBoxRep3.setManaged(true);
								VBoxRep4.setVisible(true);
								VBoxRep4.setManaged(true);
								VBoxRep5.setVisible(false);
								VBoxRep5.setManaged(false);
		                    	break;
		            case "5": 	VBoxRep3.setVisible(true);
								VBoxRep3.setManaged(true);
								VBoxRep4.setVisible(true);
								VBoxRep4.setManaged(true);
								VBoxRep5.setVisible(true);
								VBoxRep5.setManaged(true);
		                    	break;
		        }
		    }
		});
		
	}
	
	
	@FXML
	protected void ClickButtonSave(ActionEvent event) throws IOException {
		
		if(textFieldQuestion.getText().isEmpty()){
			errorLabel.setText("Intitul� de la question vide !");
			errorLabel.setVisible(true);			
			return;	
		}
		
		if(textFieldRep1.getText().isEmpty() || textFieldRep2.getText().isEmpty() || (VBoxRep3.isManaged() && textFieldRep3.getText().isEmpty()) || (VBoxRep4.isManaged() && textFieldRep4.getText().isEmpty()) || (VBoxRep5.isManaged() && textFieldRep5.getText().isEmpty())){
			errorLabel.setText("Intitul�(s) de r�ponse(s) vide(s) !");
			errorLabel.setVisible(true);
			return;	
		}
		
		String nbrReponses = ComboBoxNbrQuestion.getValue();
		if(!checkBoxRep1.isSelected() && !checkBoxRep2.isSelected()){
			if(nbrReponses.equals("5") && !checkBoxRep3.isSelected() && !checkBoxRep4.isSelected() && !checkBoxRep5.isSelected()){
				errorLabel.setText("Cochez une bonne r�ponse !");
				errorLabel.setVisible(true);
				return;
			}
			if(nbrReponses.equals("4") && !checkBoxRep3.isSelected() && !checkBoxRep4.isSelected()){
				errorLabel.setText("Cochez une bonne r�ponse !");
				errorLabel.setVisible(true);
				return;
			}
			if(nbrReponses.equals("3") && !checkBoxRep3.isSelected()){
				errorLabel.setText("Cochez une bonne r�ponse !");
				errorLabel.setVisible(true);
				return;
			}
			if(nbrReponses.equals("2")){
				errorLabel.setText("Cochez une bonne r�ponse !");
				errorLabel.setVisible(true);
				return;
			}			
		}
		
		ArrayList<Reponse> listeReponses = new ArrayList<Reponse>();
		Reponse rep1 = new Reponse(textFieldRep1.getText(), checkBoxRep1.isSelected(), textAreaRep1.getText());
		Reponse rep2 = new Reponse(textFieldRep2.getText(), checkBoxRep2.isSelected(), textAreaRep2.getText());
		listeReponses.add(rep1);
		listeReponses.add(rep2);
		
		if(VBoxRep3.isManaged()){
			Reponse rep3 = new Reponse(textFieldRep3.getText(), checkBoxRep3.isSelected(), textAreaRep3.getText());
			listeReponses.add(rep3);
		}
		if(VBoxRep4.isManaged()){
			Reponse rep4 = new Reponse(textFieldRep4.getText(), checkBoxRep4.isSelected(), textAreaRep4.getText());
			listeReponses.add(rep4);
		}
		if(VBoxRep5.isManaged()){
			Reponse rep5 = new Reponse(textFieldRep5.getText(), checkBoxRep5.isSelected(), textAreaRep5.getText());
			listeReponses.add(rep5);
		}
		
		if(modifyQuestionInterface){		
			mainController.setQuestion(tableViewSelectionModel.getSelectedIndex(), textFieldQuestion.getText(), listeReponses);		
		} else {		
			mainController.addQuestion(textFieldQuestion.getText(), listeReponses);		
		}	
		
		//Stage stage = (Stage) btn.getScene().getWindow();
		//stage.close();
		
	}
	
}
