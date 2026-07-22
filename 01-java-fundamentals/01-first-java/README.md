# 01 – My First Java Program

A "Hello, World!" starting point that goes one step further — beyond the classic greeting, it introduces variable declarations and two different ways to print output in Java.

## Table of Contents

- [Overview](#overview)
- [Logic Flow](#logic-flow)
- [Expected Output](#expected-output)
- [How to Run](#how-to-run)

## Overview

This program prints a classic "Hello, World!" message, then extends it by declaring a few variables and formatting them into output using `printf`. Each line demonstrates a different fundamental:

| Line | What It Prints | Concept Demonstrated |
|---|---|---|
| Greeting | `Hello, World! My name is Bri.` | Basic class/`main` structure, `println`, string concatenation |
| Role check | `Am I a software engineer? true` | `boolean` variable, `%s` format specifier |
| Years coding | `I've been coding for 3 years.` | `int` variable, `%d` format specifier |

## Logic Flow

1. **Setup** — Three variables are declared: `myName` (`String`), `isCoder` (`boolean`), and `yearsCoding` (`int`).
2. **Greeting** — `System.out.println` prints a Hello World message, built using string concatenation (`+`) to insert `myName`.
3. **Role check** — `System.out.printf` prints whether Bri is a coder, using `%s` to format the `boolean` value.
4. **Years coding** — `System.out.printf` prints the number of years coding, using `%d` to format the `int` value.

## Expected Output

```
Hello, World! My name is Bri.
Am I a software engineer? true
I've been coding for 3 years.
```

## How to Run

This project runs directly from IntelliJ IDEA using the `Main` class — no build tool commands are required.

1. **Open the project** in IntelliJ IDEA (`File > Open`, then select the project's root folder).
2. **Locate `Main.java`** in the project tool window, under `src/com/learn/cafe/`.
3. **Run the file** using either method:
    - Click the green ▶ (run) icon in the gutter next to `public class Main` or `public static void main`.
    - Right-click anywhere in `Main.java` and select **Run 'Main.main()'**.
4. **View the output** in the **Run** tool window at the bottom of the IDE — it will display the console output shown above.

However, if you wish to run a terminal command, it's as follows:

```bash
cd 01-java-fundamentals/01-first-java
mvn compile exec:java -Dexec.mainClass="com.learn.hello.Main"
```

## Notes

- `println` automatically adds a newline; `printf` requires `\n` to be added explicitly.
- `%s` works with any object (it calls `.toString()` under the hood), while `%d` is strictly for integer values.