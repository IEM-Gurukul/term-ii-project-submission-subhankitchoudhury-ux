package src;


import services.*;
import model.*;
import java.util.*;

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

                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid input! Enter a number.");
                        sc.next();
                        continue;
                    }

                    int choice = sc.nextInt();
                    sc.nextLine();

                    switch (choice) {

                        case 1:
                            System.out.print("Enter Username: ");
                            String username = sc.nextLine();

                            System.out.print("Enter Password: ");
                            String password = sc.nextLine();

                            currentUser = bankingService.registerUser(username, password);
                            System.out.println("Registered! Your User ID: " + currentUser.getUserId());
                            break;

                        case 2:
                            System.out.println("Login using:");
                            System.out.println("1. Username");
                            System.out.println("2. User ID");

                            if (!sc.hasNextInt()) {
                                System.out.println("Invalid choice!");
                                sc.next();
                                continue;
                            }

                            int loginChoice = sc.nextInt();
                            sc.nextLine();

                            if (loginChoice == 1) {
                                System.out.print("Username: ");
                                String uname = sc.nextLine();

                                System.out.print("Password: ");
                                String pass = sc.nextLine();

                                currentUser = bankingService.loginByUsername(uname, pass);

                            } else if (loginChoice == 2) {
                                System.out.print("User ID: ");
                                int uid = sc.nextInt();
                                sc.nextLine();

                                System.out.print("Password: ");
                                String pass = sc.nextLine();

                                currentUser = bankingService.loginByUserId(uid, pass);

                            } else {
                                System.out.println("Invalid login option!");
                                continue;
                            }

                            System.out.println("Login successful!");
                            break;

                        case 3:
                            System.out.println("Thank you!");
                            sc.close();
                            return;

                        default:
                            System.out.println("Invalid choice!");
                    }

                } else {

                    System.out.println("\n===== USER MENU =====");
                    System.out.println("1. Create Account");
                    System.out.println("2. Deposit");
                    System.out.println("3. Withdraw");
                    System.out.println("4. Transfer");
                    System.out.println("5. View Accounts");
                    System.out.println("6. Transactions");
                    System.out.println("7. Change Password");
                    System.out.println("8. Logout");

                    System.out.print("Enter choice: ");

                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid input!");
                        sc.next();
                        continue;
                    }

                    int choice = sc.nextInt();
                    sc.nextLine();

                    switch (choice) {

                        case 1:
                            System.out.print("Initial Balance: ");
                            double bal = sc.nextDouble();
                            Account acc = bankingService.createAccount(currentUser.getUserId(), bal);
                            System.out.println("Account Created! No: " + acc.getAccountNumber());
                            break;

                        case 2:
                            System.out.print("Account No: ");
                            int dAcc = sc.nextInt();
                            System.out.print("Amount: ");
                            double dAmt = sc.nextDouble();

                            bankingService.deposit(dAcc, dAmt);
                            System.out.println("Deposited!");
                            break;

                        case 3:
                            System.out.print("Account No: ");
                            int wAcc = sc.nextInt();
                            System.out.print("Amount: ");
                            double wAmt = sc.nextDouble();

                            bankingService.withdraw(wAcc, wAmt);
                            System.out.println("Withdrawn!");
                            break;

                        case 4:
                            System.out.print("From Acc: ");
                            int from = sc.nextInt();
                            System.out.print("To Acc: ");
                            int to = sc.nextInt();
                            System.out.print("Amount: ");
                            double amt = sc.nextDouble();

                            bankingService.transfer(from, to, amt);
                            System.out.println("Transferred!");
                            break;

                        case 5:
                            List<Account> list = bankingService.getUserAccounts(currentUser.getUserId());
                            if (list.isEmpty()) {
                                System.out.println("No accounts found!");
                            }
                            for (Account a : list) {
                                System.out.println(a.getAccountNumber() + " | " + a.getBalance());
                            }
                            break;

                        case 6:
                            System.out.print("Account No: ");
                            int accNo = sc.nextInt();

                            List<Transaction> txns = bankingService.getAccountTransactions(accNo);
                            if (txns.isEmpty()) {
                                System.out.println("No transactions!");
                            }
                            for (Transaction t : txns) {
                                System.out.println(t.getTransactionId() + " | " + t.getType() + " | " + t.getAmount());
                            }
                            break;

                        case 7:
                            System.out.print("Old Password: ");
                            String oldP = sc.nextLine();

                            System.out.print("New Password: ");
                            String newP = sc.nextLine();

                            bankingService.changePassword(currentUser.getUserId(), oldP, newP);
                            System.out.println("Password changed!");
                            break;

                        case 8:
                            currentUser = null;
                            System.out.println("Logged out!");
                            break;

                        default:
                            System.out.println("Invalid choice!");
                    }
                }

            } catch (Exception e) {
                System.out.println("Error: " + (e.getMessage() != null ? e.getMessage() : "Something went wrong"));
                sc.nextLine();
            }
        }
    }
}