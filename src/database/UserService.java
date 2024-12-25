package database;

import communication.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import users.*;

public class UserService {


	public boolean isUserExists(String IIN) {
		try (Connection conn = Database.getConnection()) {
			String sql = "SELECT COUNT(*) FROM users WHERE IIN = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, IIN);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next() && rs.getInt(1) > 0) {
				return true;
			}
		} catch (Exception e) {
			System.err.println("Error during user existence check: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public void signUp(String IIN, String name, String surname, String email, String password, String role) {
		try (Connection conn = Database.getConnection()) {
			String sql = "INSERT INTO users (IIN, name, surname, email, password, role) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, IIN);
			pstmt.setString(2, name);
			pstmt.setString(3, surname);
			pstmt.setString(4, email);
			pstmt.setString(5, password);
			pstmt.setString(6, role);
			pstmt.executeUpdate();
			System.out.println("User registered successfully!");
		} catch (Exception e) {
			System.err.println("Error during user registration: " + e.getMessage());
			e.printStackTrace();
		}
	}


	public boolean logIn(String email, String password) {
		try (Connection conn = Database.getConnection()) {
			String sql = "SELECT password FROM users WHERE email = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String storedPassword = rs.getString("password");
				if (storedPassword.equals(password)) {
					System.out.println("Login successful!");
					return true;
				} else {
					System.out.println("Invalid password!");
				}
			} else {
				System.out.println("User not found!");
			}
		} catch (Exception e) {
			System.err.println("Error during login: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}


	public String getUserRoleByUsername(String email) {
		try (Connection conn = Database.getConnection()) {
			String sql = "SELECT role FROM users WHERE email = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getString("role");
			} else {
				System.out.println("User not found!");
			}
		} catch (Exception e) {
			System.err.println("Error fetching user role: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}


	public void removeUserByEmail(String email) {
		try (Connection conn = Database.getConnection()) {
			String sql = "DELETE FROM users WHERE email = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("User removed successfully!");
			} else {
				System.out.println("No user found with the provided email!");
			}
		} catch (Exception e) {
			System.err.println("Error during user removal: " + e.getMessage());
			e.printStackTrace();
		}
	}


	public void updateUser(String email, String newName, String newEmail) {
		try (Connection conn = Database.getConnection()) {
			String sql = "UPDATE users SET name = ?, email = ? WHERE email = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newName);
			pstmt.setString(2, newEmail);
			pstmt.setString(3, email);
			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("User updated successfully!");
			} else {
				System.out.println("No user found with the provided email!");
			}
		} catch (Exception e) {
			System.err.println("Error during user update: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void sendMessage(String sender, String receiver, String body) {
		String query = "INSERT INTO messages (sender_email, receiver_email, body) VALUES (?, ?, ?)";
		try (Connection conn = Database.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, sender);
			pstmt.setString(2, receiver);
			pstmt.setString(3, body);
			pstmt.executeUpdate();
			System.out.println("Message sent successfully!");
		} catch (SQLException e) {
			System.err.println("Error sending message: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	public List<Message> getMessages(String email) {
		String query = "SELECT * FROM messages WHERE receiver_email = ? ORDER BY created_at DESC";
		List<Message> messages = new ArrayList<>();
		try (Connection conn = Database.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				messages.add(new Message(
						rs.getString("body"),
						rs.getString("sender_email"),
						rs.getString("receiver_email")
				));
			}
		} catch (Exception e) {
			System.err.println("Error fetching messages: " + e.getMessage());
			e.printStackTrace();
		}
		return messages;
	}
	public List<Teacher> getAllTeachers() {
		List<Teacher> teachers = new ArrayList<>();

		String query = "SELECT * FROM users WHERE role = 'teacher'";

		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Aa1234");
			 PreparedStatement statement = connection.prepareStatement(query);
			 ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				String iin = resultSet.getString("iin");
				String name = resultSet.getString("name");
				String surname = resultSet.getString("surname");
				String email = resultSet.getString("email");
				String password = resultSet.getString("password");

				teachers.add(new Teacher(iin, name, surname, email, password, this));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return teachers;
	}


	public List<Student> getAllStudents() {
		List<Student> students = new ArrayList<>();

		String query = "SELECT * FROM users WHERE role = 'student'";

		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Aa1234");
			 PreparedStatement statement = connection.prepareStatement(query);
			 ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				String iin = resultSet.getString("iin");
				String name = resultSet.getString("name");
				String surname = resultSet.getString("surname");
				String email = resultSet.getString("email");
				String password = resultSet.getString("password");

				students.add(new Student(iin, name, surname, email, password));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return students;
	}

}
