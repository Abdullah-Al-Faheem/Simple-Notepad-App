package notepad;

import java.sql.*;

public class Database
{
    private static final String url = "jdbc:mysql://localhost:3306/notepad_app";
    private static final String username = "root";
    private static final String password = "your_password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public static int getUserId(String username, String password) {
        String query = "SELECT id FROM users WHERE username = ? AND password = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("id"); // Return user ID if exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;  // if no user found
    }


    // Register
    public static boolean registerUser(String username, String email, String password)
    {
        String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query))
        {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password); // In real-world, hash the password

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Return true if user is successfully added
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

