# CP4 - Golden signals and metric catalog

One table per service of the "book an appointment" journey (CP1). Names follow Prometheus
conventions: `snake_case`, a domain prefix, the unit as suffix (`_seconds`) and `_total` for
counters. In the Java services the metric is registered with Micrometer and exported on
`/actuator/prometheus` (as in CP3); `notifications-api` and `worker` would use
`prometheus_client`. Today `barber-saas-infra/observability/prometheus.yml` only scrapes
`otel-collector:8889`, so each service must be added there (or pushed through the collector).

Shared labels: `route` is the templated path of the contract, never the raw URL; `outcome` is
`success`, `rejected` (4xx business answer) or `error` (5xx or timeout).

## api-gateway (NGINX, metrics from the exporter)

| Signal | Metric | Type | Labels | PromQL |
|---|---|---|---|---|
| Latency | `gateway_request_duration_seconds` | histogram | `route`, `method` | `histogram_quantile(0.95, sum by (le, route) (rate(gateway_request_duration_seconds_bucket{route="/api/v1/appointments"}[5m])))` |
| Traffic | `gateway_requests_total` | counter | `route`, `method`, `status_class` | `sum by (route) (rate(gateway_requests_total[5m]))` |
| Errors | `gateway_requests_total` | counter | `status_class="5xx"` | `sum(rate(gateway_requests_total{status_class="5xx"}[5m])) / sum(rate(gateway_requests_total[5m]))` |
| Saturation | `gateway_rate_limited_requests_total` | counter | `route` | `sum by (route) (rate(gateway_rate_limited_requests_total[5m]))` (429 `TOO_MANY_REQUESTS`) |

## appointment-api

| Signal | Metric | Type | Labels | PromQL |
|---|---|---|---|---|
| Latency | `appointment_booking_request_duration_seconds` | histogram | `outcome` | `histogram_quantile(0.95, sum by (le) (rate(appointment_booking_request_duration_seconds_bucket[5m])))` |
| Traffic | `appointment_booking_requests_total` | counter | `outcome` | `sum(rate(appointment_booking_requests_total[5m]))` |
| Errors | `appointment_booking_requests_total` | counter | `outcome="error"` | `sum(rate(appointment_booking_requests_total{outcome="error"}[5m])) / sum(rate(appointment_booking_requests_total[5m]))` |
| Errors (downstream) | `appointment_downstream_request_duration_seconds` | histogram | `downstream`, `outcome` | `sum by (downstream) (rate(appointment_downstream_request_duration_seconds_count{outcome="error"}[5m]))` |
| Saturation | `hikaricp_connections_pending` | gauge | `pool` | `max(hikaricp_connections_pending{application="appointment-api"})` |

`outcome="rejected"` holds the `422 BUSINESS_RULE_VIOLATION` of `ex_appointment_no_double_booking`:
it is counted as traffic, but it is **not** an error of our system (CP2 logs it as `WARN`).

## schedule-api (implemented in CP3)

| Signal | Metric | Type | Labels | PromQL |
|---|---|---|---|---|
| Latency | `schedule_availability_request_duration_seconds` | histogram | none | `histogram_quantile(0.95, sum by (le) (rate(schedule_availability_request_duration_seconds_bucket[5m])))` |
| Traffic | `schedule_availability_requests_total` | counter | `outcome` | `sum(rate(schedule_availability_requests_total[5m]))` |
| Errors | `schedule_availability_requests_total` | counter | `outcome="error"` | `sum(rate(schedule_availability_requests_total{outcome="error"}[5m])) / sum(rate(schedule_availability_requests_total[5m]))` |
| Saturation | `jvm_threads_live_threads` / Tomcat busy threads | gauge | none | `max(tomcat_threads_busy_threads{application="schedule-api"}) / max(tomcat_threads_config_max_threads{application="schedule-api"})` |

## barbershop-api

| Signal | Metric | Type | Labels | PromQL |
|---|---|---|---|---|
| Latency | `barbershop_catalog_request_duration_seconds` | histogram | `route` | `histogram_quantile(0.95, sum by (le, route) (rate(barbershop_catalog_request_duration_seconds_bucket[5m])))` |
| Traffic | `barbershop_catalog_requests_total` | counter | `route`, `outcome` | `sum by (route) (rate(barbershop_catalog_requests_total[5m]))` |
| Errors | `barbershop_catalog_requests_total` | counter | `outcome="error"` | `sum(rate(barbershop_catalog_requests_total{outcome="error"}[5m])) / sum(rate(barbershop_catalog_requests_total[5m]))` |
| Saturation | `hikaricp_connections_active` | gauge | `pool` | `max(hikaricp_connections_active{application="barbershop-api"}) / max(hikaricp_connections_max{application="barbershop-api"})` |

