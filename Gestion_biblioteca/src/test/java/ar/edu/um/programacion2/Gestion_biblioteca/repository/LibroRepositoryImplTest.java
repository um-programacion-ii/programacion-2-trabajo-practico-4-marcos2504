package ar.edu.um.programacion2.Gestion_biblioteca.repository;


import ar.edu.um.programacion2.Gestion_biblioteca.model.Libro;
import ar.edu.um.programacion2.Gestion_biblioteca.model.enums.EstadoLibro;
import ar.edu.um.programacion2.Gestion_biblioteca.repository.impl.LibroRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibroRepositoryImplTest {

    private LibroRepository libroRepository;

    @BeforeEach
    void setUp() {
        libroRepository = new LibroRepositoryImpl();
    }

    @Test
    void cuandoGuardarLibro_entoncesTieneIdYSePuedeBuscar() {
        Libro libro = new Libro(null, "123-456", "Clean Code", "Robert C. Martin", EstadoLibro.DISPONIBLE);

        Libro guardado = libroRepository.save(libro);

        assertNotNull(guardado.getId());
        Optional<Libro> encontrado = libroRepository.findById(guardado.getId());
        assertTrue(encontrado.isPresent());
        assertEquals("Clean Code", encontrado.get().getTitulo());
    }

    @Test
    void cuandoBuscarPorIsbn_entoncesRetornaLibroCorrecto() {
        Libro libro = new Libro(null, "ABC-123", "Refactoring", "Martin Fowler", EstadoLibro.DISPONIBLE);
        libroRepository.save(libro);

        Optional<Libro> resultado = libroRepository.findByIsbn("ABC-123");

        assertTrue(resultado.isPresent());
        assertEquals("Refactoring", resultado.get().getTitulo());
    }

    @Test
    void cuandoEliminarLibro_entoncesYaNoExiste() {
        Libro libro = libroRepository.save(new Libro(null, "DEL-001", "Delete Me", "Anon", EstadoLibro.DISPONIBLE));

        libroRepository.deleteById(libro.getId());

        assertFalse(libroRepository.existsById(libro.getId()));
    }

    @Test
    void cuandoListarTodos_entoncesRetornaListaCompleta() {
        libroRepository.save(new Libro(null, "A", "Libro 1", "Autor", EstadoLibro.DISPONIBLE));
        libroRepository.save(new Libro(null, "B", "Libro 2", "Autor", EstadoLibro.DISPONIBLE));

        List<Libro> libros = libroRepository.findAll();

        assertEquals(2, libros.size());
    }

    @Test
    void cuandoNoExisteLibro_entoncesFindByIdRetornaEmpty() {
        Optional<Libro> resultado = libroRepository.findById(999L);

        assertTrue(resultado.isEmpty());
    }

    @Test
    void cuandoExistePorId_entoncesRetornaTrue() {
        Libro libro = libroRepository.save(new Libro(null, "XYZ", "Exists Test", "Author", EstadoLibro.DISPONIBLE));

        boolean existe = libroRepository.existsById(libro.getId());

        assertTrue(existe);
    }
}

