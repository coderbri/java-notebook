# Java Notebook 📓

A running record of my Java learning journey — from fundamentals through
progressively more advanced projects. Structured as a multi-module Maven
repo so each concept lives in its own self-contained, runnable module while
still building on everything before it.

## Structure

This repo is organized into **phases**, each containing focused **topics**:

```
java-notebook/
├── 01-java-fundamentals/
│   ├── 01-first-java/
│   ├── 02-cafe-java/
│   ├── 03-alfred-bot/
│   ├── 04-cafe-business-logic/
│   ├── 05-puzzling/
│   ├── 06-map-of-hashmatique/
│   ├── 07-list-of-exceptions/
│   └── 08-numbers-game/
├── 02-java-oop/     (up next)
├── notes/
│   ├── 01-variables-and-datatypes.md
│   ├── 02-modularization.md
│   ├── 03-loops-and-data-structures.md
│   ├── 04-exceptions.md
│   └── 05-input-and-control-flow.md
├── setup-guide.md
└── CHANGELOG.md
```

Each topic module has its own `README.md` with notes on what it covers.
The `notes/` folder holds plain markdown syntax references pulled from
lecture material — not runnable code, just quick lookups when a syntax
detail slips my mind mid-project.
See [`setup-guide.md`](./setup-guide.md) for how the project is wired
together and how new modules get added.

## Phases

| Phase                                            | Status | Focus |
|--------------------------------------------------|---|---|
| [01 — Java Fundamentals](./01-java-fundamentals) | ✅ Complete | Syntax, methods, collections, input handling, and exceptions across eight projects |
| 02 — Java Object-Oriented Programming            | 🚧 Up next | Classes, objects, and the core OOP principles |

*(This table grows as new phases are added.)*

## Running a module

Each topic is a standalone Maven module and can be run independently:

```bash
cd 01-java-fundamentals/01-first-java
mvn compile exec:java -Dexec.mainClass="com.learn.hello.Main"
```

Each module's README lists its exact command. You can also open the whole repo in IntelliJ and run any module's main class directly.

**Requirements:** JDK 17 or later (developed with JDK 20) and Maven.

## Why this exists

This repo doubles as both a study log and a portfolio artifact — a place to
revisit fundamentals, track progress over time, and eventually reference
concrete examples when applying for software engineering roles.

---
*Built and maintained by [coderbri](https://github.com/coderbri)*