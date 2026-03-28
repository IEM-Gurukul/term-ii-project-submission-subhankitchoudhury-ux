package services;

import repository.*;
import model.*;
import java.util.List;

public class TransactionService {
    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public List<Transaction> getTransactionsByAccount(int accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new RuntimeException("Account not found");
        }
        return transactionRepository.getTransactionsByAccount(accountNumber);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.getAllTransactions();
    }
}