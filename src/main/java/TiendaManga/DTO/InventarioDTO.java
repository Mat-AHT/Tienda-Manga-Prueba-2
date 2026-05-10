package TiendaManga.DTO;

import java.util.List;

import lombok.Data;

@Data
public class InventarioDTO {
    private Integer id_inventario;
    private String nombre;
    private String bodega;
    private List<String> nombresMangas;
}
