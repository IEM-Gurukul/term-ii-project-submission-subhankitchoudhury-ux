package src;
import services.*;
import model.*;
import java.util.*;
import java.util.List;
import java.util.Scanner;
import services.*;
import model.*;

public class Main {
    public static void main(String[] args) {

        BankingService bankingService = new BankingService();
        Scanner sc = new Scanner(System.in);

        User currentUser = null;

        while (true) {
            try {
                if (currentUser == null) {
                    System.out.println("\n===== WELCOME TO MINI BANKING SYSTEM =====");
                    System.out.println("1. New User (Register)");
                    System.out.println("2. Existing User (Login)");
                    System.out.println("3. Exit");
                    System.out.print("Enter choice: ");

                    int choice = sc.nextInt();
                    sc.nextLine();

                    switch (choice) {
                        case 1:
                            System.out.print("Enter Username: ");
                            String username = sc.nextLine();

                            System.out.print("Enter Password: ");
                            String password = sc.nextLine();

                            currentUser = bankingService.registerUser(username, password);
                            System.out.println("Registration successful! Your User ID: " + currentUser.getUserId());
                            break;

                        case 2:
                            System.out.println("Login using:");
                            System.out.println("1. Username");
                            System.out.println("2. User ID");
                            int loginChoice = sc.nextInt();
                            sc.nextLine();

                            if (loginChoice == 1) {
                                System.out.print("Enter Username: ");
                                String uname = sc.nextLine();

                                System.out.print("Enter Password: ");
                                String pass = sc.nextLine();

                                currentUser = bankingService.loginByUsername(uname, pass);
                            } else if (loginChoice == 2) {
                                System.out.print("Enter User ID: ");
                                int uid = sc.nextInt();
                                sc.nextLine();

                                System.out.print("Enter Password: ");
                                String pass = sc.nextLine();

                                currentUser = bankingService.loginByUserId(uid, pass);
                            }

                            System.out.println("Login successful!");
                            break;

                        case 3:
                            System.out.println("Thank you for using the system!");
                            sc.close();
                            return;

                        default:
                            System.out.println("Invalid choice!");
                    }
                } else {
                    System.out.println("\n===== USER MENU =====");
                    System.out.println("1. Create New Account");
                    System.out.println("2. Deposit");
                    System.out.println("3. Withdraw");
                    System.out.println("4. Transfer");
                    System.out.println("5. View My Accounts");
                    System.out.println("6. View Transactions");
                    System.out.println("7. Change Password");
                    System.out.println("8. Logout");
                    System.out.print("Enter choice: ");

                    int choice = sc.nextInt();
                    sc.nextLine();

                    switch (choice) {
                        case 1:
                            System.out.print("Enter Initial Balance: ");
                            double balance = sc.nextDouble();
                            Account acc = bankingService.createAccount(currentUser.getUserId(), balance);
                            System.out.println("Account created! Account Number: " + acc.getAccountNumber());
                            break;

                        case 2:
                            System.out.print("Enter Account Number: ");
                            int depAcc = sc.nextInt();

                            System.out.print("Enter Amount: ");
                            double depAmt = sc.nextDouble();

                            bankingService.deposit(depAcc, depAmt);
                            System.out.println("Deposit successful!");
                            break;

                        case 3:
                            System.out.print("Enter Account Number: ");
                            int withAcc = sc.nextInt();

                            System.out.print("Enter Amount: ");
                            double withAmt = sc.nextDouble();

                            bankingService.withdraw(withAcc, withAmt);
                            System.out.println("Withdrawal successful!");
                            break;

                        case 4:
                            System.out.print("From Account: ");
                            int from = sc.nextInt();

                            System.out.print("To Account: ");
                            int to = sc.nextInt();

                            System.out.print("Amount: ");
                            double amt = sc.nextDouble();

                            bankingService.transfer(from, to, amt);
                            System.out.println("Transfer successful!");
                            break;

                        case 5:
                            List<Account> accounts = bankingService.getUserAccounts(currentUser.getUserId());
                            for (Account a : accounts) {
                                System.out.println(
                                        "Account No: " + a.getAccountNumber() + " | Balance: " + a.getBalance());
                            }
                            break;

                        case 6:
                            System.out.print("Enter Account Number: ");
                            int accNo = sc.nextInt();

                            List<Transaction> txns = bankingService.getAccountTransactions(accNo);
                            for (Transaction t : txns) {
                                System.out.println(
                                        t.getTransactionId() + " | " +
                                                t.getType() + " | " +
                                                t.getAmount() + " | " +
                                                t.getDateTime());
                            }
                            break;

                        case 7:
                            System.out.print("Enter Old Password: ");
                            String oldPass = sc.nextLine();

                            System.out.print("Enter New Password: ");
                            String newPass = sc.nextLine();

                            bankingService.changePassword(currentUser.getUserId(), oldPass, newPass);
                            System.out.println("Password changed successfully!");
                            break;

                        case 8:
                            currentUser = null;
                            System.out.println("Logged out successfully!");
                            break;

                        default:
                            System.out.println("Invalid choice!");
                    }
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}