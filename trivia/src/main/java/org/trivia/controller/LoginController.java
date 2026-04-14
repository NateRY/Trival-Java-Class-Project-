package org.trivia.controller;

import com.dlsc.formsfx.model.structure.PasswordField;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import org.trivia.trivia.App;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginController {

    private final List<Text> categoryText = new ArrayList<>();
    private final List<String> selectedCategories = new ArrayList<>();

    @FXML
    private TextField userTextField;
    @FXML
    private TilePane categoryArea;

    @FXML
    public void initialize() {
        List<String> categories = App.getDbHandler().getCategories();
        for(String c : categories){
            Text t = new Text(c);
            t.setStyle("-fx-font-family: Monaco; -fx-font-size: 20px;");
            categoryText.add(t);
        }
        categoryArea.getChildren().addAll(categoryText);
        for (Text t : categoryText) {
            t.setOnMouseClicked(event -> {
                if (t.getText().contains("*")) {
                    t.setText(t.getText().replace("*", ""));
                    selectedCategories.remove(t.getText());
                }
                else{
                    selectedCategories.add(t.getText());
                    t.setText(t.getText() + "*");
                }
            });
        }
    }

    @FXML
    public void newGame() throws IOException {
        if (!userTextField.getText().strip().equals("")) {
            org.trivia.controller.GameController.newGame(userTextField.getText(), selectedCategories);
            App.setRoot("gameView");
        }else{
            userTextField.clear();
            userTextField.requestFocus();
        }
    }
}
