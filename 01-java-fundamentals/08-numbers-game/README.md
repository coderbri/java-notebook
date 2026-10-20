# 08 — Numbers Game

The final project of Phase 01 and a rebuild of an older challenge. A console guessing game: the computer picks a number from 0 to 10, and the player gets three valid guesses to find it. The player can type `q` to quit and can play as many rounds as they like. Everything lives in one file, `NumbersGame.java`, and it brings together loops, conditionals, methods, `Random`, `Scanner`, and `try/catch` from the earlier modules.

Run it from this module's directory:

```bash
mvn compile exec:java -Dexec.mainClass="com.learn.numbers.NumbersGame"
```

## Logic Flow

### The game loop
`main` creates one `Scanner` and repeats `playGame` followed by `askPlayAgain` until the player declines, then says goodbye.

### One round
1. Print the title banner and the rules, with the range and guess limit built from constants.
2. Pick the answer once for the round with `RANDOM.nextInt(MIN_NUMBER, MAX_NUMBER + 1)`, so 10 is a possible answer.
3. Loop while the player has guesses left, prompting `Guess n of 3` and reading a trimmed line.
4. Check each entry in this order:
    - `q` (any case) ends the round.
    - Text that isn't a number is caught and re-prompted.
    - A number outside 0–10 is rejected and re-prompted.
    - A valid guess is counted. A correct guess wins the round, and a wrong guess with tries left prints a "swing and miss" message.
5. If the guesses run out, reveal the answer.

### Play again
`askPlayAgain` repeats until the player gives a clear answer (`y`, `yes`, `n`, or `no`, in any case) and returns it as a `boolean`.

### What the game guarantees
- Bad input never crashes the program.
- Invalid or out-of-range entries don't use up a guess.
- The answer is picked once per round, so a wrong number stays wrong.
- The "keep trying" message is skipped after the final wrong guess.

## Concepts Implemented

**`Scanner` for console input.** `Scanner(System.in)` works in IntelliJ, STS4, and a real terminal, unlike `System.console()`, which returns `null` outside a terminal. One shared `Scanner` is passed into the methods that read input, and each entry is read with `nextLine()` and then parsed.

**`private static final` constants.** The range and guess limit are named constants at the top of the class instead of repeated numbers. Changing one value updates the logic and the messages together.

**Loops.** A `while` loop with a counter limits each round to three valid guesses. `askPlayAgain` uses `while (true)` and leaves only through a `return`.

**Method decomposition.** `playGame`, `askPlayAgain`, and `printBanner` each do one job, which keeps `main` to a short readable loop.

**`try/catch` for input validation.** `Integer.parseInt` throws a `NumberFormatException` on non-numeric text. Catching it turns a crash into a friendly re-prompt, and an invalid entry doesn't count as a guess.

**Ordered conditionals.** The checks run quit, then valid number, then in range, then correct, then wrong. Special commands and invalid input are handled first so the rest of the logic can trust its data.

**`Random.nextInt(origin, bound)`.** The upper bound is exclusive, so `MAX_NUMBER + 1` is passed to include 10. The two-argument form requires Java 17 or later.

For the full breakdown with code, see [`notes/05-input-and-control-flow.md`](../../notes/05-input-and-control-flow.md). For `try/catch` in more depth, see [`notes/04-exceptions.md`](../../notes/04-exceptions.md).