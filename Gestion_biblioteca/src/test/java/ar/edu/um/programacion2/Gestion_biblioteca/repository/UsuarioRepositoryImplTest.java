package ar.edu.um.programacion2.Gestion_biblioteca.repository;



import ar.edu.um.programacion2.Gestion_biblioteca.model.Usuario;
import ar.edu.um.programacion2.Gestion_biblioteca.model.enums.EstadoUsuario;
import ar.edu.um.programacion2.Gestion_biblioteca.repository.UsuarioRepository;
import ar.edu.um.programacion2.Gestion_biblioteca.repository.impl.UsuarioRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioRepositoryImplTest {

    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        usuarioRepository = new UsuarioRepositoryImpl();
    }

    @Test
    void testGuardarUsuario() {
        Usuario usuario = new Usuario(null, "Juan", "juan@mail.com", EstadoUsuario.ACTIVO);

        Usuario guardado = usuarioRepository.save(usuario);

        assertNotNull(guardado.getId());
        assertEquals("Juan", guardado.getNombre());
        assertTrue(usuarioRepository.existsById(guardado.getId()));
    }

    @Test
    void testBuscarPorId() {
        Usuario usuario = new Usuario(null, "Ana", "ana@mail.com", EstadoUsuario.ACTIVO);
        Usuario guardado = usuarioRepository.save(usuario);

        Optional<Usuario> resultado = usuarioRepository.findById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Ana", resultado.get().getNombre());
    }

    @Test
    void testActualizarUsuario() {
        Usuario usuario = new Usuario(null, "Lucía", "lucia@mail.com", EstadoUsuario.ACTIVO);
        Usuario guardado = usuarioRepository.save(usuario);

        guardado.setNombre("Lucía González");
        usuarioRepository.save(guardado);

        Optional<Usuario> actualizado = usuarioRepository.findById(guardado.getId());
        assertTrue(actualizado.isPresent());
        assertEquals("Lucía González", actualizado.get().getNombre());
    }

    @Test
    void testEliminarUsuario() {
        Usuario usuario = new Usuario(null, "Pedro", "pedro@mail.com", EstadoUsuario.ACTIVO);
        Usuario guardado = usuarioRepository.save(usuario);
        Long id = guardado.getId();

        usuarioRepository.deleteById(id);

        assertFalse(usuarioRepository.existsById(id));
        assertTrue(usuarioRepository.findById(id).isEmpty());
    }

    @Test
    void testFindAll() {
        usuarioRepository.save(new Usuario(null, "Uno", "uno@mail.com", EstadoUsuario.ACTIVO));
        usuarioRepository.save(new Usuario(null, "Dos", "dos@mail.com", EstadoUsuario.ACTIVO));

        List<Usuario> todos = usuarioRepository.findAll();

        assertEquals(2, todos.size());
    }
}

