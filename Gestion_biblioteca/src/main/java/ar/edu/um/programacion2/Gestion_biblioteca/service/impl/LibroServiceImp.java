package ar.edu.um.programacion2.Gestion_biblioteca.service.impl;
import ar.edu.um.programacion2.Gestion_biblioteca.model.Libro;
import ar.edu.um.programacion2.Gestion_biblioteca.repository.LibroRepository;
import ar.edu.um.programacion2.Gestion_biblioteca.service.LibroService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LibroServiceImp implements LibroService {
    private final LibroRepository libroRepository;

    public LibroServiceImp(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public Libro buscarPorIsbn(String isbn) {
        return libroRepository.findByIsbn(isbn)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
    }

    @Override
    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }

    @Override
    public Libro guardar(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public void eliminar(Long id) {
        libroRepository.deleteById(id);
    }

    @Override
    public Libro actualizar(Long id, Libro libro) {
        if (!libroRepository.existsById(id)) {
            throw new RuntimeException("Libro no encontrado");
        }
        libro.setId(id);
        return libroRepository.save(libro);
    }
}

