# Java Notebook • Changelog

## October 22, 2026
- Started phase `02-java-oop`
- Completed `02-java-oop/09-orders-and-items` — first object-oriented project (`Item`, `Order`, and the `TestOrders` driver)
- Updated `README.md`, `setup-guide.md`, and `CLAUDE.md` to use the Phase 02 folder name `02-java-oop`
- Completed `02-java-oop/10-baristas-challenge` (`com.learn.baristachallenge`):
  - Refactored `Item` and `Order` to use private member variables, constructors, and getters/setters
  - Added an overloaded `Order` constructor (`"Guest"` default) and moved the business logic into `addItem`, `getStatusMessage`, `getOrderTotal`, and `displayReceipt`
  - Removed the stored `total` field in favor of a calculated `getOrderTotal()`
  - Added a module `README.md` with expected output and JavaDoc comments across all three classes

---

## October 20, 2026
- Completed `01-java-fundamentals/08-numbers-game`
- Completed phase `01-java-fundamentals`
- Added `notes/05-input-and-control-flow.md` — `Scanner` vs `System.console()`, `static final` constants, extracting methods, input validation, and ordering conditionals
- Updated `notes/04-exceptions.md` to include a reference to the project `08-numbers-game` (`try/catch` around `Integer.parseInt`)
- Added `01-java-fundamentals/README.md` — phase overview linking each project with a brief and its key concepts
- Updated root `README.md` — full Phase 01 structure, all five notes, phase status table, and a corrected run command

---

## October 19, 2026
- Completed `01-java-fundamentals/06-map-of-hashmatique`
- Completed `01-java-fundamentals/07-list-of-exceptions`
- Updated documentation
  - Updated `notes/03-loops-and-data-structures.md` to include a reference to the project `06-map-of-hashmatique` (HashMap iteration with `entrySet()` and unordered iteration)
  - Added `notes/04-exceptions.md` — try/catch, checked vs unchecked exceptions, and when to prefer `instanceof`

---

## October 18, 2026
- Completed `01-java-fundamentals/05-puzzling`

---

## July 27, 2026
- Completed `01-java-fundamentals/04-cafe-business-logic`
- Updated `notes/03-loops-and-data-structures.md` to include references to the project `04-cafe-business-logic`

---

## July 27, 2026
- Updated `claude.md`
- Started `notes/` — plain markdown syntax reference, separate from Maven modules
  - `01-variables-and-datatypes.md`
  - `02-modularization.md`
  - `03-loops-and-data-structures.md`

---

## July 23, 2026
- Completed `01-java-fundamentals/03-alfred-bot`
- Updated `claude.md`

---

## July 21, 2026
- Completed `01-java-fundamentals/02-cafe-java`

---

## July 20, 2026
- Set up multi-module Maven structure (java-notebook root, phase/topic pattern)
- Completed `01-java-fundamentals/01-first-java`
- Fixed archetype-generator mistake in module creation