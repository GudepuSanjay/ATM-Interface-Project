import java.util.*;

class User {
    private String username;
    private String pin;
    private double balance;
    private List<String> transactionHistory;

    public User(String username, String pin, double balance) {
        this.username = username;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPin(String inputPin) {
        return pin.equals(inputPin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposited: " + amount);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrawn: " + amount);
        } else {
            transactionHistory.add("Withdrawal failed: Insufficient funds.");
        }
    }

    public void viewTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }
}

public class ATMInterface {
    private static Map<String, User> users = new HashMap<>();

    static {
        // Creating some sample users
        users.put("user1", new User("user1", "1234", 1000));
        users.put("user2", new User("user2", "5678", 2000));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User login
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        if (!users.containsKey(username)) {
            System.out.println("User not found!");
            return;
        }

        User currentUser = users.get(username);
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        if (!currentUser.checkPin(pin)) {
            System.out.println("Incorrect PIN!");
            return;
        }

        // ATM menu
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. View Transaction History");
            System.out.println("5. Exit");

            System.out.print("Select an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    currentUser.deposit(depositAmount);
                    break;

                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    currentUser.withdraw(withdrawAmount);
                    break;

                case 3:
                    System.out.println("Current Balance: " + currentUser.getBalance());
                    break;

                case 4:
                    currentUser.viewTransactionHistory();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
}
