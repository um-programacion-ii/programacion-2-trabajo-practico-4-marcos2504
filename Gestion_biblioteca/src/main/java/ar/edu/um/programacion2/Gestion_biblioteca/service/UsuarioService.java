package ar.edu.um.programacion2.Gestion_biblioteca.service;

import ar.edu.um.programacion2.Gestion_biblioteca.model.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario buscarPorId(Long id);
    List<Usuario> obtenerTodos();
    Usuario guardar(Usuario usuario);
    void eliminar(Long id);
    Usuario actualizar(Long id, Usuario usuario);
}

