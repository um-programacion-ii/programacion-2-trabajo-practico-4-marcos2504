package ar.edu.um.programacion2.Gestion_biblioteca.service;

import ar.edu.um.programacion2.Gestion_biblioteca.exception.LibroNoEncontradoException;
import ar.edu.um.programacion2.Gestion_biblioteca.model.Libro;
import ar.edu.um.programacion2.Gestion_biblioteca.model.enums.EstadoLibro;
import ar.edu.um.programacion2.Gestion_biblioteca.repository.LibroRepository;
import ar.edu.um.programacion2.Gestion_biblioteca.service.impl.LibroServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LibroServiceImpTest {

    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroServiceImp libroService;

    private Libro libro;

    @BeforeEach
    void setUp() {
        libro = new Libro(1L, "ISBN123", "Título", "Autor", EstadoLibro.DISPONIBLE);
    }

    @Test
    void guardarLibro_deberiaRetornarLibroGuardado() {
        when(libroRepository.save(libro)).thenReturn(libro);

        Libro resultado = libroService.guardar(libro);

        assertEquals(libro, resultado);
        verify(libroRepository).save(libro);
    }

    @Test
    void buscarPorIsbn_existente_deberiaRetornarLibro() {
        when(libroRepository.findByIsbn("ISBN123")).thenReturn(Optional.of(libro));

        Libro resultado = libroService.buscarPorIsbn("ISBN123");

        assertEquals(libro, resultado);
        verify(libroRepository).findByIsbn("ISBN123");
    }

    @Test
    void buscarPorIsbn_inexistente_deberiaLanzarExcepcion() {
        when(libroRepository.findByIsbn("NO_EXISTE")).thenReturn(Optional.empty());

        assertThrows(LibroNoEncontradoException.class, () -> libroService.buscarPorIsbn("NO_EXISTE"));
    }

    @Test
    void buscarPorId_existente_deberiaRetornarLibro() {
        when(libroRepository.findById(1L)).thenReturn(Optional.of(libro));

        Libro resultado = libroService.buscarPorId(1L);

        assertEquals(libro, resultado);
        verify(libroRepository).findById(1L);
    }

    @Test
    void buscarPorId_inexistente_deberiaLanzarExcepcion() {
        when(libroRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(LibroNoEncontradoException.class, () -> libroService.buscarPorId(99L));
    }

    @Test
    void obtenerTodos_deberiaRetornarListaDeLibros() {
        List<Libro> lista = Arrays.asList(libro, new Libro(2L, "ISBN999", "Otro", "Autor2", EstadoLibro.PRESTADO));
        when(libroRepository.findAll()).thenReturn(lista);

        List<Libro> resultado = libroService.obtenerTodos();

        assertEquals(2, resultado.size());
        verify(libroRepository).findAll();
    }

    @Test
    void eliminarLibro_deberiaLlamarADeleteById() {
        libroService.eliminar(1L);

        verify(libroRepository).deleteById(1L);
    }

    @Test
    void actualizar_existente_deberiaRetornarLibroActualizado() {
        when(libroRepository.existsById(1L)).thenReturn(true);
        when(libroRepository.save(libro)).thenReturn(libro);

        Libro resultado = libroService.actualizar(1L, libro);

        assertEquals(libro, resultado);
        assertEquals(1L, resultado.getId());
        verify(libroRepository).existsById(1L);
        verify(libroRepository).save(libro);
    }

    @Test
    void actualizar_inexistente_deberiaLanzarExcepcion() {
        when(libroRepository.existsById(1L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> libroService.actualizar(1L, libro));
    }
}

