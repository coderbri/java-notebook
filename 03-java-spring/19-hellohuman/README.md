# 19 Hello Human

A Spring Boot project for practicing the foundations of **RESTful API development** and learning how HTTP requests work. A URL typed into the browser sends a GET request to a Spring controller, which reads **query string parameters** from the URL and returns a personalized greeting. With no name provided, it greets "Human."

> - **Phase:** `03-java-spring`
> - **Package:** `org.learn.hellohuman`
> - **Stack:** Java, Spring Boot, Maven, Spring Web

For a line-by-line explanation of the controller logic and the built-in methods it uses, see [`DOCUMENTATION.md`](./DOCUMENTATION.md).

---

## Table of Contents

- [Routes](#routes)
- [Logic Flow](#logic-flow)
- [Concepts Implemented](#concepts-implemented)
- [Steps to Run](#steps-to-run)

---

## Routes

| Path | Method | Description |
|---|---|---|
| `/` | GET | Redirects to `/hello`, keeping any query parameters |
| `/hello` | GET | Returns a greeting built from the optional query parameters below |

### Query parameters for `/hello`

| Parameter | Required | Description |
|---|---|---|
| `name` | No | First name to greet. Defaults to "Human" |
| `last_name` | No | Last name, printed after the first name |
| `times` | No | How many times to repeat the greeting. Defaults to 1 and is capped at 100 |

### Examples

| URL | Response |
|---|---|
| `localhost:8080/hello` | `Hello, Human` |
| `localhost:8080/hello?name=Ava` | `Hello, Ava` |
| `localhost:8080/hello?name=Ava&last_name=Lovelace` | `Hello, Ava Lovelace` |
| `localhost:8080/hello?name=Ava&times=3` | `Hello, Ava` repeated on three lines |
| `localhost:8080/hello?name=Ava&last_name=Lovelace&times=2` | `Hello, Ava Lovelace` repeated on two lines |
| `localhost:8080/?name=Ava` | Redirects to `/hello?name=Ava` |

---

## Logic Flow

1. **Start-up.** `@SpringBootApplication` scans for components, finds `GreetingController`, and registers its `/hello` route.
2. **Request.** The browser sends a GET request such as `localhost:8080/hello?name=Ava&times=2`. The path is `/hello` and the query string is `name=Ava&times=2`.
3. **Routing.** Spring matches the path to the single handler method for GET `/hello`.
4. **Parameter binding.** Each `@RequestParam` looks up its key in the query string and converts the text to the parameter's type. Any key that is missing arrives as `null`.
5. **Name.** The controller combines the first and last names, falling back to "Human" when neither has content.
6. **Repeat.** The controller decides how many times to repeat the greeting, using 1 when `times` is missing or less than 1 and capping it at 100.
7. **Response.** The finished text is returned as plain text, one greeting per line.

```
Browser → Tomcat → Spring MVC → GreetingController.hello() → plain-text response
```

---

## Concepts Implemented

### `@SpringBootApplication` and package layout

The `Application` class sits at the top of the package tree (`org.learn.hellohuman`), and the controller lives in a subpackage (`org.learn.hellohuman.controllers`). Component scanning only looks at the Application class's package and below, so this layout lets Spring find the controller automatically.

### `@RestController` and `@RequestMapping`

`@RestController` writes a method's return value straight to the HTTP response. A class-level `@RequestMapping("/hello")` gives the controller its base path, and a method-level `@RequestMapping(value = "", method = RequestMethod.GET)` maps the handler to exactly `/hello` for GET requests.

### Query strings and `@RequestParam`

The part of a URL after `?` is the query string, a list of `key=value` pairs joined by `&`. `@RequestParam` binds one of those keys to a method parameter. With `required = false`, a missing key arrives as `null` instead of causing an error, and Spring converts the text to the parameter's type, such as `Integer` for `times`.

### One route with optional parameters

All four requirements (default, name, last name, repeat count) share the same path, so a single handler takes every parameter as optional and decides what to do based on which ones arrived. Several methods mapped to the same path and HTTP method would look identical to Spring and cause an "Ambiguous mapping" error at startup. Query parameters are not part of a route's identity.

### Plain-text responses

The handler sets `produces = MediaType.TEXT_PLAIN_VALUE`, so line breaks between repeated greetings display as real line breaks, and input like `<b>Ava</b>` is shown as text instead of being interpreted as HTML.

### Redirecting the root URL

The Application class implements `WebMvcConfigurer` and registers a redirect from `/` to `/hello`. Calling `setKeepQueryParams(true)` on it carries the query string through, so `localhost:8080/?name=Ava` still greets Ava.

### Defensive input handling

The controller treats blank names as missing, trims extra spaces, and clamps `times` to a safe range so unusual input never crashes the app or floods the response.

> The `hello` and `buildName` methods, along with every built-in method they rely on, are explained step by step in [`DOCUMENTATION.md`](./DOCUMENTATION.md).

---

## Steps to Run

1. Make sure JDK 17 or later and Maven are installed.
2. From this project's directory, start the app:

   ```bash
   mvn spring-boot:run
   ```

3. Wait for the console to show that Tomcat has started on port `8080`.
4. Open a browser and try the URLs in the [Examples](#examples) table.
5. Press `Ctrl + C` in the terminal to stop the server.