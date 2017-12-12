package paramètres;

import objets.Question;
import objets.Quiz;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainParamètres extends Application {

	private TableView<String> table = new TableView<String>();
	private ArrayList<Question> ListeQuestions = new ArrayList<Question>();
	private final ObservableList<String> data =
	        FXCollections.observableArrayList(
	       		new Quiz ("Quizz n°1",ListeQuestions).getNom(),
	       		new Quiz ("Quizz n°2",ListeQuestions).getNom(),
	       		new Quiz ("Quizz n°3",ListeQuestions).getNom()
	        );
	
    public static void main(String[] args) {
        Application.launch(MainParamètres.class, args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Page principale de paramétrage");
        Group root = new Group();
        Scene scene = new Scene(root, 600, 450, Color.WHITE);
        
        /* CONTENU DE LA PAGE */
        final Label label = new Label("Liste des quizz");
        label.setFont(new Font("Arial", 20));
 
        table.setEditable(true);
 
        TableColumn<String, String> titreCol = new TableColumn<String, String>("Intitulé");
        titreCol.setMinWidth(100);
        titreCol.setCellValueFactory(
        		data -> new SimpleStringProperty(data.getValue()));
        //TableColumn<String, String> dateCol = new TableColumn<String, String>("Date de dernière modification");
        //TableColumn<String, String> createurCol = new TableColumn<String, String>("Créateur");
        
        //table.getColumns().addAll(titreCol, dateCol, createurCol);
        table.setItems(data);
        table.getColumns().addAll(titreCol);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        /* FIN DU CONTENU DE LA PAGE */
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

