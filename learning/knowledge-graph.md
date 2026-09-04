# Knowledge Graph

The map of what I actually know. Updated after every lesson. This file decides what
I get quizzed on.

**Last updated:** 2026-09-01

---

## How This Works

### Statuses

| Status | Means |
|---|---|
| `seed` | Not yet taught. On the map because this project will teach it. |
| `introduced` | Explained to me once. I have heard it, not used it. |
| `practicing` | I have used it in real code, with help or reference. |
| `understood` | I explained it in my own words AND passed a quiz on it. |

### Rules

1. **Statuses only upgrade on evidence.** Something I said or did, recorded in the
   evidence field. Not "we covered that." Not vibes. If the evidence field is
   empty, the status is `seed`.
2. **`introduced` requires an explanation happened.** `practicing` requires code I
   wrote. `understood` requires both an in-my-own-words explanation and a passed
   quiz.
3. **Statuses can downgrade.** If I fail a quiz on something marked `understood`,
   it drops to `practicing`. Forgetting is normal and the map should be honest.
4. **No re-quizzing on fresh understood concepts.** If status is `understood` and
   last-reviewed is under 30 days old, skip it. Quiz time goes to `introduced` and
   `practicing` entries.
5. **Stale understood concepts come back.** Over 60 days since last review, it
   becomes eligible again — one quick check, not a full re-teach.
6. **Quiz priority order:** `practicing` (closest to locking in) → `introduced`
   (needs application) → stale `understood` → `seed` is never quizzed, only taught.

---

## 1. Low-Level — Language Fundamentals

The raw material. Variables, control flow, the syntax of thinking in Java.

