package com.example.crud.repository;

import com.example.crud.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    // JpaRepository ya provee: save, findAll, findById, deleteById, existsById, etc.
}
