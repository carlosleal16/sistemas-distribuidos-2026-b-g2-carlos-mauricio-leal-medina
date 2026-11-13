# ADR-C3-02: Run the stateless services on CCE across two availability zones

## Status

Proposed

## Context

Today every service runs as a Docker Compose container on one VM (ADR-020), started by a systemd unit at
boot and updated by hand over SSH with the commands in `deploy/azure/README.md`. A container that dies stays
down because most containers have no restart policy, a VM failure stops everything, and the VM cannot grow
when the booking traffic grows. Sections 2 (reliability, cost and operations), 3 (scalability) and 5
(automated deployment) of the design note all need the same thing: more than one instance per service in
more than one zone, instances that are added and removed by a metric, and a deployment that a pipeline can
run and undo. The services are already stateless (the session is in the JWT and the state is in the
databases), so they can run as replicated pods.

## Decision

The api-gateway, the eight domain services, the workflow and the worker run on a CCE cluster whose node
pool spans AZ1 and AZ2. Each service is a deployment with at least two replicas spread across the zones,
readiness and liveness checks on its `/health` endpoint, and resource requests and limits taken from the
current memory targets (512 MB for a Java service, 256 MB for a Python one). A horizontal pod autoscaler
scales appointment-api and api-gateway on CPU and schedule-api on requests per second (design note, section
3), and the node pool autoscaler adds or removes nodes when the pods do not fit. Images are built once in
CI, pushed to SWR with the commit SHA as tag, and promoted from qa to main by a CodeArts pipeline with a
manual approval for production. Only the gateway is exposed, through the ELB; `/internal/v1/*` stays inside
the cluster, as it does today. The current branch model, cherry-pick with `-x` and the release approval by
the teacher stay the same.

## Alternatives considered

- ECS virtual machines in an Auto Scaling group, one group per service, still running Docker Compose: rejected because eleven groups of VMs are slower to scale (minutes to boot a VM instead of seconds to start a pod), waste memory per VM, and leave rolling updates and rollbacks to scripts we would have to write.
- Keep one larger VM and scale vertically: rejected because it is still one machine in one zone, so it does not fix reliability, and a larger flavor is paid all the time even when there is no traffic.

## Consequences

- Positive: a failed pod restarts on its own and a failed zone leaves the other zone answering; capacity follows the traffic; a release is a rolling update with health checks and a rollback is one command to the previous image tag; the same image runs in qa and main.
- Negative: it contradicts the "no Kubernetes" line of `05-architecture/deployment.md`, so it needs a team ADR in `barber-saas-docs` before anyone builds it; the team has to learn Kubernetes manifests, and the compose files in every repository would have a second deployment format to keep in sync; a managed cluster plus at least two nodes costs more than the student VM, and the credit we use today would not cover it.
