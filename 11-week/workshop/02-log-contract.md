# CP2 - Structured log contract

Every BarberSaaS service on the "book an appointment" journey (`api-gateway`, `appointment-api`,
`barbershop-api`, `schedule-api`, `loyalty-api`, `worker`) writes one JSON object per line to
stdout, with the fields below. It extends `13-operations/observability.md` (which only requires
`timestamp`, `level`, `service`, `correlationId`, `message`) and keeps its "what not to log" rules.

## Mandatory fields

| Field | Type | Req. | Example | Why it exists |
|---|---|---|---|---|
| `timestamp` | string, ISO-8601 UTC with ms | yes | `2026-10-19T14:30:02.117Z` | Order the events of several containers on one timeline |
| `level` | enum `ERROR`/`WARN`/`INFO`/`DEBUG` | yes | `INFO` | Filter by severity and alert on `ERROR` |
| `service` | string | yes | `appointment-api` | Know which container wrote the line |
| `version` | string, semver | yes | `2.0.0` | Tie a failure to a release (`v2.0.0` in `main`) |
| `environment` | enum `dev`/`qa`/`main` | yes | `qa` | Same names as `env/dev.env.example`, `qa`, `main` |
| `trace_id` | string, 32 hex | yes | `7d3f0c9a1e5b4f2a8c6d0e1f2a3b4c5d` | W3C trace of the whole journey (CP1) |
| `span_id` | string, 16 hex | yes | `a1b2c3d4e5f60718` | The hop inside the trace that wrote the line |
| `correlation_id` | string, UUID | yes | `5b1e7c2a-4d3f-4e8b-9a6c-2f1d0e9b8a7c` | Value of `X-Correlation-Id`; equals `traceId` in the `ErrorResponse` (`_shared.yaml`), so a client error can be found in the logs |
| `event` | string, `domain.action.result` | yes | `appointment.booking.rejected` | Stable key to count and search; `message` can change, `event` cannot |
| `message` | string | yes | `slot overlaps another appointment` | Short human text, no personal data |
| `duration_ms` | integer | when the line closes an operation | `3002` | Latency of the hop without a metrics backend |

## Domain fields (BarberSaaS)

| Field | Type | Req. | Example | Why it exists |
|---|---|---|---|---|
| `barbershop_id` | UUID | yes on tenant routes | `0f8e...` | Tenant from the JWT; shows if a failure hits one barbershop or all of them |
| `appointment_id` | UUID | when it exists | `c41a...` | Follows one `appointment.appointment` row from booking to `AppointmentCreated` |
| `barber_id` | UUID | booking and availability | `3fa8...` | `barber_profile` id; double booking (`INV-APPT-001`) is per barber |
| `service_id` | UUID | booking and availability | `7c9e...` | Service whose `durationMinutes` and `priceCents` were read from barbershop-api |
| `slot_start` | string `YYYY-MM-DDTHH:mm` (barbershop time zone) | booking and availability | `2026-10-20T14:30` | The slot that was asked for; needed to explain a `422` |
| `downstream` | string | outbound calls | `schedule-api` | Which dependency answered (or did not) |
| `http_status` | integer | request lines | `422` | Outcome of the request or of the outbound call |
| `outbox_event_id` | UUID | worker and producers | `9d2c...` | `outbox_event.id` = `EventEnvelope.id`; joins the async hop |

## Never log

| Data | Where it appears | How it is masked |
|---|---|---|
| Password and `password_hash` | `identity_auth.app_user` | Never written, not even masked |
| JWT, `Authorization` header, service tokens of `barber-saas-worker` / `barber-saas-schedule-api` | every request | Header removed from the request log; only `sub` and `role` are kept |
| `JWT_PUBLIC_KEY`, DB password of `<domain>_app` | env files | Never logged; config is printed with secrets as `***` |
| Client `email` | `app_user.email` | `c***@gmail.com` (first letter + domain) only in identity-auth; other services log `client_id` |
| Client `phone` | `app_user.phone` | Last 3 digits only: `*******123` |
| `full_name`, `profile_photo_url` | snapshots in barbershop-api | Not logged; the UUID is enough |
| Appointment `notes`, `cancelled_reason` | `appointment.appointment` | Not logged: free text written by the client may hold personal data |
| FCM device tokens | notifications `device-tokens` | First 6 characters + `...` |
| `price_at_booking_cents` with the client | `appointment.appointment` | Price may be logged, but never next to the client name or email |

## Seven lines of one booking (same `trace_id`)

The app sends the booking with an `Idempotency-Key`. The first try fails because schedule-api
does not answer within the 3 s request timeout (`communication-patterns.md`). The app shell
retries with the same `Idempotency-Key` and keeps the same `trace_id`, but in the meantime
another client took the slot, so the insert hits `ex_appointment_no_double_booking`
(`DEC-APPT-03`) and the answer is `422 BUSINESS_RULE_VIOLATION`.

