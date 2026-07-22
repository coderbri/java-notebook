# Project Context: java-notebook

> Paste this file's contents into a new chat to re-establish context quickly.

## What this is
A multi-module Maven repo tracking my Java learning journey — fundamentals
through progressively harder projects. Doubles as a study log and a
portfolio artifact. Full explanation lives in the root `README.md`;
architecture/setup details live in `setup-guide.md`.

## Conventions (established — don't relitigate these)
- Root groupId: `org.learn`
- Root artifactId: `java-notebook`
- Phase folders: `NN-phase-name` (e.g. `01-java-fundamentals`), packaging `pom`, no code
- Topic folders: `NN-topic-name` (e.g. `01-first-java`), packaging `jar`, real code lives here
- Every topic module gets its own `README.md`
- New modules created via IntelliJ: **Java + Maven** (NOT "Maven Archetype" generator — caused issues before)
- JDK: 20 (Homebrew OpenJDK, aarch64)

## Current state
*(update this section as you go — this is the part that goes stale fastest)*
- Last completed: `01-java-fundamentals/02-cafe-java`
- Currently working on: —
- Next planned topic/phase: —

## Known issues / things to remember
- `<packaging>` must be a sibling of `<parent>` in a module's pom.xml, never nested inside `<parent>`
- If IntelliJ generates `archetype-resources`/`App.java`/weird structure, the archetype generator was used by mistake — delete and recreate as plain Java+Maven module

## Open questions / not yet decided
*(things you're still figuring out — delete once resolved)*
-