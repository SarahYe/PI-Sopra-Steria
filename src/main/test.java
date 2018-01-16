package main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

public class test extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    Scene scene = new Scene(new Group());
    stage.setTitle("Label Sample");
    stage.setWidth(400);
    stage.setHeight(180);

    HBox hbox = new HBox();

    final Label label1 = new Label("Search long long long long long long long long long ");
    TextField text = new TextField();
    
    /*    label1.setOnMouseEntered(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        label1.setScaleX(1.5);
        label1.setScaleY(1.5);
      }
    });

   label1.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        label1.setScaleX(1);
        label1.setScaleY(1);
      }
    });*/

    label1.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
    	  	label1.setText("mouse click detected!");
      }
    });
    
    hbox.setSpacing(10);
    hbox.getChildren().add((label1));
    ((Group) scene.getRoot()).getChildren().add(hbox);

    stage.setScene(scene);
    stage.show();
  }
}
