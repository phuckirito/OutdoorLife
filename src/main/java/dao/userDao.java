package dao;
import model.*;
public interface UserDAO {
    boolean insertGuest(String firstName, String lastName, String phoneNumber, String email, String passwordHash);
    User findByEmail(String email);
}
