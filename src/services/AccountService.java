package services;

import repository.*;
import model.*;
import java.util.List;

public class AccountService {
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Account createAccount(int userId, double initialBalance) {
        if (initialBalance < 0) {
            throw new RuntimeException("Initial balance cannot be negative");
        }
        return accountRepository.createAccount(userId, initialBalance);
    }

    public void deposit(int accountNumber, double amount) {
        if (amount <= 0) {
            throw new RuntimeException("Deposit amount must be positive");
        }

        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new RuntimeException("Account not found");
        }

        account.deposit(amount);
        transactionRepository.addTransaction(accountNumber, "DEPOSIT", amount);
    }

    public void withdraw(int accountNumber, double amount) {
        if (amount <= 0) {
            throw new RuntimeException("Withdrawal amount must be positive");
        }

        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new RuntimeException("Account not found");
        }

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        account.withdraw(amount);
        transactionRepository.addTransaction(accountNumber, "WITHDRAW", amount);
    }

    public void transfer(int fromAccount, int toAccount, double amount) {
        if (amount <= 0) {
            throw new RuntimeException("Transfer amount must be positive");
        }

        Account sender = accountRepository.findByAccountNumber(fromAccount);
        Account receiver = accountRepository.findByAccountNumber(toAccount);

        if (sender == null || receiver == null) {
            throw new RuntimeException("One or both accounts not found");
        }

        if (sender.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        sender.withdraw(amount);
        receiver.deposit(amount);

        transactionRepository.addTransaction(fromAccount, "TRANSFER_OUT", amount);
        transactionRepository.addTransaction(toAccount, "TRANSFER_IN", amount);
    }

    public List<Account> getAccountsByUser(int userId) {
        return accountRepository.getAccountsByUserId(userId);
    }

    public Account getAccount(int accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new RuntimeException("Account not found");
        }
        return account;
    }

    public void deleteAccount(int accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new RuntimeException("Account not found");
        }
        accountRepository.deleteAccount(accountNumber);
    }
}