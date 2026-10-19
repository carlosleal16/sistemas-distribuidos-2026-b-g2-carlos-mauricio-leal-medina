# CP7 - Alert policy for the booking SLO

Rules in [`06-alerts.yml`](06-alerts.yml). They protect **SLO 1** of [`05-slo.md`](05-slo.md):
99.9 % of `POST /api/v1/appointments` without `outcome="error"` over 30 days, with a budget of
**31.5 failed bookings** or **43.2 minutes** per month.

## How the two alerts work

| Alert | Burn rate | Long window | Short window (confirmation) | `for` | Severity | Who gets it |
|---|---|---|---|---|---|---|
| `BookingAvailabilityFastBurn` | 14.4 (error ratio > 1.44 %) | 1 h | 5 min | 2 min | `page` | on-call developer of the week, by phone |
| `BookingAvailabilitySlowBurn` | 6 (error ratio > 0.6 %) | 6 h | 30 min | 15 min | `ticket` | owner of `appointment-api`, as an issue in `barber-saas-docs` |

The long window proves that the budget is really being spent; the short window proves it is
still happening, so the alert clears soon after the fix instead of staying on for an hour.

## Budget spent before each alert fires

A burn rate of 1 spends the whole budget in exactly 30 days (720 h).

| | Fast burn (14.4 for 1 h) | Slow burn (6 for 6 h) |
|---|---|---|
| Share of the monthly budget | 14.4 x 1 / 720 = **2 %** | 6 x 6 / 720 = **5 %** |
| Minutes of budget | 0.02 x 43.2 = **0.86 min** | 0.05 x 43.2 = **2.16 min** |
| Failed bookings, average traffic (31,500 / 720 h = 43.75 per hour) | 0.0144 x 43.75 = **0.63** | 0.006 x 43.75 x 6 = **1.58** |
| Failed bookings, opening hours (87.5 per hour, 05-slo.md) | 0.0144 x 87.5 = **1.26** (4 % of the budget) | 0.006 x 87.5 x 6 = **3.15** (10 % of the budget) |

Our traffic is concentrated in 12 opening hours, so a burn during the day spends about twice
the share that the 30-day formula suggests.

**Full outage example.** If appointment-api answers `503` to every booking (for example because
schedule-api is down, `communication-patterns.md`), the 1 h ratio passes 1.44 % after
0.0144 x 60 = 0.9 min, and with `for: 2m` the page arrives about **3 minutes** after the start.
By then 3 x 1.46 = about **4.4 bookings** failed, **14 %** of the monthly budget. Without the
fast alert, the 21.6 minutes of opening hours that empty the budget (05-slo.md) would be gone
before anyone looked.

**Known weakness: low traffic.** With only about 7 bookings every 5 minutes, one failed booking
already makes the 5 min ratio 14 %. Two failures in one hour can page the on-call developer.
If that happens too often, the next change is a minimum-volume condition
(`sum(increase(appointment_booking_requests_total{outcome="error"}[1h])) >= 3`) added to the fast rule.

## Cause-based alerts we will NOT create

| Alert we do not create | Why not |
|---|---|
| CPU of the `appointment-api` container above 80 % | High CPU with bookings answering in time does not hurt any client, and a slow booking with low CPU (waiting on schedule-api) would never fire it. If CPU ever causes failures, the burn-rate alert already fires, and CPU stays on the Grafana dashboard for diagnosis |
| `hikaricp_connections_pending > 0` in `barbershop-api` or `schedule-api` | A queue of one or two connections for a few milliseconds is normal at the opening hour and would page people for nothing. It is a saturation signal (CP4) to look at during an incident, listed in the runbook, not a reason to wake someone |

Both stay as dashboard panels: they help find the cause after the SLO alert says that clients
are affected.
