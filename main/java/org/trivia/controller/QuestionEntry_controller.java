package org.trivia.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.trivia.database.DatabaseHandler;
import org.trivia.model.Entry;

import java.sql.Connection;
import java.sql.SQLException;

public class QuestionEntry_controller extends GP_controler {

    private DatabaseHandler db = new DatabaseHandler();


    @FXML private TextFlow questionEntryFlow;

    @FXML private TextField Level_Entry;
    @FXML private TextField Subject_Entry;
    @FXML private TextField Correct_Answer_Entry;
    @FXML private TextField Question_Entry;
    @FXML private TextField Option2_Entry;
    @FXML private TextField Option3_Entry;
    @FXML private TextField Option4_Entry;
    @FXML private TextField Hint_Entry;

    @FXML
    public void initialize() throws SQLException {

        insertEntry(new Entry());

        Connection conn = getConnection();
        seedData(conn); //has changed


        Text level = new Text("Level, ");
        level.setId("level");
        level.setFill(Color.web("#4a0000"));

        Text quest = new Text(" Question, ");
        level.setId("level");
        level.setFill(Color.BLACK);

        Text W_answer = new Text(" WrongAnswers, ");
        level.setId("level");
        level.setFill(Color.RED);

        Text correct_answer = new Text(" CorrectAnswers, ");
        level.setId("level");
        level.setFill(Color.web("#116f59"));

        Text hint = new Text(" Hint, ");
        level.setId("level");
        level.setFill(Color.web("#1a9df1"));

        String setup = "";




        setup = level.getText() + quest.getText() + W_answer.getText()
                + correct_answer.getText() + hint.getText();

        Text text = new Text(setup);
        questionEntryFlow.getChildren().add(text);

        //questionEntryFlow.getChildren().add((Node) getEntries());




    }


    public void submitQuestion() throws SQLException {


        Entry entry = new Entry();

        entry.setLevel(Level_Entry.getText());
        entry.setCategory(Subject_Entry.getText());
        entry.setAnswer(Correct_Answer_Entry.getText());
        entry.setQuestion(Question_Entry.getText());
        entry.setOption2(Option2_Entry.getText());
        entry.setOption3(Option3_Entry.getText());
        entry.setOption4(Option4_Entry.getText());
        entry.setHint(Hint_Entry.getText());

        insertEntry(entry);
        //displayQuestionEntry();

    }


//    public void displayQuestionEntry() throws SQLException {
//
//
//       // questionEntryFlow.getChildren().add();
//    }



}
