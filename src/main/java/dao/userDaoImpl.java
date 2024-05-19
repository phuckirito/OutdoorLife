package dao;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class userDaoImpl extends DBContext implements userDao {

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
                
                System.out.println(firstName+" "+lastName+" "+phoneNumber+" "+email+" "+hashedPassword);
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
}
