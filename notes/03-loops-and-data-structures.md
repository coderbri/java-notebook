# Loops & Data Structures

Reference notes from Intro to Java lecture (Day 3) covering loop
constructs, fixed-size arrays, `ArrayList`, and `HashMap`.

## For Loop
```java
for (int i = 0; i < 10; i++) {
        System.out.println(i);
}
```
Three parts, separated by `;`:
1. **Initialization** — `int i = 0` (runs once, before the loop starts)
2. **Condition** — `i < 10` (checked before every iteration; loop stops once false)
3. **Increment** — `i++` (runs after every iteration)

> **In practice:** `04-cafe-business-logic/getOrderTotal()` and `printPriceChart()` both use this exact loop shape to accumulate a running total across an array/quantity range.

## While Loop
```java
int num = 1;
while (num < 10) {
    System.out.println("Loop " + num);
    num++;
}
```
Only has a condition — initialization happens before the loop, and
incrementing must be done manually inside the loop body. Useful when the
number of iterations isn't known ahead of time (unlike a `for` loop,
which is a natural fit when it is).

> **In practice:** `04-cafe-business-logic/addCustomers()` uses `while (true) { ... break; }` — a variant where the loop runs indefinitely until a sentinel value (`"q"`) is entered, rather than a pre-known condition.

## Fixed Arrays
```java
int[] myArray = new int[6]; // all elements default to 0
```
- Declared with a fixed size — `myArray` always holds exactly 6 `int`s, can't grow or shrink
- Access/change a value by index: `myArray[1] = 2;`
- Indexes start at `0`

```java
for (int value : myArray) {
    System.out.println(value);
}
```
This is a **for-each loop** — iterates over every element directly
without needing an index variable. Good when you just need each value,
not its position.

```java
int[] newArray = {1, 2, 3, 4, 5, 6}; // declare + initialize in one line
for (int i = 0; i < newArray.length; i++) {
    System.out.println(newArray[i]);
}
```
`.length` (no parentheses — it's a field, not a method) gives an array's
fixed size.

## ArrayList
```java
import java.util.ArrayList;

ArrayList<String> names = new ArrayList<String>();
names.add("하나");
names.add("둘");
names.add("셋");
names.add("넷");
System.out.println(names); // [하나, 둘, 셋, 넷]

names.remove(1); // removes the element AT index 1 ("둘")
System.out.println(names); // [하나, 셋, 넷]
System.out.println("Size: " + names.size()); // 3
```
Unlike a fixed array, `ArrayList` can grow and shrink — `.add()` appends,
`.remove(index)` deletes by position, `.size()` gives the current count.
The `<String>` is a **generic type** — it locks the list to holding only
`String` elements, checked at compile time.

> **In practice:** `04-cafe-business-logic` uses `ArrayList<String>` for both menu items and the customer waitlist, and `ArrayList<Double>` for menu prices — `.get(index)` and `.size()` drive the display/table-printing loops throughout `CafeUtil`.

## HashMap
```java
import java.util.HashMap;
import java.util.Set;

HashMap<String, String> person = new HashMap<String, String>();
person.put("firstName", "Jane");
person.put("lastName", "Doe");
person.put("DOB", "March 18th");
System.out.println(person); // {firstName=Jane, lastName=Doe, DOB=March 18th}

System.out.println(person.get("firstName")); // Jane

Set<String> allKeys = person.keySet();
for (String key : allKeys) {
String value = person.get(key);
    System.out.printf("%s = %s\n", key, value);
}
```
- `HashMap<KeyType, ValueType>` stores key-value pairs — `.put(key, value)` adds/updates, `.get(key)` retrieves
- `.keySet()` returns a `Set` of all keys (a `Set` holds only unique values, no duplicates), which you can loop over to reach every key and its value

## Application: Looping Over the Alphabet
```java
for (char i = 'a'; i <= 'z'; i++) {
        System.out.println(i);
}
```
`char` values can be used in a numeric `for` loop the same way `int` can
— Java treats characters as small integers under the hood (each letter
has a numeric code), so `i++` moves to the next letter and `i <= 'z'`
compares them directly.

```java
ArrayList<Character> alphabetList = new ArrayList<Character>();
for (char i = 'a'; i <= 'z'; i++) {
        alphabetList.add(i);
}
```
Same loop, but collecting each character into an `ArrayList<Character>`
instead of just printing it — useful once you need to reuse or pass
around the full collection rather than just displaying it once.