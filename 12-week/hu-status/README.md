<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.
     Your weekly grade is read AUTOMATICALLY from this file:
       12-week/hu-status/README.md  (inside YOUR fork). English. -->

# Weekly Status - Week 12

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Carlos Mauricio Leal Medina
- GITHUB_USER: carlosleal16
- TEAM: Barberssas
- SPRINT_GOAL: Finish both deliveries of the observability workshop on the "book an appointment" journey of BarberSaaS (trace context, structured logs, metrics, three SLOs, burn-rate alerts, a runbook and an incident drill), so that the MVP 3 stories that open on 2026-10-26 can be measured against explicit SLOs.
<!-- CONFIG-END -->

## 1. User stories worked this week
| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| W11-WORKSHOP-D1 | As a student, I want one critical journey of BarberSaaS (book an appointment) mapped, logged, instrumented and measured, so that a failed booking can be followed by one trace id | done (2026-10-19, in class) | [`3886300`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/3886300) to [`9c3c097`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/9c3c097): journey map, log contract, `schedule-api` trace context with the test [`6d473e8`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/6d473e8), metric catalog |
| W11-WORKSHOP-D2 | As a student, I want SLOs, burn-rate alerts, a runbook and an incident drill for the "book an appointment" journey, so that the team knows how much failure the clients accept and how to react | done (2026-10-19, take-home) | [`71f5da9`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/71f5da9) SLOs, [`32bac65`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/32bac65) alert rules, [`3b741fa`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/3b741fa) runbook, [`4e29a70`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/4e29a70) incident drill and postmortem |

## 2. My individual contribution
- **Observability workshop, delivery 1 (2026-10-19, in class):** mapped the "book an appointment" journey hop by hop (`01-journey.md`), wrote the structured log contract (`02-log-contract.md`), added a trace-context model, middleware and structured logger to `schedule-api` that propagate the context to `barbershop-api` and expose metrics, with a test that checks the trace id reaches `barbershop-api` (`6d473e8`), and defined the golden signals and the metric catalog (`04-metrics.md`). Folder: [`11-week/workshop`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/tree/main/11-week/workshop).
- **Observability workshop, delivery 2 (2026-10-19, take-home):** three SLOs with their error budgets (`05-slo.md`), fast and slow burn-rate rules for the booking SLO with the budget math (`06-alerts.yml`, `06-alert-policy.md`), the runbook of the fast-burn alert with the mitigations our Docker Compose deployment supports (`07-runbook.md`), and an incident drill with a blameless postmortem and three action items written as user stories (`08-incident.md`).
- **Report update (2026-10-20):** [`427bcf7`](https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/427bcf7).
- Every commit above is in my fork and follows Conventional Commits (`docs(w11)`, `feat(w11)`, `test(w11)`).

## 3. Blockers and risks
- **The same work appears in my week 11 report.** Both workshop deliveries were committed on 2026-10-19 and 2026-10-20, and I summarized them in the week 11 report on 2026-10-20 (`427bcf7`). This report lists them with their dates; it does not add new work.
- **No product commits this week.** I found no commits of mine in the `code-corhuila/barber-saas-*` repositories between 2026-10-19 and 2026-10-25 (checked with the GitHub commits API for every repository). The MVP 3 stories (#122 to #131) were published on 2026-10-26, so my product work restarted in week 13.
- The incident drill of `08-incident.md` found a real risk in my service: `schedule-api` holds a database connection during its two HTTP calls, so a traffic peak can exhaust `DB_POOL_MAX=10`. It is written as action items, not yet as a backlog issue.

## 4. Plan for next week
- Week 13: close my part of five MVP 3 stories in `develop` (e-mail verification code, plan notices to the owners, reminder by e-mail, barber limit of the plan, ratings in the barbershop app).
- Week 13 workshop: a STRIDE analysis of the boundary I own (`api-gateway` → `barbershop-api` / `schedule-api`), turning its highest risks into security stories with automated tests.

## 5. Compliance self-check
- [x] Conventional Commits - `type(scope): summary`: `docs(w11)`, `feat(w11)`, `test(w11)`.
- [ ] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...): not met this week. The work is in my fork, committed to `main`, as the course repository expects; no PR to the product repositories.
- [x] Testable acceptance criteria: each SLO states its SLI, target and window, and each alert its threshold and `for:` duration (`05-slo.md`, `06-alerts.yml`).
- [x] Tests added/updated (unit / integration): `6d473e8 test(w11)`, the trace id reaches `barbershop-api`.
- [ ] DDD / hexagonal boundaries respected (domain has no I/O): partial. The workshop copy of `schedule-api` is a single module; the trace context, filter and logger are kept apart in an `observability` package, but there is no domain/adapter split as in the real three-module service.
- [ ] No secrets; config via environment variables: partial. No credentials in the workshop files (`application.properties` has only the name, port, version, environment and `barbershop.base-url`), but those values are written in the file, not read from environment variables.

## 6. Evidence links
- Workshop folder: https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/tree/main/11-week/workshop
- Delivery 1: https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/9c3c097
- Delivery 2: https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/4e29a70
- Report update: https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina/commit/427bcf7
