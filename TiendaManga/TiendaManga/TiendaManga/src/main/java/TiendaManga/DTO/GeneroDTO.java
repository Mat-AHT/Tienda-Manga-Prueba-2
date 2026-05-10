package TiendaManga.DTO;

import java.util.List;

import lombok.Data;

@Data
public class GeneroDTO {
    private Integer id_genero;
    private String nombre;
    private List<String> nombresMangas;
}
