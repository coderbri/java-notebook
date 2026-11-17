# Chéngyǔ: Path Variables • Documentation

A walkthrough of the new path variable routes and the custom error controller, and what each built-in piece does. For the project specifications and expected output, see the [README](./README.md).

---

## Table of Contents

- [The Travel Route](#the-travel-route)
- [The Lotto Route](#the-lotto-route)
- [The Custom Error Controller](#the-custom-error-controller)
- [Built-in Methods Quick Reference](#built-in-methods-quick-reference)
- [404 vs. 400](#404-vs-400)
- [Edge Cases](#edge-cases)

---

## The Travel Route

```java
@RequestMapping(value = "/travel/{city}", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
public String travel(@PathVariable("city") String city) {
    return "Congratulations! You will soon travel to " + city + "! " + /* chengyu text */;
}
```

1. **`{city}` is a placeholder.** The class-level `/chengyu` prefix plus `/travel/{city}` makes the full pattern `/chengyu/travel/{city}`. Whatever text sits in the `{city}` position of the URL is captured.
2. **`@PathVariable("city")`** hands that text to the `city` parameter. The name in quotes must match the placeholder exactly.
3. **The value is required.** Unlike `@RequestParam(required = false)`, a path variable cannot be left out. `/chengyu/travel` with no city does not match the route, so it produces a 404.
4. **`produces = TEXT_PLAIN_VALUE`.** The city comes straight from the URL and is echoed back, so plain text keeps a value like `<b>kyoto</b>` from being interpreted as HTML.

---

## The Lotto Route

```java
@RequestMapping(value = "/lotto/{number}", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
public String lotto(@PathVariable("number") int number) {
    if (number % 2 == 0) {
        return /* even fortune */;
    }
    return /* odd fortune */;
}
```

1. **Type conversion.** The captured text arrives as a string, and Spring converts it to `int` because of the parameter type. Text that isn't a whole number (`abc`, `8.5`) fails the conversion and produces a 400 error.
2. **The remainder operator `%`.** `number % 2` is the remainder after dividing by 2: 0 for even numbers and 1 (or -1 for negatives) for odd numbers. Checking `== 0` therefore means "even," and zero counts as even.
3. **Early return.** The even case returns immediately, so the code after the `if` only runs for odd numbers and needs no `else`.

---

## The Custom Error Controller

```java
@RestController
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<String> handleError(HttpServletRequest request) {
        Object attribute = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        int status = (attribute == null) ? 500 : Integer.parseInt(attribute.toString());

        String body = switch (status) {
            case 404 -> page("404 Not Found", "...");
            case 400 -> page("400 Bad Request", "...");
            default -> page(status + " Something Went Wrong", "...");
        };

        return ResponseEntity.status(status).contentType(MediaType.TEXT_HTML).body(body);
    }
}
```

### How errors reach this controller

When no route matches, or a path variable can't be converted, Spring marks the request with the error's status code and **forwards** it to the path `/error`. Spring Boot's default handler for `/error` is the Whitelabel page. By implementing the `ErrorController` interface and mapping `/error` yourself, your controller replaces it.

### Line by line

1. **`HttpServletRequest request`.** Spring passes in the forwarded request, which carries information about the error.
2. **`request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)`.** Reads the status code from the request. `getAttribute` returns an `Object`, and it is `null` if nobody attached a status, such as when someone visits `/error` directly.
3. **`(attribute == null) ? 500 : Integer.parseInt(attribute.toString())`.** A ternary: use 500 if there is no status, otherwise turn the attribute into text with `toString()` and then into an `int` with `Integer.parseInt`.
4. **The `switch` expression.** `switch (status) { case 404 -> ...; }` produces a value, one branch per status code. The arrow form needs no `break` and has no fall-through, and the `default` branch covers every other status.
5. **`page(title, message)`.** A private helper that builds the HTML (heading, message, link home) so each case stays one short line.
6. **`ResponseEntity`.** A wrapper that carries the body, the status code, and the headers together. Returning a plain `String` would send a 200 OK even for an error, which is misleading. `ResponseEntity.status(status)` keeps the real code.
7. **`.contentType(MediaType.TEXT_HTML)`.** Marks the response as HTML so the browser renders the heading and link. This is safe here because nothing from the URL is echoed into the page.

---

## Built-in Methods Quick Reference

| Method / syntax | What it does | Example |
|---|---|---|
| `{name}` in a route + `@PathVariable("name")` | Captures part of the URL path into a parameter | `/travel/kyoto` → `city = "kyoto"` |
| `a % b` | Remainder after dividing `a` by `b` | `8 % 2` → `0`, `7 % 2` → `1` |
| `request.getAttribute(key)` | Reads a value stored on the request | Error status code |
| `RequestDispatcher.ERROR_STATUS_CODE` | The attribute name that holds the error's status code | Used with `getAttribute` |
| `Integer.parseInt(text)` | Converts text to an `int` | `"404"` → `404` |
| `cond ? a : b` | One-line if/else that produces a value | `(x == null) ? 500 : x` |
| `switch (x) { case 1 -> ...; default -> ...; }` | A switch that produces a value, with no `break` needed | Picks the error page |
| `ResponseEntity.status(n).contentType(t).body(b)` | Builds a response with an explicit status, type, and body | A 404 HTML page |
| `MediaType.TEXT_HTML` / `TEXT_PLAIN_VALUE` | Names a content type | HTML vs. plain text |

---

## 404 vs. 400

| Status | Meaning here | Example |
|---|---|---|
| **404 Not Found** | No route matches the URL at all | `/chengyu/tomorow` (typo) or `/chengyu/travel` (missing value) |
| **400 Bad Request** | A route matched, but a value couldn't be converted | `/chengyu/lotto/abc` |

The two get different messages because they call for different fixes: check the spelling of the URL, or check the value inside it.

---

## Edge Cases

| URL | Result | Why |
|---|---|---|
| `/chengyu/travel/kyoto` | Travel fortune | Any string fills `{city}` |
| `/chengyu/travel/new%20york` | Travel fortune for "new york" | Spring decodes `%20` into a space |
| `/chengyu/travel` | 404 page | The required path variable is missing |
| `/chengyu/lotto/0` | Even fortune | `0 % 2 == 0` |
| `/chengyu/lotto/-3` | Odd fortune | `-3 % 2` is `-1`, which is not `0` |
| `/chengyu/lotto/8.5` | 400 page | `8.5` can't convert to `int` |
| `/chengyu/lotto/99999999999` | 400 page | Too large for an `int` |
| `/error` (visited directly) | "500 Something Went Wrong" page | No status attribute, so the code defaults to 500 |