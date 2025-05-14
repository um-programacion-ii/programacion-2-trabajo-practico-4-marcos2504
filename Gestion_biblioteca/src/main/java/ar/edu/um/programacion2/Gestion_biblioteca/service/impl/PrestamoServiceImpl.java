package ar.edu.um.programacion2.Gestion_biblioteca.service.impl;


import ar.edu.um.programacion2.Gestion_biblioteca.model.Prestamo;
import ar.edu.um.programacion2.Gestion_biblioteca.repository.PrestamoRepository;
import ar.edu.um.programacion2.Gestion_biblioteca.service.PrestamoService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PrestamoServiceImpl implements PrestamoService {
    private final PrestamoRepository prestamoRepository;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    @Override
    public Prestamo buscarPorId(Long id) {
        return prestamoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));
    }

    @Override
    public List<Prestamo> obtenerTodos() {
        return prestamoRepository.findAll();
    }

    @Override
    public Prestamo guardar(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    @Override
    public void eliminar(Long id) {
        prestamoRepository.deleteById(id);
    }

    @Override
    public Prestamo actualizar(Long id, Prestamo prestamo) {
        if (!prestamoRepository.existsById(id)) {
            throw new RuntimeException("Préstamo no encontrado");
        }
        prestamo.setId(id);
        return prestamoRepository.save(prestamo);
    }
}

