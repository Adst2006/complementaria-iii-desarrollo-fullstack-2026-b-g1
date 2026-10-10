package com.fullstack.tareas.controller;

import com.fullstack.tareas.model.Tarea;
import com.fullstack.tareas.service.TareaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tareas")
@Tag(name = "Tareas", description = "CRUD de tareas")
public class TareaController {

    private final TareaService service;

    public TareaController(TareaService service) {
        this.service = service;
    }

    @Operation(summary = "Listar tareas", description = "Devuelve todas las tareas. Con ?completada=true|false filtra por estado.")
    @ApiResponse(responseCode = "200", description = "Lista de tareas (puede estar vacía)")
    @GetMapping
    public List<Tarea> listar(
            @Parameter(description = "Filtrar por estado (opcional)")
            @RequestParam(required = false) Boolean completada) {
        return service.listar(completada);
    }

    @Operation(summary = "Obtener una tarea por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarea encontrada"),
            @ApiResponse(responseCode = "404", description = "La tarea no existe", content = @Content)
    })
    @GetMapping("/{id}")
    public Tarea obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @Operation(summary = "Crear una tarea")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Tarea creada"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Tarea> crear(@Valid @RequestBody Tarea tarea) {
        Tarea creada = service.crear(tarea);
        return ResponseEntity.created(URI.create("/api/tareas/" + creada.getId())).body(creada);
    }

    @Operation(summary = "Actualizar una tarea existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarea actualizada"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "La tarea no existe", content = @Content)
    })
    @PutMapping("/{id}")
    public Tarea actualizar(@PathVariable Long id, @Valid @RequestBody Tarea tarea) {
        return service.actualizar(id, tarea);
    }

    @Operation(summary = "Marcar una tarea como completada")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarea completada"),
            @ApiResponse(responseCode = "404", description = "La tarea no existe", content = @Content)
    })
    @PatchMapping("/{id}/completar")
    public Tarea completar(@PathVariable Long id) {
        return service.completar(id);
    }

    @Operation(summary = "Eliminar una tarea")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Tarea eliminada"),
            @ApiResponse(responseCode = "404", description = "La tarea no existe", content = @Content)
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
