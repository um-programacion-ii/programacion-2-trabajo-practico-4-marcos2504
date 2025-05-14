package ar.edu.um.programacion2.Gestion_biblioteca.controller;

import ar.edu.um.programacion2.Gestion_biblioteca.model.*;
import ar.edu.um.programacion2.Gestion_biblioteca.model.enums.EstadoLibro;
import ar.edu.um.programacion2.Gestion_biblioteca.model.enums.EstadoUsuario;
import ar.edu.um.programacion2.Gestion_biblioteca.service.PrestamoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PrestamoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private PrestamoService prestamoService;

    @Autowired
    private ObjectMapper objectMapper;

    private final Usuario usuarioEjemplo = new Usuario(1L, "Juan", "juan@mail.com", EstadoUsuario.ACTIVO);
    private final Libro libroEjemplo = new Libro(1L, "123456789", "1984", "George Orwell", EstadoLibro.DISPONIBLE);

    @Test
    void testObtenerTodos() throws Exception {
        List<Prestamo> prestamos = List.of(
                new Prestamo(1L, libroEjemplo, usuarioEjemplo, LocalDate.now().minusDays(3), null),
                new Prestamo(2L, libroEjemplo, usuarioEjemplo, LocalDate.now().minusDays(10), LocalDate.now())
        );

        Mockito.when(prestamoService.obtenerTodos()).thenReturn(prestamos);

        mockMvc.perform(get("/api/prestamos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void testObtenerPorId() throws Exception {
        Prestamo prestamo = new Prestamo(1L, libroEjemplo, usuarioEjemplo, LocalDate.now().minusDays(5), null);

        Mockito.when(prestamoService.buscarPorId(1L)).thenReturn(prestamo);

        mockMvc.perform(get("/api/prestamos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.libro.titulo").value("1984"))
                .andExpect(jsonPath("$.usuario.nombre").value("Juan"));
    }

    @Test
    void testCrearPrestamo() throws Exception {
        Prestamo nuevo = new Prestamo(null, libroEjemplo, usuarioEjemplo, LocalDate.now(), null);
        Prestamo creado = new Prestamo(3L, libroEjemplo, usuarioEjemplo, LocalDate.now(), null);

        Mockito.when(prestamoService.guardar(any(Prestamo.class))).thenReturn(creado);

        mockMvc.perform(post("/api/prestamos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nuevo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3));
    }

    @Test
    void testActualizarPrestamo() throws Exception {
        Prestamo actualizado = new Prestamo(1L, libroEjemplo, usuarioEjemplo, LocalDate.now().minusDays(7), LocalDate.now());

        Mockito.when(prestamoService.actualizar(eq(1L), any(Prestamo.class))).thenReturn(actualizado);

        mockMvc.perform(put("/api/prestamos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fechaDevolucion").value(LocalDate.now().toString()));
    }

    @Test
    void testEliminarPrestamo() throws Exception {
        Mockito.doNothing().when(prestamoService).eliminar(1L);

        mockMvc.perform(delete("/api/prestamos/1"))
                .andExpect(status().isOk());
    }
}

