package com.learn.bankaccount;

/**
 * @file TestBankAccount.java
 * @description Tests every BankAccount method, including edge cases, and prints the results.
 */
public class TestBankAccount {
    public static void main(String[] args) {

        // Create Accounts
        BankAccount account1 = new BankAccount();
        BankAccount account2 = new BankAccount();

        // === APP SIMULATION: DEPOSITS === //
        account1.deposit(1280.50, "checking");
        account1.deposit(320, "savings");

        account2.deposit(3000.00, "checking");

        // === APP SIMULATION: WITHDRAWALS === //
        account1.withdraw(450, "checking");
        account1.withdraw(25, "savings");

        account2.withdraw(600, "savings");

        // === GETTERS === //
        System.out.println("\n--- Getter Check (Account 1) ---");
        System.out.printf("Account Number: %d\n", account1.getAccountNumber());
        System.out.printf("Checking Balance: $%.2f\n", account1.getCheckingBalance());
        System.out.printf("Savings Balance: $%.2f\n", account1.getSavingsBalance());
        System.out.printf("Combined Balance: $%.2f\n", account1.getCombinedBalance());
        System.out.println("Account number is 10 digits: "
                + (String.valueOf(account1.getAccountNumber()).length() == 10));
        System.out.println("Account numbers are different: "
                + (account1.getAccountNumber() != account2.getAccountNumber()));

        // === EDGE CASES (Account 2) === //
        System.out.println("\n--- Edge: withdraw more than the checking balance ---");
        account2.withdraw(5000, "checking");

        System.out.println("--- Edge: withdraw the exact savings balance ---");
        account2.deposit(250.75, "savings");
        account2.withdraw(250.75, "savings");

        System.out.println("--- Edge: negative deposit ---");
        account2.deposit(-50, "checking");

        System.out.println("--- Edge: zero withdrawal ---");
        account2.withdraw(0, "checking");

        System.out.println("--- Edge: account type is not case-sensitive ---");
        account2.deposit(100, "CHECKING");

        System.out.println("--- Edge: invalid account type ---");
        account2.deposit(100, "retirement");
        account2.withdraw(10, "retirement");

        // === ACCOUNT SUMMARIES === //
        System.out.println("\n--- Account 1 Status ---");
        account1.displayAccountBalance();

        System.out.println("\n--- Account 2 Status ---");
        account2.displayAccountBalance();

        // === STATICS === //
        System.out.println("\n=== STATICS ===");
        System.out.println("Total Accounts Created: " + BankAccount.getNumberOfAccounts());
        System.out.printf("Total Money Across All Accounts: $%.2f\n", BankAccount.getTotalAmountOfMoney());

        double sumOfBalances = account1.getCombinedBalance() + account2.getCombinedBalance();
        System.out.printf("Sum of Individual Balances: $%.2f\n", sumOfBalances);
        System.out.println("Tracker matches balances: "
                + (Math.abs(sumOfBalances - BankAccount.getTotalAmountOfMoney()) < 0.005));
    }
}