```json
{"timestamp":"2026-10-19T14:30:00.104Z","level":"INFO","service":"api-gateway","version":"2.0.0","environment":"qa","trace_id":"7d3f0c9a1e5b4f2a8c6d0e1f2a3b4c5d","span_id":"1a2b3c4d5e6f7081","correlation_id":"5b1e7c2a-4d3f-4e8b-9a6c-2f1d0e9b8a7c","event":"http.request.received","message":"POST /api/v1/appointments","barbershop_id":"0f8e2d1c-6b5a-4c3d-9e8f-7a6b5c4d3e2f"}
{"timestamp":"2026-10-19T14:30:00.131Z","level":"INFO","service":"appointment-api","version":"2.0.0","environment":"qa","trace_id":"7d3f0c9a1e5b4f2a8c6d0e1f2a3b4c5d","span_id":"2b3c4d5e6f708192","correlation_id":"5b1e7c2a-4d3f-4e8b-9a6c-2f1d0e9b8a7c","event":"appointment.booking.started","message":"booking requested","barbershop_id":"0f8e2d1c-6b5a-4c3d-9e8f-7a6b5c4d3e2f","barber_id":"3fa85f64-5717-4562-b3fc-2c963f66afa6","service_id":"7c9e6679-7425-40de-944b-e07fc1f90ae7","slot_start":"2026-10-20T14:30"}
{"timestamp":"2026-10-19T14:30:03.214Z","level":"ERROR","service":"appointment-api","version":"2.0.0","environment":"qa","trace_id":"7d3f0c9a1e5b4f2a8c6d0e1f2a3b4c5d","span_id":"3c4d5e6f708192a3","correlation_id":"5b1e7c2a-4d3f-4e8b-9a6c-2f1d0e9b8a7c","event":"downstream.request.timeout","message":"schedule-api did not answer GET /api/v1/availability","duration_ms":3002,"downstream":"schedule-api","barber_id":"3fa85f64-5717-4562-b3fc-2c963f66afa6","slot_start":"2026-10-20T14:30"}
{"timestamp":"2026-10-19T14:30:03.229Z","level":"WARN","service":"api-gateway","version":"2.0.0","environment":"qa","trace_id":"7d3f0c9a1e5b4f2a8c6d0e1f2a3b4c5d","span_id":"1a2b3c4d5e6f7081","correlation_id":"5b1e7c2a-4d3f-4e8b-9a6c-2f1d0e9b8a7c","event":"http.request.completed","message":"POST /api/v1/appointments answered 503","duration_ms":3125,"http_status":503}
{"timestamp":"2026-10-19T14:30:05.410Z","level":"INFO","service":"appointment-api","version":"2.0.0","environment":"qa","trace_id":"7d3f0c9a1e5b4f2a8c6d0e1f2a3b4c5d","span_id":"4d5e6f708192a3b4","correlation_id":"5b1e7c2a-4d3f-4e8b-9a6c-2f1d0e9b8a7c","event":"appointment.booking.retried","message":"same Idempotency-Key, no appointment created yet","barber_id":"3fa85f64-5717-4562-b3fc-2c963f66afa6","slot_start":"2026-10-20T14:30"}
{"timestamp":"2026-10-19T14:30:05.587Z","level":"INFO","service":"schedule-api","version":"2.0.0","environment":"qa","trace_id":"7d3f0c9a1e5b4f2a8c6d0e1f2a3b4c5d","span_id":"5e6f708192a3b4c5","correlation_id":"5b1e7c2a-4d3f-4e8b-9a6c-2f1d0e9b8a7c","event":"availability.computed","message":"free slots computed","duration_ms":142,"barber_id":"3fa85f64-5717-4562-b3fc-2c963f66afa6","service_id":"7c9e6679-7425-40de-944b-e07fc1f90ae7","slot_start":"2026-10-20T14:30"}
{"timestamp":"2026-10-19T14:30:05.702Z","level":"WARN","service":"appointment-api","version":"2.0.0","environment":"qa","trace_id":"7d3f0c9a1e5b4f2a8c6d0e1f2a3b4c5d","span_id":"4d5e6f708192a3b4","correlation_id":"5b1e7c2a-4d3f-4e8b-9a6c-2f1d0e9b8a7c","event":"appointment.booking.rejected","message":"slot overlaps another appointment (ex_appointment_no_double_booking)","duration_ms":292,"http_status":422,"barber_id":"3fa85f64-5717-4562-b3fc-2c963f66afa6","slot_start":"2026-10-20T14:30"}
```

Filtering by `trace_id = 7d3f0c9a1e5b4f2a8c6d0e1f2a3b4c5d` tells the story: request, timeout to
schedule-api, `503` to the client, retry, slot computed, and the final double-booking rejection.

## Log levels

| Level | When the team uses it | BarberSaaS example |
|---|---|---|
| `ERROR` | Our side failed and the user got a `5xx`, or data could not be written | `downstream.request.timeout`: schedule-api did not answer in 3 s, booking answered `503` |
| `WARN` | Expected rejection or degraded path; the system worked as designed | `appointment.booking.rejected`: `422 BUSINESS_RULE_VIOLATION` for an overlapping slot |
| `INFO` | Normal business step, one line per step | `appointment.booking.started`, `outbox.event.published` for `AppointmentCreated` |
| `DEBUG` | Detail for development, off in `qa` and `main` | Slot list returned by `GET /api/v1/availability` before the busy slots are removed |
