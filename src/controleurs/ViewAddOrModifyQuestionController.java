package controleurs;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modeles.Question;
import modeles.Reponse;

public class ViewAddOrModifyQuestionController {

	private boolean modifyQuestionInterface;
	private TableViewSelectionModel<Question> tableViewSelectionModel;
	private ViewMainParametresController mainController;

	
	@FXML
	private Button btn;
	@FXML
	private CheckBox checkBoxRep1;
	@FXML
	private CheckBox checkBoxRep2;
	@FXML
	private TextArea textAreaRep1;
	@FXML
	private TextArea textAreaRep2;
	@FXML 
	private TextField textFieldQuestion;
	@FXML 
	private TextField textFieldRep1;
	@FXML 
	private TextField textFieldRep2;

	
	public void initData(boolean modifyQuestionInterface, TableViewSelectionModel<Question> tableViewSelectionModel, ViewMainParametresController controller) {
		
		this.modifyQuestionInterface = modifyQuestionInterface;
		this.tableViewSelectionModel = tableViewSelectionModel;
		mainController = controller;
		
		if(this.modifyQuestionInterface){
			
			textFieldQuestion.setText(tableViewSelectionModel.getSelectedItem().getIntituleQuestion());
			
			textFieldRep1.setText(tableViewSelectionModel.getSelectedItem().getListeReponses().get(0).getIntitule());
			textFieldRep2.setText(tableViewSelectionModel.getSelectedItem().getListeReponses().get(1).getIntitule());
			
			checkBoxRep1.setSelected(tableViewSelectionModel.getSelectedItem().getListeReponses().get(0).getCorrect());
			checkBoxRep2.setSelected(tableViewSelectionModel.getSelectedItem().getListeReponses().get(1).getCorrect());
			
			textAreaRep1.setText(tableViewSelectionModel.getSelectedItem().getListeReponses().get(0).getJustification());
			textAreaRep2.setText(tableViewSelectionModel.getSelectedItem().getListeReponses().get(1).getJustification());	
			
		}
		
	}
	
	
	@FXML
	protected void ClickButtonSave(ActionEvent event) throws IOException {
		
		if(modifyQuestionInterface){
			
			ArrayList<Reponse> listeReponses = new ArrayList<Reponse>();
			Reponse rep1 = new Reponse(textFieldRep1.getText(), checkBoxRep1.isSelected(), textAreaRep1.getText());
			Reponse rep2 = new Reponse(textFieldRep2.getText(), checkBoxRep2.isSelected(), textAreaRep2.getText());
			listeReponses.add(rep1);
			listeReponses.add(rep2);
			
			mainController.setQuestion(tableViewSelectionModel.getSelectedIndex(), textFieldQuestion.getText(), listeReponses);
			
			Stage stage = (Stage) btn.getScene().getWindow();
			stage.close();
			
		} else {
			
			ArrayList<Reponse> listeReponses = new ArrayList<Reponse>();
			Reponse rep1 = new Reponse(textFieldRep1.getText(), checkBoxRep1.isSelected(), textAreaRep1.getText());
			Reponse rep2 = new Reponse(textFieldRep2.getText(), checkBoxRep2.isSelected(), textAreaRep2.getText());
			listeReponses.add(rep1);
			listeReponses.add(rep2);
			
			mainController.addQuestion(textFieldQuestion.getText(), listeReponses);
			
			Stage stage = (Stage) btn.getScene().getWindow();
			stage.close();
			
		}	
		
	}	
	
}
