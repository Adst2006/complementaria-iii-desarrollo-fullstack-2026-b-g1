import express from "express";
import { createTaskService } from "./taskService.js";

export function createApp(service = createTaskService()) {
  const app = express();
  app.use(express.json());

  app.get("/api/tasks", (_req, res) => {
    res.json(service.list());
  });

  app.post("/api/tasks", (req, res) => {
    try {
      const task = service.add(req.body?.title);
      res.status(201).json(task);
    } catch (err) {
      res.status(400).json({ error: err.message });
    }
  });

  app.patch("/api/tasks/:id/toggle", (req, res) => {
    try {
      res.json(service.toggle(Number(req.params.id)));
    } catch (err) {
      res.status(404).json({ error: err.message });
    }
  });

  return app;
}
