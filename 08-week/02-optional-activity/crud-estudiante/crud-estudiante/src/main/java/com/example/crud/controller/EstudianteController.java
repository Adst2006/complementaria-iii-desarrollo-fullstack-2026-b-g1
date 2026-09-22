package com.example.crud.controller;

import com.example.crud.model.Estudiante;
import com.example.crud.service.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Recurso: /api/estudiantes (sustantivo en plural, como pide el punto 2 del enunciado)
@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;

    @Autowired
    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    // CREATE -> POST /api/estudiantes
    @PostMapping
    public ResponseEntity<Estudiante> crear(@Valid @RequestBody Estudiante estudiante) {
        Estudiante creado = estudianteService.crear(estudiante);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // READ (listar) -> GET /api/estudiantes
    @GetMapping
    public ResponseEntity<List<Estudiante>> listar() {
        return ResponseEntity.ok(estudianteService.listarTodos());
    }

    // READ (uno) -> GET /api/estudiantes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(estudianteService.obtenerPorId(id));
    }

    // UPDATE -> PUT /api/estudiantes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> actualizar(@PathVariable Long id, @Valid @RequestBody Estudiante estudiante) {
        return ResponseEntity.ok(estudianteService.actualizar(id, estudiante));
    }

    // DELETE -> DELETE /api/estudiantes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        estudianteService.borrar(id);
        return ResponseEntity.noContent().build();
    }
}
