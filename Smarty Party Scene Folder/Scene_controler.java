package main.resources.org.trivia.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class Scene_controler {

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchGameOneStart(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("New_Game_P1.fxml"));
        stage = (Stage)((Node) actionEvent.getSource().getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    public void switchSccene2(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("New_Game_P1.fxml"));
        stage = (Stage)((Node) actionEvent.getSource().getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}