| Concept | Status | Introduced | Last Reviewed | Evidence |
|---|---|---|---|---|
| Variables and assignment | seed | — | — | |
| Primitive types (int, double, boolean, char) | introduced | 2026-09-03 | 2026-09-03 | Task 2.2: taught primitive (`int`) vs. object type (`String`) while assigning types to Book's fields. Applied correctly — `totalPages`/`currentPage` as `int`, no explanation-back yet on the primitive/object distinction itself. |
| Strings and string methods | introduced | 2026-09-03 | 2026-09-03 | Task 2.2: `title`/`author` typed as `String`, introduced as a capitalized object type unlike primitives. Applied correctly in real code; no string *methods* used yet. |
| Operators and expressions | seed | — | — | |
| Conditionals (if / else if / else) | seed | — | — | |
| Switch statements | seed | — | — | |
| Loops (for, while, do-while) | seed | — | — | |
| Enhanced for loop (for-each) | seed | — | — | |
| Methods: defining and calling | seed | — | — | |
| Parameters and arguments | seed | — | — | |
| Return values and return types | seed | — | — | |
| void vs returning methods | seed | — | — | |
| Scope (where a variable is visible) | seed | — | — | |
| Arrays | seed | — | — | |
| ArrayList and why it beats an array here | seed | — | — | |
| Classes as blueprints | understood | 2026-08-31 | 2026-09-01 | Trunk item 2; corrected once in 0.1, then on a second pass explained it unprompted: "the class contains all the fields... no data is within those fields." Correct without help the second time. |
| Objects as instances | understood | 2026-09-01 | 2026-09-01 | "once an object is called (book) the fields are fully updated and aren't in some pending session" — grasped that an object is born fully filled, no half-built state, after one clarifying exchange. |
| Fields (instance variables) | practicing | 2026-09-01 | 2026-09-03 | Identified six fields for Book, articulated that all six are stored. Self-corrected: "is it really calculated? you are just replacing the 5 with a 10." Session-opener check 2026-09-02: "a field is an empty data holder within the book class (container)... will hold data values for book objects that'll be created" — clean, unprompted. Task 2.2: wrote all six real fields in `Book.java`; first attempt nested them inside a stray `main` method (corrected — fields live directly in the class body). Re-explained cleanly after the fix: "fields can only be stored in a class... never in methods." Real code written — upgraded to practicing. |
| Constructors | introduced | 2026-09-01 | 2026-09-01 | Named (not yet used) while explaining objects: "an object only comes into existence the moment you build it with a constructor." First exposure to the term. |
| Getters and setters | seed | — | — | |
| Encapsulation (private fields, public methods) | introduced | 2026-09-03 | 2026-09-03 | Task 2.2: taught the `private` convention before writing fields. Feynman-style check afterward: "the fields are marked as private to prevent code outside the class to access the fields and change it directly" — clean, unprompted. |
| toString() and why it exists | seed | — | — | |
| Enums (fixed set of values, e.g. book status) | practicing | 2026-09-01 | 2026-09-03 | Task 0.2: derived the exact fixed set for status by testing edge cases. Session-opener quiz 2026-09-02 was shaky (missed "why enum vs. string"); after a refresher, correctly applied it unprompted in 0.4: "status field would be invalid if a choice isnt selected from the fixed enum set... user shouldnt be allowed to create another choice of their own." Task 2.1: admitted honestly to never having used enums; warm-up conflated the enum's job (restricting to a valid set) with transition-rule enforcement (needed a correction — enum doesn't know the rules, that's separate logic). Wrote `BookStatus.java` from a generic pattern, self-corrected identifier naming (hyphens → underscores) after a nudge, applied ALL_CAPS convention after being told once. Post-task quiz clean: correctly named the compiler as catching an invalid value, at compile time before running. First real code written — upgraded to practicing. |
| null and NullPointerException | seed | — | — | |
| Compiling: source code → bytecode (JVM) | introduced | 2026-09-03 | 2026-09-03 | Task 1.3 quiz: "compiling turns source code into bytecode... .java is turned into a .class file" — right on the transformation, needed two corrections: the JVM (not the terminal) executes bytecode, and the single-file launch shortcut (`java Main.java`) compiles in memory with no `.class` file written, which contradicted their own earlier confirmed observation of the empty folder. |
| Exceptions and try/catch | seed | — | — | |
| Reading a stack trace | seed | — | — | |
| Scanner and reading keyboard input | seed | — | — | |
| Type casting and conversion | seed | — | — | |
| static vs instance | seed | — | — | |
| Dates and LocalDate | introduced | 2026-09-03 | 2026-09-03 | Task 2.2: `dateAdded` typed as `LocalDate`, introduced alongside the need for an `import`. Applied correctly in real code; no date methods used yet. |
| Integer/double division and percentage math | introduced | 2026-09-01 | 2026-09-02 | Reasoned through current-page default using the progress formula: correctly rejected starting at 1 because "1/412" would show false progress on an unopened book. Reinforced in 0.4 resolving a self-contradiction: reconnected 0/203 pages to "0% which is what we want" and derived the upper bound (current page <= total pages) unprompted. |
| Interfaces (basic idea) | seed | — | — | |
| Inheritance (basic idea) | seed | — | — | |

## 2. Structural — How Code Is Organized

How files find each other, how a project is shaped, how other people's code gets in.

