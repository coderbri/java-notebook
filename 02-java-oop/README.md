# Phase 02 • Java OOP 

The second phase of [Java Notebook](../README.md): projects that move from plain methods and collections to **object-oriented programming**. Each project is a self-contained Maven module with its own README covering the overview, logic flow, and concepts, and each one deliberately reuses and extends the ideas before it.

> **Status:** 🚧 In progress

---

## Projects

| #  | Project                                       | What it does                                                                                                                  | Key concepts                                                                              |
|----|-----------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------|
| 09 | [Orders and Items](./09-orders-and-items)     | An object-oriented rebuild of the café idea: `Item` and `Order` classes exercised by a `TestOrders` driver.                   | Classes and objects, instance fields, the test-driver pattern                             |
| 10 | [Barista's Challenge](./10-baristas-challenge) | A refactor of Orders and Items into a properly encapsulated model, with order totals, status messages, and a printed receipt. | Private fields, constructors, constructor overloading, getters/setters, calculated values |
| 11 | [Bank Account](./11-bankaccount)              | A `BankAccount` with checking and savings balances, validated deposits and withdrawals, and class-wide trackers.              | `final` fields, `static` members, validation, helper methods, edge-case testing           |
| 12 | [Coffeedore 64](#)                            | (details coming soon)                                                                                                         | (details coming soon)                                                                     |

*(This table grows as new projects are added.)*

## How the projects build on each other
- **09:** modeling real things as classes and creating objects from them.
- **10:** refactoring the same classes to hide their data behind private fields, constructors, and getters/setters, and replacing a stored `total` with a calculated `getOrderTotal()`.
- **11:** starting from encapsulation and adding class-level state (`static` trackers), a `final` account number, and defensive validation for every transaction.

## Reference notes
Plain markdown syntax references from lecture material and project lessons, kept in [`notes/`](../notes):

- [Encapsulation & Access Modifiers](../notes/06-encapsulation-and-access-modifiers.md): when to use `private` vs. `public` for fields, static members, and methods

## Running a project

```bash
cd 02-java-oop/<project-folder>
mvn compile exec:java -Dexec.mainClass="<package>.<MainClass>"
```

Each project's README lists its exact command.

## Up next
More OOP projects will be added to this phase. In the meantime, [Phase 03](../03-java-spring) has begun with **Spring Boot**: building web apps that respond to requests in the browser.

---

[← Back to the Java Notebook](../README.md)