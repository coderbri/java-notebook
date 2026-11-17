# 20 Chéngyǔ: Path Variables

A Spring Boot project for practicing **path variables**, a core piece of RESTful URL design. It builds on [project 18](../18-chengyu-routing) by adding routes that read values straight out of the URL path, such as the city in `/chengyu/travel/kyoto` or the number in `/chengyu/lotto/8`. A custom error page also replaces Spring's default Whitelabel Error page, so invalid URLs and invalid values get a clear message.

> - **Phase:** `03-java-spring`
> - **Package:** `org.learn.pathvariables`
> - **Stack:** Java, Spring Boot, Maven, Spring Web

For a line-by-line explanation of the new routes, the error controller, and the built-in methods they use, see [`DOCUMENTATION.md`](./DOCUMENTATION.md).

---

## Table of Contents

- [Requirements](#requirements)
- [Routes](#routes)
- [Logic Flow](#logic-flow)
- [Concepts Implemented](#concepts-implemented)
- [Steps to Run](#steps-to-run)

---

## Requirements

Build on the Chéngyǔ Routing Practice project and learn how to use path variables.

- [x] Add 2 new routes with corresponding method definitions that accept path variables.
    - [x] A GET request to `/chengyu/travel/kyoto` takes any string for the city and displays a congratulations message about traveling to that city.
    - [x] A GET request to `/chengyu/lotto/8` takes any integer. An even number displays a fortune about a grand journey and tempting offers, and an odd number displays a fortune about enjoying the fruits of your labor.
- [x] Adapt the messages to the context of the project.
- [x] Replace the Whitelabel Error page with a custom error page titled "404 Not Found" for invalid URLs.

---

## Routes

The routes from project 18 are carried over unchanged. The new routes are the two path variable routes and the error page.

| Path | Method | Description |
|---|---|---|
| `/chengyu` | GET | Welcome page |
| `/chengyu/yesterday` | GET | Yesterday's chéngyǔ |
| `/chengyu/today` | GET | Today's chéngyǔ |
| `/chengyu/tomorrow` | GET | Tomorrow's chéngyǔ |
| `/chengyu/random` | GET | A random chéngyǔ from a pool of three |
| `/chengyu/travel/{city}` | GET | **New.** A travel fortune for the city in the path |
| `/chengyu/lotto/{number}` | GET | **New.** An even or odd fortune for the number in the path |
| `/error` | Any | **New.** Custom error page for unmatched URLs (404), invalid values (400), and other errors |

### Examples

| URL | Response |
|---|---|
| `localhost:8080/chengyu/travel/kyoto` | "Congratulations! You will soon travel to kyoto!" followed by a travel chéngyǔ |
| `localhost:8080/chengyu/lotto/8` | Even fortune: a grand journey, but be wary of tempting offers |
| `localhost:8080/chengyu/lotto/7` | Odd fortune: enjoy the fruits of your labor with family and friends |
| `localhost:8080/chengyu/lotto/abc` | **400 Bad Request** page, since `abc` is not a whole number |
| `localhost:8080/chengyu/tomorow` | **404 Not Found** page, since the URL has a typo |

---

## Logic Flow

1. **Request.** The browser sends a GET request such as `localhost:8080/chengyu/lotto/8`.
2. **Routing.** Spring compares the URL against each route pattern. `/chengyu/lotto/{number}` matches, and `{number}` captures the text `8`.
3. **Binding.** `@PathVariable` hands the captured text to the method parameter, converting it to the parameter's type (`int` for the number, `String` for the city).
4. **Response.** For the lotto route, the method checks whether the number is even or odd and returns the matching fortune as plain text.
5. **Errors.** If no route matches, or a value can't be converted, Spring forwards the request to `/error`. The custom error controller reads the status code, picks the matching page, and returns it with the real HTTP status.

```
Browser → Tomcat → Spring MVC → MainController (travel / lotto) → plain-text response
                              ↘ no match or bad value → CustomErrorController → error page
```

---

## Concepts Implemented

### Path variables with `@PathVariable`

A `{placeholder}` in a route pattern captures whatever text sits in that position of the URL. `@PathVariable("city")` passes it to the method parameter, and the name in the quotes must match the placeholder. Spring also converts the text to the parameter's type, which is why `{number}` can be an `int`.

### Path variables vs. query parameters

Project 19 used query parameters (`?name=Ava`), which adjust a request and are optional when `required = false`. Path variables (`/travel/kyoto`) identify *which* thing is requested and are always required, so a missing value means the URL does not match the route at all.

### Even or odd with `%`

The remainder operator `number % 2 == 0` is true for even numbers (including 0). Negative odd numbers give a remainder of `-1`, so they correctly fall through to the odd fortune.

### Fixed routes and variable routes together

`/chengyu/today` and `/chengyu/travel/{city}` have different shapes, so they never conflict. When a fixed path and a variable path could both match, Spring prefers the fixed one.

### Plain text for echoed input

The travel route repeats part of the URL back to the visitor, so both new routes set `produces = MediaType.TEXT_PLAIN_VALUE`. Plain text displays a "city" like `<b>kyoto</b>` as ordinary text instead of interpreting it as HTML.

### A custom error page with `ErrorController`

Spring Boot forwards unhandled errors to `/error`. Implementing `ErrorController` replaces the default Whitelabel handler with your own. The controller reads the status code from the request, uses a `switch` expression to pick a 404, 400, or default page, and returns a `ResponseEntity` so the browser still receives the real HTTP status instead of a misleading 200.

### 404 vs. 400

A **404 Not Found** means no route matches the URL at all, such as a typo. A **400 Bad Request** means a route matched but a value could not be converted, such as `abc` where `{number}` expects an integer.

> Step-by-step explanations of the new methods and the built-ins they use are in [`DOCUMENTATION.md`](./DOCUMENTATION.md).

---

## Steps to Run

1. Make sure JDK 17 or later and Maven are installed.
2. From this project's directory, start the app:

   ```bash
   mvn spring-boot:run
   ```

3. Wait for the console to show that Tomcat has started on port `8080`.
4. Open a browser and try the URLs in the [Examples](#examples) table.
5. To see the error pages, visit a misspelled URL and a lotto URL with a non-number. Your browser's developer tools (Network tab) will show the real 404 and 400 status codes.
6. Press `Ctrl + C` in the terminal to stop the server.