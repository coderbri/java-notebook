# 18 Chéngyǔ: Routing Practice

A Spring Boot web app that delivers a line of motivation based on which URL path is typed into the browser. It is inspired by the *Daikichi (大吉)* challenge, where Daikichi means "excellent luck" or "great blessing." This version uses **chéngyǔ (成语)**, Chinese idioms and proverbs, for yesterday, today, and tomorrow.

> - **Phase:** `03-java-spring`
> - **Package:** `org.learn.routingpractice` 
> - **Stack:** Java, Spring Boot, Maven, Spring Web

---

## Table of Contents

- [Overview](#overview)
- [Routes](#routes)
- [Logic Flow](#logic-flow)
- [Concepts Implemented](#concepts-implemented)
- [Expected Output](#expected-output)
- [Steps to Run](#steps-to-run)

---

## Overview

The goal of this project is to practice **routing in Spring Boot**, specifically writing method-level `@RequestMapping` annotations underneath a class-level `@RequestMapping`.

The `MainController` class is a REST controller responsible for handling incoming HTTP requests and returning the appropriate response. Responses are simple text (the welcome page uses light HTML), with no templates or front end.

Each chéngyǔ response follows the same format:

```
[Day]'s chengyu: “[chinese chengyu]。” （pīnyīn）— [translation]. [modern applicable meaning].
```

The project was built with **Spring Web** as its only dependency, which brings in Spring MVC and an embedded Apache Tomcat server.

---

## Routes

| Path | Method | Description |
|---|---|---|
| `/` | GET | Redirects to `/chengyu` so the root URL never shows an error page |
| `/chengyu` | GET | Welcome page listing the available routes |
| `/chengyu/yesterday` | GET | Reflection and resilience: 百折不挠 |
| `/chengyu/today` | GET | Patience and perseverance: 守得云开见月明 |
| `/chengyu/tomorrow` | GET | Hope and possibility: 前程似锦 |
| `/chengyu/random` | GET | A random pick from a pool of three extra chéngyǔ |

---

## Logic Flow

1. **Start-up.** `@SpringBootApplication` triggers component scanning, which finds `MainController` and registers its routes.
2. **Request.** The browser sends a GET request to a URL such as `localhost:8080/chengyu/today`.
3. **Routing.** Spring MVC combines the class-level path (`/chengyu`) with each method-level path (`/today`) and finds the matching handler method.
4. **Response.** The handler returns a `String`, which `@RestController` writes directly to the response body.
5. **Root requests.** A request for `/` is redirected (HTTP 302) to `/chengyu` by a view controller registered in the Application class.
6. **Random route.** `/chengyu/random` picks a random index from a fixed list and returns that entry.

```
Browser → Tomcat → Spring MVC → MainController method → String response
```

---

## Concepts Implemented

### `@SpringBootApplication`

The Application class is the entry point. This one annotation combines configuration, auto-configuration, and component scanning. Scanning only looks at the Application class's package and below, which is why `MainController` lives in a subpackage (`org.learn.routingpractice.controllers`) rather than anywhere else.

### `@RestController`

`@RestController` marks a class as a web controller whose return values are written straight to the HTTP response. Without it, Spring would treat a returned `String` as the name of a view template to look up. This is also why `"redirect:/..."` strings don't work inside this controller.

### Class-level `@RequestMapping`

Placing `@RequestMapping("/chengyu")` on the class gives every route in `MainController` a shared prefix. This keeps method-level paths short (`/today` instead of `/chengyu/today`) and groups related routes in one place.

### Method-level `@RequestMapping`

Each handler method declares its own path and HTTP method:

```java
@RequestMapping(value = "/today", method = RequestMethod.GET)
```

A bare `@RequestMapping` accepts every HTTP method, so setting `method = RequestMethod.GET` makes the intent explicit and matches the challenge's requirement that these routes are GET requests. The welcome route uses an empty path (`""`), so it resolves to exactly `/chengyu`.

### Response content type

A `@RestController` returns plain text by default. The welcome route returns an HTML heading and list, so it sets `produces = MediaType.TEXT_HTML_VALUE` to make the browser render the tags instead of showing them as literal text.

### Redirecting the root URL

The Application class handles the root URL by implementing `WebMvcConfigurer` and overriding `addViewControllers`:

```java
registry.addRedirectViewController("/", "/chengyu");
```

This covers both `localhost:8080` and `localhost:8080/`, since browsers send both as `/`.

### Random selection with `List` and `Random`

The `/random` route keeps its pool of chéngyǔ in a `List<String>` created with `List.of(...)`, a fixed, read-only list that suits data that never changes at runtime. A shared `Random` instance picks an index with `nextInt(list.size())`, which always returns a valid position from `0` up to the last item. Using `size()` instead of a hardcoded number means more entries can be added without touching the route logic.

### UTF-8 text

Responses include Chinese characters and tone-marked pinyin. Spring Boot handles UTF-8 for these responses by default, so the text renders correctly as long as the source file is saved as UTF-8.

---

## Expected Output

**`localhost:8080` or `localhost:8080/`** redirects to `/chengyu`.

#### Welcome: **`localhost:8080/chengyu`**

> # Welcome to the Chéngyǔ Web App!
> ### Explore:
> 1. /chengyu/yesterday,
> 2. /chengyu/today, or
> 3. /chengyu/tomorrow.

#### Yesterday - **`localhost:8080/chengyu/yesterday`**

```
Yesterday's chengyu: “百折不挠。” （Bǎi zhé bù náo）— Though bent a hundred times, never yield. Every bug you fixed and every setback you pushed through is proof that you can keep going, and yesterday's struggles are today's strength.
```

#### Today - **`localhost:8080/chengyu/today`**

```
Today's chengyu: “守得云开见月明。” （Shǒu dé yún kāi jiàn yuè míng）— Stay until the clouds clear, and the moon appears. When the code won't compile or progress feels slow, keep showing up, because clarity often arrives right after the hardest stretch.
```

#### Tomorrow - **`localhost:8080/chengyu/tomorrow`**

```
Tomorrow's chengyu: “前程似锦。” （Qián chéng sì jǐn）— A future as bright as brocade. Every skill you build today is another thread in that brocade, so your coding journey is wide open.
```

#### Random - **`localhost:8080/chengyu/random`** returns one of the following, chosen at random:

```
Random chengyu: “水滴石穿。” （Shuǐ dī shí chuān）— Dripping water pierces stone. Small daily effort, like one commit or one practice problem a day, adds up to big results over time.

Random chengyu: “厚积薄发。” （Hòu jī bó fā）— Gather deeply, then release gradually. The skills you quietly build now will be ready when opportunity arrives, so keep learning.

Random chengyu: “乘风破浪。” （Chéng fēng pò làng）— Ride the wind and break the waves. Meet challenges head-on and use the momentum of change to move forward, even when the waters are rough.
```

---

## Steps to Run

1. Make sure JDK 20 and Maven are installed.
2. From this project's module directory, start the app:

   ```bash
   ./mvnw spring-boot:run
   ```

   (or `mvn spring-boot:run` if you are using a system-wide Maven install)

3. Wait for the console to show that Tomcat has started on port `8080`.
4. Open a browser and visit any route from the [Routes](#routes) table, starting with `http://localhost:8080`.
5. Refresh `http://localhost:8080/chengyu/random` a few times to see the different results.
6. Press `Ctrl + C` in the terminal to stop the server.

---

[← Back to Phase 03 • Java Spring](../README.md)