# Build Plan — Personal Reading Tracker

Companion to `project.md`. That file says who I am and what I'm building. This file
says what I chose and in what order I'm building it.

**Primary objective: understanding, not speed.** A section is finished when I can
explain how it works, not when the code runs. The end goal is being able to explain
my app end to end — from a click in the browser to a row in the database and back.

---

## Locked Decisions

Each of these was chosen deliberately. If I'm ever tempted to change one, the
reasoning is here — I should have a better reason than "I saw something cooler."

### Language: Java

The language of my degree, so every hour on this project makes coursework easier
and vice versa. Nothing else compounds like that. Strict about types, so the
compiler catches mistakes before the program runs instead of exploding mid-
execution. 30 years mainstream means every error I hit has a written answer
somewhere. Strict habits learned here transfer to looser languages later; the
reverse is much harder.

*Rejected:* Python (friendlier, but loose typing hides a whole class of bugs and it
isn't my coursework). JavaScript (learning a strange language while learning to
program means I can't tell which confusions are mine).

### Frontend: plain HTML + CSS, server-rendered with Thymeleaf

Server-rendered means the Java backend builds the complete HTML page and sends it
fully formed. Click a button, the browser requests a new page, the server sends
one. No JavaScript needed for anything in my MVP — every feature is a form
submission or a page view.

Thymeleaf is a template engine: normal HTML with a few extra attributes like
`th:text="${book.title}"` that get filled in when the page is built. Roughly five
attributes cover this entire project — print a value, loop a list, conditional
display, bind a form, build a link. My CSS is 100% plain CSS, untouched by
Thymeleaf.

I am skipping the *framework*, not the frontend. I write real HTML and real CSS and
style the whole thing myself.

*Rejected:* React (separate language, build system, mental model, and app — two
projects instead of one; the most common way beginners stall). Hand-written JS
(still JavaScript, and manual DOM updates get messy fast).

*Note for later:* Java and JavaScript are unrelated languages despite the names.
Java runs on the server, JavaScript runs in the browser, they talk over the
network. Adding JS later is purely additive — a `<script>` tag in a page I already
have. Nothing built now gets thrown away.

### Backend: Spring Boot

A framework is pre-written code handling common problems. Without one, "receive an
HTTP request" means network sockets and protocol parsing — weeks of work before the
app does anything. Spring Boot handles that; I write "when someone visits /books,
run this," and it manages everything underneath.

Default choice for Java web development by a wide margin. Most Java backend jobs
list it. Enormous tutorial supply. Works with Thymeleaf out of the box.

*The tension:* the framework that does the most for me teaches me the least about
what's underneath. Mitigation: I build the console app FIRST. When Spring Boot
calls my Book logic, I know exactly what that logic does because I wrote it. The
magic stays confined to the plumbing layer. I don't need to understand all of
Spring Boot — I need to understand all of *my* code, and to know where the line is.

*Rejected:* Javalin/Spark (less magic, but tiny communities — the 1am answer often
doesn't exist). Plain servlets (deep understanding, enormous boilerplate, nobody
has built this way in 15 years).

### Database: SQLite locally, PostgreSQL in production

A database stores structured data on disk and answers questions about it in SQL.

SQLite is a single file on disk. No server, no install, no config — the program
opens the file and works. Zero setup keeps focus on Java.

Postgres is a separate database server the app connects to over a network.

**Why both:** SQLite lives on the same disk as the app. Hosting platforms replace
that disk on every deploy, so a SQLite file in production gets wiped on the next
push — silently, no error, just an empty list. Postgres runs as its own service
with its own storage and survives independently of the app's lifecycle. The switch
is small: same SQL, same Java code, a few lines of config.

**This is not optional.** Deploying is in the MVP, and deploying is the moment
SQLite stops working. It's a scheduled step, not a someday-upgrade.

*Rejected:* H2 in-memory (data vanishes on restart — the exact opposite of the
lesson). MongoDB (my data is a table of books with identical fields every time,
which is precisely what SQL is for, and SQL is the more valuable skill).

### Hosting: Render

Managed hosting: rents an always-on internet-connected computer, watches my GitHub
repo, builds and deploys on push. Free tier for web services. Free apps sleep after
inactivity, so the first visit after a quiet period takes 30–60 seconds to wake.
Fine for a personal tracker.

Doing it myself would mean provisioning a machine, installing Java, configuring a
web server, managing SSL — a separate career, not a prerequisite for mine. I still
learn the parts that matter: environment variables, why config differs between
laptop and server, what a build produces, how connection strings work. I skip the
sysadmin layer.

*Rejected:* Railway (nearly identical, no sleep delay, ~$5/month after trial —
worth revisiting if the sleep annoys me). Fly.io (more control, more concepts at
the exact moment I want deployment to be boring).

**Reminder:** this app has exactly one user and it's me. No login system, no
sharing, no public profiles. If I catch myself designing for an audience, that's
scope creep.

---

## Build Sections

Nine sections. Each ends in something I can see working. Each builds on the last.
No section starts until the previous deliverable exists.

Task-level breakdown comes later, one section at a time.

### Section 0 — The paper sketch

Before any code, any install, any account: 20 minutes with paper defining what a
Book is. What fields does it hold? What are the possible statuses? How does a book
move between them? What's required vs. optional? What would make a book invalid?

This is the design decision tutorials always hand over pre-made. Making it myself
is the point.

**Deliverable:** a page of paper describing the Book model in plain language, in my
handwriting.

**Tasks:**

- [x] 0.1 — Fields: what information does a Book hold? Decide each one, and decide
      what is stored vs. calculated. ✓ Six stored fields: title, author, total pages,
      current page, status, date added. Calculated values (progress %) are NOT fields.
      Defaults: current page starts at 0 (not 1 — keeps 0% math clean for an unopened
      book), status starts at "Want to Read", date added is an automatic timestamp.
- [x] 0.2 — Statuses: what states can a book be in? Name the exact, complete set.
      ✓ Four statuses: Want to Read, Currently Reading, Finished, Dropped. Found the
      Dropped gap by stress-testing the original three against "quit mid-book."
      Confirmed multiple books can share "Currently Reading" at once (no new status
      needed) and that rereading targets Currently Reading, not Want to Read.
- [x] 0.3 — Transitions: how does a book move between statuses? Which moves are
      allowed, which are nonsense? ✓ Want to Read → Currently Reading; Currently
      Reading → Finished or Dropped; Finished → Currently Reading (reread); Dropped
      → Currently Reading (pick up). Nothing transitions to Want to Read after
      creation — that status is creation-only. Key distinction found: the *initial*
      status chosen when a book is added (any of the four, e.g. logging a book
      already finished) is not a transition — transitions only govern a book already
      in the system. Kept Want to Read → Finished blocked as a transition
      deliberately, for accountability, with the option to loosen it later.
- [x] 0.4 — Validity: what makes a Book invalid? What should the program refuse to
      accept? ✓ Total pages: must be > 0. Title/author: must not be empty — no
      character restrictions (rejected a digits/symbols ban after considering real
      names with apostrophes/hyphens and online aliases). Current page: must be
      >= 0 (0 is valid — "haven't started," matches the 0.1 default) and <=
      total pages (no theoretical upper bound beyond that). Status: must be one
      of the four fixed enum values, nothing else. Date added: not user input —
      it's the automatic timestamp from 0.1, so format validation doesn't apply;
      "invalid" here means the program failed to set it, not a malformed value.
- [x] 0.5 — Check the sketch against the MVP feature list in `project.md`. Can
      every MVP feature be built from this model? Anything missing, anything unused?
      ✓ All seven MVP features map to the model. Resolved a real design tension on
      Edit: since status can't freely be edited without undermining 0.3's
      accountability rule, Edit is scoped to title/author/total pages only — status
      changes only through Set Status (transition-gated), current page only through
      its own feature, wrong status at creation means delete-and-re-add. Caught a
      real gap: date added was stored but no feature displayed it, contradicting its
      own stated purpose ("so the user knows when they added the book") — fixed by
      folding it into the View feature.

### Section 1 — Source control and a running Java project

Install Java and an IDE. Create an empty project. Initialize Git, make a GitHub
repo, push. Write a program that prints one line. Commit it.

Learn: what a commit is, what a push is, what `.gitignore` does and why compiled
output never belongs in a repo.

**Deliverable:** a GitHub repo with at least two commits, containing a Java program
that runs and prints something.

**Tasks:**

- [x] 1.1 — Confirm Java (JDK) and an IDE are installed and working. ✓ Java 26
      (JDK, confirmed via both `java -version` and `javac -version`), working in
      IntelliJ IDEA's built-in terminal.
- [x] 1.2 — Create the project folder, initialize Git, write `.gitignore` and
      `README.md`. First commit. ✓ Project folder already existed. Caught and
      resolved a mix-up (moved a stray file into `.idea/`'s own auto-generated
      gitignore instead of the project's real one). `.gitignore` covers
      `files.zip`, `.idea/`, `*.class` — reasoned through source-vs-compiled-output
      and IDE-metadata-vs-shared-history distinctions to get there. First commit
      `25f7908`: "initial project setup: paper sketch design docs, gitignore,
      readme, no code (java) yet."
- [x] 1.3 — Write a one-line Java program and get it running. ✓ `src/main/java/Main.java`,
      printing "Hello Reader". Hit and fixed real mistakes along the way: a
      module created then deleted (module vs. plain directory distinction),
      a wrong nested path (`src/main/src/`), and a rename that swept the
      required-lowercase `main` method into capitalized `Main` along with the
      class. Ran it via the single-file source-launch shortcut (`java
      Main.java` directly, Java 11+) — confirmed by checking the folder that
      no `.class` file was produced, since that mode compiles in memory only.
- [x] 1.4 — Second commit (the working program). ✓ Commit `9a20895`: "First java
      program, helping me understand how code runs in terminal. Updating my
      learning progression as I create Reading-tracker app." Bundled
      `src/main/java/Main.java` with the three `learning/*.md` updates from this
      session. First draft of the message covered only the code half; revised
      after a prompt to name the why and cover both halves of the diff.
- [x] 1.5 — Create a GitHub repo, connect it as a remote, push. Confirm the commits
      show up online. ✓ Repo `theM0A/Reading-Tracker`, created empty (no
      README/.gitignore/license, to avoid a conflicting initial history).
      Renamed local branch `master` → `main` unprompted to match GitHub's
      default before pushing. `git remote add origin`, then
      `git push -u origin main` — both commits confirmed live on GitHub.
      Quiz on the `-u` flag needed one correction: attributed the
      no-more-`-u`-needed behavior to the branch rename rather than to the
      upstream tracking link `-u` itself creates.

**Section 1 complete** — all five tasks done. Deliverable met: a GitHub repo with
two commits, containing a Java program that runs and prints something.

### Section 2 — The Book class and a list in memory

Turn the paper sketch into a Java class. Fields, a constructor, methods. An enum
for status. Create several books in code, put them in an ArrayList, print them all.
No input, no menu, no saving.

Learn: classes, objects, fields, constructors, methods, enums, ArrayList,
`toString()`.

**Deliverable:** running the program prints a list of three hardcoded books with
their details.

**Tasks:**

- [x] 2.1 — Create `BookStatus.java`: an enum with the four fixed statuses from
      task 0.2. ✓ `WANT_TO_READ`, `CURRENTLY_READING`, `FINISHED`, `DROPPED`.
      First real enum ever written — worked through unfamiliar syntax from a
      generic example (not the answer), then two corrections on naming: hyphens
      aren't legal Java identifiers (parsed as subtraction), and enum constants
      follow an ALL_CAPS convention. Landed both on the second attempt.
- [x] 2.2 — Create `Book.java`: the six fields from task 0.1, matching their
      types to what each one actually holds. ✓ `title`/`author` as `String`,
      `totalPages`/`currentPage` as `int`, `status` as `BookStatus`,
      `dateAdded` as `LocalDate` (needing an `import`). First attempt nested
      all six fields inside a stray `main` method, copying `Main.java`'s shape
      — corrected: fields sit directly in the class body, `Book` needs no
      `main` at all. User asked to adopt the Feynman method going forward
      (see [[feedback-feynman-pause]] in memory) mid-task.
- [x] 2.3 — Write `Book`'s constructor. ✓ `public Book(String title, String author,
      int totalPages)` — three required parameters matching what varies book to
      book, with `currentPage`/`status`/`dateAdded` set to their 0.1 defaults
      directly in the body. First attempt placed the constructor outside the
      class body entirely (a syntax error, corrected after a prediction
      question — distinguished access control (`private`) from syntactic
      placement inside the class). Second attempt included `currentPage` as a
      required parameter, self-caught and corrected before writing code: "total
      pages of a book will differ from book to book... i mixed up the
      requirements and automatic set fields." Needed the `this` keyword taught
      fresh (parameter/field name shadowing) and explained back correctly
      unprompted. Two wrong attempts at reaching an enum constant and a static
      method (`BookStatus(WANT_TO_READ)`, `LocalDate(LocalDate)` — calling a
      type name like a function) corrected by teaching dot notation for both a
      fixed enum constant and a static method call; explained the difference
      (constant vs. computed value) back correctly afterward.
- [x] 2.4 — Write `Book`'s `toString()` method so a Book prints its own details.
      ✓ `@Override public String toString()`, one return statement concatenating
      all six fields with `\n` between each, labeled. Several wrong attempts
      corrected along the way: wrapping the whole expression in `String(...)`
      (same "call the type name like a function" mistake as `BookStatus`/
      `LocalDate` in 2.3 — self-recognized the pattern once asked); six separate
      `return` statements instead of one, corrected after a question about what
      `return` actually does (exits immediately, hands the value to the
      *caller*, not "back to the class" — needed one correction on that
      phrasing, then explained it back correctly); hardcoded
      `BookStatus.WANT_TO_READ` / `LocalDate.now()` instead of referencing the
      real `status`/`dateAdded` fields, self-corrected after being asked what a
      changed/older book would wrongly print; `/n` instead of `\n` for line
      breaks, looked up and self-corrected; `currentPage` missing entirely from
      the output with `totalPages` mislabeled under "Current Page," self-caught
      by tracing the concatenation piece by piece.
- [x] 2.5 — In `Main.java`, create three hardcoded `Book` objects, put them in an
      ArrayList, and print the whole list. ✓ `ArrayList<Book>`, three `new
      Book(...)` calls nested directly inside `.add(...)`, one `System.out
      .println(book)` printing the whole list — no loop needed yet (`ArrayList`'s
      own `toString()` calls each element's `toString()` automatically, deferred
      to Section 3). Two real mistakes corrected: `new ArrayList<>;` missing its
      `()` (got fully stuck on this one, needed a direct explanation rather than
      a guided question); `.add()` called with three raw values instead of a
      built `Book` object, same fix applied unprompted to the remaining two
      lines once shown one worked example. Ran the program, predicted the
      output correctly beforehand, and closed by explaining unprompted why real
      field values printed instead of `Book@1a2b3c` — tied `toString()`'s
      override back to this exact result.

**Section 2 complete** — all five tasks done. Deliverable met: running the
program prints a list of three hardcoded books with their full details.

### Section 3 — A working console app

Add a menu loop. Read input from the keyboard. Add books, view books, update a
current page, change a status, delete a book. Validate input — reject a page number
above the total, reject empty titles, handle someone typing letters where a number
belongs without crashing.

Everything still disappears on exit. That's expected and it's the setup for the
next section.

Learn: Scanner, loops, conditionals, methods that return values, input validation,
exception handling, separating logic from display.

**Deliverable:** I can run the app in a terminal and manage a list of books
entirely through the menu, and I can't crash it with bad input.

**Tasks:**

- [x] 3.1 — Build the menu skeleton: a loop that prints the action list (View,
      Add, Update Page, Change Status, Delete, Exit) and reads my choice with
      `Scanner`. No real actions wired yet — just prove the loop runs,
      redisplays the menu, and exits cleanly on Exit. ✓ `while (number != 6)`
      loop in `Main.java`, printing the menu and reading a choice via
      `input.nextInt()` each pass, `"Goodbye!"` printed after the loop ends.
      Predicted "works once" for a broken-read scenario, corrected to the real
      answer (infinite loop, no pause for input) after a walkthrough; then
      correctly predicted and confirmed by running that typing 3 loops back
      and typing 6 exits cleanly.
- [x] 3.2 — Wire "View all books" to the real `ArrayList` from Section 2,
      numbered, with a message if the list is empty. ✓ Nested `if
      (book.isEmpty())` / `else` inside the `number == 1` branch, indexed
      `for` loop printing `(i + 1) + ". " + book.get(i)`. Hit and fixed a real
      compile error (`book.isEmpty` missing `()`) by reading the actual
      compiler message rather than being told the answer. Ran it, confirmed
      all three books print numbered 1–3 with full details.
- [ ] 3.3 — Wire "Add a book": prompt for title, author, total pages via
      `Scanner`. Validate against 0.4's rules (non-empty title/author,
      totalPages > 0) and re-prompt instead of crashing on bad input.
- [ ] 3.4 — Wire "Update current page": pick a book from the list, read a new
      page number, validate it's >= 0 and <= that book's totalPages.
- [ ] 3.5 — Wire "Change status": pick a book, choose a target status, and
      enforce the transition rules from 0.3 (reject illegal moves with a
      message, don't silently apply anything).
- [ ] 3.6 — Wire "Delete a book": pick a book, remove it from the ArrayList,
      confirm removal.
- [ ] 3.7 — Handle bad non-numeric input everywhere `Scanner` reads a number
      (e.g., typing "abc" for total pages) without crashing — catch the
      exception, show a message, re-prompt.

### Section 4 — Persistence

Make the data survive. Save to a file on exit, load on startup. Once that works,
replace it with SQLite: create a table, write SQL to insert, select, update, and
delete, connect from Java through JDBC.

Learn: file I/O, why in-memory data disappears, what a database is, basic SQL,
JDBC, connections and statements.

**Deliverable:** add books, close the program, reopen it, and the books are still
there — first from a file, then from a database.

### Section 5 — A page in a browser

First Spring Boot project. A controller method that responds to a URL. A Thymeleaf
template that renders. Start with static text, then pass real data from Java into
the page.

This is where the mental model shifts: the browser and the code are now separate
things talking over a network.

Learn: what a server is, what a request and response are, what a route is, what a
controller does, how Thymeleaf fills in values.

**Deliverable:** `localhost:8080` in my browser shows a page listing my books,
pulled from Java.

### Section 6 — Styling and forms

Make it look like something I want to open. Write the CSS myself — layout,
typography, spacing, a progress indicator. Add HTML forms for adding a book and
updating progress, wired to POST routes.

Learn: HTML forms, GET vs POST, how form data reaches the server, CSS layout,
responsive basics so it's usable on my phone.

**Deliverable:** a styled page where I can submit a form and see the new book
appear in the list.

### Section 7 — Full features on the web

Every MVP feature working through the browser against the real database: add, edit,
delete, update progress, change status, filter by status, show progress percentage.
The Book logic from the console app carries over — this section wires it to the web
layer.

Learn: connecting controller, service, and database layers; handling edits and
deletes over HTTP; server-side validation; what happens on refresh after a form
post.

**Deliverable:** the complete MVP feature list works in my browser, backed by the
database, running locally.

### Section 8 — Tests

Write automated tests for the logic that matters — progress calculation, status
transitions, validation rules. Run them and watch them pass. Break something on
purpose and watch them fail.

Learn: what a unit test is, JUnit basics, why tests exist, what's worth testing and
what isn't.

**Deliverable:** a test suite that runs with one command, and a deliberate bug that
the tests catch.

### Section 9 — Live on the internet

Switch from SQLite to Postgres. Move config into environment variables. Deploy to
Render. Fix whatever breaks — something always breaks, and debugging it is the
lesson.

Learn: dev vs production config, environment variables, why secrets never enter
Git, connection strings, reading deploy logs.

**Deliverable:** I open a URL on my phone, add the book I'm actually reading, and
update my page number after tonight's session.

---

## Definition of Done

The MVP is done when Section 9's deliverable is real, and when I can walk someone
through what happens between typing the URL and seeing my book list — through the
browser, the network, the controller, the logic, the database, and back.

Only then does the parking lot in `project.md` open.
