package parametres;

import objets.Question;
import objets.Reponse;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
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

	private TableView<String> table = new TableView<String>();
	private ArrayList<Reponse> ListeReponses = new ArrayList<Reponse>();
	private final ObservableList<String> data =
	        FXCollections.observableArrayList(
	       		new Question ("Quel est l'intru parmis ces réponses ?",ListeReponses).getIntutileQuestion(),
	       		new Question ("Quelle heure est-il ?",ListeReponses).getIntutileQuestion(),
	       		new Question ("Quel est ... ?",ListeReponses).getIntutileQuestion()
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
 
        TableColumn<String, String> titreCol = new TableColumn<String, String>("Intitulé des questions");
        titreCol.setMinWidth(400);
        titreCol.setCellValueFactory(
        		data -> new SimpleStringProperty(data.getValue()));
        //TableColumn<String, String> dateCol = new TableColumn<String, String>("Date de derniere modification");
        //TableColumn<String, String> createurCol = new TableColumn<String, String>("Createur");
        
        //table.getColumns().addAll(titreCol, dateCol, createurCol);
        table.setItems(data);
        table.getColumns().addAll(titreCol);
        
        
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
                	        data.add(textField.getText());
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
                TextField textField = new TextField (table.getSelectionModel().getSelectedItem());
                Button btn = new Button("Enregistrer");
                btn.setOnAction(new EventHandler<ActionEvent>() {
                	    public void handle(ActionEvent e) {
                	        int index = table.getSelectionModel().getSelectedIndex();
                	        table.getItems().set(index, textField.getText());
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
            	 String selectedItem = table.getSelectionModel().getSelectedItem();
            	 table.getItems().remove(selectedItem);
            }
        });
        
        HBox hb = new HBox();
        hb.getChildren().addAll(boutonAjout, boutonModifier, boutonSupprimer);
        hb.setSpacing(100);
        hb.setPadding(new Insets(5, 0, 0, 10));
        
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
       
        /* FIN DU CONTENU DE LA PAGE */
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

