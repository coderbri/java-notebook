# 10 - Barista's Challenge

## Overview

This challenge builds on the `Item` and `Order` classes from Challenge 9 and moves the café's business logic *inside* the classes themselves. Member variables are now private, objects are created through constructors, and each `Order` has methods that work on its own data (adding items, reporting status, calculating the total, and printing a receipt). `TestOrders` is now only a thin controller that creates objects and calls those methods.

**Package:** `com.learn.baristachallenge`

## Table of Contents

- [Logic Flow](#logic-flow)
- [Concepts Implemented](#concepts-implemented)
- [Expected Output](#expected-output)
- [Steps to Run](#steps-to-run)

## Logic Flow

### `Item`
1. An `Item` is created with a name and a price through its constructor.
2. Other classes read or change those values only through getters and setters.

### `Order`
1. **Creation:** `new Order()` sets the customer to `"Guest"`, while `new Order("Jimmy")` uses the given name. Both start with an empty `ArrayList<Item>`.
2. **`addItem(Item)`:** appends the item to this order's list.
3. **`getStatusMessage()`:** returns a "thank you for waiting" message while the order is pending, or "your order is ready" once `setReady(true)` has been called.
4. **`getOrderTotal()`:** loops through the items, adds up each `getPrice()`, and returns the sum. The total is calculated on demand instead of being stored.
5. **`displayReceipt()`:** prints the customer name and each item with its price. If the order has no items, it prints an error line instead of a total.

### `TestOrders`
1. Creates four menu items and four orders (one guest, three named).
2. For each order, it adds items, prints the pending status, marks the order ready, prints the ready status, and prints the receipt.
3. Order 3 is intentionally left empty to test the empty-order receipt.

## Concepts Implemented

- **Encapsulation.** The `name` and `price` fields in `Item`, and the `customerName`, `isReady`, and `orderedItems` fields in `Order`, are all private. Outside code, like `TestOrders`, can't touch them directly and has to go through getters and setters. This is how `getOrderTotal()` reads prices with `item.getPrice()` instead of accessing a field.

- **Constructors and overloading.** `Order` has two constructors with the same name and different parameters. The no-argument version defaults the customer to `"Guest"`, and the overloaded version accepts a name. Both initialize `orderedItems` so `addItem` never hits a `null` list.

- **Instance methods that act on their own data.** `addItem`, `getStatusMessage`, `getOrderTotal`, and `displayReceipt` don't take the customer or item list as arguments. Each one uses the data stored in the specific order it was called on, so `order1.displayReceipt()` and `order4.displayReceipt()` print different receipts from the same code.

- **Derived values instead of stored state.** The old `total` field is gone. `getOrderTotal()` recalculates the sum every time, so the total can't go stale if items are added later.

- **Boolean accessor naming.** The `isReady` flag uses `isReady()` as its getter and `setReady(boolean)` as its setter, following the Java convention for booleans.

- **Conditional logic.** `getStatusMessage()` branches on `isReady`, and `displayReceipt()` branches on `orderedItems.isEmpty()` to handle the edge case of an order with nothing in it.

- **Formatted output.** `printf` with width specifiers (`%-11s`, `%.2f`) keeps the receipt columns aligned.

## Expected Output

```
========= Café Java ==========

+~~~~~~~~~ ORDER 1 ~~~~~~~~~~+
Thank you for waiting, Guest. Your order will be ready shortly.
Guest, your order is ready.

+-----------------------+
|        Receipt        |
| --------------------- |
| Customer: Guest       |
| Cappuccino  -- $4.36  |
| Mocha       -- $3.50  |
| --------------------- |
|         Total: $7.86  |
+-----------------------+

+~~~~~~~~~ ORDER 2 ~~~~~~~~~~+
Thank you for waiting, Jimmy. Your order will be ready shortly.
Jimmy, your order is ready.

+-----------------------+
|        Receipt        |
| --------------------- |
| Customer: Jimmy       |
| Drip Coffee -- $2.15  |
| --------------------- |
|         Total: $2.15  |
+-----------------------+

+~~~~~~~~~ ORDER 3 ~~~~~~~~~~+
Thank you for waiting, Noah. Your order will be ready shortly.
Noah, your order is ready.

+-----------------------+
|        Receipt        |
| --------------------- |
| Customer: Noah        |
|    Error: No items    |
+-----------------------+

+~~~~~~~~~ ORDER 4 ~~~~~~~~~~+
Cindhuri, your order is ready.

+-----------------------+
|        Receipt        |
| --------------------- |
| Customer: Cindhuri    |
| Latte       -- $4.16  |
| Latte       -- $4.16  |
| --------------------- |
|         Total: $8.32  |
+-----------------------+
```

**What each case demonstrates:**
- **Order 1:** the no-argument constructor (`"Guest"`) with multiple items.
- **Order 2:** a named order with a single item.
- **Order 3:** an empty order, which shows the error line instead of a total.
- **Order 4:** the same item added twice, with the total summed correctly.

## Steps to Run

From this module's directory:

```bash
mvn compile exec:java -Dexec.mainClass="com.learn.baristachallenge.TestOrders"
```