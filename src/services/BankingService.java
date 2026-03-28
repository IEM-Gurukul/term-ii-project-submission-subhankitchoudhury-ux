package services;

import repository.*;
import model.*;
import java.util.*;

public class BankingService {

    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    public BankingService() {
        userRepository = new UserRepository();
        accountRepository = new AccountRepository();
        transactionRepository = new TransactionRepository();
    }

    // ================= USER =================

    public User registerUser(String username, String password) {
        if (username == null || !username.matches("[a-zA-Z0-9_]{3,}")) {
            throw new RuntimeException("Invalid username! Use at least 3 characters (letters/numbers).");
        }

        if (password == null || password.length() < 4) {
            throw new RuntimeException("Password must be at least 4 characters!");
        }

        if (userRepository.findByUsername(username) != null) {
            throw new RuntimeException("Username already exists!");
        }

        return userRepository.createUser(username, password);
    }

    public User loginByUsername(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new RuntimeException("User not found!");
        }

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Incorrect password!");
        }

        return user;
    }

    public User loginByUserId(int userId, String password) {
        User user = userRepository.findByUserId(userId);

        if (user == null) {
            throw new RuntimeException("User ID not found!");
        }

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Incorrect password!");
        }

        return user;
    }

    public void changePassword(int userId, String oldPassword, String newPassword) {
        User user = userRepository.findByUserId(userId);

        if (user == null) {
            throw new RuntimeException("User not found!");
        }

        if (!user.getPassword().equals(oldPassword)) {
            throw new RuntimeException("Old password is incorrect!");
        }

        if (newPassword == null || newPassword.length() < 4) {
            throw new RuntimeException("New password must be at least 4 characters!");
        }

        user.setPassword(newPassword);
    }

    // ================= ACCOUNT =================

    public Account createAccount(int userId, double initialBalance) {
        if (initialBalance < 0) {
            throw new RuntimeException("Initial balance cannot be negative!");
        }

        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new RuntimeException("User not found!");
        }

        Account account = accountRepository.createAccount(userId, initialBalance);
        user.addAccount(account);

        return account;
    }

    public List<Account> getUserAccounts(int userId) {
        List<Account> accounts = accountRepository.getAccountsByUserId(userId);
        return accounts != null ? accounts : new ArrayList<>();
    }

    // ================= TRANSACTIONS =================

    public void deposit(int accountNumber, double amount) {
        if (amount <= 0) {
            throw new RuntimeException("Deposit amount must be positive!");
        }

        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new RuntimeException("Account not found!");
        }

        account.deposit(amount);
        transactionRepository.addTransaction(accountNumber, "DEPOSIT", amount);
    }

    public void withdraw(int accountNumber, double amount) {
        if (amount <= 0) {
            throw new RuntimeException("Withdrawal amount must be positive!");
        }

        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new RuntimeException("Account not found!");
        }

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance!");
        }

        account.withdraw(amount);
        transactionRepository.addTransaction(accountNumber, "WITHDRAW", amount);
    }

    public void transfer(int fromAccount, int toAccount, double amount) {
        if (amount <= 0) {
            throw new RuntimeException("Transfer amount must be positive!");
        }

        Account sender = accountRepository.findByAccountNumber(fromAccount);
        Account receiver = accountRepository.findByAccountNumber(toAccount);

        if (sender == null || receiver == null) {
            throw new RuntimeException("One or both accounts not found!");
        }

        if (sender.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance in sender account!");
        }

        sender.withdraw(amount);
        receiver.deposit(amount);

        transactionRepository.addTransaction(fromAccount, "TRANSFER_OUT", amount);
        transactionRepository.addTransaction(toAccount, "TRANSFER_IN", amount);
    }

    public List<Transaction> getAccountTransactions(int accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);

        if (account == null) {
            throw new RuntimeException("Account not found!");
        }

        List<Transaction> transactions = transactionRepository.getTransactionsByAccount(accountNumber);
        return transactions != null ? transactions : new ArrayList<>();
    }
}