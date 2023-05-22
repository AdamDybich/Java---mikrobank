import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Account {
    private double balance;

    public Account() {
        this.balance = 0;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
            System.out.println("Amount withdrawn successfully.");
        } else {
            System.out.println("Insufficient funds");
        }
    }

    public double getBalance() {
        return this.balance;
    }
}

public class Main {
    public static void main(String[] args) {
        Account account = new Account();
        loadBalanceFromFile(account); // Wczytanie salda z pliku na początku programu
        System.out.println("Initial balance: " + account.getBalance());

        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("Menu:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Show Balance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount); // Wywołanie metody deposit() z klasy Account
                    System.out.println("Amount deposited successfully.");
                    break;
                case 2:
                    System.out.print("Withdraw amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount); // Wywołanie metody withdraw() z klasy Account
                    break;
                case 3:
                    System.out.println("Current balance: " + account.getBalance()); // Wywołanie metody getBalance() z klasy Account
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.println();

        } while (choice != 4);

        // Zapisywanie kwoty do pliku
        saveBalanceToFile(account);
    }

    private static void loadBalanceFromFile(Account account) {
        try {
            FileReader fileReader = new FileReader("balance.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String balanceString = bufferedReader.readLine();
            if (balanceString != null) {
                double balance = Double.parseDouble(balanceString.split(": ")[1]);
                account.deposit(balance);
                System.out.println("Balance loaded from file: " + balance);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Error while loading balance from file: " + e.getMessage());
        }
    }

    private static void saveBalanceToFile(Account account) {
        try {
            FileWriter fileWriter = new FileWriter("balance.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("Final balance: " + account.getBalance()); // Wywołanie metody getBalance() z klasy Account
            bufferedWriter.close();
            System.out.println("Balance saved to file.");
        } catch (IOException e) {
            System.out.println("Error while saving balance to file: " + e.getMessage());
        }
    }
}