import request from "supertest";
import { describe, it, expect, beforeEach } from "vitest";
import { createApp } from "../app.js";
import { createTaskService } from "../taskService.js";

describe("Backend - servicio de tareas", () => {
  let service;

  beforeEach(() => {
    service = createTaskService();
  });

  it("agrega una tarea válida (camino feliz)", () => {
    const task = service.add("Estudiar pruebas");
    expect(task).toEqual({ id: 1, title: "Estudiar pruebas", done: false });
    expect(service.list()).toHaveLength(1);
  });

  it("CASO ERROR: rechaza un título vacío", () => {
    expect(() => service.add("   ")).toThrow("El título es obligatorio");
    expect(service.list()).toHaveLength(0);
  });

  it("CASO LÍMITE: rechaza un título de más de 100 caracteres", () => {
    expect(() => service.add("a".repeat(101))).toThrow(/100 caracteres/);
  });

  it("CASO LÍMITE: acepta un título de exactamente 100 caracteres", () => {
    expect(service.add("a".repeat(100)).title).toHaveLength(100);
  });
});

describe("Backend - endpoints (integración con supertest)", () => {
  let app;

  beforeEach(() => {
    app = createApp(createTaskService());
  });

  it("POST /api/tasks crea una tarea y responde 201", async () => {
    const res = await request(app)
      .post("/api/tasks")
      .send({ title: "Hacer entrega" });

    expect(res.status).toBe(201);
    expect(res.body).toMatchObject({ title: "Hacer entrega", done: false });
  });

  it("CASO ERROR: POST /api/tasks sin título responde 400", async () => {
    const res = await request(app).post("/api/tasks").send({});

    expect(res.status).toBe(400);
    expect(res.body.error).toBe("El título es obligatorio");
  });

  it("CASO ERROR: PATCH sobre una tarea inexistente responde 404", async () => {
    const res = await request(app).patch("/api/tasks/999/toggle");

    expect(res.status).toBe(404);
    expect(res.body.error).toBe("Tarea no encontrada");
  });

  it("GET /api/tasks devuelve las tareas creadas", async () => {
    await request(app).post("/api/tasks").send({ title: "Una" });
    const res = await request(app).get("/api/tasks");

    expect(res.status).toBe(200);
    expect(res.body).toHaveLength(1);
  });
});
