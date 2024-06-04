package dao;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
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
    public boolean insertGuest(String firstName, String lastName,String email, String phoneNumber, String password) {
        try {
            // Get connection
            Connection conn = getConnection();
            System.out.println(conn);
            // Hash the password using SHA-256
            String hashedPassword = hashPassword(password);

            // Prepare SQL statement
            String sql = "INSERT INTO ACCOUNT (first_name, last_name,  Gmail, phone_number, passwordHash) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, firstName);
                pstmt.setString(2, lastName);
                pstmt.setString(3, email);
                pstmt.setString(4, phoneNumber);
                pstmt.setString(5, hashedPassword);

                System.out.println(firstName + " " + lastName + " " + email + " " + phoneNumber + " " + hashedPassword);

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

    public String getRandom() {
        Random random = new Random();
        int number = random.nextInt(999999);
        return String.format("%06d", number);
    }

    public boolean sendEmail(User user) {
    boolean test = false;
    String toEmail = user.getEmail();
    String fromEmail = "vietnqde170722@fpt.edu.vn";
    String passwordEmail = "enyujdkngfzylzkj"; 

    try {
        Properties pr = new Properties();
        pr.put("mail.smtp.host", "smtp.gmail.com");
        pr.put("mail.smtp.port", "587");
        pr.put("mail.smtp.auth", "true");
        pr.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(pr, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, passwordEmail);
            }
        });

        Message mess = new MimeMessage(session);
        mess.setFrom(new InternetAddress(fromEmail));
        mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        mess.setSubject("Verify Email Register Account");
        mess.setText("Register Successfully!! Please verify your account using this code: " + user.getCode());

        Transport.send(mess);
        test = true;

    } catch (Exception e) {
        e.printStackTrace();
    }
    return test;
}


    public boolean emailExists(String email) throws Exception {
        boolean exists = false;
        String query = "SELECT 1 FROM ACCOUNT WHERE Gmail = ?";
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
        String query = "SELECT * FROM ACCOUNT WHERE Gmail=?";
        try (
            Connection conn = getConnection(); 
            PreparedStatement ps = conn.prepareStatement(query)) {
            // Set the email parameter
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("Account_id"); // Assuming 'id' is the column name
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String password = rs.getString("passwordHash");
                    boolean isAdmin=rs.getBoolean("isAdmin");
                // Create and return the User object
                    User user = new User();
                    user.setId(id);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setPasswordHash(password);
                    user.setAdmin(isAdmin);
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log or handle the exception as needed
        }
        return null;
    }
    public boolean updatePassword(String email, String newPassword) throws Exception {
        try (
                Connection conn = getConnection();) {
            String sql = "UPDATE ACCOUNT SET passwordHash = ? WHERE Gmail = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, newPassword); // Bạn có thể mã hóa mật khẩu trước khi lưu vào DB
                stmt.setString(2, email);
                return stmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
