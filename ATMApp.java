import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Insufficient funds or invalid withdrawal amount.");
        }
    }
}

class ATM {
    private BankAccount bankAccount;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void showMenu() {
        System.out.println("ATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    public void processChoice(int choice) {
        switch (choice) {
            case 1:
                checkBalance();
                break;
            case 2:
                performDeposit();
                break;
            case 3:
                performWithdrawal();
                break;
            case 4:
                System.out.println("Exiting the ATM.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void checkBalance() {
        double balance = bankAccount.getBalance();
        System.out.println("Your account balance is: " + balance);
    }

    private void performDeposit() {
        System.out.print("Enter the amount to deposit: ");
        double amount = getUserAmount();
        bankAccount.deposit(amount);
    }

    private void performWithdrawal() {
        System.out.print("Enter the amount to withdraw: ");
        double amount = getUserAmount();
        bankAccount.withdraw(amount);
    }

    private double getUserAmount() {
        Scanner scanner = new Scanner(System.in);
        double amount = scanner.nextDouble();
        if (amount < 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
            amount = getUserAmount();
        }
        return amount;
    }
}

public class ATMApp {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(1000.0);
        ATM atm = new ATM(bankAccount);

        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        while (running) {
            atm.showMenu();
            int choice = scanner.nextInt();
            if (choice == 4) {
                running = false;
            } else {
                atm.processChoice(choice);
            }
            System.out.println();
        }
    }
}