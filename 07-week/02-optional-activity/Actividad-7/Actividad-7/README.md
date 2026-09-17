# Semana 07 · Entity y Repository (JPA)

## Caso propio: Sistema de Biblioteca

Se modela la gestión de libros de una biblioteca.

## 1. Entity

La clase `Libro` (`src/main/java/com/miapp/biblioteca/model/Libro.java`) se mapea a la tabla `libros` usando:

- `@Entity` → indica que la clase es una entidad JPA.
- `@Table(name = "libros")` → define el nombre de la tabla.
- `@Id` + `@GeneratedValue(strategy = GenerationType.IDENTITY)` → llave primaria autoincremental.
- `@Column(...)` → restricciones y nombres de columnas (`nullable`, `unique`, `length`).

Atributos: `id`, `titulo`, `autor`, `isbn`, `anioPublicacion`, `disponible`.

## 2. Repository

La interfaz `LibroRepository` (`src/main/java/com/miapp/biblioteca/repository/LibroRepository.java`) extiende `JpaRepository<Libro, Long>`, lo que provee automáticamente el CRUD básico (`save`, `findAll`, `findById`, `deleteById`, etc).

Además se define una consulta por método (query method):

```java
Optional<Libro> findByIsbn(String isbn);
```

Spring Data JPA genera la consulta SQL automáticamente a partir del nombre del método, sin necesidad de escribir la implementación.

## 3. Operaciones CRUD y para qué se usarían

| Operación | Método | Para qué |
|---|---|---|
| **Create** | `libroRepository.save(nuevoLibro)` | Registrar un libro nuevo cuando ingresa al catálogo de la biblioteca. |
| **Read** | `findAll()`, `findById(id)`, `findByIsbn(isbn)` | Consultar el catálogo completo, revisar un libro específico o verificarlo por ISBN antes de prestarlo. |
| **Update** | `save(libroExistente)` con el `id` ya asignado | Cambiar el estado `disponible` cuando el libro se presta o se devuelve, o corregir datos. |
| **Delete** | `deleteById(id)` | Dar de baja un libro perdido o dañado permanentemente. |

## 4. Cómo probar

Se incluye un `LibroController` REST en `src/main/java/com/miapp/biblioteca/controller/LibroController.java` con los endpoints:

- `POST /api/libros` → crear
- `GET /api/libros` → listar
- `GET /api/libros/{id}` → obtener uno
- `PUT /api/libros/{id}` → actualizar
- `DELETE /api/libros/{id}` → eliminar


