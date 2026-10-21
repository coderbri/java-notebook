# 01 — Java Fundamentals

The first phase of [Java Notebook](../README.md): eight projects that take a program from "Hello, World" to a complete interactive game. Each project is a self-contained Maven module with its own README covering the overview, logic flow, and concepts, and later projects deliberately reuse and extend earlier ideas.

## Projects

| # | Project | What it does | Key concepts |
|---|---|---|---|
| 01 | [First Java](./01-first-java) | Prints a greeting built from variables. | Variables and data types, `println` vs. `printf`, string formatting |
| 02 | [Café Java](./02-cafe-java) | A first café utility class with methods for rewards, order totals, menus, and customers. | Methods, loops, arrays, `ArrayList`, console input |
| 03 | [Alfred Bot](./03-alfred-bot) | A virtual assistant that greets guests, announces the date, and responds to conversation. | Method overloading, encapsulation, `switch`, date and time classes, `Scanner` |
| 04 | [Café Business Logic](./04-cafe-business-logic) | An expanded café library: reward streaks, order totals, menus with prices, a bulk discount chart, and a waitlist. | Overloading, array vs. `ArrayList` iteration, early returns, accumulators, repeated input |
| 05 | [Puzzling](./05-puzzling) | Random dice rolls, letters, passwords, and array shuffling. | `Random`, `static final` constants, `StringBuilder`, Fisher-Yates shuffle |
| 06 | [Map of Hashmatique](./06-map-of-hashmatique) | A track list stored in a `HashMap`. | Key-value storage, `put` / `get`, `entrySet()`, unordered iteration |
| 07 | [List of Exceptions](./07-list-of-exceptions) | Safely casts values from a list of mixed types. | Autoboxing, casting vs. parsing, `instanceof`, `ClassCastException` |
| 08 | [Numbers Game](./08-numbers-game) | A guessing game with input validation, three guesses, and replay. | `Scanner`, constants, `try/catch` validation, guard-first conditionals, method decomposition |

## How the projects build on each other
- **01–02:** syntax, variables, and the first methods and collections.
- **03–04:** organizing code into overloaded methods, using the pattern of a logic class plus a test driver.
- **05–06:** working with randomness and key-value data.
- **07–08:** handling bad data through casting checks, input validation, and exceptions, finishing with a game that pulls together loops, conditionals, methods, and input handling.

## Reference notes
Plain markdown syntax references from lecture material and project lessons, kept in [`notes/`](../notes):

- [Variables & Data Types](../notes/01-variables-and-datatypes.md): declaring variables, primitives, wrapper classes
- [Modularization](../notes/02-modularization.md): splitting logic from the entry point, conditionals, `switch`, ternaries, strings
- [Loops & Data Structures](../notes/03-loops-and-data-structures.md): loops, arrays, `ArrayList`, `HashMap`
- [Exceptions](../notes/04-exceptions.md): `try/catch`, checked vs. unchecked exceptions, `try/catch` vs. `instanceof`
- [Input & Control Flow](../notes/05-input-and-control-flow.md): `Scanner`, constants, helper methods, ordering conditionals

## Running a project

```bash
cd 01-java-fundamentals/<project-folder>
mvn compile exec:java -Dexec.mainClass="<package>.<MainClass>"
```

Each project's README lists its exact command.

## Up next
Phase 02 moves into **Object-Oriented Programming**: classes, objects, and the core OOP principles.