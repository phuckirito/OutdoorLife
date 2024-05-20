package dao;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;

public class UserDaoImpl extends DBContext implements UserDAO {

    // Method to hash password using SHA-256
    private String hashPassword(String password) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(password.getBytes());

        // Convert byte array to hexadecimal string
        StringBuilder hexString = new StringBuilder();
        for (byte b : encodedHash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    @Override
    public boolean insertGuest(String firstName, String lastName, String phoneNumber, String email, String password) {
        try {
            // Get connection
            Connection conn = getConnection();
            System.out.println(conn);
            // Hash the password using SHA-256
            String hashedPassword = hashPassword(password);

            // Prepare SQL statement
            String sql = "INSERT INTO guest (first_name, last_name, phone_number, email, passwordHash) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, firstName);
                pstmt.setString(2, lastName);
                pstmt.setString(3, phoneNumber);
                pstmt.setString(4, email);
                pstmt.setString(5, hashedPassword);

                System.out.println(firstName + " " + lastName + " " + phoneNumber + " " + email + " " + hashedPassword);

                // Execute the insert statement
                int rowsAffected = pstmt.executeUpdate();

                // Check if the insertion was successful
                return rowsAffected > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean emailExists(String email) throws Exception {
        boolean exists = false;
        String query = "SELECT 1 FROM guest WHERE email = ?";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                exists = rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }
@Override
    public User findByEmail(String email) {
        String query = "SELECT * FROM guest WHERE email=?";
        try (
            Connection conn = getConnection(); 
            PreparedStatement ps = conn.prepareStatement(query)) {
            // Set the email parameter
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id"); // Assuming 'id' is the column name
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String password = rs.getString("passwordHash");
                // Create and return the User object
                    User user = new User();
                    user.setId(id);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setPasswordHash(password);
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log or handle the exception as needed
        }
        return null;
    }

}
