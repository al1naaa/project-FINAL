package database;

import java.sql.Connection;
import java.sql.PreparedStatement; 
import java.sql.ResultSet;

public class UserService {
	public void signUp(String username, String email, String password, String role) {
	    String hashedPassword = PasswordUtils.hashPassword(password);

	    try (Connection conn = Database.getConnection()) {
	        String sql = "INSERT INTO users (username, email, password_hash, role) VALUES (?, ?, ?, ?)";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, username);
	        pstmt.setString(2, email);
	        pstmt.setString(3, hashedPassword);
	        pstmt.setString(4, role);
	        pstmt.executeUpdate();
	        System.out.println("User registered successfully!");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public boolean logIn(String username, String password) {
	    try (Connection conn = Database.getConnection()) {
	        String sql = "SELECT password_hash FROM users WHERE username = ?";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, username);
	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            String storedHash = rs.getString("password_hash");
	            return PasswordUtils.checkPassword(password, storedHash);
	        } else {
	            System.out.println("Username not found!");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}

}
