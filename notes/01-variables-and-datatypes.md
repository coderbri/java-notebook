# Variables & Data Types

Reference notes from Intro to Java lecture, covering the entry point method,
variable declaration, primitives vs. wrapper classes, and compiling/running
from the terminal.

## Entry Point Method
```java
public class FirstJava {
    public static void main( String[] args ) {
        // program starts here
    }
}
```
Every standalone Java program needs a `main` method with this exact
signature — `public static void main(String[] args)`. It's the method the
JVM looks for and calls first.

## Printing to Console
```java
System.out.println("Hello World");
```
`println` prints and adds a newline automatically.

## Declaring Variables
Pattern: `dataType variableName = value;`

```java
String firstName = "Jane"; // double quotes for strings
char letter = 'a';         // single quotes for char
int favoriteNumber;        // can declare without a value...
favoriteNumber = 7;        // ...and assign separately
boolean isCoder = true;
```
- Variable names use camelCase
- `String` uses double quotes; `char` uses single quotes (and holds exactly one character)
- A variable can be declared and assigned on separate lines

## String Formatting
```java
System.out.println("Hello! My name is " + firstName + ".");
System.out.printf("%s's favorite number is %d\n", firstName, favoriteNumber);
```
- `+` concatenates strings directly
- `printf` substitutes values into `%` placeholders — `%s` for strings/objects, `%d` for integers — and needs an explicit `\n` for a newline (unlike `println`)

## Primitives vs. Wrapper Classes
| Primitive | Wrapper   | Notes                                      |
|-----------|-----------|---------------------------------------------|
| `int`     | `Integer` | fixed range — see long/int gotcha below     |
| `long`    | `Long`    | use for values too large for `int`          |
| `double`  | `Double`  | preferred over `float`, which is deprecated |
| `boolean` | `Boolean` |                                              |
| `char`    | `Character` |                                           |

```java
long population = 87654321;
double balance = 25.00;

Integer myCatsAge = 3;
Long bigNumber;
Double decimalNumber = 7.58;
Integer result = myCatsAge / favoriteNumber; // wrapper classes support arithmetic like primitives
```
Wrapper classes (`Integer`, `Long`, `Double`, etc.) behave like their
primitive counterparts for basic operations (e.g. division), but are
objects — useful later for things like collections (`List<Integer>`),
which can't hold raw primitives.

## Gotcha: `int` vs `long` literals
```java
// This throws a compile error:
int population = 4375296489157;
// error: incompatible types: possible lossy conversion from long to int
```
A numeric literal too large for `int`'s range must either be declared as
`long`, or suffixed with `L` (`4375296489157L`) if you specifically want it
as a `long` literal. Assigning a too-large literal directly to `int` is a
type mismatch, not a runtime error — the compiler catches it before the
program ever runs.

## Compiling & Running from Terminal
```bash
javac FirstJava.java   # compiles to FirstJava.class
java FirstJava         # runs the compiled class
```
Note: this is the plain `javac`/`java` workflow for a single standalone
file with no package or dependencies — not how modules in this repo are
run (those use `mvn compile exec:java`, since they're Maven-managed).