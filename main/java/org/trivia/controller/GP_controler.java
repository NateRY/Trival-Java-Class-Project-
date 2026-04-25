package org.trivia.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.trivia.database.DatabaseHandler;
import org.trivia.trivia.App;

import java.awt.event.MouseEvent;
import java.beans.EventHandler;
import java.io.IOException;

public class GP_controler extends DatabaseHandler {


    public GP_controler() {}

    //This brings player to the name entry scene
    public void goP1_NewGame() throws IOException {
        App.setRoot("P1_Gameplay_Entry");
    }

    public void goP2_NewGame() throws IOException {
        App.setRoot("P2_Gamplay_Entry");
    }

    public void goQuestionEntry() throws IOException {
        App.setRoot("Question_Entry_Scene");
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

    @FXML
    private void goHome() throws IOException {
        App.setRoot("Smarty_Party_HomeScreen");

    }

    public void closeGame() throws IOException {
        Platform.exit();
    }


}
