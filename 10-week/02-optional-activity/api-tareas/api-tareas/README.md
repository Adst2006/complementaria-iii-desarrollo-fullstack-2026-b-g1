# API de Tareas — Desarrollo Fullstack · Semana 10

API REST con CRUD en capas y persistencia, documentada con Swagger y probada con Postman.

## Tecnologías

- Java 17+
- Spring Boot 3.3.5 (Web, Data JPA, Validation)
- H2 (base de datos en archivo, los datos persisten al reiniciar)
- springdoc-openapi (Swagger UI)
- Maven

## Arquitectura en capas

```
src/main/java/com/fullstack/tareas
├── controller/   TareaController         → endpoints REST (HTTP)
├── service/      TareaService            → lógica de negocio
├── repository/   TareaRepository         → acceso a datos (Spring Data JPA)
├── model/        Tarea, Prioridad        → entidad + validaciones
├── exception/    GlobalExceptionHandler  → respuestas de error 400 / 404
└── config/       OpenApiConfig           → metadatos de Swagger
```

## Cómo ejecutarla

Requisitos: JDK 17 o superior y Maven (o un IDE como IntelliJ / VS Code).

```bash
git clone <URL-DE-TU-REPOSITORIO>
cd api-tareas
mvn spring-boot:run
```

La API queda disponible en `http://localhost:8080`.

Para generar el `.jar`:

```bash
mvn clean package
java -jar target/api-tareas-1.0.0.jar
```

## Documentación (Swagger)

- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs

## Persistencia

Base de datos H2 en archivo (`./data/tareasdb`). Consola web opcional en
http://localhost:8080/h2-console con:

| Campo    | Valor                                |
|----------|--------------------------------------|
| JDBC URL | `jdbc:h2:file:./data/tareasdb`       |
| User     | `sa`                                 |
| Password | (vacío)                              |

## Endpoints

Base: `/api/tareas`

| Método | Ruta                          | Descripción                                             | Códigos       |
|--------|-------------------------------|---------------------------------------------------------|---------------|
| GET    | `/api/tareas`                 | Listar tareas (opcional `?completada=true\|false`)      | 200           |
| GET    | `/api/tareas/{id}`            | Obtener una tarea por id                                | 200, 404      |
| POST   | `/api/tareas`                 | Crear una tarea                                         | 201, 400      |
| PUT    | `/api/tareas/{id}`            | Actualizar una tarea                                    | 200, 400, 404 |
| PATCH  | `/api/tareas/{id}/completar`  | Marcar una tarea como completada                        | 200, 404      |
| DELETE | `/api/tareas/{id}`            | Eliminar una tarea                                      | 204, 404      |

### Modelo `Tarea`

| Campo       | Tipo    | Reglas                                        |
|-------------|---------|-----------------------------------------------|
| id          | Long    | Generado automáticamente                      |
| titulo      | String  | Obligatorio, máx. 100                         |
| descripcion | String  | Opcional, máx. 255                            |
| prioridad   | Enum    | Obligatoria: `BAJA`, `MEDIA` o `ALTA`         |
| fechaLimite | Fecha   | Opcional, formato `AAAA-MM-DD`                |
| completada  | Boolean | Por defecto `false`                           |

### Ejemplo: crear una tarea

```bash
curl -X POST http://localhost:8080/api/tareas \
  -H "Content-Type: application/json" \
  -d '{"titulo":"Entregar actividad de la semana 10","descripcion":"Subir el repo con README","prioridad":"ALTA","fechaLimite":"2026-10-15"}'
```

Respuesta `201 Created`:

```json
{
  "id": 1,
  "titulo": "Entregar actividad de la semana 10",
  "descripcion": "Subir el repo con README",
  "prioridad": "ALTA",
  "fechaLimite": "2026-10-15",
  "completada": false
}
```

### Ejemplo: marcar como completada

```bash
curl -X PATCH http://localhost:8080/api/tareas/1/completar
```

### Ejemplo: listar solo las pendientes

```bash
curl "http://localhost:8080/api/tareas?completada=false"
```

### Ejemplo de error de validación (`400`)

```json
{
  "timestamp": "2026-10-08T14:30:00",
  "status": 400,
  "error": "Bad Request",
  "mensaje": "Datos inválidos",
  "errores": {
    "titulo": "El título es obligatorio",
    "prioridad": "La prioridad es obligatoria"
  }
}
```

Una prioridad fuera de `BAJA`, `MEDIA`, `ALTA`, una fecha mal escrita o un JSON mal formado también responden `400`.

### Ejemplo de recurso inexistente (`404`)

```json
{
  "timestamp": "2026-10-08T14:30:00",
  "status": 404,
  "error": "Not Found",
  "mensaje": "Tarea con id 99 no encontrada"
}
```




