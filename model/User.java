package model;
import java.util.UUID;

public class User {
    private String userId;
    private String name;
    private String email;
    private String phoneNumber;

    public User(String name, String email, String phoneNumber) {
        this.userId = UUID.randomUUID().toString().replace("-", "").substring(0, 12);
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
}
