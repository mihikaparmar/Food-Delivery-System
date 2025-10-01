package repository;

import model.User;
import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private Map<String, User> users = new HashMap<>();

    public void save(User user) {
        users.put(user.getUserId(), user);
    }

    public User findById(String userId) {
        return users.get(userId);
    }
}
