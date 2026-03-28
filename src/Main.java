import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        BankingService service = new BankingService();
        Scanner sc = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\n===== MINI BANKING SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Display Accounts");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Account Number: ");
                    int accNo = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Initial Balance: ");
                    double balance = sc.nextDouble();

                    service.createAccount(accNo, name, balance);
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    int depAcc = sc.nextInt();

                    System.out.print("Enter Amount: ");
                    double depAmt = sc.nextDouble();

                    service.deposit(depAcc, depAmt);
                    break;

                case 3:
                    System.out.print("Enter Account Number: ");
                    int withAcc = sc.nextInt();

                    System.out.print("Enter Amount: ");
                    double withAmt = sc.nextDouble();

                    service.withdraw(withAcc, withAmt);
                    break;

                case 4:
                    service.displayAccounts();
                    break;

                case 5:
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}