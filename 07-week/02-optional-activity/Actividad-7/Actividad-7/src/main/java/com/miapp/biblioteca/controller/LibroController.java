package com.miapp.biblioteca.controller;

import com.miapp.biblioteca.model.Libro;
import com.miapp.biblioteca.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroRepository libroRepository;

    // CREATE
    @PostMapping
    public Libro crear(@RequestBody Libro libro) {
        return libroRepository.save(libro);
    }

    // READ (todos)
    @GetMapping
    public List<Libro> listar() {
        return libroRepository.findAll();
    }

    // READ (uno por id)
    @GetMapping("/{id}")
    public Libro obtener(@PathVariable Long id) {
        return libroRepository.findById(id).orElseThrow();
    }

    // UPDATE
    @PutMapping("/{id}")
    public Libro actualizar(@PathVariable Long id, @RequestBody Libro datos) {
        Libro libro = libroRepository.findById(id).orElseThrow();
        libro.setTitulo(datos.getTitulo());
        libro.setAutor(datos.getAutor());
        libro.setIsbn(datos.getIsbn());
        libro.setAnioPublicacion(datos.getAnioPublicacion());
        libro.setDisponible(datos.isDisponible());
        return libroRepository.save(libro);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        libroRepository.deleteById(id);
    }
}
