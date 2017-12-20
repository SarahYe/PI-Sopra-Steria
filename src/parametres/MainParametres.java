package parametres;

import objets.Question;
import objets.Reponse;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainParametres extends Application {

	private TableView<Question> table = new TableView<Question>();
	private ArrayList<Reponse> ListeReponses = new ArrayList<Reponse>();
	
	private final ObservableList<Question> data =
	        FXCollections.observableArrayList(
	       		new Question ("Quel est l'intru parmis ces réponses ?",ListeReponses),
	       		new Question ("Quelle heure est-il ?",ListeReponses)
	        );
	
    public static void main(String[] args) {
        Application.launch(MainParametres.class, args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Page principale de parametrage");
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.WHITE);
        
        ArrayList<Reponse> ListeReponses1 = new ArrayList<Reponse>();
        ListeReponses1.add(new Reponse("pomme de terre", true, "il s'agit en effet de la bonne réponse !"));
        ListeReponses1.add(new Reponse("patate", false, "bien sûr que non, c'était la pomme de terre !"));
        data.set(0, new Question ("Quel est l'intru parmis ces réponses ?",ListeReponses1));
        
        ArrayList<Reponse> ListeReponses2 = new ArrayList<Reponse>();
        ListeReponses2.add(new Reponse("42", true, "Vous n'avez pas compris la question, mais bonne réponse !"));
        ListeReponses2.add(new Reponse("patate ?", true, "Pourquoi pas."));
        data.set(1, new Question ("Quelle heure est-il ?",ListeReponses2));
        
        /* CONTENU DE LA PAGE */
        final Label label = new Label("Liste des questions");
        label.setFont(new Font("Arial", 20));
 
        table.setEditable(false);
 
        TableColumn<Question, String> titreCol = new TableColumn<Question, String>("Intitulé des questions");
        titreCol.setMinWidth(450);
        titreCol.setCellValueFactory(
        		new PropertyValueFactory<Question,String>("intituleQuestion"));
        
        //table.getColumns().addAll(titreCol, dateCol, createurCol);
        table.setItems(data);
        table.getColumns().add(titreCol);
        
        
        final Button boutonAjout = new Button("Ajouter");
        boutonAjout.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
    
            	Group root2 = new Group();
            	Stage stage = new Stage();
                stage.setTitle("Nouvelle question");
                stage.setScene(new Scene(root2, 450, 450));
                
                Label label1 = new Label("Intitulé de la question :");
                TextField textField = new TextField();              
                HBox hb = new HBox();
                hb.getChildren().addAll(label1, textField);
                hb.setSpacing(10);
                
                
                Label labelR1 = new Label("Intitulé de la réponse 1 :");
                TextField textFieldR1 = new TextField();
                HBox hbR1 = new HBox();
                hbR1.getChildren().addAll(labelR1, textFieldR1);
                hbR1.setSpacing(10);
                CheckBox cbR1 = new CheckBox("Réponse correcte");
                TextArea textFieldR1J = new TextArea();
                textFieldR1J.setMaxSize(400, 60);               
                VBox vbR1 = new VBox();
                vbR1.getChildren().addAll(hbR1, cbR1, textFieldR1J);
                
                Label labelR2 = new Label("Intitulé de la réponse 2 :");
                TextField textFieldR2 = new TextField();
                HBox hbR2 = new HBox();
                hbR2.getChildren().addAll(labelR2, textFieldR2);
                hbR2.setSpacing(10);
                CheckBox cbR2 = new CheckBox("Réponse correcte");
                TextArea textFieldR2J = new TextArea();
                textFieldR2J.setMaxSize(400, 60);               
                VBox vbR2 = new VBox();
                vbR2.getChildren().addAll(hbR2, cbR2, textFieldR2J);
                
                Button btn = new Button("Enregistrer");
                btn.setOnAction(new EventHandler<ActionEvent>() {
                	    public void handle(ActionEvent e) {
                	    	ArrayList<Reponse> listeReponses = new ArrayList<Reponse>();
                	    	Reponse rep1 = new Reponse (textFieldR1.getText(), cbR1.isSelected(), textFieldR1J.getText());
                	    	Reponse rep2 = new Reponse (textFieldR2.getText(), cbR2.isSelected(), textFieldR2J.getText());
                	    	listeReponses.add(rep1);
                	    	listeReponses.add(rep2);
                	    	data.add(new Question(textField.getText(), listeReponses));
                	        Stage stage = (Stage) btn.getScene().getWindow();
                	        stage.close();
                	     }
                	 });
                
                VBox vb = new VBox();
                vb.getChildren().addAll(hb, vbR1, vbR2, btn);
                vb.setSpacing(20);
                vb.setPadding(new Insets(25, 0, 0, 25));
                
                root2.getChildren().add(vb);
                stage.show();
            }
        });
        
        final Button boutonModifier = new Button("Modifier");
        boutonModifier.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
  
            	Group root2 = new Group();
            	Stage stage = new Stage();
                stage.setTitle("Modifier une question");
                stage.setScene(new Scene(root2, 450, 450));
                
                Label label1 = new Label("Intitulé :");
                TextField textField = new TextField (table.getSelectionModel().getSelectedItem().getIntituleQuestion());
                HBox hb = new HBox();
                hb.getChildren().addAll(label1, textField);
                hb.setSpacing(10);
                
                Label labelR1 = new Label("Intitulé de la réponse 1 :");
                TextField textFieldR1 = new TextField(table.getSelectionModel().getSelectedItem().getListeReponses().get(0).getIntitule());
                HBox hbR1 = new HBox();
                hbR1.getChildren().addAll(labelR1, textFieldR1);
                hbR1.setSpacing(10);
                CheckBox cbR1 = new CheckBox("Réponse correcte");
                cbR1.setSelected(table.getSelectionModel().getSelectedItem().getListeReponses().get(0).getCorrect());
                TextArea textFieldR1J = new TextArea(table.getSelectionModel().getSelectedItem().getListeReponses().get(0).getJustification());
                textFieldR1J.setMaxSize(400, 60);               
                VBox vbR1 = new VBox();
                vbR1.getChildren().addAll(hbR1, cbR1, textFieldR1J);
                
                Label labelR2 = new Label("Intitulé de la réponse 2 :");
                TextField textFieldR2 = new TextField(table.getSelectionModel().getSelectedItem().getListeReponses().get(1).getIntitule());
                HBox hbR2 = new HBox();
                hbR2.getChildren().addAll(labelR2, textFieldR2);
                hbR2.setSpacing(10);
                CheckBox cbR2 = new CheckBox("Réponse correcte");
                cbR2.setSelected(table.getSelectionModel().getSelectedItem().getListeReponses().get(1).getCorrect());
                TextArea textFieldR2J = new TextArea(table.getSelectionModel().getSelectedItem().getListeReponses().get(1).getJustification());
                textFieldR2J.setMaxSize(400, 60);               
                VBox vbR2 = new VBox();
                vbR2.getChildren().addAll(hbR2, cbR2, textFieldR2J);
                
                Button btn = new Button("Enregistrer");
                btn.setOnAction(new EventHandler<ActionEvent>() {
                	    public void handle(ActionEvent e) {
                	    	ArrayList<Reponse> listeReponses = new ArrayList<Reponse>();
                	    	Reponse rep1 = new Reponse (textFieldR1.getText(), cbR1.isSelected(), textFieldR1J.getText());
                	    	Reponse rep2 = new Reponse (textFieldR2.getText(), cbR2.isSelected(), textFieldR2J.getText());
                	    	listeReponses.add(rep1);
                	    	listeReponses.add(rep2);
                	        int index = table.getSelectionModel().getSelectedIndex();
                	        table.getItems().set(index, new Question(textField.getText(), listeReponses));
                	        Stage stage = (Stage) btn.getScene().getWindow();
                	        stage.close();
                	     }
                	 });
                
                VBox vb = new VBox();
                vb.getChildren().addAll(hb, vbR1, vbR2, btn);
                vb.setSpacing(20);
                vb.setPadding(new Insets(25, 0, 0, 25));
                
                root2.getChildren().add(vb);
                stage.show();
            }
        });
        
        final Button boutonSupprimer = new Button("Supprimer");
        boutonSupprimer.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	 Question selectedItem = table.getSelectionModel().getSelectedItem();
            	 table.getItems().remove(selectedItem);
            }
        });
        
        HBox hb = new HBox();
        boutonAjout.setMinWidth(100);
        boutonModifier.setMinWidth(100);
        boutonSupprimer.setMinWidth(100);
        hb.getChildren().addAll(boutonAjout, boutonModifier, boutonSupprimer);
        hb.setSpacing(75);
        //hb.setPadding(new Insets(25, 0, 0, 25));
        
        final VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(25, 0, 0, 25));
        vbox.getChildren().addAll(label, table, hb);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
       
        /* FIN DU CONTENU DE LA PAGE */
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}

