import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    private List<Account> accounts = new ArrayList<>();
    private int accountCounter = 1001;

    public Account createAccount(int userId, double initialBalance) {
        Account account = new Account(accountCounter++, initialBalance, userId);
        accounts.add(account);
        return account;
    }

    public Account findByAccountNumber(int accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accountNumber) {
                return acc;
            }
        }
        return null;
    }

    public List<Account> getAccountsByUserId(int userId) {
        List<Account> userAccounts = new ArrayList<>();
        for (Account acc : accounts) {
            if (acc.getUserId() == userId) {
                userAccounts.add(acc);
            }
        }
        return userAccounts;
    }

    public List<Account> getAllAccounts() {
        return accounts;
    }

    public void deleteAccount(int accountNumber) {
        Account acc = findByAccountNumber(accountNumber);
        if (acc != null) {
            accounts.remove(acc);
        }
    }
}