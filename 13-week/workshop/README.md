# Week 13 Workshop - Threat Modeling and the Security Backlog

<!-- CONFIG-START -->
- FULL_NAME: Carlos Mauricio Leal Medina
- GITHUB_USER: carlosleal16
- TEAM: Barberssas
- DOCS_REPO: https://github.com/code-corhuila/barber-saas-docs
- BOUNDARY: api-gateway → barbershop-api / schedule-api (catalog and availability endpoints)
- CLAIM_ISSUE: https://github.com/code-corhuila/barber-saas-docs/issues/152
- REFLECTION_DOC: https://docs.google.com/document/d/1oc5ba6eA5cetRJ94wCOSNe-6cDnee1tfcQxKl5_ZA2k/edit?usp=sharing
- LIBRARY_BOOK: Costas Santos, Jesús. Seguridad informática. 1st ed. Bogotá: Ediciones de la U (Ra-Ma), 2011. Call number 005.8 C838s Ej.1
- LIBRARY_RECORD_TIME: 2026-10-29 08:40
- STORIES: https://github.com/code-corhuila/barber-saas-docs/issues/163, https://github.com/code-corhuila/barber-saas-docs/issues/164, https://github.com/code-corhuila/barber-saas-docs/issues/165
<!-- CONFIG-END -->

## Boundary

Requests that already passed the edge reach `barbershop-api` (`/api/v1/barbershops`,
`/api/v1/barbers`, `/api/v1/services`) and `schedule-api` (`/api/v1/availability`,
`/api/v1/schedule-exceptions`) through the `api-gateway`. I own both services, so this is the
boundary I model.
