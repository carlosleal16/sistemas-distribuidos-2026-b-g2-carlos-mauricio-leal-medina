# ADR-C3-01: Managed multi-AZ databases with cross-region backups

## Status

Proposed

## Context

All the data of BarberSaaS lives in two containers on one VM: one PostgreSQL 16 instance with one schema
per domain and one MongoDB instance for notifications (ADR-011, ADR-020). The backup is a daily dump
made by `scripts/backup.sh`, kept on the same disk and copied off the machine by hand. Section 4 of the
design note sets the targets for the critical store, the `appointment` schema: RTO 30 minutes and RPO 5
minutes. Today a lost disk can mean losing up to a day of bookings, and a broken VM means no service until
someone rebuilds it by hand. Section 2 (reliability and performance efficiency) shows the same weakness
from two sides: there is no standby to fail over to and no read replica to absorb the availability queries.

## Decision

The PostgreSQL instance moves to RDS for PostgreSQL 16 in primary/standby mode, with the primary in AZ1 and
the standby in AZ2, semi-synchronous replication and automatic failover, plus one read replica for
availability and report queries. MongoDB moves to a three-node DDS replica set. Both keep their automated
full backups and incremental log backups every 5 minutes, with 7 days of retention, and the backups are
copied to an OBS bucket in a second region. Nothing changes inside the data model: each domain keeps its own
schema, its reader and writer roles (for example `schedule_reader`, `schedule_writer`) and its own application user (`schedule_app`), the `-db` repositories
keep running their Liquibase changelogs with their own control tables, and no service ever connects as the
administrator.

## Alternatives considered

- Keep the self-managed containers and add a second PostgreSQL container with streaming replication: rejected because the team would have to build and test the failover, the replication monitoring and the point-in-time recovery itself, which is exactly what a managed service already does and we do not have the operations capacity for it.
- One RDS instance per PostgreSQL domain (seven database instances): rejected because it multiplies the cost by seven for a platform with little traffic, and the schema-per-domain rule with separate users already gives the isolation the norm asks for (ADR-011, Annex J).

## Consequences

- Positive: an AZ failure loses no confirmed booking and the platform is back in minutes; a bad migration can be undone with a point-in-time restore; the read replica takes the availability queries off the primary; backups no longer depend on someone copying them by hand.
- Negative: managed instances cost more than a container on a VM we already pay for, and the standby and the replica are paid even when idle; the read replica is asynchronous, so a query sent there can miss a booking made a moment ago, which is why the booking itself and its no-double-booking check keep going to the primary; the team depends on Huawei Cloud for the database engine version and the maintenance windows.
