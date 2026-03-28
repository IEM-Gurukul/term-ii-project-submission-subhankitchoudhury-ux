package repository;

import java.util.*;
import model.Transaction;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionRepository {
    private List<Transaction> transactions = new ArrayList<>();
    private int transactionCounter = 1;

    public Transaction addTransaction(int accountNumber, String type, double amount) {
        Transaction txn = new Transaction(
                transactionCounter++,
                accountNumber,
                type,
                amount,
                java.time.LocalDateTime.now());
        transactions.add(txn);
        return txn;
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public List<Transaction> getTransactionsByAccount(int accountNumber) {
        return transactions.stream()
                .filter(t -> t.getAccountNumber() == accountNumber)
                .collect(Collectors.toList());
    }
}