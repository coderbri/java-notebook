# 07 — List of Exceptions

A single-file project that shows how to safely cast values from an `ArrayList` holding mixed types (strings, characters, and integers). Instead of letting a bad cast throw a `ClassCastException`, the program checks each value's type with `instanceof` before casting, so it never crashes and always runs to completion.

## Table of Contents
- [Overview](#overview)
- [Logic Flow](#logic-flow)
  - [1. Building the Mixed List](#1-building-the-mixed-list)
  - [2. Retrieving Each Value](#2-retrieving-each-value)
  - [3. Checking Before Casting](#3-checking-before-casting)
  - [4. Finishing the Loop](#4-finishing-the-loop)
- [Concepts Implemented](#concepts-implemented)
- [Expected Output](#expected-output)
- [Steps to Run](#steps-to-run)

## Overview
An `ArrayList<Object>` can hold any type of value, which is flexible but means Java can't guarantee what comes out. Pulling a value out and casting it to the wrong type throws a `ClassCastException`. This project loops through a list of four mixed values and attempts to treat each one as an `Integer`, using a type check so only genuine integers are cast and everything else is skipped with a message. Everything lives in one file, `ExceptionsList.java`.

## Logic Flow

### 1. Building the Mixed List
```java
ArrayList<Object> myList = new ArrayList<>();
myList.add("Hello, World");
myList.add('z');
myList.add(4);
myList.add("13");
```
Each value is wrapped in an object when added (autoboxing), so the list actually holds a `String`, a `Character`, an `Integer`, and another `String`.

### 2. Retrieving Each Value
The loop walks the list by index and stores each element in an `Object` variable. `Object` is the parent of every class, so any element fits without a cast.

### 3. Checking Before Casting
```java
if (obj instanceof Integer) {
    Integer castedValue = (Integer) obj;
    // print success
} else {
    // print skip message
}
```
`instanceof` asks whether the object really is an `Integer`. If it is, the cast is safe and the program prints the value. If not, the program prints a skip message with the offending value and moves on.

### 4. Finishing the Loop
After the loop, `Finished!` prints. Reaching that line shows every index was handled and nothing crashed.

## Concepts Implemented

**Heterogeneous collections.** Declaring the list as `ArrayList<Object>` lets it hold unrelated types. The trade-off is that type safety moves from compile time to runtime: Java can no longer promise what type an element is, so the code has to check.

**Autoboxing.** Primitive values are wrapped into their object counterparts when added to a collection, so `'z'` becomes a `Character` and `4` becomes an `Integer`. This is why the types matter even though `'z'` and `4` are both "numbers" at the primitive level.

**Casting is not converting.** A cast only tells Java to treat an existing object as a different type, and it fails if the object isn't actually that type. `"13"` looks like a number but is a `String`, and `'z'` is a `Character`, so neither can be cast to `Integer`. Turning the text `"13"` into the number `13` requires parsing with `Integer.parseInt`, which is a different operation.

**`instanceof` for defensive type-checking.** Checking the type first means the invalid cast never happens, so no exception is thrown at all. That suits this project because a wrong type is an expected situation, not a surprise.

**Why `instanceof` instead of `try/catch`.** `ClassCastException` can also be caught with a `try/catch` block, and the notes in [`notes/04-exceptions.md`](../../notes/04-exceptions.md) walk through that version side by side. When a failure is predictable and cheap to test for ahead of time, checking first is cleaner and avoids the cost of creating an exception. `try/catch` is the better fit when failure can't be checked in advance, such as reading files or parsing user input.

## Expected Output

```
 >> Skipped index 0: Value 'Hello, World' is not an Integer.
 >> Skipped index 1: Value 'z' is not an Integer.
 > Successfully casted index 2: 4
 >> Skipped index 3: Value '13' is not an Integer.
Finished!
```

## Steps to Run

From this module's directory (`01-java-fundamentals/07-list-of-exceptions`):

```bash
mvn compile exec:java -Dexec.mainClass="com.learn.exceptionslist.ExceptionsList"
```

No input is required.