# Guía de Actividad Práctica
## Desarrollo Fullstack · Semana 6 · Arquitectura en capas de una API

**Nombre completo:** _(escribe aquí tu nombre)_
**Usuario de GitHub:** _(escribe aquí tu usuario)_

---

## 1. Caso elegido

**API de gestión de tareas (Task Manager)**

Una API donde los usuarios pueden crear, consultar, actualizar y eliminar tareas (`tasks`). Cada tarea tiene un título, un estado de completado y una fecha de creación.

---

## 2. Diagrama de capas

```
Cliente (Postman / frontend)
        |
        v
   POST /tasks
        |
        v
 ┌─────────────────────────────┐
 │        CONTROLLER           │
 │ Recibe la petición HTTP y   │
 │ valida el formato           │
 └──────────────┬──────────────┘
                | llama a
                v
 ┌─────────────────────────────┐
 │          SERVICE            │
 │ Aplica reglas de negocio y  │
 │ validaciones                │
 └──────────────┬──────────────┘
                | llama a
                v
 ┌─────────────────────────────┐
 │        REPOSITORY           │
 │ Ejecuta el acceso a datos   │
 │ (SQL / ORM)                 │
 └──────────────┬──────────────┘
                | persiste/lee
                v
 ┌─────────────────────────────┐
 │           ENTITY            │
 │ Representa la tabla Task    │
 │ en la base de datos         │
 └─────────────────────────────┘
```

**Regla de dependencia:** cada capa solo conoce la capa inmediatamente inferior. El Controller nunca accede directo a la base de datos.

---

## 3. Responsabilidad de cada capa

### Controller
Recibe la petición HTTP (`GET`, `POST`, etc.), extrae los datos del request (body, params) y valida que el formato sea correcto (por ejemplo, que venga el campo `title`). No contiene lógica de negocio: solo traduce HTTP en objetos de la aplicación y devuelve la respuesta con el código de estado correcto.

### Service
Aquí vive la lógica de negocio real: por ejemplo, "una tarea no puede crearse sin título", "solo el dueño puede marcarla como completada", o "al completar una tarea se debe registrar la fecha". El Service orquesta llamadas al Repository y puede combinar varias operaciones.

### Repository
Es la capa de acceso a datos. Se encarga de las consultas (SQL directo o mediante un ORM como Sequelize, TypeORM o SQLAlchemy): `INSERT`, `SELECT`, `UPDATE`, `DELETE`. No sabe nada de HTTP ni de reglas de negocio, solo sabe hablar con la base de datos.

### Entity
Es la representación de la tabla en la base de datos (por ejemplo, `Task` con campos `id`, `title`, `completed`, `created_at`). Define la estructura de los datos que se guardan y se leen.

---

## 4. Endpoint de ejemplo y capas que atraviesa

**Endpoint:** `POST /tasks`
**Body de ejemplo:**
```json
{
  "title": "Comprar café"
}
```

**Flujo de la petición:**

1. **Controller** recibe el `POST`, valida que `title` no esté vacío.
2. **Service** aplica la regla de negocio (por ejemplo, el título no puede tener más de 100 caracteres) y le asigna `completed: false` por defecto.
3. **Repository** ejecuta el `INSERT INTO tasks (...)`.
4. **Entity** define cómo se ve una fila `Task` en la base de datos.
5. La respuesta regresa por el mismo camino: Repository → Service → Controller → Cliente (con código `201 Created`).

---

## 5. Conclusión

Separar la API en estas cuatro capas permite que cada parte del sistema tenga una única responsabilidad, facilita las pruebas unitarias (se puede probar el Service sin necesidad de una base de datos real) y hace que el código sea más fácil de mantener y escalar a futuro.
