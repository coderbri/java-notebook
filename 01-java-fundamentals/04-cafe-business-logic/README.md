# 04 — Café Business Logic

A utility library for managing café operations — customer reward streaks, order totals, menu display (with and without pricing), bulk-quantity discount charts, and an interactive customer waitlist. Builds on `02-cafe-java` using more advanced syntax: method overloading, `ArrayList`/array iteration, `switch` statements, and loop-driven console input.

## Table of Contents
- [Overview](#overview)
- [Logic Flow](#logic-flow)
    - [Customer Reward Streak](#customer-reward-streak)
    - [Order Total](#order-total)
    - [Menu Display](#menu-display)
    - [Price Chart](#price-chart)
    - [Customer Waitlist](#customer-waitlist)
- [Concepts Implemented](#concepts-implemented)
- [Expected Output](#expected-output)
- [Steps to Run](#steps-to-run)

## Overview
`CafeUtil.java` holds all business logic as public methods; `TestCafe.java` is the driver, creating one `CafeUtil` instance and exercising each feature in sequence through five labeled test sections in `main`. Two methods (`getStreakGoal` and `displayMenu`) are overloaded — a simpler default version plus a more flexible version that takes extra parameters.

## Logic Flow

### Customer Reward Streak
`getStreakGoal()` sums every integer from `1` to `10` to determine purchases needed for a 10-week streak. The overloaded `getStreakGoal(int numWeeks)` generalizes this to any duration, letting an admin adjust the streak length without touching the default method.

### Order Total
`getOrderTotal(double[] prices)` iterates a fixed array of item prices and accumulates them into a single running total.

### Menu Display
`displayMenu(ArrayList<String> menuItems)` prints a simple indexed menu table. The overloaded `displayMenu(ArrayList<String>, ArrayList<Double>)` prints items alongside their prices, first validating both lists are the same size — returning `false` immediately on mismatch (before any printing), or `true` after successfully rendering the table.

### Price Chart
`printPriceChart(String itemName, double price, int maxQuantity)` prints pricing for quantities `1` through `maxQuantity`, applying an increasing $0.50-per-unit discount as quantity grows — each iteration reduces a running `discount` accumulator that's added into that row's total.

### Customer Waitlist
`addCustomers(ArrayList<String> customers)` loops indefinitely, reading a name via `Scanner` on each pass. Typing `"q"` (case-insensitive) exits the loop; any other input adds the customer to the list, greets them, reports how many people are ahead of them, and reprints the full waitlist table.

## Concepts Implemented

**Method overloading.** `getStreakGoal()` and `displayMenu()` each have two versions with different parameter lists — Java selects which one runs based on what's passed at the call site. Note the `displayMenu` overload also differs in return type (`void` vs `boolean`), since only the two-argument version has a real failure case (mismatched list sizes) worth reporting back to the caller.

**Array vs. ArrayList iteration.** `getOrderTotal` iterates a fixed-size `double[]` with `.length`; menu/waitlist methods iterate `ArrayList`s with `.size()` and `.get(index)` — same indexed-loop shape, different underlying structure.

**Early return for validation.** The two-list `displayMenu` overload checks list sizes *before* printing anything, returning `false` immediately on mismatch rather than printing a partial or broken table.

**`switch` for a small fixed set of cases.** The waitlist greeting varies by position (1st, 2nd, everyone else) using a `switch` on `position`, cleaner here than a chain of `if/else`.

**Accumulator pattern.** Both `getOrderTotal` (summing prices) and `printPriceChart` (a running discount that grows more negative each pass) use a variable that updates across loop iterations to build up a result.

**Reading repeated input with `Scanner`.** Unlike earlier projects that read input once, `addCustomers` reads in a loop until a sentinel value (`"q"`) is entered — `System.console()` was avoided here too, since it returns `null` outside a real terminal (breaks in IntelliJ).

## Expected Output

```
+------------------------+
|       Café Java        |
+------------------------+

----- Streak Goal Test -------
Purchases needed by week 10: 55
Purchases needed by week 15: 120

----- Order Total Test -------
Total order prices: $14.17

----- Display Menu Test ------
+--------------------+
|  Café Java - Menu  |
+--------------------+
| No. |    Coffees   |
+-----+--------------+
|  0  | drip coffee  |
|  1  | cappuccino   |
|  2  | latte        |
|  3  | mocha        |
+--------------------+
+---------------------------+
|      Café Java - Menu     |
+---------------------------+
| No. |   Coffees   | Price |
+-----+-------------+-------+
|  0  | drip coffee | $2.15 |
|  1  | cappuccino  | $4.36 |
|  2  | latte       | $4.16 |
|  3  | mocha       | $3.50 |
+---------------------------+

----- Price Chart for Menu Items ------
+----------------------------+
|  Columbian Coffee Grounds  |
+----------------------------+
|  Qty. |    Price ($USD)    |
+-------+--------------------+
|  1    |     $15.00         |
|  2    |     $29.50         |
|  3    |     $44.00         |
+----------------------------+

------- Customer Waitlist Test --------
Please enter your name (type 'q' to quit): Bri
Hello, Bri! Your order will be ready shortly.
+--------------------------+
|   Café Java - Waitlist   |
+--------------------------+
|  Place  |    Customer    |
+---------+----------------+
|   1     |  Bri           |
Please enter your name (type 'q' to quit): q
 > Exiting Waitlist...
```
*(Waitlist output depends on live console input; shown here for one entered name.)*

## Steps to Run

From this module's directory (`01-java-fundamentals/04-cafe-business-logic`):

```bash
mvn compile exec:java -Dexec.mainClass="com.learn.cafebizlogic.TestCafe"
```

Follow the prompt to add customers to the waitlist; type `q` to exit.

---
<section align="center">
  <code>coderBri © 2026</code>
</section>