`route` here is only `/api/v1/services/{id}` and `/api/v1/barbers/{id}`, the two reads of the journey.

## loyalty-api

| Signal | Metric | Type | Labels | PromQL |
|---|---|---|---|---|
| Latency | `loyalty_coupon_lookup_duration_seconds` | histogram | none | `histogram_quantile(0.95, sum by (le) (rate(loyalty_coupon_lookup_duration_seconds_bucket[5m])))` |
| Traffic | `loyalty_events_received_total` | counter | `event_type`, `outcome` | `sum by (event_type) (rate(loyalty_events_received_total[5m]))` |
| Errors | `loyalty_events_received_total` | counter | `outcome="error"` | `sum(rate(loyalty_events_received_total{outcome="error"}[5m]))` |
| Saturation | `hikaricp_connections_pending` | gauge | `pool` | `max(hikaricp_connections_pending{application="loyalty-api"})` |

`outcome` of an event: `processed`, `duplicate` (same `sourceEventId`), `unknown_type` (422), `error`.

## worker (outbox relay)

| Signal | Metric | Type | Labels | PromQL |
|---|---|---|---|---|
| Latency | `worker_outbox_relay_lag_seconds` | histogram | `producer`, `event_type` | `histogram_quantile(0.95, sum by (le, producer) (rate(worker_outbox_relay_lag_seconds_bucket[15m])))` (from `occurred_at` to `published`) |
| Traffic | `worker_events_relayed_total` | counter | `producer`, `event_type`, `outcome` | `sum by (event_type) (rate(worker_events_relayed_total[5m]))` |
| Errors | `worker_events_relayed_total` | counter | `outcome="failed"` | `sum by (producer) (rate(worker_events_relayed_total{outcome="failed"}[15m]))` |
| Saturation | `worker_outbox_pending_events` | gauge | `producer` | `max by (producer) (worker_outbox_pending_events)` (rows of `idx_outbox_event_unpublished`) |

## Label cardinality

| Label | Values | Max series per metric |
|---|---|---|
| `route` | templated paths of the contract, 2 to 6 per service | 6 |
| `method` | `GET`, `POST`, `PATCH`, `DELETE` | 4 |
| `outcome` | 3 or 4 fixed values | 4 |
| `status_class` | `2xx`, `3xx`, `4xx`, `5xx` | 4 |
| `downstream` | `barbershop-api`, `schedule-api`, `loyalty-api` | 3 |
| `producer` | `identity-auth-api`, `appointment-api`, `loyalty-api` | 3 |
| `event_type` | events of `event-catalog.md` (about 10) | 10 |
| `pool` | one Hikari pool per service | 1 |

Worst case: `gateway_requests_total` = 6 routes x 4 methods x 4 classes = 96 series per gateway.

**Forbidden labels** (unbounded) and what replaces them:

| Forbidden | Why | Replacement |
|---|---|---|
| `barbershop_id` | grows with every tenant that signs up | `barbershop_id` field in the CP2 log, filtered by `trace_id` |
| `barber_id`, `client_id`, `appointment_id`, `service_id` | one series per row of the database | log fields; a trace exemplar on the histogram |
| raw URL (`/api/v1/barbers/3fa8...`) | the id inside the path explodes the series | `route` with the template `/api/v1/barbers/{id}` |
| `email`, `phone` | personal data and unbounded | never in metrics (CP2 "never log") |
| `slot_start`, `date` | one value per minute of the calendar | log field |

## Latency buckets

`0.05, 0.1, 0.25, 0.5, 1, 2, 3, 5` seconds.

- A client on the mobile app expects the free slots of a barber to appear in less than **1 s**
  after choosing the date, and the booking to be confirmed in less than **2 s**. The SLOs of CP6
  will be set on those two edges, so both are bucket limits.
- `3` is the request timeout between services (`communication-patterns.md`): a request in the
  `le="3"` to `le="5"` range is one that hit the timeout of schedule-api or barbershop-api.
- `0.05` to `0.25` separate the fast reads of barbershop-api (`/api/v1/services/{id}`) from the
  ones that already queue on the database pool.
- The CP3 module uses `0.05, 0.1, 0.25, 0.5, 1, 3`; adding `2` and `5` is the next change there.
