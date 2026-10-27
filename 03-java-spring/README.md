# Phase 03 • Java Spring

The third chapter of the [Java Notebook](../README.md). After fundamentals and OOP, this phase moves into **Spring Boot**: building web applications that respond to requests in the browser, starting with routing and controllers.

> **Status:** 🚧 In progress

---

## Projects

| # | Project                                 | Package | Focus |
|---|-----------------------------------------|---|---|
| 18 | [Chéngyǔ: Routing Practice](./18-chengyu-routing) | `org.learn.routingpractice` | Class-level and method-level `@RequestMapping`, plus a root redirect |

*(This table grows as new projects are added.)*

### 18 Chéngyǔ: Routing Practice

A Spring Boot app inspired by the *Daikichi (大吉)* "great blessing" challenge, reimagined with **chéngyǔ (成语)**, Chinese idioms. Each URL path returns a line of motivation for yesterday, today, or tomorrow, plus a random pick, and the root URL redirects to the welcome page.

**Routes:** `/chengyu`, `/chengyu/yesterday`, `/chengyu/today`, `/chengyu/tomorrow`, `/chengyu/random`

---

## Running a Project

Spring Boot projects start an embedded Tomcat server, so they run differently from the plain Java modules in earlier phases:

```bash
cd 03-java-spring/18-chengyu-routing
mvn spring-boot:run
```

Then open `http://localhost:8080` in a browser. Each project's README lists its routes and exact steps.

**Requirements:** JDK 17 or later (developed with JDK 20) and Maven.

---

[← Back to the Java Notebook](../README.md)