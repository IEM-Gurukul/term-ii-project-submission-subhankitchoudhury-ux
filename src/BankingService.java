public class BankingService {
    private AccountRepository repository;

    public BankingService() {
        repository = new AccountRepository();
    }

    // ✅ Name validation method
    private boolean isValidName(String name) {
        return name != null && name.matches("[a-zA-Z ]+");
    }

    // ✅ CREATE ACCOUNT (FIXED)
    public void createAccount(int accNo, String name, double balance)
            throws DuplicateAccountException, InvalidAmountException {

        // ❌ Empty name
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidAmountException("Name cannot be empty!");
        }

        // ❌ Name with numbers/symbols
        if (!isValidName(name)) {
            throw new InvalidAmountException("Name must contain only alphabets!");
        }

        // ❌ Negative balance
        if (balance < 0) {
            throw new InvalidAmountException("Initial balance cannot be negative!");
        }

        Account acc = new Account(accNo, name, balance);
        repository.addAccount(acc);

        System.out.println("Account created successfully!");
    }

    // ✅ DEPOSIT (IMPROVED)
    public void deposit(int accNo, double amount)
            throws AccountNotFoundException, InvalidAmountException {

        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive!");
        }

        Account acc = repository.findAccount(accNo);
        acc.deposit(amount);

        System.out.println("Deposit successful!");
    }

    // ✅ WITHDRAW (IMPROVED)
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

    // ✅ DISPLAY
    public void displayAccounts() {
        if (repository.getAllAccounts().isEmpty()) {
            System.out.println("No accounts found!");
            return;
        }

        for (Account acc : repository.getAllAccounts()) {
            System.out.println("----------------------------");
            System.out.println("Account Number: " + acc.getAccountNumber());
            System.out.println("Name: " + acc.getName());
            System.out.println("Balance: " + acc.getBalance());
        }
    }
}