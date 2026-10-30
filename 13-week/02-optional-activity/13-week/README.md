# Semana 13 · Pruebas de la aplicación

## Qué incluye
- **Backend** (`backend/`): servicio de tareas + API Express.
  - Pruebas en `backend/tests/tasks.test.js` (unitarias del servicio e integración de endpoints con supertest).
- **Frontend** (`frontend/`): componente React `TaskForm`.
  - Pruebas en `frontend/TaskForm.test.jsx` (React Testing Library).

## Casos de error / límite cubiertos
- Título vacío (servicio y endpoint → 400).
- Título de más de 100 caracteres y de exactamente 100.
- Tarea inexistente (endpoint → 404).
- Formulario vacío o solo con espacios: muestra error y no llama `onAdd`.

## Cómo ejecutar
```bash
npm install
npm test
```
