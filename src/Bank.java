// Bank.java
// This class manages all bank accounts and operations (like a bank manager)

import java.util.ArrayList;  // Import ArrayList to store multiple accounts

public class Bank {
    // These are the "properties" the bank has

    private ArrayList<Account> accounts;  // List of ALL accounts in the bank
    private int accountCounter;           // Counter to generate unique account numbers

    // CONSTRUCTOR - Creates a new Bank object
    public Bank() {
        this.accounts = new ArrayList<>();  // Create empty list of accounts
        this.accountCounter = 1;            // Start account numbering at 1
    }

    // CREATE ACCOUNT METHOD - Creates a new bank account
    // Returns the newly created Account object
    public Account createAccount(String accountHolderName) {
        // Generate unique account number (e.g., "ACC001", "ACC002", etc.)
        String accountNumber = String.format("ACC%03d", accountCounter);
        // %03d means: number with 3 digits, pad with zeros (1 becomes 001)

        accountCounter++;  // Increase counter for next account

        // Create the new account
        Account newAccount = new Account(accountNumber, accountHolderName);

        // Add it to our list of accounts
        accounts.add(newAccount);

        // Return the new account so we can use it
        return newAccount;
    }

    // FIND ACCOUNT METHOD - Searches for an account by account number
    // Returns the Account if found, or null if not found
    public Account findAccount(String accountNumber) {
        // Loop through all accounts in our list
        for (Account account : accounts) {
            // Check if this account's number matches what we're looking for
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;  // Found it! Return the account
            }
        }
        return null;  // Not found - return null
    }

    // WITHDRAW METHOD - Processes a withdrawal from an account
    // Returns true if successful, false if failed
    public boolean withdraw(String accountNumber, double amount) {
        // First, find the account
        Account account = findAccount(accountNumber);

        // Check if account exists
        if (account == null) {
            System.out.println("Error: Account not found!");
            return false;
        }

        // Check if amount is valid (positive number)
        if (amount <= 0) {
            System.out.println("Error: Amount must be positive!");
            return false;
        }

        // Try to withdraw from the account
        boolean success = account.withdraw(amount);

        if (success) {
            System.out.println("Withdrawal successful!");
            System.out.println("New balance: $" + account.getBalance());
            return true;
        } else {
            System.out.println("Error: Insufficient funds!");
            System.out.println("Current balance: $" + account.getBalance());
            return false;
        }
    }

    // TRANSFER METHOD - Transfers money from one account to another
    // Returns true if successful, false if failed
    public boolean transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        // Find both accounts
        Account fromAccount = findAccount(fromAccountNumber);
        Account toAccount = findAccount(toAccountNumber);

        // Check if both accounts exist
        if (fromAccount == null) {
            System.out.println("Error: Source account not found!");
            return false;
        }

        if (toAccount == null) {
            System.out.println("Error: Destination account not found!");
            return false;
        }

        // Check if amount is valid
        if (amount <= 0) {
            System.out.println("Error: Amount must be positive!");
            return false;
        }

        // Check if source account has enough money
        if (fromAccount.getBalance() < amount) {
            System.out.println("Error: Insufficient funds in source account!");
            System.out.println("Available balance: $" + fromAccount.getBalance());
            return false;
        }

        // Perform the transfer (withdraw from source, deposit to destination)
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);

        System.out.println("Transfer successful!");
        System.out.println(fromAccount.getAccountHolderName() + " new balance: $" + fromAccount.getBalance());
        System.out.println(toAccount.getAccountHolderName() + " new balance: $" + toAccount.getBalance());

        return true;
    }

    // DISPLAY BALANCE METHOD - Shows the balance of an account
    public void displayBalance(String accountNumber) {
        // Find the account
        Account account = findAccount(accountNumber);

        // Check if account exists
        if (account == null) {
            System.out.println("Error: Account not found!");
            return;
        }

        // Display the account information
        System.out.println("\n--- Account Balance ---");
        System.out.println(account);  // Uses Account's toString() method
        System.out.println("------------------------\n");
    }

    // DISPLAY TRANSACTION HISTORY METHOD - Shows all transactions for an account
    public void displayTransactionHistory(String accountNumber) {
        // Find the account
        Account account = findAccount(accountNumber);

        // Check if account exists
        if (account == null) {
            System.out.println("Error: Account not found!");
            return;
        }

        // Get the transaction history
        ArrayList<Transaction> history = account.getTransactionHistory();

        // Display header
        System.out.println("\n--- Transaction History for " + account.getAccountHolderName() + " ---");
        System.out.println("Account Number: " + accountNumber);

        // Check if there are any transactions
        if (history.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            // Display each transaction
            for (Transaction transaction : history) {
                System.out.println(transaction);  // Uses Transaction's toString() method
            }
        }

        System.out.println("Current Balance: $" + account.getBalance());
        System.out.println("------------------------------------------\n");
    }

    // GET ALL ACCOUNTS METHOD - Returns list of all accounts (useful for testing)
    public ArrayList<Account> getAllAccounts() {
        return accounts;
    }
}