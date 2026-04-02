package org.trivia.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.trivia.model.Entry;
import org.trivia.trivia.App;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameController {
    private static String username;
    private static List<String> selectedCategory;
    private int currentEntryId = 0;
    private String currentChoice;
    private int currentScore;
    private  List<Entry> entries;

    @FXML
    public Text questionText;
    @FXML
    private TextField option1;
    @FXML
    private TextField option2;
    @FXML
    private TextField option3;
    @FXML
    private TextField option4;
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
        entries = App.getDbHandler().getEntryByCategories(selectedCategory);
        if  (entries.isEmpty()) {
            questionText.setText("There are no questions to display.");
        }
        displayQuestion(currentEntryId);
    }


    @FXML
    private void getChoice(ActionEvent event){
        TextField choice = (TextField) event.getSource();
        currentChoice = choice.getText();
    }


    private void displayQuestion(int id) {
        currentChoice = "";

        if  (id <= entries.size()) {
            Entry entry = entries.get(id);

            questionText.setText(entry.getQuestion());
            List<String> options =  Arrays.asList(entry.getAnswer(), entry.getOption2(), entry.getOption3(), entry.getOption4());
            Collections.shuffle(options);

            option1.setText(options.get(0));
            option2.setText(options.get(1));
            option3.setText(options.get(2));
            option4.setText(options.get(3));

            progressBar.setProgress(id * 1.0/ entries.size());
            progressLabel.setText(String.valueOf(id) + " / " + String.valueOf(entries.size()));

            if (currentChoice.equals(entry.getAnswer())){
                ++currentScore;
                scoreLabel.setText(String.valueOf(currentScore));
            }

            ++currentEntryId;
        }
    }



    @FXML
    public void submitAction() {
        displayQuestion(currentEntryId);
    }
}
