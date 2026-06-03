package TiendaManga.Model;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PAGO")
public class Pago {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id_pago;

    @NotNull(message = "El monto es obligatorio")
    @Min(value = 1, message = "El monto debe ser al menos 1")
    @Max(value = 10000000, message = "el monto maximo debe ser de $10.000.000")
    @Column(nullable = false, length = 10000000)
    private Integer monto;

    @NotBlank(message = "El método de pago es obligatorio")
    @Size(min = 4, max = 60)
    @Column(nullable = false, length = 60)
    private String metodoPago;

    @NotNull(message = "La fecha es obligatoria")
    @Column(nullable = false)
    private LocalDateTime fechaTransaccion;

    @ManyToOne
    @JoinColumn(name = "id_usuario") 
    private Usuario usuario;

}
