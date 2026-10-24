# 11 - Bank Account

## Overview

This challenge builds a `BankAccount` class with checking and savings balances, plus class-wide (static) trackers for the number of accounts created and the total money stored across all of them. Users can't set any attribute directly. Money moves only through `deposit` and `withdraw`, which reject invalid account types, amounts of $0 or less, and overdrafts. The bonus feature gives every account a random ten-digit account number. `TestBankAccount` exercises every method, including edge cases.

**Package:** `com.learn.bankaccount`

**Class file:** `BankAccount.java`
**Controller file:** `TestBankAccount.java`

## Table of Contents

- [Logic Flow](#logic-flow)
- [Concepts Implemented](#concepts-implemented)
- [Expected Output](#expected-output)
- [Steps to Run](#steps-to-run)

## Logic Flow

### `BankAccount`
1. **Creation:** the constructor increments the static account count, sets both balances to `0`, and assigns a random ten-digit account number.
2. **`deposit(amount, accountType)`:**
    1. `isInvalidTransaction` rejects unrecognized account types and amounts of $0 or less.
    2. The amount is added to the checking or savings balance.
    3. The amount is added to the static total.
3. **`withdraw(amount, accountType)`:**
    1. `isInvalidTransaction` runs the same checks as deposit.
    2. `hasSufficientFunds` rejects withdrawals larger than the chosen balance.
    3. The amount is subtracted from the balance and from the static total.
4. **`displayAccountBalance()`:** prints a boxed summary of the account number, checking, savings, and combined balances.
5. **Getters:** read the balances, combined balance, account number, and the static trackers. There are no setters.

### `TestBankAccount`
1. Creates two accounts.
2. Runs core deposits and withdrawals on both.
3. Checks every getter, the ten-digit account number, and that the two account numbers differ.
4. Runs edge cases on account 2.
5. Displays both account summaries.
6. Prints the static trackers and checks that the tracker equals the sum of the individual balances.

## Concepts Implemented

**Encapsulation.** `checkingBalance`, `savingsBalance`, and `accountNumber` are private, and the class has getters but no setters. The only way to change a balance is through `deposit` or `withdraw`, so every change passes through validation. This meets the requirement that users can't set any attributes.

**Static (class-level) members.** `numberOfAccounts` and `totalAmountOfMoney` belong to the class rather than to any one object. The constructor increments the count, and `deposit` and `withdraw` update the total. They are also private, with `public static` getters, so outside code can read them but can't overwrite them.

**`final` fields.** `accountNumber` is `final` because it's assigned once in the constructor and never changes.

**Private helper methods.** `isInvalidTransaction`, `hasSufficientFunds`, `generateAccountNumber`, and `printExitLine` are private because only `BankAccount` needs them. Putting validation in one helper means `deposit` and `withdraw` can't drift apart on the rules. The helpers that don't use instance state are `static`. See `notes/06-encapsulation-and-access-modifiers.md`.

**Guard clauses (early return).** Each public method checks for bad input first and returns immediately after printing the reason. The main logic then runs without extra nesting.

**Null-safe string comparison.** `"checking".equalsIgnoreCase(accountType)` puts the literal first, so a `null` account type returns `false` instead of throwing a `NullPointerException`. `equalsIgnoreCase` also makes the account type case-insensitive.

**Derived values.** `getCombinedBalance()` calculates checking plus savings on demand instead of storing a third field that could go out of sync.

**Random number generation.** `RANDOM.nextLong(9_000_000_000L)` returns a value from 0 to 8,999,999,999, and adding `1_000_000_000L` shifts it into the ten-digit range (1,000,000,000 to 9,999,999,999). A single shared `Random` instance is reused instead of creating a new one per account.

**Formatted output.** `printf` width specifiers (`%12d`, `%10s`) and `%.2f` keep the account box aligned and every amount at two decimal places.

## Expected Output

Account numbers are random, so yours will differ from the ones below.

```text
==== BankAccount - DEPOSIT ======
 > You deposited $1280.50 to your checking account.
============= EXIT ==============

==== BankAccount - DEPOSIT ======
 > You deposited $320.00 to your savings account.
============= EXIT ==============

==== BankAccount - DEPOSIT ======
 > You deposited $3000.00 to your checking account.
============= EXIT ==============

=== BankAccount - WITHDRAWAL ====
 > You withdrew $450.00 from your checking account.
============= EXIT ==============

=== BankAccount - WITHDRAWAL ====
 > You withdrew $25.00 from your savings account.
============= EXIT ==============

=== BankAccount - WITHDRAWAL ====
 > ERROR: Insufficient funds!
============= EXIT ==============


--- Getter Check (Account 1) ---
Account Number: 4127398406
Checking Balance: $830.50
Savings Balance: $295.00
Combined Balance: $1125.50
Account number is 10 digits: true
Account numbers are different: true

--- Edge: withdraw more than the checking balance ---
=== BankAccount - WITHDRAWAL ====
 > ERROR: Insufficient funds!
============= EXIT ==============

--- Edge: withdraw the exact savings balance ---
==== BankAccount - DEPOSIT ======
 > You deposited $250.75 to your savings account.
============= EXIT ==============

=== BankAccount - WITHDRAWAL ====
 > You withdrew $250.75 from your savings account.
============= EXIT ==============

--- Edge: negative deposit ---
==== BankAccount - DEPOSIT ======
 > ERROR: Amount must be greater than $0.00!
============= EXIT ==============

--- Edge: zero withdrawal ---
=== BankAccount - WITHDRAWAL ====
 > ERROR: Amount must be greater than $0.00!
============= EXIT ==============

--- Edge: account type is not case-sensitive ---
==== BankAccount - DEPOSIT ======
 > You deposited $100.00 to your CHECKING account.
============= EXIT ==============

--- Edge: invalid account type ---
==== BankAccount - DEPOSIT ======
 > ERROR: Account Type must be 'checking' or 'savings'!
============= EXIT ==============

=== BankAccount - WITHDRAWAL ====
 > ERROR: Account Type must be 'checking' or 'savings'!
============= EXIT ==============


--- Account 1 Status ---
+------------------------------+
| Account Number:   4127398406 |
+------------------------------+
| Checking Balance:   $ 830.50 |
| Savings Balance:    $ 295.00 |
| Combined Balance:  $ 1125.50 |
+------------------------------+

--- Account 2 Status ---
+------------------------------+
| Account Number:   1288889823 |
+------------------------------+
| Checking Balance:  $ 3100.00 |
| Savings Balance:      $ 0.00 |
| Combined Balance:  $ 3100.00 |
+------------------------------+

=== STATICS ===
Total Accounts Created: 2
Total Money Across All Accounts: $4225.50
Sum of Individual Balances: $4225.50
Tracker matches balances: true
```

**What each case demonstrates:**

| Case | Demonstrates |
|---|---|
| Deposits to checking and savings (accounts 1 and 2) | `deposit` updates the correct balance and the static total |
| Withdrawals from checking and savings (account 1) | `withdraw` subtracts from the correct balance and the static total |
| Withdraw $600 from empty savings (account 2) | The insufficient-funds check blocks a withdrawal from a $0 balance |
| Getter check | All getters return correct values, and account numbers are ten digits and unique |
| Withdraw more than the checking balance | Insufficient funds when the account has some money, but not enough |
| Withdraw the exact savings balance | A withdrawal equal to the balance is allowed, and the balance reaches $0.00 |
| Negative deposit | Amounts of $0 or less are rejected, so a deposit can't drain an account |
| Zero withdrawal | The same amount rule applies to withdrawals |
| `"CHECKING"` deposit | Account types are not case-sensitive |
| `"retirement"` deposit and withdrawal | Unrecognized account types are rejected for both actions |
| Account summaries | `displayAccountBalance` shows the account number and all three balances |
| Statics | Account count is 2, and the total tracker matches the sum of both accounts' balances |

## Steps to Run

From this module's directory:

```bash
mvn compile exec:java -Dexec.mainClass="com.learn.bankaccount.TestBankAccount"
```