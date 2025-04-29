package util;

import java.sql.*;

public class DataBase {
    public static void startDatabase(){
        Connection c = null;

        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Highscore.db");
        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public static void insertDatabase(String name, int score){
        Connection c = null;
        Statement stmt = null;
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Highscore.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "INSERT INTO Highscores (Player,Score) " + "VALUES (\"" + name + "\", " + score + " );";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

    public static int getNumberRec() {
        Connection c = null;
        Statement stmt = null;
        int res = -1;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Highscore.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Highscores;");
            res = rs.getInt(1);
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return res;
    }

    public static void printDatabase() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Highscore.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Highscores;");
            while(rs.next()){
                String name = rs.getString(1);
                int score = rs.getInt(2);
                System.out.println("Player = " + name);
                System.out.println("Score = " + score);
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public static String getName(int pos) {
        Connection c = null;
        Statement stmt = null;
        String res = "NULL";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Highscore.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "SELECT * FROM Highscores WHERE ID = " + pos + ";";
            ResultSet rs = stmt.executeQuery(sql);
            res = rs.getString(1);
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return res;
    }

    public static int getScore(int pos) {
        Connection c = null;
        Statement stmt = null;
        int res = -1;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Highscore.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "SELECT * FROM Highscores WHERE ID = " + pos + ";";
            ResultSet rs = stmt.executeQuery(sql);
            res = rs.getInt(2);
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return res;
    }
}
