package org.trivia.model;

import org.trivia.trivia.App;
import java.time.LocalDateTime;

public class Game {
    private int id;
    private String username;
    private LocalDateTime gameDate;
    private String category;
    private String level;
    private Double score;

    public Game(String username) {
        this.username = username;
        this.gameDate = LocalDateTime.now();
    }

    public Game(String username, String category, String level) {
        this.id = -1;
        this.username = username;
        this.gameDate = LocalDateTime.now();
        this.category = category;
        this.level = level;
    }

    public Game(int id, String username, LocalDateTime gameDate, String category, String level, Double score) {
        this.id = id;
        this.username = username;
        this.gameDate = gameDate;
        this.category = category;
        this.level = level;
        this.score = score;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setGameDate(LocalDateTime gameDate) {
        this.gameDate = gameDate;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getGameDate() {
        return gameDate;
    }

    public String getCategory() {
        return category;
    }

    public String getLevel() {
        return level;
    }

    public Double getScore() {
        return score;
    }

    public int persist(){
        id = App.getDbHandler().saveGames(this);
        return id;
    }
}
