package dao;
import model.*;
public interface UserDAO {
    boolean insertGuest(String firstName, String lastName, String email, String phoneNumber, String passwordHash);
    User findByEmail(String email);
}
