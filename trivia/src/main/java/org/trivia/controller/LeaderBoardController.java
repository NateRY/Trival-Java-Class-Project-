package org.trivia.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import org.trivia.model.Game;
import org.trivia.trivia.App;

import java.io.IOException;
import java.util.List;

public class LeaderBoardController {

    @FXML
    private GridPane lbGridPane;

    @FXML
    public void initialize() {
        lbGridPane.getChildren().clear();
        List<Game> games = App.getDbHandler().getGames();
        for (int i = 0; i < games.size(); i++) {
            StackPane rank = new StackPane();
            Circle circle = new Circle();
            circle.setRadius(12);
            circle.setFill(Color.valueOf("#26ff1f"));
            circle.setStroke(Color.GRAY);
            rank.getChildren().add(circle);
            rank.getChildren().add(new Label("" + (i + 1)));

            lbGridPane.add(rank, 0, i);

            Label userNameLabel = new Label(games.get(i).getUsername());
            userNameLabel.setAlignment(Pos.CENTER);
            userNameLabel.setPrefHeight(29);
            userNameLabel.setPrefWidth(276);

            userNameLabel.setStyle("-fx-background-radius: 10; -fx-background-color: lightblue; -fx-font-family: monaco; -fx-font-size: 16;");

            lbGridPane.add(userNameLabel, 1, i);

            Label scoreLabel = new Label(games.get(i).getScore().toString());
            scoreLabel.setAlignment(Pos.CENTER);
            scoreLabel.setPrefHeight(29);
            scoreLabel.setPrefWidth(100);

            scoreLabel.setStyle("-fx-background-radius: 10; -fx-background-color: lightblue; -fx-font-family: monaco; -fx-font-size: 16;");

            lbGridPane.add(scoreLabel, 2, i);
        }

    }

    @FXML
    private void exit() {
        System.exit(0);
    }

    @FXML
    public void handleLogin() throws IOException {
        App.setRoot("login");
    }

    @FXML
    private void goHome() throws IOException {
        App.setRoot("home");
    }
}
