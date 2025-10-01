package service;
import repository.*;
import model.*;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public String registerUser(String name, String email, String phoneNumber) {
        User user = new User(name, email, phoneNumber);
        userRepository.save(user);
        System.out.println("User Registered!!! UserId: " + user.getUserId());
        return user.getUserId();
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId);
    }
}
