# 01 — My First Java Program

A "Hello, World!" starting point — covers basic program structure,
variables, and simple output formatting.

## Concepts covered
- Basic class and `main` method structure
- Declaring variables (`String`, `boolean`, `int`)
- `System.out.println` vs `System.out.printf`
- String concatenation and format specifiers (`%s`, `%d`)

## Code

```java
package com.learn.hello;

public class Main {
    public static void main(String[] args) {

        String myName = "Bri";
        boolean isCoder = true;
        int yearsCoding = 3;

        System.out.println("Hello, World! My name is " + myName + ".");
        System.out.printf("Am I a software engineer? %s\n", isCoder);
        System.out.printf("I've been coding for %d years.\n", yearsCoding);
    }
}
```

## Output

```
Hello, World! My name is Bri.
Am I a software engineer? true
I've been coding for 3 years.
```

## Run it

```bash
cd 01-java-fundamentals/01-first-java
mvn compile exec:java -Dexec.mainClass="com.learn.hello.Main"
```

## Notes
- `println` auto-adds a newline; `printf` requires `\n` explicitly.
- `%s` works for any object (calls `.toString()`), `%d` is integer-only.