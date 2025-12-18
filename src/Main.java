// Main.java
// This is the main entry point of our Bank Management Application
// It displays the menu and handles user interaction

import java.util.Scanner;  // Import Scanner - a tool to read user input from keyboard

public class Main {

    // This is the MAIN METHOD - the starting point of any Java program
    // When you run the program, Java looks for this method and starts here
    public static void main(String[] args) {

        // Create a new Bank object (our bank manager)
        Bank bank = new Bank();

        // Create a Scanner object to read user input from the keyboard
        Scanner scanner = new Scanner(System.in);

        // CREATE TEST DATA - Sample accounts for demonstration
        System.out.println("===========================================");
        System.out.println("   KIMBI BANK MANAGEMENT SYSTEM: LOADING...    ");
        System.out.println("===========================================\n");

        System.out.println("Creating test accounts...\n");

        // Create 3 sample accounts
        Account account1 = bank.createAccount("Manyi Takor");
        Account account2 = bank.createAccount("Jules Owona");
        Account account3 = bank.createAccount("Manka Olivia");

        // Add some money to the accounts (so we can test withdrawals/transfers)
        account1.deposit(5000.0);  // Manyi starts with $5000
        account2.deposit(3000.0);  // Jules starts with $3000
        account3.deposit(1500.0);  // Manka starts with $1500

        System.out.println("Test accounts created successfully!");
        System.out.println("- " + account1.getAccountNumber() + ": " + account1.getAccountHolderName() + " - $" + account1.getBalance());
        System.out.println("- " + account2.getAccountNumber() + ": " + account2.getAccountHolderName() + " - $" + account2.getBalance());
        System.out.println("- " + account3.getAccountNumber() + ": " + account3.getAccountHolderName() + " - $" + account3.getBalance());
        System.out.println();

        // MAIN PROGRAM LOOP - Keeps running until user chooses to exit
        boolean running = true;  // Flag to control the loop

        while (running) {  // Loop continues as long as running is true

            // DISPLAY MENU
            System.out.println("===========================================");
            System.out.println("       KIMBI BANK MANAGEMENT SYSTEM: MENU      ");
            System.out.println("===========================================");
            System.out.println("1. Create Account");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Transfer Money");
            System.out.println("4. Check Balance");
            System.out.println("5. View Transaction History");
            System.out.println("6. Exit");
            System.out.println("===========================================");
            System.out.print("Enter your choice (1-6): ");

            // READ USER INPUT
            int choice = 0;  // Variable to store user's choice

            // Try to read an integer from user input
            try {
                choice = scanner.nextInt();  // Read the number user types
                scanner.nextLine();  // Clear the input buffer (technical detail - don't worry about it)
            } catch (Exception e) {
                // If user types something that's not a number, handle the error
                System.out.println("\nError: Please enter a valid number (1-6)!\n");
                scanner.nextLine();  // Clear the bad input
                continue;  // Go back to the start of the loop (show menu again)
            }

            System.out.println();  // Print blank line for spacing

            // PROCESS USER CHOICE - Execute the appropriate action
            switch (choice) {

                case 1:  // CREATE ACCOUNT
                    System.out.println("--- Create New Account ---");
                    System.out.print("Enter account holder name: ");
                    String name = scanner.nextLine();  // Read the name user types

                    if (name.trim().isEmpty()) {  // Check if name is empty
                        System.out.println("Error: Name cannot be empty!\n");
                        break;
                    }

                    Account newAccount = bank.createAccount(name);
                    System.out.println("\nAccount created successfully!");
                    System.out.println("Account Number: " + newAccount.getAccountNumber());
                    System.out.println("Account Holder: " + newAccount.getAccountHolderName());
                    System.out.println("Initial Balance: $0.00\n");
                    break;

                case 2:  // WITHDRAW MONEY
                    System.out.println("--- Withdraw Money ---");
                    System.out.print("Enter account number: ");
                    String withdrawAccountNumber = scanner.nextLine();

                    System.out.print("Enter amount to withdraw: $");
                    double withdrawAmount = 0;

                    try {
                        withdrawAmount = scanner.nextDouble();
                        scanner.nextLine();  // Clear buffer
                    } catch (Exception e) {
                        System.out.println("Error: Invalid amount!\n");
                        scanner.nextLine();
                        break;
                    }

                    System.out.println();
                    bank.withdraw(withdrawAccountNumber, withdrawAmount);
                    System.out.println();
                    break;

                case 3:  // TRANSFER MONEY
                    System.out.println("--- Transfer Money ---");
                    System.out.print("Enter source account number (FROM): ");
                    String fromAccount = scanner.nextLine();

                    System.out.print("Enter destination account number (TO): ");
                    String toAccount = scanner.nextLine();

                    System.out.print("Enter amount to transfer: $");
                    double transferAmount = 0;

                    try {
                        transferAmount = scanner.nextDouble();
                        scanner.nextLine();  // Clear buffer
                    } catch (Exception e) {
                        System.out.println("Error: Invalid amount!\n");
                        scanner.nextLine();
                        break;
                    }

                    System.out.println();
                    bank.transfer(fromAccount, toAccount, transferAmount);
                    System.out.println();
                    break;

                case 4:  // CHECK BALANCE
                    System.out.println("--- Check Balance ---");
                    System.out.print("Enter account number: ");
                    String balanceAccountNumber = scanner.nextLine();

                    bank.displayBalance(balanceAccountNumber);
                    break;

                case 5:  // VIEW TRANSACTION HISTORY
                    System.out.println("--- Transaction History ---");
                    System.out.print("Enter account number: ");
                    String historyAccountNumber = scanner.nextLine();

                    bank.displayTransactionHistory(historyAccountNumber);
                    break;

                case 6:  // EXIT
                    System.out.println("===========================================");
                    System.out.println("   Thank you for using Kimbi Bank System!   ");
                    System.out.println("===========================================\n");
                    running = false;  // Set flag to false - this exits the loop
                    break;

                default:  // Invalid choice (not 1-6)
                    System.out.println("Error: Invalid choice! Please enter a number between 1 and 6.\n");
                    break;
            }
        }

        // Close the scanner (cleanup - good practice)
        scanner.close();
    }
}