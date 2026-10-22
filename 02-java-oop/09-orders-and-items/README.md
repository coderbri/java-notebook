# 09 — Orders & Items

The first project of Phase 02 and an object-oriented rebuild of the café idea from [04 — Café Business Logic](../../01-java-fundamentals/04-cafe-business-logic). Instead of parallel lists of names and prices, the café is modeled with objects: an `Item` for each menu item and an `Order` for each customer. The project has three classes in the package `com.learn.ordersitems`: `Item`, `Order`, and the `TestOrder` driver that simulates four customers.

Run it from this module's directory:

```bash
mvn compile exec:java -Dexec.mainClass="com.learn.ordersitems.TestOrder"
```

## Logic Flow

### The classes

| Class | Member variables | Methods |
|---|---|---|
| `Item` | `name` (`String`), `price` (`double`), both private | Constructor `Item(name, price)`, plus `getName()`, `setName()`, `getPrice()`, `setPrice()` |
| `Order` | `customerName` (`String`), `total` (`double`), `isReady` (`boolean`), `orderedItems` (`ArrayList<Item>`) | Constructor `Order(customerName)`, `calculateTotal()`, `markReady()`, `displayStatus(...)` |
| `TestOrder` | none | `main`, which runs the simulation |

A new `Order` starts with a total of `0.0`, no items, and a status of not ready.

### The simulation
1. Define the message strings that the status display uses.
2. Create four `Item` objects (`dripCoffee`, `mocha`, `latte`, `cappuccino`), add them to a `menu` list, and print the menu table.
3. Create four `Order` objects, one per customer.
4. Walk each order through its scenario:
    - **Cindhuri** orders a mocha and is shown as pending. She is then marked ready, and her total of $3.50 appears.
    - **Jimmy** orders a drip coffee and is marked ready, with a total of $2.15.
    - **Noah** orders a cappuccino and stays pending.
    - **Sam** orders a latte and is shown as pending. He then adds two more lattes, the total is recalculated to $12.48, and the order is marked ready.

### How the total is kept current
`calculateTotal()` sets the total back to zero and then adds the price of every item in the order. Because it recalculates from scratch, it can be called any time after items are added without double counting.

## Concepts Implemented

- **Classes and objects.** A class is a blueprint, and `new` builds an object from it. Each `Order` object holds its own customer name, items, total, and status, so `order1` and `order2` never interfere with each other.

- **Constructors and `this`.** `Item(String name, double price)` and `Order(String customerName)` set up an object's starting state. Inside them, `this.customerName = customerName` separates the object's field from the parameter with the same name.

- **Encapsulation with getters and setters.** `Item` keeps its fields private and exposes them through `getName()`, `getPrice()`, and their setters, so other classes can't change an item's data directly. In this version, `Order`'s member variables are public, so `TestOrder` reads and updates them directly, for example `order1.orderedItems.add(mocha)`.

- **Composition.** An `Order` contains a list of `Item` objects. Building one object out of other objects ("an order has items") is how larger models are assembled in OOP.

- **Behavior that works on object state.** Methods on `Order` act on the object's own data: `calculateTotal()` sums its items, `markReady()` flips its status, and `displayStatus(...)` branches on that status so the display logic lives with the order rather than in `main`.

- **Collections of objects.** `ArrayList<Item>` stores custom objects instead of plain strings, and a for-each loop (`for (Item item : orderedItems)`) walks them, calling `getPrice()` on each.

- **Formatted output.** `printf` with `%-14s` and `%.2f` lines the menu up in columns and prints prices to two decimal places.