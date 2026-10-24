package com.learn.bankaccount;

import java.util.Random;

/**
 * @file BankAccount.java
 * @description Models a bank account with checking and savings balances, plus class-wide trackers for the number of accounts and the total money stored.
 */
public class BankAccount {
    // === CLASS ATTRIBUTES (Private for Encapsulation) ==== //
    /**
     * Users cannot set any attributes directly, so every field is private.
     * The account number is final because it never changes after creation.
     */
    private double checkingBalance;
    private double savingsBalance;
    private final long accountNumber;

    // === STATIC / CLASS MEMBERS ========================== //
    private static int numberOfAccounts = 0;
    private static double totalAmountOfMoney = 0.0;
    private static final Random RANDOM = new Random();

    // === CONSTRUCTORS ==================================== //
    /**
     * Creates an account with zero balances and a random ten-digit account number.
     * Also increments the class-wide account count.
     */
    public BankAccount() {
        BankAccount.numberOfAccounts++;
        this.checkingBalance = 0;
        this.savingsBalance = 0;
        this.accountNumber = generateAccountNumber();
    }

    // === METHODS ========================================= //

    /**
     * Deposits money into the checking or savings account and adds it to the class-wide total.
     * Invalid account types and amounts of $0 or less are rejected.
     * @param amount the dollar amount to deposit, which must be greater than 0
     * @param accountType either "checking" or "savings" (not case-sensitive)
     */
    public void deposit(double amount, String accountType) {
        System.out.println("==== BankAccount - DEPOSIT ======");
        if (isInvalidTransaction(amount, accountType)) {
            printExitLine();
            return;
        }

        if (accountType.equalsIgnoreCase("checking")) {
            this.checkingBalance += amount;
        } else {
            this.savingsBalance += amount;
        }
        BankAccount.totalAmountOfMoney += amount;

        System.out.printf(" > You deposited $%.2f to your %s account.\n", amount, accountType.toLowerCase());
        printExitLine();
    }

    /**
     * Withdraws money from the checking or savings account and subtracts it from the class-wide total.
     * Invalid account types, amounts of $0 or less, and insufficient funds are rejected.
     * @param amount the dollar amount to withdraw, which must be greater than 0
     * @param accountType either "checking" or "savings" (not case-sensitive)
     */
    public void withdraw(double amount, String accountType) {
        System.out.println("=== BankAccount - WITHDRAWAL ====");

        if (isInvalidTransaction(amount, accountType)) {
            printExitLine();
            return;
        }

        if (!hasSufficientFunds(amount, accountType)) {
            System.out.println(" > ERROR: Insufficient funds!");
            printExitLine();
            return;
        }

        if (accountType.equalsIgnoreCase("checking")) {
            this.checkingBalance -= amount;
        } else {
            this.savingsBalance -= amount;
        }
        BankAccount.totalAmountOfMoney -= amount;

        System.out.printf(" > You withdrew $%.2f from your %s account.\n", amount, accountType.toLowerCase());
        printExitLine();
    }

    /** Prints a boxed summary of the account number and balances. */
    public void displayAccountBalance() {
        System.out.println("+------------------------------+");
        System.out.printf( "| Account Number: %12d |\n", this.accountNumber);
        System.out.println("+------------------------------+");
        System.out.printf( "| Checking Balance: %10s |\n", String.format("$ %.2f", this.checkingBalance));
        System.out.printf( "| Savings Balance: %11s |\n", String.format("$ %.2f", this.savingsBalance));
        System.out.printf( "| Combined Balance: %10s |\n", String.format("$ %.2f", getCombinedBalance()));
        System.out.println("+------------------------------+");
    }

    // === PRIVATE HELPERS ================================= //

    /**
     * Checks that the account type is valid and the amount is positive.
     * Prints the reason when the request is rejected.
     * @param amount the dollar amount for the transaction
     * @param accountType the account the transaction targets
     * @return true if the request can proceed, false otherwise
     */
    private static boolean isInvalidTransaction(double amount, String accountType) {
        if ( !"checking".equalsIgnoreCase(accountType) && !"savings".equalsIgnoreCase(accountType) ) {
            System.out.println(" > ERROR: Account Type must be 'checking' or 'savings'!");
            return true;
        }
        if (amount <= 0) {
            System.out.println(" > ERROR: Amount must be greater than $0.00!");
            return true;
        }
        return false;
    }

    /**
     * Checks whether the chosen account holds at least the requested amount.
     * @param amount the dollar amount to withdraw
     * @param accountType either "checking" or "savings" (not case-sensitive)
     * @return true if the balance covers the amount, false otherwise
     */
    private boolean hasSufficientFunds(double amount, String accountType) {
        if (accountType.equalsIgnoreCase("checking")) {
            return amount <= this.checkingBalance;
        } else if (accountType.equalsIgnoreCase("savings")) {
            return amount <= this.savingsBalance;
        }
        return false;
    }

    /**
     * Generates a random ten-digit account number from 1,000,000,000 to 9,999,999,999.
     * @return a random ten-digit account number
     */
    private static long generateAccountNumber() {
        return 1_000_000_000L + RANDOM.nextLong(9_000_000_000L);
    }

    /** Prints the closing line that ends each transaction block. */
    private static void printExitLine() {
        System.out.println("============= EXIT ==============\n");
    }

    // === GETTERS (No Setters, to preserve encapsulation) === //

    public double getCheckingBalance() {
        return checkingBalance;
    }

    public double getSavingsBalance() {
        return savingsBalance;
    }

    public double getCombinedBalance() {
        return this.checkingBalance + this.savingsBalance;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public static int getNumberOfAccounts() {
        return numberOfAccounts;
    }

    public static double getTotalAmountOfMoney() {
        return totalAmountOfMoney;
    }
}
