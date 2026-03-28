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
                System.out.println("5. Transfer Money");
                System.out.println("6. Search Account by Number");
                System.out.println("7. Search Account by Name");
                System.out.println("8. Update Account Name");
                System.out.println("9. Delete Account");
                System.out.println("10. Bank Statistics");
                System.out.println("11. Exit");
                System.out.print("Enter your choice: ");

                choice = sc.nextInt();

                switch (choice) {

                    // ✅ CREATE ACCOUNT
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

                        System.out.print("Enter Amount: ");
                        double depAmt = sc.nextDouble();

                        service.deposit(depAcc, depAmt);
                        break;

                    // ✅ WITHDRAW
                    case 3:
                        System.out.print("Enter Account Number: ");
                        int withAcc = sc.nextInt();

                        System.out.print("Enter Amount: ");
                        double withAmt = sc.nextDouble();

                        service.withdraw(withAcc, withAmt);
                        break;

                    // ✅ DISPLAY
                    case 4:
                        service.displayAccounts();
                        break;

                    // ✅ TRANSFER
                    case 5:
                        System.out.print("From Account: ");
                        int from = sc.nextInt();

                        System.out.print("To Account: ");
                        int to = sc.nextInt();

                        System.out.print("Amount: ");
                        double amt = sc.nextDouble();

                        service.transfer(from, to, amt);
                        break;

                    // ✅ SEARCH BY ACCOUNT NUMBER
                    case 6:
                        System.out.print("Enter Account Number: ");
                        int searchAcc = sc.nextInt();

                        service.searchAccount(searchAcc);
                        break;

                    // ✅ SEARCH BY NAME
                    case 7:
                        sc.nextLine();
                        System.out.print("Enter Name: ");
                        String searchName = sc.nextLine();

                        service.searchByName(searchName);
                        break;

                    // ✅ UPDATE NAME
                    case 8:
                        System.out.print("Enter Account Number: ");
                        int updAcc = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter New Name: ");
                        String newName = sc.nextLine();

                        service.updateAccountName(updAcc, newName);
                        break;

                    // ✅ DELETE ACCOUNT
                    case 9:
                        System.out.print("Enter Account Number: ");
                        int delAcc = sc.nextInt();

                        service.deleteAccount(delAcc);
                        break;

                    // ✅ BANK STATISTICS
                    case 10:
                        service.showBankStats();
                        break;

                    // ✅ EXIT
                    case 11:
                        System.out.println("Exiting... Thank you!");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }

            }
            // ❌ WRONG INPUT TYPE
            catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter correct data type.");
                sc.nextLine(); // clear buffer
            }

            // ❌ ALL CUSTOM EXCEPTIONS
            catch (DuplicateAccountException | AccountNotFoundException | InvalidAmountException
                    | InsufficientBalanceException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 11);

        sc.close();
    }
}