# Semana 11 · Proteger la API con JWT

API en Node + Express con login que devuelve un JWT y endpoints protegidos con `Authorization: Bearer <token>`.

## Ejecutar

```bash
cd 11-week
npm install
npm start
```

Servidor en `http://localhost:3000`.

## Usuario de prueba

- usuario: `admin`
- contraseña: `1234`

## Endpoints

| Método | Ruta              | Protegido | Descripción                    |
|--------|-------------------|-----------|--------------------------------|
| GET    | `/`               | No        | Estado de la API               |
| POST   | `/login`          | No        | Devuelve `{ "token": "..." }`  |
| GET    | `/perfil`         | Sí        | Datos del usuario del token    |
| GET    | `/datos-secretos` | Sí        | Contenido protegido            |

## Probar el flujo

1. Sin token → **401**

```bash
curl -i http://localhost:3000/perfil
```

2. Login para obtener el token

```bash
curl -s -X POST http://localhost:3000/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"1234"}'
```

3. Con token → **200**

```bash
curl -i http://localhost:3000/perfil \
  -H "Authorization: Bearer TU_TOKEN_AQUI"
```

## Notas

- El secreto del JWT se lee de `JWT_SECRET` (por defecto un valor de ejemplo).
- El token expira en 1 hora.
- Las contraseñas se guardan con hash (bcrypt), no en texto plano.
