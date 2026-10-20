# Console Input & Control Flow

Reference notes from the `08-numbers-game` project: reading console input with `Scanner`, declaring constants, splitting logic into methods, validating input with `try/catch`, and ordering conditionals.

## Reading Input with `Scanner`
```java
Scanner scanner = new Scanner(System.in);
String guess = scanner.nextLine().trim();
```
- `System.in` is the program's raw input stream (bytes from the keyboard). `Scanner` wraps it and turns it into text you can read.
- `nextLine()` returns everything typed up to Enter, without the newline. `.trim()` removes stray spaces at either end.
- A prompt is a separate step. `System.out.print(...)` (no newline) keeps the cursor on the same line as the prompt, while `println` pushes the user's typing onto the next line.
- Read a whole line with `nextLine()` and then convert it with `Integer.parseInt`, instead of calling `nextInt()`. `nextInt()` stops before the newline and leaves it behind, so a later `nextLine()` returns an empty string.

### Why not `System.console()`?
`System.console()` returns a `Console` object only when the program is launched from a real, interactive terminal. IntelliJ and Spring Tool Suite 4 (which is Eclipse-based) don't run your program in a real terminal. They capture its input and output so they can display it in their own Run/Console panel. In that situation, on the JDK this repo uses (20), `System.console()` returns `null`, and calling `.readLine()` on it throws a `NullPointerException`.

| | `Scanner(System.in)` | `System.console()` |
|---|---|---|
| Works in IntelliJ / STS4 | Yes | No (returns `null`) |
| Works from a terminal (`java`, `mvn exec:java`) | Yes | Yes |
| Built-in prompt argument | No (print the prompt separately) | Yes (`readLine("prompt")`) |
| Hidden password input | No | Yes (`readPassword()`) |

Course examples often use `System.console()` because they're run from a terminal with `javac` and `java`. `Scanner` is the choice that behaves the same everywhere.

### Use one shared `Scanner`
Create the `Scanner` once in `main` and pass it to every method that needs input. A `Scanner` reads ahead into its own buffer, so a second `Scanner` on `System.in` can miss text the first one already consumed. Closing any `Scanner` that wraps `System.in` also closes the stream for the whole program, so `close()` belongs at the very end of `main`.

## Constants: `private static final`
```java
private static final int MIN_NUMBER = 0;
private static final int MAX_NUMBER = 10;
private static final int MAX_GUESSES = 3;
private static final Random RANDOM = new Random();
```
Each keyword has a job:
- `private` — only this class can use it (encapsulation).
- `static` — one copy belongs to the class instead of one per object. `main` and the helper methods are `static`, and a static method can only use other static members without an object to work through.
- `final` — the variable can't be reassigned after it's set.

Why declare them at the top of the class:
- **Convention.** Java style guides put fields first, so a reader sees the class's settings immediately. It's a convention, not a language rule.
- **No magic numbers.** A bare `3` or `10` buried in the code doesn't say what it means. `MAX_GUESSES` does.
- **One place to change.** Changing `MAX_GUESSES` updates the loop condition and every message built from it. A harder mode becomes a one-line edit.
- **`UPPER_SNAKE_CASE`** signals "constant" at a glance.

`final` locks the *variable*, not the object it points to. `RANDOM` always refers to the same `Random` object, but that object's internal state still changes every time it generates a number.

## Splitting Logic into Methods: `askPlayAgain()`
`main` now reads like a summary of the program:
```java
while (playAgain) {
    playGame(scanner);
    playAgain = askPlayAgain(scanner);
}
```
Each method has one job:
- `playGame` runs a single round.
- `askPlayAgain` asks the question and returns `true` or `false`.
- `main` decides whether to keep going.

`askPlayAgain` also hides a validation loop:
```java
while (true) {
    // ask, then read the response
    if (response.equals("yes") || response.equals("y")) {
        return true;
    } else if (response.equals("no") || response.equals("n")) {
        return false;
    }
    // anything else: print a message and ask again
}
```
This is the "ask until valid" pattern. `while (true)` never ends on its own, so the only way out is a `return`, which happens only on a clear answer. Keeping that loop in its own method keeps `main` short. Earlier versions used `response.equalsIgnoreCase("yes")`, which quietly treated `y`, `n`, and typos all as "no".

