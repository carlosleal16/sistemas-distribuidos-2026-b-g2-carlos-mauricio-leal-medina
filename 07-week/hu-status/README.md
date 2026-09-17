<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.
     Your weekly grade is read AUTOMATICALLY from this file:
       07-week/hu-status/README.md  (inside YOUR fork). English. -->

# Weekly Status - Week 07

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Carlos Mauricio Leal Medina
- GITHUB_USER: carlosleal16
- TEAM: Barberssas
- SPRINT_GOAL: Mantener sincronizado el tracking documental del ecosistema (DOCS) mientras arranca la implementación real en CODE.
<!-- CONFIG-END -->

## 1. User stories worked this week
| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| N/A | No se trabajó ninguna HU de producto esta semana; el único cambio fue documentación de gobernanza/tracking en DOCS (no mapea a una HU de `04-requirements`) | done | https://github.com/code-corhuila/barber-saas-docs/commit/3f4822d785eb134433f8aeecd45951965dcd4a4e |

## 2. My individual contribution
- `docs(archive): index MVP cadence and weekly-tracking pointers` (DOCS, commit `3f4822d`, 2026-09-17): agregué `99-archive/mvp-weekly-index.md` para consolidar en un solo lugar dónde viven los hitos del MVP y el tracking académico semanal, sin duplicar contenido que ya es dueño otro documento. 39 líneas agregadas, 1 archivo.
- Rama `docs/archive-mvp-weekly-index`, aún no mergeada a `main` de DOCS a la fecha de este reporte — por eso el estado real de esa contribución es "en PR", no "cerrada".
- Sin commits propios en WEEKLY ni en CODE durante la ventana 2026-09-14 a 2026-09-17 (verificado con `git log --since/--until` en los tres repos).

## 3. Blockers and risks
- **CODE (`barber-saas`) sigue sin código real.** Solo tiene `README.md`; los subproyectos backend (Java/Spring Boot) y móvil (Expo) descritos en la documentación todavía no existen. Sin esto, no hay HUs de producto que reportar con evidencia real.
- **SPEC-007 sigue bloqueado**: falta integrar al mapa del ecosistema los repos propios de Juan Pablo Barrero y de mí (Carlos Leal) — los remotos no están confirmados en `_ecosistema/SPEC-PLAN-PROMPT.md`.
- Riesgo de reportar HUs sin evidencia enlazable si no se prioriza empezar la implementación en CODE antes de la semana 8.

## 4. Plan for next week
- Resolver SPEC-007 (integrar repos de Juan Pablo y propio al mapa del ecosistema) aportando las URLs de remoto pendientes.
- Arrancar la implementación real en CODE (walking skeleton del backend o del móvil) para tener HUs de producto verificables en la próxima entrega.
- Mergear a `main` la rama `docs/archive-mvp-weekly-index` de DOCS (pendiente de PR/aprobación del docente vía CODEOWNERS).

## 5. Compliance self-check
- [x] Conventional Commits - `type(scope): summary` (el commit de la semana sigue el formato)
- [ ] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...) — N/A, no hubo trabajo de código esta semana
- [ ] Testable acceptance criteria — N/A, no hubo HU de producto esta semana
- [ ] Tests added/updated (unit / integration) — N/A, no hubo código esta semana
- [ ] DDD / hexagonal boundaries respected (domain has no I/O) — N/A, CODE todavía no tiene código
- [x] No secrets; config via environment variables (sin secretos en el único cambio de la semana)

## 6. Evidence links
- https://github.com/code-corhuila/barber-saas-docs/commit/3f4822d785eb134433f8aeecd45951965dcd4a4e
- https://github.com/code-corhuila/barber-saas-docs/tree/docs/archive-mvp-weekly-index
