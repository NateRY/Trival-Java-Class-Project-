package org.trivia.controller;

import javafx.fxml.FXML;
import org.trivia.trivia.App;

import java.io.IOException;

public class GP_controler {


    public void goP1_NewGame() throws IOException {
        App.setRoot("P1_Gameplay_Entry");
    }

    public void goP2_NewGame() throws IOException {
        App.setRoot("P2_Gameplay_Entry");
    }

    @FXML
    private void goHome() throws IOException {
        App.setRoot("Smarty_Party_HomeScene");

    }

    public void goLeaderboard() throws IOException {
        App.setRoot("Leaderboard_Scene");
    }

    public void playGameP1() throws IOException {
        App.setRoot("P1_GamePlay_Sceen");
    }

    public void playGameP2() throws IOException {
        App.setRoot("");
    }

    public void goQuestionEntry() throws IOException {
        App.setRoot("Question_Entry_Scene");
    }



}
