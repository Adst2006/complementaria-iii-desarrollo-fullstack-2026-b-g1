package com.miapp.biblioteca.repository;

import com.miapp.biblioteca.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Consulta derivada: Spring Data JPA genera el SQL a partir del nombre del método
    Optional<Libro> findByIsbn(String isbn);

    List<Libro> findByAutor(String autor);

    List<Libro> findByDisponibleTrue();
}
