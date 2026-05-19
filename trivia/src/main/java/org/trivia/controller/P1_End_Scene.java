package org.trivia.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.trivia.trivia.App;

import java.io.IOException;

public class P1_End_Scene extends P1Gameplay_Entry_Controller{

    @FXML private TextFlow playerStats;

    private static int P1score;
    private static int rounds;

    //Get the image of congratulation
    @FXML private ImageView congrats_View;
    Image congrats = new Image(
            getClass().getResource("/org/trivia/view/Congratulations_SP_End.png").toExternalForm()
    );



    //to send player to the Leadbaord scene
    public void goLeaderboard() throws IOException {
        //App.setRoot("Leaderboard");
    }

    @FXML public void initialize()
    {
        Text Vline = new Text(" | ");
        Text Vline2 = new Text(" | ");
        Text Vline3 = new Text(" | ");

        Text P1name = new Text();
        P1name.setText(getPlayerName());

        Text P1level = new Text();
        P1level.setText(getP1_level());



        P1name.setStyle("-fx-text-fill: black; ");

        Text scoreTxt = new Text();
        scoreTxt.setText("Score: " + String.valueOf(P1score));
        scoreTxt.setStyle("-fx-text-fill: #116f59; ");

        P1level.setStyle("-fx-text-fill: #4a0000; ");

        Text roundsTxt = new Text();
        roundsTxt.setText("Rounds: " +String.valueOf(rounds));
        roundsTxt.setStyle("-fx-text-fill: #1a9df1; ");



        //playerStats.setStyle("-fx-background-color: white;" + "-fx-font-size: 25px;");
        playerStats.getChildren().addAll(P1name, Vline, scoreTxt, Vline2, P1level, Vline3, roundsTxt);

        congrats_View.setImage(congrats);

    }

    public static void setPlayer(int score, int round) {
        P1score = score;
        rounds = round;
    }


    public void goHome() throws IOException {
        P1_Gameplay_Controller.cleanGame();
        App.setRoot("Smarty_Party_HomeScreen");
    }
}

