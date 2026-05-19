package org.trivia.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.trivia.database.DatabaseHandler;
import org.trivia.model.Entry;

import org.trivia.trivia.App;

import java.io.IOException;
import java.util.Collections;

import java.util.ArrayList;
import java.util.List;

public class P1_Gameplay_Controller {

    //Connect the database to the Gameplay scene
    private final DatabaseHandler dataH = new DatabaseHandler();

    //After quarry is sent, this stores available question information
    protected static ArrayList<Entry> runQuest = new ArrayList<>();

    //This stores answer and button so they can be adjusted
    protected static ArrayList<Button> AllAnswers = new ArrayList<>();

    //This stores the anwers to be shuffled and ready to be assigned a button
    protected static ArrayList<String> options = new ArrayList<>();


    //Holds the Subject, level, and round picked in previous scene (Player Entry)
    private static String catorgory;
    protected static String P1_level;
    protected static int P1_round;


    //This Sentinal value stops the array from overflowing the stack
    private static boolean runStarted = true;

    //The scene is conprised of a boarder pane, in the center is a Vbox
    @FXML protected VBox VBox_Center;

    //This holds the fxid for the Top left coner (Timer)
    //The Timeline that sets up the timer
    //The int value that will be decremented
    @FXML private Label timerLabel;
    private static Timeline countdown;
    private int seconds =0;


    //fxid: for the "Rounds" lable in the top right coner (Rounds)
    //int value stores the current round (incremented every question)
    @FXML private Label roundLabel;
    protected int gameRounds = 0;


    //Connecets the question Lable in the cent of the VBox
    @FXML protected Label question_box;

    //Label on top of the hint and submit button
    @FXML protected Label hintLabel;
    @FXML protected Button submitButton;


    //fxid: for option buttons
    @FXML protected Button button_one;
    @FXML protected Button button_two;
    @FXML protected Button button_three;
    @FXML protected Button button_four;



    //fxid: for bottom left coner label
    @FXML protected Label RightWrong;

    //fxid: for bottom right progressBar
    @FXML private ProgressBar progressBar;

    //how much it start with, roungs 1
    private static double progressBarNumber = 0.35;

    //Stores the answer given by the player
    protected String answer;

    //Stores the correct answer stored from the database
    protected String correctAnswer;

    //stores player score
    protected static int score = 0;


    //runQuest (stores question from quarry) is a stack
    //it allows for push,pop,and peek. However, push is used only to add
    // a new subject to this current run
    public void push(Entry entry)
    {
        runQuest.add(entry);
    }
    public void pop() { runQuest.removeLast(); }
    public Entry peek()
    {
        return runQuest.getLast();
    }


    //Gets subject, level of difficulty, and number of rounds from Player Entry scene
    public static void setPlayer( String cat,String level, int round)
    {
        catorgory = cat;
        P1_level = level;
        P1_round = round;
    }

    @FXML
    public void initialize() throws IOException {

        //Makes sure stack is filled up with one subject at a time
        if(runStarted)
        {
            runQuest = (ArrayList<Entry>) dataH.getEntryByCategories(List.of(catorgory));
            System.out.println("Run Question have been started\n");
            runStarted = false;
        }

        //Sends to the terminal for debugging
        System.out.println("runQuest stack size: " + runQuest.size() + "\n");

        //Shuffle the question for random question output of giving subject
        Collections.shuffle(runQuest);

        //if the query came up emtpy sends that to the terminal
        if(runQuest.isEmpty()){
            System.out.println("There is no questions in this round");
        }

        //for debugging, makes sure score is at 0
        RightWrong.setText("Current Score: "+ String.valueOf(score));

        //stares the game
        play();
        System.out.println("Round: " +gameRounds + "\n");

        //sends current question to the terminal for debugging
        System.out.println(peek().getQuestion()+ "\n");

        //sets up process bar
        progressBar.setProgress(progressBarNumber);



    }





    public void play()
    {
        //this will start the timer and add new question
        // and increase the rounds and progress bar
        // and display current round
        progressBar.setProgress(progressBarNumber);
        gameRounds++;
        roundLabel.setText("Round " + String.valueOf(gameRounds));


        //makes sure buttons are available
        disableAnswerButtons(false);

        //gets question from top of the stack
        Entry current_entry = peek();

        //loads question to question label
        question_box.setText(current_entry.getQuestion());

        //stores correct answer
        correctAnswer = current_entry.getAnswer();

        //makes sure Hint is not shown unless requested
        hintLabel.setVisible(false);

        //makes sure both containers for buttons and answer are clear
        options.clear();
        AllAnswers.clear();


        //adds to option container
        options.add(current_entry.getAnswer());
        options.add(current_entry.getOption2());
        options.add(current_entry.getOption3());
        options.add(current_entry.getOption4());

        //shuffles options
        Collections.shuffle(options);

        //adds buttons to container
        AllAnswers.add(button_one);
        AllAnswers.add(button_two);
        AllAnswers.add(button_three);
        AllAnswers.add(button_four);


        //assigns buttons with options
        for(int i =0; i < AllAnswers.size(); i++)
        {
            AllAnswers.get(i).setText(options.get(i));
        }


        //gives action to option buttons and stores answer
        //also show answer in the terminal
        button_one.setOnAction(e -> {
            answer = AllAnswers.getFirst().getText();
            System.out.println("Answer has been picked: "  + answer);

            AnswerPicked(AllAnswers, answer);

        });

        button_two.setOnAction(e -> {
            answer = AllAnswers.get(1).getText();
            System.out.println("Answer has been picked: "  + answer);

            AnswerPicked(AllAnswers, answer);
        });

        button_three.setOnAction(e -> {
            answer = AllAnswers.get(2).getText();
            System.out.println("Answer has been picked: "  + answer);

            AnswerPicked(AllAnswers, answer);

        });

        button_four.setOnAction(e -> {
            answer = AllAnswers.get(3).getText();
            System.out.println("Answer has been picked: "  + answer);

            AnswerPicked(AllAnswers, answer);
        });

        //starts the timer
        Time();


    }



