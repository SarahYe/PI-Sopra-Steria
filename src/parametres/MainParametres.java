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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
	       		new Question ("Quelle heure est-il ?",ListeReponses),
	       		new Question ("Quel est ... ?",ListeReponses)
	        );
	
    public static void main(String[] args) {
        Application.launch(MainParametres.class, args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Page principale de parametrage");
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.WHITE);
        
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
                
                Label label1 = new Label("Intitulé :");
                TextField textField = new TextField ();
                Button btn = new Button("Enregistrer");
                btn.setOnAction(new EventHandler<ActionEvent>() {
                	    public void handle(ActionEvent e) {
                	        data.add(new Question(textField.getText(), ListeReponses));
                	        Stage stage = (Stage) btn.getScene().getWindow();
                	        stage.close();
                	     }
                	 });
                HBox hb = new HBox();
                hb.getChildren().addAll(label1, textField, btn);
                hb.setSpacing(10);
                
                root2.getChildren().add(hb);
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
                Button btn = new Button("Enregistrer");
                btn.setOnAction(new EventHandler<ActionEvent>() {
                	    public void handle(ActionEvent e) {
                	        int index = table.getSelectionModel().getSelectedIndex();
                	        table.getItems().set(index, new Question(textField.getText(), ListeReponses));
                	        Stage stage = (Stage) btn.getScene().getWindow();
                	        stage.close();
                	     }
                	 });
                HBox hb = new HBox();
                hb.getChildren().addAll(label1, textField, btn);
                hb.setSpacing(10);
                
                root2.getChildren().add(hb);
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