| Concept | Status | Introduced | Last Reviewed | Evidence |
|---|---|---|---|---|
| Multiple files in one program | seed | — | — | |
| Packages and imports | introduced | 2026-09-03 | 2026-09-03 | Task 2.2: wrote `import java.time.LocalDate;` to pull in a type from elsewhere in the standard library. First real import written; first attempt missed the trailing semicolon (corrected). |
| One class per file convention | seed | — | — | |
| Project directory structure (src/main/java) | seed | — | — | |
| What a dependency is | introduced | 2026-08-31 | 2026-08-31 | Trunk item 7: "someone else's code that my project relies on" |
| Build tool (Maven/Gradle) role | introduced | 2026-08-31 | 2026-08-31 | Trunk item 7, plan.md locked decision |
| pom.xml — Java's package manifest | seed | — | — | |
| Compiling: source code → bytecode | seed | — | — | |
| Classpath and build output (target/) | seed | — | — | |
| Packaging into a runnable JAR | seed | — | — | |
| Separation of concerns | introduced | 2026-08-31 | 2026-08-31 | Explained frontend/logic split: "frontend only cares about taking input and displaying output, it doesn't perform the logic" |
| Layered architecture (controller / service / repository) | seed | — | — | |
| Data model as the foundation layer | practicing | 2026-08-31 | 2026-09-02 | Trunk item 2; task 0.1: designed the Book model on paper, chose six fields, cut parking-lot fields (genre, rating, notes), distinguished stored from calculated. Task 0.3: defined the full transition rules governing how status can legally change. Task 0.4: derived validity rules per field, catching and resolving a self-contradiction on current page's lower bound against the locked 0.1 default. Task 0.5: audited all seven MVP features against the model — kept Edit consistent with 0.3's accountability rule by scoping it to title/author/total pages only (rejected editing status directly once the tradeoff was framed), and caught a real gap where date added was stored but never displayed by any feature. Section 0 complete. |
| Application logic layer | introduced | 2026-08-31 | 2026-08-31 | "The engine of the app that makes the car run — the knife that cuts the ingredients" |
| Frontend / UI layer | introduced | 2026-08-31 | 2026-08-31 | "The cover of the car, the buttons, steering wheel, gas pedal — allows the user to communicate with the engine" |
| Backend / server layer | introduced | 2026-08-31 | 2026-08-31 | Trunk item 6; corrected the "frontend talks directly to logic" gap |
| Configuration files | seed | — | — | |
| Framework vs library | seed | — | — | |

## 3. Engineering Practice — How Professionals Work

The habits that separate someone who codes from someone who ships.

