package TiendaManga.DTO;

import java.util.List;

import lombok.Data;

@Data
public class OrigenDTO {
    private Integer id_origen;
    private String nombre;
    private List<String> nombresMangas;
}
