<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.
     Your weekly grade is read AUTOMATICALLY from this file:
       15-week/hu-status/README.md  (inside YOUR fork). English. -->

# Weekly Status - Week 15

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Carlos Mauricio Leal Medina
- GITHUB_USER: carlosleal16
- TEAM: Barberssas
- SPRINT_GOAL: Third cut: take release 3.0.0 of my nine repositories from `qa` to `main` (`release.3.0.0` filled with `git cherry-pick -x`, approved by the teacher) and close security stories #163 and #164 with tests before the release, so that MVP 3 reaches production with no open security issue.
<!-- CONFIG-END -->

## 1. User stories worked this week
| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| [#163](https://github.com/code-corhuila/barber-saas-docs/issues/163) | [security] An owner cannot change the services or schedules of another barbershop | done (Main, issue closed 2026-11-10) | Test PR [`schedule-api#27`](https://github.com/code-corhuila/barber-saas-schedule-api/pull/27) (`9773344`, `WeeklyScheduleAuthorizationHttpTest`), promoted with `-x` in [`schedule-api#30`](https://github.com/code-corhuila/barber-saas-schedule-api/pull/30) (qa) and [`#31`](https://github.com/code-corhuila/barber-saas-schedule-api/pull/31) (release), in `main` through [`schedule-api#26`](https://github.com/code-corhuila/barber-saas-schedule-api/pull/26). [Closing comment](https://github.com/code-corhuila/barber-saas-docs/issues/163#issuecomment-6781085419) |
| [#164](https://github.com/code-corhuila/barber-saas-docs/issues/164) | [security] barbershop-api and schedule-api split user and service tokens by route | done (Main, issue closed 2026-11-10) | Service tokens get 401 on app routes: [`barbershop-api#32`](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/32), [`schedule-api#28`](https://github.com/code-corhuila/barber-saas-schedule-api/pull/28). `aud: barber-saas-app` required on app routes: [`barbershop-api#33`](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/33), [`schedule-api#29`](https://github.com/code-corhuila/barber-saas-schedule-api/pull/29). Promoted: [`barbershop-api#34`](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/34) → [`#35`](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/35), [`schedule-api#30`](https://github.com/code-corhuila/barber-saas-schedule-api/pull/30) → [`#31`](https://github.com/code-corhuila/barber-saas-schedule-api/pull/31). [Closing comment](https://github.com/code-corhuila/barber-saas-docs/issues/164#issuecomment-6781087260) |
| [#165](https://github.com/code-corhuila/barber-saas-docs/issues/165) | [security] The public barber list does not expose the barber's account id | done (Main, issue closed 2026-11-10) | Week 14 fix, now in `main` through [`barbershop-api#31`](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/31) (`3c43ff1` and `c84480a`, cherry picked from `qa`) |
| [#122](https://github.com/code-corhuila/barber-saas-docs/issues/122) | MVP 3: finish the mobile app and deploy it (my part: release 3.0.0 of my nine repositories) | done (Main, tag `v3.0.0`) | Nine `release.3.0.0` fill PRs with `git cherry-pick -x` (for example [`barbershop-api#30`](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/30), 14 commits with their trail), then nine `release.3.0.0` → `main` PRs approved by the teacher: [`barbershop-api#31`](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/31), [`barbershop-app#26`](https://github.com/code-corhuila/barber-saas-barbershop-app/pull/26), [`barbershop-db#15`](https://github.com/code-corhuila/barber-saas-barbershop-db/pull/15), [`schedule-api#26`](https://github.com/code-corhuila/barber-saas-schedule-api/pull/26), [`schedule-app#20`](https://github.com/code-corhuila/barber-saas-schedule-app/pull/20), [`schedule-db#14`](https://github.com/code-corhuila/barber-saas-schedule-db/pull/14), [`notifications-api#29`](https://github.com/code-corhuila/barber-saas-notifications-api/pull/29), [`notifications-app#13`](https://github.com/code-corhuila/barber-saas-notifications-app/pull/13), [`notifications-db#14`](https://github.com/code-corhuila/barber-saas-notifications-db/pull/14) |
| HU-AUTH-004 ([#123](https://github.com/code-corhuila/barber-saas-docs/issues/123)) | As a client, I want to verify my e-mail before my first sign-in, so that my account is tied to an address I own | done (Main). My part: notifications | [`notifications-api#29`](https://github.com/code-corhuila/barber-saas-notifications-api/pull/29), [`notifications-db#14`](https://github.com/code-corhuila/barber-saas-notifications-db/pull/14) |
| HU-SHOP-003 ([#126](https://github.com/code-corhuila/barber-saas-docs/issues/126)) | As an admin (barbershop owner), I want to see my plan, my trial and my barber limit, so that I know how many barbers I can still add | done (Main) | [`barbershop-api#31`](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/31), [`barbershop-app#26`](https://github.com/code-corhuila/barber-saas-barbershop-app/pull/26) |
| HU-SADMIN-004 ([#128](https://github.com/code-corhuila/barber-saas-docs/issues/128)) | As an admin, I want to be warned before my trial or paid month ends, so that my barbershop is not suspended by surprise | done (Main) | [`notifications-api#29`](https://github.com/code-corhuila/barber-saas-notifications-api/pull/29) |
| HU-NOTIF-005 ([#131](https://github.com/code-corhuila/barber-saas-docs/issues/131)) | As a client, I want a reminder one hour before my appointment, by push and e-mail, so that I do not miss it | done (Main) | [`notifications-api#29`](https://github.com/code-corhuila/barber-saas-notifications-api/pull/29) |
| HU-APPT-005 ([#130](https://github.com/code-corhuila/barber-saas-docs/issues/130)) | As a client, I want to rate a completed appointment, so that other clients can choose a barbershop and a barber | done (Main). My part: the ratings in the barbershop app | [`barbershop-app#26`](https://github.com/code-corhuila/barber-saas-barbershop-app/pull/26) |

## 2. My individual contribution
- **Release 3.0.0 to `main` (2026-11-09):** in my nine repositories I cut `release.3.0.0` from `main` and filled it from `qa` with every commit that `git cherry origin/main origin/qa` reported, oldest first, with `git cherry-pick -x`. No conflicts, and the tree equals `origin/qa`. Each fill PR has a "Promotion trail" table (commit → `qa` sha ← `develop` sha). Then I opened the nine `release.3.0.0` → `main` PRs for the teacher (ariel5253).
- **#163, test before the release (2026-11-10):** `WeeklyScheduleAuthorizationHttpTest` in `schedule-api#27` has three tests. An owner who changes another barbershop's week gets 404 and that week stays unchanged. BARBER and CLIENT get 403. A request without a token gets 401. The code already scoped the schedule to the caller's barbershop, so no fix was needed.
- **#164, a real gap fixed (2026-11-10):** in `barbershop-api` and `schedule-api`, a SERVICE token was accepted on `/api/v1/**` (403 instead of 401), and nobody checked `aud`. Now `AuthFilter` answers 401 to service tokens on app routes. App routes also require `aud: barber-saas-app` (`Rs256Verifier.verifyForApp`, string or array, RFC 7519 4.1.3). `/internal/v1/**` still takes service tokens only and does not check `aud`.
  - New tests in both services: `ServiceTokenHttpTest`, `AppAudienceHttpTest` and new cases in `Rs256VerifierTest`.
  - Local `mvn verify`, BUILD SUCCESS. `barbershop-api`: 83 core, 28 adapters (7 JDBC skipped), 42 app. `schedule-api`: 26 core, 21 adapters (2 JDBC skipped), 16 app.
  - I also checked it with real tokens from `dev-token.sh`.
- **Re-approval:** pushing the security fixes to `release.3.0.0` dismissed the teacher's approval on `barbershop-api#31` and `schedule-api#26`. I commented the new trail tables on both PRs, and the teacher approved them again on the new heads (2026-11-10).
- **Merge to `main` and tag:** after the approval, my teammate Daniel Cerquera merged the nine PRs and published `v3.0.0` (2026-11-12). I checked that `main` equals `qa` in the nine repositories and that `v3.0.0` points to `main`.
- **Certification (WEEKLY repo):** evidence of chapters 9 to 14, from [`352166b`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/352166b) (2026-11-09) to [`23bd75e`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/23bd75e) (2026-11-12).

## 3. Blockers and risks
- **GitHub Actions is still locked by the organization's billing.** Every run since 2026-10-26 fails in seconds with "The job was not started because your account is locked due to a billing issue". This includes the `main` run of `release.3.0.0` ([run 43359669778](https://github.com/code-corhuila/barber-saas-barbershop-api/actions/runs/43359669778)). The evidence of green tests is local only. `develop` has no required status check, so a red test does not block a merge today. That is why I left the CI criterion of #163 unchecked and explained it in the closing comment.
- The promotion PRs (to `qa` and to `release`) are over 400 lines. They only re-apply commits already reviewed in small PRs to `develop`.
- `#164` only covers `barbershop-api` and `schedule-api`. The other services should get the same `aud` check, but their owners decide that.
- The MVP 3 user stories (#122, #123, #126, #128, #130, #131) are in `main` but their issues are still open on the board.

## 4. Plan for next week
- Week 16: help with the final test of release 3.0.0 on `main` and fix through `hotfix/*` only if it finds a defect.
- Ask the board owner to close #122 and the five MVP 3 stories now that they are in `main`.
- Re-run the CI of `develop`, `qa` and `main` when the billing lock is removed, and link the green runs.
- Propose the same `aud` check for the other `-api` services to their owners.

## 5. Compliance self-check
- [x] Conventional Commits - `type(scope): summary`: `test(schedule)`, `fix(auth)`, `feat(auth)`, `chore(qa)`, `chore(release)`, `docs(certification)`.
- [ ] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...): partial. The promotions use `hu-security-qa` → `qa` and `release.3.0.0` → `main`, with `git cherry-pick -x` and the trail in each PR. The work branches to `develop` use the 4.5 names (`test/...`, `fix/...`, `feat/...`), not `hu-xxx-dev`.
- [x] Testable acceptance criteria: #163 and #164 had HTTP criteria (404/403/401, and 401 for service tokens or tokens without `aud`). Each one is now an automated test.
- [x] Tests added/updated (unit / integration): `WeeklyScheduleAuthorizationHttpTest`, `ServiceTokenHttpTest`, `AppAudienceHttpTest` (in both services) and new `Rs256VerifierTest` cases. Caveat: they ran locally only (the CI is locked).
- [x] DDD / hexagonal boundaries respected (domain has no I/O): the token checks live in the HTTP adapter (`AuthFilter`, `Rs256Verifier`). The core modules did not change.
- [x] No secrets; config via environment variables: the tests sign tokens with a key pair generated at runtime. The public key and the service token come from environment variables (`JWT_PUBLIC_KEY`, `SERVICE_TOKEN`).

## 6. Evidence links
- Release to `main` (nine PRs): https://github.com/code-corhuila/barber-saas-barbershop-api/pull/31, https://github.com/code-corhuila/barber-saas-barbershop-app/pull/26, https://github.com/code-corhuila/barber-saas-barbershop-db/pull/15, https://github.com/code-corhuila/barber-saas-schedule-api/pull/26, https://github.com/code-corhuila/barber-saas-schedule-app/pull/20, https://github.com/code-corhuila/barber-saas-schedule-db/pull/14, https://github.com/code-corhuila/barber-saas-notifications-api/pull/29, https://github.com/code-corhuila/barber-saas-notifications-app/pull/13, https://github.com/code-corhuila/barber-saas-notifications-db/pull/14
- Release fill with `-x` (example): https://github.com/code-corhuila/barber-saas-barbershop-api/pull/30
- Releases `v3.0.0`: https://github.com/code-corhuila/barber-saas-barbershop-api/releases/tag/v3.0.0, https://github.com/code-corhuila/barber-saas-schedule-api/releases/tag/v3.0.0, https://github.com/code-corhuila/barber-saas-notifications-api/releases/tag/v3.0.0
- #163: https://github.com/code-corhuila/barber-saas-schedule-api/pull/27, https://github.com/code-corhuila/barber-saas-docs/issues/163
- #164: https://github.com/code-corhuila/barber-saas-barbershop-api/pull/32, https://github.com/code-corhuila/barber-saas-barbershop-api/pull/33, https://github.com/code-corhuila/barber-saas-schedule-api/pull/28, https://github.com/code-corhuila/barber-saas-schedule-api/pull/29, https://github.com/code-corhuila/barber-saas-docs/issues/164
- Security promotions: https://github.com/code-corhuila/barber-saas-barbershop-api/pull/34, https://github.com/code-corhuila/barber-saas-barbershop-api/pull/35, https://github.com/code-corhuila/barber-saas-schedule-api/pull/30, https://github.com/code-corhuila/barber-saas-schedule-api/pull/31
- Locked CI run: https://github.com/code-corhuila/barber-saas-barbershop-api/actions/runs/43359669778
- Certification: https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/tree/main/certification

## 7. Status against the rubric (third cut, my nine repositories)
- Serious faults (numeral 13): none found. I checked this on 2026-11-12 against `origin/main` and `origin/qa` of my nine repositories:
  - 13.1: no merge of `develop`, `qa` or `main` into another permanent branch.
  - 13.2: every non-merge commit on `main` that is not in `develop` carries `cherry picked from`.
  - Branches and tag: `main` has the same tree as `qa`, and `v3.0.0` points to the head of `main`.
- Dimension 3 (promotion): `develop` → `qa` → `release.3.0.0` → `main` with `-x` and a trail table in every PR, plus the teacher's approval on `main` (rule 6).
- Dimension 6 (release): tag `v3.0.0` and a GitHub release for each repository, with a `[3.0.0]` CHANGELOG.
- Open gaps:
  - The CI is locked by billing (dimension 6 and 18.3), so the test evidence is local only.
  - The MVP 3 issues are still open on the board (dimension 7).
