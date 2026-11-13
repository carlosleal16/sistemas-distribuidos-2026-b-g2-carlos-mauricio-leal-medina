# Certification C3 — Carlos Mauricio Leal Medina

## Cover

| Field | Value |
|---|---|
| Full name | Carlos Mauricio Leal Medina |
| GitHub user | carlosleal16 |
| Group | G2 |
| Team | Barberssas (BarberSaaS) |
| Team -docs repository | https://github.com/code-corhuila/barber-saas-docs |
| Course | HCCDP-Solution Architectures Course (Huawei Talent, Huawei ICT Academy) |
| Course link | https://e.huawei.com/en/talent/outPage/#/sxz-course/home?courseId=I4bs1u--tD4kL8Fg3clrbgvqnhA |
| Start date | 2026-10-28 |
| Completion date | 2026-11-13 |

## Study log

| Date | Hours | Chapters or labs | Evidence file |
|---|---|---|---|
| 2026-10-28 | 1.5 | Pre-class Guidance, Chapter 1 | evidence/00-enrollment.png, evidence/01-chapter-01.png |
| 2026-10-28 | 1.25 | Chapter 2 | evidence/02-chapter-02.png |
| 2026-10-28 | 0.5 | Chapter 3 | evidence/03-chapter-03.png |
| 2026-10-28 | 0.2 | Chapter 4 | evidence/04-chapter-04.png |
| 2026-10-28 | 0.4 | Chapter 5 | evidence/05-chapter-05.png |
| 2026-10-28 | 0.65 | Chapter 6 | evidence/06-chapter-06.png |
| 2026-11-05 | 4.0 | Chapter 7 | evidence/07-chapter-07.png |
| 2026-11-07 | 2.85 | Chapter 8 | evidence/08-chapter-08.png |
| 2026-11-08 | 0.67 | Chapter 9 (first part) | evidence/09-chapter-09.png |
| 2026-11-09 | 6.0 | Chapter 9 (second part) | evidence/09-chapter-09.png |
| 2026-11-10 | 3.25 | Chapter 10 | evidence/10-chapter-10.png |
| 2026-11-10 | 2.57 | Chapter 11 | evidence/11-chapter-11.png |
| 2026-11-11 | 2.6 | Chapter 12 | evidence/12-chapter-12.png |
| 2026-11-11 | 3.03 | Chapter 13 | evidence/13-chapter-13.png |
| 2026-11-12 | 5.9 | Chapter 14 | evidence/14-chapter-14.png |
| 2026-11-13 | 1.48 | Final exam | evidence/19-final-exam.png |
| Total | 36.85 | | |

## Labs

The course page did not let me open the labs: the lab entries were not available from my Huawei Talent account, so I could not run them and I have no lab screenshots. I did not invent evidence for them.

| Evidence file | Lab name | What I built | What failed first and how I fixed it |
|---|---|---|---|
| Not available | Lab 1 | Not done: the lab was not available from my account. | The lab did not open from the course page. |
| Not available | Lab 2 | Not done: the lab was not available from my account. | The lab did not open from the course page. |
| Not available | Lab 3 | Not done: the lab was not available from my account. | The lab did not open from the course page. |
| Not available | Lab 4 | Not done: the lab was not available from my account. | The lab did not open from the course page. |

## Final exam

- Score: 88 / 100 (27 of 30 closed-ended questions correct, 63 min 34 s of 90 min), passed
- Date: 2026-11-13
- Certificate: certificate.pdf (Certificate of Completion ICT20261114000018, issue date 2026-11-14 in the platform's time zone)
- Evidence: evidence/19-final-exam.png

## Skills summary

I learned to design a cloud solution from the five principles of the course: reliability, security, performance efficiency, cost and operations.
For scalability, I now design stateless services so they can scale out horizontally behind a load balancer with Auto Scaling rules driven by a metric and a threshold, and I relieve the database with caching, read/write splitting and sharding.
For distributed architecture, I use message queues to decouple services, so one slow service does not block the whole request chain.
For disaster recovery, I start from the RTO and RPO of each data store and choose the pattern that meets them: backup and restore, active-standby or active-active across availability zones or regions.
For automated deployment, I use a CI/CD pipeline that builds container images once and promotes them through environments with blue-green or canary releases and a tested rollback.
I applied these ideas to BarberSaaS: its domain services, its schema-per-domain databases and its Docker Compose deployment are the starting point of the target architecture in my design note.
