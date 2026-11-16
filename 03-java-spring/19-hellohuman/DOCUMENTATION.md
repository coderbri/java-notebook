# 19 Hello Human • Documentation

A walkthrough of how `GreetingController` turns a URL query string into a greeting, and what each built-in method in it does. For the project specifications and expected output, see the [README](../README.md).

---

## Table of Contents

- [The Big Idea: One Route, Optional Parameters](#the-big-idea-one-route-optional-parameters)
- [How a Request Becomes a Response](#how-a-request-becomes-a-response)
- [The `hello` Method, Line by Line](#the-hello-method-line-by-line)
- [The `buildName` Method, Line by Line](#the-buildname-method-line-by-line)
- [Built-in Methods Quick Reference](#built-in-methods-quick-reference)
- [Why Not Several Request Maps?](#why-not-several-request-maps)
- [Edge Cases](#edge-cases)

---

## The Big Idea: One Route, Optional Parameters

The challenge adds requirements in layers: a default greeting, then a name, then a last name, then a repeat count. It is tempting to write one handler method per layer, but all of them are the **same route** (`/hello`) with different **query parameters**. So one handler takes every parameter as optional and decides what to do based on which ones actually arrived.

```java
@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
public String hello(
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "last_name", required = false) String lastName,
        @RequestParam(value = "times", required = false) Integer times
) {
    String greeting = "Hello, " + buildName(name, lastName);
    int count = (times == null || times < 1) ? 1 : Math.min(times, MAX_TIMES);
    return String.join("\n", Collections.nCopies(count, greeting));
}
```

The work is split into two steps, each in its own place:

1. **`buildName`** decides *who* to greet (first name, last name, or "Human").
2. **`hello`** decides *how many times* and assembles the final text.

---

## How a Request Becomes a Response

Take the URL `localhost:8080/hello?name=Ava&last_name=Lovelace&times=2`.

1. The browser sends a GET request. The **path** is `/hello`, and the **query string** is `name=Ava&last_name=Lovelace&times=2`.
2. Spring matches the path to `hello`, the only handler for GET `/hello`.
3. For each `@RequestParam`, Spring looks up that key in the query string and converts the text to the parameter's type. `name` and `last_name` stay `String`; `times` becomes an `Integer`.
4. If a key is missing and `required = false`, Spring passes **`null`** for that parameter instead of failing.
5. The method runs and returns a `String`, which Spring writes to the response body as plain text.

```
GET /hello?name=Ava&last_name=Lovelace&times=2
   → name = "Ava", lastName = "Lovelace", times = 2
   → "Hello, Ava Lovelace" (two lines)
```

---

## The `hello` Method, Line by Line

### `String greeting = "Hello, " + buildName(name, lastName);`

Builds the single greeting once, so it can be repeated later. The `+` joins the fixed text with whatever name `buildName` returns.

### `int count = (times == null || times < 1) ? 1 : Math.min(times, MAX_TIMES);`

This line packs three ideas together, so here it is in pieces:

- **The ternary operator** (`condition ? valueIfTrue : valueIfFalse`) is a one-line `if/else` that produces a value. Here it reads: "if `times` is missing or less than 1, use 1; otherwise use the smaller of `times` and `MAX_TIMES`."
- **`||` short-circuits.** The left side (`times == null`) is checked first. If it is true, Java skips the right side. This matters because `times < 1` on a `null` would crash, so the `null` check must come first.
- **`Math.min(a, b)`** returns the smaller of two numbers. With `MAX_TIMES = 100`, a request for `times=500` becomes 100, so nobody can flood the response.

### Why `Integer` and not `int`?

`int` is a primitive and can never be `null`. When `times` is missing, Spring needs to pass "nothing," which only a wrapper type like `Integer` can represent. (An alternative is `@RequestParam(defaultValue = "1") int times`, which hands Spring the fallback instead.)

### `return String.join("\n", Collections.nCopies(count, greeting));`

Read it from the inside out:

- **`Collections.nCopies(count, greeting)`** returns a read-only list containing `greeting` repeated `count` times. For `count = 3`: `["Hello, Ava", "Hello, Ava", "Hello, Ava"]`.
- **`String.join("\n", list)`** glues the items together with the separator between them (and none at the end). `"\n"` is a line break, so each greeting appears on its own line.

This replaces a loop that would build the string by hand.

### Why `produces = MediaType.TEXT_PLAIN_VALUE`?

It declares the response as plain text. Two benefits: `\n` displays as a real line break in the browser, and a name like `<b>Ava</b>` is shown as text instead of being interpreted as HTML.

---

## The `buildName` Method, Line by Line

```java
private String buildName(String name, String lastName) {
    boolean hasFirst = name != null && !name.isBlank();
    boolean hasLast = lastName != null && !lastName.isBlank();

    if (hasFirst && hasLast) {
        return name.trim() + " " + lastName.trim();
    } else if (hasFirst) {
        return name.trim();
    } else if (hasLast) {
        return lastName.trim();
    }
    return "Human";
}
```

### The two `boolean` checks

`name != null && !name.isBlank()` means "the parameter arrived **and** it has real content."

- **`&&` short-circuits too.** If `name` is `null`, Java stops at the left side and never calls `.isBlank()` on it, which would throw a `NullPointerException`. The `null` check must come first.
- **`isBlank()`** is `true` for an empty string or one with only spaces. That is why `?name=` and `?name=%20%20` fall back to "Human." (Its cousin `isEmpty()` only catches the zero-length case.)

Storing each check in a named `boolean` keeps the `if` chain below readable.

### The `if / else if` chain

The cases are listed from most specific to least:

| `hasFirst` | `hasLast` | Result |
|---|---|---|
| true | true | `Ava Lovelace` |
| true | false | `Ava` |
| false | true | `Lovelace` |
| false | false | `Human` |

### `trim()`

Removes spaces from both ends, so `?name=%20Ava%20` greets "Ava" instead of " Ava ".

### Why a separate method?

`buildName` has one job and a clear name, so `hello` stays short. It is `private` because only this controller needs it, and it is not a route.

---

## Built-in Methods Quick Reference

| Method / syntax | What it does | Example |
|---|---|---|
| `@RequestParam(value = "x", required = false)` | Reads `x` from the query string; passes `null` if missing | `?x=5` → `"5"` |
| `str.isBlank()` | `true` if empty or only whitespace | `"  ".isBlank()` → `true` |
| `str.trim()` | Removes leading and trailing whitespace | `" Ava ".trim()` → `"Ava"` |
| `cond ? a : b` | One-line if/else that produces a value | `(5 < 1) ? 1 : 5` → `5` |
| `Math.min(a, b)` | Smaller of two numbers | `Math.min(500, 100)` → `100` |
| `Collections.nCopies(n, x)` | Read-only list of `x` repeated `n` times | `nCopies(2, "Hi")` → `["Hi", "Hi"]` |
| `String.join(sep, list)` | Joins items with a separator between them | `join("\n", ["a", "b"])` → `a` and `b` on two lines |
| `&&` / `\|\|` | Logical AND / OR; both stop early when the result is already known | `x != null && x.isBlank()` |

---

## Why Not Several Request Maps?

Spring chooses a handler by **URL path** and **HTTP method**. Query parameters are not part of that identity unless you add an explicit condition such as `params = "name"`. So three methods mapped to GET `/hello`, each with different `@RequestParam`s, look identical to Spring, and the app fails at startup with an "Ambiguous mapping" error.

Because `required = false` already lets one method accept any combination of parameters, a single handler is both correct and simpler. Separate routes make sense when the **path** means something different (for example `/chengyu/today` vs. `/chengyu/tomorrow`).

---

## Edge Cases

| URL | Result | Why |
|---|---|---|
| `/hello` | `Hello, Human` | Every parameter is `null` |
| `/hello?name=` | `Hello, Human` | Empty string fails `isBlank()` |
| `/hello?name=%20Ava%20` | `Hello, Ava` | `trim()` removes the spaces |
| `/hello?last_name=Lovelace` | `Hello, Lovelace` | Last name alone is allowed |
| `/hello?name=Ava&times=0` | One `Hello, Ava` | `times < 1` falls back to 1 |
| `/hello?name=Ava&times=-5` | One `Hello, Ava` | Same rule |
| `/hello?name=Ava&times=500` | 100 lines | Capped by `Math.min` |
| `/hello?name=Ava&times=abc` | 400 error | Spring can't convert `"abc"` to `Integer` |
| `/?name=Ava` | Redirects to `/hello?name=Ava` | `setKeepQueryParams(true)` on the root redirect |