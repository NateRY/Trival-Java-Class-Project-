package org.trivia.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.trivia.trivia.App;

import java.io.IOException;
import java.util.List;

public class MainController {
    private String username;
    private List<String> selectedCategory;

    @FXML
    private Label welcomeText;

    @FXML
    private void exit() {
        System.exit(0);
    }

    @FXML
    private void leaderboard() throws IOException {
        App.setRoot("leaderboard");
    }

    @FXML
    public void handleLogin() throws IOException {
        App.setRoot("login");
    }

    public void manageEntries() throws IOException {
        App.setRoot("manager");
    }
}
