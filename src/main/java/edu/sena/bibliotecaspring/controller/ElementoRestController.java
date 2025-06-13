package edu.sena.bibliotecaspring.controller;

import edu.sena.bibliotecaspring.model.ElementoBiblioteca;
import edu.sena.bibliotecaspring.model.Libro;
import edu.sena.bibliotecaspring.model.Revista;
import edu.sena.bibliotecaspring.model.DVD;
import edu.sena.bibliotecaspring.service.LibroService;
import edu.sena.bibliotecaspring.service.RevistaService;
import edu.sena.bibliotecaspring.service.DVDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4173") // Permite CORS para tu frontend
public class ElementoRestController {

    @Autowired
    private LibroService libroService;

    @Autowired
    private RevistaService revistaService;

    @Autowired
    private DVDService dvdService;

    // GET /api/elementos - Obtener todos los elementos
    @GetMapping("/elementos")
    public ResponseEntity<List<ElementoBiblioteca>> getAllElementos() {
        List<ElementoBiblioteca> elementos = new ArrayList<>();

        // Agregar todos los libros
        elementos.addAll(libroService.findAll());

        // Agregar todas las revistas
        elementos.addAll(revistaService.findAll());

        // Agregar todos los DVDs
        elementos.addAll(dvdService.findAll());

        return ResponseEntity.ok(elementos);
    }

    // GET /api/libros - Obtener solo libros
    @GetMapping("/libros")
    public ResponseEntity<List<Libro>> getAllLibros() {
        List<Libro> libros = libroService.findAll();
        return ResponseEntity.ok(libros);
    }

    // GET /api/revistas - Obtener solo revistas
    @GetMapping("/revistas")
    public ResponseEntity<List<Revista>> getAllRevistas() {
        List<Revista> revistas = revistaService.findAll();
        return ResponseEntity.ok(revistas);
    }

    // GET /api/dvds - Obtener solo DVDs
    @GetMapping("/dvds")
    public ResponseEntity<List<DVD>> getAllDVDs() {
        List<DVD> dvds = dvdService.findAll();
        return ResponseEntity.ok(dvds);
    }

    // POST /api/libros - Crear nuevo libro
    @PostMapping("/libros")
    public ResponseEntity<Libro> createLibro(@RequestBody Libro libro) {
        Libro nuevoLibro = libroService.save(libro);
        return ResponseEntity.ok(nuevoLibro);
    }

    // PUT /api/libros/{id} - Actualizar libro
    @PutMapping("/libros/{id}")
    public ResponseEntity<Libro> updateLibro(@PathVariable Long id, @RequestBody Libro libro) {
        libro.setId(id);
        Libro libroActualizado = libroService.save(libro);
        return ResponseEntity.ok(libroActualizado);
    }

    // DELETE /api/libros/{id} - Eliminar libro
    @DeleteMapping("/libros/{id}")
    public ResponseEntity<Void> deleteLibro(@PathVariable Long id) {
        libroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Métodos similares para revistas y DVDs...
    @PostMapping("/revistas")
    public ResponseEntity<Revista> createRevista(@RequestBody Revista revista) {
        Revista nuevaRevista = revistaService.save(revista);
        return ResponseEntity.ok(nuevaRevista);
    }

    @PostMapping("/dvds")
    public ResponseEntity<DVD> createDVD(@RequestBody DVD dvd) {
        DVD nuevoDVD = dvdService.save(dvd);
        return ResponseEntity.ok(nuevoDVD);
    }
}