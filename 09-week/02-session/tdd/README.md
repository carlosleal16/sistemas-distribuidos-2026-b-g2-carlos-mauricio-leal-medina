# TDD en BarberSaaS — material de la sesión 2

Exposición en clase (2026-10-01): qué es TDD (Test-Driven Development) y cómo se aplica en los
8 dominios de BarberSaaS.

![Infografía TDD en BarberSaaS](infografia-tdd.png)

## Archivos

| Archivo | Contenido |
|---|---|
| [`presentacion-tdd.html`](presentacion-tdd.html) | Las 12 diapositivas de la exposición, con las notas del presentador debajo de cada una. Se abre en cualquier navegador. |
| [`infografia-tdd.html`](infografia-tdd.html) | La infografía en versión web (se adapta a celular y a modo oscuro). |
| [`infografia-tdd.png`](infografia-tdd.png) | La misma infografía como imagen. |

## Resumen

1. **Qué es.** Primero se escribe la prueba y después el código que la hace pasar, en ciclos
   cortos: **rojo** (la prueba falla), **verde** (código mínimo que la pasa) y **refactor**
   (limpiar sin cambiar el comportamiento).
2. **Trazabilidad (P.1).** Cada criterio de aceptación de una HU de `04-requirements` se vuelve
   una prueba. La cadena es HU → prueba → código → PR que referencia `barber-saas-docs#NN` → CI.
3. **Por capas en cada `-api` hexagonal.** Unitarias puras en el dominio, unitarias con mocks en
   los casos de uso, pruebas de contrato contra el OpenAPI de `07-api` en el adaptador REST e
   integración contra el esquema del `-db` en la persistencia.
4. **En los 8 dominios.** identity-auth, barbershop, appointment, schedule, loyalty,
   notifications, finance-inventory y platform-admin. Cada uno convierte los criterios de sus HU
   en pruebas, en su `-api` y, donde exista, en su `-worker` o `-workflow`.
5. **Respeta la Norma 2026-B.** Regla 7: las pruebas de integración usan las migraciones del
   `-db`. Regla 8: lo de otro dominio se simula por su API, nunca se consulta su base.
6. **Git y CI.** El ciclo queda en el historial (`test:` → `feat:` → `refactor:`) y el `ci.yml`
   de cada repo bloquea el merge si alguna prueba falla.

> Los nombres de prueba, el código Java/JUnit y el historial de commits que aparecen en el
> material son ejemplos ilustrativos: los 29 repos de `code-corhuila` todavía no tienen código
> de aplicación.
