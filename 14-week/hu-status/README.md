<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.
     Your weekly grade is read AUTOMATICALLY from this file:
       14-week/hu-status/README.md  (inside YOUR fork). English. -->

# Weekly Status - Week 14

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Carlos Mauricio Leal Medina
- GITHUB_USER: carlosleal16
- TEAM: Barberssas
- SPRINT_GOAL: Close security story #165 contract-first (contract, `barbershop-api`, `barbershop-app`) and promote MVP 3 (release 3.0.0) of my nine repositories from `develop` to `qa` with `git cherry-pick -x` and a `[3.0.0]` CHANGELOG, so that the release to `main` for the third cut only needs the teacher's approval.
<!-- CONFIG-END -->

## 1. User stories worked this week
| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| [#165](https://github.com/code-corhuila/barber-saas-docs/issues/165) | [security] The public barber list does not expose the barber's account id (I, risk 6, P1) | doing (Dev and QA done; issue stays open until the release reaches `main`) | Contract first: [`barber-saas-docs#171`](https://github.com/code-corhuila/barber-saas-docs/pull/171) (`barbershop-service.yaml` 1.6.0, `PublicBarberProfile`). Then [`barbershop-api#26`](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/26) (test `a5157e5` before fix `3ea32be`) and [`barbershop-app#21`](https://github.com/code-corhuila/barber-saas-barbershop-app/pull/21) (`PublicBarber` type). In `qa` through [`barbershop-api#27`](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/27) and [`barbershop-app#22`](https://github.com/code-corhuila/barber-saas-barbershop-app/pull/22) |
| [#122](https://github.com/code-corhuila/barber-saas-docs/issues/122) | MVP 3: finish the mobile app and deploy it (my part: release 3.0.0 of my nine repositories) | doing (QA). `develop` → `qa` done in the nine repositories; `release` → `main` and tag `v3.0.0` pending | Promotions to `qa` with `git cherry-pick -x`: [`barbershop-api#27`](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/27), [`barbershop-app#22`](https://github.com/code-corhuila/barber-saas-barbershop-app/pull/22), [`notifications-api#23`](https://github.com/code-corhuila/barber-saas-notifications-api/pull/23), [`notifications-db#10`](https://github.com/code-corhuila/barber-saas-notifications-db/pull/10). CHANGELOG `[3.0.0]` in `develop` and then `qa` in the nine repositories, for example [`barbershop-api#28`](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/28) → [`#29`](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/29) (`1b1ea76`, cherry picked from `4092d1a`) |
| HU-AUTH-004 ([#123](https://github.com/code-corhuila/barber-saas-docs/issues/123)) | As a client, I want to verify my e-mail before my first sign-in, so that my account is tied to an address I own | doing (QA). My part, done in week 13, is now in `qa` | [`notifications-api#23`](https://github.com/code-corhuila/barber-saas-notifications-api/pull/23), [`notifications-db#10`](https://github.com/code-corhuila/barber-saas-notifications-db/pull/10) (`bae4252` and `12f4485`, cherry picked from `develop`) |
| HU-SHOP-003 ([#126](https://github.com/code-corhuila/barber-saas-docs/issues/126)) | As an admin (barbershop owner), I want to see my plan, my trial and my barber limit, so that I know how many barbers I can still add | doing (QA) | [`barbershop-api#27`](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/27) (`017976c` to `84bfc95`, `activeBarbers` and the plan's limit), [`barbershop-app#22`](https://github.com/code-corhuila/barber-saas-barbershop-app/pull/22) |
| HU-SADMIN-004 ([#128](https://github.com/code-corhuila/barber-saas-docs/issues/128)) | As an admin, I want to be warned before my trial or paid month ends, so that my barbershop is not suspended by surprise | doing (QA) | [`notifications-api#23`](https://github.com/code-corhuila/barber-saas-notifications-api/pull/23) |
| HU-NOTIF-005 ([#131](https://github.com/code-corhuila/barber-saas-docs/issues/131)) | As a client, I want a reminder one hour before my appointment, by push and e-mail, so that I do not miss it | doing (QA) | [`notifications-api#23`](https://github.com/code-corhuila/barber-saas-notifications-api/pull/23) |
| HU-APPT-005 ([#130](https://github.com/code-corhuila/barber-saas-docs/issues/130)) | As a client, I want to rate a completed appointment, so that other clients can choose a barbershop and a barber | doing (QA). My part: the ratings in the barbershop app | [`barbershop-app#22`](https://github.com/code-corhuila/barber-saas-barbershop-app/pull/22) |

## 2. My individual contribution
- **Security story #165, contract first (2026-11-04):** the anonymous `GET /api/v1/barbershops/{id}/barbers` returned `userId`, the barber's account id in identity-auth (a finding of my week 13 STRIDE). I changed the contract first in [`barber-saas-docs#171`](https://github.com/code-corhuila/barber-saas-docs/pull/171): `PublicBarberProfile` = `BarberProfile` without `userId`, `barbershop-service.yaml` 1.6.0, and `09-microservices/service-catalog.md` updated (it still said 1.4.0). Then `barbershop-api#26`: the test `a5157e5` was committed first and failed, then `3ea32be` answers `PublicBarberView`. The authenticated `GET /api/v1/barbers` keeps `userId`, because appointment-app and schedule-app use it to find the signed-in barber. Finally `barbershop-app#21` types the public list as `PublicBarber` (`tsc --noEmit` clean, vitest 37/37).
- **MVP 3 promotion to `qa` (2026-11-04):** in my nine repositories (`barbershop-{db,api,app}`, `schedule-{db,api,app}`, `notifications-{db,api,app}`) I re-applied every commit that `git cherry origin/qa origin/develop` reported as missing, oldest first, with `git cherry-pick -x`. No conflicts. Before each push the tree was identical to `origin/develop` and every cited sha is an ancestor of `origin/develop`. Examples: 13 commits in `barbershop-api#27`, 22 in `notifications-api#23`, 9 in `barbershop-app#22`, 3 in `notifications-db#10`.
- **Release 3.0.0 CHANGELOG (2026-11-04):** nine `chore/release-3.0.0-prep` PRs to `develop` with the `[3.0.0]` section, built from `git log --no-merges --reverse` since the 2.0.0 changelog and grouped by commit type with the user stories, then nine PRs promoting that single commit to `qa` with `-x`.
- **Certification (WEEKLY repo):** chapter 7 evidence [`21c6997`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/21c6997) (2026-11-05) and chapter 8 evidence [`e5998fa`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/e5998fa) (2026-11-07).

## 3. Blockers and risks
- **The organization's GitHub Actions did not run on 2026-11-04.** Every check of this week's PRs (`build-and-test`, `build`, `track`) failed in 2 seconds with "The job was not started because your account is locked due to a billing issue". The code never failed a test: I validated each PR locally (Maven tests in `barbershop-api#26`, `tsc` and vitest 37/37 in `barbershop-app#21`) and wrote it in the PR. The PRs were merged with red checks, so the CI evidence for MVP 3 is missing until Actions is unlocked and the runs are repeated.
- The release to `main` (`release/3.0.0` → `main`, tag `v3.0.0`) is not open yet. It needs the teacher's approval (golden rule 6), so #165 and the five HUs stay in QA.
- Some promotion PRs to `qa` are over 400 lines (`notifications-api#23` +1170/-117, `barbershop-api#27` +711/-64). They only re-apply commits already reviewed in small PRs to `develop`, but the size is real.
- Still pending from week 13: the PR with `WeeklyScheduleAuthorizationHttpTest` to `schedule-api` (#163), and #164, which needs the `aud` claim from the `identity-auth-api` owner.

## 4. Plan for next week
- Week 15 (third cut): open `release/3.0.0` → `main` in my nine repositories with `git cherry-pick -x` from `qa`, ask the teacher (ariel5253) for approval, then tag `v3.0.0` and publish the GitHub release; close #165 when it reaches `main`.
- Re-run the CI of the merged PRs once the Actions billing lock is removed, and link the green runs.
- Open the PR to `barber-saas-schedule-api` (`develop`) with `WeeklyScheduleAuthorizationHttpTest` and `security.yml` for #163.
- Agree the `aud` claim for #164 with the `identity-auth-api` owner.

## 5. Compliance self-check
- [x] Conventional Commits - `type(scope): summary`: `test(barbers)`, `fix(barbers)`, `fix(catalog)`, `docs(api)`, `docs(changelog)`, `chore(release)`, `docs(certification)`.
- [ ] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...): partial. The promotions to `qa` use `hu-mvp3-qa` → `qa` with `git cherry-pick -x` and the trail in the PR body; the work branches to `develop` are `fix/...` and `chore/release-3.0.0-prep` (rule 4.5 names), not `hu-xxx-dev`. Nothing reached `main` yet.
- [x] Testable acceptance criteria: #165 has an automated criterion (the public list has no `userId`), now covered by the `barbershop-api#26` test.
- [x] Tests added/updated (unit / integration): `a5157e5 test(barbers)` before `3ea32be fix(barbers)`; the test failed before the fix. Caveat: the CI did not run (billing lock), only local runs.
- [x] DDD / hexagonal boundaries respected (domain has no I/O): `PublicBarberView` is an HTTP adapter view; the domain `BarberProfile` was not changed.
- [x] No secrets; config via environment variables: no configuration changed this week; the CHANGELOGs and PR bodies contain no credentials.

## 6. Evidence links
- Contract: https://github.com/code-corhuila/barber-saas-docs/pull/171
- #165 code: https://github.com/code-corhuila/barber-saas-barbershop-api/pull/26, https://github.com/code-corhuila/barber-saas-barbershop-app/pull/21
- Promotions to `qa`: https://github.com/code-corhuila/barber-saas-barbershop-api/pull/27, https://github.com/code-corhuila/barber-saas-barbershop-app/pull/22, https://github.com/code-corhuila/barber-saas-notifications-api/pull/23, https://github.com/code-corhuila/barber-saas-notifications-db/pull/10
- CHANGELOG 3.0.0 (develop → qa): https://github.com/code-corhuila/barber-saas-barbershop-api/pull/28, https://github.com/code-corhuila/barber-saas-barbershop-api/pull/29, https://github.com/code-corhuila/barber-saas-barbershop-db/pull/12, https://github.com/code-corhuila/barber-saas-barbershop-db/pull/13, https://github.com/code-corhuila/barber-saas-schedule-api/pull/23, https://github.com/code-corhuila/barber-saas-schedule-api/pull/24, https://github.com/code-corhuila/barber-saas-notifications-app/pull/10, https://github.com/code-corhuila/barber-saas-notifications-app/pull/11
- Failed CI run (billing lock): https://github.com/code-corhuila/barber-saas-barbershop-api/actions/runs/41909240190
- Certification: https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/tree/main/certification
