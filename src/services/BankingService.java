import java.util.List;

public class BankingService {
    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    public BankingService() {
        userRepository = new UserRepository();
        accountRepository = new AccountRepository();
        transactionRepository = new TransactionRepository();
    }

    public User registerUser(String username, String password) {
        if (username == null || !username.matches("[a-zA-Z0-9_]+")) {
            throw new RuntimeException("Invalid username");
        }
        if (password == null || password.length() < 4) {
            throw new RuntimeException("Password too short");
        }
        if (userRepository.findByUsername(username) != null) {
            throw new RuntimeException("Username already exists");
        }
        return userRepository.createUser(username, password);
    }

    public User loginByUsername(String username, String password) {
        if (!userRepository.authenticateByUsername(username, password)) {
            throw new RuntimeException("Invalid credentials");
        }
        return userRepository.findByUsername(username);
    }

    public User loginByUserId(int userId, String password) {
        if (!userRepository.authenticateByUserId(userId, password)) {
            throw new RuntimeException("Invalid credentials");
        }
        return userRepository.findByUserId(userId);
    }

    public Account createAccount(int userId, double initialBalance) {
        if (initialBalance < 0) {
            throw new RuntimeException("Invalid balance");
        }
        Account acc = accountRepository.createAccount(userId, initialBalance);
        return acc;
    }

    public void deposit(int accountNumber, double amount) {
        if (amount <= 0) {
            throw new RuntimeException("Invalid amount");
        }
        Account acc = accountRepository.findByAccountNumber(accountNumber);
        if (acc == null) {
            throw new RuntimeException("Account not found");
        }
        acc.deposit(amount);
        transactionRepository.addTransaction(accountNumber, "DEPOSIT", amount);
    }

    public void withdraw(int accountNumber, double amount) {
        if (amount <= 0) {
            throw new RuntimeException("Invalid amount");
        }
        Account acc = accountRepository.findByAccountNumber(accountNumber);
        if (acc == null) {
            throw new RuntimeException("Account not found");
        }
        if (acc.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        acc.withdraw(amount);
        transactionRepository.addTransaction(accountNumber, "WITHDRAW", amount);
    }

    public List<Account> getUserAccounts(int userId) {
        return accountRepository.getAccountsByUserId(userId);
    }

    public List<Transaction> getAccountTransactions(int accountNumber) {
        return transactionRepository.getTransactionsByAccount(accountNumber);
    }

    public void transfer(int fromAccount, int toAccount, double amount) {
        if (amount <= 0) {
            throw new RuntimeException("Invalid amount");
        }

        Account sender = accountRepository.findByAccountNumber(fromAccount);
        Account receiver = accountRepository.findByAccountNumber(toAccount);

        if (sender == null || receiver == null) {
            throw new RuntimeException("Account not found");
        }

        if (sender.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        sender.withdraw(amount);
        receiver.deposit(amount);

        transactionRepository.addTransaction(fromAccount, "TRANSFER_OUT", amount);
        transactionRepository.addTransaction(toAccount, "TRANSFER_IN", amount);
    }

    public void changePassword(int userId, String oldPassword, String newPassword) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (!user.getPassword().equals(oldPassword)) {
            throw new RuntimeException("Incorrect password");
        }
        if (newPassword.length() < 4) {
            throw new RuntimeException("Password too short");
        }
        user.setPassword(newPassword);
    }
}