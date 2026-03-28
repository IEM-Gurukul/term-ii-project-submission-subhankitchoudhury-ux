import java.time.LocalDateTime;

public class Transaction {
    private int transactionId;
    private int accountNumber;
    private String type;
    private double amount;
    private LocalDateTime dateTime;

    public Transaction(int transactionId, int accountNumber, String type, double amount, LocalDateTime dateTime) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.dateTime = dateTime;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}