import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private List<User> users = new ArrayList<>();
    private int userCounter = 1;

    public User createUser(String username, String password) {
        User user = new User(userCounter++, username, password);
        users.add(user);
        return user;
    }

    public User findByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public User findByUserId(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }

    public List<User> getAllUsers() {
        return users;
    }

    public boolean authenticateByUsername(String username, String password) {
        User user = findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    public boolean authenticateByUserId(int userId, String password) {
        User user = findByUserId(userId);
        return user != null && user.getPassword().equals(password);
    }
}