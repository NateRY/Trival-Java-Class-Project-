package org.trivia.trivia;

import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.trivia.database.DatabaseHandler;

import java.io.IOException;

public class App extends Application {
    private static Scene scene;
    private static DatabaseHandler dbHandler;

    @Override
    public void start(Stage stage) throws Exception {
        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
        dbHandler = new DatabaseHandler();


        Parent root = loadFXML("home");
        scene = new Scene(root, 800, 600);
        // scene.getStylesheets().add(App.class.getResource("styles/style.css").toExternalForm());
        stage.setTitle("Trivia Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/org/trivia/view/" + fxml + ".fxml"));
        return fxmlLoader.load();

    }

    public static DatabaseHandler getDbHandler() {
        return dbHandler;
    }

    public static void main(String[] args) {
        launch();
    }
}
