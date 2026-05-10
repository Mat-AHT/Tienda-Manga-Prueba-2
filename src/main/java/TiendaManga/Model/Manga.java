package TiendaManga.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "Mangas")
public class Manga {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id_manga;

    @Size(min = 4, max = 60)
    @NotBlank(message = "El nombre del Manga debe tener al menos 4 caracteres.")
    private String nombre;

    @Size(min = 0, max = 10)
    @NotNull(message = "El precio no puede quedar en 0")
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
