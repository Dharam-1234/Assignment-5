// Build a class Account and derive SavingsAccount and CurrentAccount. Include interest calculation and withdrawal limits.


// Base class
class Account {
    protected String accountHolder;
    protected String accountNumber;
    protected double balance;

    public Account(String accountHolder, String accountNumber, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: $" + amount);
    }

    public void displayBalance() {
        System.out.println("Account Balance: $" + balance);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        } else {
            System.out.println("Insufficient balance.");
        }
    }
}

// SavingsAccount class
class SavingsAccount extends Account {
    private double interestRate; // annual interest rate

    public SavingsAccount(String accountHolder, String accountNumber, double balance, double interestRate) {
        super(accountHolder, accountNumber, balance);
        this.interestRate = interestRate;
    }

    public void calculateInterest(int years) {
        double interest = balance * interestRate * years / 100;
        balance += interest;
        System.out.println("Interest added: $" + interest);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 1000) {
            System.out.println("Withdrawal limit exceeded! Max allowed: $1000 per transaction.");
        } else {
            super.withdraw(amount);
        }
    }
}

// CurrentAccount class
class CurrentAccount extends Account {
    private double overdraftLimit;

    public CurrentAccount(String accountHolder, String accountNumber, double balance, double overdraftLimit) {
        super(accountHolder, accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount > balance + overdraftLimit) {
            System.out.println("Overdraft limit exceeded!");
        } else {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        }
    }
}
public class BankSystem {
    public static void main(String[] args) {
        // Savings account test
        SavingsAccount savings = new SavingsAccount("Alice", "SA1001", 5000, 4.5);
        System.out.println("Savings Account:");
        savings.displayBalance();
        savings.calculateInterest(2); // 2 years of interest
        savings.withdraw(1200); // Over limit
        savings.withdraw(800);  // Allowed
        savings.displayBalance();

        System.out.println("\n------------------------\n");

        // Current account test
        CurrentAccount current = new CurrentAccount("Bob", "CA2001", 3000, 1000);
        System.out.println("Current Account:");
        current.displayBalance();
        current.withdraw(3500); // Within overdraft
        current.withdraw(600);  // Exceeds overdraft
        current.displayBalance();
    }
}

// output:
// Savings Account:
// Account Balance: $5000.0
// Interest added: $450.0
// Withdrawal limit exceeded! Max allowed: $1000 per transaction.
// Withdrawn: $800.0
// Account Balance: $4650.0

// ------------------------

// Current Account:
// Account Balance: $3000.0
// Withdrawn: $3500.0
// Overdraft limit exceeded!
// Account Balance: $-500.0
