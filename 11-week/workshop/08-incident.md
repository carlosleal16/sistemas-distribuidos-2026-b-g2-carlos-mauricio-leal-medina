# CP9 - Incident drill and blameless postmortem

**INC-W11-01 (drill).** Saturday 2026-10-24, 09:00 to 09:40 (Colombia, UTC-5). During those 40
minutes, `schedule-api` (the callee of hop 4 marked in CP1) answers `GET /api/v1/availability`
in about 3 s for **25 %** of the requests. appointment-api waits at most 3 s for it
(`communication-patterns.md`), so those bookings time out and answer `503`.

## 1. Trace of one slow booking

`trace_id = 9e4c2b7a0d1f4e6a8b3c5d7e9f0a1b2c`. Durations in milliseconds.

- `api-gateway POST /api/v1/appointments`: 3,071 ms, `503`
  - `appointment-api bookAppointment`: 3,049 ms, `503`
    - `appointment-api GET barbershop-api /api/v1/services/{id}`: 36 ms, `200`
    - `appointment-api GET schedule-api /api/v1/availability`: **3,001 ms, timeout**
      - `schedule-api getAvailability`: 3,412 ms (keeps running after the caller gave up)
        - `schedule-api HikariPool getConnection`: **3,180 ms** (pool of `DB_POOL_MAX=10` exhausted)
        - `schedule-api GET barbershop-api /api/v1/barbers/{id}`: 41 ms
        - `schedule-api GET appointment-api /internal/v1/busy-slots`: 55 ms
    - loyalty-api coupon lookup, `INSERT appointment` and `AppointmentCreated`: **never run**

## 2. Four log lines of that trace (CP2 format)

```json
{"timestamp":"2026-10-24T14:12:30.204Z","level":"INFO","service":"appointment-api","version":"2.0.0","environment":"main","trace_id":"9e4c2b7a0d1f4e6a8b3c5d7e9f0a1b2c","span_id":"b1c2d3e4f5a60718","correlation_id":"0c7d1e2f-3a4b-4c5d-8e6f-7a8b9c0d1e2f","event":"appointment.booking.started","message":"booking requested","barbershop_id":"0f8e2d1c-6b5a-4c3d-9e8f-7a6b5c4d3e2f","barber_id":"3fa85f64-5717-4562-b3fc-2c963f66afa6","service_id":"7c9e6679-7425-40de-944b-e07fc1f90ae7","slot_start":"2026-10-24T11:00"}
{"timestamp":"2026-10-24T14:12:33.247Z","level":"ERROR","service":"appointment-api","version":"2.0.0","environment":"main","trace_id":"9e4c2b7a0d1f4e6a8b3c5d7e9f0a1b2c","span_id":"c2d3e4f5a6071829","correlation_id":"0c7d1e2f-3a4b-4c5d-8e6f-7a8b9c0d1e2f","event":"downstream.request.timeout","message":"schedule-api did not answer GET /api/v1/availability","duration_ms":3001,"downstream":"schedule-api","barber_id":"3fa85f64-5717-4562-b3fc-2c963f66afa6","slot_start":"2026-10-24T11:00"}
{"timestamp":"2026-10-24T14:12:33.275Z","level":"ERROR","service":"api-gateway","version":"2.0.0","environment":"main","trace_id":"9e4c2b7a0d1f4e6a8b3c5d7e9f0a1b2c","span_id":"a0b1c2d3e4f50617","correlation_id":"0c7d1e2f-3a4b-4c5d-8e6f-7a8b9c0d1e2f","event":"http.request.completed","message":"POST /api/v1/appointments answered 503","duration_ms":3071,"http_status":503}
{"timestamp":"2026-10-24T14:12:33.659Z","level":"WARN","service":"schedule-api","version":"2.0.0","environment":"main","trace_id":"9e4c2b7a0d1f4e6a8b3c5d7e9f0a1b2c","span_id":"d3e4f5a607182930","correlation_id":"0c7d1e2f-3a4b-4c5d-8e6f-7a8b9c0d1e2f","event":"availability.computed","message":"free slots computed after waiting for a database connection","duration_ms":3412,"barber_id":"3fa85f64-5717-4562-b3fc-2c963f66afa6","service_id":"7c9e6679-7425-40de-944b-e07fc1f90ae7"}
```

## 3. Values the SLIs reach

Traffic of `05-slo.md`: 1.46 bookings and 5.83 availability reads per minute in opening hours.

| SLO | During the 40 min | Effect on the 30-day budget |
|---|---|---|
| SLO 1, booking availability (99.9 %) | 58 bookings, **14.6 failed**, SLI = **75 %** | 14.6 / 31.5 = **46 %** of the budget; equal to 10 min of full outage. The 30-day SLI is still 99.95 % |
| SLO 2, availability in 1 s or less (99 %) | 233 reads, **58 slower than 1 s**, SLI = **75 %** | 58 / 1,260 = **4.6 %** of the budget |
| SLO 3, `AppointmentCreated` in 60 s or less (99 %) | not affected: a failed booking writes no event | 0 % |

