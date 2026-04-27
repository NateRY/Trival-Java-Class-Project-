package org.trivia.controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import org.trivia.trivia.App;


import java.io.IOException;
import java.util.ArrayList;

public class P1Gameplay_Entry_Controller extends GP_controler {

    @FXML private ComboBox<String> subject_entry;
    @FXML private ComboBox<String> Level_entry;
    @FXML private ComboBox<Integer> rounds;

    protected Text name;
    protected String P1_subject;
    protected String P1_level;
    protected int P1_round;



    public void initialize() {


        String level_1 = "easy";
        String level_2 = "medium";
        String level_3 = "hard";
        Level_entry.getItems().addAll(level_1, level_2, level_3);

        Level_entry.setOnAction(event -> {
            System.out.println("Level entry selected");
        });


        String math = "Math";
        String history = "History";
        String TV = "TV";
        String Sports = "Sports";
        String Java = "Java";
        String CPP = "C++";
        subject_entry.getItems().addAll(math, history, TV, Sports, Java, CPP);
        subject_entry.setOnAction(event -> {
            System.out.println("Subject entry selected");
        });

        Integer round1 = 1;
        Integer round2 = 2;
        Integer round3 = 3;
        rounds.getItems().add(round1);
        rounds.setOnAction(event -> {
            System.out.println("Round entry selected");
        });

    }



    @FXML
    private void goHome_P1() throws IOException {
        App.setRoot("Smarty_Party_HomeScreen");
    }


    @FXML
    private void P1_Gameplay() throws IOException {

        P1_subject =  subject_entry.getValue();
        P1_level = Level_entry.getValue();
        P1_round = rounds.getValue();

        App.setRoot("P1_Gameplay_Sceen");
    }

    public int getP1_round() {
        return P1_round;
    }
}
