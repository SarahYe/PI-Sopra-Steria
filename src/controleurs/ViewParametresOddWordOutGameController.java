package controleurs;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import modeles.Question;
import modeles.Quiz;
import modeles.Reponse;

public class ViewParametresOddWordOutGameController implements Initializable{

	@FXML 
	private TextField textFieldQuestion;
	@FXML 
	private TextField TextFieldBRep;
	@FXML 
	private TextField TextFieldMRep;
	@FXML 
	private TableView<String> tableBReps;
	@FXML 
	private TableView<String> tableMReps;
	@FXML 
	private TableColumn<String, String> TableColumnBReps;
	@FXML 
	private TableColumn<String, String> TableColumnMReps;
	
	private String xml;
	
	public void setXML(String xml) {
		this.xml=xml;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ViewAddOrModifyQuestionController.addTextFLimiter(textFieldQuestion,150);
	}
	
	public void initData() {
		File f =  new File(xml);
		System.out.println("saluuuut");
		TableColumnBReps.setCellValueFactory(cellData -> 
	    			new ReadOnlyStringWrapper(cellData.getValue()));
		TableColumnMReps.setCellValueFactory(cellData -> 
	    			new ReadOnlyStringWrapper(cellData.getValue()));
		if (f.exists()) {
			ArrayList<Question> list = new ArrayList<Question>();
			Quiz quiz = new Quiz("", list);
			Quiz quiz2 = quiz.convertirXMLToJava(xml);
			textFieldQuestion.setText(quiz2.getListeQuestions().get(0).getIntituleQuestion());
			for (int i = 0; i < quiz2.getListeQuestions().get(0).getListeReponses().size(); i++) {
				Reponse rep = quiz2.getListeQuestions().get(0).getListeReponses().get(i);
				if(rep.getCorrect()){
					tableBReps.getItems().add(rep.getIntitule());
				} else {
					tableMReps.getItems().add(rep.getIntitule());
				}
			}
		} else 
			System.out.println("xml : "+ xml);		
	}
	
	@FXML
	protected void ClickButtonAddGoodAnswer(ActionEvent event) throws IOException {
	    ObservableList<String> data = tableBReps.getItems();
	    data.add(TextFieldBRep.getText());      
	    TextFieldBRep.setText("");  
	 }
	 
	 @FXML
	 protected void ClickButtonAddBadAnswer(ActionEvent event) {
		 ObservableList<String> data = tableMReps.getItems();
		 data.add(TextFieldMRep.getText());
	     TextFieldMRep.setText("");  
	 }
	 
	 @FXML
		protected void ClickButtonSupprGoodAnswer(ActionEvent event) throws IOException {
		 	if (tableBReps.getSelectionModel().isEmpty()) {
				return;
			}

			String selectedItem = tableBReps.getSelectionModel().getSelectedItem();
			tableBReps.getItems().remove(selectedItem); 
		 }
	 
	 @FXML
		protected void ClickButtonSupprBadAnswer(ActionEvent event) throws IOException {
		 	if (tableMReps.getSelectionModel().isEmpty()) {
				return;
			}

			String selectedItem = tableMReps.getSelectionModel().getSelectedItem();
			tableMReps.getItems().remove(selectedItem);
		 }
	 
	 @FXML
	 protected void ClickButtonSave(ActionEvent event) throws IOException {
		 
		ArrayList<Reponse> listeReponses = new ArrayList<Reponse>();	
		for (int i = 0; i < tableBReps.getItems().size(); i++){
			listeReponses.add(new Reponse(tableBReps.getItems().get(i), true, ""));
		}
		for (int i = 0; i < tableMReps.getItems().size(); i++){
			listeReponses.add(new Reponse(tableMReps.getItems().get(i), false, ""));
		}
		
		ArrayList<Question> listeQuestions = new ArrayList<Question>();
		listeQuestions.add(new Question(textFieldQuestion.getText(), listeReponses));
		
		Quiz quiz = new Quiz("Nom du quiz", listeQuestions);
		quiz.convertirJavaToXML(quiz, xml);
		
		//Stage stage = (Stage) textFieldQuestion.getScene().getWindow();
		//stage.close();
		
		//popup de confirmation
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Paramétrage  d'un jeu de tri");
		alert.setContentText("Le paramétrage a bien été enregistré !");
		alert.showAndWait();
		 
	 }
	 
}
