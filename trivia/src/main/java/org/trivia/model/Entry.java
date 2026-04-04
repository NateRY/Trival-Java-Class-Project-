package org.trivia.model;

import org.trivia.trivia.App;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Entry {
    private long id;
    private String question;
    private String answer;
    private String option2;
    private String option3;
    private String option4;
    private String hint;
    private String category;
    private String level;

    public Entry() {
        id = -1;
        question = "";
        answer = "";
        option2 = "";
        option3 = "";
        option4 = "";
        hint = "";
        category = "";
        level = "";
    }

    public Entry(String question, String answer, String option2, String option3, String option4, String hint, String category, String level) {
        this.id = -1;
        this.question = question;
        this.answer = answer;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.hint = hint;
        this.category = category;
        this.level = level;
    }

    public Entry(long id, String question, String answer, String option2, String option3, String option4, String hint, String category, String level) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.hint = hint;
        this.category = category;
        this.level = level;
    }

    public long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public List<String> getShuffledOptions() {
           List<String> options = Arrays.asList(answer, option2, option3, option4);
           Collections.shuffle(options);
           return options;
    }

    public String getHint() {
        return hint;
    }

    public String getCategory() {
        return category;
    }

    public String getLevel() {return level;}

    public void setId(long id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public long persist(){
        var id = App.getDbHandler().insertEntry(this);
        this.setId(id);
        return id;
    }

    public void delete(){
        App.getDbHandler().deleteEntry(id);
    }

    public void update(){
        App.getDbHandler().updateEntry(this);
    }

}


