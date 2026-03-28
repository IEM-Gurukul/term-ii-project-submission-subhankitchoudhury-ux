public class AuthService {
    private UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(String username, String password) {
        if (username == null || !username.matches("[a-zA-Z0-9_]+")) {
            throw new RuntimeException("Invalid username");
        }
        if (password == null || password.length() < 4) {
            throw new RuntimeException("Password must be at least 4 characters");
        }
        if (userRepository.findByUsername(username) != null) {
            throw new RuntimeException("Username already exists");
        }
        return userRepository.createUser(username, password);
    }

    public User loginWithUsername(String username, String password) {
        if (!userRepository.authenticateByUsername(username, password)) {
            throw new RuntimeException("Invalid username or password");
        }
        return userRepository.findByUsername(username);
    }

    public User loginWithUserId(int userId, String password) {
        if (!userRepository.authenticateByUserId(userId, password)) {
            throw new RuntimeException("Invalid user ID or password");
        }
        return userRepository.findByUserId(userId);
    }
}