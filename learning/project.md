# Project: Personal Reading Tracker

## Who I Am

- 3rd-year Computer Science student, fall semester online, lightweight course load
- Comfortable with: some HTML/CSS (rusty, ~1 year ago), some Java from classes
- Honest skill level: beginner — have followed tutorials but never designed or built
  an original project from scratch
- Goal: use this one evolving project to actually learn Java instead of doing more
  tutorials, then ship it live on the internet
- Rule: AI assists as teacher/debugger, does NOT write the project for me — I make
  design decisions, attempt code first, get feedback when stuck

## Why This Project

I track my reading in Apple Notes right now — just a plain list of titles. That
works but it's unstructured. A real app gives each book a status, a page count, a
progress indicator. It also gives me a reason to write Java every day on something
I'll actually use.

I'm also a runner (Strava), do calisthenics (fall off around week 3), journal
occasionally, and want to build coding skill so I can eventually earn money making
things. This project is the first real thing.

## Learning Path

The project evolves through phases. Each phase teaches new Java concepts by adding
real functionality, not contrived exercises.

1. **Console app** — learn Java fundamentals (classes, objects, ArrayLists, methods,
   enums, input validation, file I/O)
2. **Add persistence** — swap file I/O for SQLite, learn JDBC basics
3. **Web MVP** — Spring Boot backend + simple HTML/CSS frontend, deploy live
4. **v2 features** — pull from the parking lot below

I do NOT skip phases. The console app is not a throwaway — the Book class and core
logic carry forward into every later phase.

## MVP — The Smallest Useful Version (Live on the Internet)

This is the target for phase 3. Everything below must work end to end, deployed to
a free host (Railway, Render, or Fly.io), before I touch anything in the parking
lot.

### Data model

- **Book**: title, author, total pages, current page, status, date added

### Core features

- Add a new book (title, author, total pages, and optionally starting status — for
  logging a book already finished, in progress, or dropped, not just new pickups)
- Edit a book's details
- Delete a book
- Update current page (reading progress)
- Set status: Want to Read, Currently Reading, Finished, or Dropped
- View all books, filtered by status (each entry shows its date added, so the
  list actually surfaces the timestamp being stored, not just holds it silently)
- See a simple progress indicator (e.g., "page 184 / 412 — 45%")

### Technical requirements

- Data persists in a database (SQLite or H2) — closing the browser does not lose
  books
- Deployed and accessible via a public URL
- Single user (no login system — just me; if needed, one password via environment
  variable)
- Simple HTML/CSS frontend — no React, no JavaScript framework
- Spring Boot backend serving the pages and handling form submissions

### What "done" looks like

I can open a URL on my phone, add the book I'm currently reading, update my page
number after a reading session, and see my list of books organized by status. That
is the entire MVP. If I'm tempted to add anything else before deploying, I re-read
this section.

## Parking Lot (v2 and Beyond)

Everything below is a real feature I want — written down so it stops living in my
head and distracting me. None of it enters the codebase until the MVP is deployed.

### Reading sessions and stats (v2 — first after MVP)

- Log a reading session: date, start page, end page, optional duration
- Pages read today / this week / this month
- Current reading streak (consecutive days with a logged session)
- Longest reading streak
- Reading history: list of all sessions for a book
- Average pages per session
- Track rereads: a counter for how many times a book has been reread

### Status model (v2+)

- A fifth status: "On Hold" — pause a book without dropping it. Idea surfaced
  during Section 0 task 0.4 (validity rules) on 2026-09-02. Not designed yet —
  would need its own transition rules (likely Currently Reading ↔ On Hold, and
  On Hold → Dropped). Deliberately not touching this until MVP ships; the
  current four-status set (locked in task 0.2) stays as-is until then.

### Metadata auto-fill (v2+)

- Upload a book's PDF (or other file) and have the program auto-fill title,
  author, and total pages instead of typing them in. Idea surfaced during
  Section 0 task 0.5 (checking the sketch against the MVP list) on 2026-09-02,
  while thinking through the Edit feature. Not designed — would need file
  parsing/metadata extraction, well beyond "form submission" scope for the MVP.

### Notes and reflection (v2+)

- Add a note or thought to a specific book (tied to a page number or chapter)
- "Resurface a random old note" button
- Rate a book when finished (1–5 stars)
- Short review or takeaway when finishing a book

### Organization (v2+)

