package model;

public class Account {
    private int accountNumber;
    private double balance;
    private int userId;

    public Account(int accountNumber, double balance, int userId) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.userId = userId;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public int getUserId() {
        return userId;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}