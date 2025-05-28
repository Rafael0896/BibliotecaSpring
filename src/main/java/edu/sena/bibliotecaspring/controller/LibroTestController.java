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

    /*
    GET
    Función: Solicitar datos de un recurso específico
    Uso: Para recuperar información sin modificarla
    Ejemplo: Obtener lista de usuarios o detalles de un producto
     */
    @GetMapping("/{id}")
    public ResponseEntity<Libro> getLibroById(@PathVariable Long id) {
        Libro libro = libroService.findById(id);
        if (libro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(libro);
    }

    /*
    POST
    Función: Enviar datos para crear un nuevo recurso
    Uso: Para enviar información al servidor (formularios, uploads)
    Ejemplo: Crear un nuevo usuario o publicar un comentario
    Características: No es cacheable, no permanece en historial
     */
    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        Libro nuevoLibro = libroService.save(libro);
        return ResponseEntity.status(201).body(nuevoLibro);
    }
    /*
    PUT
    Función: Actualizar un recurso existente o crearlo si no existe
    Uso: Para modificar completamente un recurso
    Ejemplo: Actualizar todos los campos de un perfil de usuario
    Características: Es idempotente (múltiples llamadas iguales producen el mismo resultado)
     */
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
    /*
    DELETE
    Función: Eliminar un recurso específico
    Uso: Para borrar datos del servidor
    Ejemplo: Eliminar un usuario o producto
    Características: Puede tener restricciones de seguridad
     */

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