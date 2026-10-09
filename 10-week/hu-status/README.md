<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.
     Your weekly grade is read AUTOMATICALLY from this file:
       10-week/hu-status/README.md  (inside YOUR fork). English. -->

# Weekly Status - Week 10

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Carlos Mauricio Leal Medina
- GITHUB_USER: carlosleal16
- TEAM: Barberssas
- SPRINT_GOAL: Build my third domain, `notifications` (`-db` on MongoDB, `-api` in Python 3.12 + FastAPI, `-app` in Ionic Angular), close the password-recovery e-mail (HU-AUTH-002) end to end, and promote my 9 repositories through `develop` → `qa` → `release.2.0.0` with `git cherry-pick -x`, leaving the release pull request to `main` open for the teacher's approval before the checkpoint-2 delivery (2026-10-13).
<!-- CONFIG-END -->

## 1. User stories worked this week
| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| HU-AUTH-002 | As a client, barber or admin who forgot my password, I want a 6-digit code sent to my e-mail, so that I can set a new password without exposing whether my e-mail is registered | done (QA, release 2.0.0; last fix in Dev) | `notifications-api` [#10](https://github.com/code-corhuila/barber-saas-notifications-api/pull/10) + [#11](https://github.com/code-corhuila/barber-saas-notifications-api/pull/11) (opened by Daniel Cerquera; I applied the review findings and merged them), [#16](https://github.com/code-corhuila/barber-saas-notifications-api/pull/16) (expired code ignored); `notifications-db` [#4](https://github.com/code-corhuila/barber-saas-notifications-db/pull/4) (`processed_event`, by Daniel); `infra-postgres` [#29](https://github.com/code-corhuila/barber-saas-infra-postgres/pull/29) (`SMTP_*` names). Refs [`barber-saas-docs#6`](https://github.com/code-corhuila/barber-saas-docs/issues/6) |
| HU-NOTIF-001 | As a client or barber, I want an in-app inbox (and a push when FCM is set) for my appointment events, so that I learn about a booking, a change or a cancellation without asking the barbershop | done (QA, release 2.0.0) | `notifications-db` [#3](https://github.com/code-corhuila/barber-saas-notifications-db/pull/3); `notifications-api` [#3](https://github.com/code-corhuila/barber-saas-notifications-api/pull/3)–[#9](https://github.com/code-corhuila/barber-saas-notifications-api/pull/9); `notifications-app` [#4](https://github.com/code-corhuila/barber-saas-notifications-app/pull/4), [#5](https://github.com/code-corhuila/barber-saas-notifications-app/pull/5); `api-gateway` [#11](https://github.com/code-corhuila/barber-saas-api-gateway/pull/11); `front` [#13](https://github.com/code-corhuila/barber-saas-front/pull/13), [#14](https://github.com/code-corhuila/barber-saas-front/pull/14); `infra-postgres` [#22](https://github.com/code-corhuila/barber-saas-infra-postgres/pull/22). Refs [`#5`](https://github.com/code-corhuila/barber-saas-docs/issues/5) (also the notices of [`#21`](https://github.com/code-corhuila/barber-saas-docs/issues/21) and [`#23`](https://github.com/code-corhuila/barber-saas-docs/issues/23)) |
| HU-SHOP-002 | As an admin (barbershop owner), I want to add barbers to my barbershop with an initial password and see their names in the team, the schedule and the public catalog, so that clients can pick a real barber | done (QA, release 2.0.0) | `barbershop-db` [#5](https://github.com/code-corhuila/barber-saas-barbershop-db/pull/5); `barbershop-api` [#17](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/17); `barbershop-app` [#9](https://github.com/code-corhuila/barber-saas-barbershop-app/pull/9), [#10](https://github.com/code-corhuila/barber-saas-barbershop-app/pull/10); `schedule-app` [#7](https://github.com/code-corhuila/barber-saas-schedule-app/pull/7). Refs [`#76`](https://github.com/code-corhuila/barber-saas-docs/issues/76) |
| HU-AUTH-003 / HU-SADMIN-001 | As a future owner, I want my barbershop created by the onboarding saga, and as a super-admin I want to operate barbershops, so that both reach `barbershop-api` only through its internal operations | done (QA, release 2.0.0) | `barbershop-api` [#16](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/16) (create/remove for the saga), [#20](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/20) (platform-admin operations). Refs [`#7`](https://github.com/code-corhuila/barber-saas-docs/issues/7), [`#12`](https://github.com/code-corhuila/barber-saas-docs/issues/12) |
| HU-APPT-001 | As a client, I want availability that subtracts the booked appointments, so that I can never book a taken slot | done (QA, release 2.0.0) | `schedule-api` [#15](https://github.com/code-corhuila/barber-saas-schedule-api/pull/15), [#16](https://github.com/code-corhuila/barber-saas-schedule-api/pull/16) (busy slots read from `appointment-api` with schedule's own service token). Refs [`#4`](https://github.com/code-corhuila/barber-saas-docs/issues/4) |
| HU-SHOP-001-A / HU-SHOP-001-B | As an admin, I want to configure my service catalog and my barbers' weekly schedules from screens that are clear to use, so that the data the client sees is right | done (QA, release 2.0.0) | `barbershop-app` [#11](https://github.com/code-corhuila/barber-saas-barbershop-app/pull/11), [#13](https://github.com/code-corhuila/barber-saas-barbershop-app/pull/13); `schedule-app` [#8](https://github.com/code-corhuila/barber-saas-schedule-app/pull/8), [#10](https://github.com/code-corhuila/barber-saas-schedule-app/pull/10). Refs [`#67`](https://github.com/code-corhuila/barber-saas-docs/issues/67), [`#68`](https://github.com/code-corhuila/barber-saas-docs/issues/68) |
| REL-2.0.0 | As the team, we want MVP 2 promoted from `develop` to `qa` and to `release.2.0.0` by re-application, with a CHANGELOG and a release pull request to `main`, so that the teacher can approve checkpoint 2 with a verifiable trail | done (Main, tag `v2.0.0`) | 36 pull requests in my 9 repos, table in section 2; the 9 release pull requests were approved by `ariel5253` and merged to `main` on 2026-10-09. Refs [`#109`](https://github.com/code-corhuila/barber-saas-docs/issues/109) |

> The Status column says the environment each story reached (norm 16.7): "QA, release 2.0.0" means
> its commits are in `qa` and in `release.2.0.0` with their `(cherry picked from commit …)` trail;
> `main` received them through the release pull requests on 2026-10-09 (section 2). The
> repository-rename chores of the week (`barber-saas-infra` → `barber-saas-infra-postgres`,
> Barber Saas headers) reference [`#59`](https://github.com/code-corhuila/barber-saas-docs/issues/59)
> and are not a story; they are listed in section 2.

## 2. My individual contribution
- **Today (2026-10-08): the password-recovery e-mail, end to end (HU-AUTH-002).**
  - Daniel Cerquera had opened `notifications-api` #10 (domain and use case) and #11 (SMTP adapter
    and `processed_event` in MongoDB) in my repository, but they were not merged, so the story was
    still missing in `develop`. Before merging, I checked the contract end to end:
    `identity-auth-api` writes `PasswordResetRequested` with `userId, email, fullName, code,
    expiresAt`, the worker routes it to notifications (`routing.py`), and the consumer expects
    exactly those fields.
  - **Applied the automated review before merging (norm 9.9), tests first:**
    - #10 finding 1: if the e-mail was sent but recording the event failed, every retry of the
      worker resent the code. Now it answers `PROCESSED` and only logs the failure
      (`3d5cc3c` test → `f45c8b9` fix).
    - #11 finding 1: `SMTP_SECURITY=none` together with `SMTP_USERNAME` would log in in
      cleartext. Now it is refused at startup (`593e73e` → `272edeb`).
    - Composition tests for "no SMTP" and "cleartext credentials" (`79ee302`).
    - Every finding was answered in writing on the PR: applied, already handled, or not applied
      with the reason (the STARTTLS/SSL end-to-end test, which would need a test CA in CI).
    - #10 and #11 merged to `develop` with green CI.
  - `infra-postgres` [#29](https://github.com/code-corhuila/barber-saas-infra-postgres/pull/29):
    `SMTP_*` names and placeholders in `env/{dev,qa,main}.env.example`; no value is versioned and
    the `no-secrets` check passed. Merged at `39fca13`.
  - **Tested on the real platform.** Brought up postgres, mongo, the gateway, identity-auth,
    the worker and notifications with `./scripts/up.sh dev`. Registered a client (`201`) and
    requested the code (`202`). The worker delivered the event (`done: 1, failed: 0`), the code
    arrived by Gmail SMTP, and MongoDB keeps only the event id in `processed_event`, never the
    code. The TTL index `ttl_processed_event_processed_at` is 2,592,000 s (30 days).
  - `notifications-api` [#16](https://github.com/code-corhuila/barber-saas-notifications-api/pull/16):
    an event whose `expiresAt` already passed answers `IGNORED` and sends nothing, and it no
    longer answers `503` without a mail server. This closes #10 finding 3. Tests `3d97edc` →
    fix `d76f7d5`; 5 changed lines outside tests.
- **Today: release 2.0.0 in my 9 repositories** (`barbershop-{db,api,app}`,
  `schedule-{db,api,app}`, `notifications-{db,api,app}`), issue
  [`#109`](https://github.com/code-corhuila/barber-saas-docs/issues/109):
  1. **To `develop`** (`chore/release-2.0.0-prep`): CI runs only on pull requests to `develop`
     and `qa`, with a 10-minute timeout. A new `CHANGELOG.md` (Keep a Changelog + SemVer) is
     generated from `git log --no-merges origin/qa..origin/develop`, grouped by commit type and
     listing every referenced story. Green CI, rebase and merge.
  2. **To `qa`** (`hu-mvp2-qa`): every non-merge commit of `origin/qa..origin/develop`
     re-applied in topological order with `git cherry-pick -x`. There were no conflicts, and
     `git diff --quiet origin/develop HEAD` was checked before the push. Green CI, rebase and merge.
  3. **To `release.2.0.0`** (cut from `main`, filled from `hu-mvp2-release`): the same procedure
     from `qa`, with an identical tree.
  4. **To `main`**: release pull request with stories, scope, deployment, rollback and a trail
     table showing the `qa` and the `develop` sha of every commit (norm 11.3). `ariel5253`
     approved the 9 of them, and I merged them (rebase and merge) on 2026-10-09 between 00:48
     and 00:49. `main` has the same tree as `release.2.0.0`, no merge commit, and every new commit
     carries its trail. Daniel Cerquera created the annotated tag `v2.0.0` on that commit and the
     GitHub release `v2.0.0 — MVP 2 (corte 2)` in each repository (norm 11.5).

  | Repo | PR develop | PR qa | PR release | PR main | Commits promoted | Same tree | Trail OK |
  |---|---|---|---|---|---|---|---|
  | barbershop-api | [#21](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/21) | [#22](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/22) | [#23](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/23) | [#24](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/24) merged | 89 | yes | yes |
  | barbershop-app | [#15](https://github.com/code-corhuila/barber-saas-barbershop-app/pull/15) | [#16](https://github.com/code-corhuila/barber-saas-barbershop-app/pull/16) | [#17](https://github.com/code-corhuila/barber-saas-barbershop-app/pull/17) | [#18](https://github.com/code-corhuila/barber-saas-barbershop-app/pull/18) merged | 45 | yes | yes |
  | barbershop-db | [#8](https://github.com/code-corhuila/barber-saas-barbershop-db/pull/8) | [#9](https://github.com/code-corhuila/barber-saas-barbershop-db/pull/9) | [#10](https://github.com/code-corhuila/barber-saas-barbershop-db/pull/10) | [#11](https://github.com/code-corhuila/barber-saas-barbershop-db/pull/11) merged | 22 | yes | yes |
  | schedule-api | [#19](https://github.com/code-corhuila/barber-saas-schedule-api/pull/19) | [#20](https://github.com/code-corhuila/barber-saas-schedule-api/pull/20) | [#21](https://github.com/code-corhuila/barber-saas-schedule-api/pull/21) | [#22](https://github.com/code-corhuila/barber-saas-schedule-api/pull/22) merged | 75 | yes | yes |
  | schedule-app | [#13](https://github.com/code-corhuila/barber-saas-schedule-app/pull/13) | [#14](https://github.com/code-corhuila/barber-saas-schedule-app/pull/14) | [#15](https://github.com/code-corhuila/barber-saas-schedule-app/pull/15) | [#16](https://github.com/code-corhuila/barber-saas-schedule-app/pull/16) merged | 38 | yes | yes |
  | schedule-db | [#7](https://github.com/code-corhuila/barber-saas-schedule-db/pull/7) | [#8](https://github.com/code-corhuila/barber-saas-schedule-db/pull/8) | [#9](https://github.com/code-corhuila/barber-saas-schedule-db/pull/9) | [#10](https://github.com/code-corhuila/barber-saas-schedule-db/pull/10) merged | 20 | yes | yes |
  | notifications-api | [#12](https://github.com/code-corhuila/barber-saas-notifications-api/pull/12) | [#13](https://github.com/code-corhuila/barber-saas-notifications-api/pull/13) | [#14](https://github.com/code-corhuila/barber-saas-notifications-api/pull/14) | [#15](https://github.com/code-corhuila/barber-saas-notifications-api/pull/15) merged | 46 | yes | yes |
  | notifications-app | [#6](https://github.com/code-corhuila/barber-saas-notifications-app/pull/6) | [#7](https://github.com/code-corhuila/barber-saas-notifications-app/pull/7) | [#8](https://github.com/code-corhuila/barber-saas-notifications-app/pull/8) | [#9](https://github.com/code-corhuila/barber-saas-notifications-app/pull/9) merged | 9 | yes | yes |
  | notifications-db | [#5](https://github.com/code-corhuila/barber-saas-notifications-db/pull/5) | [#6](https://github.com/code-corhuila/barber-saas-notifications-db/pull/6) | [#7](https://github.com/code-corhuila/barber-saas-notifications-db/pull/7) | [#8](https://github.com/code-corhuila/barber-saas-notifications-db/pull/8) merged | 15 | yes | yes |

  - **Checked after the merges, on the remote.** Every commit of `qa` and of `release.2.0.0`
    above `main` carries `(cherry picked from commit …)`. The cited sha is an ancestor of
    `origin/develop` (for `qa`) or of `origin/qa` (for the release). Neither branch has a merge
    commit, and develop, qa and the release have the same tree. `notifications-api` `develop` is
    now 2 commits ahead, which is #16, merged after the cut.
- **Earlier this week (2026-10-05 → 07).**
  - Repository-rename and header chores, one small pull request per repository (Refs
    [`#59`](https://github.com/code-corhuila/barber-saas-docs/issues/59)):
    - `chore: use the new repository name barber-saas-infra-postgres`: [`barbershop-api#18`](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/18), [`barbershop-app#12`](https://github.com/code-corhuila/barber-saas-barbershop-app/pull/12), [`barbershop-db#6`](https://github.com/code-corhuila/barber-saas-barbershop-db/pull/6), [`schedule-api#17`](https://github.com/code-corhuila/barber-saas-schedule-api/pull/17), [`schedule-app#9`](https://github.com/code-corhuila/barber-saas-schedule-app/pull/9), [`schedule-db#5`](https://github.com/code-corhuila/barber-saas-schedule-db/pull/5).
    - `chore: Barber Saas header and the barber-saas-infra-postgres name`: [`barbershop-api#19`](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/19), [`barbershop-app#14`](https://github.com/code-corhuila/barber-saas-barbershop-app/pull/14), [`barbershop-db#7`](https://github.com/code-corhuila/barber-saas-barbershop-db/pull/7), [`notifications-api#2`](https://github.com/code-corhuila/barber-saas-notifications-api/pull/2), [`notifications-app#2`](https://github.com/code-corhuila/barber-saas-notifications-app/pull/2), [`notifications-db#2`](https://github.com/code-corhuila/barber-saas-notifications-db/pull/2), [`schedule-api#18`](https://github.com/code-corhuila/barber-saas-schedule-api/pull/18), [`schedule-app#11`](https://github.com/code-corhuila/barber-saas-schedule-app/pull/11), [`schedule-db#6`](https://github.com/code-corhuila/barber-saas-schedule-db/pull/6).
  - [`notifications-app#3`](https://github.com/code-corhuila/barber-saas-notifications-app/pull/3)
    (the whole inbox in one pull request, 752 lines without tests) was closed and split into #4
    (CI and templates) and #5 (the inbox screen), to stay within 400 lines (9.2).
  - Built the `notifications` domain with the tests first:
    - `notifications-db` #3: collections, indexes and roles in MongoDB.
    - `notifications-api` #3–#9: inbox core, HTTP with RS256, envelope and correlation id,
      domain events from the worker, device tokens, composition root, MongoDB persistence
      behind the same ports, and FCM push.
    - `notifications-app` #4–#5: Ionic Angular inbox with unread notices and mark as read.
    - The shared points, one change each: gateway route file #11, shell mount and "Avisos" tab
      `front` #13–#14, infra include #22.
  - In `barbershop` and `schedule`:
    - Internal operations for the onboarding saga and platform-admin (#16, #20).
    - The barber's name and photo snapshot (`barbershop-db` #5, `barbershop-api` #17).
    - Adding barbers and showing their names (apps #9, #10, `schedule-app` #7).
    - Busy slots read from `appointment-api` (#15, #16).
    - UI fixes in both apps (#13, `schedule-app` #10).

## 3. Blockers and risks
- ~~Release 2.0.0 is not in `main` yet~~: solved on 2026-10-09. The 9 release pull requests were
  approved by `ariel5253` and merged, and `v2.0.0` is tagged on `main` (norm 11.5).
- **Pull request size (norm 9.2, 400 lines).**
  - My development pull requests this week are within the limit; #16, for example, has 5 lines
    outside tests.
  - The **promotion** pull requests are not: 533 to 3,878 lines without tests, because each one
    re-applies all of MVP 2 at once, the same way the team promoted its other repositories.
    They cannot be undone without rewriting history (a grave fault, 9.6).
  - The reasoning is written on each release pull request: no new change is reviewed there, and
    a release is the whole release (11.2). Next checkpoint: promote to `qa` one story at a time
    (10.2).
  - Two older development PRs are slightly over: `barbershop-api#3` (416) and
    `notifications-db#3` (482).
- **`CODEOWNERS` comment line.** On 2026-10-06 the team's header commit (`2f73387` in
  `barbershop-api`, the same one in my other 8 repositories) changed the comment of
  `.github/CODEOWNERS` from `library-docs` to `barber-saas-docs`.
  - The rule `* @ariel5253` is unchanged.
  - The teacher already approved the same change into `main` in `identity-auth-api` and `worker`.
  - It stays as it is, because reverting it would be one more edit to the file. It is still
    named here because modifying `CODEOWNERS` is item 13.7.
- **Branch names.**
  - `qa/…` (norm 6.3.1) cannot be pushed in a repository that has a `qa` branch: git refuses a
    ref `qa` and a ref `qa/x` together (`directory file conflict`, tested today).
  - The team uses `hu-mvp2-qa`, `hu-mvp2-release` and `release.2.0.0` in every repository, and
    the template's own self-check names `hu-xxx-dev -> develop`.
- **`./scripts/up.sh dev` on Windows (Git Bash)** rewrites `/docker-entrypoint-initdb.d/…` as a
  Windows path; it works with `MSYS_NO_PATHCONV=1`. The full platform does not build because the
  local clone of `finance-inventory-api` (not my repository) has uncommitted code that does not
  compile. I did not touch it; the services of my stories ran.

## 4. Plan for next week
- ~~Get the 9 release pull requests approved and tag `v2.0.0` in each repository (11.5)~~: done on
  2026-10-09.
- Promote `notifications-api` #16 to `qa`, as one story in one promotion pull request within 400
  lines.
- `notifications-app`: push registration (device token) from the Capacitor app, once
  `FCM_SERVICE_ACCOUNT_JSON` exists in dev.
- HU-SEC-001 in my services: fail-fast validation of required variables and a pre-commit secret
  scan (pending since week 09).
- Ask the infra owner to document `MSYS_NO_PATHCONV=1` for Windows in the infra README.

## 5. Compliance self-check
- [x] Conventional Commits - `type(scope): summary`. The CHANGELOG generator rejects any
      non-conventional subject, and all 9 ranges passed. Examples: `test(events): specify that an
      expired reset code is ignored and not e-mailed` → `fix(events): ignore a password-reset
      event whose code already expired`; `ci: run only on pull requests to develop and qa, for
      at most 10 minutes`.
- [x] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...):
      `chore/…`/`fix/…` → `develop`, `hu-mvp2-qa` → `qa`, `hu-mvp2-release` → `release.2.0.0`,
      and `release.2.0.0` → `main` (open). Nothing was committed directly to a permanent branch,
      no permanent branch was merged into another, and every promotion used `cherry-pick -x`.
- [x] Testable acceptance criteria: each fix states the failure it prevents and has a test for
      it (e.g. "an expired code is ignored even without a mail server"). The e-mail story was
      also verified on the running platform (status codes, worker counters, MongoDB record).
- [x] Tests added/updated (unit / integration): 5 new tests in `notifications-api` today, each
      committed before its fix; `lint-imports` keeps the domain free of frameworks; CI green on
      every development and `qa` pull request.
- [x] DDD / hexagonal boundaries respected (domain has no I/O): the expiry rule lives in
      `domain/model/password_reset.py` and the use case; SMTP is an outbound adapter behind the
      `EmailSender` port; the collections live only in `notifications-db`.
- [x] No secrets; config via environment variables: `SMTP_PASSWORD` lives only in the
      git-ignored `env/dev.env` (checked with `git check-ignore` and in the history), the
      examples carry names only, and cleartext SMTP credentials are refused.
- [ ] PR at most 400 changed lines (9.2): **not met by the promotion PRs** (see Blockers).

## 6. Evidence links
- Issue [`#109`](https://github.com/code-corhuila/barber-saas-docs/issues/109) (DOCS): Release 2.0.0 (MVP 2). Promote develop → qa → main and tag v2.0.0
- Issue [`#6`](https://github.com/code-corhuila/barber-saas-docs/issues/6): HU-AUTH-002 (recover a forgotten password via e-mail)
- Issue [`#5`](https://github.com/code-corhuila/barber-saas-docs/issues/5): HU-NOTIF-001 (receive appointment notifications)
- Issue [`#76`](https://github.com/code-corhuila/barber-saas-docs/issues/76): HU-SHOP-002 (add barbers to my barbershop)
- Issues [`#4`](https://github.com/code-corhuila/barber-saas-docs/issues/4), [`#7`](https://github.com/code-corhuila/barber-saas-docs/issues/7), [`#12`](https://github.com/code-corhuila/barber-saas-docs/issues/12), [`#67`](https://github.com/code-corhuila/barber-saas-docs/issues/67), [`#68`](https://github.com/code-corhuila/barber-saas-docs/issues/68): HU-APPT-001, HU-AUTH-003, HU-SADMIN-001, HU-SHOP-001-A, HU-SHOP-001-B
- [`notifications-api#10`](https://github.com/code-corhuila/barber-saas-notifications-api/pull/10), [`#11`](https://github.com/code-corhuila/barber-saas-notifications-api/pull/11): the review answers ("What was done with each finding") are in the PR comments
- [`notifications-api#16`](https://github.com/code-corhuila/barber-saas-notifications-api/pull/16): expired reset code ignored
- [`infra-postgres#29`](https://github.com/code-corhuila/barber-saas-infra-postgres/pull/29): `SMTP_*` names in the env examples
- Release pull requests to `main`, approved and merged (each with the norm 9.2 note in its comments): [`barbershop-api#24`](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/24), [`barbershop-app#18`](https://github.com/code-corhuila/barber-saas-barbershop-app/pull/18), [`barbershop-db#11`](https://github.com/code-corhuila/barber-saas-barbershop-db/pull/11), [`schedule-api#22`](https://github.com/code-corhuila/barber-saas-schedule-api/pull/22), [`schedule-app#16`](https://github.com/code-corhuila/barber-saas-schedule-app/pull/16), [`schedule-db#10`](https://github.com/code-corhuila/barber-saas-schedule-db/pull/10), [`notifications-api#15`](https://github.com/code-corhuila/barber-saas-notifications-api/pull/15), [`notifications-app#9`](https://github.com/code-corhuila/barber-saas-notifications-app/pull/9), [`notifications-db#8`](https://github.com/code-corhuila/barber-saas-notifications-db/pull/8)
- `CHANGELOG.md` on `develop` of each of the 9 repositories: section `[2.0.0] - 2026-10-08`

## 7. Status against the rubric (checkpoint 2)
Checked today on the remote of my 9 repositories. The last full `/normas-check todos` report is
from 2026-09-28, so it is not reused here and no point estimate is invented from it.

| Dimension | What was checked | Result |
|---|---|---|
| 3 — Promotion and traceability | merge commits in `qa` and the release; `-x` trail on every promoted commit; cited sha exists in its source branch (15.3, 15.4, 15.6) | 0 merges; 359 promoted commits, all with a valid trail |
| 6 — Release | release cut from `main`, filled from `qa`, PR with stories, trail, scope, deployment and rollback (11.1–11.3) | done: approved by `ariel5253`, merged to `main` and tagged `v2.0.0` |
| 5 — Data isolation | migrations only in the `-db`, no other domain's database (5.2.1, 15.7) | the `-api` and `-app` repos have no migration files; cross-domain reads go through APIs |
| 2 / 7 — Branches and governance | prefixes, PR per environment, review answers (6.3, 9.9) | met, except the `qa/` prefix (impossible next to `qa`) and the 400-line limit on promotions |
| Grave faults (13) | merge between permanent branches, missing `-x`, fake trail, secrets, history rewrite, CODEOWNERS | **none**, with the `CODEOWNERS` comment line declared in Blockers |
