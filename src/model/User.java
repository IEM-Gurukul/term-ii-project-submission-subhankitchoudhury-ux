import java.util.ArrayList;
import java.util.List;

public class User {
    private int userId;
    private String username;
    private String password;
    private List<Account> accounts;

    public User(int userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.accounts = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    public Account getAccountByNumber(int accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accountNumber) {
                return acc;
            }
        }
        return null;
    }

    public boolean hasAccount(int accountNumber) {
        return getAccountByNumber(accountNumber) != null;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}