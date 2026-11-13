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
| [#122](https://github.com/code-corhuila/barber-saas-docs/issues/122) | MVP 3: finish the mobile app and deploy it (my part: release 3.0.0 of my nine repositories) | done (Main, tag `v3.0.0`, merged 2026-11-12) | Nine `release.3.0.0` fill PRs with `git cherry-pick -x` (for example [`barbershop-api#30`](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/30), 14 commits with their trail), then nine `release.3.0.0` → `main` PRs, merged with rebase on 2026-11-12, eight of them approved by the teacher (`notifications-api#29` without approval, see section 3): [`barbershop-api#31`](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/31), [`barbershop-app#26`](https://github.com/code-corhuila/barber-saas-barbershop-app/pull/26), [`barbershop-db#15`](https://github.com/code-corhuila/barber-saas-barbershop-db/pull/15), [`schedule-api#26`](https://github.com/code-corhuila/barber-saas-schedule-api/pull/26), [`schedule-app#20`](https://github.com/code-corhuila/barber-saas-schedule-app/pull/20), [`schedule-db#14`](https://github.com/code-corhuila/barber-saas-schedule-db/pull/14), [`notifications-api#29`](https://github.com/code-corhuila/barber-saas-notifications-api/pull/29), [`notifications-app#13`](https://github.com/code-corhuila/barber-saas-notifications-app/pull/13), [`notifications-db#14`](https://github.com/code-corhuila/barber-saas-notifications-db/pull/14). Releases `v3.0.0`: [`barbershop-api`](https://github.com/code-corhuila/barber-saas-barbershop-api/releases/tag/v3.0.0), [`barbershop-app`](https://github.com/code-corhuila/barber-saas-barbershop-app/releases/tag/v3.0.0), [`barbershop-db`](https://github.com/code-corhuila/barber-saas-barbershop-db/releases/tag/v3.0.0), [`schedule-api`](https://github.com/code-corhuila/barber-saas-schedule-api/releases/tag/v3.0.0), [`schedule-app`](https://github.com/code-corhuila/barber-saas-schedule-app/releases/tag/v3.0.0), [`schedule-db`](https://github.com/code-corhuila/barber-saas-schedule-db/releases/tag/v3.0.0), [`notifications-api`](https://github.com/code-corhuila/barber-saas-notifications-api/releases/tag/v3.0.0), [`notifications-app`](https://github.com/code-corhuila/barber-saas-notifications-app/releases/tag/v3.0.0), [`notifications-db`](https://github.com/code-corhuila/barber-saas-notifications-db/releases/tag/v3.0.0) |
| HU-AUTH-004 ([#123](https://github.com/code-corhuila/barber-saas-docs/issues/123)) | As a client, I want to verify my e-mail before my first sign-in, so that my account is tied to an address I own | done (Main). My part: notifications | [`notifications-api#29`](https://github.com/code-corhuila/barber-saas-notifications-api/pull/29), [`notifications-db#14`](https://github.com/code-corhuila/barber-saas-notifications-db/pull/14) |
| HU-SHOP-003 ([#126](https://github.com/code-corhuila/barber-saas-docs/issues/126)) | As an admin (barbershop owner), I want to see my plan, my trial and my barber limit, so that I know how many barbers I can still add | done (Main) | [`barbershop-api#31`](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/31), [`barbershop-app#26`](https://github.com/code-corhuila/barber-saas-barbershop-app/pull/26) |
| HU-SADMIN-004 ([#128](https://github.com/code-corhuila/barber-saas-docs/issues/128)) | As an admin, I want to be warned before my trial or paid month ends, so that my barbershop is not suspended by surprise | done (Main) | [`notifications-api#29`](https://github.com/code-corhuila/barber-saas-notifications-api/pull/29) |
| HU-NOTIF-005 ([#131](https://github.com/code-corhuila/barber-saas-docs/issues/131)) | As a client, I want a reminder one hour before my appointment, by push and e-mail, so that I do not miss it | done (Main) | [`notifications-api#29`](https://github.com/code-corhuila/barber-saas-notifications-api/pull/29) |
| HU-APPT-005 ([#130](https://github.com/code-corhuila/barber-saas-docs/issues/130)) | As a client, I want to rate a completed appointment, so that other clients can choose a barbershop and a barber | done (Main). My part: the ratings in the barbershop app | [`barbershop-app#26`](https://github.com/code-corhuila/barber-saas-barbershop-app/pull/26) |
| CERT-C3 | As a student, I want to take the Huawei course of the third cut and keep its evidence in this repository | doing: final exam passed (88 points, 2026-11-13) and certificate in the repository; missing: the study hours (36.85 of the 50 required) and the submission in Moodle (due 2026-11-15) | Commits of `certification/`, one by one: [`5d4f909`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/5d4f909), [`558003b`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/558003b), [`2f674c3`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/2f674c3), [`8cba91a`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/8cba91a), [`a905d3f`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/a905d3f), [`21c6997`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/21c6997), [`e5998fa`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/e5998fa), [`42aaeac`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/42aaeac), [`352166b`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/352166b), [`0e842ed`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/0e842ed), [`a424d83`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/a424d83), [`dd0a3e4`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/dd0a3e4), [`c88af19`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/c88af19), [`23bd75e`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/23bd75e), [`2734ff4`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/2734ff4), [`a4d75f1`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/a4d75f1), [`ed80152`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/ed80152). Certificate: [`certification/certificate.pdf`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/blob/main/certification/certificate.pdf) (ICT20261114000018). Moodle submission: not made yet |

## 2. My individual contribution
- **Release 3.0.0 to `main` (2026-11-09):** in my nine repositories I cut `release.3.0.0` from `main` and filled it from `qa` with every commit that `git cherry origin/main origin/qa` reported, oldest first, with `git cherry-pick -x`. No conflicts, and the tree equals `origin/qa`. Each fill PR has a "Promotion trail" table (commit → `qa` sha ← `develop` sha). Then I opened the nine `release.3.0.0` → `main` PRs for the teacher (ariel5253).
- **#163, test before the release (2026-11-10):** `WeeklyScheduleAuthorizationHttpTest` in `schedule-api#27` has three tests. An owner who changes another barbershop's week gets 404 and that week stays unchanged. BARBER and CLIENT get 403. A request without a token gets 401. The code already scoped the schedule to the caller's barbershop, so no fix was needed.
- **#164, a real gap fixed (2026-11-10):** in `barbershop-api` and `schedule-api`, a SERVICE token was accepted on `/api/v1/**` (403 instead of 401), and nobody checked `aud`. Now `AuthFilter` answers 401 to service tokens on app routes. App routes also require `aud: barber-saas-app` (`Rs256Verifier.verifyForApp`, string or array, RFC 7519 4.1.3). `/internal/v1/**` still takes service tokens only and does not check `aud`.
  - New tests in both services: `ServiceTokenHttpTest`, `AppAudienceHttpTest` and new cases in `Rs256VerifierTest`.
  - Local `mvn verify`, BUILD SUCCESS. `barbershop-api`: 83 core, 28 adapters (7 JDBC skipped), 42 app. `schedule-api`: 26 core, 21 adapters (2 JDBC skipped), 16 app.
  - I also checked it with real tokens from `dev-token.sh`.
- **Re-approval:** pushing the security fixes to `release.3.0.0` dismissed the teacher's approval on `barbershop-api#31` and `schedule-api#26`. I commented the new trail tables on both PRs, and the teacher approved them again on the new heads (2026-11-10).
- **Merge to `main` and tag (2026-11-12):** my teammate Daniel Cerquera (Pipecerquera) merged my nine `release.3.0.0` → `main` PRs with rebase. Eight had the teacher's approval. He merged `notifications-api#29` as admin without an approval (see Blockers and risks). Then he published the annotated tag `v3.0.0` and a GitHub Release in each of my nine repositories: [`barbershop-api`](https://github.com/code-corhuila/barber-saas-barbershop-api/releases/tag/v3.0.0), [`barbershop-app`](https://github.com/code-corhuila/barber-saas-barbershop-app/releases/tag/v3.0.0), [`barbershop-db`](https://github.com/code-corhuila/barber-saas-barbershop-db/releases/tag/v3.0.0), [`schedule-api`](https://github.com/code-corhuila/barber-saas-schedule-api/releases/tag/v3.0.0), [`schedule-app`](https://github.com/code-corhuila/barber-saas-schedule-app/releases/tag/v3.0.0), [`schedule-db`](https://github.com/code-corhuila/barber-saas-schedule-db/releases/tag/v3.0.0), [`notifications-api`](https://github.com/code-corhuila/barber-saas-notifications-api/releases/tag/v3.0.0), [`notifications-app`](https://github.com/code-corhuila/barber-saas-notifications-app/releases/tag/v3.0.0), [`notifications-db`](https://github.com/code-corhuila/barber-saas-notifications-db/releases/tag/v3.0.0). I checked that `main` equals `qa` in the nine repositories and that `v3.0.0` points to `main`.
- **Final presentation (2026-11-12):** the team presented the final project with a live demo on the Azure host and delivered `c3-release` and `c3-project` in Moodle. For it I recorded a promotional video for end users and a setup video for technical users (2026-11-09).
- **Certification (WEEKLY repo):** evidence of chapters 9 to 14, from [`352166b`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/352166b) (2026-11-09) to [`23bd75e`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/23bd75e) (2026-11-12); final exam passed with 88 points and the certificate on 2026-11-13 ([`2734ff4`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/2734ff4)); README, design note and ADRs on 2026-11-13 ([`a4d75f1`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/a4d75f1), [`ed80152`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/ed80152)). Still open: hours and the Moodle submission (row CERT-C3).

## 3. Blockers and risks
- **`notifications-api#29` reached `main` without a review.** The teacher's approval was dismissed when a fix was pushed to `release.3.0.0` on 2026-11-11 (Daniel's commit, [comment](https://github.com/code-corhuila/barber-saas-notifications-api/pull/29#issuecomment-6801406840)), the reviewer (ariel5253) did not answer a new review request, and Daniel merged it as admin on 2026-11-12. GitHub shows the PR as merged with `REVIEW_REQUIRED`. This breaks rule 6 (`main` only with the teacher's approval) for this one PR; the other eight have the approval.
- **GitHub Actions is still locked by the organization's billing.** Every run since 2026-10-26 fails in seconds with "The job was not started because your account is locked due to a billing issue". This includes the `main` run of `release.3.0.0` ([run 43359669778](https://github.com/code-corhuila/barber-saas-barbershop-api/actions/runs/43359669778)). The evidence of green tests is local only. `develop` has no required status check, so a red test does not block a merge today. That is why I left the CI criterion of #163 unchecked and explained it in the closing comment.
- The promotion PRs (to `qa` and to `release`) are over 400 lines. They only re-apply commits already reviewed in small PRs to `develop`.
- `#164` only covers `barbershop-api` and `schedule-api`. The other services should get the same `aud` check, but their owners decide that.
- The MVP 3 user stories (#122, #123, #126, #128, #130, #131) are in `main` but their issues are still open on the board.
- The certification is not finished: 36.85 of the 50 study hours are logged, and the labs could not be opened from my account.

## 4. Plan for next week
- Before 2026-11-15 23:59: finish the study hours of the certification and submit it in Moodle (folder link and `certificate.pdf`).
- Week 16 (2026-11-16 to 2026-11-22) is the grading week: no sprint. The oral defence of the certification is on 2026-11-19.
- Left as open items for the team, not planned work: closing the MVP 3 issues on the board, re-running the CI when the billing lock is removed, the same `aud` check in the other `-api` services, and a review of `notifications-api#29` after the fact.

## 5. Compliance self-check
- [x] Conventional Commits - `type(scope): summary`: `test(schedule)`, `fix(auth)`, `feat(auth)`, `chore(qa)`, `chore(release)`, `docs(certification)`, `docs(week-12)`, `docs(week-14)`, `docs(week-15)`.
- [ ] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...): partial. The promotions use `hu-security-qa` → `qa` and `release.3.0.0` → `main`, with `git cherry-pick -x` and the trail in each PR. The work branches to `develop` use the 4.5 names (`test/...`, `fix/...`, `feat/...`), not `hu-xxx-dev`. The nine release PRs reached `main` by rebase (2026-11-12), eight with the teacher's approval and `notifications-api#29` without it.
- [x] Testable acceptance criteria: #163 and #164 had HTTP criteria (404/403/401, and 401 for service tokens or tokens without `aud`). Each one is now an automated test.
- [x] Tests added/updated (unit / integration): `WeeklyScheduleAuthorizationHttpTest`, `ServiceTokenHttpTest`, `AppAudienceHttpTest` (in both services) and new `Rs256VerifierTest` cases. Caveat: they ran locally only (the CI is locked).
- [x] DDD / hexagonal boundaries respected (domain has no I/O): the token checks live in the HTTP adapter (`AuthFilter`, `Rs256Verifier`). The core modules did not change.
- [x] No secrets; config via environment variables: the tests sign tokens with a key pair generated at runtime. The public key and the service token come from environment variables (`JWT_PUBLIC_KEY`, `SERVICE_TOKEN`).

## 6. Evidence links
- Release to `main` (nine PRs): https://github.com/code-corhuila/barber-saas-barbershop-api/pull/31, https://github.com/code-corhuila/barber-saas-barbershop-app/pull/26, https://github.com/code-corhuila/barber-saas-barbershop-db/pull/15, https://github.com/code-corhuila/barber-saas-schedule-api/pull/26, https://github.com/code-corhuila/barber-saas-schedule-app/pull/20, https://github.com/code-corhuila/barber-saas-schedule-db/pull/14, https://github.com/code-corhuila/barber-saas-notifications-api/pull/29, https://github.com/code-corhuila/barber-saas-notifications-app/pull/13, https://github.com/code-corhuila/barber-saas-notifications-db/pull/14
- Release fill with `-x` (example): https://github.com/code-corhuila/barber-saas-barbershop-api/pull/30
- Releases `v3.0.0`: https://github.com/code-corhuila/barber-saas-barbershop-api/releases/tag/v3.0.0, https://github.com/code-corhuila/barber-saas-barbershop-app/releases/tag/v3.0.0, https://github.com/code-corhuila/barber-saas-barbershop-db/releases/tag/v3.0.0, https://github.com/code-corhuila/barber-saas-schedule-api/releases/tag/v3.0.0, https://github.com/code-corhuila/barber-saas-schedule-app/releases/tag/v3.0.0, https://github.com/code-corhuila/barber-saas-schedule-db/releases/tag/v3.0.0, https://github.com/code-corhuila/barber-saas-notifications-api/releases/tag/v3.0.0, https://github.com/code-corhuila/barber-saas-notifications-app/releases/tag/v3.0.0, https://github.com/code-corhuila/barber-saas-notifications-db/releases/tag/v3.0.0
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
- Dimension 3 (promotion): `develop` → `qa` → `release.3.0.0` → `main` with `-x` and a trail table in every PR, plus the teacher's approval on `main` (rule 6) on eight of the nine PRs.
- Dimension 6 (release): annotated tag `v3.0.0` and a GitHub release for each of the nine repositories (published by Pipecerquera on 2026-11-12), with a `[3.0.0]` CHANGELOG.
- Open gaps:
  - The CI is locked by billing (dimension 6 and 18.3), so the test evidence is local only.
  - The MVP 3 issues are still open on the board (dimension 7).
  - `notifications-api#29` was merged to `main` without the teacher's approval (rule 6).

## 8. Complete evidence of the week, item by item
<!-- FULL-EVIDENCE-START -->
Everything of mine from 2026-11-07 to 2026-11-13, taken from `gh api` and `git log` on 2026-11-13. Dates are the commit date on each branch, in UTC (the merge to `main` on the evening of 2026-11-12 in Colombia shows as 2026-11-13 UTC). A commit on `release.3.0.0` is a `git cherry-pick -x` of the `qa` commit, so its author date can be older. The commits on `main` are the same commits, put on `main` by Daniel Cerquera (Pipecerquera) when he merged the release PRs with rebase on 2026-11-12; I am their author, he is the committer.

### 8.1 Commits in my nine repositories

**barber-saas-barbershop-api**

- `develop` (4):
  - [`ee62c9a`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/ee62c9aeefb9e6e206a1b5d96b452f2a56c1e436) 2026-11-10 `test(auth): expect 401 for a service token on the routes of the app`
  - [`98be0b2`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/98be0b2d777a302a5fb90a3abfba99c4e94bccb2) 2026-11-10 `fix(auth): reject service tokens on the routes of the app with 401`
  - [`15f846a`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/15f846a441fb087338bb07268cf8cd866a61a98d) 2026-11-10 `feat(auth): verify that a token for the app names barber-saas-app as its audience`
  - [`50d21c3`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/50d21c39762f8bdb62995b01cf25c5b7a6475213) 2026-11-10 `feat(auth): require the app audience on the routes of the app`
- `qa` (4):
  - [`3faec3e`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/3faec3ea4068ad0c2120e888c4f55915342c6cd0) 2026-11-10 `test(auth): expect 401 for a service token on the routes of the app`
  - [`71a1432`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/71a1432fa95821b75aceccdee03b84568c31e242) 2026-11-10 `fix(auth): reject service tokens on the routes of the app with 401`
  - [`73b3d8f`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/73b3d8fe6fd88703b76e443728e3d1d8a3c68178) 2026-11-10 `feat(auth): verify that a token for the app names barber-saas-app as its audience`
  - [`737943f`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/737943fef1d2cb1b95fedd54a341004b967442ff) 2026-11-10 `feat(auth): require the app audience on the routes of the app`
- `release.3.0.0` (18):
  - [`b96acfc`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/b96acfcb462537e3135e9320d3ab004454712da5) 2026-11-09 `test(domain): specify the barber limit of the plan and the active barbers count`
  - [`df5c885`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/df5c88585d57afc3884f1139d94126e6e87f674d) 2026-11-09 `feat(domain): count the active barbers and refuse one over the plan's limit`
  - [`f4b78b1`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/f4b78b117f849431f5099c49372c6aa2efb60687) 2026-11-09 `test(application): specify the plan's barber limit when a profile is created`
  - [`c470bac`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/c470bac56ffc2de915165ec35dfc6863f298e1a8) 2026-11-09 `refactor(http): share the internal call with the service token and its limits`
  - [`b489246`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/b489246bcd2624c97602be5f97cacaf6d2d50516) 2026-11-09 `feat(http): read a plan from platform-admin with the service token`
  - [`c96f732`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/c96f7323620be994e3065493fc6a78cf4c1b76e6) 2026-11-09 `feat(application): refuse a barber profile over the plan's limit read from platform-admin`
  - [`7489385`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/74893853460bb2aa5296f577a923236cb77ae913) 2026-11-09 `test(http): specify active barbers on every read and the plan's limit over http`
  - [`c5f14d1`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/c5f14d1ac47af75700ca70f45b30d29b5b395f33) 2026-11-09 `test(persistence): specify the barber profiles counted on every barbershop read`
  - [`65d8ef7`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/65d8ef768c045e107106984fed0698a3dddce93b) 2026-11-09 `feat(persistence): count the barber profiles on every barbershop read`
  - [`353a69e`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/353a69e37c324a28ba4dc08ab842c0c83d3912cb) 2026-11-09 `feat(http): expose activeBarbers on the barbershop reads`
  - [`9017819`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/901781989e9ccaa6a587307174603d6b556bd985) 2026-11-09 `docs(readme): explain activeBarbers and the plan's barber limit`
  - [`5e38abe`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/5e38abedd40b9081b288eb069eb7003419d1cf46) 2026-11-09 `test(barbers): expect the public catalog without the account id`
  - [`71e0644`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/71e0644df034737868c3b6964ea167b7ff8b719e) 2026-11-09 `fix(barbers): answer the public catalog without the account id`
  - [`c832700`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/c832700213facb92c16830d95329bdea3345ea45) 2026-11-09 `docs(changelog): record release 3.0.0`
  - [`2e69799`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/2e69799f71ee8947704c8503b622bafa755d36b4) 2026-11-10 `test(auth): expect 401 for a service token on the routes of the app`
  - [`be728ee`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/be728ee7522a197c43e16004c7b9fdd2b8fdd81c) 2026-11-10 `fix(auth): reject service tokens on the routes of the app with 401`
  - [`5b1cf3c`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/5b1cf3c1d83cbc5e216e9eeef36f31ce12aad8d1) 2026-11-10 `feat(auth): verify that a token for the app names barber-saas-app as its audience`
  - [`c9fd34c`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/c9fd34ce123fc13b27d80d728d1f002bc593c68c) 2026-11-10 `feat(auth): require the app audience on the routes of the app`
- `main` (18):
  - [`91526d7`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/91526d7dfb8be4a2b4649653797a130e6b4e7e4a) 2026-11-13 `test(domain): specify the barber limit of the plan and the active barbers count`
  - [`285c235`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/285c235421feddbb3eff7cd5b9a4be9d440c3d98) 2026-11-13 `feat(domain): count the active barbers and refuse one over the plan's limit`
  - [`bd31aff`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/bd31aff31e798a3e376e585ca4ca32141eae9241) 2026-11-13 `test(application): specify the plan's barber limit when a profile is created`
  - [`06564a8`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/06564a8de86d893e1f069755555d7d1a8560de8c) 2026-11-13 `refactor(http): share the internal call with the service token and its limits`
  - [`f18b18b`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/f18b18be52260a6d172703f68e63e68d526d8aea) 2026-11-13 `feat(http): read a plan from platform-admin with the service token`
  - [`e6bd678`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/e6bd678ad16551b5a2aeba1ade45d4349252ce17) 2026-11-13 `feat(application): refuse a barber profile over the plan's limit read from platform-admin`
  - [`b12fea8`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/b12fea8c0115720ae23671bd4ce5993084c42c22) 2026-11-13 `test(http): specify active barbers on every read and the plan's limit over http`
  - [`9ea8c34`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/9ea8c34dd67488fc118db3a232cad8ff641c949e) 2026-11-13 `test(persistence): specify the barber profiles counted on every barbershop read`
  - [`3f507f6`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/3f507f6d7aba94dce7b6b3fda75f9e201a151fae) 2026-11-13 `feat(persistence): count the barber profiles on every barbershop read`
  - [`ed99cd4`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/ed99cd474d2a169a8fac701e6c785f9da3ed29e5) 2026-11-13 `feat(http): expose activeBarbers on the barbershop reads`
  - [`002504d`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/002504d909438f112ca6b0e081f6064c1681354c) 2026-11-13 `docs(readme): explain activeBarbers and the plan's barber limit`
  - [`9fcb8f5`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/9fcb8f5f58c07bb32d6ee5f943dd2c435d3f91e4) 2026-11-13 `test(barbers): expect the public catalog without the account id`
  - [`4aee533`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/4aee533e47379195d3b6aad65fc95b355e11a7e3) 2026-11-13 `fix(barbers): answer the public catalog without the account id`
  - [`b0b8500`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/b0b850035d7854ba9869e737aab3791f98c8a6a7) 2026-11-13 `docs(changelog): record release 3.0.0`
  - [`0985d53`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/0985d5300e2b69b8d1dce64bc0872ecafe2c0ba1) 2026-11-13 `test(auth): expect 401 for a service token on the routes of the app`
  - [`0e7748d`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/0e7748d3bc81cea8457a7897d7dcebbe6ec52ee0) 2026-11-13 `fix(auth): reject service tokens on the routes of the app with 401`
  - [`5334937`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/53349379a22cbcaa3b78f54637221162b0027f09) 2026-11-13 `feat(auth): verify that a token for the app names barber-saas-app as its audience`
  - [`164b628`](https://github.com/code-corhuila/barber-saas-barbershop-api/commit/164b628318476ff0621f44eea46eddb4a58ad358) 2026-11-13 `feat(auth): require the app audience on the routes of the app`

**barber-saas-barbershop-app**

- `release.3.0.0` (10):
  - [`245bb1a`](https://github.com/code-corhuila/barber-saas-barbershop-app/commit/245bb1a55bee918ea9644b6ee0ba3c7970ffe32e) 2026-11-09 `test(plan): specify the barber limit check before adding a barber`
  - [`b3df1fe`](https://github.com/code-corhuila/barber-saas-barbershop-app/commit/b3df1fe9d8164751d53571d9acd43d8b2fd40500) 2026-11-09 `feat(plan): check the plan's barber limit with my barbershop and my subscription`
  - [`31dcdc2`](https://github.com/code-corhuila/barber-saas-barbershop-app/commit/31dcdc22384c0aec21c41ecb189998ef1160595c) 2026-11-09 `feat(barbers): show the plan's barber limit before adding a barber`
  - [`7846157`](https://github.com/code-corhuila/barber-saas-barbershop-app/commit/7846157764aea164bb27271468f2b32c94a4cc49) 2026-11-09 `docs(readme): explain the barber limit on the team screen`
  - [`2159074`](https://github.com/code-corhuila/barber-saas-barbershop-app/commit/21590749f2f0539a9040bcad8127623e43f27a68) 2026-11-09 `test(reviews): specify the review summary of the barbershop and each barber`
  - [`0b51d02`](https://github.com/code-corhuila/barber-saas-barbershop-app/commit/0b51d029877b4592f58e1ee85f6a5876844c1da3) 2026-11-09 `feat(reviews): read the review summary of a barbershop or a barber`
  - [`ab14784`](https://github.com/code-corhuila/barber-saas-barbershop-app/commit/ab14784154add5bbd45f613a252f8d0f5ffed4f2) 2026-11-09 `feat(reviews): show the rating of the barbershop and of each barber in gold`
  - [`4db0434`](https://github.com/code-corhuila/barber-saas-barbershop-app/commit/4db04347e249bcd45260ecb5bcf54b279055999f) 2026-11-09 `docs(readme): explain where the ratings come from`
  - [`e37185d`](https://github.com/code-corhuila/barber-saas-barbershop-app/commit/e37185d3f03496bcf61bba15f5f6bbea2bc359a5) 2026-11-09 `fix(catalog): type the public barbers without the account id`
  - [`3b4a815`](https://github.com/code-corhuila/barber-saas-barbershop-app/commit/3b4a8152daf3521799fc525fd0df8d5bd45f630d) 2026-11-09 `docs(changelog): record release 3.0.0`
- `main` (10):
  - [`53317a1`](https://github.com/code-corhuila/barber-saas-barbershop-app/commit/53317a1d798c65a91e76d521ac37042e8bd1e732) 2026-11-13 `test(plan): specify the barber limit check before adding a barber`
  - [`4072a43`](https://github.com/code-corhuila/barber-saas-barbershop-app/commit/4072a43d7f5d6bc9fd9206c7d25fbeeab2005eb9) 2026-11-13 `feat(plan): check the plan's barber limit with my barbershop and my subscription`
  - [`efc5429`](https://github.com/code-corhuila/barber-saas-barbershop-app/commit/efc54291863e90cd978fac801eae1a67dbf0c61a) 2026-11-13 `feat(barbers): show the plan's barber limit before adding a barber`
  - [`e95b13f`](https://github.com/code-corhuila/barber-saas-barbershop-app/commit/e95b13ff809350250dd196857930718c79ac9357) 2026-11-13 `docs(readme): explain the barber limit on the team screen`
  - [`b346ec9`](https://github.com/code-corhuila/barber-saas-barbershop-app/commit/b346ec94db6b369fa2eac4ccac050f77c6dec74a) 2026-11-13 `test(reviews): specify the review summary of the barbershop and each barber`
  - [`97ccdaa`](https://github.com/code-corhuila/barber-saas-barbershop-app/commit/97ccdaae4274949e344d94861430a924877cccf3) 2026-11-13 `feat(reviews): read the review summary of a barbershop or a barber`
  - [`6e21255`](https://github.com/code-corhuila/barber-saas-barbershop-app/commit/6e21255b95d34711594dd7ccbd0cdeaaf27bc486) 2026-11-13 `feat(reviews): show the rating of the barbershop and of each barber in gold`
  - [`40b9f22`](https://github.com/code-corhuila/barber-saas-barbershop-app/commit/40b9f2218f2344e0ca9a036e4e5af5e058d7513a) 2026-11-13 `docs(readme): explain where the ratings come from`
  - [`c879320`](https://github.com/code-corhuila/barber-saas-barbershop-app/commit/c8793205628c204ba04044d0c5642850fe8120a9) 2026-11-13 `fix(catalog): type the public barbers without the account id`
  - [`357ed08`](https://github.com/code-corhuila/barber-saas-barbershop-app/commit/357ed0854a835d42b629f75ce98a222d0dcee99e) 2026-11-13 `docs(changelog): record release 3.0.0`

**barber-saas-barbershop-db**

- `release.3.0.0` (1):
  - [`231c9ba`](https://github.com/code-corhuila/barber-saas-barbershop-db/commit/231c9ba165a9050301d61cff0f4b7cef33ac4316) 2026-11-09 `docs(changelog): record release 3.0.0`
- `main` (1):
  - [`35ebece`](https://github.com/code-corhuila/barber-saas-barbershop-db/commit/35ebecef06c197800c597fc596d6ad3bf90829eb) 2026-11-13 `docs(changelog): record release 3.0.0`

**barber-saas-schedule-api**

- `develop` (5):
  - [`9773344`](https://github.com/code-corhuila/barber-saas-schedule-api/commit/97733442b8988ab18f3f2d1f5c080734aaa53aa3) 2026-11-10 `test(schedule): lock the weekly schedule to the owner of its barbershop`
  - [`1ce1325`](https://github.com/code-corhuila/barber-saas-schedule-api/commit/1ce13253baebf1bb743cf521cce75e93e8f377f6) 2026-11-10 `test(auth): expect 401 for a service token on the routes of the app`
  - [`eb93794`](https://github.com/code-corhuila/barber-saas-schedule-api/commit/eb9379400f08154541436bc8f771bddb7d07f235) 2026-11-10 `fix(auth): reject service tokens on the routes of the app with 401`
  - [`86c9c9e`](https://github.com/code-corhuila/barber-saas-schedule-api/commit/86c9c9edd9227f572a29849ee98bbf22718e0de3) 2026-11-10 `feat(auth): verify that a token for the app names barber-saas-app as its audience`
  - [`54f4f3b`](https://github.com/code-corhuila/barber-saas-schedule-api/commit/54f4f3b86ef741e6738ea181a46f5e7cfb3c7900) 2026-11-10 `feat(auth): require the app audience on the routes of the app`
- `qa` (5):
  - [`d64c639`](https://github.com/code-corhuila/barber-saas-schedule-api/commit/d64c6399f7a0b7123551386d535d5782516dbadd) 2026-11-10 `test(schedule): lock the weekly schedule to the owner of its barbershop`
  - [`7cf0df6`](https://github.com/code-corhuila/barber-saas-schedule-api/commit/7cf0df60ff92d93e3fe44c9a8cb7000e96b1a7cb) 2026-11-10 `test(auth): expect 401 for a service token on the routes of the app`
  - [`6e7211e`](https://github.com/code-corhuila/barber-saas-schedule-api/commit/6e7211e2fd2c4c75d05bc3b516e11cfada84f459) 2026-11-10 `fix(auth): reject service tokens on the routes of the app with 401`
  - [`b7fb3d0`](https://github.com/code-corhuila/barber-saas-schedule-api/commit/b7fb3d026accccfa5878698cb851fd3044501823) 2026-11-10 `feat(auth): verify that a token for the app names barber-saas-app as its audience`
  - [`ea0562c`](https://github.com/code-corhuila/barber-saas-schedule-api/commit/ea0562c2fa5cd11e9a7def95f9b2e05bb358bfaf) 2026-11-10 `feat(auth): require the app audience on the routes of the app`
- `release.3.0.0` (6):
  - [`fc7fb12`](https://github.com/code-corhuila/barber-saas-schedule-api/commit/fc7fb127abc28da301e45d13d8045371552bc714) 2026-11-09 `docs(changelog): record release 3.0.0`
  - [`15efca8`](https://github.com/code-corhuila/barber-saas-schedule-api/commit/15efca88248e5ed47df394de9935010f618b648b) 2026-11-10 `test(schedule): lock the weekly schedule to the owner of its barbershop`
  - [`52b104c`](https://github.com/code-corhuila/barber-saas-schedule-api/commit/52b104c15a9ce3431a7378ebef9eb7dca2e30413) 2026-11-10 `test(auth): expect 401 for a service token on the routes of the app`
  - [`2b4ab6c`](https://github.com/code-corhuila/barber-saas-schedule-api/commit/2b4ab6c01d00f58b3aa1cc1dda7867f0d2cc6395) 2026-11-10 `fix(auth): reject service tokens on the routes of the app with 401`
  - [`d77d90f`](https://github.com/code-corhuila/barber-saas-schedule-api/commit/d77d90fa721d3f7232347893ec36a52f92089b76) 2026-11-10 `feat(auth): verify that a token for the app names barber-saas-app as its audience`
  - [`c6ac89a`](https://github.com/code-corhuila/barber-saas-schedule-api/commit/c6ac89ac712301509e73521628fe91668a493149) 2026-11-10 `feat(auth): require the app audience on the routes of the app`
- `main` (6):
  - [`c8187b6`](https://github.com/code-corhuila/barber-saas-schedule-api/commit/c8187b664f95549927c46ccc88fade6248e8277d) 2026-11-13 `docs(changelog): record release 3.0.0`
  - [`2def08c`](https://github.com/code-corhuila/barber-saas-schedule-api/commit/2def08cc1b206dd9d3504c9829ad952ccf6d3f90) 2026-11-13 `test(schedule): lock the weekly schedule to the owner of its barbershop`
  - [`c69f234`](https://github.com/code-corhuila/barber-saas-schedule-api/commit/c69f234f96946d329c41b1a36017e1a53b2d36e0) 2026-11-13 `test(auth): expect 401 for a service token on the routes of the app`
  - [`fe1dc9c`](https://github.com/code-corhuila/barber-saas-schedule-api/commit/fe1dc9c06207e0ff5feff9b45efc30b9c3848556) 2026-11-13 `fix(auth): reject service tokens on the routes of the app with 401`
  - [`e0c023c`](https://github.com/code-corhuila/barber-saas-schedule-api/commit/e0c023c69632186c3608c13d7100a69661ef3df8) 2026-11-13 `feat(auth): verify that a token for the app names barber-saas-app as its audience`
  - [`600e1ef`](https://github.com/code-corhuila/barber-saas-schedule-api/commit/600e1ef2f1326f4a47352b6ba4ad2139c1636983) 2026-11-13 `feat(auth): require the app audience on the routes of the app`

**barber-saas-schedule-app**

- `release.3.0.0` (1):
  - [`234f74a`](https://github.com/code-corhuila/barber-saas-schedule-app/commit/234f74a3d9128b4c66006a1cf602ced7b0d181d5) 2026-11-09 `docs(changelog): record release 3.0.0`
- `main` (1):
  - [`91dea87`](https://github.com/code-corhuila/barber-saas-schedule-app/commit/91dea87acaedb281f27620f4154bf3ae6435b854) 2026-11-13 `docs(changelog): record release 3.0.0`

**barber-saas-schedule-db**

- `release.3.0.0` (1):
  - [`22958c1`](https://github.com/code-corhuila/barber-saas-schedule-db/commit/22958c1304594b21580594f0f8186adece0c1a3d) 2026-11-09 `docs(changelog): record release 3.0.0`
- `main` (1):
  - [`f9cbdbd`](https://github.com/code-corhuila/barber-saas-schedule-db/commit/f9cbdbde6c6c9562f00ca8e53f5e5423e39cd35d) 2026-11-13 `docs(changelog): record release 3.0.0`

**barber-saas-notifications-api**

- `release.3.0.0` (20):
  - [`29f8dac`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/29f8dac4c308ca7eca51ddac70bd1be2372d7a4a) 2026-11-09 `test(events): specify the e-mail verification code like the reset code`
  - [`b59aca2`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/b59aca2bac9e48bd31248c5e7012ce772b3aa104) 2026-11-09 `feat(events): e-mail the verification code like the reset code`
  - [`d995f03`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/d995f03d245365d021445444f2718628a7ecb635) 2026-11-09 `docs(readme): list the verification code with the reset code`
  - [`c9014e5`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/c9014e5cf6e8f7f32efa05ce7b40a8b36cc75cbd) 2026-11-09 `test(events): specify the plan notices to the owners by inbox, push and e-mail`
  - [`a726571`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/a7265710cc35caa3409e9859c6697506ac96b5ae) 2026-11-09 `feat(domain): word the plan notices for each owner of the barbershop`
  - [`f847ca9`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/f847ca993382398b0fb85dd880f4f6917dec9c3f) 2026-11-09 `feat(application): e-mail a notice as a second channel without blocking the event`
  - [`1911475`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/19114756542f66771a3e35e33bb6c1b113a9d1d1) 2026-11-09 `docs(readme): explain the notices e-mailed to the owners and when smtp is missing`
  - [`5e4d184`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/5e4d1843787d59863f218e37fe83401768700131) 2026-11-09 `test(events): specify both reminder windows and their e-mail to the client`
  - [`d411c89`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/d411c89a2e1a2f6fa7e8d53ded6a2a04d37eabc5) 2026-11-09 `feat(domain): word the hour-before reminder and mark reminders to be e-mailed`
  - [`87b8308`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/87b8308c346fd5ae4b63c66d4e3d1beb6e68cbc3) 2026-11-09 `feat(application): e-mail reminders to the client's address read from identity-auth`
  - [`3681707`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/3681707c5bc2627bbf100efb59ce4ff3b4a9c48a) 2026-11-09 `feat(identity): read a user's contact from identity-auth with the service token`
  - [`c93d7b5`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/c93d7b5470f6b2becbe1908a565fc2161b9cdc28) 2026-11-09 `test(app): specify the identity-auth settings of the composition root`
  - [`c7969bd`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/c7969bdf25125ddc7334c4af067520536778e269) 2026-11-09 `feat(app): compose the identity-auth contact reader with its own service token`
  - [`70b95d2`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/70b95d2c09b21d45e2abf4e5bf7d50cf7c879bcd) 2026-11-09 `docs(readme): explain the reminder windows and their e-mail`
  - [`47ec387`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/47ec387b1d1c2086794ceec74883eb48f436d154) 2026-11-09 `test(events): specify one info line per handled event, without the payload`
  - [`e90021c`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/e90021c8b7cc4813e02bb5fa35e1cf06263ce48a) 2026-11-09 `feat(application): log every handled event with its id, type and outcome`
  - [`56df20e`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/56df20e76cdfa51464eb9b315b5d9f9708b17af9) 2026-11-09 `docs(readme): explain the log line of each handled event`
  - [`fffb4a9`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/fffb4a9f2ddc6e9c4d9105f22c0d8b2bcbce64f0) 2026-11-09 `test(events): specify the plan and barbershop names in the notices, with the text without them as fallback`
  - [`12d294d`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/12d294de21a53e645d00fab2ed67318cad53c566) 2026-11-09 `feat(domain): name the plan and the barbershop in the notices when the event carries them`
  - [`854a5d5`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/854a5d52469cb4f3e6609b7cd28ada1ed296adc8) 2026-11-09 `docs(changelog): record release 3.0.0`
- `main` (20):
  - [`1d97ba6`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/1d97ba614dfe39587faf82f10ae5a217394adcbe) 2026-11-13 `test(events): specify the e-mail verification code like the reset code`
  - [`b4a225b`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/b4a225b6ec740bc3648f808a1d8b2fdb6145b743) 2026-11-13 `feat(events): e-mail the verification code like the reset code`
  - [`c7486a1`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/c7486a195f14ff4da3b26262478d365510db8df8) 2026-11-13 `docs(readme): list the verification code with the reset code`
  - [`fb55390`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/fb55390774d2e119e08cbe93ea910a808888de75) 2026-11-13 `test(events): specify the plan notices to the owners by inbox, push and e-mail`
  - [`9bc417c`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/9bc417cfa25cefc9a42299f061adae1086898e97) 2026-11-13 `feat(domain): word the plan notices for each owner of the barbershop`
  - [`314f313`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/314f3133e29bfd8ad4f3bb0b2244b27e11b18444) 2026-11-13 `feat(application): e-mail a notice as a second channel without blocking the event`
  - [`fbd604f`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/fbd604ffc83397a92a68ec963177e0851457d563) 2026-11-13 `docs(readme): explain the notices e-mailed to the owners and when smtp is missing`
  - [`46111ae`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/46111ae22840e1e9f79509cef604c3d6ffb59559) 2026-11-13 `test(events): specify both reminder windows and their e-mail to the client`
  - [`4a51992`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/4a519922912464d82b27eea07bf0b4665cc69ce4) 2026-11-13 `feat(domain): word the hour-before reminder and mark reminders to be e-mailed`
  - [`96fde1a`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/96fde1a1b97c2f1eaa684ca2e4f96740a064bc3c) 2026-11-13 `feat(application): e-mail reminders to the client's address read from identity-auth`
  - [`7bd380b`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/7bd380b45260493863448f2379ca0b26a30ef9ed) 2026-11-13 `feat(identity): read a user's contact from identity-auth with the service token`
  - [`5204fdd`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/5204fdd0427541a91b2df1c1440a9416099e929b) 2026-11-13 `test(app): specify the identity-auth settings of the composition root`
  - [`36735b2`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/36735b24c562e849aa4397be6641299ce82b1693) 2026-11-13 `feat(app): compose the identity-auth contact reader with its own service token`
  - [`5cb4891`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/5cb4891feaf5ac0b1aee7c388c6ee22df0217d2f) 2026-11-13 `docs(readme): explain the reminder windows and their e-mail`
  - [`2541152`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/25411526813afe633b9f08fb7c0b7af977cf508f) 2026-11-13 `test(events): specify one info line per handled event, without the payload`
  - [`3eff3bb`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/3eff3bb0247baaceac738c0e2b2ae9edf45346bd) 2026-11-13 `feat(application): log every handled event with its id, type and outcome`
  - [`3ac532e`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/3ac532e8c7b0a93f097c63735451a5064437b14e) 2026-11-13 `docs(readme): explain the log line of each handled event`
  - [`d16e750`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/d16e750139e14469998a558bb4b321ed2a989f42) 2026-11-13 `test(events): specify the plan and barbershop names in the notices, with the text without them as fallback`
  - [`bd7c5a4`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/bd7c5a49b8c71838da3d0b70d97d50ce850f6853) 2026-11-13 `feat(domain): name the plan and the barbershop in the notices when the event carries them`
  - [`df062f5`](https://github.com/code-corhuila/barber-saas-notifications-api/commit/df062f5c98979fa30da392c0477b12b444f67780) 2026-11-13 `docs(changelog): record release 3.0.0`

**barber-saas-notifications-app**

- `release.3.0.0` (1):
  - [`a43862d`](https://github.com/code-corhuila/barber-saas-notifications-app/commit/a43862d37e3e8c8626b738178e973b1563b3d19e) 2026-11-09 `docs(changelog): record release 3.0.0`
- `main` (1):
  - [`2c68fc7`](https://github.com/code-corhuila/barber-saas-notifications-app/commit/2c68fc73b879a3c7e7f0a049624b0507a05d191f) 2026-11-13 `docs(changelog): record release 3.0.0`

**barber-saas-notifications-db**

- `release.3.0.0` (4):
  - [`72128e8`](https://github.com/code-corhuila/barber-saas-notifications-db/commit/72128e88d4562a09851183d8c7a7714c01af5ab1) 2026-11-09 `test(structure): accept a processed e-mail verification and refuse a notice type`
  - [`be40160`](https://github.com/code-corhuila/barber-saas-notifications-db/commit/be40160bc531885e58584504d2712ded7761f82d) 2026-11-09 `fix(ddl): accept EmailVerificationRequested in processed_event`
  - [`840fb46`](https://github.com/code-corhuila/barber-saas-notifications-db/commit/840fb46accf99628bcd372cdf70f31fab560b8f5) 2026-11-09 `docs(changelog): record the processed_event fix`
  - [`75949b7`](https://github.com/code-corhuila/barber-saas-notifications-db/commit/75949b7a67bc3143e3c394ac7d1ea3fef4e0ff43) 2026-11-09 `docs(changelog): record release 3.0.0`
- `main` (4):
  - [`26692a5`](https://github.com/code-corhuila/barber-saas-notifications-db/commit/26692a53b5f27992bde43b78817a9c2c82c671c3) 2026-11-13 `test(structure): accept a processed e-mail verification and refuse a notice type`
  - [`10269ba`](https://github.com/code-corhuila/barber-saas-notifications-db/commit/10269ba752f887a5db8c46009c50b7218dffd0d5) 2026-11-13 `fix(ddl): accept EmailVerificationRequested in processed_event`
  - [`ebdd4e0`](https://github.com/code-corhuila/barber-saas-notifications-db/commit/ebdd4e0c76c8054b1696a4f76b27539b76656529) 2026-11-13 `docs(changelog): record the processed_event fix`
  - [`af2a3de`](https://github.com/code-corhuila/barber-saas-notifications-db/commit/af2a3de02939e2baecd36525cd2bd08db18ac30c) 2026-11-13 `docs(changelog): record release 3.0.0`

Total: 142 commit entries (the same change counts once per branch it reached).

### 8.2 Pull requests I opened (27)

- [`barbershop-api#30`](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/30) `hu-mvp3-release → release.3.0.0`, opened by me, merged by me on 2026-11-09
- [`barbershop-api#31`](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/31) `release.3.0.0 → main`, opened by me, merged by Pipecerquera, rebase on 2026-11-12
- [`barbershop-api#32`](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/32) `test/route-caller-split → develop`, opened by me, merged by me on 2026-11-10
- [`barbershop-api#33`](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/33) `feat/require-app-audience → develop`, opened by me, merged by me on 2026-11-10
- [`barbershop-api#34`](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/34) `hu-security-qa → qa`, opened by me, merged by me on 2026-11-10
- [`barbershop-api#35`](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/35) `hu-security-release → release.3.0.0`, opened by me, merged by me on 2026-11-10
- [`barbershop-app#25`](https://github.com/code-corhuila/barber-saas-barbershop-app/pull/25) `hu-mvp3-release → release.3.0.0`, opened by me, merged by me on 2026-11-09
- [`barbershop-app#26`](https://github.com/code-corhuila/barber-saas-barbershop-app/pull/26) `release.3.0.0 → main`, opened by me, merged by Pipecerquera, rebase on 2026-11-12
- [`barbershop-db#14`](https://github.com/code-corhuila/barber-saas-barbershop-db/pull/14) `hu-mvp3-release → release.3.0.0`, opened by me, merged by me on 2026-11-09
- [`barbershop-db#15`](https://github.com/code-corhuila/barber-saas-barbershop-db/pull/15) `release.3.0.0 → main`, opened by me, merged by Pipecerquera, rebase on 2026-11-12
- [`schedule-api#25`](https://github.com/code-corhuila/barber-saas-schedule-api/pull/25) `hu-mvp3-release → release.3.0.0`, opened by me, merged by me on 2026-11-09
- [`schedule-api#26`](https://github.com/code-corhuila/barber-saas-schedule-api/pull/26) `release.3.0.0 → main`, opened by me, merged by Pipecerquera, rebase on 2026-11-12
- [`schedule-api#27`](https://github.com/code-corhuila/barber-saas-schedule-api/pull/27) `test/weekly-schedule-authorization → develop`, opened by me, merged by me on 2026-11-10
- [`schedule-api#28`](https://github.com/code-corhuila/barber-saas-schedule-api/pull/28) `test/route-caller-split → develop`, opened by me, merged by me on 2026-11-10
- [`schedule-api#29`](https://github.com/code-corhuila/barber-saas-schedule-api/pull/29) `feat/require-app-audience → develop`, opened by me, merged by me on 2026-11-10
- [`schedule-api#30`](https://github.com/code-corhuila/barber-saas-schedule-api/pull/30) `hu-security-qa → qa`, opened by me, merged by me on 2026-11-10
- [`schedule-api#31`](https://github.com/code-corhuila/barber-saas-schedule-api/pull/31) `hu-security-release → release.3.0.0`, opened by me, merged by me on 2026-11-10
- [`schedule-app#19`](https://github.com/code-corhuila/barber-saas-schedule-app/pull/19) `hu-mvp3-release → release.3.0.0`, opened by me, merged by me on 2026-11-09
- [`schedule-app#20`](https://github.com/code-corhuila/barber-saas-schedule-app/pull/20) `release.3.0.0 → main`, opened by me, merged by Pipecerquera, rebase on 2026-11-12
- [`schedule-db#13`](https://github.com/code-corhuila/barber-saas-schedule-db/pull/13) `hu-mvp3-release → release.3.0.0`, opened by me, merged by me on 2026-11-09
- [`schedule-db#14`](https://github.com/code-corhuila/barber-saas-schedule-db/pull/14) `release.3.0.0 → main`, opened by me, merged by Pipecerquera, rebase on 2026-11-12
- [`notifications-api#28`](https://github.com/code-corhuila/barber-saas-notifications-api/pull/28) `hu-mvp3-release → release.3.0.0`, opened by me, merged by me on 2026-11-09
- [`notifications-api#29`](https://github.com/code-corhuila/barber-saas-notifications-api/pull/29) `release.3.0.0 → main`, opened by me, merged by Pipecerquera, rebase, without approval on 2026-11-12
- [`notifications-app#12`](https://github.com/code-corhuila/barber-saas-notifications-app/pull/12) `hu-mvp3-release → release.3.0.0`, opened by me, merged by me on 2026-11-09
- [`notifications-app#13`](https://github.com/code-corhuila/barber-saas-notifications-app/pull/13) `release.3.0.0 → main`, opened by me, merged by Pipecerquera, rebase on 2026-11-12
- [`notifications-db#13`](https://github.com/code-corhuila/barber-saas-notifications-db/pull/13) `hu-mvp3-release → release.3.0.0`, opened by me, merged by me on 2026-11-09
- [`notifications-db#14`](https://github.com/code-corhuila/barber-saas-notifications-db/pull/14) `release.3.0.0 → main`, opened by me, merged by Pipecerquera, rebase on 2026-11-12

PRs I merged: the 18 above marked "merged by me" (fill and promotion PRs, and the PRs to `develop`).

### 8.3 Comments, reviews and issues

- 2026-11-10, comment on [`barbershop-api#31`](https://github.com/code-corhuila/barber-saas-barbershop-api/pull/31#issuecomment-6780771176): the new trail table after the security fixes, to ask for the approval again.
- 2026-11-10, comment on [`schedule-api#26`](https://github.com/code-corhuila/barber-saas-schedule-api/pull/26#issuecomment-6780772363): the same, for schedule-api.
- 2026-11-10, closing comment on [`docs#163`](https://github.com/code-corhuila/barber-saas-docs/issues/163#issuecomment-6781085419), and I closed the issue.
- 2026-11-10, closing comment on [`docs#164`](https://github.com/code-corhuila/barber-saas-docs/issues/164#issuecomment-6781087260), and I closed the issue.
- Reviews: none this week. My last review was on [`docs#154`](https://github.com/code-corhuila/barber-saas-docs/pull/154) on 2026-10-29, outside this week.
- Issues closed by me: `docs#163` and `docs#164` (2026-11-10). `docs#165` was closed by Pipecerquera on 2026-11-10.

### 8.4 Commits in this repository (WEEKLY)

- [`e5998fa`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/e5998fa) 2026-11-07 `docs(certification): add chapter 8 evidence`
- [`f21f5ac`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/f21f5ac) 2026-11-09 `docs(week-14): complete hu-status with MVP 3 promotion and #165`
- [`42aaeac`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/42aaeac) 2026-11-09 `docs(certification): fill the study log up to chapter 9`
- [`352166b`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/352166b) 2026-11-09 `docs(certification): add chapter 9 evidence and study log row`
- [`0e842ed`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/0e842ed) 2026-11-10 `docs(certification): add chapter 10 evidence and study log row`
- [`a424d83`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/a424d83) 2026-11-10 `docs(certification): add chapter 11 evidence and study log row`
- [`dd0a3e4`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/dd0a3e4) 2026-11-11 `docs(certification): add chapter 12 evidence and study log row`
- [`c88af19`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/c88af19) 2026-11-12 `docs(certification): add chapter 13 evidence and study log row`
- [`23bd75e`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/23bd75e) 2026-11-12 `docs(certification): add chapter 14 evidence and study log row`
- [`97b6de2`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/97b6de2) 2026-11-12 `docs(week-15): complete hu-status with release 3.0.0 to main and #163/#164`
- [`a32e412`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/a32e412) 2026-11-12 `docs(week-12): complete hu-status with the observability workshop deliveries`
- [`2734ff4`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/2734ff4) 2026-11-13 `docs(certification): add final exam evidence and certificate`
- [`a4d75f1`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/a4d75f1) 2026-11-13 `docs(certification): complete cover, labs and skills summary`
- [`ed80152`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/ed80152) 2026-11-13 `docs(certification): add architecture design note, target diagram and ADRs`

### 8.5 Work outside GitHub

- 2026-11-09: ran the whole platform from a clean clone on my laptop (clone, keys, `up.sh`, migrations): 13 healthy containers; a second run of the migrations applied zero changes.
- 2026-11-09: recorded a promotional video for end users (1 min 28 s) and a setup video for technical users (2 min 56 s) for the final presentation.
- 2026-11-09 to 2026-11-12: studied chapters 9 to 14 of the Huawei course (study log in `certification/README.md`).
- 2026-11-12: the team presented the final project with a live demo on the Azure host, and delivered `c3-release` and `c3-project` in Moodle.
- 2026-11-13: passed the final exam of the Huawei course (88 points) and got the certificate.
<!-- FULL-EVIDENCE-END -->
