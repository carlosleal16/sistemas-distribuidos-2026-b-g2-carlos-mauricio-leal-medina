<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.
     Your weekly grade is read AUTOMATICALLY from this file:
       07-week/hu-status/README.md  (inside YOUR fork). English. -->

# Weekly Status - Week 07

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Carlos Mauricio Leal Medina
- GITHUB_USER: carlosleal16
- TEAM: Barberssas
- SPRINT_GOAL: Keep the documentation tracking of the ecosystem (DOCS) in sync while the real implementation starts in CODE.
<!-- CONFIG-END -->

## 1. User stories worked this week
| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| N/A | No product story was worked this week; the only change was governance/tracking documentation in DOCS, which does not map to a story of `04-requirements` | done | [PR #15](https://github.com/code-corhuila/barber-saas-docs/pull/15) (commit [`3f4822d`](https://github.com/code-corhuila/barber-saas-docs/commit/3f4822d785eb134433f8aeecd45951965dcd4a4e)) |

## 2. My individual contribution
- **[`barber-saas-docs#15`](https://github.com/code-corhuila/barber-saas-docs/pull/15): `docs(archive): index MVP cadence and weekly-tracking pointers`**
  (branch `docs/archive-mvp-weekly-index`, commit `3f4822d`, 2026-09-17, 1 file, +39).
  - Added `99-archive/mvp-weekly-index.md`, which gathers in one place where the MVP milestones
    and the weekly academic tracking live, without duplicating content owned by another document.
  - My first pull request in the project. It was approved by `ariel5253` and by a teammate.
  - On 2026-09-28 the team closed it without merging: it had been a test of the pull request
    flow and its content was not needed on `main` (comment on the PR).
- No commits of mine in WEEKLY or CODE between 2026-09-14 and 2026-09-17 (checked with
  `git log --since/--until` in the three repositories).

## 3. Blockers and risks
- **CODE (`barber-saas`) still has no real code.** It only has a `README.md`; the backend
  (Java/Spring Boot) and mobile (Expo) subprojects described in the documentation do not exist
  yet. Without them there are no product stories to report with real evidence.
- **SPEC-007 is still blocked**: the repositories of Juan Pablo Borrero and mine are not yet in
  the ecosystem map (their remotes are not confirmed in `_ecosistema/SPEC-PLAN-PROMPT.md`).
- Risk of reporting stories without linkable evidence if the implementation in CODE does not
  start before week 08.

## 4. Plan for next week
- Solve SPEC-007 (add Juan Pablo's repositories and mine to the ecosystem map) with the missing
  remote URLs.
- Start the real implementation in CODE (walking skeleton of the backend or the mobile app) so
  the next report has verifiable product stories.
- Get the `docs/archive-mvp-weekly-index` pull request reviewed by the teacher (CODEOWNERS).

## 5. Compliance self-check
- [x] Conventional Commits - `type(scope): summary` (the week's commit follows the format)
- [ ] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...): N/A, there was no code work this week; the documentation change went through `docs/…` → PR #15
- [ ] Testable acceptance criteria: N/A, no product story this week
- [ ] Tests added/updated (unit / integration): N/A, no code this week
- [ ] DDD / hexagonal boundaries respected (domain has no I/O): N/A, CODE has no code yet
- [x] No secrets; config via environment variables (no secrets in the week's only change)

## 6. Evidence links
- [`barber-saas-docs#15`](https://github.com/code-corhuila/barber-saas-docs/pull/15): index MVP cadence and weekly-tracking pointers (approved, then closed by the team as a test pull request)
- https://github.com/code-corhuila/barber-saas-docs/commit/3f4822d785eb134433f8aeecd45951965dcd4a4e
- https://github.com/code-corhuila/barber-saas-docs/tree/docs/archive-mvp-weekly-index
