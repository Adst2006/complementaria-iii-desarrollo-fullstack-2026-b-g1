package com.fullstack.tareas.service;

import com.fullstack.tareas.exception.RecursoNoEncontradoException;
import com.fullstack.tareas.model.Tarea;
import com.fullstack.tareas.repository.TareaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TareaService {

    private final TareaRepository repository;

    public TareaService(TareaRepository repository) {
        this.repository = repository;
    }

    /** Lista todas las tareas; si completada no es null, filtra por estado. */
    @Transactional(readOnly = true)
    public List<Tarea> listar(Boolean completada) {
        if (completada == null) {
            return repository.findAll();
        }
        return repository.findByCompletada(completada);
    }

    @Transactional(readOnly = true)
    public Tarea obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Tarea con id " + id + " no encontrada"));
    }

    @Transactional
    public Tarea crear(Tarea tarea) {
        tarea.setId(null);
        return repository.save(tarea);
    }

    @Transactional
    public Tarea actualizar(Long id, Tarea datos) {
        Tarea existente = obtenerPorId(id);
        existente.setTitulo(datos.getTitulo());
        existente.setDescripcion(datos.getDescripcion());
        existente.setPrioridad(datos.getPrioridad());
        existente.setFechaLimite(datos.getFechaLimite());
        existente.setCompletada(datos.isCompletada());
        return repository.save(existente);
    }

    @Transactional
    public Tarea completar(Long id) {
        Tarea existente = obtenerPorId(id);
        existente.setCompletada(true);
        return repository.save(existente);
    }

    @Transactional
    public void eliminar(Long id) {
        Tarea existente = obtenerPorId(id);
        repository.delete(existente);
    }
}
