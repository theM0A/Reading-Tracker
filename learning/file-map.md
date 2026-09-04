# File Map

Every file and folder in this project, one line on what it is and why it exists.

**The rule: nothing in my repo is a mystery box.** If a file appears — because I
made it, because a tool generated it, because a tutorial said to — it gets an entry
here before I move on. A file I can't explain is a gap in my understanding sitting
in plain sight.

**Last updated:** 2026-08-31

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

### Project root (Section 1)

| Path | Marker | What it is and why it exists |
|---|---|---|
| `.gitignore` | known | Tells Git which files to never track. Written line by line in task 1.2: `files.zip` (a redundant leftover, kept but excluded), `.idea/` (personal IDE workspace state, not shared history), `*.class` (compiled bytecode — a regeneratable byproduct of `.java` source, not the source itself). |
| `README.md` | known | Plain-language intro to the project, written by hand in task 1.2. |
| `.idea/` | generated | IntelliJ's own project metadata (window layout, module config) — created automatically the moment the folder was opened as an IntelliJ project. Machine/personal state, not shared — hence it's in `.gitignore`. |
| `.git/` | generated | Git's internal database — every commit, the staging area, branch info. Created empty by `git init` in task 1.2; nothing was tracked automatically, tracking required a separate `git add` step. Never touched by hand. |
| `src/main/java/` | known | Where Java source lives — Maven convention, adopted early even before Maven's installed, so the structure doesn't need to change later. |
| `src/main/java/Main.java` | known | Entry point. Task 1.3: written by hand, printing "Hello Reader" — proof a Java program can run before any real app logic exists. |
| `src/main/java/BookStatus.java` | known | Task 2.1: enum of the four fixed statuses from task 0.2 — `WANT_TO_READ`, `CURRENTLY_READING`, `FINISHED`, `DROPPED`. First enum written from scratch. |
| `src/main/java/Book.java` | known | Task 2.2 (in progress): the six private fields from the paper sketch. Constructor and `toString()` still to come in 2.3/2.4. |

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
