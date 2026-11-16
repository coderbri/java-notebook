# Phase 03 • Java Spring

The third chapter of the [Java Notebook](../README.md). After fundamentals and OOP, this phase moves into **Spring Boot**: building web applications that respond to requests in the browser, starting with routing and controllers.

> **Status:** 🚧 In progress

---

## Projects

| # | Project                                           | Package                     | Focus                                                                    |
|---|---------------------------------------------------|-----------------------------|--------------------------------------------------------------------------|
| 18 | [Chéngyǔ: Routing Practice](./18-chengyu-routing) | `org.learn.routingpractice` | Class-level and method-level `@RequestMapping`, plus a root redirect     |
| 19 | [Hello Human](./19-hello-human)                   | `org.learn.hellohuman`      | `@RequestParam` query string parameters, one route with optional inputs  |

*(This table grows as new projects are added.)*

### 18 Chéngyǔ: Routing Practice

A Spring Boot project for practicing the foundations of RESTful API development, using **chéngyǔ (成语)**, Chinese idioms, as the content. Each URL path returns a line of motivation for yesterday, today, or tomorrow, plus a random pick, and the root URL redirects to the welcome page.

**Routes:** `/chengyu`, `/chengyu/yesterday`, `/chengyu/today`, `/chengyu/tomorrow`, `/chengyu/random`

### 19 Hello Human

A Spring Boot project for practicing query string parameters. A single `/hello` route greets "Human" by default, or a visitor by `name` and `last_name`, and repeats the greeting with a `times` parameter. Every parameter is an optional `@RequestParam`, so one handler covers every combination. The controller logic and the built-in methods it uses are explained in a standalone [`DOCUMENTATION.md`](./19-hello-human/DOCUMENTATION.md).

**Route:** `/hello` (query parameters: `name`, `last_name`, `times`)

---

## How the projects build on each other
- **18:** routing by *path*: mapping URLs to handler methods with class-level and method-level `@RequestMapping`.
- **19:** routing by *input*: reading optional query string values with `@RequestParam` and handling every combination in a single route.

---

## Running a Project

Spring Boot projects start an embedded Tomcat server, so they run differently from the plain Java modules in earlier phases:

```bash
cd 03-java-spring/<project-folder>
mvn spring-boot:run
```

Then open `http://localhost:8080` in a browser. Each project's README lists its routes and exact steps.

**Requirements:** JDK 17 or later (developed with JDK 20) and Maven.

---

[← Back to the Java Notebook](../README.md)