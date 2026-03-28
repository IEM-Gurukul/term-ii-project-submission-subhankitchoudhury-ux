package util;

public class InputValidator {

    public static void validateUsername(String username) throws IllegalArgumentException {
        if (username == null || !username.matches("[a-zA-Z0-9_]{3,}")) {
            throw new IllegalArgumentException(
                    "Username must be at least 3 characters and contain only letters, numbers, or underscores");
        }
    }

    public static void validatePassword(String password) throws IllegalArgumentException {
        if (password == null || password.length() < 4) {
            throw new IllegalArgumentException("Password must be at least 4 characters long");
        }
    }

    public static void validateAmount(double amount) throws IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
    }

    public static void validateInitialBalance(double balance) throws IllegalArgumentException {
        if (balance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
    }

    public static void validateAccountNumber(int accountNumber) throws IllegalArgumentException {
        if (accountNumber <= 0) {
            throw new IllegalArgumentException("Invalid account number");
        }
    }

    public static void validateName(String name) throws IllegalArgumentException {
        if (name == null || !name.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Name must contain only alphabets and spaces");
        }
    }
}