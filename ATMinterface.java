import java.util.Scanner;
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = Math.max(0, initialBalance);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            
            System.out.printf("Successfully deposited: $%.2f.%n", amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        }
        if (balance >= amount) {
            balance -= amount;
            System.out.printf("Successfully withdrew: $%.2f.%n", amount);
            return true;
        } else {
            System.out.printf("Insufficient balance. You tried to withdraw: $%.2f%n", amount);
            return false;
        }
    }
}

class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    
    private double getValidatedDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine(); 
            try {
                double value = Double.parseDouble(input);
                if (value <= 0) { 
                    System.out.println("Amount must be positive. Please try again.");
                    continue; 
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number (e.g., 100.00).");
            }
        }
    }

    private int getValidatedIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine(); 
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number for your choice.");
            }
        }
    }
    public void start() {
        int choice;
        do {
            System.out.println("\n--- ATM Information ---");
            
            choice = getValidatedIntInput("Plz,Enter your choice for checking ATM information: ");

            switch (choice) {
                case 1:
                    System.out.printf("Your current balance is: $%.2f%n", account.getBalance());
                    break;
                case 2:
                    System.out.printf("Your current balance: $%.2f%n", account.getBalance()); // Show current
                    double depositAmount = getValidatedDoubleInput("Enter amount to deposit: $");
                    account.deposit(depositAmount);
                    
                    System.out.printf("Your new balance is: $%.2f%n", account.getBalance());
                    break;
                case 3:
                    System.out.printf("Your current balance: $%.2f%n", account.getBalance()); 
                    double withdrawAmount = getValidatedDoubleInput("Enter amount to withdraw: $");
                    boolean success = account.withdraw(withdrawAmount);
                    
                    if (success) {
                        System.out.printf("Your new balance is: $%.2f%n", account.getBalance());
                    } else {
                        
                        System.out.printf("Your current balance remains: $%.2f%n", account.getBalance());
                    }
                    break;
                case 4:
                    System.out.println("Thank you for using ....ATM!");
                    break;
                default:
                    System.out.println("Invalid option. Please enter a number between 1 and 4.");
            }
        } while (choice != 4);
        scanner.close(); 
    }
}

public class ATMinterface {
    public static void main(String[] args) {
        
        BankAccount userAccount = new BankAccount(1250.75); 
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}