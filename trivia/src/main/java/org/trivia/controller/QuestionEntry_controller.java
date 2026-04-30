
package org.trivia.controller;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.trivia.database.DatabaseHandler;
import org.trivia.model.Entry;
import org.trivia.trivia.App;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class QuestionEntry_controller extends DatabaseHandler {

    private final DatabaseHandler db = new DatabaseHandler();
    boolean HowTo = true;


    @FXML private TextFlow questionEntryFlow;

    @FXML private ComboBox Level_entry;
    @FXML private ComboBox subject_entry;
    @FXML private TextField Correct_Answer_Entry;
    @FXML private TextField Question_Entry;
    @FXML private TextField Option2_Entry;
    @FXML private TextField Option3_Entry;
    @FXML private TextField Option4_Entry;
    @FXML private TextField Hint_Entry;

    @FXML private Label submit_label;

    @FXML
    public void initialize() throws SQLException {

        insertEntry(new Entry());
        System.out.println(getEntryCount() + "--------- \n");

        ArrayList<Entry> entries = (ArrayList<Entry>) db.getEntries();
        System.out.println(entries.size() + " this is the array size.");


        String level_1 = "Easy";
        String level_2 = "Medium";
        String level_3 = "Hard";
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





        for(Entry e: entries){

            Text space = new Text(" ");
            Text Vline = new Text(" | ");
            Text Vline2 = new Text(" | ");
            Text Vline3 = new Text(" | ");
            Text Sline = new Text("\n_______________________________ \n");

            Text level = new Text("Level, ");
            level.setFill(Color.web("#4a0000"));

            Text quest = new Text(" Question, ");
            quest.setFill(Color.BLACK);

            Text w_answer = new Text(" WrongAnswers, ");
            w_answer.setFill(Color.RED);

            Text correct_answer = new Text(" CorrectAnswers, ");
            correct_answer.setFill(Color.web("#116f59"));

            Text hint = new Text(" Hint, ");
            hint.setFill(Color.web("#1a9df1"));


            if(HowTo)
            {
                questionEntryFlow.getChildren().addAll(level,quest,Vline,w_answer, Vline2,
                        correct_answer,space, hint, Sline);

                HowTo = false;
            }
            else{
                level.setText(e.getLevel());
                quest.setText(e.getQuestion());
                w_answer.setText(e.getOption2());
                correct_answer.setText(e.getAnswer());
                hint.setText(e.getHint());

                //where its failing
                questionEntryFlow.getChildren().addAll(level,space,quest, Vline, w_answer, Vline2,
                        correct_answer,Vline3, hint, Sline);
            }

            }




    }


    public void submitQuestion() throws SQLException {


        Entry entry = new Entry();

        entry.setLevel((String) Level_entry.getValue());
        entry.setCategory((String) subject_entry.getValue());

        entry.setAnswer(Correct_Answer_Entry.getText());
        entry.setQuestion(Question_Entry.getText());
        entry.setOption2(Option2_Entry.getText());
        entry.setOption3(Option3_Entry.getText());
        entry.setOption4(Option4_Entry.getText());
        entry.setHint(Hint_Entry.getText());

        insertEntry(entry);
        submit_label.setText("Done!");


    }


    public void goHome() throws IOException {
        App.setRoot("Smarty_Party_HomeScreen");
    }



}