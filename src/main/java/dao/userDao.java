package dao;
import model.*;
public interface userDao {
    boolean insertGuest(String firstName, String lastName, String phoneNumber, String email, String passwordHash);
}