| Concept | Status | Introduced | Last Reviewed | Evidence |
|---|---|---|---|---|
| Source control: what and why | introduced | 2026-08-31 | 2026-08-31 | "Allows you to save code that works, undo code if you break something, and see the progression of your app over time" |
| Git repository (init, local repo) | practicing | 2026-09-02 | 2026-09-02 | Task 1.2: predicted `git init` would auto-track existing files — corrected (it only creates an empty `.git/` database, tracking is a separate deliberate step). Ran `git init` and `git status` themselves, correctly predicted `.gitignore`'d files wouldn't appear as untracked. Post-task quiz: explained `init` vs `add` but conflated `init` with producing the untracked-files list (that's `git status`'s doing, not `init`'s) — corrected, not yet a clean pass. |
| Commits as snapshots | understood | 2026-08-31 | 2026-09-03 | Explained saving working versions; corrected that Git tracks files, not app behavior. Session-opener 2026-09-02: "a commit saves your code progress on a project" — clean, unprompted. Task 1.2: predicted `git add` moves files between status categories (needed a small precision correction), then staged and ran a real first commit with a self-written message, commit `25f7908`. Session-opener 2026-09-03: "the act of saving your files that you tracked and stage to your git repository" — clean, unprompted. Task 1.4 quiz, same sitting: "staged files are those that are updated and ready to be saved... committed files are just those updated files saved to your git repo with a message behind them" — two clean, correct, unprompted explanations in one sitting with no corrections needed. Upgraded to understood. |
| Writing good commit messages | practicing | 2026-09-02 | 2026-09-03 | Task 1.2: wrote the first real commit message unprompted — "initial project setup: paper sketch design docs, gitignore, readme, no code (java) yet" — described both what's included and what's deliberately missing. Task 1.4: first draft described only what changed; after a prompt, articulated the what-vs-why distinction unprompted ("not just saying what changed... but also the why behind what you did") and revised the message to cover both files changed (code + docs) in the commit. Needed the prompt to get there — not yet a clean unprompted pass. |
| .gitignore and what never belongs in a repo | understood | 2026-09-02 | 2026-09-02 | Task 1.2: wrote a real `.gitignore` line by line (`files.zip`, `.idea/`, `*.class`), with self-written comments. First explanation of ignoring `.class` needed a correction (implied it "protects" tracked `.java` files). Post-task quiz 2026-09-02: correctly stated the shared-vs-personal-state rule for `.idea/` vs `learning/` unprompted, and independently re-derived the class/java independence point without being reminded — "that isn't going to affect what is being sent to the .class files because that is automatically handled when javac compiles." Real transfer, not memorized. |
| GitHub, remotes, push/pull | practicing | 2026-08-31 | 2026-09-03 | Trunk item 1; offsite-backup benefit noted as the piece I missed. Session-opener 2026-09-02: "the data will be saved and available on the web... while you might lose ur laptop resulting in loss of code" — landed the offsite-backup point this time, unprompted. Session-opener 2026-09-03: "sends your committed code to github... stored ready to be access on the web, even if you lose all of your code on your laptop" — repeated cleanly, unprompted. Task 1.5: correctly predicted `remote add origin` and `push` before running them, created the GitHub repo empty on purpose (avoided the README/.gitignore conflict trap), connected and pushed for real, confirmed both commits live on GitHub. Quiz on the `-u` / upstream-tracking flag needed a correction — conflated the branch rename with what `-u` actually does. Real code run, so upgraded to practicing; not `understood` yet given that correction. |
| Branches | introduced | 2026-09-03 | 2026-09-03 | Asked unprompted what `-u` and branches were, between sections. Explained back correctly, unprompted: "i could use a second branch to experiment on a new feature without messing up what already works as im further along in my project." |
| Reading a diff | seed | — | — | |
| Persistence: why data must outlive the process | introduced | 2026-08-31 | 2026-08-31 | "If you can't save that data you are just working with an empty system"; corrected to "app works fine, it just forgets between runs" |
| What a database is | introduced | 2026-08-31 | 2026-08-31 | Trunk item 5 |
| SQL basics (SELECT, INSERT, UPDATE, DELETE) | seed | — | — | |
| Tables, rows, columns, schema | seed | — | — | |
| Primary keys and IDs | seed | — | — | |
| JDBC — Java talking to a database | seed | — | — | |
| SQLite vs Postgres and when each fits | introduced | 2026-08-31 | 2026-08-31 | plan.md decision; pushed back on my "switch if my situation calls for it" — it's a scheduled MVP step |
| CRUD as a concept | seed | — | — | |
| File I/O (reading/writing files) | seed | — | — | |
| HTTP request and response | seed | — | — | |
| Routes and URL paths | seed | — | — | |
| GET vs POST | seed | — | — | |
| HTTP status codes | seed | — | — | |
| Client vs server | introduced | 2026-08-31 | 2026-08-31 | Corrected during frontend explanation: browser on my phone, logic on a server, network in between |
| HTML structure and semantic tags | seed | — | — | |
| HTML forms and form submission | seed | — | — | |
| CSS layout and styling | seed | — | — | |
| Responsive/mobile-friendly basics | seed | — | — | |
| Template engines (Thymeleaf) | introduced | 2026-08-31 | 2026-08-31 | Asked whether it would hold back plain HTML learning; answered — it's HTML plus ~5 attributes |
| Java vs JavaScript are unrelated | introduced | 2026-08-31 | 2026-08-31 | Asked "this language can be combined with java right?" — corrected; they run in different places and talk over the network |
| Input validation | seed | — | — | |
| Server-side vs client-side validation | seed | — | — | |
| Debugging: forming and testing a hypothesis | seed | — | — | |
| Print debugging vs a real debugger | seed | — | — | |
| Unit tests and JUnit | seed | — | — | |
| What is worth testing and what isn't | seed | — | — | |
| Watching a test fail on purpose | seed | — | — | |
| Environment variables | seed | — | — | |
| Secrets never enter source control | seed | — | — | |
| Dev vs production configuration | seed | — | — | |
| Deployment: local → live | introduced | 2026-08-31 | 2026-08-31 | "You don't have to configure everything yourself, you get a computer that's always on... vs learning more concepts and doing more tasks yourself" |
| Managed hosting vs self-managed servers | introduced | 2026-08-31 | 2026-08-31 | Same answer; corrected that I still learn env vars/config/builds, just not sysadmin work |
| Reading deploy logs | seed | — | — | |
| Writing a README | seed | — | — | |
| Refactoring without changing behavior | seed | — | — | |

## 4. AI-Era Practice — Working Well With Tools Like This

Not optional extras. These are the difference between using AI to learn and using
it to avoid learning.

| Concept | Status | Introduced | Last Reviewed | Evidence |
|---|---|---|---|---|
| Writing a project spec before coding | introduced | 2026-08-31 | 2026-08-31 | Directed the creation of project.md; set the teacher-not-coder rule myself |
| Defining an MVP | introduced | 2026-08-31 | 2026-09-02 | Asked for the MVP split and accepted streaks/stats being cut to v2. Session-opener 2026-09-02: "a prototype that gets the foundational idea out of your head and into reality so you can improve on it after you get real world feedback" — clean, unprompted. |
| Scope discipline / resisting feature creep | practicing | 2026-08-31 | 2026-09-02 | Requested pushback; flagged twice for imagining users; task 0.1: included social features, reminders, notes, streaks, genre, rating on paper — accepted all cuts when challenged against MVP feature list. Task 0.2: self-caught a redundant "Want to Reread" status before I flagged it. Task 0.4: mid-discussion, surfaced a possible fifth "On Hold" status and immediately parked it unprompted ("That's an idea for later though"); also caught that a "book read in the past" idea was really the separate reading-sessions feature, not a date-added validity rule. Asked for a standing rule: auto-park any new idea without being reminded (see [[feedback-auto-park-ideas]]). |
| Parking lot for deferred ideas | introduced | 2026-08-31 | 2026-08-31 | project.md parking lot written so ideas stop nagging |
| Sequencing work so each layer builds on the last | introduced | 2026-08-31 | 2026-08-31 | Accepted console-before-web ordering in plan.md |
| Agent memory files (project.md, plan.md, this file) | introduced | 2026-08-31 | 2026-08-31 | Instructed that every future session starts by reading project.md |
| Explaining a concept back in my own words | practicing | 2026-08-31 | 2026-08-31 | Did it four times — source control, persistence, application logic, frontend — and absorbed corrections on two |
| Making technical decisions with stated reasoning | practicing | 2026-08-31 | 2026-09-01 | Locked 5 decisions, each with a why in my own words; chose Render over Railway on cost. Task 0.3: chose to block Want to Read → Finished for accountability, explicitly naming the tradeoff and reserving the right to revisit. |
| Evaluating alternatives and tradeoffs | practicing | 2026-08-31 | 2026-09-02 | Weighed alternatives on all 5 decisions. Task 0.3: weighed strict-accountability vs. user-flexibility on the Want to Read → Finished transition and picked a side with reasoning, not just listed options. Task 0.5: chose delete-and-re-add over a status "redo" feature after seeing that editable status would quietly undo 0.3's accountability rule — picked the option consistent with an earlier decision over the more convenient-seeming one. |
| Asking AI to teach rather than solve | introduced | 2026-08-31 | 2026-08-31 | Set the rule in project.md before any code existed |
| Verifying AI claims instead of accepting them | seed | — | — | |
| Reviewing AI-generated code before using it | seed | — | — | |
| Reading a diff critically | seed | — | — | |
| Knowing when to ask vs when to struggle first | seed | — | — | |
| Rubber-duck debugging | seed | — | — | |
| Keeping the knowledge graph honest | seed | — | — | |

---

## Current State

| Status | Count |
|---|---|
| understood | 2 |
| practicing | 5 |
| introduced | 24 |
| seed | 63 |

**First two `understood` concepts:** Classes as blueprints and Objects as instances
— both landed while working out the class/object distinction during task 0.1's
default-value discussion. Neither is eligible for re-quiz for 30 days.

**Next quiz eligibility:** the four `practicing` entries (explaining concepts back,
making technical decisions, scope discipline, data model design), plus newly
`introduced` items — Constructors and Integer/double division — once there's code
to apply them to.

**First thing that will move:** low-level Java. Sections 1–3 will push a large
block of section 1 from `seed` to `practicing` quickly, because writing the Book
class means using classes, fields, constructors, enums, and ArrayList in the same
sitting.
