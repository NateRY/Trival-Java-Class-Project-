package org.trivia.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
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

    protected  ArrayList<Button> AllAnswers = new ArrayList<>();

    private boolean started = true;

    @FXML protected VBox VBox_Center;

    //change to progress bar
    protected int gameRounds;

    @FXML private Label timerLabel;

    @FXML protected Label question_box;

    @FXML protected Button submitButton;

    @FXML protected Button button_one;
    @FXML protected Button button_two;
    @FXML protected Button button_three;
    @FXML protected Button button_four;


    @FXML protected Label RightWrong;

    protected String answer;
    protected String correctAnswer;
    protected int r_N_W;

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


        RightWrong.setText(" "+ String.valueOf(r_N_W));

        while(!runQuest.isEmpty())
        {
            while(started)
            {
                play();
                System.out.println(gameRounds + "\n");
            }
            System.out.println(peek().getQuestion()+ "\n");
            pop();

            gameRounds--;
        }



    }



    public boolean play()
    {
        //this will start the timer and add new question
        // and increase the rounds and progress bar

        Entry current_entry = peek();

        question_box.setText(current_entry.getQuestion());

        correctAnswer = current_entry.getAnswer();

        button_one.setText(current_entry.getAnswer());
        button_two.setText(current_entry.getOption2());
        button_three.setText(current_entry.getOption3());
        button_four.setText(current_entry.getOption4());



        AllAnswers.add(button_one);
        AllAnswers.add(button_two);
        AllAnswers.add(button_three);
        AllAnswers.add(button_four);


        button_one.setOnAction(e -> {
            answer = current_entry.getAnswer();
            System.out.println("Answer has been picked: "  + answer);

            AnswerPicked(AllAnswers, answer);

        });


        button_two.setOnAction(e -> {
            answer = current_entry.getOption2();
            System.out.println("Answer has been picked: "  + answer);

            AnswerPicked(AllAnswers, answer);
        });


        button_three.setOnAction(e -> {
            answer = current_entry.getOption3();
            System.out.println("Answer has been picked: "  + answer);

            AnswerPicked(AllAnswers, answer);

        });

        button_four.setOnAction(e -> {
            answer = current_entry.getOption4();
            System.out.println("Answer has been picked: "  + answer);

            AnswerPicked(AllAnswers, answer);
        });


        return started = false;


    }


    public void submitedAswer()
    {
        //submitButton

        Label EndRound = new Label();

        for(Button op : AllAnswers)
        {
            if((op.getText()).equals(correctAnswer))
            {
                op.setStyle("-fx-background-color: #116f59;" + "-fx-background-radius: 20;" +
                        "-fx-padding: 10");
            }
            else {
                op.setStyle("-fx-background-color: #c1121f;" + "-fx-background-radius: 20;"
                        + "-fx-padding: 10");
            }

        }

        if(correctAnswer.equals(answer))
        {
            r_N_W += 10;

            EndRound.setText("You Got it!!!");
            EndRound.setStyle("-fx-text-fill: #116f59;" +  "-fx-Font-Size: 25px;" + "-fx-font-family: Impact");

            VBox_Center.getChildren().add(EndRound) ;
        }
        else
        {
            EndRound.setText("Sorry, your answer is wrong");
            EndRound.setStyle("-fx-text-fill: #c1121f;" +  "-fx-Font-Size: 25px;" + "-fx-font-family: Impact");

            VBox_Center.getChildren().add(EndRound) ;
        }


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


    public void AnswerPicked(ArrayList<Button>AllAnswers, String answer)
    {

        for(Button op : AllAnswers)
        {
            if((op.getText()).equals(answer))
            {
                op.setStyle("-fx-background-color: #116f59;" + "-fx-background-radius: 20;" +
                    "-fx-padding: 10");
            }
            else {
                op.setStyle("-fx-background-color: #f4b400;" + "-fx-background-radius: 20;"
                        + "-fx-padding: 10");
            }

        }





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
