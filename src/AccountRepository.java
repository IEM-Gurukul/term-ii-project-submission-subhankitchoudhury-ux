import java.util.*;

public class AccountRepository {
    private List<Account> accountList = new ArrayList<>();

    public void addAccount(Account account) {
        accountList.add(account);
    }

    public Account findAccount(int accNo) {
        for (Account acc : accountList) {
            if (acc.getAccountNumber() == accNo) {
                return acc;
            }
        }
        return null;
    }

    public List<Account> getAllAccounts() {
        return accountList;
    }
}