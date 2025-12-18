// Account.java
// This class represents a single bank account with balance and transaction history

import java.util.ArrayList;  // Import ArrayList - a tool to store lists of things

public class Account {
    // These are the "properties" every account has

    private String accountNumber;        // Unique ID for this account (e.g., "ACC001")
    private String accountHolderName;    // Owner's name (e.g., "John Doe")
    private double balance;              // Current money in account (e.g., 5000.0)
    private ArrayList<Transaction> transactions;  // List of all transactions for this account

    // CONSTRUCTOR - Creates a new Account object
    // Called when we do: new Account("ACC001", "John Doe")
    public Account(String accountNumber, String accountHolderName) {
        this.accountNumber = accountNumber;          // Store the account number
        this.accountHolderName = accountHolderName;  // Store the owner's name
        this.balance = 0.0;                          // Start with $0 balance
        this.transactions = new ArrayList<>();       // Create an empty list for transactions
    }

    // GETTER METHODS - These let other classes READ our private data

    public String getAccountNumber() {
        return accountNumber;  // Return the account number
    }

    public String getAccountHolderName() {
        return accountHolderName;  // Return the owner's name
    }

    public double getBalance() {
        return balance;  // Return current balance
    }

    // DEPOSIT METHOD - Adds money to the account
    public void deposit(double amount) {
        balance += amount;  // Add the amount to current balance (balance = balance + amount)
        // Record this deposit in transaction history
        addTransaction("DEPOSIT", amount);
    }

    // WITHDRAW METHOD - Removes money from the account
    // Returns true if successful, false if not enough money
    public boolean withdraw(double amount) {
        if (balance >= amount) {  // Check if we have enough money
            balance -= amount;    // Subtract the amount (balance = balance - amount)
            addTransaction("WITHDRAWAL", amount);  // Record the withdrawal
            return true;  // Success!
        } else {
            return false;  // Failed - not enough money
        }
    }

    // ADD TRANSACTION METHOD - Records a transaction in history
    // This is a private helper method (only used inside this class)
    private void addTransaction(String type, double amount) {
        // Get current date/time as a simple string
        String date = java.time.LocalDateTime.now().toString().substring(0, 19);
        // Create a new Transaction object and add it to our list
        Transaction transaction = new Transaction(type, amount, date);
        transactions.add(transaction);
    }

    // GET TRANSACTION HISTORY METHOD - Returns list of all transactions
    public ArrayList<Transaction> getTransactionHistory() {
        return transactions;  // Return the list
    }

    // TO STRING METHOD - Creates readable text version of account info
    @Override
    public String toString() {
        return String.format("Account: %s | Holder: %s | Balance: $%.2f",
                accountNumber, accountHolderName, balance);
        // Example: "Account: ACC001 | Holder: John Doe | Balance: $5000.00"
    }
}