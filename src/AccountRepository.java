import java.util.*;

public class AccountRepository {
    private List<Account> accounts = new ArrayList<>();

    // ✅ ADD ACCOUNT
    public void addAccount(Account account) throws DuplicateAccountException {
        if (accountExists(account.getAccountNumber())) {
            throw new DuplicateAccountException("Account already exists!");
        }
        accounts.add(account);
    }

    // ✅ FIND ACCOUNT
    public Account findAccount(int accNo) throws AccountNotFoundException {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accNo) {
                return acc;
            }
        }
        throw new AccountNotFoundException("Account not found!");
    }

    // ✅ CHECK IF ACCOUNT EXISTS
    public boolean accountExists(int accNo) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accNo) {
                return true;
            }
        }
        return false;
    }

    // ✅ DELETE ACCOUNT
    public void deleteAccount(int accNo) throws AccountNotFoundException {
        Account acc = findAccount(accNo);
        accounts.remove(acc);
    }

    // ✅ UPDATE ACCOUNT NAME
    public void updateName(int accNo, String newName) throws AccountNotFoundException {
        Account acc = findAccount(accNo);
        acc.setName(newName);
    }

    // ✅ GET ALL ACCOUNTS (SAFE COPY)
    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts);
    }

    // ✅ GET TOTAL BALANCE OF BANK
    public double getTotalBankBalance() {
        double total = 0;
        for (Account acc : accounts) {
            total += acc.getBalance();
        }
        return total;
    }

    // ✅ COUNT TOTAL ACCOUNTS
    public int getTotalAccounts() {
        return accounts.size();
    }

    // ✅ SEARCH BY NAME
    public List<Account> searchByName(String name) {
        List<Account> result = new ArrayList<>();

        for (Account acc : accounts) {
            if (acc.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(acc);
            }
        }

        return result;
    }
}