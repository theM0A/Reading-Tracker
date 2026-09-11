# File Map

Every file and folder in this project, one line on what it is and why it exists.

**The rule: nothing in my repo is a mystery box.** If a file appears — because I
made it, because a tool generated it, because a tutorial said to — it gets an entry
here before I move on. A file I can't explain is a gap in my understanding sitting
in plain sight.

**Last updated:** 2026-09-10 (Section 3 phase-boundary checkpoint)

---

## Markers

| Marker | Means |
|---|---|
| `known` | I explained what this is and why it exists, in my own words. |
| `parked` | Honest one-liner for now, real understanding scheduled. Not a permanent excuse — every parked entry names when it gets resolved. |
| `generated` | Machine-made. Never hand-edit. I should still know what produces it and what it's for. |

A `parked` entry without a scheduled deep dive is just a mystery box wearing a
label. Every one has a "resolve by" note.

---

## Current Files

### `learning/`

Documentation for how I'm learning, not part of the application. Lives in the repo
so it's versioned alongside the code and survives across sessions.

| Path | Marker | What it is and why it exists |
|---|---|---|
| `learning/` | known | Folder holding the project's memory. Separate from application code because it's about *me*, not the app. Every session starts here. |
| `learning/project.md` | known | Who I am, my experience level, the project idea, the MVP scope, the parking lot, and the eight core components. The stable "what and why" — changes rarely. |
| `learning/plan.md` | known | The five locked technical decisions with reasoning and rejected alternatives, plus the nine build sections. The "how and in what order" — changes as the plan evolves. |
| `learning/knowledge-graph.md` | known | Status of every concept this project teaches, from `seed` to `understood`. Decides what I get quizzed on. Updated after every lesson. |
| `learning/file-map.md` | known | This file. Keeps every other file explainable. |
| `learning/study-method.md` | known | Written by me on 2026-09-06: daily working rhythm (cold-attempt first, recall before the next task), the stuck-timer guardrail, phase-boundary rebuild/teach-back checkpoints, and the 4-step Feynman recipe. Now folded into `CLAUDE.md`'s protocols so Claude Code actually holds me to it. |

### Project root (Section 1)

