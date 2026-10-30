// Servicio de tareas (lógica de negocio) -- almacenamiento en memoria
export function createTaskService() {
  const tasks = [];
  let nextId = 1;

  return {
    list() {
      return [...tasks];
    },

    add(title) {
      if (typeof title !== "string" || title.trim() === "") {
        throw new Error("El título es obligatorio");
      }
      if (title.trim().length > 100) {
        throw new Error("El título no puede superar 100 caracteres");
      }
      const task = { id: nextId++, title: title.trim(), done: false };
      tasks.push(task);
      return task;
    },

    toggle(id) {
      const task = tasks.find((t) => t.id === id);
      if (!task) throw new Error("Tarea no encontrada");
      task.done = !task.done;
      return task;
    },
  };
}
