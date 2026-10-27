# Java Notebook • Changelog

## October 26, 2026

- Started phase `03-java-spring` and completed `03-java-spring/18-chengyu-routing` (`org.learn.routingpractice`):
  - Created a Spring Boot + Maven project with Spring Web as the only dependency
  - Built `MainController` as a `@RestController` with a class-level `@RequestMapping("/chengyu")` and method-level `@RequestMapping` routes for the welcome page, `/yesterday`, `/today`, and `/tomorrow`
  - Set `method = RequestMethod.GET` on each route and `produces = MediaType.TEXT_HTML_VALUE` on the HTML welcome page
  - Wrote a chéngyǔ line for each day in the format: Chinese idiom, pīnyīn, translation, and a modern encouraging meaning
  - Added a `/chengyu/random` route that picks from a `List.of(...)` pool using `Random.nextInt(list.size())`
  - Redirected `/` to `/chengyu` by implementing `WebMvcConfigurer` in the Application class, so the root URL never shows an error page
  - Added a module `README.md` with routes, logic flow, and expected output
- Added phase READMEs for `02-java-oop` and `03-java-spring`, following the `01-java-fundamentals` format (project table, how projects build on each other, reference notes, run instructions)
- Updated the phase 01 README to the same `Phase 0X •` title format, with a status line and a back-link footer
- Updated the root `README.md`: added phase 03 to the structure tree and phases table, added `notes/06-encapsulation-and-access-modifiers.md` to the tree, and added a Spring Boot run command alongside the plain Java one
- Listed `12 Coffeedore 64` in the phase 02 project table as an upcoming project
---

## October 23, 2026

- Completed `02-java-oop/11-bank-account` (`com.learn.bankaccount`):
  - Built `BankAccount` with private checking/savings balances, a `final` random ten-digit account number, and private static trackers for account count and total money
  - Added `deposit` and `withdraw` with validation for account type, amount, and sufficient funds, using a shared `isInvalidTransaction` helper
  - Added `getCombinedBalance()` and a boxed `displayAccountBalance()`
  - Built `TestBankAccount` covering every method plus edge cases and a tracker cross-check
  - Added a module `README.md` with expected output and JavaDoc comments across both classes
- Added `notes/06-encapsulation-and-access-modifiers.md` covering when to use `private` vs `public` for fields, static members, and methods

---

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