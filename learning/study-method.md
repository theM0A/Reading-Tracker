# Study Method — Reading Tracker Project

This file documents the daily working rhythm and understanding-checks for this
project. It exists so Claude Code (and future me) can hold me to this
structure instead of quietly drifting into "just get it working" mode.

## Why this file exists

The project's core rule is "teacher/debugger, not code generator." This doc is
the operational version of that rule — what a day of work is supposed to look
like, and how I verify I actually learned something instead of just producing
working code.

## Daily structure (4–5 hours available)

1. **Morning session (~2 hrs) — one task, attempted cold.**
   No AI help until genuinely stuck (not just "slightly unsure"). Sitting in
   confusion for a while is expected and is where the actual learning happens.

2. **Real break.** Not scrolling. Let it consolidate.

3. **Afternoon session (~1.5–2 hrs) — two parts:**
   - **Recall first:** explain or rebuild the core logic from *this morning's*
     task from memory, no looking at the code. This is the single highest-leverage
     step — it catches "I copied the pattern but don't actually get it" before
     it compounds.
   - **Then** move to the next task in the build plan.

4. **Last ~30 min — update `knowledge-graph.md`.**
   Mark what moved from "learning" to "understood" today. Note anything that
   still feels shaky. This is a running record of real growth, and useful
   interview material later ("here's how I tracked my own learning").

## Guardrail: the stuck-timer rule

If a task eats more than **~2.5–3 hours in one sitting with no real progress**,
that's the signal to stop and ask Claude Code for help (in teacher mode) —
not to keep grinding. The goal is productive struggle, not burnout or
hour-padding.

This pairs with the existing "when I broke something" protocol in
`CLAUDE.md`: that one governs *debugging after something breaks*; this one
governs *when to ask for help before frustration turns into copy-pasting*.

## Testing understanding: targeted rebuild, not full rebuild

A full ground-up rebuild of the whole MVP is tempting after finishing, but it
over-practices the parts already done many times (Book class, basic CRUD) and
eats time that's better spent on the parts that actually need re-testing.

**Better version — pick 2–3 checkpoints at the real conceptual jumps:**

- Build the `Book` class + ArrayList CRUD from a blank file
- Wire up JDBC to SQLite from scratch
- Build one Spring Boot controller + Thymeleaf view from scratch

**Rules for the rebuild:**
- No looking at old code or the plan while attempting
- Time-box each checkpoint: 30–45 minutes
- If I can't get there in time, that's data, not failure — it tells me
  exactly what to review
- Compare against the original *after*, not during. A difference isn't
  automatically wrong — but if I can't explain *why* the original did
  something a certain way, that's the gap to close
- Do each checkpoint **once**. Looping doesn't add much past the first honest
  attempt.

## Testing understanding: explain-out-loud (cheaper, same signal)

For everything that isn't one of the 2–3 checkpoints above: explain the piece
out loud or in writing, as if teaching someone else, cold, with no notes.

This catches the same kind of gap as rebuilding, faster. If I can't explain
*why* the DAO pattern separates persistence from the `Book` model, rebuilding
that code won't fix the gap either — I need the explanation step regardless.

## The Feynman technique (use this as the "explain-out-loud" method)

This is the specific technique behind the explain-out-loud step above — worth
naming so it's easy to actually do instead of vaguely "explaining stuff":

1. Pick the concept or piece of code (e.g. "why the DAO pattern exists here").
2. Explain it in plain language, like you're teaching it to someone with no
   context — no jargon as a crutch. If you catch yourself using a term you
   can't unpack, that's the gap.
3. Wherever you get stuck or hand-wavy, stop — that's the exact spot to go
   re-study, not the whole topic.
4. Re-explain it simpler. If you can't simplify it further and it still holds
   together, you actually understand it.

This is a 5–10 minute exercise, not a project. Use it inline during the
afternoon recall step and the phase-boundary teach-backs — it's the *method*,
not an extra thing to schedule on top of them.

## A couple more considerations (light-touch, don't over-schedule these)

- **Don't turn every technique into a checklist item.** The point of all of
  this is to catch real gaps, not to perform a ritual. If a day's recall is
  solid and fast, don't manufacture struggle — move on.
- **Sleep matters more than it seems.** Concept consolidation happens between
  sessions, not just during them. If a concept isn't sticking, a night's sleep
  before re-attempting is often worth more than another hour of grinding.
- **It's fine to skip a technique on a given day.** These are tools, not
  obligations — the "one task per sitting" and stuck-timer rules already do
  the heavy lifting. Everything else here is in service of those, not a
  replacement for actually building the project.

## Additions to strengthen understanding further

A few things not yet in the plan, worth folding in:

- **Keep a "why, not what" log per section.** Instead of commit messages like
  "add Book class," note the decision behind it — e.g. "chose ArrayList over
  array because size is unknown at compile time." Five words is enough. This
  builds a paper trail of *reasoning*, which is what interviewers actually
  probe for, and it's the fastest way to catch decisions I made on autopilot.

- **Bug journal.** Every time something breaks and I fix it, write one line:
  what broke, what I assumed that was wrong, what the actual cause was. Over
  a project this size, patterns emerge (e.g. "I keep assuming mutable state is
  safe to share") that are worth knowing about yourself.

- **Teach-back checkpoint at each phase boundary, not just per-task.** In
  addition to daily recall, at the end of Phase 1 (console app) and Phase 2
  (SQLite), explain the *whole phase* end-to-end from memory: what problem it
  solved, what the main classes are and why, what you'd change if starting
  over. This catches gaps that only show up at the "how do the pieces fit
  together" level, which daily recall won't surface.

- **One deliberate "read code, predict output" drill per week.** Take a
  chunk of your own code from a few days prior, cover the output, and predict
  what it does before running it. Cheap, fast, and catches drift between what
  you think your code does and what it actually does.

- **Resist the urge to refactor "understood" code during rebuild checkpoints.**
  The checkpoint is a memory test, not a code-quality pass. If you spot a
  real improvement while rebuilding, note it in the parking lot or a TODO —
  don't chase it mid-checkpoint, or you'll be testing your refactoring
  instincts instead of your recall.
