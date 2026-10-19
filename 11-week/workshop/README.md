# Week 11 Workshop - Observability on BarberSaaS

<!-- CONFIG-START -->
- FULL_NAME: Carlos Mauricio Leal Medina
- GITHUB_USER: carlosleal16
- TEAM: Barberssas
- DOCS_REPO: https://github.com/code-corhuila/barber-saas-docs
- REFLECTION_DOC: https://docs.google.com/document/d/1J74zaHFKBA1lAYbcK-ydkZlN8WuNln6aUWjgB0KXV6U/edit?usp=sharing
- JOURNEY: (written in CP1)
<!-- CONFIG-END -->

## Scope

The workshop takes one critical journey of BarberSaaS and makes it observable end to end.
I own `barbershop-db/api/app` and `schedule-db/api/app`, and I support
`notifications-db/api/app`, so the journey crosses those services.

## Checkpoints

| CP | File | Topic |
|---|---|---|
| CP1 | [01-journey.md](01-journey.md) | Critical journey and trace map |
| CP2 | [02-log-contract.md](02-log-contract.md) | Structured log contract |
| CP3 | [03-instrumentation/](03-instrumentation/) | Instrumentation code and test |
| CP4 | [04-metrics.md](04-metrics.md) | Golden signals and metric catalog |
| CP6 | [05-slo.md](05-slo.md) | SLIs, SLOs and error budget |
| CP7 | [06-alerts.yml](06-alerts.yml), [06-alert-policy.md](06-alert-policy.md) | Burn-rate alerts |
| CP8 | [07-runbook.md](07-runbook.md) | Runbook |
| CP9 | [08-incident.md](08-incident.md) | Incident drill and postmortem |
