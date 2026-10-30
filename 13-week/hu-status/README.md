<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.
     Your weekly grade is read AUTOMATICALLY from this file:
       13-week/hu-status/README.md  (inside YOUR fork). English. -->

# Weekly Status - Week 13

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Carlos Mauricio Leal Medina
- GITHUB_USER: carlosleal16
- TEAM: Barberssas
- SPRINT_GOAL: Threat-model the boundary I own (`api-gateway` → `barbershop-api` / `schedule-api`) and turn its highest risks into security stories with automated tests, so that next sprint starts with a prioritized security backlog for my two services.
<!-- CONFIG-END -->

## 1. User stories worked this week
| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| W13-WORKSHOP-D1 | As a student, I want a STRIDE analysis of the `api-gateway` → `barbershop-api` / `schedule-api` boundary turned into security stories and CI gates, so that the risks of my services are visible and testable | done (progress delivery CP0 to CP5 on 2026-10-29; final delivery CP6 to CP9 on 2026-10-30) | CP0 [`3d630d1`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/3d630d1); CP1 [`2f9dfbb`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/2f9dfbb); CP2 [`barber-saas-docs#161`](https://github.com/code-corhuila/barber-saas-docs/pull/161), [`bc35d77`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/bc35d77); CP3 [`8877825`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/8877825); CP4 [`b92a60f`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/b92a60f), [`16c10c8`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/16c10c8); CP6 [`barber-saas-docs@2cce556`](https://github.com/code-corhuila/barber-saas-docs/commit/2cce556) + [reply](https://github.com/code-corhuila/barber-saas-docs/pull/161#issuecomment-6532109719); CP7 [`e7bf561`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/e7bf561); CP8 [`fdbef10`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/fdbef10) |
| [#163](https://github.com/code-corhuila/barber-saas-docs/issues/163) | [security] An owner cannot change the services or schedules of another barbershop (E, risk 6, P1) | todo (backlog; criterion 1 already met by `CatalogHttpTest`; criterion 2 test written and passing, PR to `schedule-api` pending) | [`04-security-ci`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/tree/main/13-week/workshop/04-security-ci) |
| [#164](https://github.com/code-corhuila/barber-saas-docs/issues/164) | [security] barbershop-api and schedule-api split user and service tokens by route (S, risk 6, P1) | todo (backlog; refined after review, related to #155 and #156) | Refs #161 |
| [#165](https://github.com/code-corhuila/barber-saas-docs/issues/165) | [security] The public barber list does not expose the barber's account id (I, risk 6, P1) | todo (backlog) | Refs #161 |

## 2. My individual contribution
- **CP0:** claimed boundary TB2b in [`barber-saas-docs#152`](https://github.com/code-corhuila/barber-saas-docs/issues/152) and started `13-week/workshop/README.md` with the CONFIG block.
- **CP1:** attack surface map ([`01-attack-surface.md`](../workshop/01-attack-surface.md) and its PNG): every service, both databases, the worker relay and the external FCM/SMTP, seven trust boundaries, eight assets and the entry points of my boundary, all taken from `dependency-map.md`, `data-ownership-matrix.md` and the gateway route files.
- **CP2:** STRIDE in [`05-architecture/security/stride-carlosleal16.md`](https://github.com/code-corhuila/barber-saas-docs/pull/161), two pushes. Main findings from the code: `Rs256Verifier` does not check an audience; the anonymous barber list returns `userId`; the access log has no actor; `X-Correlation-Id` is copied verbatim. Library book: Costas Santos, *Seguridad informática* (2011), call number 005.8 C838s Ej.1, record 08:40.
- **CP3:** three security stories (#163, #164, #165), labels `security` + `P1`, each with an automated acceptance criterion and the book page.
- **CP4:** `security.yml` (gitleaks, OWASP dependency-check, authorization tests) and `WeeklyScheduleAuthorizationHttpTest` for `PUT /api/v1/barber-schedules/{barberId}`: another barbershop's owner gets 404, a barber or client 403, no token 401. I ran it against `barber-saas-schedule-api`: `Tests run: 3, Failures: 0, Errors: 0`.
- **CP5:** review of `barber-saas-docs#154` (JUANDAX233), [row I](https://github.com/code-corhuila/barber-saas-docs/pull/154#discussion_r4413632481): `identity-auth-api` is a missing producer and its `PasswordResetRequested` envelope carries the e-mail and the reset code.
- **CP6:** answered @Pipecerquera's review on #161. Row S no longer isolates port 8080 (other services call it legitimately); it splits `/api/v1/**` (user tokens with `aud`) from `/internal/v1/**` (service tokens). Row D links #155. Row E corrected: `barbershop-api` already had the cross-tenant test for services, the gap was `schedule-api`.
- **CP7:** #163 marks criterion 1 as met and points to the schedule test; #164 has a new title, story and route-split criterion, related to #155 and #156.
- **CP8:** ran the test again (`Tests run: 3, Failures: 0, Errors: 0`, `BUILD SUCCESS`, 15:05) and recorded it in `HOW-TO-RUN.md`; `security.yml` parsed and checked (actionlint not run, Docker was stopped).
- **CP9:** implementation plan written by hand in the reflection document.

## 3. Blockers and risks
- The issues #163, #164 and #165 still have to be added to the team Project board by hand: my `gh` token has no `project` scope.
- The final-delivery pushes all happened on 2026-10-30; I had no push on Thursday after class.
- #164 needs `identity-auth-api` to issue an `aud` claim, which belongs to another domain owner.

## 4. Plan for next week
- Open a PR to `barber-saas-schedule-api` (`develop`) with `WeeklyScheduleAuthorizationHttpTest` and `security.yml`, referencing #163.
- Change the contract first (`PublicBarber` in `barbershop-service.yaml`) and then `barbershop-api` for #165.
- Agree the `aud` claim with the owner of `identity-auth-api` and the per-`sub` quota with #155 before starting #164.

## 5. Compliance self-check
- [x] Conventional Commits - `type(scope): summary`: every workshop commit (`docs(w13)`, `docs(security)`, `ci(w13)`).
- [ ] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...): no code PR this week; the STRIDE PR goes to `main` of the docs repository, the only branch it has.
- [x] Testable acceptance criteria: each security story has at least one automated test as criterion.
- [x] Tests added/updated (unit / integration): `WeeklyScheduleAuthorizationHttpTest` (3 HTTP tests), still in my fork.
- [x] DDD / hexagonal boundaries respected (domain has no I/O): the test goes through the HTTP adapter; the checks stay in the use cases.
- [x] No secrets; config via environment variables: the workflow reads `NVD_API_KEY` from the repository secrets.

## 6. Evidence links
- Workshop folder: https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/tree/main/13-week/workshop
- STRIDE pull request: https://github.com/code-corhuila/barber-saas-docs/pull/161
- Security stories: https://github.com/code-corhuila/barber-saas-docs/issues/163, https://github.com/code-corhuila/barber-saas-docs/issues/164, https://github.com/code-corhuila/barber-saas-docs/issues/165
- Review comment: https://github.com/code-corhuila/barber-saas-docs/pull/154#discussion_r4413632481
- Reflection document: https://docs.google.com/document/d/1oc5ba6eA5cetRJ94wCOSNe-6cDnee1tfcQxKl5_ZA2k/edit?usp=sharing
