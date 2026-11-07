# Semana 14 · Contenerizar con Docker

**Programa:** Ingeniería de Sistemas · **Asignatura:** Desarrollo Fullstack
**Unidad 3:** Seguridad, contenedores y despliegue · **Corte 3**

## Descripción

Backend mínimo en **Java 17** (sin frameworks ni dependencias externas) empaquetado en un contenedor Docker.

Endpoints:

| Método | Ruta | Respuesta |
|---|---|---|
| GET | `/` | Mensaje de bienvenida en JSON |
| GET | `/health` | `{"status":"ok"}` |

## Estructura

```
semana-14/
├── Dockerfile
├── .dockerignore
├── pom.xml
├── README.md
└── src/main/java/co/edu/corhuila/App.java
```

## El Dockerfile

Usa dos etapas (*multi-stage build*):

1. **build:** imagen con Maven + JDK 17 que compila el proyecto y genera `target/app.jar`.
2. **final:** imagen `eclipse-temurin:17-jre` (solo el JRE, más liviana) que copia el `.jar` y lo ejecuta.

Así no necesitas tener Java ni Maven instalados en tu equipo: solo Docker.

## 1. Construir la imagen

```bash
docker build -t backend-semana14 .
```

## 2. Correr el contenedor

```bash
docker run -d -p 8080:8080 --name app-semana14 backend-semana14
```

`-p 8080:8080` mapea el puerto 8080 de tu máquina al 8080 del contenedor.

## 3. Verificar que la app responde

```bash
curl http://localhost:8080
curl http://localhost:8080/health
docker ps
docker logs app-semana14
```

También puedes abrir `http://localhost:8080` en el navegador.

## Limpieza

```bash
docker stop app-semana14
docker rm app-semana14
```

## Evidencia

1. Salida de `docker build`
2. `docker ps` con el contenedor corriendo
3. Respuesta de la app en el navegador o con `curl`

<!-- Agrega aquí tus capturas de pantalla -->

## Evidencia

![Build, run y respuesta de la app](evidencia/captura-docker.png)
