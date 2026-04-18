package org.trivia.controller;

import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import org.trivia.model.Entry;
import org.trivia.trivia.App;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class Manager {


    private Entry currentEntry;
    private List<Entry> entries;
    private StringProperty question, answer, option2, option3, option4, hint, category, level;
    private Form  editForm;

    @FXML
    public Button saveNewButton;
    @FXML
    public Button newButton;
    @FXML
    public Button cancelButton;
    @FXML
    public VBox centerVbox;
    @FXML
    public Button homeButton;
    @FXML
    public Button editButton;
    @FXML
    public Button deleteButton;
    @FXML
    public Button saveButton;
    @FXML
    public ToolBar toolBar;
    @FXML
    public TableView tblView;

    @FXML
    public void initialize(){
        toolBar.getItems().clear();
        editButton.setDisable(true);
        deleteButton.setDisable(true);
        toolBar.getItems().addAll(homeButton, newButton ,editButton, deleteButton);

        entries = App.getDbHandler().getEntries();
        System.out.println(entries.getFirst().getId());
        System.out.println(entries.getFirst().getQuestion());
        tblView.getItems().addAll(entries);
    }

    private Form editForm(StringProperty question, StringProperty answer,
                           StringProperty option2, StringProperty option3, StringProperty option4,
                           StringProperty hint, StringProperty category, StringProperty level){

        List<String> levels = Arrays.asList("easy", "medium", "hard");
        var levelsFiled = Field.ofSingleSelectionType(levels, levels.indexOf(level.getValue())).label("Level");

        levelsFiled.changedProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue){
                this.level = new SimpleStringProperty(levelsFiled.selectionProperty().getValue());
            }
        });

        return Form.of(
                Group.of(
                        Field.ofStringType(question).label("Question").required(true),
                        Field.ofStringType(answer).label("Answer").required(true),
                        Field.ofStringType(option2).label("Option 2").required(true),
                        Field.ofStringType(option3).label("Option 3").required(true),
                        Field.ofStringType(option4).label("Option 4").required(true),
                        Field.ofStringType(hint).label("Hint"),
                        Field.ofStringType(category).label("Category"),
                        levelsFiled
//                        Field.ofStringType(level).label("Level")
                )
        ).title("Entry");

    };

    @FXML
    public void selectCurrentEntry(){
        currentEntry = (Entry) tblView.getSelectionModel().getSelectedItem();
        editButton.setDisable(false);
        deleteButton.setDisable(false);
    }

    @FXML
    public void deleteEntry(){
        if (currentEntry != null){
            currentEntry.delete();
            tblView.getItems().remove(currentEntry);
            currentEntry = null;
            editButton.setDisable(true);
            deleteButton.setDisable(true);
        }
    }

    @FXML
    public void showEditForm(){
        if (currentEntry != null){
            toolBar.getItems().clear();
            editButton.setDisable(true);
            deleteButton.setDisable(true);
            toolBar.getItems().addAll(homeButton, saveButton, cancelButton);

            question = new SimpleStringProperty(currentEntry.getQuestion());
            answer = new SimpleStringProperty(currentEntry.getAnswer());
            option2 = new SimpleStringProperty(currentEntry.getOption2());
            option3 = new SimpleStringProperty(currentEntry.getOption3());
            option4 = new SimpleStringProperty(currentEntry.getOption4());
            hint = new SimpleStringProperty(currentEntry.getHint());
            category = new SimpleStringProperty(currentEntry.getCategory());
            level = new SimpleStringProperty(currentEntry.getLevel());

            centerVbox.getChildren().clear();
            editForm = editForm(question, answer, option2, option3, option4, hint, category, level);
            centerVbox.getChildren().add(new FormRenderer(editForm));

        }
    }

    @FXML
    public void showNewForm(){
        currentEntry = new Entry();
        toolBar.getItems().clear();
        editButton.setDisable(true);
        deleteButton.setDisable(true);
        toolBar.getItems().addAll(homeButton, saveNewButton, cancelButton);

        question = new SimpleStringProperty("");
        answer = new SimpleStringProperty("");
        option2 = new SimpleStringProperty("");
        option3 = new SimpleStringProperty("");
        option4 = new SimpleStringProperty("");
        hint = new SimpleStringProperty("");
        category = new SimpleStringProperty("");
        level = new SimpleStringProperty("easy");

        centerVbox.getChildren().clear();
        editForm = editForm(question, answer, option2, option3, option4, hint, category, level);
        centerVbox.getChildren().add(new FormRenderer(editForm));
    }


    @FXML
    public void saveEntry(){
        editForm.persist();
        currentEntry.setQuestion(question.getValue());
        currentEntry.setAnswer(answer.getValue());
        currentEntry.setOption2(option2.getValue());
        currentEntry.setOption3(option3.getValue());
        currentEntry.setOption4(option4.getValue());
        currentEntry.setHint(hint.getValue());
        currentEntry.setCategory(category.getValue());
        currentEntry.setLevel(level.getValue());

        currentEntry.update();
        tblView.getItems().clear();
        tblView.getItems().addAll(entries);

        cancelEdit();
    }

    @FXML
    public void saveNewEntry(){
        editForm.persist();
        currentEntry.setQuestion(question.getValue());
        currentEntry.setAnswer(answer.getValue());
        currentEntry.setOption2(option2.getValue());
        currentEntry.setOption3(option3.getValue());
        currentEntry.setOption4(option4.getValue());
        currentEntry.setHint(hint.getValue());
        currentEntry.setCategory(category.getValue());
        currentEntry.setLevel(level.getValue());

        long current_id = currentEntry.persist();
        currentEntry.setId(current_id);
        entries.add(currentEntry);
        tblView.getItems().add(currentEntry);

        cancelEdit();
    }

    @FXML
    public void cancelEdit(){
        toolBar.getItems().clear();
        editButton.setDisable(true);
        deleteButton.setDisable(true);
        toolBar.getItems().addAll(homeButton, newButton ,editButton, deleteButton);
        centerVbox.getChildren().clear();
        centerVbox.getChildren().add(tblView);

    }


    @FXML
    public void goHome(ActionEvent event) throws IOException {
        App.setRoot("home");
    }
}
