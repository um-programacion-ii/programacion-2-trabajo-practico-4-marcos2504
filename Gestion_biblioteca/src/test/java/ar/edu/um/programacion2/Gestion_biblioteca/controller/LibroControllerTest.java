package ar.edu.um.programacion2.Gestion_biblioteca.controller;

import ar.edu.um.programacion2.Gestion_biblioteca.model.Libro;
import ar.edu.um.programacion2.Gestion_biblioteca.model.enums.EstadoLibro;
import ar.edu.um.programacion2.Gestion_biblioteca.service.LibroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LibroControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private LibroService libroService;

    @Autowired
    private ObjectMapper objectMapper;

    private final Libro libroEjemplo = new Libro(1L, "123456789", "1984", "George Orwell", EstadoLibro.DISPONIBLE);

    @Test
    void testObtenerTodos() throws Exception {
        List<Libro> libros = List.of(libroEjemplo);

        Mockito.when(libroService.obtenerTodos()).thenReturn(libros);

        mockMvc.perform(get("/api/libros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].titulo").value("1984"));
    }

    @Test
    void testObtenerPorId() throws Exception {
        Mockito.when(libroService.buscarPorId(1L)).thenReturn(libroEjemplo);

        mockMvc.perform(get("/api/libros/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.autor").value("George Orwell"));
    }

    @Test
    void testCrearLibro() throws Exception {
        Libro nuevo = new Libro(null, "987654321", "Rebelión en la granja", "George Orwell", EstadoLibro.DISPONIBLE);
        Libro creado = new Libro(2L, "987654321", "Rebelión en la granja", "George Orwell", EstadoLibro.DISPONIBLE);

        Mockito.when(libroService.guardar(any(Libro.class))).thenReturn(creado);

        mockMvc.perform(post("/api/libros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nuevo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2));
    }

    @Test
    void testActualizarLibro() throws Exception {
        Libro actualizado = new Libro(1L, "123456789", "1984 (Ed. Revisada)", "George Orwell", EstadoLibro.DISPONIBLE);

        Mockito.when(libroService.actualizar(eq(1L), any(Libro.class))).thenReturn(actualizado);

        mockMvc.perform(put("/api/libros/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("1984 (Ed. Revisada)"));
    }

    @Test
    void testEliminarLibro() throws Exception {
        Mockito.doNothing().when(libroService).eliminar(1L);

        mockMvc.perform(delete("/api/libros/1"))
                .andExpect(status().isOk());
    }
}

