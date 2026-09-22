# CRUD REST · Estudiante — Semana 8 · Desarrollo Fullstack

Implementación de un CRUD REST completo (entity, repository, service, controller)
para el recurso **Estudiante**, usando Spring Boot + Spring Data JPA + H2.

## Estructura

```
src/main/java/com/example/crud/
├── CrudApplication.java
├── model/Estudiante.java              (entity)
├── repository/EstudianteRepository.java
├── service/EstudianteService.java
├── service/ResourceNotFoundException.java
└── controller/EstudianteController.java
└── controller/GlobalExceptionHandler.java
src/main/resources/application.properties
```

## Cómo ejecutar

Requisitos: Java 17+ y Maven (o usa el `mvnw` si lo agregas con `mvn -N wrapper:wrapper`).

```bash
mvn spring-boot:run
```

La API queda disponible en `http://localhost:8080/api/estudiantes`.
Consola de la base H2 (opcional, para ver los datos guardados): `http://localhost:8080/h2-console`
(JDBC URL: `jdbc:h2:mem:estudiantesdb`, usuario `sa`, sin contraseña).

## Endpoints (verbo HTTP correcto por acción, URL con sustantivo en plural)

| Acción      | Método | URL                         |
|-------------|--------|------------------------------|
| Crear       | POST   | `/api/estudiantes`           |
| Listar      | GET    | `/api/estudiantes`           |
| Obtener uno | GET    | `/api/estudiantes/{id}`      |
| Actualizar  | PUT    | `/api/estudiantes/{id}`      |
| Borrar      | DELETE | `/api/estudiantes/{id}`      |

## Pruebas de extremo a extremo (con curl)

### 1. Crear un estudiante
```bash
curl -X POST http://localhost:8080/api/estudiantes \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Laura","apellido":"Gómez","correo":"laura.gomez@correo.com","programa":"Ingeniería de Sistemas"}'
```

### 2. Listar todos los estudiantes
```bash
curl http://localhost:8080/api/estudiantes
```

### 3. Obtener un estudiante por id
```bash
curl http://localhost:8080/api/estudiantes/1
```

### 4. Actualizar un estudiante
```bash
curl -X PUT http://localhost:8080/api/estudiantes/1 \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Laura","apellido":"Gómez Ríos","correo":"laura.gomez@correo.com","programa":"Ingeniería de Sistemas"}'
```

### 5. Borrar un estudiante
```bash
curl -X DELETE http://localhost:8080/api/estudiantes/1
```


