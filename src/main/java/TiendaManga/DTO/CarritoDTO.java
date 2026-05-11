package TiendaManga.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CarritoDTO {

    private Integer id_carrito;
    private Integer cantidad;
    private LocalDate fecha_agregado;

}
