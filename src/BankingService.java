public class BankingService {
    private AccountRepository repository = new AccountRepository();

    public void createAccount(int accNo, String name, double balance) {
        Account acc = new Account(accNo, name, balance);
        repository.addAccount(acc);
        System.out.println("Account created successfully!");
    }

    public void deposit(int accNo, double amount) {
        Account acc = repository.findAccount(accNo);
        if (acc != null) {
            acc.deposit(amount);
            System.out.println("Deposit successful!");
        } else {
            System.out.println("Account not found!");
        }
    }

    public void withdraw(int accNo, double amount) {
        Account acc = repository.findAccount(accNo);
        if (acc != null) {
            if (acc.withdraw(amount)) {
                System.out.println("Withdrawal successful!");
            } else {
                System.out.println("Insufficient balance!");
            }
        } else {
            System.out.println("Account not found!");
        }
    }

    public void displayAccounts() {
        for (Account acc : repository.getAllAccounts()) {
            System.out.println("--------------------------------");
            System.out.println("Account Number: " + acc.getAccountNumber());
            System.out.println("Name: " + acc.getName());
            System.out.println("Balance: " + acc.getBalance());
        }
    }
}