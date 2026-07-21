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
│   ├── 02-variables-and-types/
│   └── ...
└── 02-object-oriented-programming/
    └── ...
```

Each topic module has its own `README.md` with notes on what it covers.
See [`setup-guide.md`](./setup-guide.md) for how the project is wired
together and how new modules get added.

## Phases

| Phase | Focus |
|---|---|
| [01 — Java Fundamentals](./01-java-fundamentals) | Syntax, variables, control flow, core language basics |
| 02 — Object-Oriented Programming | *coming soon* |

*(This table grows as new phases are added.)*

## Running a module

Each topic is a standalone Maven module and can be run independently:

```bash
cd 01-java-fundamentals/01-first-java
mvn compile exec:java
```

Or open the whole repo in IntelliJ and run any module's `Main.java` directly.

## Why this exists

This repo doubles as both a study log and a portfolio artifact — a place to
revisit fundamentals, track progress over time, and eventually reference
concrete examples when applying for software engineering roles.

---
<section align="center">
  <code>coderBri © 2026</code>
</section>