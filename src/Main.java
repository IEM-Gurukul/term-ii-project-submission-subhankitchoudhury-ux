import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        BankingService service = new BankingService();
        Scanner sc = new Scanner(System.in);

        int choice = 0;

        do {
            try {
                System.out.println("\n===== MINI BANKING SYSTEM =====");
                System.out.println("1. Create Account");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. Display Accounts");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                choice = sc.nextInt();

                switch (choice) {

                    // ✅ CREATE ACCOUNT (FULLY VALIDATED)
                    case 1:
                        System.out.print("Enter Account Number: ");
                        int accNo = sc.nextInt();
                        sc.nextLine();

                        if (accNo <= 0) {
                            throw new InvalidAmountException("Account number must be positive!");
                        }

                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        if (!name.matches("[a-zA-Z ]+")) {
                            throw new InvalidAmountException("Name must contain only alphabets!");
                        }

                        System.out.print("Enter Initial Balance: ");
                        double balance = sc.nextDouble();

                        service.createAccount(accNo, name, balance);
                        break;

                    // ✅ DEPOSIT
                    case 2:
                        System.out.print("Enter Account Number: ");
                        int depAcc = sc.nextInt();

                        if (depAcc <= 0) {
                            throw new InvalidAmountException("Invalid account number!");
                        }

                        System.out.print("Enter Amount: ");
                        double depAmt = sc.nextDouble();

                        service.deposit(depAcc, depAmt);
                        break;

                    // ✅ WITHDRAW
                    case 3:
                        System.out.print("Enter Account Number: ");
                        int withAcc = sc.nextInt();

                        if (withAcc <= 0) {
                            throw new InvalidAmountException("Invalid account number!");
                        }

                        System.out.print("Enter Amount: ");
                        double withAmt = sc.nextDouble();

                        service.withdraw(withAcc, withAmt);
                        break;

                    // ✅ DISPLAY
                    case 4:
                        service.displayAccounts();
                        break;

                    // ✅ EXIT
                    case 5:
                        System.out.println("Exiting... Thank you!");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }

            }
            // ❌ Wrong data type (e.g., string instead of number)
            catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter correct data type.");
                sc.nextLine(); // clear buffer
            }

            // ❌ All custom exceptions handled here
            catch (DuplicateAccountException | AccountNotFoundException | InvalidAmountException
                    | InsufficientBalanceException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 5);

        sc.close();
    }
}