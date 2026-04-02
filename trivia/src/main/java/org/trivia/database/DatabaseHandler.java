package org.trivia.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.trivia.model.Entry;

public class DatabaseHandler {
    private static final String url = "jdbc:mysql://localhost:3306/trivia";
    private static final String username = "andrew";
    private static final String password = "trivia";
//    private static Connection connection;
//    private static Statement statement;


    public DatabaseHandler() {
        try (Connection conn = DriverManager.getConnection(url, username, password);) {
            if (conn != null) {
                String createEntriesTable = "CREATE TABLE IF NOT EXISTS entries (" +
                        "question TEXT NOT NULL," +
                        "answer TEXT NOT NULL," +
                        "option2 TEXT NOT NULL," +
                        "option3 TEXT NOT NULL," +
                        "option4 TEXT NOT NULL," +
                        "hint TEXT," +
                        "category TEXT NOT NULL," +
                        "level TEXT NOT NULL" +
                        ");";
                String createScoresTable = "CREATE TABLE IF NOT EXISTS scores (" +
                        "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                        "userName TEXT NOT NULL," +
                        "score INTEGER NOT NULL," +
                        "category TEXT NOT NULL," +
                        "level TEXT NOT NULL," +
                        "date TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                        ");";
                Statement stmt = conn.createStatement();
                stmt.execute(createEntriesTable);
                stmt.execute(createScoresTable);
                seedData(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void seedData(Connection conn) throws SQLException {
        String countSql = "SELECT COUNT(*) FROM entries";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(countSql);
        if (rs.next() && rs.getInt(1) == 1) {
            String insertSql = "INSERT INTO entries(question, answer, option2, option3, option4, hint, category, level) VALUES(?,?,?,?,?,?,?,?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                // SCIENCE
                addSeed(pstmt, "What is the boiling point of water?", "100°C", "0°C", "50°C", "212°C", "Standard pressure.", "Science", "Easy");
                addSeed(pstmt, "Red Planet?", "Mars", "Venus", "Jupiter", "Saturn", "God of war.", "Science", "Easy");
                addSeed(pstmt, "What is the powerhouse of the cell?", "Mitochondria", "Nucleus", "Ribosome", "Vacuole", "Biology 101.", "Science", "Easy");
                addSeed(pstmt, "Atomic number of Gold?", "79", "47", "82", "50", "Symbol: Au.", "Science", "Medium");
                addSeed(pstmt, "Closest star to Earth?", "Sun", "Proxima Centauri", "Sirius", "Alpha Centauri", "Provides light.", "Science", "Medium");
                addSeed(pstmt, "Theory of General Relativity?", "Einstein", "Newton", "Bohr", "Hawking", "1915.", "Science", "Hard");
                addSeed(pstmt, "Which element has symbol 'K'?", "Potassium", "Krypton", "Kalium", "Calcium", "Found in bananas.", "Science", "Hard");

                // HISTORY
                addSeed(pstmt, "WWII End Year?", "1945", "1944", "1946", "1950", "Mid-40s.", "History", "Medium");
                addSeed(pstmt, "First US President?", "Washington", "Adams", "Jefferson", "Lincoln", "On $1 bill.", "History", "Medium");
                addSeed(pstmt, "Who was the Maid of Orleans?", "Joan of Arc", "Marie Antoinette", "Mary Queen of Scots", "Elizabeth I", "French heroine.", "History", "Easy");
                addSeed(pstmt, "Empire of Julius Caesar?", "Roman", "Greek", "Persian", "Ottoman", "Veni, vidi, vici.", "History", "Easy");
                addSeed(pstmt, "French Revolution Start Year?", "1789", "1776", "1812", "1848", "Bastille Day.", "History", "Hard");
                addSeed(pstmt, "Signing of Magna Carta?", "1215", "1066", "1492", "1588", "Runnymede.", "History", "Hard");

                // GEOGRAPHY
                addSeed(pstmt, "Capital of Kazakhstan?", "Astana", "Almaty", "Bishkek", "Tashkent", "Central Asia.", "Geography", "Hard");
                addSeed(pstmt, "Longest River?", "Nile", "Amazon", "Yangtze", "Mississippi", "In Africa.", "Geography", "Hard");
                addSeed(pstmt, "Largest Continent?", "Asia", "Africa", "North America", "Europe", "East.", "Geography", "Easy");
                addSeed(pstmt, "Largest Ocean?", "Pacific", "Atlantic", "Indian", "Arctic", "Between Asia and Americas.", "Geography", "Easy");
                addSeed(pstmt, "Country with most lakes?", "Canada", "USA", "Russia", "Brazil", "North America.", "Geography", "Medium");
                addSeed(pstmt, "Smallest country?", "Vatican City", "Monaco", "San Marino", "Liechtenstein", "Inside Rome.", "Geography", "Hard");
                addSeed(pstmt, "Tallest mountain?", "Everest", "K2", "Kangchenjunga", "Lhotse", "Himalayas.", "Geography", "Easy");

                pstmt.executeBatch();
            }
        }
    }

    private void addSeed(PreparedStatement pstmt, String q, String a, String o2, String o3, String o4, String h, String c, String l) throws SQLException {
        pstmt.setString(1, q);
        pstmt.setString(2, a);
        pstmt.setString(3, o2);
        pstmt.setString(4, o3);
        pstmt.setString(5, o4);
        pstmt.setString(6, h);
        pstmt.setString(7, c);
        pstmt.setString(8, l);
        pstmt.addBatch();
    }

    public int getEntryCount(){
        int count = 0;
        String query = "SELECT count(1) FROM entries";
        try (Connection connection = DriverManager.getConnection(url, username, password);){
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
                Connection conn = DriverManager.getConnection(url, username, password);
        ){
            String query = "INSERT INTO entries(question, answer, option2, option3, option4, hint, category, level) VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";
            Statement stmt = conn.createStatement();
            try (PreparedStatement pstmt = conn.prepareStatement(query,  Statement.RETURN_GENERATED_KEYS)){
                pstmt.setString(1, entry.getQuestion());
                pstmt.setString(2, entry.getAnswer());
                pstmt.setString(3, entry.getOption2());
                pstmt.setString(4, entry.getOption3());
                pstmt.setString(5, entry.getOption4());
                pstmt.setString(6, entry.getHint());
                pstmt.setString(7, entry.getCategory());
                pstmt.setString(8, entry.getLevel());
                pstmt.execute();

                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1); // Get the first column of the result set
                        System.out.println("New ID: " + id);
                    }
                }
            }
        }catch  (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    public void deleteEntry(long id){
        String query = String.format("DELETE FROM entries WHERE id = %d", id);
        try (
                Connection conn = DriverManager.getConnection(url, username, password);
        ){
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        }catch(SQLException e){System.out.println(e.getMessage());}
    }

    public List<Entry> getEntries(){
        List<Entry> entries = new ArrayList<>();
        String  query = "SELECT * FROM entries";
        try (Connection conn = DriverManager.getConnection(url, username, password);
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
        try (
                Connection conn = DriverManager.getConnection(url, username, password);
        ){
            String query = "update entries set question = ?, answer = ?, option2 = ?, option3 = ?, " +
                    "option4 = ?, hint = ?, category = ?, level = ? where id = ?";
            Statement stmt = conn.createStatement();
            try (PreparedStatement pstmt = conn.prepareStatement(query,  Statement.RETURN_GENERATED_KEYS)){
                pstmt.setString(1, entry.getQuestion());
                pstmt.setString(2, entry.getAnswer());
                pstmt.setString(3, entry.getOption2());
                pstmt.setString(4, entry.getOption3());
                pstmt.setString(5, entry.getOption4());
                pstmt.setString(6, entry.getHint());
                pstmt.setString(7, entry.getCategory());
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
        String  query = "SELECT distinct category FROM entries";
        try (Connection conn = DriverManager.getConnection(url, username, password);
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
        String  query = "SELECT * FROM entries";

        if  (!categories.isEmpty()){
            query = "SELECT * FROM entries WHERE category IN ('" + String.join("','", categories) + "') limit 10";
        }
        System.out.println(query);

        try (Connection conn = DriverManager.getConnection(url, username, password);
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




}
