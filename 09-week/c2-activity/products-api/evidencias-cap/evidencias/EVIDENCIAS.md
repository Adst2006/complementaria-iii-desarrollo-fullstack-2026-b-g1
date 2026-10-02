# Evidencias - Corte 2: API REST con Spring Boot


Recurso: `Product` · Base URL: `http://localhost:8080/api/products` · Documentación: Swagger UI (`/swagger-ui.html`)

## 1. Aplicación en ejecución
La consola muestra `Tomcat started on port 8080` y `Started ProductApiApplication`, además de la creación de la tabla `products` (JPA/H2).

![App corriendo](img/01-app-corriendo.png)

## 2. Documentación con Swagger
Swagger UI con los 5 endpoints del CRUD.

![Swagger](img/02-swagger-endpoints.png)

## 3. POST /api/products - Crear (cuerpo de la petición)
![POST cuerpo](img/03-post-cuerpo.png)

## 4. POST /api/products - Respuesta 201 Created
El producto se guarda y se devuelve con `id: 1` generado por la base de datos.

![POST 201](img/04-post-201.png)

## 5. GET /api/products - Listar (200 OK)
![GET lista](img/05-get-lista-200.png)

## 6. GET /api/products/1 - Obtener uno (200 OK)
![GET por id](img/06-get-por-id-200.png)

## 7. PUT /api/products/1 - Actualizar (cuerpo de la petición)
Se cambia el precio de 2500000 a 2300000.

![PUT cuerpo](img/07-put-cuerpo.png)

## 8. PUT /api/products/1 - Respuesta 200 OK
![PUT 200](img/08-put-200.png)

## 9. Caso de error 404 - GET /api/products/999
El id no existe y la API responde 404 con un mensaje claro.

![GET 404](img/09-get-404-id-999.png)

## 10. Caso de error 400 - POST con datos inválidos (petición)
Nombre vacío y precio negativo.

![POST inválido](img/10-post-invalido-cuerpo.png)

## 11. Caso de error 400 - Respuesta 400 Bad Request
La validación devuelve los mensajes de cada campo inválido.

![POST 400](img/11-post-400.png)

## 12. DELETE /api/products/1 - Respuesta 204 No Content
![DELETE 204](img/12-delete-204.png)

## 13. Verificación del borrado - GET /api/products/1 (404)
Tras eliminarlo, el producto ya no existe.

![GET 404 tras borrar](img/13-get-404-tras-borrar.png)

---

# Pruebas con Postman
Colección importada desde `postman/Products-API.postman_collection.json` (variable `baseUrl = http://localhost:8080`).

## 14. POST crear producto - 201 Created
![Postman POST](img/14-postman-post-201.png)

## 15. GET lista - 200 OK
![Postman GET lista](img/15-postman-get-lista-200.png)

## 16. PUT actualizar - 200 OK
El precio pasa de 2500000 a 2300000.

![Postman PUT](img/16-postman-put-200.png)

## 17. Caso de error 404 - GET /api/products/999
![Postman 404](img/17-postman-error-404.png)

## 18. Caso de error 400 - POST con datos inválidos
![Postman 400](img/18-postman-error-400.png)

## 19. DELETE - 204 No Content
La respuesta no tiene cuerpo, como corresponde a un 204.

![Postman DELETE](img/19-postman-delete-204.png)

## 20. Verificación del borrado - GET /api/products/1 (404)
![Postman 404 tras borrar](img/20-postman-get-404-tras-borrar.png)
