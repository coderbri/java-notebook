# 05 — Puzzling

A set of problem-solving puzzles built around randomness: dice rolls, random letters, password generation, and array shuffling. Introduces the `java.util.Random` class and applies earlier concepts (loops, overloading, `ArrayList`) to tasks where the output changes on every run.

## Table of Contents
- [Overview](#overview)
- [Logic Flow](#logic-flow)
    - [Ten Rolls](#ten-rolls)
    - [Random Letter](#random-letter)
    - [Password Generation](#password-generation)
    - [Shuffle Array](#shuffle-array)
    - [Generate Password (Overloaded)](#generate-password-overloaded)
- [Concepts Implemented](#concepts-implemented)
- [Expected Output](#expected-output)
- [Steps to Run](#steps-to-run)

## Overview
`PuzzleJava.java` holds every puzzle as a public method and owns a single `Random` instance shared across all of them. `TestPuzzleJava.java` is the driver: it creates one `PuzzleJava` object and calls each method in order under a labeled header. Because every method is random, the output differs on each run — what stays constant is the *shape* of the result (a number in range, a password of the right length, an array with the same elements in a new order).

## Logic Flow

### Ten Rolls
`getTenRolls()` loops ten times, adding `randomMachine.nextInt(1, 21)` to a list on each pass. The two-argument `nextInt` includes the lower bound and excludes the upper bound, so `(1, 21)` yields `1` through `20`.

### Random Letter
`getRandomLetter()` picks a random index from `0` to `25` using `ALPHABET.length()` as the bound, then returns the character at that index with `charAt`.

### Password Generation
- `generatePassword()` calls `getRandomLetter()` eight times and appends each result to a `StringBuilder`, then converts it to a `String` with `.toString()` before returning.
- `getNewPasswordSet(int length)` calls `generatePassword()` once per requested password and collects the results in an `ArrayList<String>`.

### Shuffle Array
`shuffleArray(int[] array)` implements the **Fisher-Yates (Knuth) shuffle**. It starts at the last index (`array.length - 1`) and works backward. At each position `i`, it picks a random index from `0` to `i` (inclusive) and swaps the two values. A short trace on `{1, 2, 3, 4, 5}`, using one possible set of random picks:

| `i` | `index` picked | Swap | Array after |
|---|---|---|---|
| 4 | 1 | `array[1]` ↔ `array[4]` | `[1, 5, 3, 4, 2]` |
| 3 | 3 | stays in place | `[1, 5, 3, 4, 2]` |
| 2 | 0 | `array[0]` ↔ `array[2]` | `[3, 5, 1, 4, 2]` |
| 1 | 0 | `array[0]` ↔ `array[1]` | `[5, 3, 1, 4, 2]` |

The loop stops at `i > 0` because the first position has nothing left to swap with. Writing `nextInt(i + 1)` matters: the bound is exclusive, so at `i = 4` it passes `5` and can return `0` through `4`. That is what lets an element land on itself (the `i = 3` row above) instead of being forced to move.

The `char[]` overload follows the identical algorithm and exists as a helper for the overloaded password method below.

### Generate Password (Overloaded)
`generatePassword(int length)` builds a password in three stages. Using a length of `7` as an example (the `og password` and `pwCharacterArray` lines below came from temporary debug prints used while developing, and were removed from the final source):

1. **Build an alternating pattern.** Even indexes get a random letter and odd indexes get a random digit (`i % 2 == 0`).
2. **Convert to a `char[]`.** `toCharArray()` turns the string into a mutable array, since `String` objects can't be changed in place.
3. **Shuffle and rebuild.** `shuffleArray(char[])` scrambles the characters, and `new String(chars)` turns the result back into a `String`.

```
og password: k4r7x2m
pwCharacterArray = [k, 4, r, 7, x, 2, m]
Finalized password: 7xm2kr4
```
Without step 3, every password would follow the same letter-digit-letter-digit pattern, which an attacker could exploit. The shuffle removes that predictability while keeping the guaranteed mix of letters and digits.

## Concepts Implemented

- **The `Random` class.** `new Random()` creates a pseudorandom number generator, imported from `java.util`. `nextInt(bound)` returns `0` up to `bound - 1`, while `nextInt(origin, bound)` returns `origin` up to `bound - 1`. The two-argument form requires Java 17 or later. In both, the upper bound is exclusive, which is why the rolls use `(1, 21)` to cover `1` through `20`.

- **`static final` constants.** `ALPHABET` is declared `private static final`. `static` means one copy belongs to the class instead of one per object, and `final` means the value can't be reassigned, which protects the alphabet from accidental changes elsewhere in the class. A performance benefit exists but is tiny here, since string literals are already shared by Java behind the scenes. The real payoff is safety and clear intent.

- **`StringBuilder` vs. `+=` concatenation.** Strings are immutable, so `password += letter` inside a loop creates a brand-new `String`, copies the old contents into it, appends the letter, and discards the old string for garbage collection, on every iteration. At eight characters the cost is invisible, but in larger applications processing files or long text, it adds up to real memory overhead. `StringBuilder` is mutable and appends to one buffer, so it avoids the intermediate objects. `.toString()` converts the finished builder back to a `String` to satisfy the return type.

- **The Fisher-Yates shuffle.** A standard algorithm for shuffling in place: walk backward through the array and swap each position with a random position at or before it. Every ordering is equally likely, which is why it's preferred over ad hoc approaches like swapping random pairs.

- **Method overloading.** `generatePassword()` and `generatePassword(int length)` share a name with different parameter lists, as do the two `shuffleArray` methods, which differ by array type (`int[]` vs. `char[]`). Java picks the version based on what is passed in. The shuffle logic is duplicated across the two array types because generics don't work with primitive arrays.

- **In-place modification.** `shuffleArray` returns `void` because arrays are objects passed by reference: the method changes the caller's array directly, with no copy to return.

- **Modulo for alternating patterns.** `i % 2 == 0` is true on every even index, which switches between appending a letter and appending a digit.

- **`List` vs. `ArrayList` return types.** `getTenRolls()` declares its return type as the `List` interface, while `getNewPasswordSet()` returns `ArrayList<String>` directly. Both work. Declaring the interface keeps callers independent of the implementation, and using the concrete class is fine in a project this size.

## Expected Output

Values are random, so your output will differ on every run. This is a representative example:

```
=== Get Ten Rolls ====================
[14, 3, 20, 7, 11, 1, 18, 9, 5, 16]

=== Get Random Letter ================
q

=== Generate Password ================
mfhtzcwa

=== Get New Password Set =============
[kdpwxqzn, ahvbtlre, ojycgsiu, wnxefqmd]

=== Shuffle Array ====================
Original array: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
Shuffled array: [7, 2, 10, 5, 1, 9, 3, 8, 6, 4]

=== Shuffle Array (Overloaded) =======
Original char array: [j, a, v, a, t, e, s, t]
Shuffled char array: [t, a, s, j, a, t, v, e]

=== Generate Password (Overloaded) ===
Finalized 7-char password: 7xm2kr4
Finalized 8-char password: 0qb5w3d9
```

## Steps to Run

From this module's directory (`01-java-fundamentals/05-puzzling`):

```bash
mvn compile exec:java -Dexec.mainClass="com.learn.puzzling.TestPuzzleJava"
```

No input is required. Run it several times to see the random results change.