# Products API - Corte 2 (Spring Boot REST)

**Nombre:** Alex David Salgado Trujillo  

API REST con Spring Boot y arquitectura en capas (entity, repository, service, controller)
para el recurso **Product**, con persistencia JPA (H2 en memoria).

## Requisitos
- Java 17 o superior
- Maven 3.8 o superior

## Cómo ejecutar
```bash
mvn spring-boot:run
```
La API queda en `http://localhost:8080`.

- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs
- Consola H2: http://localhost:8080/h2-console (JDBC URL: `jdbc:h2:mem:productsdb`, user `sa`, sin password)

> La base de datos es en memoria: los datos se borran al detener la aplicación.

## Endpoints

| Método | Ruta | Descripción | Respuestas |
|--------|------|-------------|------------|
| GET | `/api/products` | Lista todos los productos | 200 |
| GET | `/api/products/{id}` | Obtiene un producto por id | 200, 404 |
| POST | `/api/products` | Crea un producto | 201, 400 |
| PUT | `/api/products/{id}` | Actualiza un producto | 200, 400, 404 |
| DELETE | `/api/products/{id}` | Elimina un producto | 204, 404 |

## Estructura
```
src/main/java/com/example/productapi/
  entity/       -> Product (JPA + validaciones)
  repository/   -> ProductRepository (JpaRepository)
  service/      -> ProductService (lógica de negocio)
  controller/   -> ProductController (endpoints REST)
  exception/    -> manejo global de errores (400 / 404)
postman/        -> colección de Postman lista para importar
evidencias/     -> capturas de Swagger y Postman
```

## Probar con Postman
Importa `postman/Products-API.postman_collection.json`. Incluye los 5 endpoints
y dos casos de error (404 al pedir un id inexistente y 400 al enviar datos inválidos).

## Evidencias
Las capturas de las pruebas en Swagger y Postman están en [`evidencias/EVIDENCIAS.md`](evidencias/EVIDENCIAS.md).

## API reference

- The API exposes a single resource, `Product`, under the base path `/api/products`, and all requests and responses use JSON.
- `GET /api/products` returns the full list of products stored in the database.
- `GET /api/products/{id}` returns one product by its id, or responds with `404 Not Found` if it does not exist.
- `POST /api/products` creates a new product from the request body and responds with `201 Created`, or `400 Bad Request` if the data is invalid (for example, a blank name or a negative price).
- `PUT /api/products/{id}` replaces the fields of an existing product and returns the updated object, or `404 Not Found` if the id does not exist.
- `DELETE /api/products/{id}` removes the product and responds with `204 No Content`, or `404 Not Found` if it does not exist.

### Example body (POST / PUT)
```json
{
  "name": "Laptop",
  "description": "14 inch, 16GB RAM",
  "price": 2500000,
  "stock": 10
}
```

### Error format
```json
{
  "timestamp": "2026-10-01T10:00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Producto con id 99 no encontrado"
}
```
