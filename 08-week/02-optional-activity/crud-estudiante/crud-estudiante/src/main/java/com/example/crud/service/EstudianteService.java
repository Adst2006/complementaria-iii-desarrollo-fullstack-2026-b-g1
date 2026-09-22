package com.example.crud.service;

import com.example.crud.model.Estudiante;
import com.example.crud.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;

    @Autowired
    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    // CREATE
    public Estudiante crear(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    // READ (listar todos)
    public List<Estudiante> listarTodos() {
        return estudianteRepository.findAll();
    }

    // READ (obtener uno)
    public Estudiante obtenerPorId(Long id) {
        return estudianteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado con id: " + id));
    }

    // UPDATE
    public Estudiante actualizar(Long id, Estudiante datos) {
        Estudiante existente = obtenerPorId(id);
        existente.setNombre(datos.getNombre());
        existente.setApellido(datos.getApellido());
        existente.setCorreo(datos.getCorreo());
        existente.setPrograma(datos.getPrograma());
        return estudianteRepository.save(existente);
    }

    // DELETE
    public void borrar(Long id) {
        Estudiante existente = obtenerPorId(id);
        estudianteRepository.delete(existente);
    }
}
