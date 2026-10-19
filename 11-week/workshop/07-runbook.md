# CP8 - Runbook: `BookingAvailabilityFastBurn`

| | |
|---|---|
| Alert | `BookingAvailabilityFastBurn` ([`06-alerts.yml`](06-alerts.yml)), `severity: page`, `team: barberssas` |
| SLO | SLO 1, 99.9 % of `POST /api/v1/appointments` without error over 30 days ([`05-slo.md`](05-slo.md)) |
| Meaning | In the last hour bookings failed more than 14.4 times faster than the budget allows; 2 % of the monthly budget is already gone and the 5 min window says it is still happening |
| Severity | **P1** of `13-operations/incident-management.md` (core functionality degraded); **P0** if the ratio is 100 % |

## 1. Symptoms

- Clients see "could not book" in `appointment-app`; the API answers `503` or `500` with
  `ErrorResponse.traceId` (= `X-Correlation-Id`).
- Barbershop owners report on WhatsApp that clients cannot book.
- The availability screen may also be slow, because hop 4 (appointment-api to schedule-api,
  CP1) is the most common cause.

## 2. Confirm (first 5 minutes)

1. Acknowledge the alert and write in the team channel: `Investigating BookingAvailabilityFastBurn - <name>`.
2. Check that the error ratio is real and still high:

   ```promql
   appointment:booking_error_ratio:rate5m
   ```

3. Find which downstream fails:

   ```promql
   sum by (downstream) (rate(appointment_downstream_request_duration_seconds_count{outcome="error"}[5m]))
   ```

   ```promql
   histogram_quantile(0.95, sum by (le) (rate(schedule_availability_request_duration_seconds_bucket[5m])))
   ```

4. Take one `traceId` from a failed answer (or from a `downstream.request.timeout` line) and read
   the whole story of that booking in the CP2 logs:

   ```bash
   docker compose logs --no-log-prefix --since 30m api-gateway appointment-api schedule-api barbershop-api loyalty-api \
     | jq -c 'select(.trace_id == "7d3f0c9a1e5b4f2a8c6d0e1f2a3b4c5d")'
   ```

   ```bash
   docker compose logs --no-log-prefix --since 30m appointment-api \
     | jq -c 'select(.level == "ERROR") | {timestamp, event, downstream, duration_ms, trace_id}'
   ```

5. Check the containers: `docker compose ps` (health of `schedule-api`, `barbershop-api`,
   `loyalty-api`, `postgres`) and the last deploy (`git log -1` of the repo that was deployed).

If the ratio is back under 1.44 % and no `ERROR` lines appear in the last 5 minutes, it was a
short spike: write it in the channel and leave the alert to clear.

## 3. Mitigate (ordered from lowest to highest risk)

Before each step: say it in the channel, wait for the Incident Commander's OK, run it, report
the result within 2 minutes (`incident-management.md`, step 5).

| # | Mitigation | When | How | Risk |
|---|---|---|---|---|
| 1 | **Roll back** the service that was deployed last | The errors started after a deploy of `appointment-api`, `schedule-api` or `barbershop-api` | Redeploy the previous tag (for example `v2.0.0`): `git checkout v2.0.0` in that repo, then `docker compose up -d --build <service>` | Low: the previous version already passed QA. Only possible if the schema change of the `-db` is backward compatible |
| 2 | **Restart or scale out** the slow dependency | `schedule-api` or `barbershop-api` answers slowly but is not failing on its own data (high latency, Tomcat busy threads near max, memory near the 512 MB limit) | `docker compose restart schedule-api`; if that does not help, `docker compose up -d --scale schedule-api=2` (the service only uses `expose`, no fixed `container_name`, so two replicas can run) | Medium: each replica opens up to `DB_POOL_MAX=10` connections to the single PostgreSQL; check `hikaricp_connections_active` before a third replica |
| 3 | **Shed load at the gateway** | The cause is a traffic peak (for example a barbershop shared its booking link) and steps 1 and 2 are not enough | Lower `rate` of `limit_req_zone per_client` in `barber-saas-api-gateway/nginx/conf.d/10-security.conf` (today 10 r/s) and `nginx -s reload` | High: real clients get `429 TOO_MANY_REQUESTS`; it protects the bookings that do get in, but it is still a user-visible failure |

Not available in our architecture today, so not in this runbook: a feature flag (there is no
flag service), a circuit breaker (no Resilience4j in `appointment-api`, the call fails closed
after the 3 s timeout of `communication-patterns.md`) and failover to another region (a single
Docker Compose host).

## 4. Escalate

| Role (`incident-management.md`) | Who in team Barberssas | When |
|---|---|---|
| On-call engineer | The developer on call this week (weekly rotation of the three) | Gets the page; runs sections 2 and 3 |
| Incident Commander | Daniel Cerquera (`Pipecerquera`), owner of `infra` and `api-gateway` | After 15 minutes without recovery, or at once if P0 |
| Tech lead of the failing hop | `appointment-api`, `loyalty-api`: Juan Pablo Borrero (`JUANDAX233`). `schedule-api`, `barbershop-api`: Carlos Leal (`carlosleal16`). `worker`, `postgres`: Daniel Cerquera | As soon as section 2 points to their service |
| Comms lead | The Incident Commander, while the team has three people | Message to the barbershop owners if the outage lasts more than 15 minutes |
| Teacher (`ariel5253`) | Approves the `hotfix/` pull request to `main` | Only if the fix must reach `main` (golden rule 6) |

## 5. Verify recovery

The incident is resolved when all of these are true:

- `appointment:booking_error_ratio:rate5m` is under `0.0144` for 15 minutes and the
  `BookingAvailabilityFastBurn` alert is resolved.
- The p95 of `schedule_availability_request_duration_seconds` is under 1 s again (SLO 2).
- A test booking in QA (`POST /api/v1/appointments` with a new `Idempotency-Key`) answers `201`
  and its `trace_id` shows every span of CP1, including `AppointmentCreated` reaching
  `loyalty-api` in less than 60 s (SLO 3).
- `docker compose ps` shows every service of the journey `healthy`.
- The remaining budget is written in the channel, and if more than 50 % is gone the policy of
  `05-slo.md` is applied. A postmortem in the CP9 format follows every P0 or P1.
