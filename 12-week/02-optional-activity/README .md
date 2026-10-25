# Semana 12 · Integrar frontend y backend

Frontend en HTML + JavaScript puro que consume la API con JWT de la semana 11.

```
12-week/
├── backend/    API (Express + JWT + CORS)
└── frontend/   index.html (login, token, datos protegidos)
```

## Ejecutar

### 1. Backend

```bash
cd 12-week/backend
npm install
npm start
```

API en `http://localhost:3000`.

### 2. Frontend

Abre `12-week/frontend/index.html` en el navegador. Mejor aún, sírvelo desde otro puerto (así se ve el CORS en acción):

- VS Code: extensión **Live Server** → clic derecho en `index.html` → *Open with Live Server*.
- O con Node: `npx serve frontend` (desde `12-week/`).

### 3. Probar

Usuario `admin`, contraseña `1234`.

1. Inicia sesión: el token se guarda en `localStorage`.
2. Pulsa **Cargar datos**: se llama a `/datos-secretos` con `Authorization: Bearer <token>`.
3. Prueba errores: contraseña incorrecta, apagar el backend, o borrar el token.

## Qué cubre la guía

- **Login y token**: `POST /login`, el token se guarda en `localStorage`.
- **Endpoint protegido**: `GET /datos-secretos` enviando el token, y se muestran los datos.
- **CORS**: el backend usa el paquete `cors` (`origin`, `methods` y `allowedHeaders` con `Authorization`), necesario porque el frontend corre en otro origen.
- **Carga**: mensaje "Cargando…" y botones deshabilitados mientras dura la petición (el backend añade un retraso de 600 ms; se cambia con `DELAY_MS`).
- **Errores**: mensajes distintos para credenciales incorrectas, sesión expirada (401, cierra sesión) y servidor caído o CORS bloqueado.

## Notas

- Si cambias el puerto del backend, actualiza `API_URL` en `frontend/index.html`.
- Guardar el token en `localStorage` es lo más simple para la práctica; en producción se prefieren cookies `HttpOnly`.
