# java-notebook — Multi-Module Project Setup Guide

A reference for how this repo is structured and how to add new phases/topics going forward.

---

## Structure

```
java-notebook/                     <-- Root Maven project (packaging: pom)
│   pom.xml                        <-- Parent POM, lists phase modules
│   README.md
│
├── 01-java-fundamentals/          <-- Phase aggregator (packaging: pom)
│   │   pom.xml                    <-- Lists topic modules
│   │
│   ├── 01-first-java/             <-- Real module (packaging: jar)
│   │   │   pom.xml
│   │   │   README.md
│   │   └── src/main/java/...
│   │
│   └── 02-variables-and-types/
│       └── ...
│
└── 02-java-oop/
    └── ...
```

Three levels, three roles:
- **Root** (`java-notebook`) — owns Git, shared settings. No code.
- **Phase** (e.g. `01-java-fundamentals`) — groups related topics. No code.
- **Topic** (e.g. `01-first-java`) — actual Java code lives here.

---

## Root `pom.xml` template

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.learn</groupId>
    <artifactId>java-notebook</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>pom</packaging>

    <properties>
        <maven.compiler.source>20</maven.compiler.source>
        <maven.compiler.target>20</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <modules>
        <!-- phase modules get added here automatically by IntelliJ -->
    </modules>

</project>
```

## Phase `pom.xml` template (aggregator — no code)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.learn</groupId>
        <artifactId>java-notebook</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <artifactId>01-java-fundamentals</artifactId>
    <packaging>pom</packaging>

    <modules>
        <!-- topic modules get added here automatically by IntelliJ -->
    </modules>

</project>
```

## Topic `pom.xml` template (real code)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.learn</groupId>
        <artifactId>01-java-fundamentals</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <artifactId>01-first-java</artifactId>
    <packaging>jar</packaging>

</project>
```
No `<modules>` block here — a topic module is a leaf, not an aggregator.

---

## Steps to add a new phase

1. Right-click `java-notebook` → **New → Module**
2. Select **Java** on the left (not "Maven Archetype" under Generators) and **Maven** as the build system
3. Set artifactId (e.g. `02-java-oop`), confirm Parent is `java-notebook`
4. Leave **Add sample code** unchecked
5. Open the generated `pom.xml`, add `<packaging>pom</packaging>` and an empty `<modules></modules>` block
6. Delete the generated `src/` folder — this level holds no code

## Steps to add a new topic inside a phase

1. Right-click the phase folder (e.g. `01-java-fundamentals`) → **New → Module**
2. Select **Java** + **Maven**, artifactId (e.g. `02-variables-and-types`), confirm Parent is the phase module
3. Leave **Add sample code** unchecked
4. Keep `packaging=jar` and the generated `src/main/java` — this is where code goes
5. Right-click `java` folder → **New → Package** (e.g. `com.learn.variables`)
6. Right-click the package → **New → Java Class** → `Main`
7. Add a short `README.md` in the topic folder describing what it covers

---

## Known pitfalls

**`<packaging>` placed inside `<parent>` throws an error.**
`<parent>` only accepts `groupId`, `artifactId`, `version`, `relativePath`. `<packaging>` must be a sibling of `<parent>`, describing the current module — not nested inside it.

**Module created with "Maven Archetype" generator instead of plain Maven.**
If IntelliJ's New Module dialog defaults to the Generators section and picks an archetype like `maven-archetype-archetype`, you'll get a strange structure (`archetype-resources`, `App.java`, `archetype.xml`, `META-INF.maven`) meant for building archetypes, not writing code. Fix: delete the module and recreate it, explicitly choosing **Java** (not an archetype) + **Maven** as the build system.

---

## Verify the whole reactor builds

From the root, in the Maven tool window: **Reload All Maven Projects**, then run `mvn clean install`. If every child correctly lists its parent, the whole tree compiles as one build.