# CP6 - SLIs, SLOs and error budget

Three SLOs for the "book an appointment" journey (CP1), measured with the CP4 metrics. All
windows are **30 days rolling**.

## Traffic estimate

| Input | Value | Source |
|---|---|---|
| Paid barbershops | 50 | KR1 in `03-product/vision.md` (12-month target) |
| Barbers per barbershop | 3 (shops have 2 to 4) | `03-product/problem-framing.md` |
| Appointments per barber per day | 10 (a cut takes 30 to 45 min in a 10-hour day, with gaps) | my estimate |
| Share booked in the app | 70 % (the rest are walk-ins, `DEC-APPT-04`) | success criterion in `problem-framing.md` |
| Opening hours | 12 h per day (08:00 to 20:00), 30 days a month | my estimate |

- Bookings: 50 x 3 x 10 x 0.70 = **1,050 `POST /api/v1/appointments` per day**, so
  **31,500 per month**. During opening hours that is 1,050 / 720 min = **1.46 bookings per minute**.
- Availability reads: a client checks about 4 dates or barbers before booking, so
  4 x 1,050 = **4,200 `GET /api/v1/availability` per day**, **126,000 per month**.
- Events: every booking writes one `AppointmentCreated` in `outbox_event`, so
  **31,500 events per month** relayed by the worker to loyalty-api.

## SLO 1 - Booking availability

| | |
|---|---|
| SLI | good bookings / valid bookings. Good = `outcome` is `success` or `rejected` (a `422` for an overlapping slot is a correct answer). Valid = every booking that reached appointment-api |
| Target | **99.9 %** over 30 days, the same as NFR-010 in `04-requirements/non-functional.md` |

```promql
sum(rate(appointment_booking_requests_total{outcome!="error"}[30d]))
/
sum(rate(appointment_booking_requests_total[30d]))
```

Error budget: 0.1 %.

- In requests: 0.001 x 31,500 = **31.5 failed bookings per month**.
- In minutes of full outage over the whole window: 0.001 x 43,200 = **43.2 minutes**.
- Because bookings only happen in opening hours, 31.5 failed bookings / 1.46 per minute =
  **21.6 minutes** of full outage during opening hours spends the whole budget.

## SLO 2 - Availability latency

| | |
|---|---|
| SLI | `GET /api/v1/availability` requests answered in 1 s or less / all of them |
| Target | **99 %** over 30 days |
| Why 1 s | CP4: a client on the mobile app expects the free slots in less than 1 s after choosing the date. `1.0` is a bucket of `schedule_availability_request_duration_seconds` |

```promql
sum(rate(schedule_availability_request_duration_seconds_bucket{le="1.0"}[30d]))
/
sum(rate(schedule_availability_request_duration_seconds_count[30d]))
```

Error budget: 1 %.

- In requests: 0.01 x 126,000 = **1,260 slow availability answers per month**.
- In minutes: 0.01 x 43,200 = **432 minutes** of every request being slower than 1 s.

NFR-001 (p95 < 200 ms) stays as a performance target checked with K6 before a release; it is
not the user-facing SLO because the client does not notice the difference between 200 ms and 800 ms.

## SLO 3 - Event freshness (`AppointmentCreated` reaches loyalty-api)

| | |
|---|---|
| SLI | `AppointmentCreated` events published to loyalty-api within 60 s of `occurred_at` / all `AppointmentCreated` events |
| Target | **99 %** over 30 days |
| Why it matters | When a coupon is applied at booking (`DEC-APPT-09`), it only becomes `USED` in loyalty-api when the event arrives (`DEC-LOY-06`). A late event shows the client a coupon that was already spent |

```promql
sum(rate(worker_outbox_relay_lag_seconds_bucket{producer="appointment-api",event_type="AppointmentCreated",le="60"}[30d]))
/
sum(rate(worker_outbox_relay_lag_seconds_count{producer="appointment-api",event_type="AppointmentCreated"}[30d]))
```

Buckets of `worker_outbox_relay_lag_seconds` (the CP4 metric, buckets defined here because the
worker polls every few seconds, not in milliseconds): `5, 15, 30, 60, 120, 300`.

Error budget: 1 %.

- In events: 0.01 x 31,500 = **315 late events per month**.
- In minutes: 0.01 x 43,200 = **432 minutes** of the worker stopped or lagging more than 60 s.

## Error budget policy (team Barberssas)

Measured on SLO 1, the one the client feels most. The same steps apply to SLO 2 and SLO 3 for
the owners of `schedule-api` and `worker`.

| Budget consumed | What the team does |
|---|---|
| **50 %** (about 16 failed bookings) | Warning in the team channel. The owner of `appointment-api` reviews the failed traces by `trace_id` (CP2) and opens a `fix/` issue in `barber-saas-docs`. Normal work continues |
| **75 %** (about 24 failed bookings) | No new features are merged to `develop` in the services of the journey (`appointment-api`, `schedule-api`, `barbershop-api`, `loyalty-api`, `worker`); only fixes and tests. The promotion to `qa` waits |
| **100 %** (31.5 failed bookings) | Feature freeze for the journey until the 30-day SLI is back over 99.9 %. Only `hotfix/` branches go to `main`, with the teacher's approval (`ariel5253`). A blameless postmortem (CP9 format) is mandatory before the next release |
