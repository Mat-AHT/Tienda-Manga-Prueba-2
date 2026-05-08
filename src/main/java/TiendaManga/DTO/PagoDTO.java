package TiendaManga.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PagoDTO {

    private Integer id_pago;
    private Integer monto;
    private String metodoPago;
    private LocalDateTime fechaTransaccion;

}
