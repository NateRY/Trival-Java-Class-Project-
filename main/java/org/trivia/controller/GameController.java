package org.trivia.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.trivia.model.Entry;
import org.trivia.model.Game;
import org.trivia.model.User;
import org.trivia.trivia.App;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class GameController {
    private static String username;
    private User currentUser;
    private static List<String> selectedCategory;
    private Game currentGame;

    private int currentEntryId = 0;
    private String currentChoice;
    private Button currentChoiceButton;
    private int currentScore;
    private  List<Entry> entries;

    @FXML
    private Text hint;
    @FXML
    private GridPane optionsGridPane;
    @FXML
    public Button submitButton;
    @FXML
    public Text questionText;
    @FXML
    private Button option1;
    @FXML
    private Button option2;
    @FXML
    private Button option3;
    @FXML
    private Button option4;
    @FXML
    private ProgressBar progressBar;
    @FXML Text scoreLabel;
    @FXML
    private Text progressLabel;

    @FXML
    public static void newGame(String name, List<String> categories) {
        username = name;
        selectedCategory = categories;
    }

    @FXML
    public void initialize() {
        currentUser = new User(username);
        currentGame = new Game(username);
        entries = App.getDbHandler().getEntryByCategories(selectedCategory);
        Collections.shuffle(entries);
        if  (entries.isEmpty()) {
            questionText.setText("There are no questions to display.");
        }
        progressLabel.setText("0 / " + String.valueOf(entries.size()));
        displayQuestion(currentEntryId);

        submitButton.getStyleClass().add("-color-button-bg-focused");
    }


    @FXML
    private void getChoice(ActionEvent event){
        Button choice = (Button) event.getSource();
        currentChoice = choice.getText();
        currentChoiceButton = choice;
        submitButton.setDisable(false);
        if (!choice.getStyleClass().contains("danger")) {
            choice.getStyleClass().add("danger");
        }

        for(var c: List.of(option1, option2, option3, option4)) {
            if (c != choice) {
                c.getStyleClass().remove("danger");
            }
        }
        System.out.println("choice is " + currentChoice);
    }

    private void displayQuestion(int id) {
        submitButton.setDisable(true);
        if  (id < entries.size()) {
            Entry entry = entries.get(id);

            questionText.setText(entry.getQuestion());
            List<String> shuffledOptions =  entry.getShuffledOptions();

            option1.setText(shuffledOptions.get(0));
            option2.setText(shuffledOptions.get(1));
            option3.setText(shuffledOptions.get(2));
            option4.setText(shuffledOptions.get(3));
        }
        else {
            questionText.setText("Congratulations! You have finished the game.");
            submitButton.setDisable(true);
            optionsGridPane.getChildren().clear();
            // Handle save game below
            App.getDbHandler().saveGames(currentGame);
        }
    }

    @FXML
    public void submitAction() {
        currentChoiceButton.getStyleClass().remove("danger");
        if (entries.get(currentEntryId).getAnswer().equals(currentChoice) ) {
            ++currentScore;
        }

        ++currentEntryId;
        progressBar.setProgress(currentEntryId * 1.0/ entries.size());
        progressLabel.setText(currentEntryId + " / " + entries.size());
        scoreLabel.setText("Score: " + String.format("%d", (int)(currentScore * 100.0 / (currentEntryId))) + "%");
        currentChoice = "";

        displayQuestion(currentEntryId);
    }

    @FXML
    private void goHome() throws IOException {
        App.setRoot("home");
    }

    @FXML
    private void showLeaderboard() throws IOException {
        App.setRoot("leaderboard");
    }

    @FXML
    private void playAgain() throws IOException {
        App.setRoot("game");
    }

}