| Path | Marker | What it is and why it exists |
|---|---|---|
| `.gitignore` | known | Tells Git which files to never track. Written line by line in task 1.2: `files.zip` (a redundant leftover, kept but excluded), `.idea/` (personal IDE workspace state, not shared history), `*.class` (compiled bytecode — a regeneratable byproduct of `.java` source, not the source itself). |
| `README.md` | known | Plain-language intro to the project, written by hand in task 1.2. |
| `.idea/` | generated | IntelliJ's own project metadata (window layout, module config) — created automatically the moment the folder was opened as an IntelliJ project. Machine/personal state, not shared — hence it's in `.gitignore`. |
| `.git/` | generated | Git's internal database — every commit, the staging area, branch info. Created empty by `git init` in task 1.2; nothing was tracked automatically, tracking required a separate `git add` step. Never touched by hand. |
| `src/main/java/` | known | Where Java source lives — Maven convention, adopted early even before Maven's installed, so the structure doesn't need to change later. |
| `src/main/java/Main.java` | known | Entry point. Task 1.3: written by hand, printing "Hello Reader." Task 2.5: builds three `Book` objects, stores them in an `ArrayList<Book>`, and prints the whole list. Task 3.1: added a `Scanner` and a `while` loop printing a numbered menu and reading a choice each pass, exiting cleanly on 6. Task 3.2: wired option 1 (View) to the real `book` `ArrayList` — an empty-list check and an indexed `for` loop printing each book numbered. Task 3.3: wired option 2 (Add) — three validation `while` loops collecting title/author/totalPages, then `new Book(...)` added to the list; also consumes the leftover newline after the menu's `nextInt()` to avoid the classic double-prompt bug. Task 3.4: wired option 3 (Update current page) — numbered book list via `getTitle()`, a validation `while` loop for the chosen book number, current-page display via getters, a new-page prompt with its own validation `while` loop against `0` and `getTotalPages()`, then `setCurrentPage()` and a printed confirmation. Every list-index usage subtracts 1 from the displayed (1-based) book number to reach the real (0-based) `ArrayList` index. Task 3.5: wired option 4 (Change Status) — reuses 3.4's book-selection pattern, then a target-status submenu built by looping `BookStatus.values()` (self-taught, not previously covered) instead of hardcoding four print lines. Maps the chosen number to a target status via an `if/else if` chain checked against the (current status, target status) pair from 0.3's legal-move list; the `choice` variable (the selected book's index) is what keeps every step — display, validation, and the final `setStatus()` call — pointed at the same book. Three real bugs found and fixed during this task: a boundary regression (`< 0` instead of `<= 0`, allowing `0` through to crash `book.get(-1)`); an infinite loop from checking the wrong thing (validating the illegal "target = Want to Read" move against the book's *current* status instead of unconditionally rejecting it, since that rule never depends on current status); and a classic `String` `==` vs `.equals()` bug on the reread-confirmation prompt, both self-fixed before being told the exact issue. Also independently caught and added the missing `DROPPED → CURRENTLY_READING` legal-move case. Task 3.6: extended option 2 (Add) to let the creator optionally set a starting status — after building `newBook` from title/author/totalPages, a y/n prompt gates an optional submenu (looping `BookStatus.values()` same as 3.5) that calls `newBook.setStatus(...)` once via direct index, no branching, since the while-loop validation already guarantees a valid choice; `book.add(newBook)` sits once, after and outside the y/n `if`, so the book is added either way. First draft had a redundant four-branch `if/else if` copy-pasted from 3.5's transition chain where every branch ran the identical line — self-collapsed to one line once asked to trace it. Task 3.7: wired option 5 (Delete) — an `isEmpty()`/`else` guard (matching View/Update's existing shape) wraps the whole feature, then the 3.4/3.5 selection pattern (numbered list via `getTitle()`, a validated `deleteChoice` index), a title confirmation message, a y/n gate (`deleteConfirm`), and `book.remove(deleteChoice - 1)` inside the `y` branch only — first use of `.remove()` on the list. First draft had no empty-library guard; self-fixed after correctly predicting, cold, that it would infinite-loop. Task 3.8: wrapped every `scanner.nextInt()` call in the file in `try`/`catch (InputMismatchException e)`, clearing the buffer with `scanner.nextLine()` in each `catch` so a bad token doesn't get re-read forever. Found and fixed three real bugs surfaced while doing this: the menu's `number` variable carrying a stale value across outer-loop passes (a pre-existing latent bug exposed by adding a new validation loop around the menu read), a dead/redundant validation loop on Update Page's `currentPage`, and a stale unconditional `scanner.nextLine()` in Change Status left over from before try/catch existed, which silently swallowed a line of input on the exception path. All Section 3 menu options now wired, all numeric reads crash-proof. Task 4.2: added a save block right after the menu's `while` loop ends (runs once, on Exit) — a `try { PrintWriter writer = new PrintWriter("books.txt"); ... } catch (FileNotFoundException e) { ... }`, a `for` loop over `book` writing each book's six fields as one concatenated `println()` with `\n`s between them (same technique as `toString()` in 2.4) plus a blank `println()` after each book, then `writer.close()`. First draft opened the `PrintWriter` outside the `try` block; self-fixed once asked to check the risky line against the try block's braces. Task 4.3: removed the three hardcoded startup books and the 2.5 debug `println` (both would've caused duplicates once load exists); added a load block right after `ArrayList<Book> book` is declared — `try { Scanner fileScanner = new Scanner(new File("books.txt")); while (fileScanner.hasNextLine()) { ... } } catch (FileNotFoundException e) { ... }`, reading 7 lines per book (6 fields into `String` variables, 1 bare call consuming the blank separator), converting back to real types (`Integer.parseInt`, `BookStatus.valueOf`, `LocalDate.parse`) and rebuilding each `Book` via the constructor plus `setCurrentPage`/`setStatus`/`setDateAdded`, then `book.add(newBook)`. Verified end-to-end: real books survive an Add → Exit → relaunch → View round trip. |
| `src/main/java/BookStatus.java` | known | Task 2.1: enum of the four fixed statuses from task 0.2 — `WANT_TO_READ`, `CURRENTLY_READING`, `FINISHED`, `DROPPED`. First enum written from scratch. |
| `src/main/java/Book.java` | known | Task 2.2: the six private fields. Task 2.3: constructor taking `title`/`author`/`totalPages` as required parameters, defaulting `currentPage`/`status`/`dateAdded`. Task 2.4: `toString()` concatenating all six fields into a labeled, multi-line `String`. Task 3.4: added getters/setters so `Main` can read and change private fields safely — `getTitle()`, `getTotalPages()`, `getCurrentPage()`, and `setCurrentPage(int)`; the first two title/currentPage getters were self-added, unprompted, once `Main.java` needed them. Task 3.5: added `getStatus()` and `setStatus(BookStatus status)`, matching `Book`'s existing getter/setter pattern. `setStatus`'s parameter is deliberately named the same as the field (`status`), requiring `this.status = status` to reach the field instead of the parameter — a real naming collision (shadowing), unlike `setCurrentPage(int newPage)`'s differently-named parameter, which doesn't need `this.` to work. Both styles are valid; matching the parameter name to the field plus `this.` is the more common professional convention. Task 3.6: self-added `getAuthor()`, matching the existing getter pattern, once `Main.java`'s new-book confirmation printout needed it. Task 4.3: added `setDateAdded(LocalDate)`, with a comment restricting its intended use to load logic only — `dateAdded` has no setter by design (0.1/0.3: not freely user-editable), but load needs to restore a book's true original date rather than let the constructor default it to today. |

### Project root (Section 4)

| Path | Marker | What it is and why it exists |
|---|---|---|
| `books.txt` | generated | The saved-books file. Created and overwritten by `Main.java`'s save block (task 4.2) every time the program exits — not hand-edited. One field per line, blank line between books (Option C, chosen in task 4.1). Will be replaced by SQLite (`books.db`) once Section 4's later tasks land; kept only as the file-based stepping stone. |

### `checkpoint/` (Section 3 phase-boundary checkpoint)

Not part of the app. A one-time, timed cold-rebuild exercise (2026-09-10) —
recreate `Book` + ArrayList CRUD from a blank file, no looking at the real code,
per `CLAUDE.md`'s phase-boundary protocol. Kept intentionally as a record of
where memory stood at the end of Section 3, not maintained going forward.

| Path | Marker | What it is and why it exists |
|---|---|---|
| `checkpoint/section3/java/Book.java` | known | Cold-rebuild attempt at `Book`. Nearly matched the real file — missing `getTitle()`/`getAuthor()` (not needed without a compact numbered-list display), field named `dateAdd` instead of `dateAdded`. |
| `checkpoint/section3/java/BookStatus.java` | known | Cold-rebuild attempt at the status enum — exact match to the real one. |
| `checkpoint/section3/java/Main.java` | known | Cold-rebuild attempt at ArrayList CRUD, hardcoded (no `Scanner`, no validation, as scoped). Used `book.remove(book.get(2))` (object-reference removal) instead of the real code's index-based `book.remove(deleteChoice - 1)` — a valid alternative that surfaced a real `.equals()`/reference-equality discussion, since resolved. Contains one harmless unused import (`java.lang.Integer`), leftover from an abandoned approach to the max-pages logic. |

---

## Files Not Yet Created

Placeholders so I know what's coming and nothing appears as a surprise. These get
real entries — with real markers — when they actually exist.

### Section 1 will create

| Path | Expected marker | What it will be |
|---|---|---|
| `pom.xml` | parked → known | Maven's manifest: project identity, Java version, dependency list. Starts parked ("declares what my project needs"), resolved in Section 5 when Spring Boot dependencies make it concrete. |
| `src/main/java/` | known | Where my Java source lives. Maven convention, not arbitrary — the build tool looks here by default. |
| `target/` | generated | Compiled bytecode and packaged builds. Maven creates it. Never edited, never committed. |

### Sections 2–3 will create

| Path | Expected marker | What it will be |
|---|---|---|
| `Book.java` | known | The Book class from my paper sketch. The core data model — must be `known` on day one. |
| `BookStatus.java` | known | Enum of the fixed statuses a book can have. |
| `BookService.java` (or similar) | known | The application logic — add, update, filter, calculate progress. The engine. Name and shape are my design decision. |

### Section 4 will create

| Path | Expected marker | What it will be |
|---|---|---|
| `books.db` | generated | The SQLite database file. Created by the program, holds real data, never hand-edited, never committed. |
| `BookRepository.java` (or similar) | known | The code that talks to the database. SQL lives here and nowhere else. |
| `schema.sql` | known | Defines the books table structure. |

### Sections 5–7 will create

| Path | Expected marker | What it will be |
|---|---|---|
| `src/main/resources/` | known | Non-Java files Spring Boot needs — templates, CSS, config. |
| `templates/*.html` | known | Thymeleaf templates. HTML I wrote, so `known`, though individual Thymeleaf attributes may be parked as I meet them. |
| `static/style.css` | known | My CSS. Written by hand, no framework. |
| `application.properties` | parked → known | Spring Boot configuration — database connection, port, settings. Parked on first sight, resolved in Section 9 when the dev/prod split makes each line matter. |
| `BookController.java` | known | Maps URLs to code. The front door of the web app. |

### Sections 8–9 will create

| Path | Expected marker | What it will be |
|---|---|---|
| `src/test/java/` | known | Where tests live. Separate tree from `src/main` so tests never ship to production. |
| `BookServiceTest.java` (or similar) | known | Tests for the logic that matters — progress math, status transitions, validation. |
| `application-prod.properties` or env config | known | Production settings pointing at Postgres. Must be `known` — this is where secrets management lives. |
| `Procfile` / `render.yaml` (if needed) | parked → known | Tells the host how to run my app. Resolved during Section 9 deploy debugging. |

---

## Standing Rules

1. **A new file gets an entry before the session ends.** No exceptions, including
   files created by a command I ran without fully understanding.
2. **Every `parked` entry names when it gets resolved.** If I can't say when, I
   can't park it.
3. **`generated` still requires knowing what produces it and why.** "The tool made
   it" is not an explanation. "Maven puts compiled bytecode in `target/`, which is
   why `target/` is in `.gitignore`" is.
4. **If a tutorial or an AI tells me to create a file I don't understand, that is a
   stop signal**, not a step to follow. Ask first, create second.
