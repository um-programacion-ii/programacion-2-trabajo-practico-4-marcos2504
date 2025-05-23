package ar.edu.um.programacion2.Gestion_biblioteca.repository;
import ar.edu.um.programacion2.Gestion_biblioteca.model.Prestamo;

import java.util.List;
import java.util.Optional;

public interface PrestamoRepository {
    Prestamo save(Prestamo prestamo);
    Optional<Prestamo> findById(Long id);
    List<Prestamo> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}