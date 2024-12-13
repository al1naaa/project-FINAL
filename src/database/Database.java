package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Database {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Aa1234";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    } 
    
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to the database successfully!");
        } catch (SQLException e) {
            System.err.println("Connection failed!");
            e.printStackTrace();
        }
    } 
}

