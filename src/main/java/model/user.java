package model;

import java.time.LocalDateTime;
@lombok.Getter
@lombok.Setter
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor 
@lombok.Data 
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String passwordHash;
    private boolean isAdmin;
    private boolean isGuest;
    private boolean isUser;
    private LocalDateTime registeredAt;
    private LocalDateTime lastLogin;

    
}
