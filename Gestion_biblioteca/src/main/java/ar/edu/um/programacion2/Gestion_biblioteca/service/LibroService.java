package ar.edu.um.programacion2.Gestion_biblioteca.service;

import ar.edu.um.programacion2.Gestion_biblioteca.model.Libro;

import java.util.List;

public interface LibroService {
    Libro buscarPorIsbn(String isbn);
    List<Libro> obtenerTodos();
    Libro guardar(Libro libro);
    void eliminar(Long id);
    Libro actualizar(Long id, Libro libro);
    Libro buscarPorId(Long id);
}

