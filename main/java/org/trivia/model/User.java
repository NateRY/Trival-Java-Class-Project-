package org.trivia.model;

import org.trivia.trivia.App;

public class User {
    private int id;
    private String name;

    public User(String name) {
        this.id = App.getDbHandler().getUserId(name);
        if (id == -1){
            id = App.getDbHandler().insertUser(name);
        }
        this.name = name;
    };

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public void persist(){
        id = App.getDbHandler().insertUser(name);
    }

}
