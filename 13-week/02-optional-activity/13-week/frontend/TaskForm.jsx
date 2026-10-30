import { useState } from "react";

export default function TaskForm({ onAdd }) {
  const [title, setTitle] = useState("");
  const [error, setError] = useState("");

  function handleSubmit(e) {
    e.preventDefault();
    if (title.trim() === "") {
      setError("El título es obligatorio");
      return;
    }
    setError("");
    onAdd(title.trim());
    setTitle("");
  }

  return (
    <form onSubmit={handleSubmit}>
      <label htmlFor="title">Nueva tarea</label>
      <input
        id="title"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
      />
      <button type="submit">Agregar</button>
      {error && <p role="alert">{error}</p>}
    </form>
  );
}
