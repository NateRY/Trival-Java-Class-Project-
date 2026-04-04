module org.trivia.trivia {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires atlantafx.base;

    opens org.trivia.trivia to javafx.fxml;
    opens org.trivia.controller to javafx.fxml;

    exports org.trivia.trivia;
    exports org.trivia.model;
    exports org.trivia.controller;
    exports org.trivia.database;
}