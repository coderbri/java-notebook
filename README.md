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
│   └── 03-alfred-bot/
├── 02-object-oriented-programming/
└── notes/
    ├── 01-variables-and-datatypes.md
    ├── 02-modularization.md
    └── 03-loops-and-data-structures.md
```

Each topic module has its own `README.md` with notes on what it covers.
The `notes/` folder holds plain markdown syntax references pulled from
lecture material — not runnable code, just quick lookups when a syntax
detail slips my mind mid-project.
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
    <p>Built and maintained by <code>coderBri © 2026</code></p>
</section>