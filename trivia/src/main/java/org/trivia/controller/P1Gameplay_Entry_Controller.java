package org.trivia.controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.trivia.trivia.App;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//This is the Controller Class for Player Entry scene
public class P1Gameplay_Entry_Controller {

    //Connects to Name TextField
    @FXML private TextField nameEntry;

    //Connects to the Subject/Catagory combo box
    @FXML private ComboBox<String> subject_entry;

    //Connects to the Level comboBox
    @FXML private ComboBox<String> Level_entry;

    //Connect to the rounds combobox
    @FXML private ComboBox<Integer> rounds;

    //Holds player entered player name
    private String PlayerName;

    //Holds and passes Subject/Catagory to the next file
    private String P1_subject;

    //Holds Level selected
    private String P1_level;

    //Holds round choice
    private int P1_round;



    public void initialize() {

        //Stores entered name
        PlayerName = nameEntry.getText().trim();


        //sets up the varible that the level comboBox will display
        String level_1 = "Easy";
        String level_2 = "Medium";
        String level_3 = "Hard";
        Level_entry.getItems().addAll(level_1, level_2, level_3);

        //Allows the choice to be stored
        Level_entry.setOnAction(event -> {
            System.out.println("Level entry selected");
            P1_level = Level_entry.toString();
        });


        //sends a queary to the database to get current list of subjects
        List<String> catorgory = App.getDbHandler().getCategories();
        subject_entry.getItems().addAll(catorgory);

        //Allows the player's choice to storeed and gives feedback in the terminal
        subject_entry.setOnAction(event -> {
            System.out.println("Subject entry selected" + subject_entry.getValue());
            P1_subject = subject_entry.getValue();
        });

        //Sets up and stores the rounds
        Integer round1 = 1;
        Integer round2 = 2;
        Integer round3 = 3;
        rounds.getItems().addAll(round1,round2,round3);
        rounds.setOnAction(event -> {

            //gives feedback in the terminal (for Debugging)
            System.out.println("Round entry selected");
            P1_round = rounds.getValue();
        });


    }

    //getter for the Player name
    public String getPlayerName() {
        return PlayerName;
    }

    //getter for Subject picked
    public String getP1_subject() {return P1_subject;}

    //getter for Level picked
    public String getP1_level() {return P1_level;}

    //getter for number of round picked
    public int getP1_round() {return P1_round;}



    //Connects to the "Main Menu" button, Sends the player back to the start Menu
    @FXML
    private void goHome_P1() throws IOException {
        App.setRoot("Smarty_Party_HomeScreen");
    }


    //Connected to the "Play" button. Sends data and Player to the Gameplay scene
    @FXML
    private void P1_Gameplay() throws IOException {

        P1_Gameplay_Controller.setPlayer(getP1_subject(), getP1_level(), getP1_round());
        App.setRoot("P1_Gameplay_Sceen");

    }

}
