package org.trivia.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import org.trivia.database.DatabaseHandler;
import org.trivia.trivia.App;

import java.io.IOException;

//This is the Controller class for the "Main Menu"
public class GP_controler extends DatabaseHandler {


    //constructor
    public GP_controler() {}

    //This brings player to the name entry scene
    public void goP1_NewGame() throws IOException {
        App.setRoot("P1_Gameplay_Entry");
    }

    //Brings player to 2 player game mode
    public void goP2_NewGame() throws IOException {
        App.setRoot("P2_Gamplay_Entry");
    }

    //Brings player to the Question entry mode, so player can
    //sumbit a questions to the database
    public void goQuestionEntry() throws IOException {
        App.setRoot("Question_Entry_Scene");
    }


    //Brings player to the leaderboard, send quary to database
    public void goLeaderboard() throws IOException {
        App.setRoot("leaderboard");
    }

    //makes sure that the "Main Menu" button leads to the start menu
    @FXML
    private void goHome() throws IOException {
        App.setRoot("Smarty_Party_HomeScreen");

    }

    //Connected to the quit button, close the program
    public void closeGame() throws IOException {
        Platform.exit();
    }


}