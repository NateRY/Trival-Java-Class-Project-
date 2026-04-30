package org.trivia.database;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.trivia.model.Entry;
import org.trivia.model.Game;

public class DatabaseHandler {
    private static final String url = "jdbc:mysql://localhost:3306/trivia";
    private static final String dbuser = "root";
    private static final String dbpassword = "NajiRtothemoon1@";

    public DatabaseHandler() {}

    public int getEntryCount(){
        int count = 0;
        String query = "SELECT count(1) FROM entries";
        try (Connection connection = DriverManager.getConnection(url, dbuser, dbpassword);){
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            resultSet.next();
            count = resultSet.getInt(1);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return count;
    }

    public long insertEntry(Entry entry){
        long id = -1;
        try (
                Connection conn = DriverManager.getConnection(url, dbuser, dbpassword);
        ){
            String query = "INSERT INTO entries(question, answer, option2, option3, option4, hint, category_id, level) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            int category_id = getCategoryId(entry.getCategory());
            if (category_id == -1){
                category_id = insertCategory(entry.getCategory());
            }

            try (PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
                pstmt.setString(1, entry.getQuestion());
                pstmt.setString(2, entry.getAnswer());
                pstmt.setString(3, entry.getOption2());
                pstmt.setString(4, entry.getOption3());
                pstmt.setString(5, entry.getOption4());
                pstmt.setString(6, entry.getHint());
                pstmt.setInt(7, category_id);
                pstmt.setString(8, entry.getLevel());
                System.out.println(pstmt.toString());
                int affectedRows = pstmt.executeUpdate();

                if (affectedRows == 1){
                    try (ResultSet rs = pstmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            id = rs.getLong(1); // Get the first column of the result set
                            System.out.println("New ID: " + id);
                        }
                    }
                }
            }
        }catch  (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    public int insertCategory(String category){
        int id = -1;
        try (Connection conn = DriverManager.getConnection(url, dbuser, dbpassword);){
            String query = String.format("INSERT INTO categories(category) VALUES ('%s')", category);
            Statement stmt = conn.createStatement();
            int affectedRows = stmt.executeUpdate(query);
            if(affectedRows == 1){
                id  = getCategoryId(category);
            }
        }catch  (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    public int getCategoryId(String category){
        int id = -1;
        try (Connection conn = DriverManager.getConnection(url, dbuser, dbpassword);){
            String query = String.format("SELECT id FROM categories WHERE category = '%s' LIMIT 1", category.strip());
            System.out.println(query);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()){
                id = rs.getInt(1);
            }
        }catch  (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    public void deleteEntry(long id){
        String query = String.format("DELETE FROM entries WHERE id = %d", id);
        try (
                Connection conn = DriverManager.getConnection(url, dbuser, dbpassword);
        ){
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        }catch(SQLException e){System.out.println(e.getMessage());}
    }

    public List<Entry> getEntries(){
        List<Entry> entries = new ArrayList<>();
        String  query = "SELECT e.*, c.category FROM entries e LEFT JOIN categories c ON e.category_id = c.id";
        try (Connection conn = DriverManager.getConnection(url, dbuser, dbpassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)){
            while (rs.next()) {
                entries.add(new Entry(rs.getInt("id"), rs.getString("question"),
                        rs.getString("answer"), rs.getString("option2"), rs.getString("option3"),
                        rs.getString("option4"), rs.getString("hint"), rs.getString("category"),
                        rs.getString("level")));
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return entries;
    }

    public void updateEntry(Entry entry){
        int category_id;
        try (
                Connection conn = DriverManager.getConnection(url, dbuser, dbpassword);
        ){
            category_id = getCategoryId(entry.getCategory());
            if (category_id == -1){
                category_id = insertCategory(entry.getCategory());
            }
            String query = "update entries set question = ?, answer = ?, option2 = ?, option3 = ?, " +
                    "option4 = ?, hint = ?, category_id = ?, level = ? where id = ?";
            Statement stmt = conn.createStatement();
            try (PreparedStatement pstmt = conn.prepareStatement(query,  Statement.RETURN_GENERATED_KEYS)){
                pstmt.setString(1, entry.getQuestion());
                pstmt.setString(2, entry.getAnswer());
                pstmt.setString(3, entry.getOption2());
                pstmt.setString(4, entry.getOption3());
                pstmt.setString(5, entry.getOption4());
                pstmt.setString(6, entry.getHint());
                pstmt.setInt(7, category_id);
                pstmt.setString(8, entry.getLevel());
                pstmt.setLong(9, entry.getId());
                pstmt.execute();
            }
        }catch  (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<String> getCategories(){
        List<String> categories = new ArrayList<>();
        String  query = "SELECT category FROM categories";
        try (Connection conn = DriverManager.getConnection(url, dbuser, dbpassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)){
            while (rs.next()) {
                categories.add(rs.getString("category"));
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return categories;
    }

    public List<Entry> getEntryByCategories(List<String> categories){
        List<Entry> entries = new ArrayList<>();
        String  query = "SELECT e.*, c.category FROM entries e LEFT JOIN  categories c ON e.category_id = c.id";

        if  (!categories.isEmpty()){
            query = "SELECT e.*, c.category  FROM entries e LEFT JOIN categories c on e.category_id = c.id WHERE category IN ('" +
                    String.join("','", categories) + "') ORDER BY RAND() LIMIT 10";
        }

        try (Connection conn = DriverManager.getConnection(url, dbuser, dbpassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)){
            while (rs.next()) {
                entries.add(new Entry(rs.getInt("id"), rs.getString("question"),
                        rs.getString("answer"), rs.getString("option2"), rs.getString("option3"),
                        rs.getString("option4"), rs.getString("hint"), rs.getString("category"),
                        rs.getString("level")));
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return entries;
    }

    public String getUserName(int userId){
        String  query = String.format("SELECT name FROM users WHERE id = %d", userId);
        String name = "";
        try (Connection conn = DriverManager.getConnection(url, dbuser, dbpassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)){
            if (rs.first()) {
                name = rs.getString("name");
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return name;
    }

    public int getUserId(String username){
        String  query = String.format("SELECT id FROM users WHERE name = '%s'", username.strip());
        System.out.println(query);
        int id = -1;
        try (Connection conn = DriverManager.getConnection(url, dbuser, dbpassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)){
            if (rs.next()) {
                id = rs.getInt("id");
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    public int insertUser(String userName){
        int id = -1;
        try (Connection conn = DriverManager.getConnection(url, dbuser, dbpassword);){
            String query = String.format("INSERT INTO users(name) VALUES ('%s')", userName);
            Statement stmt = conn.createStatement();
            int affectedRows = stmt.executeUpdate(query);
            if(affectedRows == 1){
                ResultSet rs = stmt.getGeneratedKeys();
                id = rs.getInt(1);
            }

        }catch  (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    public List<Game> getGames(){
        List<Game> games = new ArrayList<>();
        String  query = "SELECT g.id, u.name, g.game_date, c.category, g.level, g.score,  " +
                "FROM games g LEFT JOIN users u on g.user_id = u.id " +
                "LEFT JOIN categories c on g.category_id = c.id " +
                "ORDER BY score DESC";
        try (Connection conn = DriverManager.getConnection(url, dbuser, dbpassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)){
            while (rs.next()) {
                games.add(new Game(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getObject("game_date", LocalDateTime.class),
                        rs.getString("category"),
                        rs.getString("level"),
                        rs.getDouble("score")));
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return games;
    }

    public int saveGame(Game game){
        int id = -1;
        int categoryId = getCategoryId(game.getCategory());
        if  (categoryId == -1){
            categoryId = insertCategory(game.getCategory());
        }
        int userId = getUserId(game.getUsername());
        if  (userId == -1){
            userId = insertUser(game.getUsername());
        }

        String  query = String.format("INSERT INTO game(user_id, game_date, category_id, level, score values(%d, '%s', %d, %f))",
                userId, game.getGameDate(), categoryId, game.getScore());
        try (Connection conn = DriverManager.getConnection(url, dbuser, dbpassword);){
            Statement stmt = conn.createStatement();
            int affectedRows = stmt.executeUpdate(query);
            if(affectedRows == 1){
                ResultSet rs = stmt.getGeneratedKeys();
                id = rs.getInt(1);
            }
        }catch  (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }
}
