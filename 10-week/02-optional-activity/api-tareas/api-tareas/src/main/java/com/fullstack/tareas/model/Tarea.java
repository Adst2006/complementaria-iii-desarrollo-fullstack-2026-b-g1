package com.fullstack.tareas.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "tareas")
@Schema(description = "Tarea de la lista de pendientes")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador generado automáticamente", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 100, message = "El título no puede superar 100 caracteres")
    @Schema(description = "Título de la tarea", example = "Entregar actividad de la semana 10")
    private String titulo;

    @Size(max = 255, message = "La descripción no puede superar 255 caracteres")
    @Schema(description = "Detalle de la tarea", example = "Subir el repositorio con README, Swagger y Postman")
    private String descripcion;

    @NotNull(message = "La prioridad es obligatoria")
    @Enumerated(EnumType.STRING)
    @Schema(description = "Prioridad de la tarea", example = "ALTA")
    private Prioridad prioridad;

    @Schema(description = "Fecha límite (formato AAAA-MM-DD)", example = "2026-10-15")
    private LocalDate fechaLimite;

    @Schema(description = "Indica si la tarea ya fue completada", example = "false")
    private boolean completada;

    public Tarea() {
    }

    public Tarea(String titulo, String descripcion, Prioridad prioridad, LocalDate fechaLimite, boolean completada) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.fechaLimite = fechaLimite;
        this.completada = completada;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Prioridad getPrioridad() { return prioridad; }
    public void setPrioridad(Prioridad prioridad) { this.prioridad = prioridad; }

    public LocalDate getFechaLimite() { return fechaLimite; }
    public void setFechaLimite(LocalDate fechaLimite) { this.fechaLimite = fechaLimite; }

    public boolean isCompletada() { return completada; }
    public void setCompletada(boolean completada) { this.completada = completada; }
}
