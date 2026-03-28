import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
    private static final AtomicInteger userIdCounter = new AtomicInteger(1);
    private static final AtomicInteger accountIdCounter = new AtomicInteger(1001);
    private static final AtomicInteger transactionIdCounter = new AtomicInteger(1);

    public static int generateUserId() {
        return userIdCounter.getAndIncrement();
    }

    public static int generateAccountId() {
        return accountIdCounter.getAndIncrement();
    }

    public static int generateTransactionId() {
        return transactionIdCounter.getAndIncrement();
    }
}