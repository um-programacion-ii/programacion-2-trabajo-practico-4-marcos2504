package ar.edu.um.programacion2.Gestion_biblioteca.controller;



import ar.edu.um.programacion2.Gestion_biblioteca.model.Libro;
import ar.edu.um.programacion2.Gestion_biblioteca.service.LibroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    /**
     * Obtiene todos los libros registrados.
     * @return Lista de libros.
     */
    @GetMapping
    public List<Libro> obtenerTodos() {
        return libroService.obtenerTodos();
    }

    /**
     * Obtiene un libro por su ID.
     * @param id ID del libro.
     * @return Libro correspondiente al ID.
     */
    @GetMapping("/{id}")
    public Libro obtenerPorId(@PathVariable Long id) {
        return libroService.buscarPorId(id);
    }

    /**
     * Crea un nuevo libro.
     * @param libro Datos del libro a crear.
     * @return Libro creado.
     */
    @PostMapping
    public Libro crear(@RequestBody Libro libro) {
        return libroService.guardar(libro);
    }

    /**
     * Actualiza un libro existente.
     * @param id ID del libro a actualizar.
     * @param libro Datos nuevos del libro.
     * @return Libro actualizado.
     */
    @PutMapping("/{id}")
    public Libro actualizar(@PathVariable Long id, @RequestBody Libro libro) {
        return libroService.actualizar(id, libro);
    }
    /**
     * Elimina un libro por su ID.
     * @param id ID del libro a eliminar.
     */
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        libroService.eliminar(id);
    }
}

