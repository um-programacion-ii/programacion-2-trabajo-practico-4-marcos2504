package ar.edu.um.programacion2.Gestion_biblioteca.model;

import ar.edu.um.programacion2.Gestion_biblioteca.model.enums.EstadoLibro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Libro {
    private Long id;
    private String isbn;
    private String titulo;
    private String autor;
    private EstadoLibro estado;
}
