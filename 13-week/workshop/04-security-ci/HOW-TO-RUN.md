# CP4 - Security gates and negative authorization test

## What is here

| File | Purpose |
|---|---|
| `security.yml` | GitHub Actions workflow proposed for `barber-saas-schedule-api`: secret scan (gitleaks), dependency audit (OWASP dependency-check) and the authorization tests. It runs on every pull request to `develop`, `qa` and `main`; a red job blocks the merge. |
| `WeeklyScheduleAuthorizationHttpTest.java` | Negative test of `PUT /api/v1/barber-schedules/{barberId}` in `schedule-api`. |

This folder is a proposal. Nothing was changed in the team code repository today.

## Story and criterion it covers

- Story: [barber-saas-docs#163](https://github.com/code-corhuila/barber-saas-docs/issues/163),
  "An owner cannot change the services or schedules of another barbershop" (STRIDE E, risk 6, P1).
- Acceptance criterion 2: with an `ADMIN_BARBERSHOP` token of barbershop A,
  `PUT /api/v1/barber-schedules/{barberId}` with a barber of barbershop B returns `404`; with a
  `BARBER` or `CLIENT` token it returns `403`.
- Criterion 1 (`PUT /api/v1/services/{id}`) is already covered in `barbershop-api` by
  `CatalogHttpTest.another_barbershop_never_sees_or_edits_the_service`. `schedule-api` had no test
  of this kind, so this is the one I wrote.

| Test | Request | Expected |
|---|---|---|
| `the_owner_of_another_barbershop_gets_404_and_the_week_is_unchanged` | `PUT` with an `ADMIN_BARBERSHOP` token of another barbershop | `404`, and a later `GET` still shows the owner's week |
| `the_barber_and_a_client_of_the_same_barbershop_get_403` | `PUT` with a `BARBER` and with a `CLIENT` token of the same barbershop | `403` |
| `a_request_without_a_token_gets_401` | `PUT` without `Authorization` | `401` |

## How to run it locally

Requirements: Java 21 and Maven 3.9. No database is needed: with `DATABASE_URL` empty the
service uses its in-memory repositories, and `BarbershopApiStub` answers for `barbershop-api`.

```bash
git clone https://github.com/code-corhuila/barber-saas-schedule-api.git
cd barber-saas-schedule-api
cp <fork>/13-week/workshop/04-security-ci/WeeklyScheduleAuthorizationHttpTest.java \
   schedule-app/src/test/java/co/edu/corhuila/barbersaas/schedule/app/
mvn -B -pl schedule-app -am test -Dtest=WeeklyScheduleAuthorizationHttpTest \
    -Dsurefire.failIfNoSpecifiedTests=false
```

The test extends `HttpTest` of `schedule-app`, so it signs its tokens with the same throwaway RS256
key pair that the other HTTP tests use.

## Workflow notes

- gitleaks runs as its container (`zricethezav/gitleaks:v8.30.1`) because `gitleaks-action`
  needs a paid license on organization repositories.
- dependency-check needs the repository secret `NVD_API_KEY`; without it the NVD download takes
  very long.
