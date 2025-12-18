// Transaction.java
// This class represents a single banking transaction (deposit, withdrawal, or transfer)

public class Transaction {
    // These are the "properties" or "attributes" of a transaction
    // Think of them as "pieces of information" we want to remember

    private String transactionType;  // What kind of transaction? (DEPOSIT, WITHDRAWAL, TRANSFER)
    private double amount;           // How much money was involved?
    private String date;             // When did this transaction happen?

    // This is a CONSTRUCTOR - a special method that creates a new Transaction object
    // It's called when we do: new Transaction("DEPOSIT", 100.0, "Dec 18, 2025")
    public Transaction(String transactionType, double amount, String date) {
        this.transactionType = transactionType;  // Store the type of transaction
        this.amount = amount;                    // Store the amount of money
        this.date = date;                        // Store the date
    }

    // This method converts the transaction information into readable text
    // It's automatically called when we try to print a Transaction object
    @Override
    public String toString() {
        // Return a formatted string showing all transaction details
        return String.format("[%s] %s: $%.2f", date, transactionType, amount);
        // Example output: "[Dec 18, 2025] DEPOSIT: $100.00"
    }
}