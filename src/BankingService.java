import java.util.List;

public class BankingService {
    private AccountRepository repository;

    public BankingService() {
        repository = new AccountRepository();
    }

    // ✅ Name validation
    private boolean isValidName(String name) {
        return name != null && name.matches("[a-zA-Z ]+");
    }

    // ✅ CREATE ACCOUNT
    public void createAccount(int accNo, String name, double balance)
            throws DuplicateAccountException, InvalidAmountException {

        if (accNo <= 0) {
            throw new InvalidAmountException("Account number must be positive!");
        }

        if (name == null || name.trim().isEmpty()) {
            throw new InvalidAmountException("Name cannot be empty!");
        }

        if (!isValidName(name)) {
            throw new InvalidAmountException("Name must contain only alphabets!");
        }

        if (balance < 0) {
            throw new InvalidAmountException("Initial balance cannot be negative!");
        }

        Account acc = new Account(accNo, name, balance);
        repository.addAccount(acc);

        System.out.println("Account created successfully!");
    }

    // ✅ DEPOSIT
    public void deposit(int accNo, double amount)
            throws AccountNotFoundException, InvalidAmountException {

        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive!");
        }

        Account acc = repository.findAccount(accNo);
        acc.deposit(amount);

        System.out.println("Deposit successful!");
    }

    // ✅ WITHDRAW
    public void withdraw(int accNo, double amount)
            throws AccountNotFoundException, InvalidAmountException, InsufficientBalanceException {

        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive!");
        }

        Account acc = repository.findAccount(accNo);

        if (acc.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance!");
        }

        acc.withdraw(amount);
        System.out.println("Withdrawal successful!");
    }

    // ✅ TRANSFER MONEY
    public void transfer(int fromAcc, int toAcc, double amount)
            throws AccountNotFoundException, InvalidAmountException, InsufficientBalanceException {

        if (amount <= 0) {
            throw new InvalidAmountException("Transfer amount must be positive!");
        }

        if (fromAcc == toAcc) {
            throw new InvalidAmountException("Cannot transfer to same account!");
        }

        Account sender = repository.findAccount(fromAcc);
        Account receiver = repository.findAccount(toAcc);

        if (sender.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance!");
        }

        sender.withdraw(amount);
        receiver.deposit(amount);

        System.out.println("Transfer successful!");
    }

    // ✅ SEARCH BY ACCOUNT NUMBER
    public void searchAccount(int accNo) throws AccountNotFoundException {
        Account acc = repository.findAccount(accNo);

        System.out.println("----- Account Found -----");
        System.out.println("Account Number: " + acc.getAccountNumber());
        System.out.println("Name: " + acc.getName());
        System.out.println("Balance: " + acc.getBalance());
    }

    // ✅ SEARCH BY NAME
    public void searchByName(String name) {
        List<Account> result = repository.searchByName(name);

        if (result.isEmpty()) {
            System.out.println("No accounts found with this name!");
            return;
        }

        for (Account acc : result) {
            System.out.println("----------------------------");
            System.out.println("Account Number: " + acc.getAccountNumber());
            System.out.println("Name: " + acc.getName());
            System.out.println("Balance: " + acc.getBalance());
        }
    }

    // ✅ DELETE ACCOUNT
    public void deleteAccount(int accNo) throws AccountNotFoundException {
        repository.deleteAccount(accNo);
        System.out.println("Account deleted successfully!");
    }

    // ✅ UPDATE NAME
    public void updateAccountName(int accNo, String newName)
            throws AccountNotFoundException, InvalidAmountException {

        if (!isValidName(newName)) {
            throw new InvalidAmountException("Invalid name!");
        }

        repository.updateName(accNo, newName);
        System.out.println("Name updated successfully!");
    }

    // ✅ DISPLAY ALL ACCOUNTS
    public void displayAccounts() {
        List<Account> accounts = repository.getAllAccounts();

        if (accounts.isEmpty()) {
            System.out.println("No accounts found!");
            return;
        }

        for (Account acc : accounts) {
            System.out.println("----------------------------");
            System.out.println("Account Number: " + acc.getAccountNumber());
            System.out.println("Name: " + acc.getName());
            System.out.println("Balance: " + acc.getBalance());
        }
    }

    // ✅ BANK STATISTICS
    public void showBankStats() {
        System.out.println("----- BANK STATISTICS -----");
        System.out.println("Total Accounts: " + repository.getTotalAccounts());
        System.out.println("Total Bank Balance: " + repository.getTotalBankBalance());
    }
}