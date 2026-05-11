package TiendaManga.Model;

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
@Table(name = "MANGA")
public class Manga {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id_manga;

    @Size(min = 4, max = 60)
    @NotBlank(message = "El nombre del Manga debe tener al menos 4 caracteres.")
    private String nombre;

    @Min(value = 0, message = "El valor minimo debe ser $0")
    @Max(value = 10000000, message = "El valor maximo debe ser $10.000.000")
    @NotNull
    private Integer precio;
    
    @Size(min = 4, max = 60)
    @NotBlank(message = "La sinopsis del Manga debe tener al menos 4 caracteres.")
    private String sinopsis;


    // Relaciones 
    @ManyToOne @JoinColumn(name = "id_autor")
    private Autor autor;
    
    @ManyToOne @JoinColumn(name = "id_genero")
    private Genero genero;

    @ManyToOne @JoinColumn(name = "id_demografia")
    private Demografia demografia;

    @ManyToOne @JoinColumn(name = "id_origen")
    private Origen origen;

}
