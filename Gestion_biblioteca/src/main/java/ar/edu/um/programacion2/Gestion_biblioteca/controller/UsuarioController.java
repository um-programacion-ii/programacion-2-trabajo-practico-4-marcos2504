package ar.edu.um.programacion2.Gestion_biblioteca.controller;


import ar.edu.um.programacion2.Gestion_biblioteca.model.Usuario;
import ar.edu.um.programacion2.Gestion_biblioteca.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    /**
     * Obtiene todos los usuarios.
     * @return Lista de usuarios.
     */
    @GetMapping
    public List<Usuario> obtenerTodos() {
        return usuarioService.obtenerTodos();
    }
    /**
     * Obtiene un usuario por su ID.
     * @param id ID del usuario.
     * @return usario correspondiente al ID.
     */
    @GetMapping("/{id}")
    public Usuario obtenerPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }
    /**
     * Crea un nuevo usuario.
     * @param usuario Datos del usuario a crear.
     * @return Usuario creado.
     */
    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario) {
        return usuarioService.guardar(usuario);
    }
    /**
     * Actualiza un usuario existente.
     * @param id ID del usuario a actualizar.
     * @param usuario Datos nuevos del usuario.
     * @return Usuario actualizado.
     */
    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.actualizar(id, usuario);
    }
    /**
     * Elimina un usuario por su ID.
     * @param id ID del usuario  a eliminar.
     */
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
    }
}

