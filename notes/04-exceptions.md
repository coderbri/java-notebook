# Exceptions & try/catch

Reference notes on handling exceptions in Java, built around the `07-list-of-exceptions` challenge: casting values from an `ArrayList<Object>` that mixes strings, characters, and integers.

## What an exception is
An exception is an object Java creates when something goes wrong while the program runs. If nothing handles it, the program stops and prints a stack trace. Handling it lets the program recover and keep going.

## try / catch
```java
ArrayList<Object> myList = new ArrayList<>();
myList.add("Hello, World");
myList.add('z');
myList.add(4);
myList.add("13");

for (int i = 0; i < myList.size(); i++) {
    try {
        Integer castedValue = (Integer) myList.get(i);
        System.out.println(" > Successfully casted index " + i + ": " + castedValue);
    } catch (ClassCastException e) {
        System.out.println(" >> Error at index " + i + ": " + e.getMessage());
    }
}
```
How it runs:
1. Java runs the `try` block line by line.
2. If a line throws an exception, the rest of the `try` block is skipped and control jumps to the matching `catch`.
3. After the `catch` finishes, the program continues normally after the whole `try/catch`, so the loop keeps going.

If nothing is thrown, the `catch` block is simply skipped.

## What happens at each index
| Index | Value | Stored as | Cast to `Integer` |
|---|---|---|---|
| 0 | `"Hello, World"` | `String` | throws `ClassCastException` |
| 1 | `'z'` | `Character` | throws `ClassCastException` |
| 2 | `4` | `Integer` | succeeds |
| 3 | `"13"` | `String` | throws `ClassCastException` |

`e.getMessage()` returns a description of the failure. It typically reads like `class java.lang.String cannot be cast to class java.lang.Integer`, followed by extra detail about the module and loader. Printing `e` itself includes the exception's class name as well.

## Cast vs. parse
A cast changes how Java *treats* an object and fails if the object isn't really that type, so `"13"` can't be cast to `Integer`. To turn text into a number, parse it. Parsing is also a classic place where `try/catch` is the right tool:
```java
String text = "13";
try {
    int number = Integer.parseInt(text);
    System.out.println(number + 1);
} catch (NumberFormatException e) {
    System.out.println("Not a number: " + text);
}
```
Here the program can't know ahead of time whether the text is numeric (it may come from user input), so catching the failure is the practical approach.

## Checked vs. unchecked exceptions
- **Unchecked** exceptions extend `RuntimeException` (`ClassCastException`, `NumberFormatException`, `NullPointerException`). The compiler doesn't force you to handle them.
- **Checked** exceptions (such as `IOException`) must be caught or declared with `throws`, or the code won't compile.

## try / catch / finally syntax
```java
try {
    // code that might throw
} catch (NumberFormatException | NullPointerException e) {
    // multiple types can share one catch
} catch (Exception e) {
    // broader types go last
} finally {
    // runs whether or not an exception was thrown
}
```
- List specific exceptions before general ones, since Java uses the first matching `catch`.
- Catching a bare `Exception` also swallows bugs you didn't anticipate, so prefer the specific type.
- `finally` runs either way, which makes it the place for cleanup such as closing a resource.

## try/catch vs. instanceof
The same challenge can avoid the exception completely by checking the type first:
```java
if (obj instanceof Integer) {
    Integer castedValue = (Integer) obj;
}
```
| | `instanceof` check | `try/catch` |
|---|---|---|
| Best when | A wrong type is expected and easy to test for | Failure can't be predicted or checked in advance |
| Control flow | Ordinary `if/else` | Jumps to the `catch` block |
| Cost | A cheap type test | Creating an exception captures a stack trace, which is slower |
| Examples | Mixed-type lists, optional values | Parsing input, file and network access |

A common rule of thumb: don't use exceptions for ordinary control flow. For this challenge, where wrong types are built into the data, `instanceof` is the cleaner tool, which is why the module uses it.

> **In practice:** `07-list-of-exceptions` casts a mixed `ArrayList<Object>` using `instanceof` so no `ClassCastException` is ever thrown.