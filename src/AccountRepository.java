import java.util.*;

public class AccountRepository {
    private List<Account> accounts = new ArrayList<>();

    public void addAccount(Account account) throws DuplicateAccountException {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == account.getAccountNumber()) {
                throw new DuplicateAccountException("Account already exists!");
            }
        }
        accounts.add(account);
    }

    public Account findAccount(int accNo) throws AccountNotFoundException {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accNo) {
                return acc;
            }
        }
        throw new AccountNotFoundException("Account not found!");
    }

    public List<Account> getAllAccounts() {
        return accounts;
    }
}