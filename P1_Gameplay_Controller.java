package org.trivia.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.trivia.database.DatabaseHandler;
import org.trivia.model.Entry;

import javafx.event.ActionEvent;
import java.util.Collections;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class P1_Gameplay_Controller extends P1Gameplay_Entry_Controller {

    private final DatabaseHandler dataH = new DatabaseHandler();
    protected ArrayList<Entry> AllQuest = new ArrayList<>();
    protected ArrayList<Entry> runQuest = new ArrayList<>();

    //change to progress bar
    protected int gameRounds;

    @FXML private Label timerLabel;

    @FXML protected Label question_box;


    @FXML protected Button button_one;
    @FXML protected Button button_two;
    @FXML protected Button button_three;
    @FXML protected Button button_four;


    @FXML protected Label RightWrong;




    String answer;

    @FXML
    public void initialize()
    {
        AllQuest = (ArrayList<Entry>) dataH.getEntries();
        System.out.println(AllQuest.size() + "\n");



//        for(Entry e: Quest) {
//            System.out.println( e.getLevel() + " " + e.getQuestion() + " | " + e.getOption3() +
//                    " | " + e.getOption2() + " | " + e.getOption4() + " \n " +e.getAnswer() + "\n"
//                    + " " + e.getHint() + " \n");
//        }

        int cat_id;

        for(Entry q : AllQuest)
        {
            cat_id = getCategoryId(q.getCategory());

            if(cat_id == 1)
            {
                push(q);
            }
        }

        System.out.println(runQuest.size() + "\n");
        Collections.shuffle(runQuest);





        while(!runQuest.isEmpty())
        {
            play();
            System.out.println(gameRounds + "\n");
            System.out.println(peek().getQuestion()+ "\n");
            pop();

            gameRounds--;
        }


    }

    public void play()
    {
        //this will start the timer and add new question
        // and increase the rounds and progress bar

        Entry current_entry = peek();

        question_box.setText(current_entry.getQuestion());

        button_one.setText(current_entry.getAnswer());
        button_one.setOnAction(actionEvent -> {
            answer = current_entry.getAnswer();
            System.out.println("Answer has been picked: "  + answer);

        });

        button_two.setText(current_entry.getOption2());
        button_two.setOnAction(actionEvent -> {
            answer = current_entry.getOption2();
            System.out.println("Answer has been picked: "  + answer);

        });

        button_three.setText(current_entry.getOption3());
        button_three.setOnAction(actionEvent -> {
            answer = current_entry.getOption3();
            System.out.println("Answer has been picked: "  + answer);

        });

        button_four.setText(current_entry.getOption4());
        button_four.setOnAction(actionEvent -> {
            answer = current_entry.getOption4();
            System.out.println("Answer has been picked: "  + answer);

        });







    }



    public void pickedAnswer(){

        //this is going to store the answer of the plays
        // to be match with the correct answer

    }

    public void push(Entry entry)
    {
        runQuest.add(entry);
    }

    public void pop()
    {
        runQuest.removeLast();

    }

    public Entry peek()
    {
        return runQuest.getLast();
    }





//    public void Timer()
//    {
//        AtomicInteger seconds = new AtomicInteger(200);
//        Timeline timer = null;
//
//        timerLabel.setText(String.valueOf(seconds.get()));
//
//        Timeline finalTimer = timer;
//        timer = new Timeline(
//                new KeyFrame(Duration.seconds(1), e -> {
//
//                    seconds.getAndDecrement();
//                    timerLabel.setText(String.valueOf(seconds.get()));
//
//                    if (seconds.get() <= 0) {
//                        finalTimer.stop();
//                        timerLabel.setText("0");
//                    }
//                })
//        );
            //timer.setCycleCount(Timeline.INDEFINITE);
//    }



}
