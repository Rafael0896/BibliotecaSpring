package edu.sena.bibliotecaspring.controller;

import edu.sena.bibliotecaspring.model.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import edu.sena.bibliotecaspring.service.LibroService;
@RestController
@RequestMapping("/api/libros")

public class LibroTestController {
    @Autowired
    private LibroService libroService; // Asegúrate de tener un servicio para manejar la lógica de negocio

    @GetMapping("/{id}")
    public ResponseEntity<Libro> getLibroById(@PathVariable Long id) {
        Libro libro = libroService.findById(id);
        if (libro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(libro);
    }
    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        Libro nuevoLibro = libroService.save(libro);
        return ResponseEntity.status(201).body(nuevoLibro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @RequestBody Libro libro) {
        Libro libroExistente = libroService.findById(id);
        if (libroExistente == null) {
            return ResponseEntity.notFound().build();
        }
        libro.setId(id); // Aseguramos que el ID se mantenga
        Libro libroActualizado = libroService.save(libro);
        return ResponseEntity.ok(libroActualizado);
    }
    //Metodo eliminar libro por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        Libro libroExistente = libroService.findById(id);
        if (libroExistente == null) {
            return ResponseEntity.notFound().build();
        }
        libroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}