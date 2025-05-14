package ar.edu.um.programacion2.Gestion_biblioteca.repository;


import ar.edu.um.programacion2.Gestion_biblioteca.model.Libro;
import ar.edu.um.programacion2.Gestion_biblioteca.model.Prestamo;
import ar.edu.um.programacion2.Gestion_biblioteca.model.Usuario;
import ar.edu.um.programacion2.Gestion_biblioteca.service.impl.PrestamoServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PrestamoServiceImpTest {

    @Mock
    private PrestamoRepository prestamoRepository;

    @InjectMocks
    private PrestamoServiceImp prestamoService;

    private Prestamo prestamo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        prestamo = new Prestamo(1L, new Libro(1L, "123-456-789", "El Hobbit", "J.R.R. Tolkien", null),
                new Usuario(1L, "Juan", "juan@mail.com", null),
                LocalDate.now(), LocalDate.now().plusDays(10));
    }

    @Test
    void testBuscarPorId() {
        when(prestamoRepository.findById(1L)).thenReturn(Optional.of(prestamo));

        Prestamo resultado = prestamoService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("El Hobbit", resultado.getLibro().getTitulo());
    }

    @Test
    void testBuscarPorIdCuandoNoExiste() {
        when(prestamoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> prestamoService.buscarPorId(1L));
    }

    @Test
    void testGuardarPrestamo() {
        when(prestamoRepository.save(prestamo)).thenReturn(prestamo);

        Prestamo resultado = prestamoService.guardar(prestamo);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(prestamoRepository, times(1)).save(prestamo);
    }

    @Test
    void testEliminarPrestamo() {
        doNothing().when(prestamoRepository).deleteById(1L);

        prestamoService.eliminar(1L);

        verify(prestamoRepository, times(1)).deleteById(1L);
    }

    @Test
    void testActualizarPrestamo() {
        Prestamo prestamoActualizado = new Prestamo(1L, new Libro(1L, "123-456-789", "El Hobbit", "J.R.R. Tolkien", null),
                new Usuario(1L, "Juan", "juan@mail.com", null),
                LocalDate.now(), LocalDate.now().plusDays(20));

        when(prestamoRepository.existsById(1L)).thenReturn(true);
        when(prestamoRepository.save(prestamoActualizado)).thenReturn(prestamoActualizado);

        Prestamo resultado = prestamoService.actualizar(1L, prestamoActualizado);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals(LocalDate.now().plusDays(20), resultado.getFechaDevolucion());
        verify(prestamoRepository, times(1)).save(prestamoActualizado);
    }

    @Test
    void testActualizarPrestamoCuandoNoExiste() {
        when(prestamoRepository.existsById(1L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> prestamoService.actualizar(1L, prestamo));
    }
}

