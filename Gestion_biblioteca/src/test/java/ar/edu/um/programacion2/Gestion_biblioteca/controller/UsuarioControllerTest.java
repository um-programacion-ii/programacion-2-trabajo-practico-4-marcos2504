package ar.edu.um.programacion2.Gestion_biblioteca.controller;


import ar.edu.um.programacion2.Gestion_biblioteca.model.Usuario;
import ar.edu.um.programacion2.Gestion_biblioteca.model.enums.EstadoUsuario;
import ar.edu.um.programacion2.Gestion_biblioteca.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testObtenerTodos() throws Exception {
        List<Usuario> usuarios = List.of(
                new Usuario(1L, "Juan", "juan@mail.com", EstadoUsuario.ACTIVO),
                new Usuario(2L, "Ana", "ana@mail.com", EstadoUsuario.INACTIVO)
        );

        Mockito.when(usuarioService.obtenerTodos()).thenReturn(usuarios);

        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].nombre").value("Juan"));
    }

    @Test
    void testObtenerPorId() throws Exception {
        Usuario usuario = new Usuario(1L, "Juan", "juan@mail.com", EstadoUsuario.ACTIVO);

        Mockito.when(usuarioService.buscarPorId(1L)).thenReturn(usuario);

        mockMvc.perform(get("/api/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"));
    }

    @Test
    void testCrearUsuario() throws Exception {
        Usuario usuario = new Usuario(null, "Lucia", "lucia@mail.com", EstadoUsuario.ACTIVO);
        Usuario creado = new Usuario(3L, "Lucia", "lucia@mail.com", EstadoUsuario.ACTIVO);

        Mockito.when(usuarioService.guardar(any(Usuario.class))).thenReturn(creado);

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.nombre").value("Lucia"));
    }

    @Test
    void testActualizarUsuario() throws Exception {
        Usuario actualizado = new Usuario(1L, "Juan Modificado", "juan@mail.com", EstadoUsuario.ACTIVO);

        Mockito.when(usuarioService.actualizar(eq(1L), any(Usuario.class))).thenReturn(actualizado);

        mockMvc.perform(put("/api/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan Modificado"));
    }

    @Test
    void testEliminarUsuario() throws Exception {
        Mockito.doNothing().when(usuarioService).eliminar(1L);

        mockMvc.perform(delete("/api/usuarios/1"))
                .andExpect(status().isOk());
    }
}

