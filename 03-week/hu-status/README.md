

# Weekly Status - Week 03

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Carlos Mauricio Leal Medina
- GITHUB_USER: carlosleal16
- TEAM: Barberssas
- SPRINT_GOAL: Design and implement the first vertical slice following DDD and Hexagonal Architecture, defining service boundaries, data ownership, contracts, and domain rules.
<!-- CONFIG-END -->

## 1. User stories worked this week
| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| HU-XXX-001 | NONE | NONE | NONE |

## 2. My individual contribution
- Designed the domain model using Tactical DDD concepts such as entities, value objects, aggregates, aggregate roots, invariants, and domain events.
- Identified the aggregate root and defined the business rules that must be protected by the domain.
- Applied Hexagonal Architecture by separating the domain, application, and infrastructure layers.
- Defined ports as interfaces and separated inbound and outbound adapters from the core business logic.
- Reviewed the dependency direction to ensure that infrastructure and framework code do not leak into the domain.

## 3. Blockers and risks
- Defining clear service boundaries can be challenging when multiple services require similar information.
- There is a risk of accidentally coupling services through shared database access or duplicated business rules.
- External or legacy models may introduce naming and structural differences that require an Anti-Corruption Layer.
- Additional care is required to ensure that framework-specific annotations and infrastructure dependencies do not enter the domain layer.

## 4. Plan for next week
- Refine the service boundaries and bounded contexts based on the MVP requirements.
- Review and validate the API contracts between services.
- Complete or improve automated unit and integration tests.
- Review the ports and adapters implementation to ensure that dependencies point inward.

## 5. Compliance self-check
- [X] Conventional Commits - `type(scope): summary`
- [ ] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...)
- [x] Testable acceptance criteria
- [ ] Tests added/updated (unit / integration)
- [ ] DDD / hexagonal boundaries respected (domain has no I/O)
- [ ] No secrets; config via environment variables

## 6. Evidence links
- [Sesion1-Sesion2](Week 3 Summary.png)

- https://github.com/carlosleal16/sistemas-distribuidos-2026-b-g2-carlos-mauricio-leal-medina.git