## 4. Which alert fires first

Error ratio during the incident = 25 %, far above both thresholds (1.44 % and 0.6 %).

- **`BookingAvailabilityFastBurn` fires first, at about minute 6 (09:06).** The 1 h ratio grows
  as 0.25 x t / 60 and passes 1.44 % at t = 3.5 min; the 5 min window is already over it; then
  `for: 2m`. By then about 2 bookings had failed.
- `BookingAvailabilitySlowBurn` would fire at about minute 24 (09:24): the 6 h ratio
  (0.25 x t / 360) passes 0.6 % at t = 8.6 min, plus `for: 15m`. It opens the ticket.

## 5. Runbook step that stops the bleeding

[`07-runbook.md`](07-runbook.md) section 2 finds the cause: `downstream="schedule-api"` has all
the errors, and the trace shows the 3,180 ms wait for a connection. Mitigation 1 (rollback) does
not apply: nothing was deployed since `v2.0.0`. **Mitigation 2, scale out
`schedule-api` to two replicas**, stops the bleeding: it doubles the connections (2 x 10, checked
against `hikaricp_connections_active` of PostgreSQL) and the error ratio drops to 0.

## 6. Blameless postmortem

### Timeline (2026-10-24, UTC-5)

| Time | Event |
|---|---|
| 08:55 | A barbershop shares its booking link on Instagram for a Saturday promotion; availability reads double |
| 09:00 | 25 % of `GET /api/v1/availability` take about 3 s; bookings start to answer `503` |
| 09:06 | `BookingAvailabilityFastBurn` pages the on-call developer (Carlos Leal) |
| 09:09 | Alert acknowledged; message in the team channel |
| 09:17 | Confirmed: errors only on `downstream="schedule-api"`; trace `9e4c2b7a...` shows the pool wait. Declared **P1** |
| 09:19 | Daniel Cerquera takes the Incident Commander role |
| 09:24 | `BookingAvailabilitySlowBurn` opens the ticket |
| 09:27 | `docker compose restart schedule-api`: no effect, the wait comes back in 2 minutes |
| 09:36 | `docker compose up -d --scale schedule-api=2`, approved by the IC |
| 09:40 | Error ratio back to 0; end of impact |
| 09:56 | Fast-burn alert resolved; recovery checks of the runbook pass; 46 % of the budget used |

### Impact

- 14.6 failed bookings (about 15 clients) in 40 minutes; equal to **10 minutes** of full outage.
- 46 % of the monthly budget of SLO 1 spent in one morning, close to the 50 % step of the policy.
- 58 slow availability answers (4.6 % of the SLO 2 budget).

### Root cause

`schedule-api` holds a database connection for the whole `getAvailability` request, including
its two HTTP calls to `barbershop-api` and to `appointment-api` (`/internal/v1/busy-slots`).
With `DB_POOL_MAX=10` and double the usual traffic, the pool ran out, and about one request in
four waited around 3 s for a connection. That was longer than the 3 s timeout of appointment-api.

### Contributing factors

- One replica of `schedule-api`, with a pool sized for the average and not for a peak.
- The timeout of appointment-api (3 s) is equal to the wait, so a slow answer is a failed booking.
- No circuit breaker: during the 40 minutes appointment-api kept sending every booking to a
  saturated `schedule-api`.
- `hikaricp_connections_pending` was only a dashboard panel (CP7) and nobody looked at it before
  the page.

### Action items (to open as issues in `barber-saas-docs`)

1. **As the owner of `schedule-api`, I want `getAvailability` to release the database connection
   before calling barbershop-api and appointment-api, so that a traffic peak does not exhaust the pool.**
   - Given `DB_POOL_MAX=10` and downstream answers of 200 ms, when a K6 test sends 40 concurrent
     availability requests for 5 minutes, then `max(hikaricp_connections_pending)` is 0 and
     the p95 is under 1 s.
2. **As the team, we want two replicas of `schedule-api` in `qa` and `main`, so that one slow
   instance does not fail a quarter of the bookings.**
   - Given two running replicas, when one is stopped with `docker compose stop`, then 20 bookings
     in a row answer `201` and `appointment:booking_error_ratio:rate5m` stays at 0.
3. **As a client, I want the app to tell me at once that booking is unavailable when
   schedule-api is saturated, so that I do not wait 3 s for a failure.**
   - Given a stub of schedule-api that answers after 3.5 s, when 5 bookings in a row time out,
     then the 6th booking answers `503` in under 100 ms (circuit breaker open) and returns to
     normal 30 s after the stub answers in 100 ms.
