package org.trivia.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import org.trivia.trivia.App;
import java.io.IOException;


public class Manager {

    @FXML
    public Button homeButton;
    @FXML
    public Button editButton;
    @FXML
    public Button deleteButton;
    @FXML
    public Button saveButton;
    @FXML
    public ToolBar toolBar;

    @FXML
    public void initialize(){
        toolBar.getItems().clear();
        toolBar.getItems().addAll(homeButton, editButton, deleteButton);
    }

    @FXML
    public void goHome(ActionEvent event) throws IOException {
        App.setRoot("home");
    }
}
