# 06 - Encapsulation and Access Modifiers

Conventions for deciding what is `private` and what is `public` in a class. The default is to hide everything and open up only what outside code needs.

## The Four Access Levels

| Modifier | Visible to | Typical use |
|---|---|---|
| `private` | The same class only | Fields, helpers, validation, internal formatting |
| *(none)* package-private | Classes in the same package | Rare for now; shared helpers inside one package |
| `protected` | Same package and subclasses | Members meant to be inherited (revisit with inheritance) |
| `public` | Everything | The class's API: constructors and methods the controller calls |

## Fields (Member Variables)

**Rule: every field is `private`.** Outside code reads or changes state only through methods, so the class can protect its own rules (for example, "a balance can't go negative").

- **Add a getter only if outside code needs to read the value.** Don't generate getters for everything automatically.
- **Add a setter only if outside code should be allowed to change the value.** If a setter exists, validate the input inside it.
- **No setter for derived or tracked values.** A `total` that can be calculated should be a method (`getOrderTotal()`), not a stored field with a setter.
- **Use `final` for values that never change after construction.** The compiler then enforces it, and no setter is possible.
- **Boolean getters use `is`.** For example, `isReady()` and `setReady(boolean)`.

## Static Members

- **Mutable static state is `private static`** with a `public static` getter. This lets the class control every change to the shared value.
- **Constants are `private static final`** unless other classes genuinely need them, in which case `public static final`. They use UPPER_SNAKE_CASE (for example, `RANDOM`).
- **Don't make static trackers `public`.** Anyone could overwrite the value from outside with `BankAccount.totalAmountOfMoney = 1_000_000;`.

## Methods

**Rule: a method is `public` only if the controller (or another class) needs to call it.** Everything else is `private`.

Make a method `public` when it:
- is part of what the class *does* for the outside world (`deposit`, `withdraw`, `addItem`, `displayReceipt`)
- is a getter or setter you decided to expose

Make a method `private` when it:
- is a **helper** that only exists to support other methods (`printExitLine`)
- **validates** input or state before an action (`isInvalidTransaction`, `hasSufficientFunds`)
- **generates** internal values the caller shouldn't control (`generateAccountNumber`)
- could **break the class's rules** if called directly from outside

Make a private method `static` when it doesn't use any instance fields (`this`). It signals "this helper only depends on its arguments."

## Decision Checklist

1. Does code outside this class need to use it? **No → `private`.**
2. Could calling or changing it directly break the class's rules? **Yes → `private`** and expose a safe method instead.
3. Does it only read state? **Getter** (if needed).
4. Does it change state? **A named action method** (`deposit`) before a raw setter, since the action method can enforce the rules.
5. Does it avoid `this`? **Add `static`.**

## Common Mistakes

- **Public fields:** they let any code bypass validation.
- **Setters for everything:** a `setCheckingBalance` method defeats the purpose of having `deposit` and `withdraw`.
- **Returning a mutable internal collection:** `getOrderedItems()` hands back the real list, so callers can modify it without going through `addItem`. Returning a copy or an unmodifiable view avoids this.
- **Validation spread across callers:** put the check in one private helper that every public method uses.

## In practice

- **`10-baristas-challenge`:** `Item` and `Order` fields are private. `Order` exposes `addItem`, `getOrderTotal`, and `displayReceipt`, and the old stored `total` became a method.
- **`11-bank-account`:** balances are private with getters and no setters. Money changes only through `deposit` and `withdraw`. The static trackers are private with static getters, `accountNumber` is `final`, and `isInvalidTransaction`, `hasSufficientFunds`, `generateAccountNumber`, and `printExitLine` are private helpers.