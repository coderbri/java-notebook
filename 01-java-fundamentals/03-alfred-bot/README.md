# 03 — Alfred Bot

Alfred is a virtual assistant that greets guests, announces the current date, and responds to snippets of conversation. This project practices method overloading, conditional/switch logic, string formatting, and encapsulation — split across a logic class (`AlfredQuotes`) and a test driver (`AlfredTest`).

## Table of Contents
- [Overview](#overview)
- [Logic Flow](#logic-flow)
    - [Guest Greeting](#guest-greeting)
    - [Date Announcement](#date-announcement)
    - [Respond to Conversation](#respond-to-conversation)
- [Concepts Implemented](#concepts-implemented)
- [Expected Output](#expected-output)
- [Steps to Run](#steps-to-run)

## Overview
`AlfredQuotes.java` holds all of Alfred's behavior — greetings, date formatting, conversation responses — and exposes it as a public API. `AlfredTest.java` is the driver: it creates one `AlfredQuotes` instance and exercises every feature through three self-contained test methods (`testGuestGreetings`, `testDateAnnouncement`, `testConversationResponses`), all called in sequence from `main`. Splitting logic from testing this way means `AlfredQuotes` could be reused elsewhere without dragging along any test/console code.

## Logic Flow

### Guest Greeting
Three overloaded `guestGreeting()` methods, from simplest to most dynamic:
- No-arg → returns a generic greeting via the private `basicGreeting()` helper
- `(name)` → auto-detects morning/afternoon/evening from the system clock (`Calendar`) and greets the guest by name
- `(dayPeriod, name)` → skips detection entirely and uses whatever day period the caller supplies

### Date Announcement
Two ways to report the current date/time:
- `dateAnnouncement()` — default `Date` formatting
- `dateAnnouncementSimplified()` — `SimpleDateFormat` with a custom pattern, fixed to EST via `TimeZone`

`AlfredTest` asks the user which format they want and picks the method with a `switch` statement.

### Respond to Conversation
`respondToConversation()` scans the input string with `.contains()` and returns one of three canned replies depending on whether "Alexis" or "Alfred" appears — otherwise falls back to a neutral response.

## Concepts Implemented

1. **Method overloading.** `guestGreeting()` is defined three separate times with different parameter lists (no args, one `String`, two `String`s). Java picks which version to run based on the arguments passed at the call site — same method name, different behavior depending on what's provided.

2. **Encapsulation.** `basicGreeting()` is `private` because it's an implementation detail `guestGreeting()` relies on internally — nothing outside the class has a legitimate reason to call it directly. Every method meant to be part of Alfred's public "interface" (`guestGreeting`, `dateAnnouncement`, `respondToConversation`) is `public` instead.

3. **`static` fields and methods.** `alfredBot` and `scanner` are declared `static` in `AlfredTest` so every test method can reach them without passing them around as parameters. `main` itself must be `static` because the JVM calls it before any object exists — anything `main` calls directly needs to be `static` for the same reason.

4. **`switch` vs. `if/else`.** The date-format choice ("regular" vs. "simplified") is a small, fixed set of known options, which is exactly the case a `switch` statement reads more clearly than a chain of `if/else` — especially if more formats get added later.

5. **String formatting.** `String.format()` builds each response by substituting values into a template (`%s` for strings, `%d` for integers) rather than concatenating pieces with `+`.

6. **Date/time handling.** `Calendar` reads the current hour to auto-detect morning/afternoon/evening. `Date` captures the current instant. `SimpleDateFormat` + `TimeZone` reformat that instant into a custom, human-readable pattern fixed to EST.

7. **Console input via `Scanner`.** User input (name, date-format choice) is read with `Scanner(System.in)` rather than `System.console()`, since `System.console()` returns `null` when run through an IDE instead of a real terminal.

## Expected Output

```
======= GUEST GREETING TEST =======

Hello, lovely to see you. How are you?
Good evening, Ava Lovelace. How can I assist you today?

To whom do I have the honor of assisting? Bri
Good morning, Bri. How can I assist you today?

===== DATE ANNOUNCEMENT TEST ======
How would you like to know the date? (regular/simplified) simplified
It is currently Monday, July 21, 2026 and 03:45 PM EST.

=== CONVERSATION RESPONSE TEST ====
Right way. She certainly isn't sophisticated enough for that.
At your service. As you wish, naturally.
Right. And with that, I shall retire.
```
*(Exact greeting/date text will vary based on current system time and console input.)*

## Steps to Run

From this module's directory (`01-java-fundamentals/03-alfred-bot`):

```bash
mvn compile exec:java -Dexec.mainClass="com.learn.alfredbot.AlfredTest"
```

Follow the on-screen prompts to enter your name and pick a date format.

---
<section align="center">
  <code>coderBri © 2026</code>
</section>