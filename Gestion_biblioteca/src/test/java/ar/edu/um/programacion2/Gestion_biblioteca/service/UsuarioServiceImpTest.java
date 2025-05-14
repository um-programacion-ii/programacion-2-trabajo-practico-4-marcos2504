package ar.edu.um.programacion2.Gestion_biblioteca.service;

import ar.edu.um.programacion2.Gestion_biblioteca.model.Usuario;
import ar.edu.um.programacion2.Gestion_biblioteca.repository.UsuarioRepository;
import ar.edu.um.programacion2.Gestion_biblioteca.service.impl.UsuarioServiceImp;
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
class UsuarioServiceImpTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImp usuarioService;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Juan");
        usuario.setEmail("juan@email.com");
    }

    @Test
    void guardarUsuario_deberiaRetornarUsuarioGuardado() {
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioService.guardar(usuario);

        assertEquals(usuario, resultado);
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void buscarPorId_existente_deberiaRetornarUsuario() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.buscarPorId(1L);

        assertEquals(usuario, resultado);
        verify(usuarioRepository).findById(1L);
    }

    @Test
    void buscarPorId_inexistente_deberiaLanzarExcepcion() {
        when(usuarioRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> usuarioService.buscarPorId(2L));
    }

    @Test
    void obtenerTodos_deberiaRetornarListaDeUsuarios() {
        Usuario otro = new Usuario();
        otro.setId(2L);
        otro.setNombre("Ana");
        otro.setEmail("ana@email.com");

        List<Usuario> lista = Arrays.asList(usuario, otro);
        when(usuarioRepository.findAll()).thenReturn(lista);

        List<Usuario> resultado = usuarioService.obtenerTodos();

        assertEquals(2, resultado.size());
        verify(usuarioRepository).findAll();
    }

    @Test
    void eliminarUsuario_deberiaLlamarADeleteById() {
        usuarioService.eliminar(1L);

        verify(usuarioRepository).deleteById(1L);
    }

    @Test
    void actualizar_existente_deberiaRetornarUsuarioActualizado() {
        when(usuarioRepository.existsById(1L)).thenReturn(true);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioService.actualizar(1L, usuario);

        assertEquals(usuario, resultado);
        assertEquals(1L, resultado.getId());
        verify(usuarioRepository).existsById(1L);
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void actualizar_inexistente_deberiaLanzarExcepcion() {
        when(usuarioRepository.existsById(1L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> usuarioService.actualizar(1L, usuario));
    }
}