## Validating Input with `try/catch`
```java
try {
    int numberGuess = Integer.parseInt(guess);
    // range check, then game logic
} catch (NumberFormatException e) {
    System.out.println("[x] Invalid input. Enter a number from 0 to 10, or 'q' to quit.");
}
```
In plain terms: *try to turn the typed text into a number. If the text isn't a number, don't crash. Show a message and let the loop ask again.*

- Text that makes `parseInt` fail: `g`, an empty line, `3.5`, and numbers too big for an `int`.
- The guess counter goes up only after the parse and the range check both succeed, so bad input never uses up a guess.
- This is a good fit for `try/catch` because the program can't know ahead of time whether what the user types is numeric. See [`04-exceptions.md`](./04-exceptions.md) for when `instanceof` is the better tool instead.

## Ordering Conditionals
The order of an `if / else if` chain matters, because Java stops at the first condition that is true. A good rule of thumb is **guards first, success next, fallback last**.

1. **Guards.** Reject or exit on anything invalid, so the code after it can assume clean data.
2. **Success.** Handle the case you're really after.
3. **Fallback.** Everything else, usually the plain `else`.

The Numbers Game follows this order for each guess:

| Order | Check | Why it comes here |
|---|---|---|
| 1 | Is it `q`? Quit | `"q"` would fail to parse as a number, so it must be handled before parsing |
| 2 | Did it parse as a number? | Nothing after this works on text |
| 3 | Is it in range? | Invalid guesses shouldn't count or be compared to the answer |
| 4 | Count the guess | Only valid guesses reach this line |
| 5 | Is it correct? Win and return | The success case |
| 6 | Otherwise, tell them to keep trying (if guesses remain) | The fallback |

Rules that fall out of this:
- **Order by dependency.** Each check should run only after what it relies on has been confirmed.
- **Most specific before most general.** If overlapping ranges are tested out of order, the wrong branch wins:
  ```java
  if (score >= 60) { grade = "D"; }        // a 95 matches here first
  else if (score >= 90) { grade = "A"; }   // never reached for 95
  ```
  Test `>= 90` first, then `>= 80`, and so on.
- **Exit early.** Returning from the quit check and the win check keeps the rest of the code from nesting deeper and deeper.
- **Order matters inside a condition too.** `&&` and `||` stop as soon as the result is known, so `if (text != null && text.equals("q"))` is safe, and flipping it would not be.

### Flattening guards with early exits
A guard can leave right away with `continue` (back to the next loop pass) or `return`, which removes the nested `else`. This version of the guess check behaves the same as the nested one:

```java
try {
    int numberGuess = Integer.parseInt(guess);

    if (numberGuess < MIN_NUMBER || numberGuess > MAX_NUMBER) {
        System.out.printf("[x] Out of range. Enter a number from %d to %d.\n", MIN_NUMBER, MAX_NUMBER);
        continue;
    }

    guessCount++;
    if (numberGuess == answer) {
        System.out.println("[!] Lucky guess! But can you do it again?");
        return;
    }
    if (guessCount < MAX_GUESSES) {
        System.out.println("[x] Swing and miss! Keep trying...");
    }
} catch (NumberFormatException e) {
    // ...
}
```

## Gotcha: the upper bound is exclusive
```java
RANDOM.nextInt(MIN_NUMBER, MAX_NUMBER)       // 0 to 9, so 10 is never picked
RANDOM.nextInt(MIN_NUMBER, MAX_NUMBER + 1)   // 0 to 10
```
`nextInt(origin, bound)` includes `origin` but excludes `bound`. It's the same rule behind `nextInt(i + 1)` in the Fisher-Yates shuffle from `05-puzzling`. The two-argument form needs Java 17 or later.

## Bugs fixed across versions
| Bug | Fix |
|---|---|
| Crashed on input that wasn't a number or `q` | `try/catch` around `Integer.parseInt` |
| Guesses below 0 or above 10 were accepted | Range check before counting the guess |
| A previously wrong number could later be "correct" | Pick the answer once per round, outside the guess loop |
| Unlimited guesses | Counter capped at `MAX_GUESSES` |
| "Keep trying" message printed after the final guess | Print it only while guesses remain |
| No way to play again | Outer loop plus `askPlayAgain` |
| The answer could never be 10 (`nextInt(0, 10)`) | Upper bound of `MAX_NUMBER + 1` |

> **In practice:** `08-numbers-game` uses every idea on this page: one shared `Scanner`, `static final` constants, `askPlayAgain()`, `try/catch` around `parseInt`, and a guard-first conditional order in `playGame()`.