- Tags or genres per book
- Search across all books and notes
- Sort books by date added, progress, title, author
- Browse a wider library of books (beyond what's actively tracked) and mark each as
  read / not read — a separate flag from the three-state progress status, more like
  a checklist across a larger shelf than a page-by-page tracker

### Goals and motivation (v2+)

- Set a reading goal: books per year or pages per month
- Visual progress toward goal
- Weekly summary: what I read this week vs. last week

### Polish (v2+)

- Book cover images (pull from Open Library API or upload)
- Charts: pages over time, books finished per month
- Mobile-responsive design
- Dark mode
- Export data as CSV or JSON

### Someday / maybe

- Rebuild frontend in React or another framework (when/if I learn JS properly)
- Reading timer (start/stop while reading, auto-log session)
- Public "bookshelf" page I can share
- Integration with Goodreads or Open Library for book metadata

## Core Components (The Trunk)

The eight pieces I need to learn and build to get this deployed end to end. Every
future session should be traceable to one of these.

### 1. Source control — Git and GitHub

Git saves snapshots of my code over time. When a piece of work functions, I commit
it: "this version works, save it." Break something later, rewind to any earlier
snapshot. GitHub stores those snapshots online so the work isn't only on my laptop.

Note: Git tracks changes to my *code files*, not the running app. It doesn't verify
that anything works — a commit is a claim I'm making. And pushing to GitHub means
laptop dies, project survives.

Why I need it: I will break things constantly while learning. Git turns "I broke it
and don't know what I changed" into a 30-second rewind.

### 2. Data model

The blueprint defining what a Book is — what information it holds and what rules
apply. In Java this is a class: title, author, total pages, current page, status,
date added. This is what the paper sketch produces.

Why I need it: it's the noun the whole program acts on. Everything else is a verb
applied to it.

### 3. Application logic

The code that does things with the data: add a book, change status, calculate that
page 184 of 412 is 45%, filter to only finished books. The engine. It doesn't know
whether input came from a console or a browser — it takes instructions and does
work on Books.

Why I need it: it *is* the project. Everything else is plumbing carrying data in and
results out.

### 4. User interface (frontend)

What the human sees and touches. Phase 1: console text and typed commands. Phase 3:
HTML pages — a form to add a book, a list, a button to update a page number. Its
only job is collecting input and displaying output. It does not think.

Why I need it: a program with no interface is a program nobody can use, including
me.

### 5. Persistence

Permanent memory. While the program runs, data lives in RAM — fast, temporary,
wiped when the program closes. Persistence writes data somewhere that survives a
restart. Phase 1: a file on disk. Phase 2+: a database, software built for storing
and searching structured data, able to instantly answer "every book with status
Currently Reading."

Precise failure mode: without persistence the app doesn't break — it works
perfectly and forgets everything between runs. Add three books, close it, reopen:
code runs fine, books gone.

Why I need it: an app whose data doesn't survive can't do its job, because its job
is remembering things over time.

### 6. Backend (server)

A Java program running on a remote computer, waiting for requests. I open the URL,
my browser sends "show me my books," the server receives it, asks the database,
runs my logic, sends back a page. Spring Boot is a framework — a large body of
pre-written code — handling the tedious plumbing of requests and responses.

Important: on the web, the frontend does NOT talk to application logic directly.
Browser is on my phone; logic is on a server far away; the internet sits between
them. The backend is the piece that receives requests from a browser it has never
met and turns them into calls to my Book code.

Why I need it: "live on the internet" means a program on a remote machine is
answering my browser. That program is the backend.

### 7. Build tool (Maven or Gradle)

Manages dependencies and packages my code so it can run. A dependency is someone
else's code mine relies on — Spring Boot, the database driver. The build tool
downloads them, compiles my Java into executable form, and bundles everything into
one deployable package.

Why I need it: a real project is my files plus a dozen libraries. Managing that by
hand becomes unmanageable fast.

### 8. Deployment and configuration

Putting the packaged app on an internet-connected server so a URL reaches it.
Railway or Render provide a free one. Configuration is the settings that differ
between my laptop and that server — port, database location, any password. Those
live outside the code in environment variables so secrets never enter Git.

Why I need it: until this step, the project is a thing on my laptop. This is what
makes it real.

## First Milestone

Before writing any code: spend 20 minutes on paper sketching what a "Book" is.
What fields does it have? What should I be able to do with it? What are the
possible statuses and how do they change? This is the design decision that
tutorials always hand you pre-made — I'm making it myself.

Then: open an empty Java project, create a Book class, and write a console program
that can add multiple books to an ArrayList and print them out. No file saving, no
menus, no formatting. Just proof that I can model a book and store more than one.
