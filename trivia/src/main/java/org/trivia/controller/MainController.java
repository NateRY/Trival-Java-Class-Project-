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
    private void play() {
        welcomeText.setText("Playing...");
    }

    @FXML
    private void exit() {
        System.exit(0);
    }

    @FXML
    private void settings() {
        welcomeText.setText("Settings...");
    }

    @FXML
    private void leaderboard() {
        welcomeText.setText("Leaderboard...");
    }

    @FXML
    public void handleLogin() throws IOException {
        App.setRoot("/org/trivia/view/loginView");
    }

    public void addQuestion() {
    }
}
