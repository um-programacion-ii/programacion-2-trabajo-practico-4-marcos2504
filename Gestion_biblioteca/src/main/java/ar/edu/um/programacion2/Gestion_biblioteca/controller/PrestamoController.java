package ar.edu.um.programacion2.Gestion_biblioteca.controller;


import ar.edu.um.programacion2.Gestion_biblioteca.model.Prestamo;
import ar.edu.um.programacion2.Gestion_biblioteca.service.PrestamoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }
    /**
     * Obtiene todos los prestamos registrados.
     * @return Lista de prestamos.
     */
    @GetMapping
    public List<Prestamo> obtenerTodos() {
        return prestamoService.obtenerTodos();
    }
    /**
     * Obtiene un prestamo por su ID.
     * @param id ID del prestami.
     * @return prestamo correspondiente al ID.
     */
    @GetMapping("/{id}")
    public Prestamo obtenerPorId(@PathVariable Long id) {
        return prestamoService.buscarPorId(id);
    }
    /**
     * Crea un nuevo prestamo.
     * @param prestamo Datos del prestamo a crear.
     * @return pretamo creado.
     */
    @PostMapping
    public Prestamo crear(@RequestBody Prestamo prestamo) {
        return prestamoService.guardar(prestamo);
    }
    /**
     * Actualiza un prestamo existente.
     * @param id ID del prestamo a actualizar.
     * @param prestamo Datos nuevos del prestamo.
     * @return Prestamo actualizado.
     */
    @PutMapping("/{id}")
    public Prestamo actualizar(@PathVariable Long id, @RequestBody Prestamo prestamo) {
        return prestamoService.actualizar(id, prestamo);
    }
    /**
     * Elimina un prestamo por su ID.
     * @param id ID del prestamo a eliminar.
     */
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        prestamoService.eliminar(id);
    }
}

