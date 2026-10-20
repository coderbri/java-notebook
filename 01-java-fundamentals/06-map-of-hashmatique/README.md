# 06 — Map of Hashmatique

A track list built with a `HashMap`, which stores song titles as keys and their lyrics as values. The project covers creating a map, adding and retrieving entries, and looping through a map by entry, all in a single file.

## Table of Contents
- [Overview](#overview)
- [Logic Flow](#logic-flow)
  - [Creating the HashMap](#creating-the-hashmap)
  - [Adding Songs](#adding-songs)
  - [Retrieving a Song](#retrieving-a-song)
  - [Printing the Track List](#printing-the-track-list)
- [Concepts Implemented](#concepts-implemented)
- [Expected Output](#expected-output)
- [Steps to Run](#steps-to-run)

## Overview
`Hashmatique.java` is the only file in this module. Its `main` method builds the track list, pulls out one song by title, and then calls `printTrackList` to display every entry. A `HashMap` stores **key-value pairs** and finds a value quickly by its key, which makes it a natural fit for looking up lyrics by track title.

## Logic Flow

### Creating the HashMap
```java
HashMap<String, String> trackList = new HashMap<String, String>();
```
The two type arguments set the key type (`String` track title) and the value type (`String` lyrics). The compiler enforces both, so putting an `int` in either slot is a compile error.

### Adding Songs
Each song is added with `.put(key, value)`, using the track title as the key. The project stores four tracks: *A Little Sweet*, *What You Mean to Me*, *Coming*, and *Firework*.

### Retrieving a Song
`trackList.get("Coming")` takes a key and returns its value, which is then printed with a `printf`. No loop is needed, because the map finds the entry directly from the key.

### Printing the Track List
`printTrackList(HashMap<String, String> trackList)` loops over `trackList.entrySet()`. Each pass of the loop gives one `Entry` object holding both halves of the pair, so the method calls `entry.getKey()` for the title and `entry.getValue()` for the lyrics and prints them as `Track: {title} — Lyrics: {lyrics}`.

## Concepts Implemented

**Key-value storage.** A `HashMap` pairs each key with exactly one value. `.put(key, value)` adds an entry, and `.get(key)` retrieves it. If `.put()` is called with a key that already exists, the old value is overwritten. If `.get()` is called with a key that doesn't exist, it returns `null` instead of throwing an error.

**Looping by entry instead of by key.** The common first approach is to loop over `keySet()` and call `.get(key)` inside the loop to fetch each value. That works, but it performs a second lookup for every key. Looping over `entrySet()` hands you the key and value together, so there is one step per entry. At this size the speed difference is negligible, but the code is cleaner and the habit scales to larger maps.

**Iteration order isn't guaranteed.** A `HashMap` doesn't remember insertion order. It arranges entries by the hash of each key, so the printed track list can come out in a different order than the songs were added (see the Expected Output). If order matters, `LinkedHashMap` keeps entries in the order they were inserted, and is a drop-in replacement for `HashMap`.

**Generics.** The `<String, String>` in the declaration is a pair of generic type arguments, the map equivalent of the `<String>` in `ArrayList<String>`. They are checked at compile time.

**Nested types and `Map.Entry`.** `Entry` is a type nested inside the `Map` interface, so its canonical name is `Map.Entry`. The code refers to it as `HashMap.Entry`, which also compiles because `HashMap` implements `Map`. Either works, and `Map.Entry` is the form you will see most often.

**Helper methods.** Printing is split into its own method, `printTrackList`, which keeps `main` focused on the steps (create, retrieve, print) and makes the printing reusable for any `HashMap<String, String>`.

## Expected Output

Lyrics are shown as placeholders here. In this example run, the entries print in this order, which is not the order they were added:

```
 > Pulled-out track: [lyrics for "Coming"]

Track: Coming — Lyrics: [lyrics for "Coming"]

Track: Firework — Lyrics: [lyrics for "Firework"]

Track: A Little Sweet — Lyrics: [lyrics for "A Little Sweet"]

Track: What You Mean to Me — Lyrics: [lyrics for "What You Mean to Me"]

```
The blank line after each track comes from the `\n` inside the format string plus the newline that `println` adds. The order depends on how Java hashes the titles, so treat it as an observation rather than a guarantee.

## Steps to Run

From this module's directory (`01-java-fundamentals/06-map-of-hashmatique`):

```bash
mvn compile exec:java -Dexec.mainClass="com.learn.hashmatique.Hashmatique"
```

No input is required.