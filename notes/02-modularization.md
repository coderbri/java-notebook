# Modularization

Reference notes from Intro to Java lecture on splitting a class into
reusable methods and driving it from a separate controller/main class —
the same pattern used later in `AlfredQuotes`/`AlfredTest`.

## Why split into two files?
`FirstJavaClass` holds the actual logic as methods. `FirstJavaController`
holds only `main`, creates an instance of `FirstJavaClass`, and calls its
methods. This separates "what the program can do" from "the entry point
that runs it" — the same reasoning behind `AlfredQuotes` vs `AlfredTest`.

```java
public class FirstJavaController {
    public static void main(String[] args) {
        FirstJavaClass fClass = new FirstJavaClass();
        fClass.d1Code();
        System.out.println(fClass.number);
    }
}
```
- `new FirstJavaClass()` creates an **instance** of the class
- `fClass.d1Code()` calls a method *on* that instance — this only works because `d1Code()` is a non-static (instance) method
- `fClass.number` accesses a field on the class. Note: `number` is declared `static`, meaning it technically belongs to the class itself, not to any one instance — `FirstJavaClass.number` would also work here. Accessing a static field through an instance (`fClass.number`) is legal but can be misleading, since it looks like an instance field.

## Conditionals
```java
void conditionals() {
    int number = 7;

    if (number > 10) {
        System.out.println("Greater than 10");
    } else if (number == 7) {
        System.out.println("It's exactly 7");
    } else {
        System.out.println("Something else");
    }
}
```
- `if` runs its block only when the condition is `true`
- `else if` checks another condition only if the previous one(s) failed
- `else` catches everything not matched above — optional, and there can be any number of `else if` blocks between `if` and the final `else`

### Comparing `double` values
```java
void conditionals() {
    double double1 = 123d;
    double double2 = 456d;

    if (Double.compare(double1, double2) == 0) {
        System.out.println("numbers are equal");
    } else if (Double.compare(double1, double2) > 0) {
        System.out.println("double1 is greater than double2");
    } else {
        System.out.println("double2 is greater than double1");
    }
}
```
`Double.compare(a, b)` returns `0` if equal, a positive number if `a > b`,
and a negative number if `a < b`. It's generally safer than `==`/`>`/`<`
directly on `double`s, since floating-point rounding can make two
"equal" values fail a direct `==` check. The `d` suffix on `123d` marks
the literal explicitly as a `double`.

## Switch
```java
void switches() {
    int day = 3;

    switch (day) {
        case 1:
            System.out.println("Monday");
            break;
        case 2:
            System.out.println("Tuesday");
            break;
        case 3:
            System.out.println("Wednesday");
            break;
        default:
            System.out.println("Some other day");
            break;
    }
}
```
- `switch(value)` checks `value` against each `case`
- `break` exits the switch after a matching case runs — **omitting it lets execution "fall through" into the next case**, which is rarely what you want
- `default` runs when no `case` matches — equivalent to a final `else`

### Switching on a `String`
```java
void switches() {
    String city = "Manhattan";

    switch (city) {
        case "Seattle":
            System.out.println("Seattle");
            break;
        case "San Francisco":
            System.out.println("San Francisco");
            break;
        case "Chicago":
            System.out.println("Chicago");
            break;
        case "Manhattan":
            System.out.println("Manhattan");
            break;
        default:
            System.out.println("City not found...");
    }
}
```
`switch` isn't limited to numbers — it also works on `String` (and a few
other types). Each `case` is matched against the string's actual content
(effectively using `.equals()` under the hood), not object identity.
Note: `default` doesn't strictly need a trailing `break` if it's the last
branch in the switch, though including one is harmless and consistent.

## Ternary
```java
void ternary() {
    int number = 7;
    String result = (number % 2 == 0) ? "even" : "odd";
    System.out.println(result);
}
```
A compact one-line `if/else` for simple cases. Three parts, in order:
1. **condition** — `number % 2 == 0`
2. **value if true** — `"even"`
3. **value if false** — `"odd"`

Best for short, simple decisions — an `if/else` is usually clearer once
the logic gets more involved.

## Strings
```java
String name = "Bri";
int length = name.length();          // 3
char first = name.charAt(0);         // 'B'
boolean same = name.equals("Bri");   // true — use .equals(), not == , to compare String content
String upper = name.toUpperCase();   // "BRI"
```
`==` compares whether two variables point to the *same object in memory*,
not whether their contents match — this is a common early gotcha with
Strings specifically. Always use `.equals()` to compare what two strings
actually say.

### `String.format()` specifiers
```java
int balance = 123;
double usBalance = 456.789;
double krBalance = 789.0123;

String nameInput = "Bri";
System.out.println(String.format("Hi, %s. Your balance is $%d.", nameInput, balance));
System.out.println(String.format("Hi, %s. Your balance is $%.2f.", nameInput, usBalance));
System.out.println(String.format("Hi, %s. Your balance is $%.3f.", nameInput, krBalance));
```
- `%s` → any object (calls `.toString()`)
- `%d` → integer types only — passing a `double` here throws an error
- `%.2f` → floating-point, rounded to 2 decimal places (`%.3f` for 3, etc.) — the number after the `.` controls precision

### Reading input with `System.console()`
The original lecture example read user input with:
```java
String nameInput = System.console().readLine();
```
This works when run from an actual terminal, but returns `null` (and
throws a `NullPointerException` on `.readLine()`) when run through most
IDE consoles, including IntelliJ's Run window. In this repo's modules,
`Scanner(System.in)` is used instead specifically because it works
consistently in both environments — see `03-alfred-bot` for the pattern.