    //for testing and debuting
    //sends the number of seconds added to the timer per second
    int somthing = 0;


    public void Time()
    {

        //timer starts with 45seconds
        seconds = 45;

        //Give the number to the Timer label
        timerLabel.setText(String.valueOf(seconds));

        //give seconds to timer
        countdown = new Timeline(new KeyFrame(Duration.seconds(1), e->{

            //decrements timer
            seconds--;

            //updates Timer label
            timerLabel.setText(String.valueOf(seconds));

            //if Timer ends befor option is picked, the timer stops
            //and "submit" is automatically called
            if(seconds <= 0)
            {
                timerLabel.setText("Times UP!");
                countdown.stop();
                submitedAswer();
            }

            //used for debugging, shows the amount of seconds going by
            System.out.println("Timer is going by 1 second." + somthing++);

        }));

        countdown.setCycleCount(Timeline.INDEFINITE);
        countdown.play();

    }



    //method to make the button picked a different color then othe buttons
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



    //makes Hint visible
    @FXML public void showHint(){

        hintLabel.setText(peek().getHint());
        hintLabel.setVisible(true);

    }


    @FXML public void submitedAswer()
    {


        //Shows feedback on whether Player was correct
        Label EndRound = new Label();


        //makes the correct button one color and makes other options another
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
            score += 10;

            EndRound.setText("You Got it!!!");
            EndRound.setStyle("-fx-text-fill: #116f59;" +  "-fx-Font-Size: 25px;" + "-fx-font-family: Impact");

            VBox_Center.getChildren().add(EndRound) ;
        }
        else
        {
            score -= 10;
            EndRound.setText("Sorry, your answer is wrong");
            EndRound.setStyle("-fx-text-fill: #c1121f;" +  "-fx-Font-Size: 25px;" + "-fx-font-family: Impact");

            VBox_Center.getChildren().add(EndRound) ;
        }


        //makes sure Timer has stopped
        if(countdown != null){
            countdown.stop();
        }

        //Removes the current question from the stack
        pop();

        //disables option buttons so player choice is final
        disableAnswerButtons(true);


        //Creates a continued button to load next question
        Button continueButton = new Button("CONTINUE");
        continueButton.setStyle("-fx-background-color: #37344e;" + "-fx-text-fill: #f4b400 ; "
                + "-fx-background-radius: 20;" + "-fx-padding: 10");


        continueButton.setOnAction(e -> {
            try {

                //if button is press but stack is empty, end game
                if(runQuest.isEmpty()){

                    EndofGame_P1();
                }
                continueButtonClicked();

            } catch (IOException ex) {
                ex.printStackTrace(System.err);
            }

            //updates score
            RightWrong.setText(" "+ String.valueOf(score));

            //removes the continue button and label
            VBox_Center.getChildren().removeAll(EndRound, continueButton);
        });


        //adds button to center-Vbox
        VBox_Center.getChildren().add(continueButton);

    }


    private void disableAnswerButtons(boolean state) {
        button_one.setDisable(state);
        button_two.setDisable(state);
        button_three.setDisable(state);
        button_four.setDisable(state);
        submitButton.setDisable(state);

    }



    public void continueButtonClicked() throws IOException {


        for(Button op : AllAnswers)
        {
            op.setStyle("-fx-background-color: #f4b400;" + "-fx-background-radius: 20;" + "-fx-padding: 10");
        }

        hintLabel.setVisible(false);

        //fix this get it to update when the round ends
        progressBarNumber += 0.15;
        if(runQuest.isEmpty()){
            EndofGame_P1();
        }
        else
        {
            play();
        }


    }



    @FXML public void goHome() throws IOException {
        cleanGame();
        App.setRoot("Smarty_Party_HomeScreen");
    }


    public void EndofGame_P1() throws IOException {

        P1_End_Scene.setPlayer(score, P1_round);
        App.setRoot("P1_End_Scene");
    }

    public static void cleanGame()
    {
        runStarted = true;
        runQuest.clear();
        AllAnswers.clear();
        options.clear();
        score = 0;
        progressBarNumber = 0;
        catorgory = null;
        P1_level = null;
        P1_round = 0;
        if(countdown != null){

            countdown.stop();
        }

    }



}


