package ar.edu.um.programacion2.Gestion_biblioteca.model;

import ar.edu.um.programacion2.Gestion_biblioteca.model.enums.EstadoUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private Long id;
    private String nombre;
    private String email;
    private EstadoUsuario estado;
}
