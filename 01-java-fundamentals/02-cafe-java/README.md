# 02 – Café Java

A simple Java console application that simulates a coffee shop order system. It demonstrates core Java fundamentals — variables, `printf` formatting, conditionals, and the ternary operator — by walking through four customers placing orders and receiving their status and totals.

## Table of Contents

- [Overview](#overview)
- [Logic Flow](#logic-flow)
- [Expected Output](#expected-output)
- [How to Run](#how-to-run)

## Overview

Café Java models a small set of customer orders at a coffee shop. Each customer scenario exercises a different language feature:

| Customer | Order | Concept Demonstrated |
|---|---|---|
| Cindhuri | Drip coffee | Ternary operator for conditional message + total |
| Noah | Cappuccino | `if / else` for ready vs. pending status |
| Sam | 2 lattes | Variable math (quantity × price) |
| Jimmy | Mocha (billed for coffee by mistake) | Multi-step interaction simulating an order correction |

Menu prices and customer names are declared as variables up front, and order-readiness is controlled by `boolean` flags so the flow can be changed just by flipping `true`/`false`.

## Logic Flow

1. **Setup** — Message templates (with `%s` / `%.2f` placeholders), menu prices, customer names, and order-ready flags are declared as variables.
2. **Header** — A simple ASCII banner is printed to open the simulation.
3. **Cindhuri** — A ternary operator checks `isReadyOrder1`. Since it's `false`, the pending message is chosen over the ready + total message.
4. **Noah** — A standard `if / else` checks `isReadyOrder2`. Since it's `true`, the ready message and cappuccino total print.
5. **Sam** — `lattePrice * 2` calculates the total for two lattes, then an `if / else` on `isReadyOrder3` prints the ready message and that total.
6. **Jimmy** — Simulates a billing mistake and its correction:
    - Jimmy is first (incorrectly) shown a total that combines a coffee charge with his mocha (`dripCoffeePrice + mochaPrice`).
    - Jimmy "speaks up" via a printed line, and the "barista" responds.
    - The corrected, accurate total (`mochaPrice` alone) is printed as the final total.

## Expected Output

```
+------------------------+
|       Café Java        |
+------------------------+

Welcome to Café Java, Cindhuri.
Cindhuri, your order will be ready shortly.

Welcome to Café Java, Noah.
Noah, your order is ready!
Your total is $4.36.

Welcome to Café Java, Sam.
Sam, your order is ready!
Your total is $8.32.

Welcome to Café Java, Jimmy.
Jimmy, your order is ready!
Your total is $5.65.

Jimmy: Hey! I didn't order a coffee! I only ordered a mocha!
Barista: We're so sorry, sir. We'll fix that right up.
Jimmy, your order is ready!
Your total is $3.50.
```

## How to Run

This project runs directly from IntelliJ IDEA using the `Main` class — no build tool commands are required.

1. **Open the project** in IntelliJ IDEA (`File > Open`, then select the project's root folder).
2. **Locate `Main.java`** in the project tool window, under `src/com/learn/cafe/`.
3. **Run the file** using either method:
    - Click the green ▶ (run) icon in the gutter next to `public class Main` or `public static void main`.
    - Right-click anywhere in `Main.java` and select **Run 'Main.main()'**.
4. **View the output** in the **Run** tool window at the bottom of the IDE — it will display the console output shown above.

No additional dependencies, arguments, or configuration are needed to run this project.

---
<section align="center">
  <code>coderBri © 2026</code>
</section>