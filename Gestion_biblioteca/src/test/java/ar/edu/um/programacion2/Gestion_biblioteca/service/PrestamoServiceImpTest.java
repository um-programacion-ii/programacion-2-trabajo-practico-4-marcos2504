package ar.edu.um.programacion2.Gestion_biblioteca.service;

import ar.edu.um.programacion2.Gestion_biblioteca.model.Libro;
import ar.edu.um.programacion2.Gestion_biblioteca.model.Prestamo;
import ar.edu.um.programacion2.Gestion_biblioteca.model.Usuario;
import ar.edu.um.programacion2.Gestion_biblioteca.repository.PrestamoRepository;
import ar.edu.um.programacion2.Gestion_biblioteca.service.impl.PrestamoServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PrestamoServiceImpTest {

    @Mock
    private PrestamoRepository prestamoRepository;

    @InjectMocks
    private PrestamoServiceImp prestamoService;

    private Prestamo prestamo;

    @BeforeEach
    void setUp() {
        Libro libro = new Libro();
        libro.setId(1L);
        libro.setTitulo("Libro A");

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Pedro");

        prestamo = new Prestamo();
        prestamo.setId(1L);
        prestamo.setLibro(libro);
        prestamo.setUsuario(usuario);
        prestamo.setFechaPrestamo(LocalDate.of(2024, 5, 1));
        prestamo.setFechaDevolucion(LocalDate.of(2024, 5, 10));
    }

    @Test
    void guardar_deberiaRetornarPrestamoGuardado() {
        when(prestamoRepository.save(prestamo)).thenReturn(prestamo);

        Prestamo resultado = prestamoService.guardar(prestamo);

        assertEquals(prestamo, resultado);
        verify(prestamoRepository).save(prestamo);
    }

    @Test
    void buscarPorId_existente_deberiaRetornarPrestamo() {
        when(prestamoRepository.findById(1L)).thenReturn(Optional.of(prestamo));

        Prestamo resultado = prestamoService.buscarPorId(1L);

        assertEquals(prestamo, resultado);
        verify(prestamoRepository).findById(1L);
    }

    @Test
    void buscarPorId_inexistente_deberiaLanzarExcepcion() {
        when(prestamoRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> prestamoService.buscarPorId(2L));
    }

    @Test
    void obtenerTodos_deberiaRetornarListaDePrestamos() {
        Prestamo otro = new Prestamo();
        otro.setId(2L);

        when(prestamoRepository.findAll()).thenReturn(Arrays.asList(prestamo, otro));

        List<Prestamo> resultado = prestamoService.obtenerTodos();

        assertEquals(2, resultado.size());
        verify(prestamoRepository).findAll();
    }

    @Test
    void eliminar_deberiaLlamarADeleteById() {
        prestamoService.eliminar(1L);

        verify(prestamoRepository).deleteById(1L);
    }

    @Test
    void actualizar_existente_deberiaRetornarPrestamoActualizado() {
        when(prestamoRepository.existsById(1L)).thenReturn(true);
        when(prestamoRepository.save(prestamo)).thenReturn(prestamo);

        Prestamo resultado = prestamoService.actualizar(1L, prestamo);

        assertEquals(prestamo, resultado);
        assertEquals(1L, resultado.getId());
        verify(prestamoRepository).existsById(1L);
        verify(prestamoRepository).save(prestamo);
    }

    @Test
    void actualizar_inexistente_deberiaLanzarExcepcion() {
        when(prestamoRepository.existsById(1L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> prestamoService.actualizar(1L, prestamo));
    }
}
