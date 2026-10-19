# CP3 - How to run the schedule-api instrumentation module

Minimal Spring Boot module for `schedule-api`, in the team stack (Java 21, Spring Boot 3.5.6,
Maven). It covers hop 5 of the CP1 journey: `GET /api/v1/availability` in schedule-api calls
`GET /api/v1/barbers/{id}` in barbershop-api.

| Class | What it does |
|---|---|
| `TraceContext` | Parses, creates and formats the W3C `traceparent` |
| `TraceContextFilter` | Reads or creates `traceparent` and `X-Correlation-Id`, opens the server span, returns both headers, logs `http.request.completed` (level by status) |
| `StructuredLogger` | Writes one JSON line in the CP2 format, with `trace_id` and `span_id` injected |
| `TraceparentInterceptor` | Copies the trace to every outbound `RestClient` call as a child span |
| `BarbershopClient` | The outbound call to barbershop-api |
| `AvailabilityController` | Counter `schedule_availability_requests_total{outcome}` and histogram `schedule_availability_request_duration_seconds` |
| `TracePropagationTest` | Proves that the `trace_id` received equals the `trace_id` sent to barbershop-api |

## Requirements

- JDK 21 and Maven 3.9 (`java -version`, `mvn -v`)

## Commands

```bash
cd 11-week/workshop/03-instrumentation/schedule-api
mvn test
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=18083 --barbershop.base-url=http://localhost:1"
curl -i -H "traceparent: 00-7d3f0c9a1e5b4f2a8c6d0e1f2a3b4c5d-2b3c4d5e6f708192-01" "http://localhost:18083/api/v1/availability?barberId=3fa85f64&serviceId=7c9e6679&date=2026-10-20"
curl http://localhost:18083/actuator/prometheus
```

On Windows, enable long paths in the clone first (`git config core.longpaths true`): the
package path of this module is longer than 260 characters inside the OneDrive folder.

## Real output on my machine

`mvn test` (a fake barbershop-api runs inside the test on a random port):

```text
{"timestamp":"2026-10-19T14:36:41.130913300Z","level":"INFO","service":"schedule-api","version":"2.0.0","environment":"dev","trace_id":"7d3f0c9a1e5b4f2a8c6d0e1f2a3b4c5d","span_id":"3cb76f7ee6ee4427","correlation_id":"96274787-5f26-4bda-bf67-c4c35cdcc89f","event":"downstream.request.completed","message":"barbershop-api GET /api/v1/barbers/{id}","downstream":"barbershop-api","duration_ms":69,"barber_id":"3fa85f64"}
{"timestamp":"2026-10-19T14:36:41.157747200Z","level":"INFO","service":"schedule-api","version":"2.0.0","environment":"dev","trace_id":"7d3f0c9a1e5b4f2a8c6d0e1f2a3b4c5d","span_id":"3cb76f7ee6ee4427","correlation_id":"96274787-5f26-4bda-bf67-c4c35cdcc89f","event":"availability.computed","message":"free slots computed","service_id":"7c9e6679","slots":2,"barber_id":"3fa85f64"}
{"timestamp":"2026-10-19T14:36:41.219038800Z","level":"INFO","service":"schedule-api","version":"2.0.0","environment":"dev","trace_id":"7d3f0c9a1e5b4f2a8c6d0e1f2a3b4c5d","span_id":"3cb76f7ee6ee4427","correlation_id":"96274787-5f26-4bda-bf67-c4c35cdcc89f","event":"http.request.completed","message":"GET /api/v1/availability","http_status":200,"duration_ms":231}
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 9.397 s -- in co.edu.corhuila.barbersaas.schedule.availability.TracePropagationTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

Running the module with barbershop-api down (`--barbershop.base-url=http://localhost:1`), the
request answers `500` and the two lines keep the `trace_id` sent by the caller:

```text
{"timestamp":"2026-10-19T14:39:20.784048600Z","level":"ERROR","service":"schedule-api","version":"2.0.0","environment":"dev","trace_id":"7d3f0c9a1e5b4f2a8c6d0e1f2a3b4c5d","span_id":"84507441c1552e4a","correlation_id":"02fdb5e9-640c-426f-8e95-1c64f447ca64","event":"downstream.request.failed","message":"barbershop-api GET /api/v1/barbers/{id} failed","downstream":"barbershop-api","barber_id":"3fa85f64","duration_ms":81}
{"timestamp":"2026-10-19T14:39:20.789563500Z","level":"ERROR","service":"schedule-api","version":"2.0.0","environment":"dev","trace_id":"7d3f0c9a1e5b4f2a8c6d0e1f2a3b4c5d","span_id":"84507441c1552e4a","correlation_id":"02fdb5e9-640c-426f-8e95-1c64f447ca64","event":"http.request.completed","message":"GET /api/v1/availability","duration_ms":97,"http_status":500}
```

`/actuator/prometheus` after that failed request:

```text
schedule_availability_request_duration_seconds_bucket{le="0.05"} 0
schedule_availability_request_duration_seconds_bucket{le="0.1"} 1
schedule_availability_request_duration_seconds_bucket{le="0.25"} 1
schedule_availability_request_duration_seconds_bucket{le="0.5"} 1
schedule_availability_request_duration_seconds_bucket{le="1.0"} 1
schedule_availability_request_duration_seconds_bucket{le="3.0"} 1
schedule_availability_request_duration_seconds_bucket{le="+Inf"} 1
schedule_availability_request_duration_seconds_count 1
schedule_availability_request_duration_seconds_sum 0.080406
schedule_availability_request_duration_seconds_max 0.080406
schedule_availability_requests_total{outcome="error"} 1.0
```
